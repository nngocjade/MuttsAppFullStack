package com.muttsapp.service;

import com.muttsapp.mapper.UserMapper;
import com.muttsapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public ArrayList<User> getAllUsers(){
        return userMapper.getAllUsers();
    }

    public User findUserIdByEmail(String email) {
        return userMapper.findUserIDByEmail(email);
    }

    public User getUserById(int user_id) {
        return userMapper.findUserById(user_id);
    }
}
