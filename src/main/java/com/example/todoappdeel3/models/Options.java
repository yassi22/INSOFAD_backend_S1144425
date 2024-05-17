package com.example.todoappdeel3.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Options {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private double added_price;

    @ManyToOne
    @JsonBackReference
    public ProductVariant productVariant;

    public Options(String name, double added_price, ProductVariant productVariant) {
        this.name = name;
        this.added_price = added_price;
        this.productVariant = productVariant;
    }

    public Options(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAdded_price() {
        return added_price;
    }

    public void setAdded_price(double added_price) {
        this.added_price = added_price;
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
    }
}
