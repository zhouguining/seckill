package com.zepal.seckill.common;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * @author zepal
 * */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 异常拦截
	 * */
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public Msg globalExceptinHandler(HttpServletRequest request, Exception e) {
		//如果异常来自JSR303返回参数异常
		if(e instanceof ValidationException) {
			return Msg.fail(EnumConstants.FAIL).add("errorInfo", e.getMessage()).add("uri", request.getRequestURI().toString());
		}
		return Msg.fail(EnumConstants.SYSTEM_EXCEPTION).add("errorInfo", e.getMessage()).add("url", request.getRequestURL().toString());
	}
}
