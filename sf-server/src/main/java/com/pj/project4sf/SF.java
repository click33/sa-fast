package com.pj.project4sf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.project4sf.public4mapper.PublicMapper;
import com.pj.project4sf.public4mapper.PublicService;

/**
 * 有关sa-fast的所有的Bean在此定义 
 * @author kong
 *
 */
@Component
public class SF {
	

	// =================================  所有Mapper ==================================

	public static PublicMapper publicMapper;					// Mapper: 公共Mapper 
	
	
	
	// =================================  所有Service ==================================  

	public static PublicService publicService;						// Service：公共service
	
	

	// =================================  注入所有Bean ==================================  
	
	// 注入 
	@Autowired
	public void setBean(
			PublicMapper publicMapper,
			PublicService publicService
			) {
		SF.publicMapper = publicMapper;
		SF.publicService = publicService;
	}
	
	
	
	
}
