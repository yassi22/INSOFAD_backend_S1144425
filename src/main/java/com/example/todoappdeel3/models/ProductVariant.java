package com.example.todoappdeel3.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ProductVariant {

    @Id
    @GeneratedValue
    private long id;


}
