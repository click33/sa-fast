package com.pj.project4sf.sfcfg;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pj.project4sf.SF;

@Service
public class SysCfgService {

	
	// 获得cfg_value 根据cfg_name
	@Cacheable(value="sf_cfg_", key="#cfg_name")	// 加入缓存，以提高性能 
	public String getCfgValue(String cfg_name){
		return SF.publicMapper.getColumnByWhere("sf_cfg", "cfg_value", "cfg_name", cfg_name);
	}
	
	
	// 修改cfg_value 根据cfg_name
	@CacheEvict(value="sf_cfg_", key="#cfg_name")	// 清除缓存 
	public int updateCfgValue(String cfg_name, String cfg_value){
		return SF.publicMapper.updateColumnBy("sf_cfg", "cfg_value", cfg_value, "cfg_name", cfg_name);
	}
	
	
	
		
	
	
	
	
}
