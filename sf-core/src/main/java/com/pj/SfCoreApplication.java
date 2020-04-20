package com.pj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fly.spring.SqlFlySetup;
import com.pj.gen.GenUtil;
import com.pj.gen.cfg.GenCfgManager;

@SqlFlySetup
@SpringBootApplication
public class SfCoreApplication {
	
	public static void main(String[] args) {

		SpringApplication.run(SfCoreApplication.class, args); // run-->x

		// 一些全局设置 
		GenCfgManager.cfg
			.setProjectPath("E:\\work\\project-yun\\sa-fast\\sf-server\\")	// 服务端代码 - 项目地址 
	        .setCodePath("src/main/java/")					// 服务端代码 - 存放路径 
	        .setPackagePath( "com.pj.project")				// 服务端代码 - 总包名 
	        .setPackage_utils("com.pj.utils.sg.*")		// util类包地址 
	        .setAuthor("kong")								// 代码作者 
            .setAdminPath("E:\\work\\project-yun\\sa-fast\\sf-admin\\")		// 后台管理端项目地址   
            .setAdminCodePath("sa-html\\")						// 后台管理代码存放目录 
            .addTableAll()		// 添加所有表 (要生成的表)
            .removeTableName("sf_role", "sf_role_permission", "sf_admin", "sf_apilog")	// 移除这些内置的表，不必生成代码   
            ;
		
		System.out.println("\n\n\n--------------------------------------------\n\n\n");
		
		
		// 开始读取并输出 
		GenUtil.doRead();	// 读取数据 
		GenUtil.doOutMyBatis();	// 输出 - mybatis版本 
		GenUtil.doOutAdminHtml();	// 输出 admin后台管理代码 
		System.out.println("\n\n * * * * * * * * * * * *  完结撒花 * * * * * * * * * * * * \n\n");
		
	}
	

	

}