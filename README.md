# sa-fast 快速开发框架 v2.1.0 

一个基于springboot架构的快速开发框架，内置代码生成器
- 只需声明简单的表注释，即可生成完善的增删改查
- 数据库表建好了，项目也就开发一半了 
- 消灭重复，我们是专业的

### 功能架构 
- 权限验证基于sa-token：[http://sa-token.dev33.cn/](http://sa-token.dev33.cn/)
- 后台管理基于sa-admin：[http://sa-admin.dev33.cn/](http://sa-admin.dev33.cn/)
- 接口文档基于sa-doc：[http://sa-doc.dev33.cn/](http://sa-doc.dev33.cn/)
- 接口服务端基于springboot架构，集成常用功能，如：文件上传、角色授权、集成redis等等 
- 内置代码生成器，快速CRUD，可自定义生成模板，方便灵活 
- ...... 


### 官网首页
- 戳我：[http://sa-fast.dev33.cn/](http://sa-fast.dev33.cn/)


### 在线文档
- 戳我：[http://sa-fast.dev33.cn/doc/index.html](http://sa-fast.dev33.cn/doc/index.html)


### 简单示例

**只需要在建表时声明如下注释**

```
	-- 商品表 
	drop table if exists ser_goods;
	CREATE TABLE `ser_goods` (
	  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id [no]', 
	  `name` varchar(200) DEFAULT NULL COMMENT '商品名称 [text]', 
	  `avatar` varchar(512) DEFAULT NULL COMMENT '商品头像 [img]', 
	  `image_list` varchar(2048) COMMENT '轮播图片 [img_list]', 
	  `content` text COMMENT '图文介绍 [f]', 
	  `remark` varchar(512) DEFAULT NULL COMMENT '商品备注 [textarea]',
	  `money` int(11) DEFAULT '0' COMMENT '商品价格 [num]', 
	  `stock_count` int(11) DEFAULT 0 COMMENT '剩余库存 [num]',
	  `status` int(11) DEFAULT '1' COMMENT '商品状态 (1=上架,2=下架) [j]',
	  `create_time` datetime COMMENT '创建日期 [date-create]',
	  `update_time` datetime COMMENT '更新日期 [date-update]',
	  PRIMARY KEY (`id`) USING BTREE
	) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='商品表';
	
	
	INSERT INTO `ser_goods`(`id`, `name`, `avatar`, `image_list`, `remark`, `content`, `money`, `stock_count`, `status`, `create_time`, `update_time`) VALUES (1001, '小苹果', 'http://127.0.0.1:8099/upload/image/2020/05-11/1589130441278158564136.jpg', 'http://127.0.0.1:8099/upload/image/2020/05-11/15891304215541588315943.png', '这是一个小呀小苹果', '这是一个小呀小苹果<p><br></p>', 23, 213, 1, now(), now());
	INSERT INTO `ser_goods`(`id`, `name`, `avatar`, `image_list`, `remark`, `content`, `money`, `stock_count`, `status`, `create_time`, `update_time`) VALUES (1002, '大鸭梨', 'http://127.0.0.1:8099/upload/image/2020/05-11/15891304588142094778376.png', 'http://127.0.0.1:8099/upload/image/2020/05-11/15891301925381859798545.jpg', '大鸭梨', '<p>大鸭梨图文介绍</p>', 214, 234, 1, now(), now());
	INSERT INTO `ser_goods`(`id`, `name`, `avatar`, `image_list`, `remark`, `content`, `money`, `stock_count`, `status`, `create_time`, `update_time`) VALUES (1003, '小橘子', 'http://127.0.0.1:8099/upload/image/2020/05-11/15891326019482012079187.jpg', 'http://127.0.0.1:8099/upload/image/2020/05-11/1589133225670119768604.jpg', '小橘子', '<p>小橘子</p>', 123, 123, 2, now(), now());
	
```

<br>

**就可以生成如下样式的增删改查表单**

![列表](https://color-test.oss-cn-qingdao.aliyuncs.com/sa-fast/g-list.png)
![增加/修改](https://color-test.oss-cn-qingdao.aliyuncs.com/sa-fast/g-update.png)



### 所有特殊声明 

| 声明				 | 说明															| 详见		|
| :--------			| :--------														| :--------	|
| [text]			| 声明一个普通输入框，这是默认值，还可以写成 `[t]`	（`[text j=like]代表模糊查询`）			| 无		|
| [no]			| 代表生成表单时忽略此字段										| 无		|
| [img]			| 声明一个图片字段，会生成图片上传								| 无		|
| [audio]			| 声明一个音频字段，会生成音频上传								| 无		|
| [video]			| 声明一个视频字段，会生成视频上传								| 无		|
| [img_list]		| 声明一个图片集合字段，会生成多图片上传，还可以写成 `[imgList]`	| 无		|
| [richtext]		| 声明一个富文本字段，会生成富文本插件，还可以写成 `[f]`			| 无		|
| [textarea]		| 声明一个多行文本域											| 无		|
| [num]			| 声明数值输入字段												| 无		|
| [enum]			| 声明一个枚举字段，具体语法请查看下方示例，还可以写成 `[j]`		| 无		|
| [date]			| 声明一个日期字段												| 无		|
| [date-create]	| 声明一个日期字段（数据创建日期）								| 无		|
| [date-update]	| 声明一个日期字段（数据更新日期）								| 无		|
| [fk-1] 			| 声明一个外键(主表数据<1000时使用，比如商品分类表)，格式为 [fk-1 pk=主表.主键.连表字段.连表字段注释]，例如：`[fk-1 pk=sys_type.id.name.所属分类]` | [fk-1](#-fk-1-简单外键) |
| [fk-2] 			| 声明一个外键(主表数据>1000时使用，比如用户表)，格式为 [fk-2 pk=主表.主键]，例如：`[fk-2 pk=sys_user.id]` | [fk-2](#-fk-2-复杂外键) |
| --notp			| 此字段取消解析								| 无		|




## 贡献代码
1. 在github上fork一份到自己的仓库
2. clone自己的仓库到本地电脑
3. 在本地电脑修改、commit、push
4. 提交pr（点击：New Pull Request）（提交pr前请保证自己fork的仓库是最新版本，如若不是先强制更新一下）
5. 等待合并


## 建议贡献的地方
- 修复源码现有bug，或增加新的实用功能
- 完善在线文档，或者修复现有错误之处
- 如果更新实用功能，可在文档友情链接处留下自己的推广链接


## QQ群 
- QQ交流群：[782974737 点击加入](https://jq.qq.com/?_wv=1027&k=5DHN5Ib)
- 加入qq群，你将获得以下支持：
- 五分钟修复一些bug
- 半小时新增一个功能点 
- 为框架贡献一些好点子 



