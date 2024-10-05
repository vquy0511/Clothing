package com.asm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.DAO.AccountDAO;
import com.asm.entity.Account;
import com.asm.service.AccountService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDAO aDao;

    @Override
    public Account findById(String username) {
        return aDao.findById(username).get();
    }

    @Override
    public Account create(Account user) throws Exception {

        if (aDao.existsById(user.getUsername())) {
            throw new Exception();
        }

        return aDao.save(user);
    }

    @Override
    public void update(Account user) {
        // TODO Auto-generated method stub
        aDao.save(user);
    }

    @Override
    public List<Account> findAll() {
        // TODO Auto-generated method stub
        return aDao.findAll();
    }

    @Override
    public List<Account> getAdministrators() {
        // TODO Auto-generated method stub
        return aDao.getAdministrators();
    }
}
