package test;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class TestCommonMail {
	public static void main(String[] args) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.163.com");
		email.setSmtpPort(25);
		email.setAuthenticator(new DefaultAuthenticator("fzyue163@163.com", "Fanzhaoyue0214"));
		email.setSSLOnConnect(true);
		email.setFrom("fzyue163@163.com");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("fancyzhaoyue@163.com");
		email.send();
	}
}
