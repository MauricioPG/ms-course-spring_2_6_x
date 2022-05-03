package com.devsuperior.msoauth.config;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import com.devsuperior.msoauth.component.JwtTokenEnhancer;
import com.devsuperior.msoauth.services.ClientServiceImpl;
import com.devsuperior.msoauth.services.UserServiceImpl;

import lombok.AllArgsConstructor;

/**
 * Authentication server configuration
 *
 * @author Honghui [wanghonghui_work@163.com] 2021/3/16
 */
@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {
	
	private final UserServiceImpl userService;
	private final ClientServiceImpl clientService;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenEnhancer jwtTokenEnhancer;
	
	private final String JKS_NAME = "dev.jks";
	private final String JKS_ALIAS = "msdev";
	private final String JKS_PASS = "dev123";

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientService);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		List<TokenEnhancer> delegates = new ArrayList<>();
		delegates.add(jwtTokenEnhancer);
		delegates.add(accessTokenConverter());
		enhancerChain.setTokenEnhancers(delegates); // Configure the content enhancer for JWT
		endpoints.authenticationManager(authenticationManager).userDetailsService(userService) // Configure the service
																								// to load user
																								// information
				.accessTokenConverter(accessTokenConverter()).tokenEnhancer(enhancerChain);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) {
		security.allowFormAuthenticationForClients();
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setKeyPair(keyPair());
		return jwtAccessTokenConverter;
	}

	@Bean
	public KeyPair keyPair() {
		// Get the key pair from the certificate on the classpath
		KeyStoreKeyFactory keyStoreKeyFactory = 
				new KeyStoreKeyFactory(new ClassPathResource(JKS_NAME),
				JKS_PASS.toCharArray());
		return keyStoreKeyFactory.getKeyPair(JKS_ALIAS, JKS_PASS.toCharArray());
	}

}
