package com.pj.gen.model;

import com.pj.gen.SUtil;

/**
 * 一个列
 */
public class DbColumn {

	private String columnName;			// 列名字
	private String columnComment;		// 字段注释
	private String columnType;			// 数据库类型 
	// private String fieldName;			// 对应的java名字   【只读字段】
	// private String getset;			// 对应的getset形式   【只读字段】
	private String fieldType;			// 对应的java类型

//	private String defaultValue;			// 数据类型对应的java默认值 
	
	public DbColumn() {
	}
	public DbColumn(String columnName, String columnComment, String columnType, String fieldType) {
		super();
		this.columnName = columnName;
		this.columnComment = columnComment;
		this.columnType = columnType;
		this.fieldType = fieldType;
	}

	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnComment() {
		return columnComment;
	}public String getColumnComment2() {	// 去空格版 
		if(columnComment == null) {
			return "";
		}
		return columnComment.replaceAll(" ", "");
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getFieldName() {
		return columnName;
	}
//	public void setFieldName(String fieldName) {
//		this.fieldName = fieldName;
//	}
	public String getGetset() {
		return SUtil.getGetSet(getFieldName()) ;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	
	// 返回默认值 
	public String getDefaultValue() {
		String str = "\"\"";
		if("long".equals(fieldType) || "int".equals(fieldType) ) {
			str = "0";
		}
		return str;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Column [name=" + columnName + ", comment=" + columnComment + ", dbType=" + columnType + ", javaType=" + fieldType + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
