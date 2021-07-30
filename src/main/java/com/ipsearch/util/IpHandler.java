package com.ipsearch.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class IpHandler {
	private String whoisKey;

	public IpHandler() {
		whoisKey = "2021072818345152504168";
	}

	public JSONObject getCountry(String ip) throws UnsupportedEncodingException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		URL url = null;
		JSONObject ipData = null;

		url = new URL("http://whois.kisa.or.kr/openapi/ipascc.jsp?query="+ip+"&key="+whoisKey+"&answer=json");
		ipData =  (JSONObject) parser.parse(new InputStreamReader(url.openStream(),"UTF-8"));

		return ipData;
	}

	public JSONObject getInfo(String ip) throws UnsupportedEncodingException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		URL url = null;
		JSONObject ipData = null;

		url = new URL("http://whois.kisa.or.kr/openapi/whois.jsp?query="+ip+"&key="+whoisKey+"&answer=json");
		ipData =  (JSONObject) parser.parse(new InputStreamReader(url.openStream(),"UTF-8"));

		return ipData;
	}

	public void printJson(JSONObject object) {
		printJson(object,0);
	}

	@SuppressWarnings("unchecked")
	private void printJson(JSONObject object,int deep) {
		Iterator<String> iter = object.keySet().iterator();

		while(iter.hasNext()) {
			String key = iter.next();

			for(int i=0; i<deep; i++)
				System.out.print("	");

			System.out.print(key + " : ");

			if(object.get(key).toString().contains("{")) {
				System.out.println();
				printJson((JSONObject)object.get(key),deep+1);
			}
			else {
				System.out.println(object.get(key).toString());
			}
		}
	}
}
