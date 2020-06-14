package com.pj.project4sf.admin4login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pj.project4sf.admin.SfAdmin;
import com.pj.project4sf.admin.SfAdminUtil;
import com.pj.project4sf.role4permission.SfRolePermissionService;
import com.pj.utils.sg.AjaxJson;
import com.pj.utils.sg.NbUtil;

import cn.dev33.satoken.stp.StpUtil;

/**
 * admin账号相关的接口 
 * @author kong
 *
 */
@RestController
@RequestMapping("/AccAdmin/")
public class SfAccAdminController {

	
	@Autowired
	SfAccAdminService sfAccAdminService;
	
	@Autowired
	SfRolePermissionService sfRolePermissionService;
	
	
	// 账号、密码登录 
	@RequestMapping("doLogin")
	AjaxJson doLogin(String key, String password) {
		// 1、验证参数 
		if(NbUtil.isOneNull(key, password)) {
			return AjaxJson.getError("请提供key与password参数");
		}
		return sfAccAdminService.doLogin(key, password);
	}
	
	
	// 退出登录 
	@RequestMapping("doExit")
	AjaxJson doExit() {
		StpUtil.logout();
		return AjaxJson.getSuccess();
	}
	

	// 管理员登录后台时需要返回的信息 
	// admin=当前登录admin信息，menu_list=当前admin权限集合 
	@RequestMapping("fristOpenAdmin")
	AjaxJson fristOpenAdmin(HttpServletRequest request) {
		// 当前admin
		SfAdmin admin = SfAdminUtil.getCurrAdmin();
		
		// 组织参数 
		Map<String, Object> map = new HashMap<>();
		map.put("admin", SfAdminUtil.getCurrAdmin());	// 当前登录admin
		map.put("per_list", sfRolePermissionService.getPcodeByRid(admin.getRole_id()));								// 当前拥有的权限集合 
//		map.put("app_cfg", SysCfgUtil.getAppCfg());								// 当前系统的配置  
		return AjaxJson.getSuccessData(map); 
	}
	
	
	// 测试
	@RequestMapping("test")
	AjaxJson test() {
//		System.err.println("================================");
//		SoMapUtil.getSoMap().getList("id").add("哈哈哈");
//		System.err.println(JSON.toJSONString(SoMapUtil.getSoMap()));
//		int line = 0;
//		AjaxError.by(line > 0, "新增店铺失败");
//		AjaxError.byLine(line, "新增店铺失败");
//		AjaxError.byIsNull("d", "商品id 不能为空");	// 验证: 商品id 
		return AjaxJson.getSuccess();
	}
	
	
	
	
	
}
