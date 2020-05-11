package com.muttsapp.service;

import com.muttsapp.mapper.UserMapper;
import com.muttsapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserLoginService {

    @Autowired
    UserMapper userMapper;

    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    public void saveUser(User user) {
        userMapper.insertUser(user);
    }
}
