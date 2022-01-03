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
   user_name            varchar(50) comment '�û���',
   name                 varchar(200) comment '�ǳ�',
   password             varchar(64) comment '����',
   is_enabled           tinyint comment '�Ƿ�����',
   gmt_create           datetime comment '����ʱ��',
   gmt_modified         datetime comment '�޸�ʱ��',
   flag					tinyint  comment '��Ч��ʶ',
   primary key (id)
)comment '�˻���';

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

alter table pf_account_menu comment '�˺Ų˵�������';

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
)comment '�˻���ɫ������';

/*==============================================================*/
/* Table: pf_menu                                               */
/*==============================================================*/
CREATE TABLE `pf_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `menu_code` varchar(3)  COMMENT '�˵�����',
  `menu_name` varchar(20) COMMENT '�˵���',
  `menu_type` tinyint COMMENT '�˵�����',
  `url` varchar(50)  COMMENT 'url',
  `parent_id` bigint COMMENT '���˵�id',
  `gmt_create` datetime  COMMENT '����ʱ��',
  `gmt_modified` datetime COMMENT '�޸�ʱ��',
  `flag` tinyint(1) COMMENT '��Ч��ʶ',
  PRIMARY KEY (`id`)
) COMMENT='�˵���';

/*==============================================================*/
/* Table: pf_role                                               */
/*==============================================================*/
create table pf_role
(
   id                   bigint not null auto_increment,
   role_name            varchar(20) comment '��ɫ����',
   role_code            varchar(1) comment '��ɫ����',
   flag           		tinyint comment '��Ч��ʶ',
   gmt_create           datetime comment '����ʱ��',
   gmt_modified         datetime comment '�޸�ʱ��',
   is_enabled			tinyint comment '�Ƿ�����',
   primary key(id)
)comment = '��ɫ��';

/*==============================================================*/
/* Table: pf_role_menu                                          */
/*==============================================================*/
create table pf_role_menu
(
   id                   bigint not null auto_increment,
   role_id              bigint comment '��ɫid',
   menu_id              bigint comment '�˵�id',
   gmt_create           datetime comment '����ʱ��',
   gmt_modified         datetime comment '�޸�ʱ��',
   flag					tinyint comment '��Ч��ʶ',
   primary key (id)
) comment '��ɫ�˵�������';

-- �ݹ��ѯ�Ӳ˵�
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
