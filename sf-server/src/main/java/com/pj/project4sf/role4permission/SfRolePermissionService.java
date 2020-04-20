package com.pj.project4sf.role4permission;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.project4sf.SF;

/**
 * 角色权限中间表 
 * @author kong
 *
 */
@Service
public class SfRolePermissionService {

	
	/**
	 * 获取指定角色的所有权限码 
	 */
    @Cacheable(value="api_pcode_list", key="#role_id")	// @增加缓存
    public List<String> getPcodeByRid(long role_id){
    	return SF.sfRolePermissionMapper.getPcodeByRoleId(role_id);
    }
	

    /**
     * [T] 修改角色的一组权限关系
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value="api_pcode_list", key="#role_id")	// @清除缓存 
    public int updateRoleMenu(long role_id, String[] pcodeArray){

    	// 万一为空 
    	if(pcodeArray == null){
    		pcodeArray = new String[0];
    	}
    	
    	// 先删
    	SF.sfRolePermissionMapper.deleteByRoleId(role_id);
    	
    	// 再添加
    	for(String pcode : pcodeArray){
    		SF.sfRolePermissionMapper.add(role_id, pcode);
        }
    	
    	// 返回
        return pcodeArray.length;
    }
	
	
	
}
