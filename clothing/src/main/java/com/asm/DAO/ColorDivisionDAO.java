package com.asm.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.ColorDivision;

public interface ColorDivisionDAO extends JpaRepository<ColorDivision,Integer> {

    @Query(value="select CAST(CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END AS BIT) from color_division where product_id = ?1",nativeQuery = true)
    boolean existsByProductId(Integer id);

    @Query(value="DELETE from color_division where product_id = ?1",nativeQuery = true)
    void deleteByProductId(Integer id);
    
}
