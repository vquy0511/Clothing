package com.asm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.DAO.AccountDAO;
import com.asm.DAO.AuthorityDAO;
import com.asm.entity.Account;
import com.asm.entity.Authority;
import com.asm.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityDAO authDao;

    @Autowired
    AccountDAO accDao;

    @Override
    public List<Authority> findAll() {
        // TODO Auto-generated method stub
        return authDao.findAll();
    }

    @Override
    public Authority create(Authority auth) {
        // TODO Auto-generated method stub
        return authDao.save(auth);
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        authDao.deleteById(id);
    }

    @Override
    public List<Authority> findAuthoritiesOfAdministrators() {
        // TODO Auto-generated method stub
        List<Account> accounts = accDao.getAdministrators();
        return authDao.authoritiesOf(accounts);
    }

}
