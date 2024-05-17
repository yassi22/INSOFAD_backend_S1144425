package com.example.todoappdeel3.dao;


import com.example.todoappdeel3.models.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAO {

    private final OrderRepository orderRepository;

    public OrderDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }


}
