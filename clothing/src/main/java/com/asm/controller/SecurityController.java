package com.asm.controller;


import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.asm.entity.Account;
import com.asm.service.AccountService;

@Controller
public class SecurityController {

    @RequestMapping("/security/login/form")
    public String loginForm(Model model) {
        return "user/security/login";
    }

    @RequestMapping("/security/login/success")
    public String loginSuccess(Model model, Authentication authentication) {

        model.addAttribute("message", "Đăng nhập thành công");
        return "forward:/home/index";
    }

    @RequestMapping("/security/logoff/success")
    public String logoffSuccess(Model model) {
        model.addAttribute("message", "Đăng xuất thành công");

        return "user/security/login";
    }

    @RequestMapping("/security/login/error")
    public String loginError(Model model) {
        model.addAttribute("message", "Sai thông tin đăng nhập");
        return "user/security/login";
    }

    @RequestMapping("/security/login/unauthoried")
    public String unauthorited(Model model) {
        model.addAttribute("message", "bạn không có quyền truy xuất");
        return "user/security/login";
    }

    @RequestMapping("/security/register/form")
    public String form(Model model) {
        Account user = new Account();
        model.addAttribute("user", user);
        return "user/security/register";
    }

    @RequestMapping("/security/register")
    public String validate(Model model, @Validated @ModelAttribute("user") Account user, Errors errors) {
        if (errors.hasErrors()) {
            return "user/security/register";
        }else {
            try {
                System.out.println("username: " + user.getUsername());
                accountService.create(user);
                System.out.println("success");
            } catch (Exception e) {
                // TODO: handle exception
                model.addAttribute("message","Username đã tồn tại");
                            return "user/security/register";

            }

        }
        return "forward:/security/login/form";
    }

    @Autowired
    HttpServletRequest request;
    
    @Autowired
    AccountService accountService;
    @GetMapping("/security/profile")
    public String profile(Model model){
        String username = request.getRemoteUser();
        Account user =  accountService.findById(username);
        model.addAttribute("user", user);
        return "user/security/profile";
    }

    @PostMapping("/security/profile/update")
    public String profile1(Model model,@RequestParam("file") MultipartFile file) {
        String username = request.getRemoteUser();
        String fullname = request.getParameter("fullname") != null ? request.getParameter("fullname") : "";
        String email = request.getParameter("email") != null ? request.getParameter("email") : "";
        String password = request.getParameter("password") != null ? request.getParameter("password") : "";
        String image = file.getOriginalFilename();
        if(!file.isEmpty()){
            File dir = new File("D:\\HomeWork\\java6\\asmfinal\\asm\\src\\main\\resources\\static\\avatar");
            if(!dir.exists()){
                dir.mkdirs();
            }
            try {
                File savedFile = new File(dir,file.getOriginalFilename());
                file.transferTo(savedFile);
            } catch (Exception e) {
                // TODO: handle exception
                throw new RuntimeException(e);

            }
        }
        Account user =  accountService.findById(username);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        if(image != "")
        user.setPhoto(image);
        accountService.update(user);
        model.addAttribute("user", user);
        return "user/security/profile"; 
    }
    @PostMapping("/security/profile/cancel")
    public String cancel(Model model){
        return "redirect:/security/profile";
    }
}
