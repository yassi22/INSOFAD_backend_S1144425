package com.example.todoappdeel3.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String orderTitle;

    private double orderPrice;


    private LocalDate orderDate;

    @ManyToOne
    @JsonBackReference
    private CustomUser customUser;

    @ManyToMany
    @JsonManagedReference
    private List<Product> product;

    public Order() {

    }

    public Order(String orderTitle, double orderPrice, LocalDate orderDate, List<Product> product) {
        this.orderTitle = orderTitle;
        this.orderPrice = orderPrice;
        this.orderDate = orderDate;
        this.product = product;
    }

    public CustomUser getCustomUser() {
        return customUser;
    }

    public void setCustomUser(CustomUser customUser) {
        this.customUser = customUser;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
