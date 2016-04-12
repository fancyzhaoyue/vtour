package com.fan.jfinal.controller;

import com.fan.common.Constants;
import com.fan.common.util.ShaUtil;
import com.fan.jfinal.base.BaseController;
import com.fan.jfinal.interceptor.LoginInterceptor;
import com.fan.jfinal.model.User;
import com.fan.jfinal.validator.LoginValidator;
import com.jfinal.aop.Before;

public class AccountController extends BaseController {
	
	@Before(LoginInterceptor.class)
	public void signup() {
		
	}
	
	public void doSignUp() {
		
	}
	
	@Before(LoginValidator.class)
	public void doLogin() {

		// 查询用户是否存在
		User user = User.dao.findFirst("select * from user where email = ? ", getPara("email"));
		if(user == null){
			renderMsg(404, "用户不存在");
			return;
		}
		
		// 验密
		String shaPwd = ShaUtil.encode("SHA", getPara("password"));
		if(!shaPwd.equals(user.get("password"))){
			renderMsg(403, "密码错误");
			return;
		}
		
		// 初始化用户
		user.remove("password");
		
		setSessionAttr(Constants.SESSION_USER, user);
		setAttr("data", user);
		renderMsg();
	}
}
