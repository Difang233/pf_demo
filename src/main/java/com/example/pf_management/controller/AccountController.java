package com.example.pf_management.controller;

import com.example.pf_management.common.BusinessException;
import com.example.pf_management.common.Result;
import com.example.pf_management.entity.Account;
import com.example.pf_management.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 根据条件查询账户，可选条件：用户名，昵称，是否启用，关联的角色
     * @param username
     * @param name
     * @param isEnabled
     * @param role
     * @return
     */
    @GetMapping
    public Result findAccounts(@RequestParam(required = false, name = "username") String username,
                               @RequestParam(required = false, name = "name") String name,
                               @RequestParam(required = false, name = "isEnabled") Boolean isEnabled,
                               @RequestParam(required = false, name = "role") Long role){
        Map<String, Object> param = new HashMap<>();
        param.put("username", username);
        param.put("name", name);
        param.put("isEnabled", isEnabled);
        param.put("role", role);
        List<Account> accounts = accountService.findAccountsByCondition(param);
        return Result.succ(accounts);
    }

    /**
     * 根据id查询账号信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findAccountById(@PathVariable("id") Long id){
        return Result.succ(accountService.findAccountById(id));
    }

    /**
     * 添加账号信息
     * @param account
     * @return
     */
    @PostMapping
    public Result insert(@RequestBody Account account){
        if (account.getUsername() == null || "".equals(account.getUsername())){
            throw new BusinessException("传入的用户名为空");
        }
        if (account.getPassword() == null || "".equals(account.getPassword())){
            throw new BusinessException("传入的密码为空");
        }
        accountService.insert(account);
        return Result.succ();
    }

    /**
     * 根据id更新账号信息
     * @param account
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Account account){
        if (account.getId() == null){
            throw new BusinessException("传入的id为空");
        }
        accountService.update(account);
        return Result.succ();
    }

    /**
     * 根据id删除账号
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Long id){
        accountService.delete(id);
        return Result.succ();
    }
}
