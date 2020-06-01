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
	public static final String p_auth = "auth";		//  权限管理  
	public static final String p_role_list = "role-list";		//  权限管理  - 角色管理 
	public static final String p_menu_list = "menu-list";		//  权限管理  - 菜单列表
	public static final String p_admin_list = "admin-list";		//  权限管理  - 管理员列表
	public static final String p_admin_add = "admin-add";		//  权限管理  - 管理员添加
	public static final String p_apilog_list = "apilog-list";		//  权限管理  - 请求日志监控 

	public static final String p_sf_cfg = "sf-cfg";		//  系统配置 	
	public static final String p_sf_cfg_app = "sf-cfg-app";		//  系统配置 - 系统对公配置		
	public static final String p_sf_cfg_server = "sf-cfg-server";		//  系统配置 - 服务器私有配置 		
	
	
	
	
	
	
}
