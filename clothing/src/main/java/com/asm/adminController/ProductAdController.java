package com.asm.adminController;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.asm.entity.Category;
import com.asm.entity.Color;
import com.asm.entity.ColorDivision;
import com.asm.entity.Product;
import com.asm.entity.Size;
import com.asm.entity.SizeDivision;
import com.asm.service.CategoryService;
import com.asm.service.ColorDivisionService;
import com.asm.service.ColorService;
import com.asm.service.ProductService;
import com.asm.service.SizeDivisionService;
import com.asm.service.SizeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductAdController {

    @Autowired
    ProductService productService;

    @Autowired
    SizeService sizeService;

    @Autowired
    ColorService colorService;
    @Autowired
    HttpServletRequest request;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/admin/product/list")
    public String list() {
        return "admin/list/product";
    }

    @GetMapping("/admin/product/update/{id}")
    public String update(Model model, @PathVariable("id") Integer id) {
        try {

            Product p = productService.findById(id);
            if (p != null)
                model.addAttribute("item", p);
            List<Size> s = sizeService.findAll();
            List<Color> c = colorService.findAll();
            model.addAttribute("color", c);
            model.addAttribute("size", s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/manage/product";

    }

    @GetMapping("/admin/product/create")
    public String add(Model model) {
        try {
            List<Size> s = sizeService.findAll();
            List<Color> c = colorService.findAll();
            model.addAttribute("color", c);
            model.addAttribute("size", s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/manage/createProduct";

    }
    @Autowired
    SizeDivisionService sizeDivisionService;

    @Autowired
    ColorDivisionService colorDivisionService;

    @PostMapping("/admin/product/create")
    public String create(@RequestParam("file") MultipartFile file, Model model,@RequestParam("size") String[] sizes,@RequestParam("color") String[] colors) {
        try {

            String cid = request.getParameter("category");
            Product item = new Product();
            item.setName(request.getParameter("name"));
            item.setPrice(Double.valueOf(request.getParameter("price")));
            item.setQuantity(Integer.valueOf(request.getParameter("quantity")));
            item.setCreate_date(new Date());
            item.setAvailable(Boolean.valueOf(request.getParameter("available")));
            item.setSex(Boolean.valueOf(request.getParameter("gender")));
            String image = file.getOriginalFilename();
            Category c = categoryService.findById(cid);
            item.setCategory(c);
            if (!file.isEmpty()) {
                File dir = new File("D:\\HomeWork\\java6\\asmfinal\\asm\\src\\main\\resources\\static\\product");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                try {
                    File savedFile = new File(dir, file.getOriginalFilename());
                    file.transferTo(savedFile);
                } catch (Exception e) {
                    // TODO: handle exception
                    throw new RuntimeException(e);

                }
            }
            if (image != "")
                item.setImage(image);
            productService.update(item);
            
            for(int i = 0; i < sizes.length;i++){
                Size s = sizeService.findById(sizes[i]);
                SizeDivision sD = new SizeDivision();
                sD.setProduct(item);
                sD.setSize(s);
                sizeDivisionService.create(sD);
            }
            for(int i = 0; i < colors.length;i++){
                Color cs = colorService.findById(colors[i]);
                ColorDivision cd = new ColorDivision();
                cd.setProduct(item);
                cd.setColor(cs);
                colorDivisionService.create(cd);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "redirect:/admin/product/list";
    }

    @PostMapping("/admin/product/update")
    public String update(@RequestParam("file") MultipartFile file) {
        try {
            String cid = request.getParameter("category");
            int id = Integer.valueOf(request.getParameter("id"));
            System.out.println("id: " + id);
            System.out.println("cid: " + cid);
            Product item = productService.findById(id);
            item.setName(request.getParameter("name"));
            item.setPrice(Double.valueOf(request.getParameter("price")));
            item.setQuantity(Integer.valueOf(request.getParameter("quantity")));
            item.setCreate_date(new Date());
            item.setAvailable(Boolean.valueOf(request.getParameter("available")));
            item.setSex(Boolean.valueOf(request.getParameter("gender")));
            Category c = categoryService.findById(cid);
            item.setCategory(c);
            String image = file.getOriginalFilename();
            if (!file.isEmpty()) {
                File dir = new File("D:\\HomeWork\\java6\\asmfinal\\asm\\src\\main\\resources\\static\\product");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                try {
                    File savedFile = new File(dir, file.getOriginalFilename());
                    file.transferTo(savedFile);
                } catch (Exception e) {
                    // TODO: handle exception
                    throw new RuntimeException(e);

                }
            }
            if (image != "")
                item.setImage(image);
            productService.update(item);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/product/list";
    }

    @RequestMapping("/admin/product/reset")
    public String reset() {

        return "redirect:/admin/product/create";

    }

    @RequestMapping("/admin/product/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        try {
            sizeDivisionService.deleteByProductId(id);
            colorDivisionService.deleteByProductId(id);
            Product p = productService.findById(id);
            productService.delete(p);
            System.out.println("success");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: " + e.getMessage());
        }

        return "redirect:/admin/product/list";
    }

}
