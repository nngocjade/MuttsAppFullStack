package com.muttsapp.service;

import com.muttsapp.mapper.ChatMapper;
import com.muttsapp.model.UserChats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ChatService {

    @Autowired
    ChatMapper chatMapper;

    public ArrayList<UserChats> getAllUserChats() {
        return chatMapper.findAllUserChatsById();
    }
}
