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

    private final OrderOptionsDAO orderOptionsDAO;

    private final OrderOptionsRepository orderOptionsRepository;

    private final ProductVariantDAO productVariantDAO;

    private final OrderProductVariantDAO orderProductVariantDAO;

    private final OrderProductVariantRepository orderProductVariantRepository;

    private final OrderProductRepository orderProductRepository;


    public OrderDAO(OrderRepository orderRepository, ProductDAO productDAO, UserRepository userRepository, ProductService productService, CategoryDAO categoryDAO, OptionsDAO optionsDAO,
                    ProductVariantDAO productVariantDAO, OrderProductVariantDAO orderProductVariantDAO, OrderProductVariantRepository orderProductVariantRepository, OrderProductRepository orderProductRepository,
     OrderOptionsDAO orderOptionsDAO, OrderOptionsRepository orderOptionsRepository) {
        this.orderRepository = orderRepository;
        this.productDAO = productDAO;
        this.userRepository = userRepository;
        this.productService = productService;
        this.categoryDAO = categoryDAO;
        this.optionsDAO = optionsDAO;
        this.productVariantDAO = productVariantDAO;
        this.orderProductVariantDAO = orderProductVariantDAO;
        this.orderProductVariantRepository = orderProductVariantRepository;
        this.orderProductRepository = orderProductRepository;
        this.orderOptionsDAO = orderOptionsDAO;
        this.orderOptionsRepository = orderOptionsRepository;
    }

    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }


    @Transactional
    public void createOrder(OrderDTO orderDTO) {
        List<Product> productList = new ArrayList<>();
        List<OrderProduct> orderProducts = new ArrayList<>();
        List<OrderProductVariant> totalOrderProductVariantList = new ArrayList<>();
        List<OrderOptions> totalOrderOptionsList = new ArrayList<>();

        processProducts(orderDTO, productList, orderProducts, totalOrderProductVariantList, totalOrderOptionsList);

        Order order = new Order(productService.makeName(productList), calculatePrice(productList, totalOrderOptionsList), LocalDateTime.now());
        order.setCustomUser(userRepository.findByEmail(orderDTO.email));
        this.orderRepository.saveAndFlush(order);

        saveOrderOptions(totalOrderOptionsList);
        saveOrderProductVariants(totalOrderProductVariantList);
        saveOrderProducts(order, orderProducts);

        order.setOrderProducts(orderProducts);
        this.orderRepository.save(order);
    }

    private void processProducts(OrderDTO orderDTO, List<Product> productList, List<OrderProduct> orderProducts,
                                 List<OrderProductVariant> totalOrderProductVariantList, List<OrderOptions> totalOrderOptionsList) {
        for (ProductDTO productJson : orderDTO.products) {
            Product product = productDAO.getProduct(productJson.id);
            OrderProduct orderProduct = new OrderProduct(product);
            productDAO.changeQuantityProduct(productJson.id);
            productList.add(product);

            List<OrderProductVariant> orderProductVariantList = processProductVariants(productJson, orderProduct, totalOrderOptionsList);
            orderProduct.setOrderProductVariants(orderProductVariantList);
            orderProducts.add(orderProduct);
            totalOrderProductVariantList.addAll(orderProductVariantList);
        }
    }

    private List<OrderProductVariant> processProductVariants(ProductDTO productJson, OrderProduct orderProduct,
                                                             List<OrderOptions> totalOrderOptionsList) {
        List<OrderProductVariant> orderProductVariantList = new ArrayList<>();
        for (ProductVariantDTO productVariantJson : productJson.variants) {
            List<OrderOptions> orderOptionsList = new ArrayList<>();
            ProductVariant productVariant = productVariantDAO.getProductVariant(productVariantJson.id);
            OrderProductVariant orderProductVariant = orderProductVariantDAO.convertProductVariantToOrderProductVariant(productVariant, orderProduct);

            processOptions(productVariantJson, orderProductVariant, orderOptionsList, totalOrderOptionsList);

            orderProductVariant.setOrderOptions(orderOptionsList);
            orderProductVariantList.add(orderProductVariant);
        }
        return orderProductVariantList;
    }

    private void processOptions(ProductVariantDTO productVariantJson, OrderProductVariant orderProductVariant,
                                List<OrderOptions> orderOptionsList, List<OrderOptions> totalOrderOptionsList) {
        for (OptionsDTO optionsJson : productVariantJson.options) {
            Options option = optionsDAO.getOption(optionsJson.id);
            OrderOptions orderOptions = orderOptionsDAO.convertOptionToOrderOption(option, orderProductVariant);
            orderOptionsList.add(orderOptions);
            totalOrderOptionsList.add(orderOptions);
        }
    }

    private void saveOrderOptions(List<OrderOptions> totalOrderOptionsList) {
        for (OrderOptions orderOptions : totalOrderOptionsList) {
            orderOptionsRepository.save(orderOptions);
        }
    }

    private void saveOrderProductVariants(List<OrderProductVariant> totalOrderProductVariantList) {
        for (OrderProductVariant orderProductVariant : totalOrderProductVariantList) {
            orderProductVariantRepository.save(orderProductVariant);
        }
    }

    private void saveOrderProducts(Order order, List<OrderProduct> orderProducts) {
        for (OrderProduct orderProduct : orderProducts) {
            orderProduct.setOrder(order);
            orderProductRepository.save(orderProduct);
        }
    }

    public double calculatePrice(List<Product> productList, List<OrderOptions> orderOptionsList){
        double totalPrice = 0.0;
        double shippingPrice = 4.95;

        for(Product product : productList) {
            totalPrice += product.getPrice();
        }

        for(OrderOptions orderOptions : orderOptionsList) {
            totalPrice += orderOptions.getAdded_price();
        }

        totalPrice += shippingPrice;

        return totalPrice;
    }


    }

