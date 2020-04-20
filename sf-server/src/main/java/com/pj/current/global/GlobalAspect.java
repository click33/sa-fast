package com.pj.current.global;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.pj.project4sf.apilog.SfApilogUtil;
import com.pj.utils.sg.AjaxJson;

/**
 * 全局日志切面, 拦截所有controller请求，写入日志 
 */
@Aspect
@Component
public class GlobalAspect {
    
	// AOP签名 --> sg代码 
	@Pointcut("execution(* com.pj.project4sf.*.*Controller*.*(..))")
    public void webLog_project4sf(){}

	// AOP签名 --> 项目代码 
	@Pointcut("execution(* com.pj.project.*.*Controller*.*(..))")
    public void webLog_project(){}

	
    // 环绕通知,环绕增强，相当于MethodInterceptor
    @Around("webLog_project4sf() || webLog_project()")
    public Object arround(ProceedingJoinPoint pjp) throws Throwable {
    	// 1、开始时 
    	SfApilogUtil.startRequest();
        try {
        	// 2、执行时 
            Object obj =  pjp.proceed();
            if(obj instanceof AjaxJson){	// 如果是 AjaxJson 
            	SfApilogUtil.endRequest((AjaxJson)obj);
            } else if (obj instanceof String) {	// 如果是 String 
            	SfApilogUtil.endRequest(AjaxJson.get(901, String.valueOf(obj)));
            } else {	// 如果都不是 
            	SfApilogUtil.endRequest(AjaxJson.get(902, String.valueOf(obj)));
            }
            return obj;
        } catch (Throwable e) {
        	throw e;	// 3、发生异常时
        }
    }
    
    
}