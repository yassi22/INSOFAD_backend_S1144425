package com.example.todoappdeel3;

import com.example.todoappdeel3.dao.*;
import com.example.todoappdeel3.dto.*;
import com.example.todoappdeel3.models.*;
import com.example.todoappdeel3.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderDAOTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductDAO productDAO;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductService productService;

    @Mock
    private CategoryDAO categoryDAO;

    @Mock
    private OptionsDAO optionsDAO;

    @InjectMocks
    private OrderDAO orderDAO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        // Arrange
        List<Order> orderList = Collections.singletonList(new Order());
        when(orderRepository.findAll()).thenReturn(orderList);

        // Act
        List<Order> result = orderDAO.getAllOrders();

        // Assert
        assertEquals(orderList, result);
    }

    @Test
    public void testCalculatePrice() {
        // Arrange
        Product product1 = new Product();
        product1.setPrice(10.0);
        Product product2 = new Product();
        product2.setPrice(20.0);

        Options option1 = new Options();
        option1.setAdded_price(2.0);
        Options option2 = new Options();
        option2.setAdded_price(3.0);

        List<Product> productList = List.of(product1, product2);
        List<Options> optionsList = List.of(option1, option2);

        // Act
        double result = orderDAO.calculatePrice(productList, optionsList);

        // Assert
        assertEquals(39.95, result);  // 10 + 20 + 2 + 3 + 4.95 (shipping)
    }

    // Additional tests can be added here
}
