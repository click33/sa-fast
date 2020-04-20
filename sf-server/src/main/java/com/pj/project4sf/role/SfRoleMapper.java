package com.pj.project4sf.role;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pj.utils.sg.SoMap;

/**
 * Mapper: 系统角色表
 * @author kong
 */
@Mapper
public interface SfRoleMapper {


	/**
	 * 增 
	 */
	int add(SfRole obj);

	/**
	 * 删 
	 */
	int delete(long id);

	/**
	 * 改 
	 */
	int update(SfRole obj);

	/**
	 * 查 
	 */
	SfRole getById(long id);

	/**
	 * 查 - 集合（参数为null或0时默认忽略此条件） 
	 */
	List<SfRole> getList(SoMap soMap);


	/**
	 * 查，根据角色名字
	 */
	SfRole getByRoleName(String role_name);

	
	int add2(String role_name);
	
}
