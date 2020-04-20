package com.pj.current.satoken;

/**
 * 权限码常量  
 */
public final class AuthConst {


	// 私有构造方法 
	private AuthConst() {
	}
	
	
	// 代表身份的权限   
	public static final String r1 = "1"; 		// 角色_id_超级管理员  	 最高权限，超管身份的代表   
	public static final String r11 = "11"; 		// 角色_id_普通账号    
	public static final String r99 = "99";		// 进入后台权限，没有此权限无法进入后台管理     
	
	
	// 所有权限码 
	public static final String p101 = "101";		//  权限管理  
	public static final String p101_1 = "101-1";		//  权限管理  - 角色管理 
	public static final String p101_2 = "101-2";		//  权限管理  - 菜单列表
	public static final String p101_3 = "101-3";		//  权限管理  - 管理员列表
	public static final String p101_4 = "101-4";		//  权限管理  - 管理员添加
	public static final String p101_5 = "101-5";		//  权限管理  - 请求日志监控 
	
	
	
	
	
	
	
}
