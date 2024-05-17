package com.example.todoappdeel3.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class ProductDTO {
    public String name;
    public String description;
    public double  price;
    public String durability;
    public String fitting;
    public String imageURL;
    public Integer stock;
    @JsonAlias("category_id")
    public long categoryId;

    public ProductDTO(String name, String description, double price, String durability, String color, String fitting, String imageURL, String size, Integer stock, long categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.durability = durability;
        this.fitting = fitting;
        this.imageURL = imageURL;
        this.stock = stock;
        this.categoryId = categoryId;
    }
}
