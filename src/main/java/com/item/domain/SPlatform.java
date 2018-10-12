/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SPlatform.java  2014-12-25 17:12:37 zhouxb ]
 */
package com.item.domain;
/**
 *  实体
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2014-12-25 17:12:37
 * @since JDK 1.5
 */

 public class SPlatform{
	/*
	 *
	 */
	private Long id;
	/*
	 *设备类型：Android或者ios
	 */
	private Integer clientType;
	/*
	 *
	 */
	private Long appId;
	/*
	 *
	 */
	private String appName;
	/*
	 *
	 */
	private Integer platformId;
	/*
	 * 
	 */
	private String platformName;
	/*
	 *
	/*

	 *总创角用户
	 */
	private Integer totalRoleUser;
	/*
	 *总注册用户
	 */
	private Integer totalRegUser;
	/*
	 *总激活设备
	 */
	private Integer devices;
	/*
	 *充值总金额
	 */
	private Integer activeUsers;
	
	private Integer payAmount;
	/*
	 *充值总次数
	 */
	private Integer payTimes;
	/*
	 *付费总人数
	 */
	private Integer payUsers;
	/**
	 * @return 
	 */
	
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

	public Integer getTotalRoleUser() {
		return totalRoleUser==null?0:totalRoleUser;
	}
	public void setTotalRoleUser(Integer totalRoleUser) {
		this.totalRoleUser = totalRoleUser;
	}
	public Integer getTotalRegUser() {
		return totalRegUser==null?0:totalRegUser;
	}
	public void setTotalRegUser(Integer totalRegUser) {
		this.totalRegUser = totalRegUser;
	}
	public Integer getDevices() {
		return devices==null?0:devices;
	}
	public void setDevices(Integer devices) {
		this.devices = devices;
	}
	public Integer getActiveUsers() {
		return activeUsers==null?0:activeUsers;
	}
	public void setActiveUsers(Integer activeUsers) {
		this.activeUsers = activeUsers;
	}
	public Integer getPayAmount() {
		return payAmount==null?0:payAmount;
	}
	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}
	public Integer getPayTimes() {
		return payTimes==null?0:payTimes;
	}
	public void setPayTimes(Integer payTimes) {
		this.payTimes = payTimes;
	}
	public Integer getPayUsers() {
		return payUsers==null?0:payUsers;
	}
	public void setPayUsers(Integer payUsers) {
		this.payUsers = payUsers;
	}
}