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
			msg = "���������� ã�� �� �����ϴ�. �ٸ� IP�� �̿��ؼ� ��ȸ�ϼ���.";
		}
		else if(returnCode == 131001) {
			msg = "���� IP�� �ƴϰų� IP �ּ� ������ �߸��Ǿ����ϴ�.";
		}
		else if(returnCode == 404){
			msg = "# KOREAN(UTF-8) ���� ������ ����Ȯ �մϴ�."
				+ "\n�Ʒ� �ּ� ��ȸ ������ Ȯ���Ͻ� �� ��ȸ�Ͽ� �ֽñ� �ٶ��ϴ�."
				+ "\n������ ���� �˻� : ��) nic.or.kr"
				+ "\n�ѱ� ������ �˻� : ��) �ѱ����ͳ���������.kr"
				+ "\n\n# ENGLISH The query type is incorrect."
				+ "\nPlease see the following query examples and try again."
				+ "\nDomain Name Search : ex) nic.or.kr";
		}
		else {
			msg = "������ ������ �߻��Ͽ����ϴ�.";
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
		return query.endsWith(".kr") || query.endsWith(".�ѱ�");
	}

}
