package com.asm.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorityADController {
	@RequestMapping("/admin/authority/list")
	public String index() {
		return "admin/authority/index";
	}
}
