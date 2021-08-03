package com.ipsearch.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalControllerAdvice {

	private final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public void exceptionHandler(Exception e,HttpServletResponse response) {
		logger.error(e.getMessage());

		try {
			response.sendError(500);
		} catch (IOException e1) {
			logger.error(e1.getMessage());
		}
	}
}
