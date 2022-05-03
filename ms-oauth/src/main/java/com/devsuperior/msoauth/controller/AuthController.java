package com.devsuperior.msoauth.controller;

import java.security.Principal;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.msoauth.api.CommonResult;
import com.devsuperior.msoauth.dto.Oauth2TokenDto;
import com.devsuperior.msoauth.services.UserServiceImpl;

/**
 * Custom Oauth2 get token interface
 *
 * @author Honghui [wanghonghui_work@163.com] 2021/3/16
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {
	
	@Autowired
	private UserServiceImpl userService;

	private final TokenEndpoint tokenEndpoint;

	public AuthController(TokenEndpoint tokenEndpoint) {
		this.tokenEndpoint = tokenEndpoint;
	}
	
	/**
	 * Oauth2 login authentication
	 */
	@PostMapping("/token")
	// @formatter:off
	public CommonResult<Oauth2TokenDto> postAccessToken(
			Principal principal, 
			@RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException	{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails appDetails = (UserDetails) authentication.getPrincipal();
		UserDetails userDetails = userService.loadUserByUsername(parameters.get("username"));
		
		// Modifying authorities before token creation, so the token itself has correct values
		UsernamePasswordAuthenticationToken pr = new UsernamePasswordAuthenticationToken(principal, appDetails, userDetails.getAuthorities());
		OAuth2AccessToken oAuth2AccessToken = tokenEndpoint
												.postAccessToken(pr, parameters)
												.getBody();
		Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
				.token(Objects.requireNonNull(oAuth2AccessToken).getValue())
				.refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
				.expiresIn(oAuth2AccessToken.getExpiresIn())
				.tokenHead("Bearer ")
				.userId((Long)oAuth2AccessToken.getAdditionalInformation().get("userId"))
				.userName((String)oAuth2AccessToken.getAdditionalInformation().get("userName"))
				.userEmail((String)oAuth2AccessToken.getAdditionalInformation().get("userEmail"))
				.authorities(userDetails.getAuthorities().stream().map(x -> (String) x.getAuthority()).collect(Collectors.toList()))
				.build();
		return CommonResult.success(oauth2TokenDto);
	}
	// @ formatter:on

}
