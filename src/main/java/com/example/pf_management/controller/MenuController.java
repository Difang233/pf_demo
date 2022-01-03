package com.example.pf_management.controller;

import com.example.pf_management.common.BusinessException;
import com.example.pf_management.common.Constant;
import com.example.pf_management.common.Result;
import com.example.pf_management.entity.Menu;
import com.example.pf_management.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 添加菜单信息
     * @param menu
     * @return
     */
    @PostMapping
    public Result insert(@RequestBody Menu menu){
        //前端传入的数据必须包括menuName和url, parentId如果为空则默认为0
        if (menu.getMenuName() == null || "".equals(menu.getMenuName())){
            throw new BusinessException("没有传入菜单名");
        }
        if (menu.getUrl() == null || "".equals(menu.getUrl())){
            throw new BusinessException("没有传入url");
        }
        menuService.insert(menu);
        return Result.succ();
    }

    /**
     * 查询所有parent_id为0的菜单
     * @return
     */
    @GetMapping("/firstLevelMenu")
    public Result findFirstLevelMenu(){
        return Result.succ(menuService.findFirstLevelMenu());
    }

    /**
     * 根据parent_id递归查询所有子菜单
     * @param parentId
     * @return
     */
    @GetMapping("/childMenu/{parent_id}")
    public Result findChildMenu(@PathVariable("parent_id")Long parentId) {
        return Result.succ(menuService.findChildMenuByParentId(parentId));
    }

    /**
     * 根据菜单id查询菜单信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findMenu(@PathVariable("id")Long id){
        return Result.succ(menuService.findMenuById(id));
    }

    /**
     * 根据菜单id更新菜单信息，但不会更新flag字段（应在删除方法中设置）
     * @param menu
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Menu menu){
        //更新时必须传入菜单id
        if (menu.getId() == null){
            throw new BusinessException("没有传入菜单id");
        }
        menuService.update(menu);
        return Result.succ();
    }

    /**
     * 删除菜单：
     *    1.删除前检查是否有角色关联，没有才可以删除
     *    2.递归删除当前菜单及其下所有子菜单
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Long id){
        menuService.delete(id);
        return Result.succ();
    }
}
