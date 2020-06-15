package com.pj.gen.model;

import java.util.HashMap;
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
	
	
	// 枚举相关 
	private String foType = "text";		// 表单类型 
	private Map<String, String> jvList = new HashMap<String, String>();	// 如果是枚举类型，则代表所有枚举类型 
	
	// 外键相关
	private String fkPkTableName;	// 如果是个外键，对应的主键表名   
	private String fkPkFieldName;	// 如果是个外键，对应的主键字段名字   
	private String fkPkConcatName;	// 如果是个外键，对应的连表查需要展示的字段名   
	private String fkPkConcatComment;	// 如果是个外键，对应的连表查需要展示的字段名 的字段注释 
	

	private Map<String, String> txMap = new HashMap<String, String>();	// 特性Map 
	
	
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
	
	// 写入字段注释 ========================
	public void setColumnComment(String columnComment) {

		try {
			// 先写入一下
			this.columnComment = columnComment;
			this.foType = "text";	// 默认text类型 
			String tx_str = "";		// 特性列表字符串 
			
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
			
			// 解析特性str   (如果包含空格)
			if(foType.indexOf(" ") > -1) {
				int tx_index = foType.indexOf(" ");
				tx_str = foType.substring(tx_index + 1, foType.length());
				foType = foType.substring(0, tx_index);
				// 特性Map
				this.txMap = SUtil.txStringToMap(tx_str);
				
//				System.out.println("解析：=========为");
//				System.out.println(foType);
//				System.out.println(texing);
			}
			
			// 开始判断, 不同类型不同展现 、
			if(foType.equals("text") || foType.equals("t") || foType.equals("input")) {	// 普通input 
				this.foType = "text";
			}
			else if(foType.equals("num")) {	// 数字input 
			}
			else if(foType.equals("textarea") || foType.equals("d")) {	// 多行文本域 
				this.foType = "textarea";
			}
			else if(foType.equals("img")) {	// 单图片
				this.foType = "img";
			}
			else if(foType.equals("audio")) {	// 单音频
				this.foType = "audio";
			}
			else if(foType.equals("video")) {	// 单视频
				this.foType = "video";
			}
			else if(foType.equals("img_list") || foType.equals("imgList")) {	// 多图片 
				this.foType = "img_list";
			}
			else if(foType.equals("audio_list") || foType.equals("audioList")) {	// 多音频 暂不支持
				this.foType = "audio_list";
			}
			else if(foType.equals("video_list") || foType.equals("videoList")) {	// 多视频 暂不支持
				this.foType = "video_list";
			}
			else if(foType.equals("date") || foType.equals("datetime")) {	// 日期 
				this.foType = "date";
				this.fieldType = "Date";
			}
			else if(foType.equals("date-create")) {	// 日期 - create 创建日期
				this.foType = "date-create";
				this.fieldType = "Date";
			}
			else if(foType.equals("date-update")) {	// 日期 - update 更新日期 
				this.foType = "date-update";
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
			else if(foType.equals("fk-1")) {	// fk-1  外键，下拉列表框 
				this.foType = "fk-1";
				String pkInfo = this.txMap.get("pk");
				try {
					String[] pkArr = pkInfo.split("\\.");
					this.fkPkTableName = pkArr[0];
					this.fkPkFieldName = pkArr[1];
					this.fkPkConcatName = pkArr[2];
					this.fkPkConcatComment = pkArr[3];
				} catch (Exception e) {
					System.err.println("外键解析出错：" + pkInfo+ "," + e.getMessage());
				}
			}
			else if(foType.equals("fk-2")) {	// fk-2  外键，弹出选择页面 
				this.foType = "fk-2";
				String pkInfo = this.txMap.get("pk");
				try {
					String[] pkArr = pkInfo.split("\\.");
					this.fkPkTableName = pkArr[0];
					this.fkPkFieldName = pkArr[1];
					this.fkPkConcatName = pkArr[2];
					this.fkPkConcatComment = pkArr[3];
				} catch (Exception e) {
					System.err.println("外键解析出错：" + pkInfo+ "," + e.getMessage());
				}
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
		c.setColumnComment("所属分类 [fk-1 pk=sys_type.id.name.所属分类]");
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

	
	// 判断其 foType 是否属于其中一种 
	public boolean isFoType(String ... foType) {
		boolean is_flag = false;
		for (String ft : foType) {
			if(this.getFoType().equals(ft)) {
				is_flag = true;
			}
		}
		return is_flag;
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

	
	
	/**
	 * @return txMap
	 */
	public Map<String, String> getTxMap() {
		return txMap;
	}
	/**
	 * @param txMap 要设置的 txMap
	 */
	public void setTxMap(Map<String, String> txMap) {
		this.txMap = txMap;
	}
	
	// 获取一个特性
	public String getTx(String tx_key) {
		String tv = this.txMap.get(tx_key);
		if(tv == null) {
			tv = "";
		}
		return tv;
	}

	// 是否包含一个特性
	public boolean isTx(String tx_key) {
		return this.txMap.get(tx_key) != null;
	}
	
	
	
	
	
	/**
	 * @return fkPkTableName
	 */
	public String getFkPkTableName() {
		return fkPkTableName;
	}
	/**
	 * @param fkPkTableName 要设置的 fkPkTableName
	 */
	public void setFkPkTableName(String fkPkTableName) {
		this.fkPkTableName = fkPkTableName;
	}
	/**
	 * @return fkPkFieldName
	 */
	public String getFkPkFieldName() {
		return fkPkFieldName;
	}
	/**
	 * @param fkPkFieldName 要设置的 fkPkFieldName
	 */
	public void setFkPkFieldName(String fkPkFieldName) {
		this.fkPkFieldName = fkPkFieldName;
	}
	/**
	 * @return fkPkConcatName
	 */
	public String getFkPkConcatName() {
		return fkPkConcatName;
	}
	/**
	 * @param fkPkConcatName 要设置的 fkPkConcatName
	 */
	public void setFkPkConcatName(String fkPkConcatName) {
		this.fkPkConcatName = fkPkConcatName;
	}
	/**
	 * @return fkPkConcatComment
	 */
	public String getFkPkConcatComment() {
		return fkPkConcatComment;
	}
	/**
	 * @param fkPkConcatComment 要设置的 fkPkConcatComment
	 */
	public void setFkPkConcatComment(String fkPkConcatComment) {
		this.fkPkConcatComment = fkPkConcatComment;
	}
	
	
	// 外键对应主键的表名，的模块，的接口名 
	public String getFkPkTableMkName() {
		return SUtil.wordEachBig(fkPkTableName);
	}

	// 外键对应主键的表名，的模块，的kebab-case形式 
	public String getFkPkTableKebabName() {
		String mkName = fkPkTableName;
		String packageModules = mkName.toLowerCase();
		return SUtil.xia_2_zhong(packageModules);
//		return SUtil.wordEachBig(fkPkTableName);
	}
	
	

	
	/* （非 Javadoc）
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DbColumn [columnName=" + columnName + ", columnComment=" + columnComment + ", columnType=" + columnType
				+ ", fieldType=" + fieldType + ", foType=" + foType + ", jvList=" + jvList + ", fkPkTableName="
				+ fkPkTableName + ", fkPkFieldName=" + fkPkFieldName + ", fkPkConcatName=" + fkPkConcatName
				+ ", fkPkConcatComment=" + fkPkConcatComment + ", txMap=" + txMap + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
