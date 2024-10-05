package com.asm.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asm.entity.OrderDetail;
import com.asm.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orderDetails")
public class OrderDetailRestController {
    
      @Autowired
    OrderService orderService;

    @GetMapping("{id}")
    public List<OrderDetail> getAllbyOrderId(@PathVariable("id") Long orderId) {

        return orderService.findbyOrderId(orderId);
    }
}
