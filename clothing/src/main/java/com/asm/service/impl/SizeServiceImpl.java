package com.asm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.DAO.SizeDAO;
import com.asm.entity.Size;
import com.asm.service.SizeService;

@Service
public class SizeServiceImpl implements SizeService{

    @Autowired
    SizeDAO sDao;

    @Override
    public List<Size> findAll() {
        // TODO Auto-generated method stub
       return sDao.findAll();
    }

    @Override
    public Size findById(String string) {
        // TODO Auto-generated method stub
        return sDao.findById(string).get();
    }

   
    
}
