package com.fan.jfinal.controller;

import com.fan.jfinal.base.BaseController;
import com.fan.jfinal.model.User;

public class UserController extends BaseController {
	
	public void index() {
		
		User user = User.dao.findById(getPara());
		setAttr("user", user);
		
		render("user.html");
	}
	
}
