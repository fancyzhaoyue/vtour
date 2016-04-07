package com.fan.common.mail;

import java.util.Properties;

import javax.mail.Session;

public class Test {
	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "mail.smallpay.com");
		prop.put("mail.smtp.port", "587");
		Session session = Session.getInstance(prop);
	}
}
