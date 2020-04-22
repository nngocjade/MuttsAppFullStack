package com.muttsapp.service;

import com.muttsapp.mapper.ChatMapper;
import com.muttsapp.mapper.UserMapper;
import com.muttsapp.model.User;
import com.muttsapp.model.UserChats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ChatMapper chatMapper;

    public ArrayList<User> getAllUsers(){
        return userMapper.getAllUsers();
    }

    public User findUserIdByEmail(String email) {
        return userMapper.findUserIDByEmail(email);
    }

    public ArrayList<User> getUserById(int user_id) {
        return userMapper.findUserById(user_id);
    }

    public ArrayList<UserChats> getSpecificChat(int id, int other_id) {
        int chat_id = chatMapper.findChatIdForUserIds(id, other_id);
        return chatMapper.findMessageByChatId(chat_id);
    }
}
