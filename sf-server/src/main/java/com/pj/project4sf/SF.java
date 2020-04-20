package com.pj.project4sf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.project4sf.admin.SfAdminMapper;
import com.pj.project4sf.admin.SfAdminService;
import com.pj.project4sf.admin4login.SfAccAdminMapper;
import com.pj.project4sf.admin4login.SfAccAdminService;
import com.pj.project4sf.admin4password.SfAdminPasswordService;
import com.pj.project4sf.apilog.SfApilogMapper;
import com.pj.project4sf.public4mapper.PublicMapper;
import com.pj.project4sf.public4mapper.PublicService;
import com.pj.project4sf.role.SfRoleMapper;
import com.pj.project4sf.role4permission.SfRolePermissionMapper;
import com.pj.project4sf.role4permission.SfRolePermissionService;

/**
 * 有关sa-fast的所有的Bean在此定义 
 * @author kong
 *
 */
@Component
public class SF {
	

	// =================================  所有Mapper ==================================

	public static PublicMapper publicMapper;					// Mapper: 公共Mapper 
	public static SfRoleMapper sfRoleMapper;					// Mapper: 系统角色表 
	public static SfRolePermissionMapper sfRolePermissionMapper;			// Mapper: 角色权限中间表 
	public static SfAdminMapper sfAdminMapper;					// Mapper: admin管理员
	public static SfAccAdminMapper sfAccAdminMapper;					// Mapper: 管理员账号相关 
	public static SfApilogMapper sfApilogMapper;					// Mapper： api请求记录表
	
	
	
	// =================================  所有Service ==================================  

	public static PublicService publicService;						// Service：公共service
	public static SfAdminService sfAdminService;						// Service: 管理员
	public static SfAccAdminService sfAccAdminService;					// Service: 管理员账号相关 
	public static SfRolePermissionService sfRolePermissionService;			// Service: 角色权限中间表 
	public static SfAdminPasswordService sfAdminPasswordService;		// Service: admin管理员密码相关 
	
	

	// =================================  注入所有Bean ==================================  
	
	// 注入 
	@Autowired
	public void setBean(
			PublicMapper publicMapper,
			SfAccAdminMapper sfAccAdminMapper,
			SfRoleMapper sfRoleMapper,				
			SfRolePermissionMapper sfRolePermissionMapper,			
			SfAdminMapper sfAdminMapper,						
			SfApilogMapper sfApilogMapper, 
			
			PublicService publicService,
			SfAdminService sfAdminService,
			SfAccAdminService sfAccAdminService,
			SfRolePermissionService sfRolePermissionService,
			SfAdminPasswordService sfAdminPasswordService
			) {
		SF.publicMapper = publicMapper;
		SF.sfAccAdminMapper = sfAccAdminMapper;
		SF.sfRoleMapper = sfRoleMapper;
		SF.sfRolePermissionMapper = sfRolePermissionMapper;
		SF.sfAdminMapper = sfAdminMapper;
		SF.sfApilogMapper = sfApilogMapper;
		
		SF.publicService = publicService;
		SF.sfAdminService = sfAdminService;
		SF.sfAccAdminService = sfAccAdminService;
		SF.sfRolePermissionService = sfRolePermissionService;
		SF.sfAdminPasswordService = sfAdminPasswordService;
	}
	
	
	
	
}
