package com.asm.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.asm.DAO.OrderDAO;
import com.asm.DAO.OrderDetailDAO;
import com.asm.entity.Order;
import com.asm.entity.OrderDetail;
import com.asm.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO oDao;

    @Autowired
    OrderDetailDAO odDao;

    @Override
    public Order create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();
        Order order = mapper.convertValue(orderData, Order.class);
        oDao.save(order);

        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
        };
        List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
                .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
        odDao.saveAll(details);
        return order;
    }

    @Override
    public List<OrderDetail> findbyOrderId(Long id) {
        // TODO Auto-generated method stub
        return odDao.findbyOrderId(id);
    }

    @Override
    public Order findById(Long id) {
        // TODO Auto-generated method stub
        return oDao.findById(id).get();
    }

    @Override
    public List<OrderDetail> findByUsername(String username) {
        // TODO Auto-generated method stub
        return odDao.findByUsername(username);
    }

    @Override
    public List<Order> findAll() {
        // TODO Auto-generated method stub
        return oDao.findAll();
    }

    @Override
    public Order update(Order savedOrder) {
        // TODO Auto-generated method stub
        return oDao.save(savedOrder);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        try {
            List<OrderDetail> details = odDao.findbyOrderId(id);
            if (details == null) {

            } else {
                odDao.deleteByOrderId(id);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        oDao.deleteById(id);

    }

    @Override
    public List<Object[]> findProductStatistics() {
        // TODO Auto-generated method stub
       return  oDao.findProductStatistics();
    }

    @Override
    public List<Object[]> findProductTotalSuccess() {
        // TODO Auto-generated method stub
        return oDao.findProductTotalSuccess();
    }
}
