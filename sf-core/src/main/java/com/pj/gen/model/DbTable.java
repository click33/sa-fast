package com.pj.gen.model;

import java.util.ArrayList;
import java.util.List;

import com.pj.gen.SUtil;
import com.pj.gen.cfg.GenCfgManager;

/**
 * 一个表
 * @author kongyongshun
 *
 */
public class DbTable {

	private String tableName;					// 表名字  
	private String tableComment;				// 表注释  
	// private String mkName;					// 模块名
	// private String modelName;				// 实体类名  -- 【只读字段】
	// private String varName;					// 变量名，返回此表起变量时的命名 -- 【只读字段】
	// private String packageModules;			// 模块报名 -- 【只读字段】
	private DbColumn primaryKey;				// 主键列  
	// private String packagePath;				// 包名 格式预览：package ${packagePath};   -- 【只读字段】
	// private boolean is_import_util;					// 判断是否需要导入 util包 （根据所有字段里是否有Date数据类型）;   -- 【只读字段】
	// private String kebabName;					// 转换成kebab-case形式     -- 【只读字段】
	
	private List<DbColumn> columnList;			// 列集合 

	
	/**
	 * 范回列的String形式
	 * @return
	 */
	public List<String> getColumnListString() {
		List<String> sList = new ArrayList<>();
		for (DbColumn column : columnList) {
			sList.add(column.getColumnName());
		}
		return sList;
	}
	
	

	// 表名字 
	public void setTableName(String name) {
		this.tableName = name;
	}
	// 返回表名 
	public String getTableName() {
		return tableName;
	}
	// 返回表名，小写形式 
	public String getTableNameSmall() {
		return tableName.toLowerCase();
	}
	
	// 表注释 
	public String getTableComment() {
		return tableComment;
	}
	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	
	// 表主键 
	public DbColumn getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(DbColumn primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	
	// 返回模块名 (对表名二次处理一下)
	public String getMkName(){
		return this.getTableName();
	}
	// 返回模块名 驼峰大写形式 
	public String getMkNameBig(){
		return SUtil.wordEachBig(getMkName());
	}
	
	// 返回实体类名(模块名下划线转大驼峰)
	public String getModelName(){
//		return getMkNameBig() + "Model";
		return getMkNameBig();
	}
	
	// 返回模块变量名  (实体类名首字母小写)
	public String getVarName() {
		// return getClassName().substring(0, 1).toLowerCase();
		return SUtil.wordFirstSmall(getMkNameBig());
	}
	// 返回变量名 - 简写模式  (只要实体类名首字母)
	public String getVarNameSimple() {
		return getModelName().substring(0, 1).toLowerCase();
	}
	// 返回模块实体类变量名
	public String getModelVarName() {
		return SUtil.wordFirstSmall(getModelName());
	}
	
	
	// 专属包名(模块名所有字母小写)
	public String getPackageModules() {
		// return getTableNameSmall();
		return getMkName().toLowerCase();
	}
	
	//  完全限定名包名 
	public String getPackagePath() {
		return GenCfgManager.cfg.packagePath + "." + getPackageModules();
	}

	// 返回表名转 kebab-case形式 
	public String getKebabName() {
		// return SUtil.xia_2_zhong(getTableName());
		return SUtil.xia_2_zhong(getPackageModules());
	}
	
	
	
	
	// 是否需要导入Date包 
	public boolean getIs_import_util() {
		boolean is_flag = false;
		for (DbColumn dbColumn : getColumnList()) {
			if(dbColumn.getFieldType().equals("Date")) {
				is_flag = true;
			}
		}
		return is_flag;
	}
	
	
	// 列集合 
	public List<DbColumn> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<DbColumn> columnList) {
		this.columnList = columnList;
	}
	
	
	// 返回Dao名
	public String getDaoName() {
		return getModelName() + "Dao"; 
	}
	// 返回Service名
	public String getServiceName() {
		return getModelName() + "Service"; 
	}

	
	// 返回服务端应该写入哪个文件夹
	public String getServerIoPath() {
		return GenCfgManager.cfg.getServerIoPath() + this.getPackageModules() + "\\";
	}
	// 返回后台管理应该写入哪个文件夹
	public String getAdminIoPath() {
		return GenCfgManager.cfg.getAdminIoPath() + this.getKebabName() + "\\";
	}
	// 返回接口文档应该写入哪个文件夹
	public String getApidocIoPath() {
		return GenCfgManager.cfg.getApidocIoPath() + "\\";
	}
	
	
	//	// 返回Dao名 变量形式 
//	public String getDaoName() {
//		return getClassName() + "Dao"; 
//	}
//	// 返回Service名 变量形式 
//	public String getServiceName() {
//		return getClassName() + "Service"; 
//	}
//	
	
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Table [name=" + tableName + ", comment=" + tableComment + ", columnList=" + columnList + "]";
	}



	


	



	



	



	

	
	
}
