package com.pj.project4sf.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pj.project4sf.SF;

import cn.dev33.satoken.stp.StpUtil;

/**
 * Service: admin管理员
 * @author kong
 *
 */
@Service
public class SfAdminService {

	
	// 管理员添加一个管理员
	@Transactional(rollbackFor = Exception.class, propagation=Propagation.REQUIRED)	// REQUIRED=如果调用方有事务  就继续使用调用方的事务 
	public long add(SfAdmin admin) {
		// 检查姓名是否合法
		SfAdminUtil.checkAdmin(admin);
		
		// 开始添加
		admin.setCreate_by_aid(StpUtil.getLoginId_asLong());	// 创建人，为当前账号  
		SF.sfAdminMapper.add(admin);	// 添加
		long id = SF.publicMapper.getPrimarykey();	// 获取主键
		SF.sfAdminPasswordService.updatePassword(id, admin.getPassword2());	// 更改密码（md5与明文）
		
		// 返回主键 
		return id;
		// return AjaxJson.getSuccessData(id);
	}
	
	
}
