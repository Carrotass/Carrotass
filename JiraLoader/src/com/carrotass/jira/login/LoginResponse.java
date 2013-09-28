package com.carrotass.jira.login;

import java.util.List;

public class LoginResponse {
	public boolean Succeed;
	
	public List<String> Cookies;
	
	public int HTTPCode;
	
	public static LoginResponse SucceedResponse(List<String> cookies) {
		LoginResponse instance = new LoginResponse();
		instance.Succeed = true;
		instance.Cookies = cookies;
		instance.HTTPCode = 200;
		return instance;
	}
	
	public static LoginResponse FailedResponse(int httpCode) {
		LoginResponse instance = new LoginResponse();
		instance.Succeed = false;
		instance.HTTPCode = httpCode;
		return instance;
	}
}
