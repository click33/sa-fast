package com.pj.utils.sg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * SoMap工具类 
 * <p>最新：2020-5-26 新增集合参数获取 
 * @author kong
 *
 */
public class SoMapUtil {

	// 线程缓存
	// private static ThreadLocal<SoMap> TLSM = new ThreadLocal<>();
	// 使用 ThreadLocal 有一个bug  那就是如果线程是从线程池里拿到的  就会造成 线程重复 
	// 这时候ThreadLocal 里get到的值很可能就是上一次线程set的值，这样就会出大大的bug  
	// 所以对此类升级一下，不再使用 ThreadLocal ，
	// 既然一次请求一次request，那将SoMap直接存储在request对象之上，岂不美哉？
	

	/**
	 * 返回当前request请求的SoMap, SoMap有什么用？ SoMap封装了当前request请求的所有参数 
	 * @return
	 */
	public static SoMap getSoMap() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();// 大善人SpringMVC提供的封装 
		if(servletRequestAttributes == null) {
			throw new RuntimeException("当前环境非JavaWeb");
		}
		HttpServletRequest request = servletRequestAttributes.getRequest(); // 当前request
		if (request.getAttribute("currentSoMap") == null || request.getAttribute("currentSoMap") instanceof SoMap == false ) {
			initSoMap(request);
		}
		return (SoMap)request.getAttribute("currentSoMap");
	}

	// 初始化当前request的 SoMap
	private static void initSoMap(HttpServletRequest request) {
		SoMap soMap = new SoMap();
		Map<String, String[]> parameterMap = request.getParameterMap();	// 获取所有参数 
		for (String key : parameterMap.keySet()) {
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

	
	
	
}
