package com.example.todoappdeel3.services;


import com.example.todoappdeel3.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public double calculatePrice(List<Product> products) {
        double total = 0.0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public String makeName(List<Product> products){
        List<String> names = new ArrayList<>();
        for(Product product : products){
            names.add(product.getName());
        }

        return String.join(" , ",names);
    }


}
