package com.pj.project4sf.admin4login;

import org.apache.ibatis.annotations.Mapper;

import io.lettuce.core.dynamic.annotation.Param;

/**
 * 账号相关 
 * @author kong
 *
 */
@Mapper
public interface SfAccAdminMapper {

	// 指定id的账号成功登录一次 
	public int successLogin(@Param("id")long id, @Param("login_ip")String login_ip);
	
}
