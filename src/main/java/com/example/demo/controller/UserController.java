package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
//        String test = userService.handleHello();
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "admin/user/tableUsers";
    }
    @RequestMapping("/admin/user")
    public String getUserPage(Model model){
//        String test = userService.handleHello();
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "admin/user/tableUsers";
    }
    //create user
    @GetMapping(value = "/admin/user/create")
    public String showCreateUserPage(@ModelAttribute("User") User user, Model model){
        model.addAttribute("user", user);
        return "admin/user/create_user";
    }
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(@ModelAttribute("User") User user, Model model){
        model.addAttribute("user", user);
        userService.save(user);
        return "redirect:/admin/user";
    }
    //user detail
    @GetMapping(value = "/admin/user/detail/{id}")
    public String showUserDetail(@PathVariable long id, Model model){
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "admin/user/showUserDetail";
    }
///update user
    @GetMapping("/admin/user/update/{id}")
    public String showUpdateForm(@PathVariable long id, Model model){
        User user = userService.findUserById(id);
        model.addAttribute("user",user);
        return "admin/user/update_user";
    }
    @PostMapping("/admin/user/update/{id}")
    public String updateUser(@PathVariable long id,@ModelAttribute("user")User user, Model model){
        User current_user = userService.findUserById(id);
        current_user.setFullName(user.getFullName());
        current_user.setEmail(user.getPassword());
        current_user.setPhone(user.getPhone());
        current_user.setAddress(user.getAddress());
        userService.save(current_user);
        return "redirect:/admin/user/detail/" + id;
    }

    @GetMapping("/admin/user/delete/{id}")
    public String confirmDeleteUser(@PathVariable long id, Model model){
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "admin/user/delete_user";
    }
    @PostMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable long id){
        userService.deleteUserById(id);
        return "redirect:/admin/user";
    }
}