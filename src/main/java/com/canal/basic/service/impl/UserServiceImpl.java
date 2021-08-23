package com.canal.basic.service.impl;

import com.canal.basic.enumeration.UserOperation;
import com.canal.basic.exception.DataNotFoundException;
import com.canal.basic.exception.ExistUserException;
import com.canal.basic.model.User;
import com.canal.basic.repository.UserRepository;
import com.canal.basic.service.UserCacheService;
import com.canal.basic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCacheService userCacheService;

    @Override
    public void register(String email, String name, String password, Boolean gender, String authCode) {
        userCacheService.verifyAuthCode(UserOperation.REGISTER.getCode(), email, authCode);
        User existUser = userRepository.findByNameOrEmail(name, email);
        if (existUser != null) throw new ExistUserException("注册失败，已存在用户名或邮箱");
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setGender(gender);
        newUser.setName(name);
        newUser.setPassword(password);
        User user = userRepository.save(newUser);
    }

    @Override
    public void sendAuthCode(Long operationCode, String email) {
        String authCode = userCacheService.generateAuthCode();
        userCacheService.saveAuthCode(operationCode, email, authCode);
        userCacheService.sendAuthCode(operationCode, email, authCode);
    }

    @Override
    public void updateUserName(Long userId, String newName) {
        User existUser = userRepository.findByIdNotAndName(userId, newName);
        if (existUser != null) throw new ExistUserException("该用户名已存在");
        Optional<User> current = userRepository.findById(userId);
        User user = current.orElse(null);
        if (user == null) throw new DataNotFoundException("未查询到该用户");
        user.setName(newName);
        userRepository.save(user);
    }

    @Override
    public List<User> getUserList(List<Long> idList) {
        List<User> users = userRepository.findUsersByIdIn(idList);
        return users;
    }
}
