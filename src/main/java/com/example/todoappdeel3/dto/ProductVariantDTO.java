package com.example.todoappdeel3.dto;

import com.example.todoappdeel3.models.Options;
import com.example.todoappdeel3.models.Product;

import java.util.List;

public class ProductVariantDTO {

    public String name;

    public String description;

    public Product product;

    public List<OptionsDTO> foundOptions;

    public ProductVariantDTO(String name, String description, Product product, List<OptionsDTO> foundOptions) {
        this.name = name;
        this.description = description;
        this.product = product;
        this.foundOptions = foundOptions;
    }


}
