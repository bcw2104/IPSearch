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

	public String returnMsg(int returnCode) throws Exception {
		String msg;

		if(returnCode == 131000) {
			msg = "지역정보를 찾을 수 없습니다. 다른 IP를 이용해서 조회하세요.";
		}
		else if(returnCode == 131001) {
			msg = "공인 IP가 아니거나 IP 주소 형식이 잘못되었습니다.";
		}
		else {
			msg = "서버에 오류가 발생하였습니다.";
		}

		return msg;
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
