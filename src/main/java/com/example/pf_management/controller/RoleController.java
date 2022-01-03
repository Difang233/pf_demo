package com.example.pf_management.controller;

import com.alibaba.druid.util.StringUtils;
import com.example.pf_management.common.BusinessException;
import com.example.pf_management.common.Result;
import com.example.pf_management.entity.Role;
import com.example.pf_management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 根据条件查询角色列表
     * 可选条件：角色名，角色编码，关联的菜单id, 是否启用
     * @param name
     * @param code
     * @param menuId
     * @param isEnabled
     * @return
     */
    @GetMapping
    public Result roleList(@RequestParam(name = "name", required = false) String name,
                               @RequestParam(name = "code", required = false) String code,
                               @RequestParam(name = "menuId", required = false) Long menuId,
                               @RequestParam(name = "isEnabled", required = false) Boolean isEnabled){
        Map<String, Object> param = new HashMap<>();
        param.put("name", name);
        param.put("code", code);
        param.put("menuId", menuId);
        param.put("isEnabled", isEnabled);
        List<Role> roles = roleService.findRolesByCondition(param);
        return Result.succ(roles);
    }

    /**
     * 根据id查找角色信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result role(@PathVariable("id") Long id){
        return Result.succ(roleService.findRoleById(id));
    }

    /**
     * 添加角色信息，并添加角色和菜单的关系（可选）
     * @param role
     * @return
     */
    @PostMapping
    public Result insert(@RequestBody Role role){
        //必须输入角色名
        if (role.getRoleName() == null || "".equals(role.getRoleName())){
            throw new BusinessException("传入的角色名为空");
        }
        roleService.insert(role);
        return Result.succ();
    }

    /**
     * 更新角色信息，不能更新flag字段（应在delete中更新）
     * @param role
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Role role){
        if (role.getId() == null){
            throw new BusinessException("必须输入更新的id");
        }
        roleService.update(role);
        return Result.succ();
    }

    /**
     * 删除角色信息，同时删除与菜单之间的关联
     * 如果待删除角色下有用户，则不允许删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Long id){
        roleService.delete(id);
        return Result.succ();
    }
}
