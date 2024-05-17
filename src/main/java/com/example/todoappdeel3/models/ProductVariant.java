package com.example.todoappdeel3.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class ProductVariant {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String description;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Product product;


    public ProductVariant(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProductVariant(){

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
