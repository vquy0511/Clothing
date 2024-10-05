package com.asm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.DAO.ColorDAO;
import com.asm.entity.Color;
import com.asm.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService{

    @Autowired
    ColorDAO cDao;
    @Override
    public List<Color> findAll() {
        // TODO Auto-generated method stub
        return cDao.findAll();
    }
    @Override
    public Color findById(String string) {
        // TODO Auto-generated method stub
        return cDao.findById(string).get();
    }
    
}
