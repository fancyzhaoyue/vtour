package com.fan.jfinal.interceptor;

import com.fan.common.Constants;
import com.fan.jfinal.model.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class LoginInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		User user = (User)controller.getSessionAttr(Constants.SESSION_USER);
		if(user == null){
			controller.redirect("/");
		}else{
			inv.invoke();
		}
	}

}
