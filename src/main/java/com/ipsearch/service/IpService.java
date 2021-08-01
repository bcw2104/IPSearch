package com.ipsearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipsearch.util.IpHandler;

@Service
public class IpService {

	@Autowired
	private IpHandler ipHandler;

	public String getIpData(String ip) throws Exception {
		String ipData = ipHandler.request(ip);

		return ipData;
	}

}
