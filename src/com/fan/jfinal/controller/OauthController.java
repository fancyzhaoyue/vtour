package com.fan.jfinal.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fan.common.oauth.OauthQQ;
import com.fan.common.oauth.OauthSina;
import com.fan.jfinal.base.BaseController;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.PropKit;

public class OauthController extends BaseController {
	
	public void index() {
	}
	
	
	public void login() {
		String url = "";
		String oauthType = getPara();
		if("qqconn".equals(oauthType)){
			url = OauthQQ.me().getAuthorizeUrl("123");
		}else if("sina".equals(oauthType)){
			url = OauthSina.me().getAuthorizeUrl("123");
		}
		redirect(url);
	}
	
	public void callback() {
		String oauthType = getPara();
		if("qqconn".equals(oauthType)){
			JSONObject dataMap = (JSONObject)OauthQQ.me().getUserInfo(getPara("code"));
			renderText(dataMap.toJSONString());
		}else if("sina".equals(oauthType)){
			JSONObject dataMap = (JSONObject)OauthSina.me().getUserInfo(getPara("code"));
			renderText(dataMap.toJSONString());
		}
	}
}
