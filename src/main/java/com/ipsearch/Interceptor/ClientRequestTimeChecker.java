package com.ipsearch.Interceptor;

import java.util.concurrent.CancellationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class ClientRequestTimeChecker implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();

		long currentTime = System.currentTimeMillis();

		if (session.getAttribute("remainTimes") == null) {
			session.setAttribute("remainTimes", 2);

		} else {
			int remainTimes = (int) session.getAttribute("remainTimes");
			long preRequestTime = (long) session.getAttribute("requestTime");
			long waitTime = 5000 - (currentTime - preRequestTime);

			if(remainTimes > 0) {
				if (waitTime > 0) {
					session.setAttribute("remainTimes", remainTimes-1);
				}
			}
			else {
				if (waitTime <= 0) {
					session.setAttribute("remainTimes", 2);
				}
				else {
					throw new CancellationException(Long.toString((waitTime / 1000) + 1));
				}
			}
		}

		session.setAttribute("requestTime", currentTime);
		return true;
	}
}
