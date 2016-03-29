package com.fan.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

public class CommonInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		
		
		controller.setAttr("webName", PropKit.get("webName"));
		controller.setAttr("webKeywords", PropKit.get("webKeywords"));
		controller.setAttr("webDescription", PropKit.get("webDescription"));
		
		inv.invoke();
	}

}
