package com.fan.common.mail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class MailSender {
	
	private static ExecutorService executor = null;
	
	public static ExecutorService getExecutor() {
		if (executor == null) {
			executor = Executors.newCachedThreadPool();
		}
		return executor;
	}
	/**
	 * @param subject    主题
	 * @param body       内容
	 * @param recipients 收件人
	 */
	public static void sendAsync(final String subject, final String body, final String... recipients){
		Runnable task = new Runnable() {
			public void run() {
				send(subject, body, recipients);
			}
		};
		getExecutor().execute(task);
	}
	/**
	 * @param subject    主题
	 * @param body       内容
	 * @param recipients 收件人
	 */
	public static void send(String subject, String body, String... recipients) {
		HtmlEmail email = new HtmlEmail();
		try {
			email.setCharset("UTF-8");
			email.setHostName("smtp.163.com");
			email.setSmtpPort(25);
			email.setSSLOnConnect(false);
			email.setStartTLSEnabled(false);
			email.setDebug(true);
			email.setAuthentication("fzyue163@163.com", "Fanzhaoyue0214");
			email.setFrom("fzyue163@163.com");
			email.setSubject(subject);
			email.addTo(recipients);
			email.setHtmlMsg(body);
			email.send();
		} catch (EmailException e) {
			throw new RuntimeException("Unabled to send email", e);
		}
	}
	public static void main(String[] args) {
		MailSender.sendAsync("Test", "<html><body><h1>hello</h1></body></html>", "fancyzhaoyue@163.com");
		MailSender.send("Test", "<html><body><h1>hello</h1></body></html>", "fancyzhaoyue@163.com");
	}

}
