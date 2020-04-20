package com.pj.utils.sg;

/**
 * Ajax发生异常时，直接抛出此异常即可 
 */
public class AjaxException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	

	/**
	 * 获得一个异常AjaxException
	 */
	public AjaxException(String message) {
        super(message);
    }
	public AjaxException(Throwable e) {
        super(e);
    }
	public AjaxException(String message, Throwable e) {
        super(message, e);
    }
	
	/**
	 * 获得一个异常AjaxException
	 */
	public static AjaxException get(String error_msg){
		return new AjaxException(error_msg);
	}
	public static AjaxException get(Throwable e){
		return new AjaxException(e);
	}
	
	/** 获得一个异常，并直接抛出 */
	public static AjaxException getAndThrow(String error_msg) {
		throw new AjaxException(error_msg);
	}

	
	/**
	 * 如果条件为true，则抛出异常
	 */
	public static void getBy(boolean bo, String error_msg) {
		if(bo) {
			throw get(error_msg);
		}
	}
	public static void getBy(boolean bo) {
		if(bo) {
			throw get("error");
		}
	}
	
	/**
	 * 根据受影响行数的(大于0通过，小于等于0抛出error)
	 */ 
	public static void getByLine(int line, String error_msg){
		if(line <= 0){
			getAndThrow(error_msg);
		}
	}
	public static void getByLine(int line){
		if(line <= 0){
			getAndThrow("受影响行数：0");
		}
	}
	
}
