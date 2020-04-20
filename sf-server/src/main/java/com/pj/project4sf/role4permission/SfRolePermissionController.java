package com.pj.project4sf.role4permission;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pj.current.satoken.AuthConst;
import com.pj.project4sf.SF;
import com.pj.project4sf.role.SfRoleUtil;
import com.pj.utils.sg.AjaxJson;

import cn.dev33.satoken.stp.StpUtil;

/**
 * Controller: 角色与权限的中间表
 */
@RestController
@RequestMapping("/SgRolePermission/")
public class SfRolePermissionController {

	
	
	
	// 拉取权限id列表  根据指定role_id 
	@RequestMapping("getPcodeByRid")
    public AjaxJson getPcodeByRid(@RequestParam(defaultValue="0") long role_id){
		StpUtil.checkPermission(AuthConst.r1);	// 鉴权
		StpUtil.checkPermission(AuthConst.p101_1);	// 鉴权 
		if(role_id == 0){
			return AjaxJson.getError("role_id不能为null或0");		// 防止拉出全部 	
		}
		return AjaxJson.getSuccessData(SF.sfRolePermissionService.getPcodeByRid(role_id));
	}
	
	
	// 拉取菜单id列表  根据当前用户role_id 
	@RequestMapping("getPcodeByCurrRid")
	public AjaxJson getPcodeByCurrRid(){
		long role_id = SfRoleUtil.getCurrRoleId();
		List<String> list = SF.sfRolePermissionService.getPcodeByRid(role_id);
		return AjaxJson.getSuccessData(list);
	}
	

	// 修改指定角色的拥有的权限 
	// role_id=角色id, code=拥有的权限码集合 
	@RequestMapping("updatePcodeByRid")
	public AjaxJson updatePcodeByRid(long role_id, String[] code){
		StpUtil.checkPermission(AuthConst.r1);	// 鉴权
		StpUtil.checkPermission(AuthConst.p101_1);	// 鉴权 
		return AjaxJson.getSuccess("ok", SF.sfRolePermissionService.updateRoleMenu(role_id, code));
	}
	


	
	


	
	
	
}
