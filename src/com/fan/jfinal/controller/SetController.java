package com.fan.jfinal.controller;

import com.fan.common.Constants;
import com.fan.jfinal.base.BaseController;
import com.fan.jfinal.interceptor.LoginInterceptor;
import com.fan.jfinal.model.User;
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
}
