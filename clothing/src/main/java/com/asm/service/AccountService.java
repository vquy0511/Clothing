package com.asm.service;

import java.util.List;

import com.asm.entity.Account;
import com.fasterxml.jackson.databind.JsonNode;

public interface AccountService {

        public Account findById(String username);

        public Account create(Account user) throws Exception;

        public void update(Account user);

        public List<Account> findAll();

        public List<Account> getAdministrators();
}
