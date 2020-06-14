package com.pj.current.satoken;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.project4sf.admin.SfAdminMapper;
import com.pj.project4sf.role4permission.SfRolePermissionService;

import cn.dev33.satoken.stp.StpInterface;

/**
 * 自定义权限验证接口扩展 
 */
@Component	// 打开此注解，保证此类被springboot扫描，即可完成sa-token的自定义权限验证扩展 
public class StpInterfaceImpl implements StpInterface {

	
	@Autowired
	SfAdminMapper sfAdminMapper;
	
	@Autowired
	SfRolePermissionService sfRolePermissionService;
	
	
	// 返回一个账号所拥有的权限码集合 
	@Override
	public List<Object> getPermissionCodeList(Object login_id, String login_key) {
		long role_id = sfAdminMapper.getById(Long.valueOf(login_id.toString())).getRole_id();
		List<String> codeList = sfRolePermissionService.getPcodeByRid(role_id);		// 所有权限id  
		List<Object> codeList2 = codeList.stream().map(String::valueOf).collect(Collectors.toList());	// 转Object 
		return codeList2;
	}

}
