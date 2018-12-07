/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SRolePlayTimeDaily.java  2015-05-19 09:54:36 zhouxb ]
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
 public class SRolePlayTimeDaily{
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
	private Integer channelId;
	/*
	 *
	 */
	private String uid;
	/*
	 *
	 */
	private String zoneId;
	/*
	 *
	 */
	private String roleId;
	/*
	 *
	 */
	private String roleName;
	/*
	 *
	 */
	private Integer playTime;
	/*
	 *
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
	public Integer getChannelId(){
		return channelId;
	}
	/**
	 * 设置 [channelId]
	 * @param channelId
	 */
	public void setChannelId(Integer channelId){
		this.channelId = channelId;
	}
	/**
	 * @return 
	 */
	public String getUid(){
		return uid;
	}
	/**
	 * 设置 [uid]
	 * @param uid 
	 */
	public void setUid(String uid){
		this.uid=uid;
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
	public String getRoleId(){
		return roleId;
	}
	/**
	 * 设置 [roleId]
	 * @param roleId 
	 */
	public void setRoleId(String roleId){
		this.roleId=roleId;
	}
	/**
	 * @return 
	 */
	public String getRoleName(){
		return roleName;
	}
	/**
	 * 设置 [roleName]
	 * @param roleName 
	 */
	public void setRoleName(String roleName){
		this.roleName=roleName;
	}
	/**
	 * @return 
	 */
	public Integer getPlayTime(){
		return playTime;
	}
	/**
	 * 设置 [playTime]
	 * @param playTime 
	 */
	public void setPlayTime(Integer playTime){
		this.playTime=playTime;
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
}