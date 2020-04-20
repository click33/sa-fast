package com.pj.current.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hutool.crypto.SecureUtil;

/**
 * 有关当前项目的一些工具方法 
 * @author kong
 *
 */
@Component
public class SystemObject {

	// ===================================== 一些二次封装的方法 ===================================================
	
	/** 返回md5加密后的密码，根据当前配置的salt
	 *   格式为： md5(salt + userid + password) 
	 */ 
	public static String getPasswordMD5(long user_id, String password) {
		return SecureUtil.md5(config.getMd5_salt() + user_id + password).toUpperCase();
	}
	
	
	// ===================================== yml自定义配置信息 ===================================================
	
	public static MyConfig config;
	@Autowired
	public void setMyConfig(MyConfig config) {
		SystemObject.config = config;
	}
		
	
}
