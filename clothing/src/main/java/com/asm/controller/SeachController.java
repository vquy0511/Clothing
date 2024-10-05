package com.asm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.DAO.ProductDAO;
import com.asm.entity.Product;
import com.asm.service.ProductService;

@Controller
public class SeachController {
    
   

    @Autowired
    ProductService productService;

    public Page sort(String name,String sort, Optional<Integer> p) {
        Page<Product> page;
        Pageable pageable;
        switch (sort) {
            case "A":
                pageable = PageRequest.of(p.orElse(0), 12, Sort.by("name"));
                page = productService.findByKeyword("%"+name+"%", pageable);
                break;
            case "Z":
                pageable = PageRequest.of(p.orElse(0), 12, Sort.by("name").descending());

                page = productService.findByKeyword("%"+name+"%", pageable);
                break;
            case "N":
                pageable = PageRequest.of(p.orElse(0), 12, Sort.by("id").descending());

                page = productService.findByKeyword("%"+name+"%", pageable);
                break;
            case "T":
                pageable = PageRequest.of(p.orElse(0), 12, Sort.by("price"));

                page = productService.findByKeyword("%"+name+"%", pageable);
                break;
            case "C":
                pageable = PageRequest.of(p.orElse(0), 12, Sort.by("price").descending());

                page = productService.findByKeyword("%"+name+"%", pageable);
                break;

            default:
                pageable = PageRequest.of(p.orElse(0), 12);
                page = productService.findByKeyword("%"+name+"%", pageable);
                break;
        }
        return page;
    }
     @Autowired
    HttpSession session;

    @RequestMapping("/product/search")
    public String search(Model model,@RequestParam("keyword") Optional<String> keyword,@RequestParam("pageN") Optional<Integer> p,
    @RequestParam("sort") Optional<String> option) {
        String kw = keyword.orElse(session.getAttribute("name") !=null ? (String) session.getAttribute("name") : null);
        session.setAttribute("name", kw);

        Page<Product> page = sort(kw,option.orElse(""),p);
        model.addAttribute("productPage", page);
        model.addAttribute("items", page.getContent());
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "forward:/product";

    }
}
