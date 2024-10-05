package com.asm.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.Category;

public interface CategoryDAO extends JpaRepository<Category,String> {
 
    @Query(value = "\n"
			+ "SELECT\n"
			+ "    c.name AS CategoryName,\n"
			+ "    SUM(p.quantity) AS TotalProducts\n"
			+ "FROM\n"
			+ "    Categories c\n"
			+ "JOIN\n"
			+ "    Products p ON c.id = p.categoryID\n"
			+ "GROUP BY c.name\n"
			+ "ORDER BY\n"
			+ "    TotalProducts DESC;\n"
			+ "", nativeQuery = true)
	List<Object[]> findProductduplicate();
}
