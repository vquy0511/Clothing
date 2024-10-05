package com.asm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.DAO.SizeDivisionDAO;
import com.asm.entity.SizeDivision;
import com.asm.service.SizeDivisionService;

@Service
public class SizeDivisionServiceImpl implements SizeDivisionService {

    @Autowired
    SizeDivisionDAO sdDao;

    @Override
    public void create(SizeDivision sD) {
        // TODO Auto-generated method stub
        try {
            if (!sdDao.existsById(sD.getId())) {
                sdDao.save(sD);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public void deleteByProductId(Integer id) {
        // TODO Auto-generated method stub
        try {
            if (sdDao.existsByProductId(id)) {
                sdDao.deleteByProductId(id);
            }
        } catch (Exception e) {
            System.out.println("Error: not query");
        }

    }

}
