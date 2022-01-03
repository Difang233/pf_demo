package com.example.pf_management.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Menu {
    private Long id;

    private String menuCode;

    private String menuName;

    private Byte menuType;

    private String url;

    private Long parentId;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    private Boolean flag;
}
