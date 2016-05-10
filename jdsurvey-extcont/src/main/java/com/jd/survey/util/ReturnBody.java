package com.jd.survey.util;
public class ReturnBody{


	private int code=200;	//
	private String message;	//返回信息
	private  ShopSurveyRelEntity data ;
	private  String request_id ;
	
	
	public ReturnBody(String message) {
		this.message = message;
		this.setData(null);
	}


	

	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ShopSurveyRelEntity getData() {
		return data;
	}

	public void setData(ShopSurveyRelEntity data) {
		this.data = data;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	
}



