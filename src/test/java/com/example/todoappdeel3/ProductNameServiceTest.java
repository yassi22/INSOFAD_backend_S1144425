package com.example.todoappdeel3;

import com.example.todoappdeel3.models.Product;
import com.example.todoappdeel3.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductNameServiceTest {

    private ProductService productNameService;

    @BeforeEach
    public void setup() {
        productNameService = new ProductService();
    }

    @Test
    public void testMakeName_withNoProducts() {
        // Arrange
        List<Product> emptyProductList = new ArrayList<>();

        // Act
        String result = productNameService.makeName(emptyProductList);

        // Assert
        assertThat(result, is(""));
    }

    @Test
    public void testMakeName_withOneProduct() {
        // Arrange
        Product product1 = new Product();
        product1.setName("Product1");
        List<Product> singleProductList = Arrays.asList(product1);

        // Act
        String result = productNameService.makeName(singleProductList);

        // Assert
        assertThat(result, is("Product1"));
    }

    @Test
    public void testMakeName_withMultipleProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setName("Product1");
        Product product2 = new Product();
        product2.setName("Product2");
        Product product3 = new Product();
        product3.setName("Product3");
        List<Product> multipleProductsList = Arrays.asList(product1, product2, product3);

        // Act
        String result = productNameService.makeName(multipleProductsList);

        // Assert
        assertThat(result, is("Product1 , Product2 , Product3"));
    }

    @Test
    public void testMakeName_withEmptyProductName() {
        // Arrange
        Product product1 = new Product();
        product1.setName("Product1");
        Product product2 = new Product();
        product2.setName("");
        Product product3 = new Product();
        product3.setName("Product3");
        List<Product> productsWithEmptyName = Arrays.asList(product1, product2, product3);

        // Act
        String result = productNameService.makeName(productsWithEmptyName);

        // Assert
        assertThat(result, is("Product1 ,  , Product3"));
    }

    @Test
    public void testMakeName_withNullProduct() {
        // Arrange
        Product product1 = new Product();
        product1.setName("Product1");
        Product product3 = new Product();
        product3.setName("Product3");
        List<Product> productsWithNull = Arrays.asList(product1, null, product3);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            productNameService.makeName(productsWithNull);
        });
    }
}
