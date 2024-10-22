package com.example.demo.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //userId
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "orders")
    private List<Order_Detail> order_details;

    public Orders(long id, double totalPrice) {
        this.id = id;
        this.totalPrice = totalPrice;
    }
    public List<Order_Detail> getOrder_details() {
        return order_details;
    }

    public void setOrder_details(List<Order_Detail> order_details) {
        this.order_details = order_details;
    }

    public Orders() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
