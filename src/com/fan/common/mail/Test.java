package com.fan.common.mail;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class Test {
	public static void main(String[] args)  {
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
			message.setSubject("注册邮件");
			// 内容
			message.setContent("hello", "text/html;charset=utf-8");
			// 发件人
			message.setFrom(new InternetAddress(authenticator.getUserName()));
			// 收件人
			message.setRecipient(RecipientType.TO, new InternetAddress("fancyzhaoyue@163.com"));
			// 发送
			Transport.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
}
