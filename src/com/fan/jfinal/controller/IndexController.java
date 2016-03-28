package com.fan.jfinal.controller;


import com.fan.jfinal.base.BaseController;
import com.fan.jfinal.model.User;
import com.fan.jfinal.validator.LoginValidator;
import com.fan.jfinal.validator.SignupValidator;
import com.jfinal.aop.Before;

public class IndexController extends BaseController {
	
	public void index() {		
		render("index.html");
	}
	
	@Before(LoginValidator.class)
	public void login() {
		// 1.通过统一登录查看 是否存在该用户
		// 2.验密
		// 3.初始化用户
		
		String email = getPara("email");
		String password = getPara("password");
		
		User user = User.dao.findFirst("select * from user where email = ? and password = ?", email, password);
				
		setAttr("data", user);
		
		setSessionAttr("_USER", user);
		setCookie("uid", user.getStr("uid"), 10000);
		renderMsg();
	}
	
	public void logout() {
		removeSessionAttr("_USER");
		redirect("/");
	}
	@Before(SignupValidator.class)
	public void signup() {
		User user = getModel(User.class);
		
		String email = getPara("email");
		String nickName = getPara("nickName");
		String password = getPara("password");
		
		user.set("email", email);
		user.set("nickName", nickName);
		user.set("password", password);
		user.save();
		
		setSessionAttr("_USER", user);
		setCookie("uid", user.getStr("uid"), 10000);
		
		renderMsg();
	}
}
