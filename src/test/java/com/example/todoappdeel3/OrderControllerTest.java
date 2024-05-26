package com.example.todoappdeel3;

import com.example.todoappdeel3.controller.OrderController;
import com.example.todoappdeel3.dao.OrderDAO;
import com.example.todoappdeel3.dto.OrderDTO;
import com.example.todoappdeel3.models.Order;
import com.example.todoappdeel3.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @Mock
    private OrderDAO orderDAO;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        // Arrange
        List<Order> orders = Collections.singletonList(new Order());
        when(orderDAO.getAllOrders()).thenReturn(orders);

        // Act
        ResponseEntity<List<Order>> response = orderController.getAllOrders();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }


    @Test
    public void testGetOrderUser() {
        // Arrange
        Long userId = 1L;
        List<Order> orders = Collections.singletonList(new Order());
        when(orderService.findOrderUser(userId)).thenReturn(orders);

        // Act
        ResponseEntity<List<Order>> response = orderController.getOrderUser(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }


}
