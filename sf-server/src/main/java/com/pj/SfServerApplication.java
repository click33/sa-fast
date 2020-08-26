package com.pj;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.pj.utils.sg.SoMap;

import cn.dev33.satoken.spring.SaTokenSetup;

@SaTokenSetup	// sa-token权限验证
@EnableCaching // 启用缓存
@EnableScheduling // 启动定时任务
@SpringBootApplication // springboot本尊
@EnableTransactionManagement // 启动注解事务管理
/**
 * @author kong
 */
public class SfServerApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SfServerApplication.class, args);
		
		SoMap so = SoMap.getSoMap()
				.set("a", null)
				.set("b", 2)
				.set("c", 3)
				.set("d", 4)
//				.clearIn("a", "c")
				.deleteThis()
				;
		System.out.println(so.isNull("a")); 
	}
	
	

}