package com.asm.adminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.asm.DAO.CategoryDAO;
import com.asm.DAO.OrderDAO;
import com.asm.service.CategoryService;
import com.asm.service.OrderService;

@Controller
public class ReportController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	CategoryService categoryService;
	
	// Thống kê-tổng hợp

	@GetMapping("/productStatistics")
	public String show(Model model) {
	    List<Object[]> productStatistics = orderService.findProductStatistics();
	    model.addAttribute("productStatistics", productStatistics);
	    
	    // Tính tổng cột "Tổng tiền"
	    double totalRevenue = 0.0;
	    for (Object[] stat : productStatistics) {
	        Double revenue = (Double) stat[4];
	        totalRevenue += revenue;
	    }
	    model.addAttribute("totalRevenue", totalRevenue);
	    
	    model.addAttribute("findProductduplicate", categoryService.findProductduplicate());
	    model.addAttribute("findProductTotalSuccess", orderService.findProductTotalSuccess());
	    
	    return "/admin/report/success";
	}


}
