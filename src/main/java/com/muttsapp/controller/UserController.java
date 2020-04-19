package com.muttsapp.controller;


import com.muttsapp.model.CustomResponseObject;
import com.muttsapp.model.User;
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





}
