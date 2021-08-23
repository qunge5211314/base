package com.canal.basic.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.canal.basic.model.User;
import com.canal.basic.response.ApiResponse;
import com.canal.basic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.support.InvocableHandlerMethod;

import java.util.List;
import java.util.Map;


@Controller
@ResponseBody
@RequestMapping(value = "/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${basic.response.message.success}")
    private String successMessage;

    @GetMapping(value = "/auth/code")
    public ApiResponse authCode(@RequestParam(name = "email") String email,
                                @RequestParam(name = "operationCode") Long operationCode) {
        userService.sendAuthCode(operationCode, email);
        return ApiResponse.success(successMessage);
    }

    @PostMapping(value = "/register")
    public ApiResponse register(@RequestParam("authCode") String authCode,
                                @RequestParam(name = "email") String email,
                                @RequestParam(name = "name") String name,
                                @RequestParam(name = "password") String password,
                                @RequestParam(name = "gender", required = false) Boolean gender) {
        userService.register(email, name, password, gender, authCode);
        return ApiResponse.success(successMessage);
    }

    @PostMapping(value = "/login/pwd")
    public ApiResponse passwordLogin(@RequestParam(required = true) String name) {
        return ApiResponse.success(successMessage, name);
    }

    @PostMapping(value = "/login/auth/code")
    public ApiResponse authCodeLogin(@RequestParam(name = "email") String email,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam("authCode") String authCode) {
        return ApiResponse.success(successMessage);
    }

    @PostMapping(value = "/update/name")
    public ApiResponse updateName(@RequestParam(name = "userId") Long userId,
                                  @RequestParam(name = "newName") String newName) {
        userService.updateUserName(userId, newName);
        return ApiResponse.success(successMessage);
    }

    @GetMapping(value = "/list")
    public ApiResponse getUserList(@RequestParam(name = "idList")List<Long> idList){
        List<User> userList = userService.getUserList(idList);
        return ApiResponse.success(successMessage, userList);
    }
}
