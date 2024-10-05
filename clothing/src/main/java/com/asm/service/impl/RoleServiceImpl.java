package com.asm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.DAO.RoleDAO;
import com.asm.entity.Role;
import com.asm.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDAO rDao;

    @Override
    public List<Role> findAll() {
        // TODO Auto-generated method stub
        return rDao.findAll();
    }

}
