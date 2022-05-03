package com.devsuperior.msapigateway.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsuperior.msapigateway.api.CommonResult;

/**
 * Handle exceptions thrown by Oauth2 globally
 *
 * @author Honghui [wanghonghui_work@163.com] 2021/3/16
 */
@ControllerAdvice
public class CustomExceptionHandler extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public static CommonResult<String> handleException(String message) {
		return CommonResult.failed(message);
	}
}
