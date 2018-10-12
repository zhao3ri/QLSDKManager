package com.item.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;

/*
 *联运平台 实体类
 */
public class SBalance {

	private Long id;
	
	private Long platformid;
	private String platfromName ;

	private int amount ;
	private Integer type ;
	private String user ;
	private Integer balance ;
	@JsonIgnore
	private  Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlatfromName() {
		return platfromName;
	}

	public void setPlatfromName(String platfromName) {
		this.platfromName = platfromName;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Long getPlatformid() {
		return platformid;
	}

	public void setPlatformid(Long platformid) {
		this.platformid = platformid;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
