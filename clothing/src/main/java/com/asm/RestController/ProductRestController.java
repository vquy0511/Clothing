package com.asm.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asm.entity.Product;
import com.asm.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @GetMapping("{id}")
    public Product getOne(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }

    @GetMapping("")
    public List<Product> getAll(){
        return productService.findAll();
    }
    @PutMapping("{id}")
    public Product update(@PathVariable("id") Integer id,@RequestBody Product product) {
        return productService.update(product);
    }
    @PostMapping("")
    public Product create(@RequestBody Product product) throws Exception {
        return productService.create(product);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id,@RequestBody Product product)  {
         productService.delete(product);
    }
    
}
