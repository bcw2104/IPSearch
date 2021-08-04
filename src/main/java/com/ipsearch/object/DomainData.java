package com.ipsearch.object;

public class DomainData {
	private int returnCode;
	private String name;
	private String regName;
	private String addr;
	private String post;
	private String adminName;
	private String adminEmail;
	private String adminPhone;
	private String lastUpdatedDate;
	private String regDate;
	private String infoYN;
	private String agency;
	private String agencyUrl;
	private String errorMsg;
	private double latitude;
	private double longitude;

	public DomainData() {
		this.returnCode = 0;
		this.name = null;
		this.regName = null;
		this.addr = null;
		this.post = null;
		this.adminName = null;
		this.adminEmail = null;
		this.adminPhone = null;
		this.lastUpdatedDate = null;
		this.regDate = null;
		this.infoYN = null;
		this.agency = null;
		this.agencyUrl = null;

		this.latitude = 0;
		this.longitude = 0;

		this.errorMsg = null;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getInfoYN() {
		return infoYN;
	}

	public void setInfoYN(String infoYN) {
		this.infoYN = infoYN;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getAgencyUrl() {
		return agencyUrl;
	}

	public void setAgencyUrl(String agencyUrl) {
		this.agencyUrl = agencyUrl;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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

	public String print() {
		StringBuilder builder = new StringBuilder();

		builder.append("==================== Domain ���� ====================\n");
		builder.append("\n������ �� : 						" + name);
		builder.append("\n����� �� : 						" + regName);
		builder.append("\n��ġ : 							" + addr);
		builder.append("\n�����ȣ : 						" + post);
		builder.append("\n������ �� : 						" + adminName);
		builder.append("\n������ Email : 					" + adminEmail);
		builder.append("\n������ ��ȭ��ȣ :					" + adminPhone);
		builder.append("\n����� : 							" + regDate);
		builder.append("\n�ֱ� ���� ������ : 				" + lastUpdatedDate);
		builder.append("\n���� ���� ���� : 					" + infoYN);
		builder.append("\n��ϴ����� : 						" + agency);
		builder.append("\n��ϴ����� URL : 				" + agencyUrl);
		builder.append("\n\n\n���� ���ͳ��ּ�(������, IP�ּ�/AS��ȣ) ��������� �ѱ����ͳ���������κ��� �������� �������Դϴ�.");

		return builder.toString();
	}

}
