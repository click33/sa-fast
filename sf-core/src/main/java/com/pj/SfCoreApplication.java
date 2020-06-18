package com.pj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fly.spring.SqlFlySetup;
import com.pj.gen.GenUtil;
import com.pj.gen.cfg.GenCfgManager;

@SqlFlySetup
@SpringBootApplication
public class SfCoreApplication {
	
	// 然后直接运行代码生成器
	public static void main(String[] args) {

		// 启动springboot  
		SpringApplication.run(SfCoreApplication.class, args); 
		
		// ===================================  你可以重写一些内部逻辑，填充一些功能 =================================== 
//		// 例如 以下代码代表截取掉表前缀 
//		DbModelManager.manager = new DbModelManager() {
//			// 重写创建 DbTable 的函数 
//			public DbTable getDbTable() {
//				return new DbTable() {
//					// 重写获取模块名称的函数 
//					public String getMkName(){
//						String tableName = this.getTableName();
//						return tableName.replaceAll("sys_", "").replaceAll("_table", "");	// 结果: sys_user_table --> user 	
//					}
//				};
//			}
//		};
		// ================== end 

		
		// ===================================  一些全局设置  =================================== 
		GenCfgManager.cfg
			.setProjectPath("E:/work/project-yun/sa-fast/sf-server/")	// 服务端代码 - 项目地址 
	        .setCodePath("src/main/java/")					// 服务端代码 - 存放路径 
	        .setPackagePath( "com.pj.project")				// 服务端代码 - 总包名 
	        .setPackage_utils("com.pj.utils.sg.*")		// util类包地址 
	        .setAuthor("shengzhang")								// 代码作者 (一定要换成您的大名哦，哈哈)
            .setAdminPath("E:/work/project-yun/sa-fast/sf-admin/")		// 后台管理端项目地址   
            .setAdminCodePath("sa-html/")						// 后台管理代码存放目录 
            .setApidocPath("E:/work/project-yun/sa-fast/sf-apidoc/")		// 接口文档项目地址    
            .setApidocCodePath("project/")						// 接口文档存放目录  
            .setFileUploadWay(1)			// 文件上传方式 (1=普通文件上传, 2=阿里云oss文件服务器[需要集成阿里云oss相关工具类]) 
            .setModelVisitWay(1)			// 实体类的访问权限修饰符 (1=private, 2=public)  
            .setModelDocWay(1)				// 实体类的注释形式 (1=行尾注释, 2=双星文档注释)  
            .setModelStyle(1) 				// 实体类字段风格 (1=保留下划线, 2=下划线转驼峰) （如果打开下划线转驼峰，需打开yml配置文件的 map-underscore-to-camel-case=true 选项 ）
            // .addTableName("sys_user")	// 添加要生成的表 
            .addTableAll()		// 添加所有表 (要生成的表)
            .removeTableName("sf_role", "sf_role_permission", "sf_admin", "sf_apilog", "sf_cfg")	// 移除这些内置的表，不必生成代码   
            ;
		
		System.out.println("\n\n\n--------------------------------------------\n\n\n");
		

		// ===================================  开始读取并输出   =================================== 
		GenUtil.doRead();	// 从数据库读取数据 
		GenUtil.doOutMyBatis();	// 输出java代码 （mybatis版本） 
//		GenUtil.doOutMyBatisService();	// 输出java代码 （mybatis版本-带service层 ） 
		GenUtil.doOutAdminHtml();	// 输出 admin后台管理页面  
		GenUtil.doOutApidoc();	// 输出 接口文档页面 
		
		
		System.out.println("\n\n * * * * * * * * * * * *  完结撒花   * * * * * * * * * * * *");
		System.out.println("sa-fast快速开发平台,  当前版本v2.1.0，更新于2020-6-18 ");
		System.out.println("在线文档： http://sa-fast.dev33.cn/");
		System.out.println("开源地址： https://github.com/click33/sa-fast\n\n");
		
	}
	

	

}