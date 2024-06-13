package com.example.todoappdeel3.dao;

import com.example.todoappdeel3.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
