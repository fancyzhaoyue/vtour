package com.fan.jfinal.controller;


import java.util.List;

import com.fan.common.Constants;
import com.fan.common.util.ShaUtil;
import com.fan.jfinal.base.BaseController;
import com.fan.jfinal.model.Pano;
import com.fan.jfinal.model.User;
import com.fan.jfinal.validator.LoginValidator;
import com.fan.jfinal.validator.SignupValidator;
import com.jfinal.aop.Before;

public class IndexController extends BaseController {
	
	@SuppressWarnings("rawtypes")
	public void index() {
		// 最新上传全景图
		List panoList = Pano.dao.find("select * from pano");
		setAttr("panoList", panoList);
		
		render("index.html");
	}
	
	@Before(LoginValidator.class)
	public void login() {

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
		
		user.remove("password");
		
		setSessionAttr(Constants.SESSION_USER, user);
		setAttr("data", user);
		renderMsg();
	}
}
