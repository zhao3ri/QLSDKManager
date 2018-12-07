/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SRoleTwentyMinute.java  2015-05-19 09:54:35 zhouxb ]
 */
package com.item.domain;
/**
 *  实体
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2015-05-19 09:54:35
 * @since JDK 1.5
 */
 public class SRoleTwentyMinute{
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
	private String zoneId;
	/*
	 *
	 */
	private Integer channelId;
	/*
	 *角色创建数
	 */
	private Integer roleEstablishs;
	/*
	 *登录角色数
	 */
	private Integer roleLogins;
	/*
	 *新增登录角色(首次登录游戏的角色数)
	 */
	private Integer roleFirstLogins;
	/*
	 *登录次数
	 */
	private Integer loginTimes;
	/*
	 *在线角色数
	 */
	private Integer roleOnlines;
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
	 * @return 角色创建数
	 */
	public Integer getRoleEstablishs(){
		return roleEstablishs;
	}
	/**
	 * 设置 角色创建数[roleEstablishs]
	 * @param roleEstablishs 角色创建数
	 */
	public void setRoleEstablishs(Integer roleEstablishs){
		this.roleEstablishs=roleEstablishs;
	}
	/**
	 * @return 登录角色数
	 */
	public Integer getRoleLogins(){
		return roleLogins;
	}
	/**
	 * 设置 登录角色数[roleLogins]
	 * @param roleLogins 登录角色数
	 */
	public void setRoleLogins(Integer roleLogins){
		this.roleLogins=roleLogins;
	}
	/**
	 * @return 新增登录角色(首次登录游戏的角色数)
	 */
	public Integer getRoleFirstLogins(){
		return roleFirstLogins;
	}
	/**
	 * 设置 新增登录角色(首次登录游戏的角色数)[roleFirstLogins]
	 * @param roleFirstLogins 新增登录角色(首次登录游戏的角色数)
	 */
	public void setRoleFirstLogins(Integer roleFirstLogins){
		this.roleFirstLogins=roleFirstLogins;
	}
	/**
	 * @return 登录次数
	 */
	public Integer getLoginTimes(){
		return loginTimes;
	}
	/**
	 * 设置 登录次数[loginTimes]
	 * @param loginTimes 登录次数
	 */
	public void setLoginTimes(Integer loginTimes){
		this.loginTimes=loginTimes;
	}
	/**
	 * @return 在线角色数
	 */
	public Integer getRoleOnlines(){
		return roleOnlines;
	}
	/**
	 * 设置 在线角色数[roleOnlines]
	 * @param roleOnlines 在线角色数
	 */
	public void setRoleOnlines(Integer roleOnlines){
		this.roleOnlines=roleOnlines;
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