package com.pj.project4sf.public4mapper;

/**
 * 以lambda表达式开启事务的辅助类
 * @author kong
 *
 */
public interface JdbcLambdaBeginRT {
	

	/**
	 * 执行事务的方法 
	 * @return
	 */
	public Object run();
	
	
}
