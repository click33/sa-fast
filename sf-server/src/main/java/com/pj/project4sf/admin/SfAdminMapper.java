package com.pj.project4sf.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pj.utils.sg.SoMap;

/**
 * Mapper: 系统管理员表
 * @author kong
 */
@Mapper
public interface SfAdminMapper {


	/**
	 * 增 #{name}, #{password}, #{role_id}
	 */
	int add(SfAdmin obj);

	/**
	 * 删 
	 */
	int delete(long id);

	/**
	 * 改 
	 */
	int update(SfAdmin obj);

	/**
	 * 查 
	 */
	SfAdmin getById(long id);

	/**
	 * 查 - 集合（参数为null或0时默认忽略此条件） 
	 */
	List<SfAdmin> getList(SoMap so);


	/**
	 * 查询，根据name
	 */
	SfAdmin getByName(String name);
	
	
	/**
	 * 查询，根据 phone 
	 */
	SfAdmin getByPhone(String phone);



}
