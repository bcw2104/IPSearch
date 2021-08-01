package com.ipsearch.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class IpHandler {
	private final String accessKey;
	private final String secretKey;
	private final CloseableHttpClient httpClient;

	public IpHandler(String accessKey, String secretKey) {
		this.accessKey = accessKey;
		this.secretKey = secretKey;

		final int timeout = 5000;
		final RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout)
				.setConnectTimeout(timeout).build();

		httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
	}

	public String request(final String ip) throws Exception {
		CloseableHttpResponse httpResponse = httpClient.execute(setRequest(ip));

		return getResponse(httpResponse);
	}

	private HttpGet setRequest(final String ip) throws Exception {
		final String requestMethod = "GET";
		final String hostName = "https://geolocation.apigw.ntruss.com";
		final String requestUrl= "/geolocation/v2/geoLocation";
		final String enc = "utf8";
		final String ext = "t";
		final String responseFormatType = "json";

		final ArrayList<String> requestParameters = new ArrayList<String>();
		requestParameters.add("ip="+ip);
		requestParameters.add("enc="+enc);
		requestParameters.add("ext="+ext);
		requestParameters.add("responseFormatType="+responseFormatType);


		String timestamp = generateTimestamp();

		String baseString = requestUrl + "?" + getRequestQueryString(requestParameters);

		String signature = makeSignature(requestMethod, baseString, timestamp);

		final String requestFullUrl = hostName + baseString;
		final HttpGet request = new HttpGet(requestFullUrl);

		request.setHeader("x-ncp-apigw-timestamp",timestamp);
		request.setHeader("x-ncp-iam-access-key",accessKey);
		request.setHeader("x-ncp-apigw-signature-v2",signature);

		return request;
	}

	private String generateTimestamp() {
		return Long.toString(System.currentTimeMillis());
	}

	private static String getRequestQueryString(final ArrayList<String> requestParameters) {
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

	private String makeSignature(final String method, final String baseString, final String timestamp) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException{
	    String space = " ";
	    String newLine = "\n";

	    String message = new StringBuilder()
	        .append(method)
	        .append(space)
	        .append(baseString)
	        .append(newLine)
	        .append(timestamp)
	        .append(newLine)
	        .append(accessKey)
	        .toString();

	    SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
	    mac.init(signingKey);
	    byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
	    String encodeBase64String = Base64.encodeBase64String(rawHmac);
	    return encodeBase64String;
	}

	private String getResponse(final CloseableHttpResponse response) throws Exception {
		final StringBuffer buffer = new StringBuffer();
		final BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8"));

		String line;

		try {
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (final Exception e) {
			throw e;
		} finally {
			response.close();
		}

		System.out.println(buffer.toString());
		return buffer.toString();
	}


}
