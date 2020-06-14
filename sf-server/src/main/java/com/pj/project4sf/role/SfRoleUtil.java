package com.pj.project4sf.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.project4sf.admin.SfAdminUtil;
import com.pj.utils.sg.AjaxError;
import com.pj.utils.sg.AjaxJson;
import com.pj.utils.sg.NbUtil;

/**
 * 工具类：SysRole
 * @author kong
 *
 */
@Component
public class SfRoleUtil {
	

	/** 底层Mapper依赖 */
	static SfRoleMapper sfRoleMapper;
	@Autowired
	public void setSfRoleMapper(SfRoleMapper sfRoleMapper) {
		SfRoleUtil.sfRoleMapper = sfRoleMapper;
	}
	
	// 获取当前会话的role_id
	public static long getCurrRoleId() {
		return SfAdminUtil.getCurrAdmin().getRole_id();
	}
	
	

	// 验证一个SysRole 是否符合标准 
	static AjaxJson checkRole(SfRole s) {
		
		// 1、名称相关 
		if(NbUtil.isNull(s.getRole_name())) {
			return AjaxJson.getError("昵称不能为空");
		}
		SfRole s2 = sfRoleMapper.getByRoleName(s.getRole_name());
		if(s2 != null && s2.getId() != s.getId()) {	// 如果该名称已存在，并且不是当前角色 
			return AjaxJson.getError("昵称与已有角色重复，请更换");
		}
		
		// 重重检验，最终合格
		return AjaxJson.getSuccess();
	}
	// 验证一个Role是否符合标准, 不符合则抛出异常
	static void checkRoleThrow(SfRole s) {
		AjaxJson aj = checkRole(s);
		if(aj.getCode() != AjaxJson.CODE_SUCCESS){
			throw AjaxError.get(aj.getMsg());
		}
	}





	
	
}
