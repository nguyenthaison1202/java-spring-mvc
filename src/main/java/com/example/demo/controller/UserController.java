package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String getHomePage(Model model){
        String test = userService.handleHello();
        model.addAttribute("eric", test);
        return "Home";
    }
    @RequestMapping("/admin/user")
    public String getUserPage(@ModelAttribute("User") User user, Model model){
        String test = userService.handleHello();
        model.addAttribute("user", user);
        return "admin/user/create";
    }
    @RequestMapping(value="/admin/user/create1", method = RequestMethod.POST)
    public String createUserPage(@ModelAttribute("User") User user, Model model){

        return "Home";
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok(user);
    }
    @GetMapping
    public ResponseEntity<List<User>> getUser(){
//        userService.getAllUser();
        return ResponseEntity.ok(userService.getAllUser());
    }
}