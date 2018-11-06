package com.item.domain;

import java.util.Date;

public class Game {
	/**
	 * 自增
	 * */
	private Long id;
	 /**
	  * 游戏名称
	 */
	private String appName;
	/**
	 * 密钥
	 * */
	private String secretKey;
	/**
	 * 创建时间
	 * */
	private Date createTime;
	/**
	 * 游戏开服时间
	 **/
	private java.util.Date serviceTime;

	/**
	 * 是否有权限 0：无 1：有
	 **/
	private Integer isAuth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(java.util.Date serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Integer getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
	}
}
