package com.asm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.asm.entity.Product;

public interface ProductService {

    Page<Product> findAll(Pageable pageable);

    Product findById(Integer id);

    List<Product> findByCategoryId(String cid);

    List<Product> findbyBestSeller();

    Page<Product> findByidAndGender(String cid, boolean b,Pageable pageable);

    Page<Product> findByKeyword(String string, Pageable pageable);

    List<Product> findAll();

    Product update(Product product);

    Product create(Product product) throws Exception;

    void delete(Product product);
    
}
