package com.asm.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.Order;


public interface OrderDAO extends JpaRepository<Order,Long>{

    @Query(value="SELECT * FROM orders  WHERE username Like ?1",nativeQuery=true)
    List<Order> findByUsername(String username);

    @Query(value = "\n"
			+ "SELECT\n"
			+ "    a.username,\n"
			+ "    a.fullname AS Accounts,\n"
			+ "	p.name,\n"
			+ "    SUM(od.quantity) AS total_quantity,\n"
			+ "    SUM(od.quantity * od.price) AS total_revenue\n"
			+ "FROM\n"
			+ "    order_details od\n"
			+ "JOIN\n"
			+ "    Orders o ON od.order_id = o.id\n"
			+ "JOIN\n"
			+ "    Products p ON od.product_id = p.id\n"
			+ "JOIN\n"
			+ "    Accounts a ON o.username = a.username\n"
			+ "WHERE\n"
			+ "    o.status = N'Thành công'\n"
			+ "GROUP BY\n"
			+ "    a.username, a.fullname,p.name\n"
			+ "ORDER BY\n"
			+ "    total_quantity DESC;", nativeQuery = true)
	List<Object[]> findProductStatistics();

    @Query(value = "\n"
			+ "SELECT p.name,\n"
			+ "    SUM(od.quantity) AS total_quantity,\n"
			+ "    SUM(od.quantity * od.price) AS total_revenue\n"
			+ "FROM\n"
			+ "    order_details od\n"
			+ "JOIN\n"
			+ "    Orders o ON od.order_id = o.id\n"
			+ "JOIN\n"
			+ "    Products p ON od.product_id = p.id\n"
			+ "JOIN\n"
			+ "    Accounts a ON o.username = a.username\n"
			+ "WHERE\n"
			+ "    o.status = N'Thành công'\n"
			+ "	GROUP BY\n"
			+ "    p.name;", nativeQuery = true)
	List<Object[]> findProductTotalSuccess();
    
}
