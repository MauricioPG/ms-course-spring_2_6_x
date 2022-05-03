package com.devsuperior.msoauth.api;

/**
 * Error code of the encapsulated API
 *
 * @author Honghui [wanghonghui_work@163.com] 2021/3/16
 */
public interface IErrorCode {
	long getCode();

	String getMessage();
}
