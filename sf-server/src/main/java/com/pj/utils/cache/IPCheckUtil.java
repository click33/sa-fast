package com.pj.utils.cache;

import com.pj.utils.sg.AjaxError;
import com.pj.utils.sg.WebNbUtil;

import cn.dev33.satoken.util.SpringMVCUtil;

/**
 * IP限流util
 * @author kong
 *
 */
public class IPCheckUtil {

	// 持久化的key 
	static String key = "sys_ck_ip:";
	
	
	// 指定ip的访问点设置为
	public static void setNow(String ip){
		RedisUtil.set(key + ip, System.currentTimeMillis() + "");
	}
	public static void setNow(){
		setNow(getMyIp());
	}
	
	// 返回指定ip上一次接入是几秒前
	public static long getT_S(String ip){
		String str = RedisUtil.get(key + ip);
		if(str == null) {
			str = "0";
		}
		return (System.currentTimeMillis() - Long.parseLong(str)) / 1000;
	}
	public static long getT_S(){
		return getT_S(getMyIp());
	}
	
	// 检查指定ip是否属于频繁访问 ，如果是，则抛出异常 , 参数：ip、频繁秒数 
	public static void checkIP(String ip, int s) {
		if(IPCheckUtil.getT_S(ip) < s){
			throw AjaxError.get("操作频繁，请稍后尝试");
		}
	}
	public static void checkIP(int s) {
		checkIP(getMyIp(), s);
	}
	
	
	
	// 返回我的ip
	private static String getMyIp() {
		String ip = WebNbUtil.getIP(SpringMVCUtil.getRequest());
		return ip;
	}
	
}