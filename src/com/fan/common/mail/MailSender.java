package com.fan.common.mail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.jfinal.kit.PropKit;

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
			email.setHostName(PropKit.get("mail.smtp.host"));
			email.setSmtpPort(PropKit.getInt("mail.stmp.port"));
			email.setSSLOnConnect(PropKit.getBoolean("mail.smtp.ssl"));
			email.setStartTLSEnabled(PropKit.getBoolean("mail.smtp.tls"));
			email.setDebug(PropKit.getBoolean("mail.debug"));
			email.setAuthentication(PropKit.get("mail.user"), PropKit.get("mail.password"));
			email.setFrom(PropKit.get("mail.user"),PropKit.get("mail.name"));
			email.setSubject(subject);
			email.addTo(recipients);
			email.setHtmlMsg(body);
			email.send();
		} catch (EmailException e) {
			throw new RuntimeException("Unabled to send email", e);
		}
	}

}
