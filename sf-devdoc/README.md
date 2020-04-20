# sa-fast 快速开发平台

一个基于springboot架构的快速开发平台，内置代码生成器

### 功能架构 
- 接口服务端基于springboot架构，集成常用功能，如：文件上传、角色授权、集成redis等等 
- 后台管理基于sa-admin：[http://sa-admin.dev33.cn/](http://sa-admin.dev33.cn/)
- 权限验证基于sa-token：[http://sa-token.dev33.cn/](http://sa-token.dev33.cn/)
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
	- sf-devdoc：使用文档
	- doc：其它文件，例如：sql脚本

3. 创建数据库
	- 在mysql中创建数据，名字为：`sf-dev`
	- 导入脚本：`doc/sa-fast.sql`, 这个是sa-fast运行的内置库，必须存在
	- 导入脚本：`doc/test-data.sql`, 这个是为了测试代码生成准备的库  

4. 导入服务端
	- 在后端ide中导入项目 `sf-server`，此为接口服务端代码基架
	- 配置好redis环境后，启动运行 
	
5. 导入后台管理
	- 在前端ide中导入 `sf-admin`, 此为后台管理代码基架
	- 启动运行 
	
6. 导入代码生成器
	- 在后端ide中导入项目 `sf-core`，此为代码生成器
	- 导入项目后，直接运行main方法就可以生成代码
	- 运行前，请先确认相关配置信息
	- 主要的注意点为：服务端代码-存放路径、后台管理端项目地址、代码作者等等（这些可直接设置为`sf-server`和`sf-admin`项目所在路径）
	- 详细可查看代码注释

7. 注意点
	- 在项目代码生成之后，可以直接重启运行
	- 生成的代码都是非常粗糙的，仅能维持基本功能点的运行，如果要作为正式项目开发，你是需要在生成代码的基础上再次休息补补的 
	- 另外一定要注意一点：**尽量不要将代码生成地址配置成你正在开发的项目地址，否则可能会在不小心的情况下，覆盖掉你二次修改后的代码**。
	- 如果要使用git功能，则需要把`.gitignore`文件的最后四行删除掉，因为这个文件把代码生成器生成的代码都给屏蔽掉了


### QQ群 
QQ交流群：[782974737 点击加入](https://jq.qq.com/?_wv=1027&k=5DHN5Ib)




