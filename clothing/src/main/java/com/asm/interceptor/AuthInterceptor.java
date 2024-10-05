package com.asm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.asm.entity.Account;
import com.asm.service.AccountService;

@Service
public class AuthInterceptor implements HandlerInterceptor {
    
    @Autowired
    AccountService accountService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                
        String uri = request.getRequestURI();
        String username = request.getRemoteUser();
        if(username == null){
            System.out.println("username : "+ username);
        }
        else{
            
            Account account = accountService.findById(username);
            System.out.println("fullname: " +account.getFullname());
            request.setAttribute("fullname",account.getFullname());
            request.setAttribute("user", account);
        }

        return true;
    }
}
