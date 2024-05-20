package com.example.todoappdeel3.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String orderTitle;

    private double orderPrice;


    public LocalDateTime datum;

    @ManyToOne
    @JsonBackReference
    public CustomUser customUser;

    @ManyToMany
    @JsonManagedReference
    private List<Product> product;

    @ManyToMany
    @JsonManagedReference
    private List<ProductVariant> productVariants;

    @ManyToMany
    @JsonManagedReference
    private List<Options> options;


    public Order() {

    }

    public Order(String orderTitle, double orderPrice, LocalDateTime datum, List<Product> product, List<ProductVariant> productVariants, List<Options> options) {
        this.orderTitle = orderTitle;
        this.orderPrice = orderPrice;
        this.datum = datum;
        this.product = product;
        this.productVariants = productVariants;
        this.options = options;
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

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public List<ProductVariant> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(List<ProductVariant> productVariants) {
        this.productVariants = productVariants;
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }



}
