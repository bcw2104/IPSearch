package com.ipsearch.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

public class WhoisClient {

	private final String accessKey;
	private final String hostName;
	private final String requestUrl;

	public WhoisClient(String accessKey) {
		this.accessKey = accessKey;

		this.hostName = "http://whois.kisa.or.kr";
		this.requestUrl = "/openapi/whois.jsp";
	}

	public String request(final String query) throws Exception {

		String requestFullUrl = getRequestURL(query);

		URL url = new URL(requestFullUrl);

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));

		String line;

		while((line = reader.readLine())!= null) {
			buffer.append(line);
		}

		return buffer.toString();
	}

	private String getRequestURL(final String query) throws UnsupportedEncodingException {
		final String answer = "json";

		final ArrayList<String> requestParameters = new ArrayList<String>();
		requestParameters.add("query="+URLEncoder.encode(query,"UTF-8"));
		requestParameters.add("key="+accessKey);
		requestParameters.add("answer="+answer);

		String queryString =  getRequestQueryString(requestParameters);

		String requestFullUrl = hostName+requestUrl + "?" +queryString;

		return requestFullUrl;
	}

	private String getRequestQueryString(final ArrayList<String> requestParameters) {
		final StringBuilder queryString = new StringBuilder();

		final Iterator<String> paramIter = requestParameters.iterator();
		while (paramIter.hasNext()) {
			queryString.append(paramIter.next());

			if (paramIter.hasNext()) {
				queryString.append('&');
			}
		}
		return queryString.toString();
	}
}
