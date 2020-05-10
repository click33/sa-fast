package com.pj.gen.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	
	private String foType = "text";		// 表单类型 
	private Map<String, String> jvList;	// 如果是枚举类型，则代表所有枚举类型 
	
	
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
	}
	public String getColumnComment2() {	// 去空格版 
		if(columnComment == null) {
			return "";
		}
		return columnComment.replaceAll(" ", "");
	}
	public String getColumnComment3() {	// 去空格 和 去括号 
		String columnComment = this.columnComment;
		// 去空格
		if(columnComment == null) {
			return "";
		}
		columnComment = columnComment.replaceAll(" ", "");
		// 去括号
		// 获取枚举信息
		int start_index = this.columnComment.lastIndexOf("(");
		int end_index = this.columnComment.lastIndexOf(")");
		if(start_index > -1 && end_index > -1) {
			String k_str = this.columnComment.substring(start_index, end_index + 1);
			columnComment = columnComment.replace(k_str, "");
		}
		return columnComment;
	}
	
	// 写入字段注释 
	public void setColumnComment(String columnComment) {

		try {
			// 先写入一下
			this.columnComment = columnComment;
			this.foType = "text";	// 默认text类型 
			
			// 如果包含--notp， 代表不要解析
			if(columnComment.indexOf("--notp") > -1) {
				columnComment = columnComment.replace("--notp", "");
				this.columnComment = columnComment;
				return;
			}
			
			// 判断是否包含[] , 代表声明表单类型 
			if(columnComment.indexOf("[") > -1 && columnComment.indexOf("]") > -1) {
			} else {
				return;
			}
			
			// 获取表单类型 
			String regex = "\\[(.*?)]";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(columnComment);
			matcher.find();
			this.foType = matcher.group(1);
			if(this.foType == null) {
				this.foType = "text";
			}
			// 去除表单声明信息 
			this.columnComment = this.columnComment.replace("[" + this.foType + "]", "");
			
			// 开始判断, 不同类型不同展现 
			
			if(foType.equals("text") || foType.equals("input")) {	// 普通input 
				this.foType = "teext";
			}
			else if(foType.equals("num")) {	// 数字input 
			}
			else if(foType.equals("textarea") || foType.equals("d")) {	// 多行文本域 
				this.foType = "textarea";
			}
			else if(foType.equals("img")) {	// 单图片
				this.foType = "img";
			}
			else if(foType.equals("img_list") || foType.equals("imgList")) {	// 多图片 
				this.foType = "img_list";
			}
			else if(foType.equals("date") || foType.equals("datetime")) {	// 日期 
				this.foType = "date";
				this.fieldType = "Date";
			}
			else if(foType.equals("richtext") || foType.equals("f")) {	// 富文本
				this.foType = "richtext";
			}
			else if(foType.equals("enum") || foType.equals("j")) {	// 枚举 
				this.foType = "enum";
				// 获取枚举信息
				int start_index = this.columnComment.lastIndexOf("(");
				int end_index = this.columnComment.lastIndexOf(")");
				if(start_index == -1 || end_index == -1) {
					return;
				}
				// 切割字符串 , 来获取 
				this.jvList = new LinkedHashMap<String, String>();
				String e_str = this.columnComment.substring(start_index + 1, end_index);
				String[] eArr = e_str.split(",");
				if(eArr != null && eArr.length != 0) {
					for (String e : eArr) {
						try {
							String key = e.split("=")[0].trim();
							String value = e.split("=")[1].trim();
							this.jvList.put(key, value);
						} catch (Exception e2) {
							System.out.println(e2.getMessage());
						}
					}
				}
//				System.out.println(e_str);
			}
			else if(foType.equals("no")) {	// no 添加修改时 不展示 
				this.foType = "no";
			}
			else {	// 什么都不是，还是默认吧 
				this.foType = "text";
			}
			
			
			
			// 后置工作
		} catch (Exception e) {
			System.out.println("字段(" + this.columnName + ")注释解析异常：" + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		DbColumn c = new DbColumn();
		c.setColumnComment("姓名[j](1=是, 2=否, 3=不是)");
		System.out.println(c);
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


	/**
	 * @return foType
	 */
	public String getFoType() {
		return foType;
	}
	/**
	 * @param foType 要设置的 foType
	 */
	public void setFoType(String foType) {
		this.foType = foType;
	}
	/**
	 * @return jvList
	 */
	public Map<String, String> getJvList() {
		return jvList;
	}
	/**
	 * @param jvList 要设置的 jvList
	 */
	public void setJvList(Map<String, String> jvList) {
		this.jvList = jvList;
	}
	/* （非 Javadoc）
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DbColumn [columnName=" + columnName + ", columnComment=" + columnComment + ", columnType=" + columnType
				+ ", fieldType=" + fieldType + ", foType=" + foType + ", jvList=" + jvList + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
