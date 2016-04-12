package com.fan.jfinal.controller;


import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.fan.common.Constants;
import com.fan.common.mail.MailAuthenticator;
import com.fan.common.util.DateUtil;
import com.fan.common.util.ShaUtil;
import com.fan.core.BaseBussException;
import com.fan.jfinal.base.BaseController;
import com.fan.jfinal.model.EmailToken;
import com.fan.jfinal.model.User;
import com.fan.jfinal.validator.LoginValidator;
import com.fan.jfinal.validator.SignupValidator;
import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.NoUrlPara;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.HashKit;

public class IndexController extends BaseController {
	
	@Before(NoUrlPara.class)
	public void index()  {}
	
	@Before(LoginValidator.class)
	public void login() {
		// 查询用户是否存在
		User user = User.dao.getUserByUniqueId(getPara("email"));
		if(user == null){
			throw new BaseBussException("用户不存在");
		}
		
		// 验密
		String shaPwd = ShaUtil.encode("SHA", getPara("password"));
		if(!shaPwd.equals(user.get("password"))){
			throw new BaseBussException("密码错误");
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
		user.set("status","9");// 未激活
		user.save();
		
		// 根据id生成uid
		user.set("uid", user.get("id"));
		user.update();
		setSessionAttr(Constants.SESSION_USER, user);
		
		// 生成token
		EmailToken token = getModel(EmailToken.class);
		token.set("token", UUID.randomUUID().toString());
		token.set("userid", user.get("id"));
		token.set("expiryDate", DateUtil.rollDateByDateUnit(new Date(), 1440, false, Calendar.MINUTE));
		token.set("tokenType", "1");
		token.save();
		
		// 发送邮件
		Properties props = new Properties();
		// SMTP服务器地址
		props.put("mail.smtp.host", "smtp.163.com");
		// SMTP服务器端口
		props.put("mail.stmp.port", 25);
		// SMTP服务器是否需要用户认证
		props.put("mail.smtp.auth", "true");
		
		MailAuthenticator authenticator = new MailAuthenticator("fzyue163@163.com", "Fanzhaoyue0214");
		Session session = Session.getInstance(props, authenticator);
		
		MimeMessage message = new MimeMessage(session);
		try {
			// 主题
			message.setSubject("欢迎加入VRena，请验证登录邮箱");
			// 内容
			String content = "<h1>欢迎加入VRena</h21><br>请点击以下链接验证你的邮箱地址<br>http://www.11kankan.com/activate/" + HashKit.sha1(token.getStr("token"));
			message.setContent(content, "text/html;charset=utf-8");
			// 发件人
			message.setFrom(new InternetAddress(authenticator.getUserName()));
			// 收件人
			message.setRecipient(RecipientType.TO, new InternetAddress(user.getStr("email")));
			// 发送
			Transport.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	public void activate() {
		EmailToken token = EmailToken.dao.findFirst("select * from emailtoken where token = ?", getPara());
		if(token != null){
			
		}
		
		
	}
}
