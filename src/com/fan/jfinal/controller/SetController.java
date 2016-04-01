package com.fan.jfinal.controller;

import com.fan.common.Constants;
import com.fan.jfinal.base.BaseController;
import com.fan.jfinal.interceptor.LoginInterceptor;
import com.fan.jfinal.model.User;
import com.fan.jfinal.validator.SignupValidator;
import com.fan.util.ShaUtil;
import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.POST;

@Before(LoginInterceptor.class)
public class SetController extends BaseController {
	
	public void profile() {
		render("profile.html");
	}
	
	@Before(POST.class)
	public void doProfile(){
		User user = getSessionAttr(Constants.SESSION_USER);
		
		user.set("nickName", getPara("nickName"));
		user.update();
		
		setSessionAttr(Constants.SESSION_USER, user);
		setAttr("data", user);
		renderMsg();
	}
	
	public void account() {
		render("index.html");
	}
	
	public void message() {
		render("index.html");
	}
	public void logout() {
		removeSessionAttr(Constants.SESSION_USER);
		redirect("/");
	}
	
	@Before(SignupValidator.class)
	public void signup() {
		
		// 验证邮箱唯一性
		User user1  = User.dao.findFirst("select * from user where email = ? ", getPara("email"));
		if(user1 != null){
			renderMsg(403, "邮箱已存在");
			return;
		}
		
		// 注册用户
		User user = getModel(User.class);
		user.set("email",    getPara("email"));
		user.set("nickName", getPara("nickName"));
		user.set("password", ShaUtil.encode("SHA", getPara("password")));
		user.save();
		
		// 根据id生成uid
		user.set("uid", user.get("id"));
		user.update();
		
		user.remove("password");
		setSessionAttr(Constants.SESSION_USER, user);
		setAttr("data", user);
		renderMsg();
	}
}
