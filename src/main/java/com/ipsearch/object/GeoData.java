package com.ipsearch.object;

public class GeoData {
	private int returnCode;
	private String requestId;
	private String country;
	private String code;
	private String r1;
	private String r2;
	private String r3;
	private double latitude;
	private double longitude;
	private String net;

	public GeoData() {
		this.returnCode = 0;
		this.requestId = null;
		this.country = null;
		this.code = null;
		this.r1 = null;
		this.r2 = null;
		this.r3 = null;
		this.latitude = 0;
		this.longitude = 0;
		this.net = null;
	}

	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getR1() {
		return r1;
	}
	public void setR1(String r1) {
		this.r1 = r1;
	}
	public String getR2() {
		return r2;
	}
	public void setR2(String r2) {
		this.r2 = r2;
	}
	public String getR3() {
		return r3;
	}
	public void setR3(String r3) {
		this.r3 = r3;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getNet() {
		return net;
	}
	public void setNet(String net) {
		this.net = net;
	}


}
