package com.example.demo.controller.client;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {
    @GetMapping("/product/{id}")
    public String getItemDetail(Model model,@PathVariable int id) {
        return "client/product/detail";
    }
}
