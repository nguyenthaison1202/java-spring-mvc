package com.example.demo.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "order_detail")
public class Order_Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long quantity;
    private double price;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

    public Order_Detail() {}

    public Order_Detail(long id, long quantity, double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }
}
