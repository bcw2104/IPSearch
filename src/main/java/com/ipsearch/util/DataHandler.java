package com.ipsearch.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ipsearch.object.GeoData;

public class DataHandler {

	public JSONObject convertStringToJson(String jsonString) throws ParseException {
		JSONParser parser = new JSONParser();

		JSONObject object = (JSONObject) parser.parse(jsonString);

		return object;
	}

	public GeoData convertStringToGeoData(String jsonString) throws Exception {
		JSONObject object = convertStringToJson(jsonString);
		GeoData geoData = new GeoData();

		int returnCode = 131002;			//Internal Server Error

		if(object.containsKey("returnCode"))
			returnCode = Integer.parseInt(object.get("returnCode").toString());

		// 요청 성공
		if(returnCode == 0) {
			JSONObject locationData = (JSONObject) object.get("geoLocation");

			geoData.setCountry(locationData.get("country").toString());
			geoData.setCode(locationData.get("code").toString());
			geoData.setR1(locationData.get("r1").toString());
			geoData.setR2(locationData.get("country").toString());
			geoData.setR3(locationData.get("country").toString());
			geoData.setLatitude(Double.parseDouble(locationData.get("lat").toString()));
			geoData.setLongitude(Double.parseDouble(locationData.get("long").toString()));
			geoData.setNet(locationData.get("net").toString());
		}

		geoData.setReturnCode(returnCode);
		geoData.setRequestId(object.get("geoLocation").toString());

		return geoData;
	}
}
