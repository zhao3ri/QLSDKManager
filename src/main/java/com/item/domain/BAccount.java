package com.item.domain;

import java.util.Date;

public class BAccount {
	/**
	 * 自增
	 * */
	private Long id;
	/**
	 * 渠道ID
	 * */
	private Long channelId;
	/**
	 * uid
	 * */
	private String uid;
	/**
	 * 创建时间
	 * */
	private Date createTime;
	/**
	 * 渠道名称
	 * */
	private String channelName;

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

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

}
