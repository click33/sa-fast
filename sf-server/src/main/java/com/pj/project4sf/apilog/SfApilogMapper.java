package com.pj.project4sf.apilog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pj.utils.sg.SoMap;

/**
 * Mapper: api请求记录表
 * @author kong 
 */
@Mapper
public interface SfApilogMapper {

	/** 保存入库 */
	int saveObj(SfApilog apiLog);
	
	
	/** 增 */
	int add(SfApilog apiLog);

	/** 删 */
	int delete(String id);	 

	/** 改 */
	int update(SfApilog apiLog);
	

	

//	/** 查 */
//	ApiLog getById(String id);	 

	/** 查 - 集合（参数为null或0时默认忽略此条件） */
	List<SfApilog> getList(SoMap so);


}
