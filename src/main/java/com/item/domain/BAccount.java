package com.item.domain;

import java.util.Date;

public class BAccount {
	/**
	 * 自增
	 * */
	private Long id;
	/**
	 * 平台ID
	 * */
	private Long platformId;
	/**
	 * uid
	 * */
	private String uid;
	/**
	 * 创建时间
	 * */
	private Date createTime;
	/**
	 * 平台名称
	 * */
	private String platformName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

}
