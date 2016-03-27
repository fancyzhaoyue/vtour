package com.fan.jfinal.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator  extends Validator{

	protected void validate(Controller c) {
		validateRequired("email", "errMsg", "邮箱不能为空");
		validateRequired("password", "errMsg", "密码不能为空");
	}

	protected void handleError(Controller c) {
		c.renderJson();
	}

}
