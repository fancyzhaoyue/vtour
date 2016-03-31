package com.fan.jfinal.config;

import com.fan.jfinal.controller.IndexController;
import com.fan.jfinal.controller.OAuthController;
import com.fan.jfinal.controller.PublishController;
import com.fan.jfinal.controller.TourController;
import com.fan.jfinal.controller.UserController;
import com.fan.jfinal.interceptor.CommonInterceptor;
import com.fan.jfinal.model.Pano;
import com.fan.jfinal.model.User;
import com.fan.jfinal.model.UserOAuth;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;

public class BaseConfig extends JFinalConfig {

	public void configConstant(Constants me) {
		PropKit.use("sysconfigwin.properties");
		me.setDevMode(PropKit.getBoolean("devmode"));
		me.setBaseViewPath("/page");
	}

	public void configRoute(Routes me) {
		me.add("/", IndexController.class);
		me.add("/u", UserController.class);
		me.add("/vt", TourController.class);
		me.add("/oauth", OAuthController.class);
		me.add("/publish", PublishController.class);
	}

	public void configPlugin(Plugins me) {
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("database.url"), PropKit.get("database.user"), PropKit.get("database.password")); 
		me.add(c3p0Plugin);
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin); 
		me.add(arp);
		
		arp.addMapping("user", User.class);
		arp.addMapping("useroauth", UserOAuth.class);
		arp.addMapping("pano", Pano.class);
	}

	public void configInterceptor(Interceptors me) {
		
		me.add(new CommonInterceptor());
		me.add(new SessionInViewInterceptor());
		
	}

	public void configHandler(Handlers me) {

		me.add(new ContextPathHandler());

	}

}
