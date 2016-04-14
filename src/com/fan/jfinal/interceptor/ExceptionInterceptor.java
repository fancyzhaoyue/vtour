package com.fan.jfinal.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.fan.core.BaseBussException;
import com.fan.jfinal.base.BaseController;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.StrKit;

public class ExceptionInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		BaseController controller = (BaseController )inv.getController();
		HttpServletRequest request = controller.getRequest();
		try{
			inv.invoke();
		}catch(Exception e){
			String message = formatException(e);
			if("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))){
				controller.renderMsg(1, message);
			}else{
				controller.setAttr("message", message);
				controller.render("/page/error/errorPage.html");
			}
		}finally{
			
		}
		
	}
	private static String formatException(Exception e){
		String message = null;
		if(e instanceof BaseBussException){
			message = e.getMessage();
			if(StrKit.isBlank(message)){
				message = e.toString();
			}
		}
		if(StrKit.isBlank(message)){
			message = "系统繁忙请稍后再试";
		}
		return message;
	}

}
