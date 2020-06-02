package com.pj.project4sf.sfcfg;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * DB活动配置操作工具类
 * 
 * @author kong
 *
 */
@Component
public class SfCfgUtil {

	
	// 配置信息
	private static SfCfgService sysCfgService;
	@Autowired
	public void setSysCfgService(SfCfgService sysCfgService) {
		SfCfgUtil.sysCfgService = sysCfgService;
	}
	
	
	
	// ====================== 快捷读取 DB 配置信息 ========================== 


	// 获取指定【cfg_name】的配置，指定key项，并转化为String值 , 取不到值时，给默认值【default_value】
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static String getCfgBy(String cfg_name, String key, String default_value) {
		// 1、获取配置字符串 
		String cfg_json = sysCfgService.getCfgValue(cfg_name);
		// 2、转换成Map
		Map<String, Object> maps = (Map)JSON.parse(cfg_json);
		// 3、取值
		Object value = maps.get(key);
		if (value == null) {
			return default_value;
		}
		return value.toString();
	}
	
	// 获取server端指定配置信息
	public static String getServerCfg(String key, String default_value) {
		return SfCfgUtil.getCfgBy("server_cfg", key, default_value);
	}
	
	// 获取App端指定配置信息 
	public static String getAppCfg(String key, String default_value) {
		return SfCfgUtil.getCfgBy("app_cfg", key, default_value);
	}
	
	
	
	
	

	// ====================== 获取指定配置 ========================== 
	

	// --- app  
	
	// 获取app端全部配置信息
	public static String getAppCfg() {
		return sysCfgService.getCfgValue("app_cfg");
	}

	// 获取配置信息：系统名称 
	public static String get_app_name() {
		return SfCfgUtil.getAppCfg("app_name", "");
	}
	
	// 获取配置信息：版本号 
	public static String get_app_version_no() {
		return SfCfgUtil.getAppCfg("app_version_no", "");
	}
	
	
	// --- server  
	
	// 新用户，默认头像地址 
	public static String get_reserve_info() {
		return SfCfgUtil.getServerCfg("reserve_info", "");
	}

	
	
	
}
