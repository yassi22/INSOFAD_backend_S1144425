package com.example.todoappdeel3.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orderProducts")
public class OrderProduct {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JsonBackReference
    private Product product;

    private String imageUrl;

    private String name;

    private double price;
    @OneToMany
    private List<OrderProductVariant> orderProductVariants;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private Order order;


    public OrderProduct(Product product,  List<OrderProductVariant> orderProductVariants) {
        this.product = product;
        this.orderProductVariants = orderProductVariants;
        this.imageUrl = product.getImageURL();
    }

    public OrderProduct(Product product) {
        this.product = product;
        this.imageUrl = product.getImageURL();
        this.name = product.getName();
        this.price = product.getPrice();

    }

    public OrderProduct(){

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderProductVariant> getOrderProductVariants() {
        return orderProductVariants;
    }

    public void setOrderProductVariants(List<OrderProductVariant> orderProductVariants) {
        this.orderProductVariants = orderProductVariants;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
}
