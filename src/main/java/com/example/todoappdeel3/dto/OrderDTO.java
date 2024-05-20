package com.example.todoappdeel3.dto;

import java.util.List;

public class OrderDTO {
    public String email;
    public List<Long> productIds;

    public List<Long> productVariantId;

    public List<Long> optionsId;


    public OrderDTO(String email, List<Long> productIds, List<Long> productVariantId, List<Long> optionsId) {
        this.email = email;
        this.productIds = productIds;
        this.productVariantId = productVariantId;
        this.optionsId = optionsId;
    }


}
