package com.item.domain.report;

import com.item.domain.Gamezone;
import com.item.domain.SZonePlatform;

public class SummaryZoneReport {
	
	private Long appId;
	
	private String appname;
	
	private String zonename;
	
	private SZonePlatform szoneplatform;
	
	private Gamezone gamezone;

	
	public Gamezone getGamezone() {
		return gamezone;
	}

	public void setGamezone(Gamezone gamezone) {
		this.gamezone = gamezone;
	}

	public SZonePlatform getSzoneplatform() {
		return szoneplatform;
	}

	public void setSzoneplatform(SZonePlatform szoneplatform) {
		this.szoneplatform = szoneplatform;
	}

	public SummaryZoneReport() {
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}
	
	public String getZonename() {
		return zonename;
	}

	public void setZonename(String zonename) {
		this.zonename = zonename;
	}
	
}
