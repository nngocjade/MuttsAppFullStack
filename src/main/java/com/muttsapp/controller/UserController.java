package com.muttsapp.controller;


import com.muttsapp.model.CustomResponseObject;
import com.muttsapp.model.Message;
import com.muttsapp.model.User;
import com.muttsapp.model.UserChats;
import com.muttsapp.service.ChatService;
import com.muttsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ChatService chatService;

    @GetMapping
    public CustomResponseObject<ArrayList<User>> getAllUsers(){
        CustomResponseObject<ArrayList<User>> obj = new CustomResponseObject<>();
        obj.setData(userService.getAllUsers());
        return obj;
    }

    @GetMapping("/{id}")
    public CustomResponseObject<ArrayList<User>> getUserById(
            @PathVariable(value="id") int id){
        CustomResponseObject<ArrayList<User>> obj = new CustomResponseObject<>();
        obj.setData(userService.getUserById(id));
                return obj;
    }

    @GetMapping("/{id}/chats")
    public CustomResponseObject<ArrayList<UserChats>> getAllChatsByUserId(
            @PathVariable(value="id") int id
    ){
        CustomResponseObject<ArrayList<UserChats>> obj = new CustomResponseObject<>();
        obj.setData(chatService.getAllUserChats(id));
        return obj;
    }

//    -----------------GETTING SPECIFIC CHATS-------------------------

    @GetMapping("/{id}/chats/{other_id}")
    public CustomResponseObject<ArrayList<Message>> getSpecificChatByUserId(
            @PathVariable(value="id") int id,
            @PathVariable(value="other_id") int other_id
    ){
        CustomResponseObject<ArrayList<Message>> obj = new CustomResponseObject<>();
        obj.setData(userService.getSpecificChat(id, other_id));
        return obj;
    }

//    -------------------------POST REQUEST-----------------------------

//    @PostMapping("/{id}/chats/{other_id}")
//    public CustomResponseObject<UserChats>  createNewChat(
//            @PathVariable(value="id")int id,
//            @PathVariable(value="other_id")int other_id,
//            @RequestBody UserChats userChats){
//        CustomResponseObject<UserChats> obj = new CustomResponseObject<>();
//        obj.setData(userService.createNewUserChat(chat, id, other_id));
//        return obj;
//    }
}
