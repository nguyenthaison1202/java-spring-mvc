package com.example.demo.controller.client;

import com.example.demo.domain.Products;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomePageController {
    private final ProductService productService;
    public HomePageController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/")
    public String homePage(Model model) {
        List<Products> products = productService.findAll();
        model.addAttribute("products", products);
        return "/client/homepage/show";
    }
    @GetMapping("/register")
    public String registerPage(Model model) {
        return "/client/auth/register";
    }
}
