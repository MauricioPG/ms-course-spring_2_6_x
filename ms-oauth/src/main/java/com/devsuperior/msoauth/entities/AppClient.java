package com.devsuperior.msoauth.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.devsuperior.msoauth.constant.MessageConstant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  Adapted from 
 *  @author Honghui [wanghonghui_work@163.com] 2021/3/16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder(toBuilder = true)

public class AppClient implements ClientDetails {
	private static final long serialVersionUID = 1L;
	
	private String clientId;
	private String resourceIds;
	private Boolean secretRequire;
	private String clientSecret;
	private Boolean scopeRequire;
	private String scope;
	private String authorizedGrantTypes;
	private String webServerRedirectUri;
	private String authorities;
	private Integer accessTokenValidity;
	private Integer refreshTokenValidity;

	@Override
	public Set<String> getResourceIds() {
		return new HashSet<>(Arrays.asList(resourceIds.split(MessageConstant.SPLIT_COMMA)));
	}

	@Override
	public boolean isSecretRequired() {
		return secretRequire;
	}

	@Override
	public String getClientSecret() {
		return clientSecret;
	}

	@Override
	public boolean isScoped() {
		return scopeRequire;
	}

	@Override
	public Set<String> getScope() {
		return new HashSet<>(Arrays.asList(scope.split(MessageConstant.SPLIT_COMMA)));
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return new HashSet<>(Arrays.asList(authorizedGrantTypes.split(MessageConstant.SPLIT_COMMA)));
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return new HashSet<>(Arrays.asList(webServerRedirectUri.split(MessageConstant.SPLIT_COMMA)));
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		Arrays.asList(authorities.split(MessageConstant.SPLIT_COMMA))
				.forEach(auth -> collection.add((GrantedAuthority) () -> auth));
		return collection;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValidity;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValidity;
	}

	@Override
	public boolean isAutoApprove(String s) {
		return false;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		return null;
	}
}