package com.example.todoappdeel3.dto;

import java.util.List;

public class OrderDTO {
    private String email;
    private List<Long> productIds;


    public OrderDTO(String email, List<Long> productIds) {
        this.email = email;
        this.productIds = productIds;
    }


}
