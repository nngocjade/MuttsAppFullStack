package com.muttsapp.controller;


import com.muttsapp.model.User;
import com.muttsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/")
    public String home(){
        return "redirect:/index";
    }

    @RequestMapping(value="/index")
    public String getMainPage(Authentication auth, Model model){
//        UserDetails userDetails = (UserDetails) auth.getPrincipal();
//        System.out.println("User has authorities: " + userDetails.getAuthorities());
        System.out.println(auth.getName());
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("user_id", user.getUser_id());
        return "admin/muttsApp";
    }

    @RequestMapping(value="/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value="/403")
    public String Error403(){
        return "403";
    }

}
