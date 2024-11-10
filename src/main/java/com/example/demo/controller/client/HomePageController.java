package com.example.demo.controller.client;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.example.demo.domain.Products;
import com.example.demo.domain.User;
import com.example.demo.domain.dto.RegisterDTO;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class HomePageController {
    private final UserService userService;
    private final ProductService productService;
    private final PasswordEncoder passwordEncoder;

    public HomePageController(UserService userService, ProductService productService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.productService = productService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/")
    public String homePage(Model model) {
        List<Products> products = productService.findAll();
        model.addAttribute("products", products);
        return "/client/homepage/show";
    }
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "/client/auth/login";
    }
    @PostMapping("/login")
    public String loginPage(Model model) {
        List<Products> products = productService.findAll();
        model.addAttribute("products", products);
        return "redirect:/";
    }
    @GetMapping("/register")
    public String registerPage(@ModelAttribute("registerUser")RegisterDTO registerDTO, Model model) {
        model.addAttribute("registerDTO", registerDTO);
        return "/client/auth/register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("registerUser")@Valid RegisterDTO registerDTO, BindingResult bindingResult) {
        User user = userService.registerDTOtoUser(registerDTO);
//        List<FieldError> errors = bindingResult.getFieldErrors();
//        for (FieldError error : errors ) {
//            System.out.println (">>>>>"+error.getField() + " - " + error.getDefaultMessage());
//        }
        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole(userService.getRoleByName("USER"));
        userService.save(user);
        return "redirect:/login";
    }
    @GetMapping("/access-deny")
    public String showDenyPage() {
        return "client/auth/deny";
    }
}
