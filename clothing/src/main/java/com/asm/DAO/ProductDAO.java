package com.asm.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.Product;

public interface ProductDAO extends JpaRepository<Product,Integer> {
    
    @Query("SELECT p FROM Product p WHERE p.category.id=?1")
    List<Product> findByCategoryId(String cid);

    @Query("SELECT p FROM Product p WHERE p.category.id LIKE %?1% AND p.sex =?2")
    Page<Product> findByCategoryIdAndGender(String cid,boolean gender,Pageable pageable);

    @Query("SELECT p FROM Product p")
    Page<Product> findAllP(Pageable pageable);

    @Query(value="select * from products "
    +"where id in (select top 4 product_id from order_details "
    +"group by product_id "
    +"order by COUNT(quantity) desc)",nativeQuery = true)
    List<Product> finByBestSeller();
    @Query("SELECT o FROM Product o WHERE o.name LIKE ?1")
    Page<Product> findByKeyword(String name,Pageable pageable);
}
