package com.fan.jfinal.controller;


import com.fan.jfinal.base.BaseController;

public class IndexController extends BaseController {
	
	public void index() {
		render("index.html");
	}
	
	public void login() {
		// 1.通过统一登录查看 是否存在该用户
		// 2.验密
		// 3.初始化用户
		renderMsg();
	}
	
	public void logout() {
		renderText("dsf");
	}
	
	public void signup() {
		renderText("注册.");
	}
}
