package com.example.todoappdeel3.models;

import com.example.todoappdeel3.dto.OptionsDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private String description;
    private String durability;
    private String fitting;
    private String imageUrl;
    private Integer quantity;
    private boolean isFinished = false;

    @ManyToMany
    @JsonBackReference
    private List<Order> order;

     /*
    Maps the many-to-one relationship between product and category, jsonbackreference so that we do not get an
    infinite dependency loop in the request. Cascasdetype merge so the product is able to create a category if we
    seed the data to the database. Without the merge you get a persistence race condition.
    */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    @Nullable
    private Category category;


    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    public Set<ProductVariant> variants;

//    @OneToMany(mappedBy = "product_variant_id")
//    @JsonManagedReference
//    @Nullable
//    private List<ProductVariant> foundVariants;


    //needed by JPA to create the entity must be present no arg constructor

    public Product() {

    }

    public Product(String name, String description, double price, Category category, String durability, String fitting, String imageUrl, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.durability = durability;
        this.fitting = fitting;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    //getters and setters are needed to map all the properties to the database by JPA, could
    //also be solved by making the properties public but gives less control over the properties.
    public Category getCategory() {
        return category;
    }

    public String getDurability() {
        return durability;
    }

    public void setDurability(String durability) {
        this.durability = durability;
    }


    public String getFitting() {
        return fitting;
    }

    public void setFitting(String fitting) {
        this.fitting = fitting;
    }

    public String getImageURL() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public void setCategory(Category category) {
        this.category = category;
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

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public Set<ProductVariant> getVariants() {
        return variants;
    }

    public void setVariants(Set<ProductVariant> variants) {
        this.variants = variants;
    }

//    public List<ProductVariant> getFoundVariants() {
//        return foundVariants;
//    }
//
//    public void setFoundVariants(List<ProductVariant> foundVariants) {
//        this.foundVariants = foundVariants;
//    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }




}
