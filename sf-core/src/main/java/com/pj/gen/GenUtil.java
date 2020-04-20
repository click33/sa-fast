package com.pj.gen;

import com.pj.gen.cfg.GenCfg;
import com.pj.gen.cfg.GenCfgManager;
import com.pj.gen.model.DbTable;
import com.pj.gen.read.FlyRead;
import com.pj.gen.read.FlyReadMySql;
import com.pj.gen.read.ReadUtil;

/**
 *  操作工具类 
 * @author kong
 *
 */
public class GenUtil {

	
	
	public static FlyRead flyRead = new FlyReadMySql();			// 默认的读取实现类    
	
	
	// 返回读取好的表信息  
	public static GenCfg getCodeCfgByReadOk(){
		initTables();
		flyRead.setCodeCfg(GenCfgManager.cfg).readInfo();
		return GenCfgManager.cfg;
	}
	
	
	// 开始读取 
	public static void doRead() {
		initTables();
		flyRead.setCodeCfg(GenCfgManager.cfg);
		flyRead.readInfo();
	}
	
	// 开始输出为MyBatis版 
	public static void doOutMyBatis() {
		
		// 模块
		for (DbTable t : GenCfgManager.cfg.tableList) {
			
			// model
			String modelPath = t.getServerIoPath() + t.getModelName() + ".java";			// 路径
			String modelContent = FreeMarkerUtil.getResult("mybatis/Model.ftl", "t", t);		// 内容 
			SUtil.outFile(modelPath, modelContent);
			System.out.println(t.getModelName() + " 写入成功：\t\t\t" + modelPath);
			
			// Mapper.java 
			String mapperJavaPath = t.getServerIoPath() + t.getMkNameBig() + "Mapper.java";	// 路径
			String mapperJavaContent = FreeMarkerUtil.getResult("mybatis/MapperJava.ftl", "t", t);	// 内容 
			SUtil.outFile(mapperJavaPath, mapperJavaContent);
			System.out.println(t.getModelName() + "Mapper.java 写入成功：\t\t" + mapperJavaPath);

			// Mapper.xml 
			String mapperXmlPath = t.getServerIoPath() + t.getMkNameBig() + "Mapper.xml";	// 路径
			String mapperXmlContent = FreeMarkerUtil.getResult("mybatis/MapperXml2.ftl", "t", t);	// 内容 
			SUtil.outFile(mapperXmlPath, mapperXmlContent);
			System.out.println(t.getModelName() + "Mapper.xml 写入成功：\t\t" + mapperXmlPath);

			// Controller
			String controllerPath = t.getServerIoPath() + t.getMkNameBig() + "Controller.java";	// 路径 
			String controllerContent = FreeMarkerUtil.getResult("mybatis/Controller.ftl", "t", t);		// 内容 
			SUtil.outFile(controllerPath, controllerContent);
			System.out.println(t.getModelName() + "Controller 写入成功：\t\t" + controllerPath);
			
			// util 
			String utilPath = t.getServerIoPath() + t.getMkNameBig() + "Util.java";	// 路径 
			String utilContent = FreeMarkerUtil.getResult("mybatis/Util2.ftl", "t", t);		// 内容 
			SUtil.outFile(utilPath, utilContent);
			System.out.println(t.getModelName() + "Util 写入成功：\t\t" + utilPath);
			
			// 多打印一行，模块之间有个间隔 
			System.out.println();	
		}
		
		// FC.java 依赖清单 
		String FCPath = GenCfgManager.cfg.getServerIoPath() + "FC.java";						// 路径  
		String FContent = FreeMarkerUtil.getResult("mybatis/FC2.ftl", "abc", 123);		// 内容 
		SUtil.outFile(FCPath, FContent);
		System.out.println("FC.java 依赖清单写入成功：\t\t" + FCPath);
		System.out.println("\n");
	}
	
	// 开始生成admin后台管理
	public static void doOutAdminHtml() {
		// 模块
		for (DbTable t : GenCfgManager.cfg.tableList) {
			// 查 
			String xxxListPath = t.getAdminIoPath() + t.getKebabName() + "-list.html";			// 路径 
			String xxxListContent = FreeMarkerUtil.getResult("admin/xxx-list.ftl", "t", t);			// 内容 
			SUtil.outFile(xxxListPath, xxxListContent);
			System.out.println(t.getModelName() + "-list.html 写入成功：\t\t\t" + xxxListPath);

			// 增 
			String xxxAddPath = t.getAdminIoPath() + t.getKebabName() + "-add.html";			// 路径 
			String xxxAddContent = FreeMarkerUtil.getResult("admin/xxx-add.ftl", "t", t);			// 内容 
			SUtil.outFile(xxxAddPath, xxxAddContent);
			System.out.println(t.getModelName() + "-add.html 写入成功：\t\t\t" + xxxAddPath);
			
			// 详情 
			String xxxInfoPath = t.getAdminIoPath() + t.getKebabName() + "-info.html";			// 路径 
			String xxxInfoContent = FreeMarkerUtil.getResult("admin/xxx-info.ftl", "t", t);			// 内容 
			SUtil.outFile(xxxInfoPath, xxxInfoContent);
			System.out.println(t.getModelName() + "-info.html 写入成功：\t\t\t" + xxxInfoPath);
			
			// 多打印一行，模块之间有个间隔 
			System.out.println();	
		}
		
		// menu-list.js 菜单列表 
		String menuListPath = GenCfgManager.cfg.getAdminPath() + "sa-resources\\menu-list.js";						// 路径  
		String menuListContent = FreeMarkerUtil.getResult("admin/menu-list.ftl", "abc", 123);		// 内容 
		SUtil.outFile(menuListPath, menuListContent);
		System.out.println("menu-list.js 菜单列表, 写入成功：\t\t" + menuListPath);
		
	}
	
	
	
	
	
	// init 相关依赖  
	private static void initTables() {
		// 初始化数据表集合
		if(GenCfgManager.cfg.tableNameList == null || GenCfgManager.cfg.tableNameList.size() == 0){
			for (String tName : ReadUtil.getTableList(GenCfgManager.cfg.sqlFly.getConnection())) {
				GenCfgManager.cfg.tableNameList.add(tName);
			}
		}
	}

	
	
}
