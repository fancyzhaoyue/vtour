package com.fan.oauth;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fan.oauth.extractor.OAuthAccessTokenExtractor;
import com.fan.oauth.extractor.TokenExtractor;
import com.fan.oauth.model.OAuthAccessToken;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.PropKit;

public class OAuthQQ extends OAuth{
	
	private static String AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize";
	private static String TOKEN_URL = "https://graph.qq.com/oauth2.0/token";
	private static String USER_INFO_URL = "https://graph.qq.com/user/get_user_info";
	private static String TOKEN_INFO_URL = "https://graph.qq.com/oauth2.0/me";
	
	public OAuthQQ(){
		setClientId(PropKit.get("qq.clientId"));
		setClientSecret(PropKit.get("qq.clientSecret"));
		setRedirectUri(PropKit.get("qq.redirectUri"));
		
		setResponseType("code");
		setAuthorizeUrl(AUTHORIZE_URL);
		setTokenUrl(TOKEN_URL);
	}
	
	private static OAuthQQ INSTANCE = new OAuthQQ();
	
	public static OAuthQQ instance() {
		return INSTANCE;
	}
		
	public String getAccessTokenVerb() {
		return "get";
	}
	
	public TokenExtractor getAccessTokenExtractor() {
        return OAuthAccessTokenExtractor.instance();
    }

	public Object getUserInfo(String code) {
		
		OAuthAccessToken accessToken = getAccessToken(code);
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("access_token", accessToken.getAccessToken());
		String result = HttpKit.get(TOKEN_INFO_URL, param);
		
		Matcher m = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(result);
		if (m.find()){				
			param.put("openid", m.group(1));
		}
		param.put("oauth_consumer_key", getClientId());
		
		JSONObject user = JSON.parseObject(HttpKit.get(USER_INFO_URL, param));
		user.put("uid", param.get("openid"));
		user.put("accessToken", accessToken.getAccessToken());
		user.put("expiresIn", accessToken.getExpiresIn());
		user.put("nickName", user.get("nickname"));
		return user;
	}
}
