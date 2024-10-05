package com.asm.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryADController {
	@RequestMapping("/admin/categories/list")
    public String list(){
        return "admin/manage/category";
    }
}
