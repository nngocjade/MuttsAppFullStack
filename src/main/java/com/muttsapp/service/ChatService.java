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

    public ArrayList<UserChats> getAllUserChats(int id) {
        ArrayList<UserChats> chats = chatMapper.findAllUserChatsById(id);

        for (UserChats c : chats){
            c.setPhoto_url(chatMapper.findPhotoURL(c.getSender_id()));
            c.setLast_message(chatMapper.getLastMessage(c.getChat_id()));
        }
        return chats;
    }
}
