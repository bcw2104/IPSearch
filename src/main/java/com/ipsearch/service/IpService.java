package com.ipsearch.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipsearch.object.GeoData;
import com.ipsearch.util.DataHandler;
import com.ipsearch.util.GeoLocationClient;

@Service
public class IpService{
	@Autowired
	private DataHandler dataHander;
	@Autowired
	private GeoLocationClient geoLocationClient;

	public GeoData getIpData(String ip) throws Exception {
		GeoData geoData = dataHander.convertStringToGeoData(geoLocationClient.request(ip));

		return geoData;
	}

	public String getRequestIp(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
	}

}
