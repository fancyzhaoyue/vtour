package test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fan.common.mail.MailSenderService;
import com.fan.common.util.DateUtil;
import com.fan.common.util.FreeMarkerTemplateUtils;
import com.fan.jfinal.model.EmailToken;
import com.jfinal.aop.Enhancer;
import com.jfinal.kit.PropKit;
import com.jfinal.render.FreeMarkerRender;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Test {
	public static void main(String[] args) {
		PropKit.use("sysconfigwin.properties");
		EmailToken token = new EmailToken();
		token.set("token",  UUID.randomUUID().toString());
		token.set("userid", "123123");
		token.set("tokenType", "1");
		token.set("expiryDate", DateUtil.rollDateByDateUnit(new Date(), 1440, false, Calendar.MINUTE));
		token.put("email", "fancyzhaoyue@163.com");
		Enhancer.enhance(MailSenderService.class).sendRegistrationEmail(token);
	}
}
