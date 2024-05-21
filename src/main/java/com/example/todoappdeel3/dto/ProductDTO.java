package com.example.todoappdeel3.dto;

import com.example.todoappdeel3.models.ProductVariant;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;
import java.util.Set;


public class ProductDTO {

    public long id;

    public String name;
    public String description;
    public double price;
    public String durability;
    public String fitting;
    public String imageURL;
    public Integer quantity;
    @JsonAlias("category_id")
    public long categoryId;
    public Set<ProductVariant> variants;

//    public Set<ProductVariant> foundVariants;

    public ProductDTO(String name, String description, double price, String durability, String fitting, String imageURL, Integer quantity, long categoryId,  Set<ProductVariant> variants) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.durability = durability;
        this.fitting = fitting;
        this.imageURL = imageURL;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.variants = variants;

    }


}
