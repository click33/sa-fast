# 项目架构

项目采用前后台分离架构

---


## 1、项目技术栈

##### 后端 
- 数据库：`mysql 5.7`
- 后端框：`springboot`
- 数据层：`mybatis-plus`（生成的代码基于`mybatis`）
- 连接池：`druid`
- 分页插件：`pagehelper插件`
- 缓存插件：`redis`
- 权限认证：`sa-token`
- 文件上传：基于应用服务器的文件上传（可配置为`oss`）
- 其它插件：`lombok`、`hutool`、`fastjson` 等等 

##### 前端
- 模板：`sa-admin`
- js框架：`vue.js`
- ui框架：`element-ui`
- 富文本：`wangEditor`
- 其它库：`jquery`、`layer`、`swiper`、`echarts` 等等 

##### 接口文档
- 基于：`sa-doc`



## 2、后端代码介绍

##### 各包说明 

> - com.pj 	主包
> 	- `current`		&emsp; 存放有关全局行为的一些包
> 	- `project`		&emsp; 项目模块总包
> 	- `project4sf`	&emsp; sa-fast 内置的模块总包
> 	- `utils`		&emsp; 工具类包 
> 	- `SfServerApplication.java`		&emsp; 启动类


##### 详细说明  

``` java
├─current			存放有关全局行为的一些包
│  ├─ config				项目配置类	（ yml 里 spring.myconfig 的相关配置会映射到MyConfig.java类属性中 ）
│  ├─ global				项目全局行为 (全局日志与全局异常处理)
│  ├─ mybatis			mybatis 相关配置（mapper.xml文件热刷新配置）
│  ├─ satoken			sa-token 相关配置 
│  │  CorsFilter.java		解决跨域问题的过滤器
│  │  SaFastPrint.java		sa-fast启动打印字符画
│  │ 
├─project		项目模块包，按表分包 
│  ├─ xxx			
│  ├─ xxx			
│  │ 
├─project4sf		sa-fast内置模块包 ，按表分包 
│  ├─ xxx
│  ├─ xxx
│  │ 
├─utils				工具类包 
│   ├─sg				sa-fast内置工具类包 
│   │       AjaxError.java		异常判断工具类 
│   │       AjaxJson.java		全局controller返回值工具类
│   │       NbUtil.java			一些常见util方法
│   │       SoMap.java			Map类优化
│   │       WebNbUtil.java		有关web的一些工具类 
│   │  JHttpUtil.java		java访问http服务工具类 
│   │  LogUtil.java			log打印工具类 
│   │  Ttime.java			计时器工具类 
│   
├── SfServerApplication.java		启动类 

```


## 3、前端代码介绍 

``` java 
├─sa-html			项目模块代码存放路径
├─sa-html-sf		sa-fast内置模块存放路径 
├─sa-resources		sa-admin 资源文件存放路径 
│  │  menu-list-sf.js		sa-fast 内置菜单文件 
│  │  menu-list.js			自定义菜单文件 
│  │  sa-code.js			sa-admin初始化代码文件 
│  
├─static			静态文件存放路径
│   │  sa.css			全局样式存放路径
│   │  sa.js			全局js存放路径，包含一系列工具类
│ 
│ index.html		首页
│ login.html		登录页
│ main.html		首屏页
```

## 4、接口文档代码介绍

``` java 
├─project		文档存放路径 
│      xxx.md	
├─sa-lib		sa-doc 相关资源 
│  index.html	首页 
│  _sidebar.md	目录树定义文件 
│
```















