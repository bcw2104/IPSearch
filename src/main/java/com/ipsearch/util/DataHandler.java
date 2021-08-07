package com.ipsearch.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ipsearch.object.DomainData;
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
		int returnCode = 131002; // Internal Server Error

		if (object.containsKey("returnCode"))
			returnCode = Integer.parseInt(object.get("returnCode").toString());

		// 요청 성공
		if (returnCode == 0 && object.containsKey("geoLocation")) {
			JSONObject locationData = (JSONObject) object.get("geoLocation");

			geoData.setRequestId(object.get("requestId").toString());
			geoData.setCountry(locationData.get("country").toString());
			geoData.setCode(locationData.get("code").toString());
			geoData.setAddr(locationData.get("r1").toString() + " " + locationData.get("r2").toString() + " "
					+ locationData.get("r3").toString());
			geoData.setLatitude(Double.parseDouble(locationData.get("lat").toString()));
			geoData.setLongitude(Double.parseDouble(locationData.get("long").toString()));
			geoData.setNet(locationData.get("net").toString());
		}

		geoData.setReturnCode(returnCode);

		return geoData;
	}

	public DomainData convertStringToDomainData(String jsonString) throws Exception {
		JSONObject object = (JSONObject) convertStringToJson(jsonString).get("whois");
		DomainData domainData = new DomainData();

		object = (JSONObject) object.get("krdomain");

		if (object.containsKey("error")) {
			object = (JSONObject) object.get("error");
			domainData.setReturnCode(Integer.parseInt(object.get("error_code").toString()));
			domainData.setErrorMsg(object.get("error_msg").toString().replace("(W)", "").replace(". ", ".\n"));
		} else {
			if (object.containsKey("name"))
				domainData.setName(object.get("name").toString());
			if (object.containsKey("regName"))
				domainData.setRegName(object.get("regName").toString());
			if (object.containsKey("addr"))
				domainData.setAddr(object.get("addr").toString());
			if (object.containsKey("post"))
				domainData.setPost(object.get("post").toString());
			if(object.containsKey("adminName"))
				domainData.setAdminName(object.get("adminName").toString());
			if (object.containsKey("adminEmail"))
				domainData.setAdminEmail(object.get("adminEmail").toString());
			if (object.containsKey("adminPhone"))
				domainData.setAdminPhone(object.get("adminPhone").toString());
			if (object.containsKey("lastUpdatedDate"))
				domainData.setLastUpdatedDate(object.get("lastUpdatedDate").toString());
			if (object.containsKey("regDate"))
				domainData.setRegDate(object.get("regDate").toString());
			if (object.containsKey("infoYN"))
				domainData.setInfoYN(object.get("infoYN").toString());
			if (object.containsKey("agency"))
				domainData.setAgency(object.get("agency").toString());
			if (object.containsKey("agency_url"))
				domainData.setAgencyUrl(object.get("agency_url").toString());
		}

		return domainData;
	}
}
