package com.fan.jfinal.controller;

import com.fan.jfinal.base.BaseController;

public class UserController extends BaseController {
	
	// /u/uid
	public void index() {
		System.out.println();
		renderText("登录.");
	}
	
	public void login() {
		renderText("登录.");
	}
	
	public void logout() {
		renderText("doLogin.");
	}
	
	public void signup() {
		renderText("注册.");
	}
	
}
