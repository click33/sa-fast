package com.pj.project4sf.admin4password;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pj.current.config.SystemObject;
import com.pj.project4sf.SF;
import com.pj.project4sf.admin.SfAdmin;
import com.pj.project4sf.admin.SfAdminUtil;
import com.pj.utils.sg.AjaxJson;
import com.pj.utils.sg.NbUtil;

/**
 * admin表 密码相关 
 * 
 * @author shengzhang
 */
@RestController
@RequestMapping("/AdminPassword/")
public class SfAdminPasswordController {


	// 指定用户修改自己密码
	@RequestMapping("update")
	AjaxJson updatePassword(String old_pwd, String new_pwd) {
		// 1、转md5
		SfAdmin a = SfAdminUtil.getCurrAdmin();
		String old_pwd_md5 = SystemObject.getPasswordMD5(a.getId(), old_pwd);
		
		// 2、验证
		if(NbUtil.isNull(a.getPassword2()) && NbUtil.isNull(old_pwd)) {
			// 如果没有旧密码，则不用取验证 
		} else {
			if(old_pwd_md5.equals(a.getPassword2()) == false) {
				return AjaxJson.getError("旧密码输入错误");
			}
		}
		
		// 3、开始改 
		int line = SF.sfAdminPasswordService.updatePassword(a.getId(), new_pwd);
		return AjaxJson.getByLine(line);
	}
	
	
	
	
}
