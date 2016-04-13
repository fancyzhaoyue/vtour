package com.fan.common.mail;

import java.io.IOException;
import com.fan.common.util.FreeMarkerTemplateUtils;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.render.FreeMarkerRender;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MailSenderService {

	private static String REGISTRATION_EMAIL_FTL = "/page/tmpl/registrationEmail.html";
	private static String VERIFICATION_EMAIL_FTL = "/page/tmpl/verificationEmail.html";
	private static String LOSTPASSWORD_EMAIL_FTL = "/page/tmpl/lostPasswordEmail.html";
	public void sendVerificationEmail(Model<?> token){
		
		sendVerificationEmail(VERIFICATION_EMAIL_FTL, PropKit.get("email.verificationSubject"),token);
	}

	public void sendRegistrationEmail(Model<?> token){
		
		sendVerificationEmail(REGISTRATION_EMAIL_FTL, PropKit.get("email.registerSubject"),token);
	}
	
	public void sendLostPasswordEmail(Model<?> token){
		
		sendVerificationEmail(LOSTPASSWORD_EMAIL_FTL, PropKit.get("email.lostPasswordSubject"),token);
	}
	
	private void sendVerificationEmail(String ftl, String subject ,Model<?> token){
		try {
			Template tmpl = FreeMarkerRender.getConfiguration().getTemplate(ftl);
			String body = FreeMarkerTemplateUtils.processTemplateIntoString(tmpl, token);
			MailSender.sendAsync(subject, body, token.getStr("email"));
		} catch (IOException e) {
			throw new RuntimeException("Flt not found", e);
		} catch (TemplateException e) {
			throw new RuntimeException("Template process error", e);
		}
	}
}
