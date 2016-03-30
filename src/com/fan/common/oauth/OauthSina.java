package com.fan.common.oauth;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.PropKit;

public class OauthSina extends Oauth{
	private String tokenInfoURL;
	private static OauthSina me = new OauthSina();
	
	public static OauthSina me() {
		return me;
	}
	public OauthSina(){
		setClientId(PropKit.get("sina.clientId"));
		setClientSecret(PropKit.get("sina.clientSecret"));
		setRedirectUri(PropKit.get("sina.redirectUri"));
		
		setAuthURL(PropKit.get("sina.authURL"));
		setTokenURL(PropKit.get("sina.tokenURL"));
		setUserURL(PropKit.get("sina.userURL"));
		tokenInfoURL = PropKit.get("sina.tokenInfoURL");
	}
	
	@SuppressWarnings("all")
	public Object getUser(Map token) {
		// 获取用户uid
		Map param = new HashMap();
		param.put("access_token", token.get("access_token"));
		JSONObject data = JSON.parseObject(HttpKit.get(tokenInfoURL, param));

		param.put("uid", data.get("uid"));

		// 获得用户信息
		JSONObject dataMap = JSON.parseObject(HttpKit.get(getUserURL(), param));
		return dataMap;
	}
}
