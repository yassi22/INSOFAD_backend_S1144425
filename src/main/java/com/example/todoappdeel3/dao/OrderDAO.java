package com.example.todoappdeel3.dao;


import com.example.todoappdeel3.dto.OrderDTO;
import com.example.todoappdeel3.models.Order;
import com.example.todoappdeel3.models.Product;
import com.example.todoappdeel3.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderDAO {

    private final OrderRepository orderRepository;

    private final ProductDAO productDAO;

    private final UserRepository userRepository;

    private final ProductService productService;





    public OrderDAO(OrderRepository orderRepository, ProductDAO productDAO, UserRepository userRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productDAO = productDAO;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    public List<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }


    @Transactional
    public void createOrder(OrderDTO orderDTO) {
        List<Product> productList = productDAO.getProducts(orderDTO.productIds);

        Order order = new Order(productService.makeName(productList), productService.calculatePrice(productList), LocalDateTime.now(), productList);
        order.setCustomUser(userRepository.findByEmail(orderDTO.email));
        this.orderRepository.save(order);

    }





}
