/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:BChannelGameZone.java  2015-01-04 09:55:29 zhouxb ]
 */
package com.item.domain;
/**
 *  实体
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2015-01-04 09:55:29
 * @since JDK 1.5
 */
 public class BChannelGameZone {
	/*
	 *
	 */
	private Long id;
	/*
	 *
	 */
	private Integer channelId;
	
	private String channelName;
	/*
	 *
	 */
	private Long gameId;
	
	private String gameName;
	/*
	 *
	 */
	private String zoneId;
	
	private String zoneName;
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
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
}