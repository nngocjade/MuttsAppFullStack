package com.muttsapp.service;

import com.muttsapp.mapper.ChatMapper;
import com.muttsapp.mapper.UserMapper;
import com.muttsapp.model.Message;
import com.muttsapp.model.User;
import com.muttsapp.model.UserChat;
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

        return userMapper.findAllUsers();
    }

    public User getUserByUserName(String user_name) {

        return userMapper.findUserByUserName(user_name);
    }

    public ArrayList<User> getUserById(int user_id) {

        return userMapper.findUserById(user_id);
    }

    public ArrayList<Message> getSpecificChat(int id, int other_id) {
        int chat_id = chatMapper.findChatIdForUserIds(id, other_id);
        return chatMapper.findMessagesByChatId(chat_id);
    }

//    public UserChat createNewUserChat(UserChat userChat, int id, int other_id) {
//        UserChat newUserChat = chatMapper.createNewUserChat(userChat);
//    }
}
