package com.example.pf_management.mapper;

import com.example.pf_management.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    void insert(Menu menu);

    List<Menu> findFirstLevelMenu();

    List<Menu> findChildMenuByParentId(Long id);

    Menu findMenuById(Long id);

    void update(Menu menu);

    Integer findRoleCountByMenuId(Long id);

    void delete(Long id);
}
