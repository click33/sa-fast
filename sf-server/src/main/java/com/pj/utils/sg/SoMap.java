package com.pj.utils.sg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Map< String, Object> 是最常用的一种Map类型，但是它写着麻烦 
 * <p>所以特封装此类，继承Map，进行一些扩展，可以让Map更灵活使用 
 * <p>最新：2020-5-26 新增集合参数获取 
 * @author kong
 */
public class SoMap extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = 1L;
	
	public SoMap() {
		put("this", this);	// 自己put自己：方便mybatis的操作 
	}

	
	
	// ============================= 常见类型转换的封装，方便调用 =============================
	
	/** 转为String并返回 */
	public String getString(String key) {
		Object value = get(key);
		if(value == null) {
			return null;
		}
		return String.valueOf(value);
	}
	
	/** 如果为空，则返回默认值 */
	public String getString(String key, String defaultValue) {
		String value = getString(key);
		if(value == null) {
			return defaultValue;
		}
		return value;
	}
	
	/** 转为int并返回 */
	public int getInt(String key) {
		String value = getString(key);
		if(value == null) {
			return 0;
		}
		return Integer.valueOf(value);
	}
	public int getInt(String key, int defaultValue) {
		String value = getString(key);
		if(value == null) {
			return defaultValue;
		}
		return Integer.valueOf(value);
	}

	/** 转为long并返回 */
	public long getLong(String key) {
		String value = getString(key);
		if(value == null) {
			return 0;
		}
		return Long.valueOf(value);
	}
	
	/** 转为double并返回 */
	public double getDouble(String key) {
		String value = getString(key);
		if(value == null) {
			return 0.0;
		}
		return Double.valueOf(value);
	}

	/** 转为boolean并返回 */
	public boolean getBoolean(String key) {
		String value = getString(key);
		if(value == null) {
			return false;
		}
		return Boolean.valueOf(value);
	}
	
	/** 转为Date并返回，根据格式： yyyy-MM-dd */
	public Date getDateBy__yyyyMMdd(String key) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(getString(key));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** 转为Date并返回，根据格式： yyyy-MM-dd HH:mm:ss */
	public Date getDateBy__yyyyMMdd_HHmmss(String key) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getString(key));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/** 获取集合 */
	@SuppressWarnings("unchecked")
	public List<Object> getList(String key) {
		Object value = get(key);
		List<Object> list = null;
		if(value == null) {
			list = new ArrayList<Object>();
		}
		else if(value instanceof List) {
			list = (List<Object>)value;
		} else {
			list = new ArrayList<Object>();
			list.add(value);
		}
		return list;
	}

	/** 获取集合(指定类型) */
	@SuppressWarnings("unchecked")
	public <T> List<T> getList(String key, Class<T> cs) {
		List<Object> list = getList(key);
		List<T> list2 = new ArrayList<T>();
		for (Object obj : list) {
			String obj2 = String.valueOf(obj);
			Object obj3 = null;
			if (cs.equals(String.class)) {
				obj3 = obj2;
			} else if (cs.equals(int.class) || cs.equals(Integer.class)) {
				obj3 = Integer.valueOf(obj2);
			} else if (cs.equals(long.class) || cs.equals(Long.class)) {
				obj3 = Long.valueOf(obj2);
			} else if (cs.equals(short.class) || cs.equals(Short.class)) {
				obj3 = Short.valueOf(obj2);
			} else if (cs.equals(byte.class) || cs.equals(Byte.class)) {
				obj3 = Byte.valueOf(obj2);
			} else if (cs.equals(float.class) || cs.equals(Float.class)) {
				obj3 = Float.valueOf(obj2);
			} else if (cs.equals(double.class) || cs.equals(Double.class)) {
				obj3 = Double.valueOf(obj2);
			} else if (cs.equals(boolean.class) || cs.equals(Boolean.class)) {
				obj3 = Boolean.valueOf(obj2);
			} else {
				obj3 = (T)obj;
			}
			list2.add((T)obj3);
		}
		return list2;
	}
	
	
	// ============================= 辅助方法 =============================


	/** 
	 * 指定key是否为以下其一：null、""、0、"0"  
	 */
	public boolean isNull(String key) {
		String value = getString(key);
		return (value == null || value.equals("") || value.equals("0"));
	}
	/** 与isNull()相反 */
	public boolean isNotNull(String key) {
		return !isNull(key);
	}

	/** 验证指定key不为空，为空则抛出异常 */
	public SoMap checkNull(String ...keys) {
		for (String key : keys) {
			if(this.isNull(key)) {
				throw new RuntimeException("参数" + key + "不能为空");
			}
		}
		return this;
	}
	
	/**
	 * 给指定key添加一个默认值（只有在这个key原来无值的情况先才会put进去）
	 */
	public void putDefaultValue(String key, Object defaultValue) {
		if(isNull(key)) {
			put(key, defaultValue);
		}
	}
	
	/** put一个值，连缀风格 */
	public SoMap set(String key, Object value) {
		put(key, value);
		return this;
	}
	
	/** del一个值，连缀风格 */
	public SoMap del(String key) {
		remove(key);
		return this;
	}
	
	/** 构建一个SoMap并返回 */
	public static SoMap getSoMap() {
		return new SoMap();
	}
	/** 构建一个SoMap并返回 */
	public static SoMap getSoMap(String key, Object value) {
		return new SoMap().set(key, value);
	}
	
	
	
	// ============================= 常见key （以下key经常用，所以封装以下，方便写代码） =============================
	
	// get 当前页 
	public int get_pageNo() {
		int pageNo = getInt("pageNo", 1);
		if(pageNo <= 0) {
			pageNo = 1;
		}
		return pageNo;
	}
	// get 页大小
	public int get_pageSize() {
		int pageSize = getInt("pageSize", 10);
		if(pageSize <= 0 || pageSize > 1000) {
			pageSize = 10;
		}
		return pageSize;
	}
	
	// get 排序方式 
	public int get_sortType() {
		return getInt("sort_type");
	}
	
	
	
	
	
	// ============================= 分页相关(封装mybatis的page-help插件 ) =============================
	
	// 分页插件 
	private com.github.pagehelper.Page<?> pagePlug;
	/** 分页插件 - 开始分页 */
	public SoMap startPage() {
		this.pagePlug= com.github.pagehelper.PageHelper.startPage(get_pageNo(), get_pageSize());
		return this;
	}
	/** 分页插件 - 结束分页, 返回总条数 */
	public long endPage() {
		return pagePlug.getTotal();
	}
	
	

	
	
}
