package com.fan.jfinal.validator;

import com.jfinal.core.Controller;

public class LoginValidator extends ShortCircuitValidator{
	
	protected void validate(Controller c) {
		validateRequired("email", "message", "邮箱不能为空");
		validateRequired("password", "message", "密码不能为空");
	}

}
