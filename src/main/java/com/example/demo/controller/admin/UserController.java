package com.example.demo.controller.admin;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoleService;
import com.example.demo.service.UploadFileService;
import com.example.demo.service.UserService;
import jakarta.servlet.ServletContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final UploadFileService uploadFileService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UserRepository userRepository, RoleService roleService, UploadFileService uploadFileService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.uploadFileService = uploadFileService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model){
//        String test = userService.handleHello();
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "/admin/user/show";
    }
    //create user
    @GetMapping(value = "/admin/user/create")
    public String showCreateUserPage(@ModelAttribute("User") User user, Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return "admin/user/create_user";
    }
    @PostMapping(value = "/admin/user/create")
    public String createUserPage(@ModelAttribute("User") User user, @RequestParam("imageFile") MultipartFile file, Model model){
//        model.addAttribute("user", user);
//        model.addAttribute("roles", roleService.findAll());
//        userService.save(user);
        String avatar = uploadFileService.uploadFile(file,"avatar");
        user.setAvatar(avatar);
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
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