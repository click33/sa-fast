package com.pj.project4sf.role;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pj.current.satoken.AuthConst;
import com.pj.project4sf.SF;
import com.pj.utils.sg.AjaxJson;
import com.pj.utils.sg.SoMap;
import com.pj.utils.sg.SoMapUtil;

import cn.dev33.satoken.stp.StpUtil;

/**
 * Controller: 系统角色表
 * @author kong
 */
@RestController
@RequestMapping("/role/")
public class SfRoleController {


	// 增  
	@RequestMapping("add")
	AjaxJson add(SfRole s, HttpServletRequest request){
		 StpUtil.checkPermission(AuthConst.p101_1);	// 鉴权
		// 检验
		if(SF.sfRoleMapper.getById(s.getId()) != null) {
			return AjaxJson.getError("此id已存在，请更换");
		}
		SfRoleUtil.checkRoleThrow(s);
		int line = SF.sfRoleMapper.add(s);
		return AjaxJson.getByLine(line);
	}

	// 删  
	@RequestMapping("delete")
	AjaxJson delete(long id, HttpServletRequest request){
		StpUtil.checkPermission(AuthConst.r1);	// 鉴权
		StpUtil.checkPermission(AuthConst.p101_1);	// 鉴权
		int line = SF.sfRoleMapper.delete(id);
		return AjaxJson.getByLine(line);
	}

	// 改  
	@RequestMapping("update")
	AjaxJson update(SfRole s){
		StpUtil.checkPermission(AuthConst.r1);	// 鉴权
		StpUtil.checkPermission(AuthConst.p101_1);	// 鉴权
		SfRoleUtil.checkRoleThrow(s);
		int line = SF.sfRoleMapper.update(s);
		return AjaxJson.getByLine(line);
	}

	// 查  
	@RequestMapping("getById")
	AjaxJson getById(long id){
		StpUtil.checkPermission(AuthConst.r99);	// 鉴权
		Object data = SF.sfRoleMapper.getById(id);
		return AjaxJson.getSuccess("ok").setData(data);
	}

	// 查 - 集合（参数为null或0时默认忽略此条件）  
	@RequestMapping("getList")
	AjaxJson getList(){
		StpUtil.checkPermission(AuthConst.r99);	// 鉴权
		SoMap so = SoMapUtil.getSoMap(); 
		List<SfRole> list = SF.sfRoleMapper.getList(so);
		return AjaxJson.getSuccessData(list);
	}


	
	

}
