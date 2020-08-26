package com.pj.utils.sg;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Map< String, Object> 是最常用的一种Map类型，但是它写着麻烦 
 * <p>所以特封装此类，继承Map，进行一些扩展，可以让Map更灵活使用 
 * <p>最新：2020-8-18 增加了 toJSONString( )方法 
 * @author kong
 */
public class SoMap extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public SoMap() {
		setThis();	// 自己put自己：方便mybatis的操作
	}
	

	/** 以下元素会在isNull函数中被判定为Null， */
	public static final Object[] NULL_ELEMENT_ARRAY = {null, "", "0", "0.0"};
	public static final List<Object> NULL_ELEMENT_LIST;

	
	static {
		NULL_ELEMENT_LIST = Arrays.asList(NULL_ELEMENT_ARRAY);
	}

	// ============================= 读值 =============================

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
		if(value == null || value.equals("")) {
			return 0;
		}
		return Integer.valueOf(value);
	}
	/** 转为int并返回，同时指定默认值 */
	public int getInt(String key, int defaultValue) {
		String value = getString(key);
		if(value == null || value.equals("")) {
			return defaultValue;
		}
		return Integer.valueOf(value);
	}

	/** 转为long并返回 */
	public long getLong(String key) {
		String value = getString(key);
		if(value == null || value.equals("")) {
			return 0;
		}
		return Long.valueOf(value);
	}

	/** 转为double并返回 */
	public double getDouble(String key) {
		String value = getString(key);
		if(value == null || value.equals("")) {
			return 0.0;
		}
		return Double.valueOf(value);
	}

	/** 转为boolean并返回 */
	public boolean getBoolean(String key) {
		String value = getString(key);
		if(value == null || value.equals("")) {
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
		if(value == null || value.equals("")) {
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
	public <T> List<T> getList(String key, Class<T> cs) {
		List<Object> list = getList(key);
		List<T> list2 = new ArrayList<T>();
		for (Object obj : list) {
			T objC = getValueByClass(obj, cs);
			list2.add(objC);
		}
		return list2;
	}

	/** 获取集合(逗号分隔式)，(指定类型) */
	public <T> List<T> getListByComma(String key, Class<T> cs) {
		String listStr = getString(key);
		if(listStr == null || listStr.equals("")) {
			return new ArrayList<>();
		}
		// 开始转化
		String [] arr = listStr.split(",");
		List<T> list = new ArrayList<T>();
		for (String str : arr) {
			T objC = getValueByClass(str, cs);
			list.add(objC);
		}
		return list;
	}


	/**
	 * 将指定值转化为指定类型并返回
	 * @param obj
	 * @param cs
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getValueByClass(Object obj, Class<T> cs) {
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
		return (T)obj3;
	}

	
	// ============================= 写值 =============================

	/**
	 * 给指定key添加一个默认值（只有在这个key原来无值的情况先才会put进去）
	 */
	public void setDefaultValue(String key, Object defaultValue) {
		if(isNull(key)) {
			put(key, defaultValue);
		}
	}

	/** put一个值，连缀风格 */
	public SoMap set(String key, Object value) {
		put(key, value);
		return this;
	}

	/** 将一个Map塞进SoMap */
	public SoMap setMap(Map<String, ?> map) {
		if(map != null) {
			for (String key : map.keySet()) {
				this.set(key, map.get(key));
			}
		}
		return this;
	}

	/** 将一个对象解析塞进SoMap */
	public SoMap setModel(Object model) {
		if(model == null) {
			return this;
		}
		Field[] fields = model.getClass().getDeclaredFields();
	    for (Field field : fields) {
	        try{
	            field.setAccessible(true);
	            this.set(field.getName(), field.get(field));
	        }catch (Exception e){
	        	throw new RuntimeException(e);
	        }
	    }
		return this;
	}

	/** 添加自身的引用 */
	public SoMap setThis() {
		this.set("this", this);
		return this;
	}

	
	// ============================= 删值 =============================

	/** del一个值，连缀风格 */
	public SoMap delete(String key) {
		remove(key);
		return this;
	}

	/** 删除自身的引用 */
	public SoMap deleteThis() {
		this.delete("this");
		return this;
	}

	/** 清理所有value为null的字段 */
	public SoMap clearNull() {
		Iterator<String> iterator = this.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			if(this.isNull(key)) {
				iterator.remove();
				this.remove(key);
			}

		}
		return this;
	}
	/** 清理指定key */
	public SoMap clearIn(String ...keys) {
		List<String> keys2 = Arrays.asList(keys);
		Iterator<String> iterator = this.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			if(keys2.contains(key) == true) {
				iterator.remove();
				this.remove(key);
			}
		}
		return this;
	}
	/** 清理掉不在列表中的key */
	public SoMap clearNotIn(String ...keys) {
		List<String> keys2 = Arrays.asList(keys);
		Iterator<String> iterator = this.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			if(keys2.contains(key) == false && key.equals("this") == false) {
				iterator.remove();
				this.remove(key);
			}

		}
		return this;
	}

	
	

	// ============================= 快速构建 ============================= 

	/** 构建一个SoMap并返回 */
	public static SoMap getSoMap() {
		return new SoMap();
	}
	/** 构建一个SoMap并返回 */
	public static SoMap getSoMap(String key, Object value) {
		return new SoMap().set(key, value);
	}
	/** 构建一个SoMap并返回 */
	public static SoMap getSoMap(Map<String, ?> map) {
		return new SoMap().setMap(map);
	}

	/**
	 * 转为JSON字符串
	 */
	public String toJSONString() {
		try {
			SoMap so = SoMap.getSoMap(this).deleteThis();
			return new ObjectMapper().writeValueAsString(so);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// ============================= 辅助方法 =============================


	/**
	 * 指定key是否为null，判定标准为 NULL_ELEMENT_ARRAY中的元素 
	 */
	public boolean isNull(String key) {
		return NULL_ELEMENT_LIST.contains(getString(key));
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


	// ============================= web辅助 =============================


	/**
	 * 返回当前request请求的的所有参数 
	 * @return
	 */
	public static SoMap getRequestSoMap() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();// 大善人SpringMVC提供的封装 
		if(servletRequestAttributes == null) {
			throw new RuntimeException("当前环境非JavaWeb");
		}
		HttpServletRequest request = servletRequestAttributes.getRequest(); // 当前request
		if (request.getAttribute("currentSoMap") == null || request.getAttribute("currentSoMap") instanceof SoMap == false ) {
			initRequestSoMap(request);
		}
		return (SoMap)request.getAttribute("currentSoMap");
	}

	/** 初始化当前request的 SoMap */
	private static void initRequestSoMap(HttpServletRequest request) {
		SoMap soMap = new SoMap();
		Map<String, String[]> parameterMap = request.getParameterMap();	// 获取所有参数 
		for (String key : parameterMap.keySet()) {
			if(key.equals("this")) {	// 防止敏感key
				continue;
			}
			try {
				String[] values = parameterMap.get(key); // 获得values 
				if(values.length == 1) {
					soMap.put(key, values[0]);
				} else {
					List<String> list = new ArrayList<String>();
					for (String v : values) {
						list.add(v);
					}
					soMap.put(key, list);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		request.setAttribute("currentSoMap", soMap);
	}



	// ============================= 常见key （以下key经常用，所以封装以下，方便写代码） =============================

	/** get 当前页  */
	public int getKeyPageNo() {
		int pageNo = getInt("pageNo", 1);
		if(pageNo <= 0) {
			pageNo = 1;
		}
		return pageNo;
	}
	/** get 页大小  */
	public int getKeyPageSize() {
		int pageSize = getInt("pageSize", 10);
		if(pageSize <= 0 || pageSize > 1000) {
			pageSize = 10;
		}
		return pageSize;
	}

	/** get 排序方式 */
	public int getKeySortType() {
		return getInt("sortType");
	}





	// ============================= 分页相关(封装mybatis的page-help插件 ) =============================

	/** 分页插件 */
	private com.github.pagehelper.Page<?> pagePlug;
	/** 分页插件 - 开始分页 */
	public SoMap startPage() {
		this.pagePlug= com.github.pagehelper.PageHelper.startPage(getKeyPageNo(), getKeyPageSize());
		return this;
	}
	/** 获取上次分页的记录总数 */
	public long getDataCount() {
		if(pagePlug == null) {
			return -1;
		}
		return pagePlug.getTotal();
	}
	/** 分页插件 - 结束分页, 返回总条数 （该方法已过时，请调用更加符合语义化的getDataCount() ） */
	@Deprecated
	public long endPage() {
		return getDataCount();
	}

	
	
	

	// ============================= 工具方法 =============================
	
	




}
