package com.example.todoappdeel3.dto;

import com.example.todoappdeel3.models.ProductVariant;

public class OptionsDTO {
    public long id;

    public String name;

    public double added_price;

    public OptionsDTO(long id,String name, double added_price) {
        this.id = id;
        this.name = name;
        this.added_price = added_price;
    }


}
