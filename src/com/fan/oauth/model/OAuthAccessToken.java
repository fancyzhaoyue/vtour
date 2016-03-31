package com.fan.oauth.model;

public class OAuthAccessToken {
	private String accessToken;
	private String tokenType;
	private Integer expiresIn;
	private String refreshToken;
	private String scope;
	private String rawResponse;

	public OAuthAccessToken(String accessToken) {
        this(accessToken, null);
    }

    public OAuthAccessToken(String accessToken, String rawResponse) {
        this(accessToken, null, null, null, null, rawResponse);
    }
    
	public OAuthAccessToken(String accessToken, String tokenType, Integer expiresIn, String refreshToken, String scope,
            String rawResponse){
		this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.rawResponse = rawResponse;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public String getScope() {
		return scope;
	}

	public String getRawResponse() {
		return rawResponse;
	}
	
}
