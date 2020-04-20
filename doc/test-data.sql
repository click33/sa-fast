
-- ======================================== 测试库（仅做测试使用） ====================================  


-- 用户表 
drop table if exists sys_user;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id号',
  `username` varchar(30) DEFAULT NULL COMMENT '昵称',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `sex` varchar(50) DEFAULT '男' COMMENT '性别',
  `tell` varchar(50) DEFAULT '...' COMMENT '介绍',
  `photo_list` varchar(2048) DEFAULT '[]' COMMENT '相册',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `status` int(11) DEFAULT '1' COMMENT '账号状态',
  `create_type` varchar(50) DEFAULT NULL COMMENT '创建方式',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12004 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';


-- 文章表 
drop table if exists ser_article;
CREATE TABLE `ser_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id号',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '内容',
  `zan_count` int(11) DEFAULT '0' COMMENT '点赞数量',
  `ct_count` int(11) DEFAULT '0' COMMENT '评论数量',
  `sc_count` int(11) DEFAULT '0' COMMENT '收藏数量',
  `see_count` int(11) DEFAULT '0' COMMENT '点击数量',
  `status` int(11) DEFAULT '1' COMMENT '帖子状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发表日期',
  `create_uid` bigint(20) DEFAULT NULL COMMENT '发表人',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT COMMENT='文章表';

INSERT INTO `ser_article`(`id`, `title`, `content`, `zan_count`, `ct_count`, `sc_count`, `see_count`, `status`, `create_time`, `create_uid`, `remarks`) VALUES (1001, '测试标题', '阿斯顿撒', 123, 213, 32, 213, 1, '2020-04-20 11:10:39', 1, '123');
INSERT INTO `ser_article`(`id`, `title`, `content`, `zan_count`, `ct_count`, `sc_count`, `see_count`, `status`, `create_time`, `create_uid`, `remarks`) VALUES (1002, '震惊，男默女泪', '震惊，男默女泪', 1, 1, 1, 1, 1, '2020-04-20 11:11:11', 1, '1');



-- 公告表 
drop table if exists sys_notice;
CREATE TABLE `sys_notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id号',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '内容',
  `see_count` int(11) DEFAULT '0' COMMENT '点击数量',
  `is_show` int(11) DEFAULT '1' COMMENT '是否显示',
  `is_lock` int(11) DEFAULT '2' COMMENT '是否锁定',
  `type_id` int(11) DEFAULT NULL COMMENT '分类id',
  `sort` bigint(20) DEFAULT '0' COMMENT '排序值',
  `create_time` timestamp NOT NULL COMMENT '发表日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT COMMENT='公告表';

INSERT INTO `sys_notice`(`id`, `title`, `content`, `see_count`, `is_show`, `is_lock`, `type_id`, `sort`, `create_time`) VALUES (1001, '系统通知', '系统通知', 1, 1, 1, 1, 1, '2020-04-20 11:11:37');
INSERT INTO `sys_notice`(`id`, `title`, `content`, `see_count`, `is_show`, `is_lock`, `type_id`, `sort`, `create_time`) VALUES (1002, '系统通知2', '系统通知2', 1, 1, 1, 1, 1, '2020-04-20 11:11:47');



-- 分类表
drop table if exists sys_type;
CREATE TABLE `sys_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id号',
  `name` varchar(50) NOT NULL COMMENT '分类名字',
  `sort` int(11) DEFAULT '1' COMMENT '排序值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `NAME` (`NAME`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT COMMENT='分类表';


-- 版本更新表 
drop table if exists sys_version;
CREATE TABLE `sys_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT '1' COMMENT '类型',
  `version_no` varchar(255) DEFAULT '' COMMENT '版本号',
  `down_href` varchar(255) DEFAULT '' COMMENT '下载路径',
  `down_count` bigint(20) DEFAULT '0' COMMENT '下载次数',
  `is_show` int(11) DEFAULT '1' COMMENT '是否启用',
  `remarks` varchar(5000) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='版本更新表'; 


-- 兑换码表  
drop table if exists sys_redeem;
CREATE TABLE `sys_redeem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `password` varchar(50) NOT NULL COMMENT '红包密码',
  `money` bigint(20) DEFAULT NULL COMMENT '红包金额',
  `status` int(11) DEFAULT '2' COMMENT '状态',
  `take_uid` bigint(20) DEFAULT NULL COMMENT '领取人',
  `take_openid` varchar(127) DEFAULT NULL COMMENT 'xopenid',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=182192 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT COMMENT='兑换码表';

INSERT INTO `sys_redeem`(`id`, `password`, `money`, `status`, `take_uid`, `take_openid`, `create_time`) VALUES (182192, 'sdsas', 12, 1, 1, '1', '2020-04-20 11:12:39');


