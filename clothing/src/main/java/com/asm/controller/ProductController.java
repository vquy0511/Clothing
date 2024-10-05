package com.asm.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.entity.Product;
import com.asm.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @RequestMapping("/product")
    public String index() {
        return "user/product/list";
    }

    public Page sort(boolean gender, String sort, Optional<Integer> p, String cid) {
        Page<Product> page;
        Pageable pageable;
        switch (sort) {
            case "A":
                pageable = PageRequest.of(p.orElse(0), 12, Sort.by("name"));
                page = productService.findByidAndGender(cid, gender, pageable);
                break;
            case "Z":
                pageable = PageRequest.of(p.orElse(0), 12, Sort.by("name").descending());
                page = productService.findByidAndGender(cid, gender, pageable);
                break;
            case "N":
                pageable = PageRequest.of(p.orElse(0), 12, Sort.by("id").descending());
                page = productService.findByidAndGender(cid, gender, pageable);
                break;
            case "T":
                pageable = PageRequest.of(p.orElse(0), 12, Sort.by("price"));
                page = productService.findByidAndGender(cid, gender, pageable);
                break;
            case "C":
                pageable = PageRequest.of(p.orElse(0), 12, Sort.by("price").descending());
                page = productService.findByidAndGender(cid, gender, pageable);
                break;
            default:
                pageable = PageRequest.of(p.orElse(0), 12);
                page = productService.findByidAndGender(cid, gender, pageable);
                break;
        }

        return page;
    }

    public Page sort(String sort, Optional<Integer> p) {
        Page<Product> page;
        Pageable pageable;
        switch (sort) {
            case "A":
                pageable = PageRequest.of(p.orElse(0), 12, Sort.by("name"));
                page = productService.findAll(pageable);
                break;
            case "Z":
                pageable = PageRequest.of(p.orElse(0), 12, Sort.by("name").descending());
                page = productService.findAll(pageable);
                break;
            case "N":
                pageable = PageRequest.of(p.orElse(0), 12, Sort.by("id").descending());
                page = productService.findAll(pageable);
                break;
            case "T":
                pageable = PageRequest.of(p.orElse(0), 12, Sort.by("price"));
                page = productService.findAll(pageable);
                break;
            case "C":
                pageable = PageRequest.of(p.orElse(0), 12, Sort.by("price").descending());
                page = productService.findAll(pageable);
                break;
            default:
                pageable = PageRequest.of(p.orElse(0), 12);
                page = productService.findAll(pageable);
                break;
        }

        return page;
    }

    @RequestMapping("/product/list")
    public String list(Model model,
            @RequestParam("cid") Optional<String> cid,
            @RequestParam("sort") Optional<String> sort,
            @RequestParam("pageN") Optional<Integer> page) {

        Page<Product> productPage = sort(sort.orElse(""), page);
        model.addAttribute("items", productPage.getContent());
        model.addAttribute("productPage", productPage);
        int totalPages = productPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("view", "list");
        
        return "user/product/list";
    }

    @RequestMapping("/product/male")
    public String male(Model model,
            @RequestParam("cid") Optional<String> cid,
            @RequestParam("sort") Optional<String> sort,
            @RequestParam("pageN") Optional<Integer> page) {
        Page<Product> productPage = sort(true, sort.orElse(""), page, cid.orElse(""));
        model.addAttribute("productPage", productPage);

        model.addAttribute("items", productPage.getContent());
       
        int totalPages = productPage.getTotalPages();
                model.addAttribute("totalPages", totalPages);

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("view", "male");
        return "user/product/list";

    }

    @RequestMapping("/product/female")
    public String female(Model model,
            @RequestParam("cid") Optional<String> cid,
            @RequestParam("sort") Optional<String> sort,
            @RequestParam("pageN") Optional<Integer> page) {
        Page<Product> productPage = sort(false, sort.orElse(""), page, cid.orElse(""));
        model.addAttribute("productPage", productPage);

        model.addAttribute("items", productPage.getContent());
        int totalPages = productPage.getTotalPages();
                model.addAttribute("totalPages", totalPages);

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("view", "female");

        return "user/product/list";

    }

    @RequestMapping("/product/bestseller")
    public String bestseller(Model model, @RequestParam("sort") Optional<String> sort,
            @RequestParam("pageN") Optional<Integer> page) {
         Page<Product> productPage = sort(sort.orElse(""), page);
        model.addAttribute("items", productPage.getContent());
        model.addAttribute("productPage", productPage);
        int totalPages = productPage.getTotalPages();
                model.addAttribute("totalPages", totalPages);

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("view", "bestseller");
        return "user/product/list";
    }
     @RequestMapping("/product/detail/{id}")  
    public String detail(Model model,@PathVariable("id") Integer id){
        Product item = productService.findById(id);
        model.addAttribute("item",item);
        return "user/product/detail";
    }
}
