package com.fan.jfinal.controller;


import com.alibaba.fastjson.JSONObject;
import com.fan.common.Constants;
import com.fan.jfinal.base.BaseController;
import com.fan.jfinal.model.User;
import com.fan.jfinal.model.UserOAuth;
import com.fan.oauth.OAuthQQ;
import com.fan.oauth.OAuthSina;

public class OAuthController extends BaseController {
	
	public void index() {
	}
	
	
	public void login() {
		String url = null;
		String oauthName = getPara();
		if("qqconn".equals(oauthName)){
			url = OAuthQQ.instance().getAuthorizationUrl("123");
		}else if("sina".equals(oauthName)){
			url = OAuthSina.instance().getAuthorizationUrl("123");
		}
		redirect(url);
	}
	
	public void callback() {
		JSONObject userInfo = null;
		String oauthName = getPara();
		if("qqconn".equals(oauthName)){
			userInfo = (JSONObject)OAuthQQ.instance().getUserInfo(getPara("code"));
		}else if("sina".equals(oauthName)){
			userInfo = (JSONObject)OAuthSina.instance().getUserInfo(getPara("code"));
		}
	
		User user = User.dao.findFirst("select a.* from user a, useroauth b where a.id = b.userid and b.oauthuid = ?", userInfo.get("uid"));
		if(user == null){
			user = new User();
			user.set("nickName", userInfo.get("nickName"));
			user.save();
			user.set("uid", user.get("id"));
			user.update();
			
			UserOAuth userOAuth = getModel(UserOAuth.class);
			userOAuth.set("userid", user.get("id"));
			userOAuth.set("oauthName", oauthName);
			userOAuth.set("oauthUid", userInfo.get("uid"));
			userOAuth.set("accessToken", userInfo.get("accessToken"));
			userOAuth.set("expiresIn", userInfo.get("expiresIn"));
			userOAuth.save();
		}
		
		user.remove("password");
		setSessionAttr(Constants.SESSION_USER, user);
		
		redirect("/");
	}
}
