package com.asm.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.SizeDivision;

public interface SizeDivisionDAO extends JpaRepository<SizeDivision,Integer> {

    @Query(value="select CAST(CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END AS BIT) from size_division\r\n" + //
            "where product_id = ?1",nativeQuery = true)
    boolean existsByProductId(Integer id);

    @Query(value="delete from size_division\r\n" + //
            "where product_id = ?1",nativeQuery = true)
    void deleteByProductId(Integer id);
    
}
