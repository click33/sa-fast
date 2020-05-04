package com.pj.project4sf.apilog;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Model: api请求记录表
 * @author kong 
 */
@Data
public class SfApilog implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;		// 记录id 
	private String req_id;		// 请求id 
	private String req_ip;		// 客户端ip 
	private String req_api;		// 请求api 
	private String req_parame;		// 请求参数 
	private String req_type;		// 请求方式
	private String req_token;		// 请求token
	private String req_header;		// 请求header
	
	private int res_code;		// 返回-状态码 
	private String res_msg;		// 返回-信息描述 
	private String res_string;		// 返回-整个信息字符串形式 
	
	private long user_id;		// user_id 
	private long admin_id;		// admin_id 
	
	private Date start_time;		// 请求开始时间 
	private Date end_time;		// 请求结束时间 
	private int cost_time;		// 花费时间，单位ms 
	
	
	// 构造一个普通 实体类
	public SfApilog() {}
	
	// 构造一个 save 实体类 
	public SfApilog(String req_id, String req_ip, String req_api, String req_parame, String req_token, long user_id,
			long admin_id) {
		super();
		this.req_id = req_id;
		this.req_ip = req_ip;
		this.req_api = req_api;
		this.req_parame = req_parame;
		this.req_token = req_token;
		this.user_id = user_id;
		this.admin_id = admin_id;
	}

	// 构造一个 update 实体类 
	public SfApilog(String req_id, int res_code, String res_msg, String res_string, int cost_time) {
		super();
		this.req_id = req_id;
		this.res_code = res_code;
		this.res_msg = res_msg;
		this.res_string = res_string;
		this.cost_time = cost_time;
	}

	
	
	
	

}
