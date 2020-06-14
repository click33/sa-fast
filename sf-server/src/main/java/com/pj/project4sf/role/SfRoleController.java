package com.pj.project4sf.role;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pj.current.satoken.AuthConst;
import com.pj.utils.sg.AjaxJson;
import com.pj.utils.sg.SoMap;

import cn.dev33.satoken.stp.StpUtil;

/**
 * Controller: 系统角色表
 * @author kong
 */
@RestController
@RequestMapping("/role/")
public class SfRoleController {

	/** 底层Mapper依赖 */
	@Autowired
	SfRoleMapper sfRoleMapper;

	// 增  
	@RequestMapping("add")
	AjaxJson add(SfRole s, HttpServletRequest request){
		 StpUtil.checkPermission(AuthConst.p_role_list);	// 鉴权
		// 检验
		if(sfRoleMapper.getById(s.getId()) != null) {
			return AjaxJson.getError("此id已存在，请更换");
		}
		SfRoleUtil.checkRoleThrow(s);
		int line = sfRoleMapper.add(s);
		return AjaxJson.getByLine(line);
	}

	// 删  
	@RequestMapping("delete")
	AjaxJson delete(long id, HttpServletRequest request){
		StpUtil.checkPermission(AuthConst.r1);	// 鉴权
		StpUtil.checkPermission(AuthConst.p_role_list);	// 鉴权
		int line = sfRoleMapper.delete(id);
		return AjaxJson.getByLine(line);
	}

	// 改  
	@RequestMapping("update")
	AjaxJson update(SfRole s){
		StpUtil.checkPermission(AuthConst.r1);	// 鉴权
		StpUtil.checkPermission(AuthConst.p_role_list);	// 鉴权
		SfRoleUtil.checkRoleThrow(s);
		int line = sfRoleMapper.update(s);
		return AjaxJson.getByLine(line);
	}

	// 查  
	@RequestMapping("getById")
	AjaxJson getById(long id){
		StpUtil.checkPermission(AuthConst.r99);	// 鉴权
		Object data = sfRoleMapper.getById(id);
		return AjaxJson.getSuccess("ok").setData(data);
	}

	// 查 - 集合（参数为null或0时默认忽略此条件）  
	@RequestMapping("getList")
	AjaxJson getList(){
		StpUtil.checkPermission(AuthConst.r99);	// 鉴权
		SoMap so = SoMap.getRequestSoMap();
		List<SfRole> list = sfRoleMapper.getList(so);
		return AjaxJson.getSuccessData(list);
	}


	
	

}
