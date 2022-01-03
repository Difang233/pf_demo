package com.example.pf_management.service.impl;

import com.example.pf_management.common.BusinessException;
import com.example.pf_management.entity.Menu;
import com.example.pf_management.entity.Role;
import com.example.pf_management.mapper.MenuMapper;
import com.example.pf_management.mapper.RoleMapper;
import com.example.pf_management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Role> findRolesByCondition(Map<String, Object> param) {
        return roleMapper.findRolesByCondition(param);
    }

    @Override
    public void insert(Role role) {
        roleMapper.insert(role);
        //新建关联关系
        insertRoleMenu(role);
    }

    @Override
    public void update(Role role) {
        //将原有和菜单的关联全部置为失效
        roleMapper.invalidRoleMenuByRoleId(role.getId());
        roleMapper.update(role);
        //新建关联关系
        insertRoleMenu(role);
    }

    @Override
    public void delete(Long id) {
        if (roleMapper.findAccountCountByRoleId(id) > 0){
            throw new BusinessException("该角色有关联的账户，不能删除");
        }
        roleMapper.invalidRoleMenuByRoleId(id);
        roleMapper.delete(id);
    }

    @Override
    public Role findRoleById(Long id) {
        return roleMapper.findRoleById(id);
    }

    /**
     * 判断传入的role中是否有menus且不为空列表，若是则更新pf_role_menu
     * @param role
     */
    private void insertRoleMenu(Role role){
        List<Menu> menus = role.getMenus();
        //前段传入menus时且不为空列表时才创建关联
        if (menus != null && !menus.isEmpty()){
            //插入pf_role_menu前查询待关联的菜单是否存在
            for (Menu menu : menus) {
                if (menuMapper.findMenuById(menu.getId()) == null){
                    throw new BusinessException("选择的菜单不存在");
                }
            }
            roleMapper.insertRoleMenu(role);
        }
    }
}
