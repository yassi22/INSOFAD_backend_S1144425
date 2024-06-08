package com.example.todoappdeel3;

import com.example.todoappdeel3.dao.*;
import com.example.todoappdeel3.dto.ProductDTO;
import com.example.todoappdeel3.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductDAOTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductVariantRepository productVariantRepository;

    @Mock
    private OptionsRepository optionsRepository;

    @InjectMocks
    private ProductDAO productDAO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        List<Product> productList = Collections.singletonList(new Product());
        when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<Product> result = productDAO.getAllProducts();

        // Assert
        assertEquals(productList, result);
    }

    @Test
    public void testGetProducts() {
        // Arrange
        List<Long> ids = Collections.singletonList(1L);
        List<Product> productList = Collections.singletonList(new Product());
        when(productRepository.findAllById(ids)).thenReturn(productList);

        // Act
        List<Product> result = productDAO.getProducts(ids);

        // Assert
        assertEquals(productList, result);
    }

    @Test
    public void testGetAllProductsByCategory() {
        // Arrange
        long categoryId = 1L;
        List<Product> productList = Collections.singletonList(new Product());
        when(productRepository.findByCategoryId(categoryId)).thenReturn(Optional.of(productList));

        // Act
        List<Product> result = productDAO.getAllProductsByCategory(categoryId);

        // Assert
        assertEquals(productList, result);
    }

    @Test
    public void testGetAllProductsByCategory_NotFound() {
        // Arrange
        long categoryId = 1L;
        when(productRepository.findByCategoryId(categoryId)).thenReturn(Optional.of(Collections.emptyList()));

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productDAO.getAllProductsByCategory(categoryId);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("No products found with that category id", exception.getReason());
    }

    @Test
    public void testGetProductId() {
        // Arrange
        long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Product result = productDAO.getProduct(productId);

        // Assert
        assertEquals(product, result);
    }

    @Test
    public void testGetProduct_NotFound() {
        // Arrange
        long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productDAO.getProduct(productId);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("De gevraagde product is niet gevonden", exception.getReason());
    }

    @Test
    public void testChangeQuantityProduct() {
        // Arrange
        long productId = 1L;
        Product product = new Product();
        product.setQuantity(10);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        boolean result = productDAO.changeQuantityProduct(productId);

        // Assert
        assertFalse(result);
        assertEquals(9, product.getQuantity());
    }

    @Test
    public void testCreateProduct() {
        // Arrange
        Product product = new Product();
        Category category = new Category();
        product.setCategory(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(productRepository.save(product)).thenReturn(product);

        // Act
        productDAO.createProduct(product);

        // Assert
        verify(categoryRepository).save(category);
        verify(productRepository).save(product);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        long productId = 1L;

        // Act
        productDAO.deleteById(productId);

        // Assert
        verify(productRepository).deleteById(productId);
    }


}
