package com.fan.jfinal.controller;


import com.fan.common.Constants;
import com.fan.jfinal.base.BaseController;
import com.fan.jfinal.model.User;
import com.fan.jfinal.validator.LoginValidator;
import com.fan.jfinal.validator.SignupValidator;
import com.fan.util.ShaUtil;
import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.POST;

public class IndexController extends BaseController {
	
	public void index() {
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
	
	@Before({POST.class, SignupValidator.class})
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
		
	}
}
