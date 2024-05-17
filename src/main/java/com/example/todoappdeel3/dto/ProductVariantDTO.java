package com.example.todoappdeel3.dto;

import com.example.todoappdeel3.models.Product;

public class ProductVariantDTO {

    public String name;

    public String description;

    public Product product;

    public ProductVariantDTO(String name, String description, Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }
}
