package com.example.todoappdeel3.dao;


import com.example.todoappdeel3.dto.OptionsDTO;
import com.example.todoappdeel3.dto.OrderDTO;
import com.example.todoappdeel3.dto.ProductDTO;
import com.example.todoappdeel3.dto.ProductVariantDTO;
import com.example.todoappdeel3.models.*;
import com.example.todoappdeel3.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDAO {

    private final OrderRepository orderRepository;

    private final ProductDAO productDAO;

    private final UserRepository userRepository;

    private final ProductService productService;

    private final CategoryDAO categoryDAO;

    private final OptionsDAO optionsDAO;


    public OrderDAO(OrderRepository orderRepository, ProductDAO productDAO, UserRepository userRepository, ProductService productService, CategoryDAO categoryDAO, OptionsDAO optionsDAO) {
        this.orderRepository = orderRepository;
        this.productDAO = productDAO;
        this.userRepository = userRepository;
        this.productService = productService;
        this.categoryDAO = categoryDAO;
        this.optionsDAO = optionsDAO;
    }

    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }


    @Transactional
    public void createOrder(OrderDTO orderDTO) {

        List<Product> productList = new ArrayList<>();

        List<Options> optionsList = new ArrayList<>();


        System.out.println(orderDTO.products);
        for (ProductDTO productJson : orderDTO.products) {
            System.out.println(productJson);
//            Product product = new Product(productJson.name,productJson.description,productJson.price,null, productJson.durability, productJson.fitting,productJson.imageUrl, productJson.quantity);
            Product product = productDAO.getProduct(productJson.id);

            productList.add(product);

            for (ProductVariantDTO productVariantJson : productJson.variants) {
                for (OptionsDTO optionsJson : productVariantJson.options) {
                    Options option = optionsDAO.getOption(optionsJson.id);
                    optionsList.add(option);
                }
            }


            Order order = new Order(productService.makeName(productList), calculatePrice(  productList, optionsList), LocalDateTime.now(), productList);
            order.setCustomUser(userRepository.findByEmail(orderDTO.email));
            this.orderRepository.save(order);

        }

    }

    protected double calculatePrice(List<Product> productList, List<Options> optionsList){
        double totalPrice = 0.0;

        for(Product product : productList) {
            totalPrice += product.getPrice();
        }

        for(Options options : optionsList) {
            totalPrice += options.getAdded_price();

        }

        return totalPrice;

    }


}
