package com.pj.project4sf.admin4password;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pj.current.config.SystemObject;
import com.pj.project4sf.SF;

/**
 * 用户表 密码相关 
 */
@Service
public class SfAdminPasswordService {

	

	// 修改一个admin的密码为 
	@Transactional(rollbackFor = Exception.class, propagation=Propagation.REQUIRED)	// REQUIRED=如果调用方有事务  就继续使用调用方的事务 
	public int updatePassword(long admin_id, String password) {
		SF.publicMapper.updateColumnById("sf_admin", "password", SystemObject.getPasswordMD5(admin_id, password), admin_id);	// 更改密码 
		if(SystemObject.config.getIs_pw()) {
			SF.publicMapper.updateColumnById("sf_admin", "pw", password, admin_id);		// 明文密码 
			return 2;
		}
		return 1;
	}
	
	
}
