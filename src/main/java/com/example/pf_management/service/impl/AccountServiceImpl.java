package com.example.pf_management.service.impl;

import com.example.pf_management.common.BusinessException;
import com.example.pf_management.entity.Account;
import com.example.pf_management.entity.Role;
import com.example.pf_management.mapper.AccountMapper;
import com.example.pf_management.mapper.RoleMapper;
import com.example.pf_management.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Account> findAccountsByCondition(Map<String, Object> param) {
        return accountMapper.findAccountsByCondition(param);
    }

    @Override
    public void insert(Account account) {
        accountMapper.insert(account);
        //新建账号和角色关系
        insertAccountRole(account);
    }

    @Override
    public Account findAccountById(Long id) {
        return accountMapper.findAccountById(id);
    }

    @Override
    public void update(Account account) {
        accountMapper.invalidAccountRole(account.getId());
        //新建账号和角色关系
        insertAccountRole(account);
        accountMapper.update(account);
    }

    @Override
    public void delete(Long id) {
        accountMapper.invalidAccountRole(id);
        accountMapper.delete(id);
    }

    private void insertAccountRole(Account account){
        //判断account中的roles是否为空
        if (account.getRoles() != null && !account.getRoles().isEmpty()){
            for (Role role : account.getRoles()) {
                //查询待关联的角色是否存在
                if (roleMapper.findRoleById(role.getId()) == null){
                    throw new BusinessException("指定的角色信息不存在");
                }
            }
            accountMapper.insertAccountRole(account);
        }
    }
}
