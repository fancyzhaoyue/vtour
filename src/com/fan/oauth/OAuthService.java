package com.fan.oauth;


import com.fan.oauth.model.OAuthAccessToken;

public class OAuthService {
	
	private final OAuth oauth;
	public OAuthService(OAuth oauth){
		this.oauth = oauth;
	}
	public String getAuthorizationUrl(String state){
		return oauth.getAuthorizationUrl(state);
	}
	
	public OAuthAccessToken getAccessToken(String code){
		return null;
	}
}
