package com.example.todoappdeel3.dao;


import com.example.todoappdeel3.dto.OrderDTO;
import com.example.todoappdeel3.dto.ProductDTO;
import com.example.todoappdeel3.models.*;
import com.example.todoappdeel3.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDAO {

    private final OrderRepository orderRepository;

    private final ProductDAO productDAO;

    private final UserRepository userRepository;

    private final ProductService productService;

    private final CategoryDAO categoryDAO;



    public OrderDAO(OrderRepository orderRepository, ProductDAO productDAO, UserRepository userRepository, ProductService productService, CategoryDAO categoryDAO) {
        this.orderRepository = orderRepository;
        this.productDAO = productDAO;
        this.userRepository = userRepository;
        this.productService = productService;
        this.categoryDAO = categoryDAO;
    }

    public List<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }


    @Transactional
    public void createOrder(OrderDTO orderDTO) {

        List<Product> productList = new ArrayList<>();
        System.out.println(orderDTO.products);
        for(ProductDTO productJson : orderDTO.products){
            System.out.println(productJson);
            Product product = new Product(productJson.name,productJson.description,productJson.price,null, productJson.durability, productJson.fitting,productJson.imageUrl, productJson.quantity);
            productList.add(product);
        }

        Order order = new Order(productService.makeName(productList), productService.calculatePrice(productList),LocalDateTime.now(), productList);
        order.setCustomUser(userRepository.findByEmail(orderDTO.email));
        this.orderRepository.save(order);

    }





}
