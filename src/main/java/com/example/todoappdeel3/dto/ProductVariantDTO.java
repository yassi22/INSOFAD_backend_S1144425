package com.example.todoappdeel3.dto;

import com.example.todoappdeel3.models.Options;
import com.example.todoappdeel3.models.Product;

import java.util.List;

public class ProductVariantDTO {

    public long id;

    public String name;

    public String description;

    public List<OptionsDTO> options;

    public ProductVariantDTO(long id, String name, String description, List<OptionsDTO> options) {
        this.name = name;
        this.description = description;
        this.options = options;
        this.id = id;
    }


}
