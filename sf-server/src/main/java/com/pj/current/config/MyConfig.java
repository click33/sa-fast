package com.pj.current.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


import lombok.Data;

/**
 * 我的自定义配置 
 */
@Data
@Component
@ConfigurationProperties(prefix="spring.myconfig")	// yml配置所在位置 
public class MyConfig {

	private String md5_salt;		// md5的盐 
	private Boolean is_pw;			// 是否明文存储密码  
	

	private String domain;			// 本项目部署到的服务器域名（文件上传、微信支付等等模块  要用到）
	
	
}
