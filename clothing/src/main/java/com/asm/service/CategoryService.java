package  com.asm.service;

import java.util.List;

import com.asm.entity.Category;
import com.asm.entity.Product;

public interface CategoryService {

    List<Category> findAll();
    
    Category findById(String id);
    
    Category update(Category category);

    Category create(Category category) throws Exception;
    
    void delete(String id);

	List<Object[]> findProductduplicate();
    
}
