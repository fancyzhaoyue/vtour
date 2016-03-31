package com.fan.oauth;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fan.oauth.extractor.OAuthAccessTokenJsonExtractor;
import com.fan.oauth.extractor.TokenExtractor;
import com.fan.oauth.model.OAuthAccessToken;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.PropKit;

public class OAuthSina extends OAuth{
	
	private static String AUTHORIZE_URL = "https://api.weibo.com/oauth2/authorize";
	private static String TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
	private static String USER_INFO_URL = "https://api.weibo.com/2/users/show.json";
	private static String TOKEN_INFO_URL = "https://api.weibo.com/oauth2/get_token_info";
	
	public OAuthSina(){
		setClientId(PropKit.get("sina.clientId"));
		setClientSecret(PropKit.get("sina.clientSecret"));
		setRedirectUri(PropKit.get("sina.redirectUri"));
		
		setResponseType("code");
		setAuthorizeUrl(AUTHORIZE_URL);
		setTokenUrl(TOKEN_URL);
	}
	
	private static OAuthSina INSTANCE = new OAuthSina();
	
	public static OAuthSina instance() {
		return INSTANCE;
	}
		
	public String getAccessTokenVerb() {
		return "post";
	}
	
	public TokenExtractor getAccessTokenExtractor() {
        return OAuthAccessTokenJsonExtractor.instance();
    }

	public Object getUserInfo(String code) {
		
		OAuthAccessToken accessToken = getAccessToken(code);
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("access_token", accessToken.getAccessToken());
		String result = HttpKit.post(TOKEN_INFO_URL, HttpKit.map2Url(param));
		
		JSONObject data = JSON.parseObject(result);
				
		param.put("uid", data.getString("uid"));
		
		JSONObject user = JSON.parseObject(HttpKit.get(USER_INFO_URL, param));
		user.put("uid", param.get("uid"));
		user.put("accessToken", accessToken.getAccessToken());
		user.put("expiresIn", accessToken.getExpiresIn());
		user.put("nickName", user.get("name"));
		return user;
	}
}
