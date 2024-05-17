package com.example.todoappdeel3.dto;

import com.example.todoappdeel3.models.ProductVariant;

public class OptionsDTO {

    public String name;

    public String added_price;

    public ProductVariant productVariant;

    public OptionsDTO(String name, String added_price, ProductVariant productVariant) {
        this.name = name;
        this.added_price = added_price;
        this.productVariant = productVariant;
    }


}
