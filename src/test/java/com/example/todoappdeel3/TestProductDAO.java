package com.example.todoappdeel3;

import com.example.todoappdeel3.dao.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestProductDAO {

    @Mock
    private ProductDAO productDAO;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private OptionsRepository optionsRepository;

    @Mock
    private ProductVariantRepository productVariantRepository;



//    @BeforeEach
//    public void setup(){
//        this.productDAO = new ProductDAO(productRepository, categoryRepository, optionsRepository, categoryRepository, productVariantRepository );
//    }



}
