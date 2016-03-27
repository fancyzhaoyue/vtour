package com.fan.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class CommonInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		
		controller.setAttr("webTitle", "virtual tour");
		controller.setAttr("webDescription", "virtual tour is 360 view");
		controller.setAttr("webKeywords", "virtual tour");
		
		inv.invoke();
	}

}
