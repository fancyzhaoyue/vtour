package com.fan.oauth.extractor;

import com.fan.oauth.model.OAuthAccessToken;

public class OAuthAccessTokenJsonExtractor extends TokenExtractor{
	
	private static final String ACCESS_TOKEN_REGEX = "\"access_token\"\\s*:\\s*\"(\\S*?)\"";
    private static final String TOKEN_TYPE_REGEX = "\"token_type\"\\s*:\\s*\"(\\S*?)\"";
    private static final String EXPIRES_IN_REGEX = "\"expires_in\"\\s*:\\s*\"?(\\d*?)\"?\\D";
    private static final String REFRESH_TOKEN_REGEX = "\"refresh_token\"\\s*:\\s*\"(\\S*?)\"";
    private static final String SCOPE_REGEX = "\"scope\"\\s*:\\s*\"(\\S*?)\"";
    
    private static OAuthAccessTokenJsonExtractor INSTANCE = new OAuthAccessTokenJsonExtractor();
    
    public static OAuthAccessTokenJsonExtractor instance() {
		return INSTANCE;
	}
	public OAuthAccessToken extract(String response) {
		final String accessToken = extractParameter(response, ACCESS_TOKEN_REGEX);
        final String tokenType = extractParameter(response, TOKEN_TYPE_REGEX);
        final String refreshToken = extractParameter(response, REFRESH_TOKEN_REGEX);
        final String scope = extractParameter(response, SCOPE_REGEX);
        final String expiresInString = extractParameter(response, EXPIRES_IN_REGEX);
        Integer expiresIn;
        try {
            expiresIn = expiresInString == null ? null : Integer.valueOf(expiresInString);
        } catch (NumberFormatException nfe) {
            expiresIn = null;
        }
        return new OAuthAccessToken(accessToken, tokenType, expiresIn, refreshToken, scope, response);
	}

}
