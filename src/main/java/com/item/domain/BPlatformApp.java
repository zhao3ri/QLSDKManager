package com.item.domain;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

/** 
 *@author 作者 lilc
 * @version 创建时间：2014年12月24日 上午10:33:27
 * 类说明 平台游戏关联类
 */
public class BPlatformApp {
	private Long id;
	
	private Long platformId;
	
	private String platformName;
	
	private Long appId;
	
	private String appName;
	private Integer status=0 ;
	private Integer registStatus=0;
	@JsonIgnore
	private String configParams;
	
	private Date createTime;

	private Integer discount ;
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Integer getRegistStatus() {
		return registStatus;
	}

	public void setRegistStatus(Integer registStatus) {
		this.registStatus = registStatus;
	}

	@JsonIgnore
	public String getConfigParams() {
		return configParams;
	}

	@JsonIgnore
	public void setConfigParams(String configParams) {
		this.configParams = configParams;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
}
