package com.example.demo.service;

import com.example.demo.domain.Products;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void saveProduct(Products products){
        productRepository.save(products);
    }
    public List<Products> findAll(){
        return productRepository.findAll();
    }

    public Products findUserById(long id) {
        return productRepository.findById(id).orElse(null);
    }
    public void deleteProductByID(long id) {
        productRepository.deleteById(id);
    }
}
