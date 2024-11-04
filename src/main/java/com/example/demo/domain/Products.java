package com.example.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Size(min = 3,message = "Please fill name of product")
    private String name;
    @DecimalMin(value = "0",inclusive = false,message= "Price must have more than 0")
    private double price;
    private String image;
    @NotNull
    @NotEmpty(message = "Please fill detail of product")
    @Column(columnDefinition = "TEXT")
    private String detailDesc;
    @NotNull
    @NotEmpty(message = "Please fill short detail of product")
    private String shortDesc;

    @Min(value = 1, message = "Quantity must have more than 1")
    private long quantity;
    private long sold;
    private String factory;
    private String target;
    public Products() {}

    public Products(long id, String name, double price, String image, String detailDesc, String shortDesc, long quantity, long sold, String factory, String target) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.detailDesc = detailDesc;
        this.shortDesc = shortDesc;
        this.quantity = quantity;
        this.sold = sold;
        this.factory = factory;
        this.target = target;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getSold() {
        return sold;
    }

    public void setSold(long sold) {
        this.sold = sold;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
