package com.example.pf_management.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Account {
    private Long id;

    private String username; //'用户名',

    private String name;//'昵称',

    private String password;//'密码',

    private Boolean isEnabled;//'是否启用',

    private Timestamp gmtCreate;//'创建时间',

    private Timestamp gmtModified;//'修改时间',

    private Boolean flag;//'生效标识',

    private List<Role> roles;//角色列表
}
