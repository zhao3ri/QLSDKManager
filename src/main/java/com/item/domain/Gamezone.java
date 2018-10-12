package com.item.domain;

public class Gamezone {
	/** 
	 * 自增
	 * */
	private Long id;
	/** 
	 * 分区ID
	 * 唯一
	 * */
	private String zoneId;
	/** 
	 * 分区名称
	 * */
	private String zoneName;
	/** 
	 * 游戏ID
	 * */
	private Long appId;
	
	private String appName;
	
	private int isHave;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getZoneId() {
		return zoneId;
	}
	
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	
	public String getZoneName() {
		return zoneName;
	}
	
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	
	public Long getAppId() {
		return appId;
	}
	
	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int getIsHave() {
		return isHave;
	}

	public void setIsHave(int isHave) {
		this.isHave = isHave;
	}

}
