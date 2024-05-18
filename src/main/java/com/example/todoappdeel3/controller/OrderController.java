package com.example.todoappdeel3.controller;


import com.example.todoappdeel3.dao.OrderDAO;
import com.example.todoappdeel3.dto.OrderDTO;
import com.example.todoappdeel3.models.CustomUser;
import com.example.todoappdeel3.models.Order;
import com.example.todoappdeel3.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/orders")

public class OrderController {


    private final OrderDAO orderDAO;


    public OrderController(OrderDAO orderDAO) {

        this.orderDAO = orderDAO;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderDAO.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO){
        try{
            this.orderDAO.createOrder(orderDTO);
            return ResponseEntity.ok("Order created");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }




//    @PostMapping
//    public Order placeOrder(@RequestBody OrderDTO orderDTO) {
//        return orderService.placeOrder(orderDTO);
//    }

//    @GetMapping("/history")
//    public ResponseEntity<List<Order>> getOrderHistory(@AuthenticationPrincipal CustomUser customUser) {
//        Long userId = customUser.getId(); // Dit haalt de ingelogde gebruiker's ID op
//        List<Order> orders = orderService.findOrdersByCustomUserId(userId);
//        if (orders.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }
//


}
