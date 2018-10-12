/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SPlatformRealtime.java  2014-12-25 17:12:36 zhouxb ]
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
 public class SPlatformRealtime{
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
	/*
	 *
	 */
	private Integer platformId;
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
		return onlineUsers;
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
		return roleUsers;
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
		return newDevices;
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
		return payAmount;
	}
	/**
	 * 设置 [payAmount]
	 * @param payAmount 
	 */
	public void setPayAmount(Integer payAmount){
		this.payAmount=payAmount;
	}
	public Integer getActiveUsers() {
		return activeUsers;
	}
	public void setActiveUsers(Integer activeUsers) {
		this.activeUsers = activeUsers;
	}
	
	
}