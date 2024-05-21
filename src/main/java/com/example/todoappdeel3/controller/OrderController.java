package com.example.todoappdeel3.controller;


import com.example.todoappdeel3.dao.OrderDAO;
import com.example.todoappdeel3.dao.OrderRepository;
import com.example.todoappdeel3.dto.OrderDTO;
import com.example.todoappdeel3.models.CustomUser;
import com.example.todoappdeel3.models.Order;
import com.example.todoappdeel3.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestBody;

import javax.print.attribute.standard.Media;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/orders")
public class OrderController {


    private final OrderDAO orderDAO;

    private final OrderService orderService;


    public OrderController(OrderDAO orderDAO, OrderService orderService) {
        this.orderService = orderService;
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
            e.printStackTrace();
           System.out.println(e.getMessage());


           return ResponseEntity.internalServerError().build();
        }
    }



//    @GetMapping("/myorder")
//    public ResponseEntity<List<Order>> getMyOrders(@AuthenticationPrincipal CustomUser customUser) {
//        Long userId = customUser.getId();
//        List<Order> orders = orderService.findOrderUser(userId);
//        if (orders.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }


    @GetMapping ("/user/{userId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity <List<Order>> getOrderUser(@PathVariable Long userId) {
        try {

            List<Order> order = this.orderService.findOrderUser(userId);
            return ResponseEntity.ok(order);

        } catch (ResponseStatusException e) {
            return ResponseEntity.badRequest().build();
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
