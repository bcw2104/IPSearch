package com.ipsearch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.ipsearch.util.IpHandler;

@Configuration
@ComponentScan(basePackages = {"com.ipsearch.service","com.ipsearch.exception"})
@PropertySource("classpath:properties/key.properties")
public class RootContext {
	@Autowired
    private Environment env;

	@Bean
	public IpHandler ipHander() {
		IpHandler ipHandler = new IpHandler(env.getProperty("key.access"),env.getProperty("key.secret"));

		return ipHandler;
	}
}
