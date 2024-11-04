package com.example.demo.controller.client;

import com.example.demo.domain.Products;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {
    private final ProductService productService;
    public ItemController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/product/{id}")
    public String getItemDetail(Model model, @PathVariable int id) {
        Products products = productService.findProductById(id);
        model.addAttribute("product", products);
        model.addAttribute("id", id);
        return "client/product/detail";
    }
}
