package com.compannex.enums;

public enum CompanyStatusEnum {

	ACTIVE("active");

	private String status;

	private CompanyStatusEnum(String status) {
		this.status = status;
	}
	
	public String getValue() {
		return this.status;
	}

}
