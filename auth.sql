/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/11/2 11:43:59                           */
/*==============================================================*/


drop table if exists pf_account;

drop table if exists pf_account_menu;

drop table if exists pf_account_role;

drop table if exists pf_menu;

drop table if exists pf_role;

drop table if exists pf_role_menu;

/*==============================================================*/
/* Table: pf_account                                            */
/*==============================================================*/
create table pf_account
(
   id                   bigint not null,
   user_name            varchar(50) comment '用户名',
   name                 varchar(200) comment '昵称',
   password             varchar(64) comment '密码',
   is_enabled           tinyint comment '是否启用',
   gmt_create           datetime comment '创建时间',
   gmt_modified         datetime comment '修改时间',
   flag					tinyint  comment '生效标识',
   primary key (id)
)comment '账户表';

/*==============================================================*/
/* Table: pf_account_menu                                       */
/*==============================================================*/
create table pf_account_menu
(
   id                   bigint not null,
   account_id           bigint,
   menu_id              bigint,
   gmt_create           datetime,
   gmt_modified         datetime,
   primary key (id)
);

alter table pf_account_menu comment '账号菜单关联表';

/*==============================================================*/
/* Table: pf_account_role                                       */
/*==============================================================*/
create table pf_account_role
(
   id                   bigint not null auto_increment,
   account_id           bigint,
   role_id              bigint,
   gmt_create           datetime,
   gmt_modified         datetime,
   flag					tinyint,
   primary key (id)
)comment '账户角色关联表';

/*==============================================================*/
/* Table: pf_menu                                               */
/*==============================================================*/
CREATE TABLE `pf_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `menu_code` varchar(3)  COMMENT '菜单编码',
  `menu_name` varchar(20) COMMENT '菜单名',
  `menu_type` tinyint COMMENT '菜单类型',
  `url` varchar(50)  COMMENT 'url',
  `parent_id` bigint COMMENT '父菜单id',
  `gmt_create` datetime  COMMENT '创建时间',
  `gmt_modified` datetime COMMENT '修改时间',
  `flag` tinyint(1) COMMENT '生效标识',
  PRIMARY KEY (`id`)
) COMMENT='菜单表';

/*==============================================================*/
/* Table: pf_role                                               */
/*==============================================================*/
create table pf_role
(
   id                   bigint not null auto_increment,
   role_name            varchar(20) comment '角色名称',
   role_code            varchar(1) comment '角色编码',
   flag           		tinyint comment '生效标识',
   gmt_create           datetime comment '创建时间',
   gmt_modified         datetime comment '修改时间',
   is_enabled			tinyint comment '是否启用',
   primary key(id)
)comment = '角色表';

/*==============================================================*/
/* Table: pf_role_menu                                          */
/*==============================================================*/
create table pf_role_menu
(
   id                   bigint not null auto_increment,
   role_id              bigint comment '角色id',
   menu_id              bigint comment '菜单id',
   gmt_create           datetime comment '创建时间',
   gmt_modified         datetime comment '修改时间',
   flag					tinyint comment '生效标识',
   primary key (id)
) comment '角色菜单关联表';

-- 递归查询子菜单
DROP function IF EXISTS `get_child_menu`;

DELIMITER $$
USE `pf_demo`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `get_child_menu`(v_parent_id bigint) RETURNS varchar(4000) CHARSET utf8mb4 COLLATE utf8mb4_general_ci
    READS SQL DATA
BEGIN
	declare result, temp varchar(4000);
    set temp := cast(v_parent_id as char);
    set result := '';
    while temp is not null do
		set result := concat(result, ',', temp);
		select group_concat(id) into temp
        from pf_menu
        where find_in_set(parent_id, temp) > 0;
	end while;
RETURN substring(result,2);
END$$

DELIMITER ;
