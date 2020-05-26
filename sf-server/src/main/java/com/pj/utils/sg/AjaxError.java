package com.pj.utils.sg;

/**
 * Ajax发生异常时，直接抛出此异常即可   （比AjaxException更先进的版本）
 */
public class AjaxError extends RuntimeException {

	
	// ========================= 定义属性 =========================  
	
	private static final long serialVersionUID = 1L; 
	
	private int code = 500;		// 底层code码 
	/**
	 * @return 获取code码  
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @return 写入code码 ，连缀风格 
	 */
	public AjaxError setCode(int code) {
		this.code = code;
		return this;
	}
	
	
	// ========================= 构造方法 =========================  

	public AjaxError(int code, String message) {
        super(message);
		setCode(code);
    }
	public AjaxError(String message) {
        super(message);
    }
	public AjaxError(Throwable e) {
        super(e);
    }
	public AjaxError(String message, Throwable e) {
        super(message, e);
    }
	

	// ========================= 获取相关 =========================  
	
	/** 获得一个异常AjaxError */
	public static AjaxError get(String error_msg){
		return new AjaxError(error_msg);
	}
	/** 获得一个异常AjaxError */
	public static AjaxError get(int code, String error_msg){
		return new AjaxError(code, error_msg);
	}
	/** 获得一个异常AjaxError */
	public static AjaxError get(Throwable e){
		return new AjaxError(e);
	}
	

	// ========================= 获取并抛出 =========================  
	
	/** 获得一个异常，并直接抛出 */
	public static void getAndThrow(String error_msg) {
		throw new AjaxError(error_msg);
	}

	/** 如果条件为true，则抛出异常 */
	public static void by(boolean bo, int code, String error_msg) {
		if(bo) {
			throw get(code, error_msg);
		}
	}
	/** 如果条件为true，则抛出异常 */
	public static void by(boolean bo, String error_msg) {
		if(bo) {
			throw get(error_msg);
		}
	}
	/** 如果条件为true，则抛出异常 */
	public static void by(boolean bo) {
		if(bo) {
			throw get("error");
		}
	}
	

	/** 根据受影响行数的(大于0通过，小于等于0抛出error) */ 
	public static void byLine(int line, int code, String error_msg){
		if(line <= 0){
			throw get(code, error_msg);
		}
	}
	/** 根据受影响行数的(大于0通过，小于等于0抛出error) */ 
	public static void byLine(int line, String error_msg){
		if(line <= 0){
			throw get(error_msg);
		}
	}
	/** 根据受影响行数的(大于0通过，小于等于0抛出error) */ 
	public static void byLine(int line){
		if(line <= 0){
			throw get("受影响行数：0");
		}
	}
	


	/** 抛出异常，根据: 是否为空 */ 
	public static void byIsNull(Object value, int code, String error_msg){
		if(isNull(value)){
			throw get(code, error_msg);
		}
	}
	/** 抛出异常，根据: 是否为空 */ 
	public static void byIsNull(Object value, String error_msg){
		if(isNull(value)){
			throw get(error_msg);
		}
	}
	/** 抛出异常，根据: 是否为空 */ 
	public static void byIsNull(Object value){
		if(isNull(value)){
			throw get("不能为空");
		}
	}
	
	/** 
	 * 指定key是否为以下其一：null、""、0、"0"  
	 */
	public static boolean isNull(Object value) {
		return (value == null || value.equals("") || value.equals(0) || value.equals("0"));
	}
	
}
