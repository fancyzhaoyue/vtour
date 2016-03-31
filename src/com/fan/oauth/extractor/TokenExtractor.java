package com.fan.oauth.extractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fan.oauth.model.OAuthAccessToken;

public abstract class TokenExtractor {
	
	public abstract OAuthAccessToken extract(String response);
	
	public String extractParameter(String response,String regex){
		final Matcher matcher = Pattern.compile(regex).matcher(response);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
	}
}
