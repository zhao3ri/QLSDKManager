package com.item.domain;

public class SDataMonthly {
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
	
	private java.util.Date statDate;
	
	private Integer yearMonth;
	/*
	 *注册用户
	 */
	private Integer regUsers;
	
	private Double conversionRate;
	
	/*
	 *月登陆角色数
	 */
	private Integer monthLoginRoles;
	
	private Integer payUsers;
	
	private Integer incPayUsers;
	
	private Integer payAmount;
	
	private Integer incPayAmount;

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

	public Integer getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(Integer yearMonth) {
		this.yearMonth = yearMonth;
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

	public Integer getMonthLoginRoles() {
		return monthLoginRoles;
	}

	public void setMonthLoginRoles(Integer monthLoginRoles) {
		this.monthLoginRoles = monthLoginRoles;
	}

	public Integer getPayUsers() {
		return payUsers;
	}

	public void setPayUsers(Integer payUsers) {
		this.payUsers = payUsers;
	}

	public Integer getIncPayUsers() {
		return incPayUsers;
	}

	public void setIncPayUsers(Integer incPayUsers) {
		this.incPayUsers = incPayUsers;
	}

	public Integer getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getIncPayAmount() {
		return incPayAmount;
	}

	public void setIncPayAmount(Integer incPayAmount) {
		this.incPayAmount = incPayAmount;
	}

	public java.util.Date getStatDate() {
		return statDate;
	}

	public void setStatDate(java.util.Date statDate) {
		this.statDate = statDate;
	}
	
	
}
