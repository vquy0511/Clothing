package com.asm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.DAO.ColorDivisionDAO;
import com.asm.entity.ColorDivision;
import com.asm.service.ColorDivisionService;

@Service
public class ColorDivisionServiceImpl implements ColorDivisionService{

    @Autowired
    ColorDivisionDAO cdDao;

    @Override
    public void create(ColorDivision cd) {
        // TODO Auto-generated method stub
        try {
            if(!cdDao.existsById(cd.getId())){
                cdDao.save(cd);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteByProductId(Integer id) {
        // TODO Auto-generated method stub
        try {
            if(cdDao.existsByProductId(id)){
                cdDao.deleteByProductId(id);
            
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: not query");
        }
    }
    
}
