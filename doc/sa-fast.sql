

-- ======================================== sa-fast 系统库 ====================================  


-- 系统角色表 
drop table if exists sf_role; 
CREATE TABLE `sf_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id，--主键、自增',
  `role_name` varchar(20) NOT NULL COMMENT '角色名称, 唯一约束',
  `role_info` varchar(200) DEFAULT NULL COMMENT '角色详细描述',
  `is_lock` int(11) NOT NULL DEFAULT '1' COMMENT '是否锁定(1=是,2=否), 锁定之后不可随意删除, 防止用户误操作',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `role_name` (`role_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统角色表';

INSERT INTO `sf_role`(`id`, `role_name`, `role_info`, `is_lock`) VALUES (1, '超级管理员', '最高权限', 1);
INSERT INTO `sf_role`(`id`, `role_name`, `role_info`, `is_lock`) VALUES (11, '普通账号', '普通账号', 1);
INSERT INTO `sf_role`(`id`, `role_name`, `role_info`, `is_lock`) VALUES (12, '测试角色', '测试角色', 2);


-- 角色权限对应表  
drop table if exists sf_role_permission; 
CREATE TABLE `sf_role_permission` (
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID ',
  `permission_code` varchar(50) DEFAULT NULL COMMENT '菜单项ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色权限中间表';

insert into sf_role_permission() values ('1', '-1', now());
insert into sf_role_permission() values ('1', '1', now());
insert into sf_role_permission() values ('1', '11', now());
insert into sf_role_permission() values ('1', '99', now());
insert into sf_role_permission() values ('1', '101', now());
insert into sf_role_permission() values ('1', '101-1', now());
insert into sf_role_permission() values ('1', '101-2', now());
insert into sf_role_permission() values ('1', '101-3', now());
insert into sf_role_permission() values ('1', '101-4', now());
insert into sf_role_permission() values ('1', '101-5', now());


-- 系统管理员表 
drop table if exists sf_admin; 
CREATE TABLE `sf_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id，--主键、自增',
  `name` varchar(100) NOT NULL COMMENT 'admin名称',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像地址',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `pw` varchar(50) DEFAULT NULL COMMENT '明文密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `role_id` int(11) DEFAULT '11' COMMENT '所属角色id',
  `status` int(11) DEFAULT '1' COMMENT '账号状态(1=正常, 2=禁用)',
  `create_by_aid` bigint(20) DEFAULT '-1' COMMENT '创建自哪个管理员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '上次登陆时间',
  `login_ip` varchar(50) DEFAULT NULL COMMENT '上次登陆IP',
  `login_count` int(11) DEFAULT '0' COMMENT '登陆次数',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统管理员表';

INSERT INTO `sf_admin`(`id`, `name`, `avatar`, `password`, `pw`, `role_id`, create_time) 
VALUES (10001, 'sa', '../../sa-resources/admin-logo.png', 'E4EF2A290589A23EFE1565BB698437F5', '123456', 1, now()); 


-- 系统api请求记录表 
drop table if exists sf_apilog; 
CREATE TABLE `sf_apilog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `req_id` varchar(50) NOT NULL COMMENT '请求id',
  `req_ip` varchar(100) DEFAULT NULL COMMENT '客户端ip',
  `req_api` varchar(512) DEFAULT NULL COMMENT '请求api',
  `req_parame` text COMMENT '请求参数',
  `req_type` varchar(50) DEFAULT NULL COMMENT '请求类型（GET、POST...）',
  `req_token` varchar(50) DEFAULT NULL COMMENT '请求token',
  `res_code` varchar(50) DEFAULT NULL COMMENT '返回-状态码',
  `res_msg` text COMMENT '返回-信息描述',
  `res_string` text COMMENT '返回-整个信息字符串形式',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'user_id',
  `admin_id` bigint(20) DEFAULT NULL COMMENT 'admin_id',
  `start_time` datetime(3) DEFAULT NULL COMMENT '请求开始时间',
  `end_time` datetime(3) DEFAULT NULL COMMENT '请求结束时间',
  `cost_time` bigint(20) DEFAULT NULL COMMENT '花费时间，单位ms',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `req_id` (`req_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='api请求记录表';


-- ======================================== 测试库 ====================================  



