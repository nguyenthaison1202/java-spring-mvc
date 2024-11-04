package com.example.demo.controller.admin;

import com.example.demo.domain.Products;
import com.example.demo.domain.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UploadFileService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadFileService uploadFileService;
    public ProductController(ProductService productService, UploadFileService uploadFileService) {
        this.productService = productService;
        this.uploadFileService = uploadFileService;
    }
    @GetMapping("/admin/product")
    public String showProduct(Model model) {
        List<Products> products = productService.findAll();
        model.addAttribute("products", products);
        return "admin/product/showProduct";
    }
    @GetMapping("/admin/product/create")
    public String showCreateProduct(Model model) {
        model.addAttribute("product", new Products());
        return "admin/product/createProduct";
    }
    @PostMapping("/admin/product/create")
    public String createProduct(@ModelAttribute("product") @Valid Products product, BindingResult bindingResult, @RequestParam("imageFile") MultipartFile file) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors ) {
            System.out.println (">>>>>"+error.getField() + " - " + error.getDefaultMessage());
        }
        if (bindingResult.hasErrors()) {
            return "admin/product/createProduct";
        }
        String img = uploadFileService.uploadFile(file,"products");
        product.setImage(img);
        productService.saveProduct(product);
        return "redirect:/admin/product";
    }

    //product detail
    @GetMapping(value = "/admin/product/detail/{id}")
    public String showProductDetail(@PathVariable long id, Model model){
        Products product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "admin/product/showProductDetail";
    }

    @GetMapping(value = "/admin/product/delete/{id}")
    public String confirmDelete(@PathVariable long id, Model model){
        Products product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "admin/product/deleteProduct";
    }
    @PostMapping(value = "/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id, Model model){
        productService.deleteProductByID(id);
        return "redirect:/admin/product";
    }
    @GetMapping("/admin/product/update/{id}")
    public String showUpdateProduct(@PathVariable long id, Model model){
        Products product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "admin/product/updateProduct";
    }
    @PostMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable long id, Model model,@RequestParam("imageFile") MultipartFile file, @ModelAttribute("Product") Products products){
        Products current_product = productService.findProductById(id);
        current_product.setName(products.getName());
        current_product.setPrice(products.getPrice());
        current_product.setFactory(products.getFactory());
        current_product.setQuantity(products.getQuantity());
        current_product.setShortDesc(products.getShortDesc());
        current_product.setTarget(products.getTarget());
        current_product.setDetailDesc(products.getDetailDesc());
        String image = uploadFileService.uploadFile(file,"products");
        current_product.setImage(image);
        productService.saveProduct(current_product);

        return "redirect:/admin/product";
    }
}
