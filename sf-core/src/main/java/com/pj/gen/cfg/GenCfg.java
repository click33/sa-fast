package com.pj.gen.cfg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fly.jdbc.SqlFly;
import com.fly.jdbc.SqlFlyFactory;
import com.pj.gen.model.DbTable;
import com.pj.gen.read.ReadUtil;

/**
 * 总配置，
 * 
 * @author kong 一路向下
 */
public class GenCfg {

	public SqlFly sqlFly = SqlFlyFactory.getSqlFly(); // 默认SqlFly对象

	// 服务端相关 
	public String projectPath = ""; // 项目路径
	public String codePath = "src/main/java/"; // 代码路径
	public String packagePath = ""; 		// 总包名 
	public String docPath = "doc/"; 		// 文档生成路径 
	public String author = ""; 				// 生成的代码作者名字 
	// public String package_somap;			// SoMap的包完全限定名 
	// public String package_ajaxjson; // AjaxJson类的地址，在代码生成时会用到
	public String package_utils = ""; // util包导包地址 
	public int fieldType = 1; 			// 对数据库表字段的处理方式（1=转小写，2=转大写，0=不变）
	public Boolean is_lomock = false; 	// 是否使用 lomock
	public Boolean is_three = true; 	// 是否标准三层模式
	
	// 后台相关 
	public String adminPath = "";	// 后台管理项目地址
	public String adminCodePath = "";	// 后台管理代码存放目录 

	// 接口文档相关
	public String apidocPath = "";	// 接口文档项目地址
	public String apidocCodePath = "";	// 接口文档存放目录 
	
	
	

	public List<String> tableNameList = new ArrayList<>(); 		// 要检索的表名字集合 
	public List<DbTable> tableList = new ArrayList<>(); 		// 检索出的表集合 

	// 返会服务端IO的主目录
	public String getServerIoPath() {
		String path = projectPath + "\\" + 
				codePath + "\\" + 
				packagePath.replace(".", "\\");
		return new File(path).getAbsolutePath() + "\\";
	}
	// 返会admin后台管理的IO的主目录
	public String getAdminIoPath() {
		String path = adminPath + "\\" + 
				adminCodePath + "\\";
		return new File(path).getAbsolutePath() + "\\";
	}
	// 返会apidoc后台管理的IO的主目录
	public String getApidocIoPath() {
		String path = apidocPath + "\\" + 
				apidocCodePath + "\\";
		return new File(path).getAbsolutePath() + "\\";
	}
		
	
	// 返回文档写入地址
	public String getDocIOPath() {
		String path = new File(projectPath).getAbsolutePath() + "\\" + docPath + "\\";
		return path;
	}
	

	// 追加所有表名字 
	public GenCfg addTableAll() {
		for (String tName : ReadUtil.getTableList(GenCfgManager.cfg.sqlFly.getConnection())) {
			GenCfgManager.cfg.tableNameList.add(tName);
		}
		return this;
	}
	
	// 追加一个表名字
	public GenCfg addTableName(String... tableNames) {
		for (String tableName : tableNames) {
			if (!tableNameList.contains(tableName))
				tableNameList.add(tableName);
		}
		return this;
	}

	// 移除一个表名字
	public GenCfg removeTableName(String... tableNames) {
		for (String tableName : tableNames) {
			tableNameList.remove(tableName);
		}
		return this;
	}
	
	
	
	// 一坨坨 get set
	public String getProjectPath() {
		return projectPath;
	}

	public GenCfg setProjectPath(String projectPath) {
		this.projectPath = projectPath;
		return this;
	}

	public String getPackagePath() {
		return packagePath;
	}

	public GenCfg setPackagePath(String packagePath) {
		this.packagePath = packagePath;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public GenCfg setAuthor(String author) {
		this.author = author;
		return this;
	}

	public int getFieldType() {
		return fieldType;
	}

	public GenCfg setFieldType(int fieldType) {
		this.fieldType = fieldType;
		return this;
	}

	public Boolean getIs_lomock() {
		return is_lomock;
	}

	public GenCfg setIs_lomock(Boolean is_lomock) {
		this.is_lomock = is_lomock;
		return this;
	}

	public Boolean getIs_three() {
		return is_three;
	}

	public GenCfg setIs_three(Boolean is_three) {
		this.is_three = is_three;
		return this;
	}

	public String getCodePath() {
		return codePath;
	}

	public GenCfg setCodePath(String codePath) {
		this.codePath = codePath;
		return this;
	}

	public String getDocPath() {
		return docPath;
	}

	public GenCfg setDocPath(String docPath) {
		this.docPath = docPath;
		return this;
	}

	
	public List<DbTable> getTableList() {
		return tableList;
	}

	public void setTableList(List<DbTable> tableList) {
		this.tableList = tableList;
	}

	public String getAdminPath() {
		return adminPath;
	}

	public GenCfg setAdminPath(String adminPath) {
		this.adminPath = adminPath;
		return this;
	}

	public String getAdminCodePath() {
		return adminCodePath;
	}

	public GenCfg setAdminCodePath(String adminCodePath) {
		this.adminCodePath = adminCodePath;
		return this;
	}
	public String getPackage_utils() {
		return package_utils;
	}
	public GenCfg setPackage_utils(String package_utils) {
		this.package_utils = package_utils;
		return this;
	}
	/**
	 * @return apidocPath
	 */
	public String getApidocPath() {
		return apidocPath;
	}
	/**
	 * @param apidocPath 要设置的 apidocPath
	 */
	public GenCfg setApidocPath(String apidocPath) {
		this.apidocPath = apidocPath;
		return this;
	}
	/**
	 * @return apidocCodePath
	 */
	public String getApidocCodePath() {
		return apidocCodePath;
	}
	/**
	 * @param apidocCodePath 要设置的 apidocCodePath
	 */
	public GenCfg setApidocCodePath(String apidocCodePath) {
		this.apidocCodePath = apidocCodePath;
		return this;
	}

	

}
