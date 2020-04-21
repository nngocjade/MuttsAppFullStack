package com.muttsapp.controller;


import com.muttsapp.model.CustomResponseObject;
import com.muttsapp.model.User;
import com.muttsapp.model.UserChats;
import com.muttsapp.service.ChatService;
import com.muttsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public User getUserById(
            @PathVariable(value="id") int id){
                return userService.getUserById(id);
    }

    @GetMapping("/{id}/chats")
    public CustomResponseObject<ArrayList<UserChats>> getAllChatsByUserId(
            @PathVariable(value="id") int id
    ){
        CustomResponseObject<ArrayList<UserChats>> obj = new CustomResponseObject<>();
        obj.setData(chatService.getAllUserChats(id));
        return obj;
    }

//    @GetMapping("/{id}/chats/{other_id}")
//    public CustomResponseObject

}
