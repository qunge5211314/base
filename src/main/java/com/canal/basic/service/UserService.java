package com.canal.basic.service;

import com.canal.basic.model.User;

import java.util.List;

public interface UserService {
    void register(String email, String name, String password, Boolean gender, String authCode);

    void sendAuthCode(Long operationCode, String email);

    void updateUserName(Long userId, String newName);

    List<User> getUserList(List<Long> idList);
}
