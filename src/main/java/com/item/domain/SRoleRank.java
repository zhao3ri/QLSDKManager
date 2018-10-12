package com.item.domain;

import java.util.Date;

/** 
 *@author 作者 lilc
 * @version 创建时间：2015年1月9日 下午5:25:45
 * 类说明
 */
public class SRoleRank {
	private Integer id;
	private String uid;
	private String roleid;
	private String rolename;
	private Long appid;
	private Integer clientType;
	private String appName;
	private Integer amount;
	private Integer platformid;
	private String platformName;
	private Date lastlogin;
	private  String startDate;
	private  String endDate;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Integer getClientType() {
		return clientType;
	}
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getLastLoginDate() {
		return lastlogin;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastlogin= lastLoginDate;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleId(String roleId) {
		this.roleid = roleId;
	}
	public String getRoleName() {
		return rolename;
	}
	public void setRoleName(String roleName) {
		this.rolename = roleName;
	}
	public Long getAppId() {
		return appid;
	}
	public void setAppId(Long appId) {
		this.appid = appId;
	}
	public Integer getPlatformId() {
		return platformid;
	}
	public void setPlatformId(Integer platformId) {
		this.platformid = platformId;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
}
