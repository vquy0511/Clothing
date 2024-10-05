package com.asm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asm.entity.Product;
import com.asm.service.ProductService;

@Controller
public class HomeController {
    
    @Autowired
    ProductService productService;

    @RequestMapping("/home/index")
    public String index(Model model){
        List<Product> list = productService.findbyBestSeller();
        if(list != null)
       
        model.addAttribute("items", list);
        return "user/home";
    }
}
