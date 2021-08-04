package com.ipsearch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.ipsearch.util.GeoLocationClient;
import com.ipsearch.util.MapClient;
import com.ipsearch.util.WhoisClient;
import com.ipsearch.util.DataHandler;

@Configuration
@ComponentScan(basePackages = {"com.ipsearch.service","com.ipsearch.exception"})
@PropertySource("classpath:properties/key.properties")
public class RootContext {
	@Autowired
    private Environment env;

	@Bean
	public GeoLocationClient geoLocationClient() {
		GeoLocationClient geoLocationClient = new GeoLocationClient(env.getProperty("key.access"),env.getProperty("key.secret"));

		return geoLocationClient;
	}

	@Bean
	public MapClient mapClient() {
		MapClient mapClient = new MapClient(env.getProperty("key.clientId"));

		return mapClient;
	}

	@Bean
	public WhoisClient whoisClient() {
		WhoisClient whoisClient = new WhoisClient(env.getProperty("key.whois"));

		return whoisClient;
	}

	@Bean
	public DataHandler dataHander() {
		DataHandler dataHander = new DataHandler();

		return dataHander;
	}
}
