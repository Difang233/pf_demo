package com.example.pf_management.service.impl;

import com.example.pf_management.common.BusinessException;
import com.example.pf_management.entity.Menu;
import com.example.pf_management.mapper.MenuMapper;
import com.example.pf_management.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public void insert(Menu menu){
        //插入前判断parentId是否存在
        if (menu.getParentId() != null){
            if (menuMapper.findMenuById(menu.getParentId()) == null){
                throw new BusinessException("传入的parentId不存在");
            }
        }
        menuMapper.insert(menu);
    }

    @Override
    public List<Menu> findFirstLevelMenu(){
        return menuMapper.findFirstLevelMenu();
    }

    @Override
    public List<Menu> findChildMenuByParentId(Long parentId){
        return menuMapper.findChildMenuByParentId(parentId);
    }

    @Override
    public Menu findMenuById(Long id) {
        return menuMapper.findMenuById(id);
    }

    @Override
    public void update(Menu menu) {
        menuMapper.update(menu);
    }

    @Override
    public void delete(Long id) {
        if (menuMapper.findRoleCountByMenuId(id) > 0){
            throw new BusinessException("有角色与菜单关联，不能删除");
        }
        menuMapper.delete(id);
    }


}
