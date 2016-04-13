package com.fan.jfinal.controller;

import com.fan.common.Constants;
import com.fan.jfinal.base.BaseController;
import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.NoUrlPara;

public class IndexController extends BaseController {
	
	@Before(NoUrlPara.class)
	public void index() {}
	
	public void logout() {
		removeSessionAttr(Constants.SESSION_USER);
		redirect("/");
	}
}
