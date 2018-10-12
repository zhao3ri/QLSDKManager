/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SRechargeHourly.java  2015-05-19 09:54:36 zhouxb ]
 */
package com.item.domain;
/**
 *  实体
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2015-05-19 09:54:36
 * @since JDK 1.5
 */
 public class SRechargeHourly{
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
	private String zoneId;
	/*
	 *
	 */
	private Integer platformId;
	/*
	 *充值角色数
	 */
	private Integer roles;
	/*
	 *充值金额
	 */
	private Integer amount;
	/*
	 *充值次数
	 */
	private Integer payTimes;
	/*
	 *今日至今最高单笔充值
	 */
	private Integer maxAmount;
	/*
	 *今日至今累计充值额
	 */
	private Integer currentAmount;
	/*
	 *时间
	 */
	private java.util.Date statDate;
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
	 * @return 充值角色数
	 */
	public Integer getRoles(){
		return roles;
	}
	/**
	 * 设置 充值角色数[roles]
	 * @param roles 充值角色数
	 */
	public void setRoles(Integer roles){
		this.roles=roles;
	}
	/**
	 * @return 充值金额
	 */
	public Integer getAmount(){
		return amount;
	}
	/**
	 * 设置 充值金额[amount]
	 * @param amount 充值金额
	 */
	public void setAmount(Integer amount){
		this.amount=amount;
	}
	/**
	 * @return 充值次数
	 */
	public Integer getPayTimes(){
		return payTimes;
	}
	/**
	 * 设置 充值次数[payTimes]
	 * @param payTimes 充值次数
	 */
	public void setPayTimes(Integer payTimes){
		this.payTimes=payTimes;
	}
	/**
	 * @return 今日至今最高单笔充值
	 */
	public Integer getMaxAmount(){
		return maxAmount;
	}
	/**
	 * 设置 今日至今最高单笔充值[maxAmount]
	 * @param maxAmount 今日至今最高单笔充值
	 */
	public void setMaxAmount(Integer maxAmount){
		this.maxAmount=maxAmount;
	}
	/**
	 * @return 今日至今累计充值额
	 */
	public Integer getCurrentAmount(){
		return currentAmount;
	}
	/**
	 * 设置 今日至今累计充值额[currentAmount]
	 * @param currentAmount 今日至今累计充值额
	 */
	public void setCurrentAmount(Integer currentAmount){
		this.currentAmount=currentAmount;
	}
	/**
	 * @return 时间
	 */
	public java.util.Date getStatDate(){
		return statDate;
	}
	/**
	 * 设置 时间[statDate]
	 * @param statDate 时间
	 */
	public void setStatDate(java.util.Date statDate){
		this.statDate=statDate;
	}
}