package com.codegenius.shop.web.vo;

import com.alibaba.fastjson.JSONObject;

public class ResultVo {

	boolean success;
	String message;
	Object data;

	public ResultVo(boolean success, String message){
		this.success = success;
		this.message = message;
	}
	
	public ResultVo(boolean success, Object data){
		this.success = success;
		this.data = data;
	}
	
	public ResultVo(boolean success, String message, Object data){
		this.success = success;
		this.message = message;
		this.data = data;
	}
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
