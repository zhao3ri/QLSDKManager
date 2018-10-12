package com.item.domain;

import java.io.Serializable;
import java.util.Date;

public class UserRecharge implements Serializable{
	
	private static final long serialVersionUID = -8247541019068381149L;
	
	private String zoneId;
	
	private Date lastRechargeDate;
	
	public UserRecharge() {
		
	}
	
	public UserRecharge(String zoneId, Date lastRechargeDate) {
		this.zoneId = zoneId;
		this.lastRechargeDate = lastRechargeDate;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public Date getLastRechargeDate() {
		return lastRechargeDate;
	}

	public void setLastRechargeDate(Date lastRechargeDate) {
		this.lastRechargeDate = lastRechargeDate;
	}
}
