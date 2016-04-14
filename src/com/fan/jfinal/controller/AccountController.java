package com.fan.jfinal.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.fan.common.Constants;
import com.fan.common.mail.MailSenderService;
import com.fan.common.util.DateUtil;
import com.fan.common.util.UUIDUtil;
import com.fan.core.BaseBussException;
import com.fan.jfinal.base.BaseController;
import com.fan.jfinal.interceptor.LoginInterceptor;
import com.fan.jfinal.model.EmailToken;
import com.fan.jfinal.model.User;
import com.fan.jfinal.validator.LoginValidator;
import com.fan.jfinal.validator.SignupValidator;
import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.HashKit;
import com.jfinal.plugin.activerecord.tx.Tx;

public class AccountController extends BaseController {
	
	@Before(LoginValidator.class)
	public void doLogin() {
		// 查询用户是否存在
		User user = User.dao.getUserByUniqueId(getPara("email"));
		if(user == null){
			throw new BaseBussException("用户不存在");
		}
		
		// 验密
		String shaPwd = HashKit.md5(getPara("password"));
		if(!shaPwd.equals(user.get("password"))){
			throw new BaseBussException("密码错误");
		}
		
		// 初始化用户
		user.remove("password");
		
		setSessionAttr(Constants.SESSION_USER, user);
		setAttr("data", user);
		renderMsg();
	}
	
	@Before(LoginInterceptor.class)
	public void signup() {}
	
	@Before({POST.class, SignupValidator.class})
	public void doSignup() {
		// 查询用户是否存在
		User user = User.dao.getUserByUniqueId(getPara("email"));
		if(user != null){
			throw new BaseBussException("该邮箱已注册");
		}
		
		// 注册用户
		User regUser = new User();
		regUser.set("uid", UUIDUtil.getShortUuid());
		regUser.set("email",    getPara("email"));
		regUser.set("nickName", getPara("nickName"));
		regUser.set("password", HashKit.md5(getPara("password")));
		regUser.set("status","9");// 未激活
		regUser.save();
		setSessionAttr(Constants.SESSION_USER, regUser);
		
		// 生成token
		EmailToken token = new EmailToken();
		token.set("token",  UUID.randomUUID().toString());
		token.set("userid", regUser.get("id"));
		token.set("tokenType", "1");
		token.set("expiryDate", DateUtil.rollDateByDateUnit(new Date(), 1440, false, Calendar.MINUTE));
		token.save();
		
		// 发送注册邮件
		token.put("email", getPara("email"));
		enhance(MailSenderService.class).sendRegistrationEmail(token);
		
		setAttr("data", regUser);
		renderMsg();
	}
	
	@Before({Tx.class})
	public void active() {
		
		EmailToken token = EmailToken.dao.findFirst("select * from emailtoken where token = ?", getPara());
		if(token == null){
			throw new BaseBussException("激活链接参数错误");
		}
		if(token.getDate("expiryDate").before(new Date())){
			throw new BaseBussException("激活链接已过期");
		}
		if("1".equals(token.getStr("status"))){
			throw new BaseBussException("邮箱已激活");
		}
		
		token.set("status", "1").update();
		User.dao.findById(token.get("userid")).set("status", "1").update();
		
		redirect("/account/activeSuccess");
	}
	public void activeSuccess() {}
}
