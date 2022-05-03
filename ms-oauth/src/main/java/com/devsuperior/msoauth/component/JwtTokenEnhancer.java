package com.devsuperior.msoauth.component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.devsuperior.msoauth.entities.User;

/**
 * JWT Content Enhancer
 *
 * @author Honghui [wanghonghui_work@163.com] 2021/3/16
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		
		Map<String, Object> info = new HashMap<>();
		
		// Set the user ID and NAME to the JWT
		info.put("userId", user.getId());
		info.put("userName", user.getName());
		info.put("userEmail",user.getEmail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}
}
