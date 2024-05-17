package com.example.todoappdeel3.controller;


import com.example.todoappdeel3.dao.OrderDAO;
import com.example.todoappdeel3.dto.OrderDTO;
import com.example.todoappdeel3.models.CustomUser;
import com.example.todoappdeel3.models.Order;
import com.example.todoappdeel3.services.OrderService;
import com.example.todoappdeel3.services.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    private final OrderService orderService;

    private final OrderDAO orderDAO;

    private final OrderServiceImpl orderServiceImpl;


    public OrderController(OrderService orderService, OrderServiceImpl orderServiceImpl, OrderDAO orderDAO) {
        this.orderService = orderService;
        this.orderServiceImpl = orderServiceImpl;
        this.orderDAO = orderDAO;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderDAO.getAllOrders());
    }

    @PostMapping
    public Order placeOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.placeOrder(orderDTO);
    }

    @GetMapping("/history")
    public ResponseEntity<List<Order>> getOrderHistory(@AuthenticationPrincipal CustomUser customUser) {
        Long userId = customUser.getId(); // Dit haalt de ingelogde gebruiker's ID op
        List<Order> orders = orderService.findOrdersByCustomUserId(userId);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }



}
