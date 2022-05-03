package com.devsuperior.msoauth.api;

/**
 * Some common API opcodes are enumerated
 *
 * @author Honghui [wanghonghui_work@163.com] 2021/3/16
 */
public enum ResultCode implements IErrorCode {
	SUCCESS(200, "Oauth - Successful operation"), 
	FAILED(500, "Oauth - Operation failed"),
	VALIDATE_FAILED(404, "Oauth - Parameter test failed"),
	UNAUTHORIZED(401, "Oauth - Not logged in yet or the token has expired"),
	FORBIDDEN(403, "Oauth - No relevant permissions");

	private final long code;
	private final String message;

	ResultCode(long code, String message) {
		this.code = code;
		this.message = message;
	}

	public long getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
