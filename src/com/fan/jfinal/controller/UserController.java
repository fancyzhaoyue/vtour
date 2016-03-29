package com.fan.jfinal.controller;

import com.fan.jfinal.base.BaseController;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class UserController extends BaseController {
	
	public void index() {
		
		String sql = "select id,nickname,email,uid from user where uid= ?";
		Record user = Db.findFirst(sql, getPara(0));
		System.out.println(user.get("nickname"));
		setAttr("user", user);
		render("user.html");
	}
	
}
