package com.fan.jfinal.validator;

import com.jfinal.core.Controller;

public class SignupValidator  extends ShortCircuitValidator{

	protected void validate(Controller c) {
		validateRequired("email",    "message", "邮箱不能为空");
		validateRequired("nickName", "message", "昵称不能为空");
		validateRequired("password", "message", "密码不能为空");
	}
}
