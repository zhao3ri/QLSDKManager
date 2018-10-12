/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SZonePlatformDaily.java  2014-12-25 17:12:36 zhouxb ]
 */
package com.item.domain;

/**
 *  实体
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2014-12-25 17:12:36
 * @since JDK 1.5
 */
 public class SZonePlatform{
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
	 *创角用户
	 */
	private Integer totalRoledUser;
	/*
	 *注册用户
	 */
	private Integer totalRegUser;
	/*
	 *新增设备
	 */
	private Integer devices;
	/*
	 *活跃用户
	 */
	private Integer activeUsers;
	/*
	 *
	 */
	private Integer payAmount;
	/*
	 *
	 */
	private Integer payUsers;
	/*
	 *
	 */
	private Integer newUserPayAmount;
	/*
	 *
	 */
	private Integer newUserPays;
	
	private Integer payTimes;

	/*
	 *首次付费用户充值额
	 */
	private Integer firstPayAmount;
	/*
	 *首次付费用户数
	 */
	private Integer firstPayUsers;
	/*
	 *开始时间
	 */
	private  String startDate;
	/*
	 *结束时间
	 */
	private  String endDate;
	/*
	 * 
	 */
	private Double keepRate1;
	/*
	 *
	 */
	private Double keepRate3;
	/*
	 *
	 */
	private Double keepRate7;
	/*
	 *
	 */
	private Double keepRate14;
	/*
	 *
	 */
	private Double keepRate30;
	/*
	 *
	 */
	private Integer lossUsers;
	/*
	 *
	 */
	private Integer lossPayUsers;
	/*
	 *
	 */
	private Integer backUsers;
	/*
	 *
	 */
	private Integer backPayUsers;
	/*
	 *
	 */
	private Integer regDevices;
	/*
	 *
	 */
	private Integer roleDevices;
	
	
	
	public Integer getPayTimes() {
		return payTimes;
	}
	public void setPayTimes(Integer payTimes) {
		this.payTimes = payTimes;
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

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
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
	public Integer getZoneId(){
		return zoneId;
	}
	/**
	 * 设置 [zoneId]
	 * @param zoneId 
	 */
	public void setZoneId(Integer zoneId){
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

	/**
	 * @return 
	 */
	public Integer getActiveUsers(){
		return activeUsers;
	}
	public Integer getTotalRoledUser() {
		return totalRoledUser;
	}
	public void setTotalRoledUser(Integer totalRoledUser) {
		this.totalRoledUser = totalRoledUser;
	}
	public Integer getTotalRegUser() {
		return totalRegUser;
	}
	public void setTotalRegUser(Integer totalRegUser) {
		this.totalRegUser = totalRegUser;
	}
	public Integer getDevices() {
		return devices;
	}
	public void setDevices(Integer devices) {
		this.devices = devices;
	}
	/**
	 * 设置 [activeUsers]
	 * @param activeUsers 
	 */
	public void setActiveUsers(Integer activeUsers){
		this.activeUsers=activeUsers;
	}
	/**
	 * @return 
	 */
	public Integer getPayAmount(){
		return payAmount;
	}
	/**
	 * 设置 [payAmount]
	 * @param payAmount 
	 */
	public void setPayAmount(Integer payAmount){
		this.payAmount=payAmount;
	}
	/**
	 * @return 
	 */
	public Integer getPayUsers(){
		return payUsers;
	}
	/**
	 * 设置 [payUsers]
	 * @param payUsers 
	 */
	public void setPayUsers(Integer payUsers){
		this.payUsers=payUsers;
	}
	/**
	 * @return 
	 */
	public Integer getNewUserPayAmount(){
		return newUserPayAmount;
	}
	/**
	 * 设置 [newUserPayAmount]
	 * @param newUserPayAmount 
	 */
	public void setNewUserPayAmount(Integer newUserPayAmount){
		this.newUserPayAmount=newUserPayAmount;
	}
	/**
	 * @return 
	 */
	public Integer getNewUserPays(){
		return newUserPays;
	}
	/**
	 * 设置 [newUserPays]
	 * @param newUserPays 
	 */
	public void setNewUserPays(Integer newUserPays){
		this.newUserPays=newUserPays;
	}
	/**
	 * @return 
	 */
	public Integer getFirstPayAmount(){
		return firstPayAmount;
	}
	/**
	 * 设置 [firstPayAmount]
	 * @param firstPayAmount 
	 */
	public void setFirstPayAmount(Integer firstPayAmount){
		this.firstPayAmount=firstPayAmount;
	}
	/**
	 * @return 
	 */
	public Integer getFirstPayUsers(){
		return firstPayUsers;
	}
	/**
	 * 设置 [firstPayUsers]
	 * @param firstPayUsers 
	 */
	public void setFirstPayUsers(Integer firstPayUsers){
		this.firstPayUsers=firstPayUsers;
	}
	/**
	 * @return 
	 */
	public Double getKeepRate1(){
		return keepRate1;
	}
	/**
	 * 设置 [keepRate1]
	 * @param keepRate1 
	 */
	public void setKeepRate1(Double keepRate1){
		this.keepRate1=keepRate1;
	}
	/**
	 * @return 
	 */
	public Double getKeepRate3(){
		return keepRate3;
	}
	/**
	 * 设置 [keepRate3]
	 * @param keepRate3 
	 */
	public void setKeepRate3(Double keepRate3){
		this.keepRate3=keepRate3;
	}
	/**
	 * @return 
	 */
	public Double getKeepRate7(){
		return keepRate7;
	}
	/**
	 * 设置 [keepRate7]
	 * @param keepRate7 
	 */
	public void setKeepRate7(Double keepRate7){
		this.keepRate7=keepRate7;
	}
	/**
	 * @return 
	 */
	public Double getKeepRate14(){
		return keepRate14;
	}
	/**
	 * 设置 [keepRate14]
	 * @param keepRate14 
	 */
	public void setKeepRate14(Double keepRate14){
		this.keepRate14=keepRate14;
	}
	/**
	 * @return 
	 */
	public Double getKeepRate30(){
		return keepRate30;
	}
	/**
	 * 设置 [keepRate30]
	 * @param keepRate30 
	 */
	public void setKeepRate30(Double keepRate30){
		this.keepRate30=keepRate30;
	}
	/**
	 * @return 
	 */
	public Integer getLossUsers(){
		return lossUsers;
	}
	/**
	 * 设置 [lossUsers]
	 * @param lossUsers 
	 */
	public void setLossUsers(Integer lossUsers){
		this.lossUsers=lossUsers;
	}
	/**
	 * @return 
	 */
	public Integer getLossPayUsers(){
		return lossPayUsers;
	}
	/**
	 * 设置 [lossPayUsers]
	 * @param lossPayUsers 
	 */
	public void setLossPayUsers(Integer lossPayUsers){
		this.lossPayUsers=lossPayUsers;
	}
	/**
	 * @return 
	 */
	public Integer getBackUsers(){
		return backUsers;
	}
	/**
	 * 设置 [backUsers]
	 * @param backUsers 
	 */
	public void setBackUsers(Integer backUsers){
		this.backUsers=backUsers;
	}
	/**
	 * @return 
	 */
	public Integer getBackPayUsers(){
		return backPayUsers;
	}
	/**
	 * 设置 [backPayUsers]
	 * @param backPayUsers 
	 */
	public void setBackPayUsers(Integer backPayUsers){
		this.backPayUsers=backPayUsers;
	}
	/**
	 * @return 
	 */
	public Integer getRegDevices(){
		return regDevices;
	}
	/**
	 * 设置 [regDevices]
	 * @param regDevices 
	 */
	public void setRegDevices(Integer regDevices){
		this.regDevices=regDevices;
	}
	/**
	 * @return 
	 */
	public Integer getRoleDevices(){
		return roleDevices;
	}
	/**
	 * 设置 [roleDevices]
	 * @param roleDevices 
	 */
	public void setRoleDevices(Integer roleDevices){
		this.roleDevices=roleDevices;
	}
}