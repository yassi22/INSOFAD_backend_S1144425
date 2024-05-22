package com.example.todoappdeel3;


import com.example.todoappdeel3.controller.ProductController;
import com.example.todoappdeel3.dao.*;
import com.example.todoappdeel3.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestProductController {

    @Mock
    private ProductDAO productDAO;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductController productController;

    @BeforeEach
    public void setup(){
        this.productController = new ProductController(productDAO, productRepository);
    }

    @Test
    public void check_if_product_controller_exists(){
        assertThat(this.productController, instanceOf(ProductController.class));

    }





}
