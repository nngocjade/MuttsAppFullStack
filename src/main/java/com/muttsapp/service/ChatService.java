package com.muttsapp.service;

import com.muttsapp.mapper.ChatMapper;
import com.muttsapp.model.Message;
import com.muttsapp.model.UserChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    ChatMapper chatMapper;

    public ArrayList<UserChat> getAllUserChats(int id) {
        ArrayList<UserChat> chats = chatMapper.findAllUserChatsById(id);

        for (UserChat c : chats){
            Message message = chatMapper.getLastMessage(c.getChat_id());
            c.setPhoto_url(chatMapper.findPhotoURL(c.getSender_id()));
            c.setLast_message(message.getMessage());
            c.setDate_sent(message.getDate_sent());
        }
        return chats;
    }

    public void saveMessage(Message message) {
        chatMapper.insertMessage(message);
    }

    public List<UserChat> getUserChatById(int id) {
        return chatMapper.findAllUserChatsById(id);
    }

}
