package com.toec.market.repair.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice( )
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public String exceptionHandler() {
		
		return "对不起，服务器忙...";
	}
}
