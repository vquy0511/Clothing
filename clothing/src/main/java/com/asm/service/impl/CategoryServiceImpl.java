package com.asm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.DAO.CategoryDAO;
import com.asm.entity.Category;
import com.asm.entity.Product;
import com.asm.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDAO cDao;
    @Override
    public List<Category> findAll() {
        return cDao.findAll();
    }
	@Override
	public Category findById(String id) {
		// TODO Auto-generated method stub
		return cDao.findById(id).get();
	}
	@Override
	public Category update(Category category) {
		// TODO Auto-generated method stub
		return cDao.save(category);
	}
	@Override
	public Category create(Category category) throws Exception {
		// TODO Auto-generated method stub
		if(cDao.existsById(category.getId())){
            throw new Exception();

        }
        return cDao.save(category);
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		if(cDao.existsById(id)){
            cDao.deleteById(id);
       }
	}
	@Override
	public List<Object[]> findProductduplicate() {
		// TODO Auto-generated method stub
		return	cDao.findProductduplicate();
	}
	
    
}
