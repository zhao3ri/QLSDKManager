/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:Sysroleappauth.java  2015-05-19 10:54:05 zhouxb ]
 */
package com.item.domain;
/**
 *  实体
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2015-05-19 10:54:05
 * @since JDK 1.5
 */
 public class Sysroleappauth{
	/*
	 *
	 */
	private Long id;
	/*
	 *
	 */
	private Integer roleId;
	/*
	 *
	 */
	private Long appId;
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
	public Integer getRoleId(){
		return roleId;
	}
	/**
	 * 设置 [roleId]
	 * @param roleId 
	 */
	public void setRoleId(Integer roleId){
		this.roleId=roleId;
	}
	/**
	 * @return 
	 */
	public Long getAppId(){
		return appId;
	}
	/**
	 * 设置 [appId]
	 * @param appId 
	 */
	public void setAppId(Long appId){
		this.appId=appId;
	}
}