package com.item.domain;

public class SDataDaily {
	/*
	 *
	 */
	private Long id;
	/*
	 *
	 */
	private Integer clientType;
	/*
	 *
	 */
	private Long appId;
	
	private String appName;
	/*
	 *
	 */
	private Integer zoneId;
	
	private String zoneName;
	/*
	 *
	 */
	private Integer platformId;
	
	private String platformName;
	/*
	 *
	 */
	private java.util.Date statDate;
	
	/*
	 *注册用户
	 */
	private Integer regUsers;
	
	private Double conversionRate;
	
	/*
	 *活跃用户
	 */
	private Integer activeUsers;
	
	/*
	 * 
	 */
	private Integer keepRole1;
	/*
	 *
	 */
	private Integer keepRole3;
	/*
	 *
	 */
	private Integer keepRole7;
	/*
	 *
	 */
	private Integer keepRole15;
	/*
	 *
	 */
	private Integer keepRole30;
	
	private Integer payUsers;
	
	private Integer incPayUsers;
	
	private Integer payAmount;
	
	private Integer incPayAmount;
	
	private Integer roleEstablishs;

	public Integer getRoleEstablishs() {
		return roleEstablishs;
	}

	public void setRoleEstablishs(Integer roleEstablishs) {
		this.roleEstablishs = roleEstablishs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
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

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public Integer getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public java.util.Date getStatDate() {
		return statDate;
	}

	public void setStatDate(java.util.Date statDate) {
		this.statDate = statDate;
	}

	public Integer getRegUsers() {
		return regUsers;
	}

	public void setRegUsers(Integer regUsers) {
		this.regUsers = regUsers;
	}

	public Double getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(Double conversionRate) {
		this.conversionRate = conversionRate;
	}

	public Integer getActiveUsers() {
		return activeUsers;
	}

	public void setActiveUsers(Integer activeUsers) {
		this.activeUsers = activeUsers;
	}

	public Integer getKeepRole1() {
		return keepRole1;
	}

	public void setKeepRole1(Integer keepRole1) {
		this.keepRole1 = keepRole1;
	}

	public Integer getKeepRole3() {
		return keepRole3;
	}

	public void setKeepRole3(Integer keepRole3) {
		this.keepRole3 = keepRole3;
	}

	public Integer getKeepRole7() {
		return keepRole7;
	}

	public void setKeepRole7(Integer keepRole7) {
		this.keepRole7 = keepRole7;
	}

	public Integer getKeepRole15() {
		return keepRole15;
	}

	public void setKeepRole15(Integer keepRole15) {
		this.keepRole15 = keepRole15;
	}

	public Integer getKeepRole30() {
		return keepRole30;
	}

	public void setKeepRole30(Integer keepRole30) {
		this.keepRole30 = keepRole30;
	}

	public Integer getIncPayUsers() {
		return incPayUsers;
	}

	public void setIncPayUsers(Integer incPayUsers) {
		this.incPayUsers = incPayUsers;
	}


	public Integer getIncPayAmount() {
		return incPayAmount;
	}

	public void setIncPayAmount(Integer incPayAmount) {
		this.incPayAmount = incPayAmount;
	}

	public Integer getPayUsers() {
		return payUsers;
	}

	public void setPayUsers(Integer payUsers) {
		this.payUsers = payUsers;
	}

	public Integer getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}
	
}
