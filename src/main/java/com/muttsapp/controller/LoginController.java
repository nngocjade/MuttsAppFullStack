package com.muttsapp.controller;


import com.muttsapp.exception.CustomException;
import com.muttsapp.model.User;
import com.muttsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

//MVC
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
        User user = userService.getUserByUserName(auth.getName());
        model.addAttribute("user_id", user.getUser_id());
        return "admin/muttsApp";
    }

//    @RequestMapping(value="/admin/index", method = RequestMethod.GET)
//    public String index(Authentication auth, Model model){
//        //model.addAttribute("user_id", name);
//        int user_id = userService.findUserByEmail(auth.getName()).getUser_id();
//        model.addAttribute("user_id", user_id);
//        return "admin/index";
//    }

    @RequestMapping(value="/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value="/logout")
    public String logout(){
        return "login";
    }

    @RequestMapping(value="/403")
    public String Error403(){
        return "403";
    }

    @RequestMapping(value={"/test"}, method = RequestMethod.GET)
    public ModelAndView test() throws CustomException {
        throw new CustomException(" ** Cuttlefish ** ");
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

}
