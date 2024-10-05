package com.asm.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.entity.Account;
import com.asm.entity.Order;
import com.asm.entity.OrderDetail;
import com.asm.service.OrderService;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/order/checkout")
    public String checkOut() {
        return "user/order/checkout";
    }

    @RequestMapping("/order/list")
    public String list(Model model) {
        String username = request.getRemoteUser();
        System.out.println("username: " + username);
        List<OrderDetail> orders = orderService.findByUsername(username);
       
        model.addAttribute("orders", orders);
        return "user/order/list";
    }

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/order/detail/{id}")
    public String detail(Model model, @PathVariable("id") Optional<Long> id) {
        Order order = orderService.findById(id.get());
        model.addAttribute("list", order.getOrderDetails());
        model.addAttribute("order", order);
        model.addAttribute("amount", order.getOrderDetails().stream()
                .mapToDouble(d -> d.getPrice() * d.getQuantity()).sum());

        return "user/order/detail";
    }
    @PostMapping("/order/cancel_order/{id}")
    public String updateStatus(@PathVariable("id") Long id) {
        Order savedOrder = orderService.findById(id);
        savedOrder.setStatus("Đã hủy");
        orderService.update(savedOrder);
        return "redirect:/order/list";
    }
}
