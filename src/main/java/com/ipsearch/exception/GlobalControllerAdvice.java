package com.ipsearch.exception;

import java.util.concurrent.CancellationException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(IllegalArgumentException.class)
	public String handle400(IllegalArgumentException e,HttpServletRequest request) {
		request.setAttribute("status", 400);
		e.printStackTrace();

		return "common/error";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public String handle404(NoHandlerFoundException e,HttpServletRequest request) {
		request.setAttribute("status", 404);
		e.printStackTrace();

	    return "common/error";
	}

	@ExceptionHandler(CancellationException.class)
	public String handle429(CancellationException e,HttpServletRequest request) {
		request.setAttribute("status", 429);
		request.setAttribute("waitTime", e.getMessage());

	    return "common/error";
	}

	@ExceptionHandler(Exception.class)
	public String handle500(Exception e,HttpServletRequest request) {
		e.printStackTrace();
		request.setAttribute("status", 500);

		return "common/error";
	}
}
