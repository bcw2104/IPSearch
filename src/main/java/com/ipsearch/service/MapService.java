package com.ipsearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipsearch.util.MapClient;

@Service
public class MapService {
	@Autowired
	private MapClient mapClient;

	public String getMapClientId() {
		return mapClient.getClientId();
	}
}
