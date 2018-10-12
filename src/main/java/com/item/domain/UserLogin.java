package com.item.domain;

import java.io.Serializable;
import java.util.Date;

public class UserLogin implements Serializable{
	private static final long serialVersionUID = 6424918223187448399L;
	
	private Date loginDate;
	
	private String zoneId;
	
	public UserLogin() {
		
	}
	
	public UserLogin(String zoneId, Date loginDate) {
		this.zoneId = zoneId;
		this.loginDate = loginDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
}
