package com.example.pf_management.service;

import com.example.pf_management.entity.Menu;

import java.util.List;

public interface MenuService {

    void insert(Menu menu);

    List<Menu> findFirstLevelMenu();

    List<Menu> findChildMenuByParentId(Long parentId);

    Menu findMenuById(Long id);

    void update(Menu menu);

    void delete(Long id);
}
