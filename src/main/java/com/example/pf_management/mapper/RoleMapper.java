package com.example.pf_management.mapper;

import com.example.pf_management.entity.Menu;
import com.example.pf_management.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper {
    List<Role> findRolesByCondition(Map<String, Object> param);

    void insert(Role role);

    void insertRoleMenu(Role role);

    void invalidRoleMenuByRoleId(Long id);

    void update(Role role);

    Integer findAccountCountByRoleId(Long id);

    void delete(Long id);

    Role findRoleById(Long id);
}
