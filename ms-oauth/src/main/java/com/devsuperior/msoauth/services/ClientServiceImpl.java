package com.devsuperior.msoauth.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devsuperior.msoauth.constant.MessageConstant;
import com.devsuperior.msoauth.entities.AppClient;

/**
 * Client management business class
 *
 * @author Honghui [wanghonghui_work@163.com] 2021/3/18
 */
@Service
public class ClientServiceImpl implements ClientDetailsService {

	@Value("${token.validity}")
	private int validity;
	
	@Value("${token.refresh}")
	private int refresh;
	
	@Value("${client.id}")
	private String clientId;
	
	@Value("${client.secret}")
	private String clientSecret;
	
	private List<AppClient> clientList;
	private final PasswordEncoder passwordEncoder;

	public ClientServiceImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@PostConstruct
	public void initData() {
		String encodedSecret = passwordEncoder.encode(clientSecret);
		clientList = new ArrayList<>();
		// @formatter:off
		// 1、password mode
		clientList.add(AppClient.builder()
				.clientId(clientId)
				.resourceIds("oauth2-resource")
				.secretRequire(false)
				.clientSecret(encodedSecret)
				.scopeRequire(false).scope("all")
				.authorizedGrantTypes("password,refresh_token")
				.authorities("USER")
				.accessTokenValidity(validity)
				.refreshTokenValidity(refresh)
				.build());
		// 2、Authorization code mode -- not used, so, hard coded
		clientList.add(AppClient.builder()
				.clientId("client-app-2")
				.resourceIds("oauth2-resource2")
				.secretRequire(false)
				.clientSecret(clientSecret)
				.scopeRequire(false).scope("all")
				.authorizedGrantTypes("authorization_code,refresh_token")
				.webServerRedirectUri("https://www.baidu.com")
				.authorities("USER")
				.accessTokenValidity(validity)
				.refreshTokenValidity(refresh)
				.build());
		
		// @formatter:on
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		// @formatter:off
		List<AppClient> findClientList = clientList.stream().filter(item 
    		-> item.getClientId().equals(clientId)).collect(Collectors.toList());
		//@formatter:on
		if (findClientList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, MessageConstant.NOT_FOUND_CLIENT);
		}
		return findClientList.get(0);
	}
}