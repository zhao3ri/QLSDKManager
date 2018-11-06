/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SZonePlatformRealtime.java  2014-12-25 17:12:35 zhouxb ]
 */
package com.item.domain;
/**
 *  实体
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2014-12-25 17:12:35
 * @since JDK 1.5
 */
 public class SZonePlatformRealtime{
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
	private String zoneId;
	
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
	 *
	 */
	private Integer onlineUsers;
	/*
	 *
	 */
	private Integer roleUsers;
	/*
	 *
	 */
	private Integer newDevices;
	/*
	 *
	 */
	private Integer payAmount;
	
	private Integer activeUsers;
	/**
	 * @return 
	 */
	public Long getId(){
		return id;
	}
	/**
	 * 设置 [id]
	 * @param id 
	 */
	public void setId(Long id){
		this.id=id;
	}
	/**
	 * @return 
	 */
	public Integer getClientType(){
		return clientType;
	}
	/**
	 * 设置 [clientType]
	 * @param clientType 
	 */
	public void setClientType(Integer clientType){
		this.clientType=clientType;
	}
	/**
	 * @return 
	 */
	public Long getAppId(){
		return appId;
	}
	/**
	 * 设置 [appId]
	 * @param appId 
	 */
	public void setAppId(Long appId){
		this.appId=appId;
	}
	/**
	 * @return 
	 */
	public String getZoneId(){
		return zoneId;
	}
	/**
	 * 设置 [zoneId]
	 * @param zoneId 
	 */
	public void setZoneId(String zoneId){
		this.zoneId=zoneId;
	}
	/**
	 * @return 
	 */
	public Integer getPlatformId(){
		return platformId;
	}
	/**
	 * 设置 [platformId]
	 * @param platformId 
	 */
	public void setPlatformId(Integer platformId){
		this.platformId=platformId;
	}
	/**
	 * @return 
	 */
	public java.util.Date getStatDate(){
		return statDate;
	}
	/**
	 * 设置 [statDate]
	 * @param statDate 
	 */
	public void setStatDate(java.util.Date statDate){
		this.statDate=statDate;
	}
	/**
	 * @return 
	 */
	public Integer getOnlineUsers(){
		return onlineUsers== null ? 0 :onlineUsers;
	}
	/**
	 * 设置 [onlineUsers]
	 * @param onlineUsers 
	 */
	public void setOnlineUsers(Integer onlineUsers){
		this.onlineUsers=onlineUsers;
	}
	/**
	 * @return 
	 */
	public Integer getRoleUsers(){
		return roleUsers== null ? 0 :roleUsers;
	}
	/**
	 * 设置 [roleUsers]
	 * @param roleUsers 
	 */
	public void setRoleUsers(Integer roleUsers){
		this.roleUsers=roleUsers;
	}
	/**
	 * @return 
	 */
	public Integer getNewDevices(){
		return newDevices== null ? 0 :newDevices;
	}
	/**
	 * 设置 [newDevices]
	 * @param newDevices 
	 */
	public void setNewDevices(Integer newDevices){
		this.newDevices=newDevices;
	}
	/**
	 * @return 
	 */
	public Integer getPayAmount(){
		return payAmount== null ? 0 :payAmount;
	}
	/**
	 * 设置 [payAmount]
	 * @param payAmount 
	 */
	public void setPayAmount(Integer payAmount){
		this.payAmount=payAmount;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public Integer getActiveUsers() {
		return activeUsers;
	}
	public void setActiveUsers(Integer activeUsers) {
		this.activeUsers = activeUsers;
	}
	
}