package com.devsuperior.msoauth.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsuperior.msoauth.api.CommonResult;


/**
 * Handle exceptions thrown by Oauth2 globally
 *
 * @author Honghui [wanghonghui_work@163.com] 2021/3/16
 */
@ControllerAdvice
public class Oauth2ExceptionHandler {
  @ResponseBody
  @ExceptionHandler(value = OAuth2Exception.class)
  public CommonResult<String> handleOauth2(OAuth2Exception e) {
    return CommonResult.failed(e.getMessage());
  }
}
