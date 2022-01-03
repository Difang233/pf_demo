package com.example.pf_management.service;

import com.example.pf_management.entity.Account;

import java.util.List;
import java.util.Map;

public interface AccountService {
    List<Account> findAccountsByCondition(Map<String, Object> param);

    void insert(Account account);

    Account findAccountById(Long id);

    void update(Account account);

    void delete(Long id);
}
