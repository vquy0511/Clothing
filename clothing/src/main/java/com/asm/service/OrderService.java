package com.asm.service;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Optional;

import com.asm.entity.Order;
import com.asm.entity.OrderDetail;

public interface OrderService {

    Order create(JsonNode orderData);

    List<OrderDetail> findbyOrderId(Long integer);

    Order findById(Long id);

    List<OrderDetail> findByUsername(String username);

    List<Order> findAll();

    Order update(Order savedOrder);

    void delete(Long id);

    List<Object[]> findProductStatistics();

    List<Object[]> findProductTotalSuccess();

}
