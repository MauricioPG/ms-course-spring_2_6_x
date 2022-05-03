package com.devsuperior.msapigateway.api;

/**
 * Generic return object
 *
 * @author Honghui [wanghonghui_work@163.com] 2021/3/16
 */
public class CommonResult<T> {
	private long code;
	private String message;
	private T data;

	protected CommonResult() {
	}

	protected CommonResult(long code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/**
	 * return result successfully
	 *
	 * @param data acquired data
	 */
	public static <T> CommonResult<T> success(T data) {
		return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
	}

	/**
	 * return result successfully
	 *
	 * @param data    acquired data
	 * @param message Tips
	 */
	public static <T> CommonResult<T> success(T data, String message) {
		return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
	}

	/**
	 * return result successfully
	 *
	 * @param errorCode error code
	 */
	public static <T> CommonResult<T> failed(IErrorCode errorCode) {
		return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
	}

	/**
	 * return result on failure
	 *
	 * @param errorCode error code
	 * @param message   error message
	 */
	public static <T> CommonResult<T> failed(IErrorCode errorCode, String message) {
		return new CommonResult<T>(errorCode.getCode(), message, null);
	}

	/**
	 * return result on failure
	 *
	 * @param message Tips
	 */
	public static <T> CommonResult<T> failed(String message) {
		return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
	}

	/**
	 * return result on failure
	 */
	public static <T> CommonResult<T> failed() {
		return failed(ResultCode.FAILED);
	}

	/**
	 * Parameter validation failed to return the result
	 */
	public static <T> CommonResult<T> validateFailed() {
		return failed(ResultCode.VALIDATE_FAILED);
	}

	/**
	 * Parameter validation failed to return the result
	 *
	 * @param message Tips
	 */
	public static <T> CommonResult<T> validateFailed(String message) {
		return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
	}

	/**
	 * not logged in return result
	 */
	public static <T> CommonResult<T> unauthorized(T data) {
		return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
	}

	/**
	 * not logged in return result
	 */
	public static <T> CommonResult<T> forbidden(T data) {
		return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
