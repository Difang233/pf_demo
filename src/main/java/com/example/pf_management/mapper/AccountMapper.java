package com.example.pf_management.mapper;

import com.example.pf_management.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountMapper {
    List<Account> findAccountsByCondition(Map<String, Object> param);

    Account findAccountById(Long id);

    void insert(Account account);

    void insertAccountRole(Account account);

    void invalidAccountRole(Long id);

    void update(Account account);

    void delete(Long id);
}
