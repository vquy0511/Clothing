package com.asm.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asm.entity.Category;
import com.asm.service.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoriesRestController {
	@Autowired
	CategoryService categoryService;

	@GetMapping()
	public List<Category> getAll() {
		return categoryService.findAll();
	}
	
    @PutMapping("{id}")
    public Category update(@PathVariable("id") String id,@RequestBody Category category) {
        return categoryService.update(category);
    }
    @PostMapping("")
    public Category create(@RequestBody Category category) throws Exception {
        return categoryService.create(category);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id)  {
    	categoryService.delete(id);
    }

}
