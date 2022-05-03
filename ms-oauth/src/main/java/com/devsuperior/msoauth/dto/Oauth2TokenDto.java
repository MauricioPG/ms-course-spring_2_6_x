package com.devsuperior.msoauth.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Oauth2 obtains Token and returns information encapsulation
 *
 * @author Honghui [wanghonghui_work@163.com] 2021/3/16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Oauth2TokenDto {
  /**
   * access token
   */
  private String token;
  /**
   * refresh token
   */
  private String refreshToken;
  /**
   * access token header prefix
   */
  private String tokenHead;
  /**
   * Effective time (seconds)
   */
  private int expiresIn;
  
  private Long userId;
  
  private String userName;
  
  private String userEmail;
  
  private List<String> authorities;
}
