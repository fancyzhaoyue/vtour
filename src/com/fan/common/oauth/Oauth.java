package com.fan.common.oauth;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.StrKit;

public abstract class Oauth {
	private String clientId;
	private String clientSecret;
	private String redirectUri;
	private String authURL;
	private String tokenURL;
	private String userURL;
	
	// 构造授权地址
	@SuppressWarnings("all")
	public String getAuthorizeUrl(String state) {
		Map param = new HashMap();
		param.put("client_id", clientId);
		param.put("redirect_uri", redirectUri);
		param.put("response_type", "code");
		if(StrKit.notBlank(state)){
			param.put("state", state);
		}
		return HttpKit.buildUrlWithQueryString(authURL, param);
	};
	
	// 通过Authorization Code获取Access Token
	@SuppressWarnings("all")
	public Map getAccessToken(String code){
		Map param = new HashMap();
		param.put("code", code);
		param.put("grant_type", "authorization_code");
		param.put("client_id",     clientId);
		param.put("client_secret", clientSecret);
		param.put("redirect_uri",  redirectUri);
		String result = HttpKit.post(tokenURL, JSONObject.toJSONString(param));
		
		Map token = new HashMap();
		try {
			JSONObject json = JSONObject.parseObject(result);
			if (null != json) {
				token = JSONObject.toJavaObject(json, HashMap.class);
			}
		} catch (Exception e) {
			Matcher m1 = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$").matcher(result);
			if (m1.find()) {
				token.put("access_token", m1.group(1));
				token.put("expires_in", m1.group(2));
				token.put("refresh_token", m1.group(3));
			} else {
				Matcher m2 = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)$").matcher(result);
				if (m2.find()) {
					token.put("access_token", m1.group(1));
					token.put("expires_in", m1.group(2));
				}
			}
		}
		return token;
	}
	
	@SuppressWarnings("rawtypes")
	public abstract Object getUser(Map token);

	@SuppressWarnings("rawtypes")
	public Object getUserInfo(String code){
		Object user = null;
		Map token = getAccessToken(code);
		if(null != token) {
			user = getUser(token);
		}
		return user;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public String getAuthURL() {
		return authURL;
	}

	public void setAuthURL(String authURL) {
		this.authURL = authURL;
	}

	public String getTokenURL() {
		return tokenURL;
	}

	public void setTokenURL(String tokenURL) {
		this.tokenURL = tokenURL;
	}

	public String getUserURL() {
		return userURL;
	}

	public void setUserURL(String userURL) {
		this.userURL = userURL;
	}

}
