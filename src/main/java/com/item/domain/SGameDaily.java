/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SGameDaily.java  2014-12-25 17:12:37 zhouxb ]
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
 public class SGameDaily{
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
	private Long gameId;
	/*
	 *
	 */
	private java.util.Date statDate;
	/*
	 *
	 */
	private Integer roleUsers;
	/*
	 *
	 */
	private Integer regUsers;
	/*
	 *
	 */
	private Integer newDevices;
	/*
	 *
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
	private Integer avgOnlineTime;
	/*
	 *
	 */
	private Integer startTimes;
	/*
	 *
	 */
	private Integer newUserPayAmount;
	/*
	 *
	 */
	private Integer newUserPayTimes;
	/*
	 *
	 */
	private Integer newUserPays;
	/*
	 *
	 */
	private Integer firstPayAmount;
	/*
	 *
	 */
	private Integer firstPayUsers;
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
	public Long getGameId(){
		return gameId;
	}
	/**
	 * 设置 [gameId]
	 * @param gameId
	 */
	public void setGameId(Long gameId){
		this.gameId = gameId;
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
	public Integer getRegUsers(){
		return regUsers;
	}
	/**
	 * 设置 [regUsers]
	 * @param regUsers 
	 */
	public void setRegUsers(Integer regUsers){
		this.regUsers=regUsers;
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
	public Integer getActiveUsers(){
		return activeUsers;
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
	public Integer getAvgOnlineTime(){
		return avgOnlineTime;
	}
	/**
	 * 设置 [avgOnlineTime]
	 * @param avgOnlineTime 
	 */
	public void setAvgOnlineTime(Integer avgOnlineTime){
		this.avgOnlineTime=avgOnlineTime;
	}
	/**
	 * @return 
	 */
	public Integer getStartTimes(){
		return startTimes;
	}
	/**
	 * 设置 [startTimes]
	 * @param startTimes 
	 */
	public void setStartTimes(Integer startTimes){
		this.startTimes=startTimes;
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
	public Integer getNewUserPayTimes(){
		return newUserPayTimes;
	}
	/**
	 * 设置 [newUserPayTimes]
	 * @param newUserPayTimes 
	 */
	public void setNewUserPayTimes(Integer newUserPayTimes){
		this.newUserPayTimes=newUserPayTimes;
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