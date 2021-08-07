package com.ipsearch.service;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipsearch.object.DomainData;
import com.ipsearch.object.GeoData;
import com.ipsearch.util.DataHandler;
import com.ipsearch.util.GeoLocationClient;
import com.ipsearch.util.WhoisClient;

@Service
public class SearchService{
	@Autowired
	private DataHandler dataHander;
	@Autowired
	private WhoisClient whoisClient;
	@Autowired
	private GeoLocationClient geoLocationClient;

	public GeoData getIpData(String query) throws Exception {
		GeoData geoData = dataHander.convertStringToGeoData(geoLocationClient.request(query));

		return geoData;
	}

	public DomainData getDomainData(String query) throws Exception {
		DomainData domainData = dataHander.convertStringToDomainData(whoisClient.request(query));

		return domainData;
	}

	public String returnMsg(int returnCode) throws Exception {
		String msg;

		if(returnCode == 131000) {
			msg = "지역정보를 찾을 수 없습니다. 다른 IP를 이용해서 조회하세요.";
		}
		else if(returnCode == 131001) {
			msg = "공인 IP가 아니거나 IP 주소 형식이 잘못되었습니다.";
		}
		else if(returnCode == 404){
			msg = "# KOREAN(UTF-8) 질의 내용이 부정확 합니다."
				+ "\n아래 주소 조회 예제를 확인하신 후 조회하여 주시기 바랍니다."
				+ "\n도메인 네임 검색 : 예) nic.or.kr"
				+ "\n한글 도메인 검색 : 예) 한국인터넷정보센터.kr"
				+ "\n\n# ENGLISH The query type is incorrect."
				+ "\nPlease see the following query examples and try again."
				+ "\nDomain Name Search : ex) nic.or.kr";
		}
		else {
			msg = "서버에 오류가 발생하였습니다.";
		}

		return msg;
	}

	public String getRequestIp(HttpServletRequest request) {
		String ip = request.getHeader("x-simplexi");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-real-ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
	}

	public boolean checkIPv4Validation(String query) {
		final String iPv4Pattern = "((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])([.](?!$)|$)){4}";

		return Pattern.matches(iPv4Pattern, query);
	}

	public String makeDomainFormat(String query) {
		query = query.toLowerCase().trim();
		query = query.replace("http://", "").replace("https://", "").replace("www.", "");

		if(query.endsWith("/"))
			query = query.substring(0, query.length()-1);

		return query;
	}

	public boolean checkKORDomain(String query) {
		return query.endsWith(".kr") || query.endsWith(".한국");
	}

}
