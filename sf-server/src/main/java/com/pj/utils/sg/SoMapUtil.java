package com.pj.utils.sg;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * SoMap工具类 
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
		Enumeration<String> paramNames = request.getParameterNames();// 获得K列表
		while (paramNames.hasMoreElements()) {
			try {
				String key = paramNames.nextElement(); // 获得k
				String value = request.getParameter(key); // 获得v
				soMap.put(key, value);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		request.setAttribute("currentSoMap", soMap);
	}

	
	
	
}
