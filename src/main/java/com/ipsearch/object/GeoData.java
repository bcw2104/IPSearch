package com.ipsearch.object;

public class GeoData {
	private int returnCode;
	private String requestId;
	private String country;
	private String code;
	private String addr;
	private double latitude;
	private double longitude;
	private String net;

	public GeoData() {
		this.returnCode = 0;
		this.requestId = null;
		this.country = null;
		this.code = null;
		this.addr = null;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	public String print() {
		StringBuilder builder = new StringBuilder();

		builder.append("===================== IP 정보 =====================\n");
		builder.append("\n국가 코드 : 						" + country);
		builder.append("\n행정구역 코드 : 					" + code);
		builder.append("\n위치 : 							" + addr);
		builder.append("\n위도 : 							" + latitude);
		builder.append("\n경도 : 							" + longitude);
		builder.append("\n통신사 : 							" + net);

		return builder.toString();
	}

}
