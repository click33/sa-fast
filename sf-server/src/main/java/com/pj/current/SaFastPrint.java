package com.pj.current;

import org.springframework.stereotype.Component;

/**
 * sa-fast加载打印 
 * @author kong
 *
 */
@Component
public class SaFastPrint {

	public SaFastPrint() {
		System.out.println("===================================================================");
		System.out.println("sa-fast快速开发平台,  当前版本v1.7.0，更新于2020-5-26 ");
		System.out.println("在线文档： http://sa-fast.dev33.cn/");
		System.out.println("开源地址： https://github.com/click33/sa-fast");
		System.out.println("===================================================================");
	}
	
}
