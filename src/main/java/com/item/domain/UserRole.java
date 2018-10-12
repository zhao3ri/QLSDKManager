package com.item.domain;

import java.io.Serializable;

public class UserRole implements Serializable{
	private static final long serialVersionUID = 867293154488812800L;

	private String zoneId;

	public String getZoneId() {
		return zoneId;
	}
	
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
}
