package com.fan.jfinal.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fan.jfinal.base.BaseController;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.PropKit;

public class OauthController2 extends BaseController {
	
	public void index() {
	}
	
	
	public void login() {
		String oauthType = getPara();
		String url = "";
		if("qqconn".equals(oauthType)){
			String authURL = "https://graph.qq.com/oauth2.0/authorize";
			Map<String, String> param = new HashMap<String, String>();
			param.put("state", "123123");
			param.put("client_id", PropKit.get("client_id.qq"));
			param.put("redirect_uri", PropKit.get("redirect_uri.qq"));
			param.put("response_type", "code");
			
			url = HttpKit.buildUrlWithQueryString(authURL, param);
		}else if("sina".equals(oauthType)){
			url = "";
		}
		redirect(url);
	}
	
	public void callback() {
		//获取Authorization Code
		
		String oauthType = getPara();
		
		if("qqconn".equals(oauthType)){
			//通过Authorization Code获取Access Token
			String tokenURL = "https://graph.qq.com/oauth2.0/token";
			Map<String, String> param = new HashMap<String, String>();
			param.put("grant_type", "authorization_code");
			param.put("client_id", PropKit.get("client_id.qq"));
			param.put("client_secret", PropKit.get("client_secret.qq"));
			param.put("redirect_uri", PropKit.get("redirect_uri.qq"));
			param.put("code", getPara("code"));
			String result = HttpKit.get(tokenURL, param);
			
			String accessToken = "";
			String expiresIn = "";
			try {
				JSONObject json = JSONObject.parseObject(result);
				if (null != json) {
					accessToken = json.getString("access_token");
				}
			} catch (Exception e) {
				Matcher m = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$").matcher(result);
				if (m.find()) {
					accessToken = m.group(1);
					expiresIn = m.group(2);
				} else {
					Matcher m2 = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)$").matcher(result);
					if (m2.find()) {
						accessToken = m2.group(1);
						expiresIn = m.group(2);
					}
				}
			}
			
			//获取用户OpenID
			String openIdURL = "https://graph.qq.com/oauth2.0/me";
			Map<String, String> map = new HashMap<String, String>();
			map.put("access_token", accessToken);
			String result1 = HttpKit.get(openIdURL, map);
			String openid = "";
			Matcher m = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(result1);
			if (m.find()){				
				openid = m.group(1);
			}
			
			//获得用户信息
			String userInfoURL = "https://graph.qq.com/user/get_user_info";
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("oauth_consumer_key", PropKit.get("client_id.qq"));
			map2.put("access_token", accessToken);
			map2.put("openid", openid);
			
			String result2 = HttpKit.get(userInfoURL, map2);
			
			System.out.println(result2);
			JSONObject dataMap = JSON.parseObject(result2);
			renderText(dataMap.toJSONString());
			
			
		}
	}
}
