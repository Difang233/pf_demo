package com.example.pf_management.service;

import com.example.pf_management.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    List<Role> findRolesByCondition(Map<String, Object> param);

    void insert(Role role);

    void update(Role role);

    void delete(Long id);

    Role findRoleById(Long id);
}
