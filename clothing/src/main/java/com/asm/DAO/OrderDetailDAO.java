package com.asm.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.Order;
import com.asm.entity.OrderDetail;


public interface OrderDetailDAO extends JpaRepository<OrderDetail,Long> {

    @Query(value = "select * from order_details\r\n" + //
            "where order_id = ?1 ", nativeQuery = true)
    List<OrderDetail> findbyOrderId(Long id);
    
    @Query(value="select * from order_details where order_id in (\r\n" + //
            "select id from orders where username Like ?1\r\n" + //
            ")",nativeQuery=true)
    List<OrderDetail> findByUsername(String username);

    @Query(value="delete from order_details where order_id = ?1",nativeQuery=true)
    void deleteByOrderId(Long id);
}
