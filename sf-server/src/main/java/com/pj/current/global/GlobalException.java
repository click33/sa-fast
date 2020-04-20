package com.pj.current.global;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.project4sf.apilog.SfApilogUtil;
import com.pj.utils.sg.AjaxJson;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;

/**
 * 全局异常拦截
 */
@ControllerAdvice // 可指定包前缀，例如：(basePackages = "com.pj.controller.admin")
public class GlobalException {

	// 全局异常拦截
	@ResponseBody
	@ExceptionHandler
	public AjaxJson handlerException(Exception e) throws Exception {

		// 打印堆栈，以供调试
		e.printStackTrace(); 

    	// 记录日志信息
    	AjaxJson aj = null;
		if(e instanceof NotLoginException){	// 如果是未登录异常 
			aj = AjaxJson.getNotLogin();
		} else if(e instanceof NotPermissionException) {	// 如果是权限异常
			NotPermissionException ee = (NotPermissionException) e;
			aj = AjaxJson.getNotJur("无此权限：" + ee.getCode());
		} else {	// 普通异常输出：500 + 异常信息
			aj = AjaxJson.getError(e.getMessage());
		}
		SfApilogUtil.endRequest(aj);
		
		// 返回到前台 
		return aj;

		// 输出到前台 
//			response.setContentType("application/json; charset=utf-8"); // http说明，我要返回JSON对象
//			response.getWriter().print(new ObjectMapper().writeValueAsString(aj));
	}

}
