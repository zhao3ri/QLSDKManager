/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:BBehaviorUser.java  2015-01-16 17:27:03 zhouxb ]
 */
package com.item.domain;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *  实体
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2015-01-16 17:27:03
 * @since JDK 1.5
 */
 public class BBehaviorUser implements Serializable{
	 
	private static final long serialVersionUID = 9180809908119947455L;
	
	@JsonIgnore
	private String uid;
	@JsonIgnore
    private Integer platformId;
	@JsonIgnore
    private Long gameId;
	@JsonIgnore
	private Integer clientType;
	@JsonIgnore
	private String zoneId;
	@JsonIgnore
	private String data;
		
	private Long firstInTime;
	private Long lastLoginTime;
	private Integer loginTimesToday;
	private Long lastHeartTime;
	private Long lastLogoutTime;
	private Long firstRoleTime;
	private Long firstPayTime;
	private Long lastPayTime;
	private Integer payTimesToday;
	private Long loginRecord;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Integer getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public Integer getClientType() {
		return clientType;
	}
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Long getFirstInTime() {
		return firstInTime;
	}
	public void setFirstInTime(Long firstInTime) {
		this.firstInTime = firstInTime;
	}
	public Long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Integer getLoginTimesToday() {
		return loginTimesToday;
	}
	public void setLoginTimesToday(Integer loginTimesToday) {
		this.loginTimesToday = loginTimesToday;
	}
	public Long getLastHeartTime() {
		return lastHeartTime;
	}
	public void setLastHeartTime(Long lastHeartTime) {
		this.lastHeartTime = lastHeartTime;
	}
	public Long getLastLogoutTime() {
		return lastLogoutTime;
	}
	public void setLastLogoutTime(Long lastLogoutTime) {
		this.lastLogoutTime = lastLogoutTime;
	}
	public Long getFirstRoleTime() {
		return firstRoleTime;
	}
	public void setFirstRoleTime(Long firstRoleTime) {
		this.firstRoleTime = firstRoleTime;
	}
	public Long getFirstPayTime() {
		return firstPayTime;
	}
	public void setFirstPayTime(Long firstPayTime) {
		this.firstPayTime = firstPayTime;
	}
	public Long getLastPayTime() {
		return lastPayTime;
	}
	public void setLastPayTime(Long lastPayTime) {
		this.lastPayTime = lastPayTime;
	}
	public Integer getPayTimesToday() {
		return payTimesToday;
	}
	public void setPayTimesToday(Integer payTimesToday) {
		this.payTimesToday = payTimesToday;
	}
	public Long getLoginRecord() {
		return loginRecord;
	}
	public void setLoginRecord(Long loginRecord) {
		this.loginRecord = loginRecord;
	}
 }