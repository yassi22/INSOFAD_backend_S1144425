package com.example.todoappdeel3;

import com.example.todoappdeel3.controller.ProductController;
import com.example.todoappdeel3.dao.ProductDAO;
import com.example.todoappdeel3.dao.ProductRepository;
import com.example.todoappdeel3.dto.DeleteVariantOptionsDTO;
import com.example.todoappdeel3.dto.ProductDTO;
import com.example.todoappdeel3.models.Product;
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

public class ProductControllerTest {

    @Mock
    private ProductDAO productDAO;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        List<Product> products = Collections.singletonList(new Product());
        when(productDAO.getAllProducts()).thenReturn(products);

        // Act
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    public void testGetProductsByCategory() {
        // Arrange
        String category = "Electronics";
        List<Product> products = Collections.singletonList(new Product());
        when(productRepository.findByCategoryName(category)).thenReturn(products);

        // Act
        ResponseEntity<List<Product>> response = productController.getProductsByCategory(category);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    public void testGetProductId() {
        // Arrange
        Long id = 1L;
        Product product = new Product();
        when(productDAO.getProduct(id)).thenReturn(product);

        // Act
        ResponseEntity<Product> response = productController.getProductId(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }


    @Test
    public void testDeleteById() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<String> response = productController.deleteById(id);

        // Assert
        verify(productDAO, times(1)).deleteById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product deleted with id " + id, response.getBody());
    }

    @Test
    public void testCheckProduct() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<String> response = productController.checkProduct(id);

        // Assert
        verify(productDAO, times(1)).checkProduct(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product checked with id " + id, response.getBody());
    }

    @Test
    public void testUncheckProduct() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<String> response = productController.uncheckProduct(id);

        // Assert
        verify(productDAO, times(1)).uncheckProduct(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product uncheck with id " + id, response.getBody());
    }

}
