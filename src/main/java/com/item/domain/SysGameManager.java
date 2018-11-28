/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SysGameManager.java  2015-05-19 10:54:05 zhouxb ]
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
 public class SysGameManager {
	/*
	 *
	 */
	private Long id;
	/*
	 *
	 */
	private Integer identityId;
	/*
	 *
	 */
	private Long gameId;
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
	public Integer getIdentityId(){
		return identityId;
	}
	/**
	 * 设置 [identityId]
	 * @param identityId
	 */
	public void setIdentityId(Integer identityId){
		this.identityId = identityId;
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
}