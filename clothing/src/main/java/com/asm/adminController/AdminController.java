package com.asm.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    
    @RequestMapping("/admin/index")
    public String index() {
        return "admin/home";
    }
}
