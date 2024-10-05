package com.asm.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderADController {
    

    @RequestMapping("/admin/order/list")
    public String list(){
        return "admin/list/order";
    }
}
