package com.fan.jfinal.config;

import com.fan.jfinal.controller.IndexController;
import com.fan.jfinal.controller.UserController;
import com.fan.jfinal.interceptor.CommonInterceptor;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;

public class BaseConfig extends JFinalConfig {

	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setBaseViewPath("/page");
	}

	public void configRoute(Routes me) {
		me.add("/", IndexController.class);
		me.add("/u", UserController.class);
	}

	public void configPlugin(Plugins me) {

	}

	public void configInterceptor(Interceptors me) {
		
		me.add(new CommonInterceptor());
		
	}

	public void configHandler(Handlers me) {

		me.add(new ContextPathHandler());

	}

}
