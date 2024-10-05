package com.asm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.asm.entity.Account;
import com.asm.entity.Order;
import com.asm.service.AccountService;
import com.asm.service.OrderService;

@Service
public class OrderInterceptor implements HandlerInterceptor {

    @Autowired
    OrderService orderService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String uri = request.getRequestURI();
        String username = request.getRemoteUser();
        String[] pathParts = uri.split("/");
        String orderId = pathParts[3]; 
        Order order = orderService.findById(Long.valueOf(orderId));
        if (!username.equals(order.getAccount().getUsername()) || username == null) {
            response.sendRedirect("/home/index?error=orderId");
            return false;
        } 
        return true;
    }
}
