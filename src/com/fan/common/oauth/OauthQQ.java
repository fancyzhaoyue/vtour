package com.fan.common.oauth;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.PropKit;

public class OauthQQ extends Oauth{
	private String tokenInfoURL;
	private static OauthQQ me = new OauthQQ();
	
	public static OauthQQ me() {
		return me;
	}
	public OauthQQ(){
		setClientId(PropKit.get("qq.clientId"));
		setClientSecret(PropKit.get("qq.clientSecret"));
		setRedirectUri(PropKit.get("qq.redirectUri"));
		
		setAuthURL(PropKit.get("qq.authURL"));
		setTokenURL(PropKit.get("qq.tokenURL"));
		setUserURL(PropKit.get("qq.userURL"));
		tokenInfoURL = PropKit.get("qq.tokenInfoURL");
	}
	
	@SuppressWarnings("all")
	public Object getUser(Map token) {
		// 获取用户OpenID
		Map param = new HashMap();
		param.put("access_token", token.get("access_token"));
		String result = HttpKit.get(tokenInfoURL, param);

		Matcher m = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(result);
		if (m.find()){				
			param.put("openid", m.group(1));
		}
		param.put("oauth_consumer_key", getClientId());
		
		// 获得用户信息
		JSONObject dataMap = JSON.parseObject(HttpKit.get(getUserURL(), param));
		return dataMap;
	}
}
