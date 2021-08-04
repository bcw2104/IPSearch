package com.ipsearch.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class GlobalControllerAdvice {

	private final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

	@ExceptionHandler(NoHandlerFoundException.class)
	public String handle404(NoHandlerFoundException e,HttpServletRequest request) {
		request.setAttribute("status", 404);
		logger.info(e.getMessage());

	    return "common/error";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String handle400(IllegalArgumentException e,HttpServletRequest request) {
		request.setAttribute("status", 400);
		logger.info(e.getMessage());

		return "common/error";
	}

	@ExceptionHandler(Exception.class)
	public String handle500(Exception e,HttpServletRequest request) {
		logger.info(e.getMessage());

		request.setAttribute("status", 500);

		return "common/error";
	}
}
