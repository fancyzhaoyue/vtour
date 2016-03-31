package com.fan.oauth;

import java.util.HashMap;
import java.util.Map;

import com.fan.oauth.extractor.OAuthAccessTokenJsonExtractor;
import com.fan.oauth.extractor.TokenExtractor;
import com.fan.oauth.model.OAuthAccessToken;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.StrKit;

@SuppressWarnings("all")
public abstract class OAuth {
	private String clientId;
	private String clientSecret;
	private String redirectUri;
	private String scope;
	private String responseType;
	
	private String authorizeUrl;
	private String tokenUrl;
	private String userInfoUrl;

	
	
	public TokenExtractor getAccessTokenExtractor() {
        return OAuthAccessTokenJsonExtractor.instance();
    }
	public String getAccessTokenVerb(){
		return "post";
	}
	
	public String getAuthorizationUrl(String state){
		Map param = new HashMap();
		param.put("client_id", getClientId());
		param.put("redirect_uri", getRedirectUri());
		param.put("response_type", getResponseType());
		if(StrKit.notBlank(state)){
			param.put("state", state);
		}
		return HttpKit.buildUrlWithQueryString(getAuthorizeUrl(), param);
	};
	
	public OAuthAccessToken getAccessToken(String code){
		Map param = new HashMap();
		param.put("code", code);
		param.put("grant_type", "authorization_code");
		param.put("client_id",     getClientId());
		param.put("client_secret", getClientSecret());
		param.put("redirect_uri",  getRedirectUri());
		
		String response = null;
		if("get".equals(getAccessTokenVerb())){
			response = HttpKit.get(tokenUrl, param);
		}else{
			response = HttpKit.post(tokenUrl, HttpKit.map2Url(param));
		}
		return getAccessTokenExtractor().extract(response);
	}
	public abstract Object getUserInfo(String code);
	
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

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	public String getAuthorizeUrl() {
		return authorizeUrl;
	}
	public void setAuthorizeUrl(String authorizeUrl) {
		this.authorizeUrl = authorizeUrl;
	}
	public String getTokenUrl() {
		return tokenUrl;
	}
	public void setTokenUrl(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}
	public String getUserInfoUrl() {
		return userInfoUrl;
	}
	public void setUserInfoUrl(String userInfoUrl) {
		this.userInfoUrl = userInfoUrl;
	}
}
