package com.pj.project4sf.apilog;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pj.current.satoken.AuthConst;
import com.pj.project4sf.SF;
import com.pj.utils.sg.AjaxJson;
import com.pj.utils.sg.SoMap;
import com.pj.utils.sg.SoMapUtil;

import cn.dev33.satoken.stp.StpUtil;

/**
 * Controller: api请求记录表
 * @author kong 
 */
@RestController
@RequestMapping("/SgApilog/")
public class SfApilogControlle {


	// 删  
	@RequestMapping("delete")
	AjaxJson delete(String id){
		StpUtil.checkPermission(AuthConst.p101_5);	// 鉴权 
		int line = SF.sfApilogMapper.delete(id);
		return AjaxJson.getByLine(line);
	}

	// 查 - 集合（参数为null或0时默认忽略此条件）  
	@RequestMapping("getList")
	AjaxJson getList() { 
		StpUtil.checkPermission(AuthConst.p101_5);	// 鉴权 
		SoMap so = SoMapUtil.getSoMap();
		List<SfApilog> list = SF.sfApilogMapper.getList(so.startPage());
		return AjaxJson.getPageData(so.endPage(), list);
	}
	
	// 测试  
	@RequestMapping("tt")
	AjaxJson tt(int a) { 
		System.out.println("controller");
		if(a == 1) {
			throw new RuntimeException("自定义异常");
		}
		return AjaxJson.getSuccess();
	}

}
