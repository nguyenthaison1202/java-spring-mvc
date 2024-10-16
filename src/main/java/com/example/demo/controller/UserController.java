package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//public class UserController {
//    private UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//    @GetMapping("/")
//    public String getHomePage(){
//        return userService.handleHello();
//    }
//}
@Controller
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/")
    public String getHomePage(){
        String test = userService.handleHello();
        return "Home.html";
    }
}