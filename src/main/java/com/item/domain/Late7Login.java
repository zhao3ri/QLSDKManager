package com.item.domain;

import java.io.Serializable;

public class Late7Login implements Serializable{

	private static final long serialVersionUID = -8068381327975858523L;
	
	private String record;
	
	private String zoneId;

	public Late7Login() {
		
	}
	
	public Late7Login(String record, String zoneId) {
		this.record = record;
		this.zoneId = zoneId;
	}
	
	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
}
