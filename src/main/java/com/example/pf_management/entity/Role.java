package com.example.pf_management.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Role {
    private Long id;

    private String roleName; //角色名称

    private String roleCode; //'角色编码'

    private Boolean flag;//'生效标识'

    private Timestamp gmtCreate;// '创建时间'

    private Timestamp gmtModified;//'修改时间'

    private List<Menu> menus; //关联的菜单id

    private Boolean isEnabled; //是否启用
}
