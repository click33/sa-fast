# sa-fast 快速开发平台 v1.5.0 

一个基于springboot架构的快速开发平台，内置代码生成器

### 功能架构 
- 权限验证基于sa-token：[http://sa-token.dev33.cn/](http://sa-token.dev33.cn/)
- 后台管理基于sa-admin：[http://sa-admin.dev33.cn/](http://sa-admin.dev33.cn/)
- 接口文档基于sa-doc：[http://sa-doc.dev33.cn/](http://sa-doc.dev33.cn/)
- 接口服务端基于springboot架构，集成常用功能，如：文件上传、角色授权、集成redis等等 
- 内置代码生成器，快速CRUD，可自定义生成模板，方便灵活 
- ...... 


### 使用教程

1. 获取最新代码，你可以从gitee，或者github上获取最新代码 
	- gitee地址：[https://gitee.com/sz6/sa-fast](https://gitee.com/sz6/sa-fast)
	- github地址：[https://github.com/click33/sa-fast](https://github.com/click33/sa-fast)

2. 目录介绍：
	- sf-core：代码生成器
	- sf-admin：后台管理
	- sf-server：接口服务端
	- sf-apidoc：api接口文档 
	- sf-devdoc：框架使用文档
	- doc：其它文件，例如：sql脚本

3. 创建数据库
	- 在mysql中创建数据，名字为：`sf-dev`
	- 导入脚本：`doc/sa-fast.sql`, 这个是sa-fast运行的内置库，必须存在
	- 导入脚本：`doc/test-data.sql`, 这个是为了测试代码生成准备的库  

4. 导入服务端
	- 在后端ide中导入项目 `sf-server`，此为接口服务端代码基架
	- 配置好redis环境后，启动运行 
	- （有关project4sf下的代码都是sa-fast内置代码，最好别动，你的模块包都写在project包下即可）
	- idea启动报错，无效绑定mapper，参考解决方案(方式2)：[https://blog.csdn.net/b515833/article/details/100009981](https://blog.csdn.net/b515833/article/details/100009981)
	
5. 导入后台管理
	- 在前端ide中导入 `sf-admin`, 此为后台管理代码基架
	- 启动运行 
	- （有关sa-html-sf下的代码都时sa-fast内置代码，最好别动, 你的代码写在sa-html\文件夹下）
	- （menu-list-sf.js是内置菜单文件，不要动，如果要添加路由，请在menu-list.js里添加（如果找不到，请新建，和menu-list-sf.js目录同级））
	
6. 导入代码生成器
	- 在后端ide中导入项目 `sf-core`，此为代码生成器
	- 导入项目后，直接运行main方法就可以生成代码
	- 运行前，请先确认相关配置信息
	- 主要的注意点为：服务端代码-存放路径、后台管理端项目地址、代码作者等等（这些可直接设置为`sf-server`和`sf-admin`项目所在路径）
	- 详细可查看代码注释
	- 生成的后台管理页面如果看不到，那可能是无权限，在 `权限管理->角色列表->分配权限` 选中即可
	- 生成代码的关键点，在于表注释的特殊声明，具体可查看下方的：[表注释特殊声明](#表注释特殊声明)

7. 导入接口文档
	- 在前端ide中导入 `sf-apidoc`, 此为接口文档基架
	- 启动运行 
	- 基于markdown格式编写接口文档，菜单树定义在 `_sidebar.md`，你可以新建`project`文件夹书写接口文档，详细可参考：[http://sa-doc.dev33.cn/](http://sa-doc.dev33.cn/)

8. 注意点
	- 在项目代码生成之后，可以直接重启运行
	- 生成的代码都是比较粗糙的，仅能维持基本功能点的运行，如果要作为正式项目开发，你是需要在生成代码的基础上再次修修补补的 
	- 另外一定要注意一点：**尽量不要将代码生成地址配置成你正在开发的项目地址，否则可能会在不小心的情况下，覆盖掉你二次修改后的代码**。
	- 如果要使用git功能，则需要把`.gitignore`文件的最后四行删除掉，因为这个文件把代码生成器生成的代码都给屏蔽掉了


### 表注释特殊声明
- **代码生成器的特殊声明，可以让代码生成器更加智能**
- 如果在建表时不写特殊声明，则只能生成普通输入框
- 如果要生成特殊的表单，如：数值输入、多行文本域、图片上传、多图上传、富文本等等，则需要写上特殊声明
- 语法为，写一个中括号，跟上关键字：`字段注释 [特殊声明]`
```
	[text]		声明一个普通输入框，这是默认值，还可以写成 [t]
	[no]		代表生成表单时忽略此字段 
	[img]		声明一个图片字段，会生成图片上传
	[img_list]	声明一个图片集合字段，会生成多图片上传，还可以写成 [imgList]
	[richtext]	声明一个富文本字段，会生成富文本插件，还可以写成 [f]
	[textarea]	声明一个多行文本域
	[num]		声明数值输入字段
	[enum]		声明一个枚举字段，具体语法请查看下方示例，还可以写成 [j]
	[date]		声明一个日期字段 
	[date-create]	声明一个日期字段（数据创建日期）
	[date-update]	声明一个日期字段（数据更新日期）
	[fk-1]		声明一个外键(主表数据<1000时使用，比如商品分类表)，格式为 [fk-1 pk=主表.主键.连表字段.连表字段注释]，例如：[fk-1 pk=sys_type.id.name.所属分类]
	[fk-2]		声明一个外键(主表数据>1000时使用，比如商品分类表)，格式为 [fk-2 pk=主表.主键.连表字段.连表字段注释]，例如：[fk-2 pk=ser_goods.id.name.所属商品]
```
- 具体例子，请查看下列建表sql，与最终生成的页面对比效果图

**建表语句**
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

**最终生成效果**
![列表](https://color-test.oss-cn-qingdao.aliyuncs.com/sa-fast/g-list.png)
![增加/修改](https://color-test.oss-cn-qingdao.aliyuncs.com/sa-fast/g-update.png)




### QQ群 
- QQ交流群：[782974737 点击加入](https://jq.qq.com/?_wv=1027&k=5DHN5Ib)
- 加入qq群，你将获得以下支持：
- 五分钟修复一些bug
- 半小时新增一个功能点 
- 为框架贡献一些好点子 



