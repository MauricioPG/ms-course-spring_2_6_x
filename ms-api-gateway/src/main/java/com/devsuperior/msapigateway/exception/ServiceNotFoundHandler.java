package com.devsuperior.msapigateway.exception;

import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ServiceNotFoundHandler extends RuntimeException {
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public String handleUnavailableService(NotFoundException e) {
		return "Service Unavailable...";
	}
}