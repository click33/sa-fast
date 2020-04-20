package com.pj.project4sf.admin;

import com.pj.project4sf.SF;
import com.pj.utils.sg.AjaxException;
import com.pj.utils.sg.NbUtil;

import cn.dev33.satoken.SaTokenManager;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;

/**
 * admin工具类 
 * @author kong
 *
 */
public class SfAdminUtil {

	
	
	// 当前admin
	public static SfAdmin getCurrAdmin() {
		long admin_id = StpUtil.getLoginId_asLong();
		return SF.sfAdminMapper.getById(admin_id);
	}
	
	
	// 检查指定姓名是否合法 ,如果不合法，则抛出异常 
	public static boolean checkName(long admin_id, String name) {
		if(NbUtil.isNull(name)) {
			throw AjaxException.get("账号名称不能为空");
		}
		if(NbUtil.isNumber(name)) {
			throw AjaxException.get("账号名称不能为纯数字");
		}
//		if(name.startsWith("a")) {
//			throw AjaxException.get("账号名称不能以字母a开头");
//		}
		SfAdmin a2 = SF.sfAdminMapper.getByName(name);
		if(a2 != null && a2.getId() != admin_id) {	// 能查出来数据，而且不是本人，则代表与已有数据重复
			throw AjaxException.get("账号名称已有账号使用，请更换");
		} 
		return true;
	}
	
	// 检查整个admin是否合格 
	public static boolean checkAdmin(SfAdmin a) {
		// 检查姓名 
		checkName(a.getId(), a.getName());
		// 检查密码 
		if(a.getPassword2().length() < 4) {
			throw new AjaxException("密码不得低于4位");
		}
		return true;
	}
	
	
	
	// 指定的name是否可用 
	public static boolean nameIsOk(String name) {
		SfAdmin a2 = SF.sfAdminMapper.getByName(name);
		if(a2 == null) {
			return true;
		}
		return false;
	}
	
	
	// 获取指定token对应的admin_id 
	public static long getAdminIdByToken(String token) {
		Object login_id = SaTokenManager.getDao().getValue(StpUtil.stpLogic.getKey_TokenValue(token));
		if(login_id == null) {
			throw new NotLoginException();
		}
		return Long.parseLong(login_id.toString());
	}


	
	
}
