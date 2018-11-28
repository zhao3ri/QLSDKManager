/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SRoleDaily.java  2015-05-19 09:54:36 zhouxb ]
 */
package com.item.domain;
/**
 *  实体
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2015-05-19 09:54:36
 * @since JDK 1.5
 */
 public class SRoleDaily{
	/*
	 *
	 */
	private Long id;
	/*
	 *
	 */
	private Integer clientType;
	/*
	 *
	 */
	private Long gameId;
	/*
	 *
	 */
	private Integer platformId;
	/*
	 *
	 */
	private String zoneId;
	/*
	 *
	 */
	private Integer roleEstablishs;
	/*
	 *
	 */
	private Integer roleLogins;
	/*
	 *
	 */
	private Integer roleFirstLogins;
	/*
	 *
	 */
	private Integer loginTimes;
	/*
	 *
	 */
	private Integer lossRoleByDay;
	/*
	 *
	 */
	private Integer lossRoleByWeek;
	/*
	 *
	 */
	private Integer lossRoleByMonth;
	
	private Integer pre7dayLogins;
	
	private Integer preWeekLogins;
	
	private Integer preMonthLogins;
	/*
	 *
	 */
	private Integer activeRoles3;
	/*
	 *
	 */
	private Integer activeByWeek;
	/*
	 *
	 */
	private Integer activeByMonth;
	/*
	 *
	 */
	private Integer keepRole1;
	/*
	 *
	 */
	private Integer keepRole2;
	/*
	 *
	 */
	private Integer keepRole3;
	/*
	 *
	 */
	private Integer keepRole4;
	/*
	 *
	 */
	private Integer keepRole5;
	/*
	 *
	 */
	private Integer keepRole6;
	/*
	 *
	 */
	private Integer keepRole7;
	/*
	 *
	 */
	private Integer keepRole15;
	/*
	 *
	 */
	private Integer keepRole30;
	/*
	 *
	 */
	private Integer currentRoles;
	/*
	 *
	 */
	private Integer roles;
	/*
	 *
	 */
	private Integer amount;
	/*
	 *
	 */
	private Integer payTimes;
	/*
	 *
	 */
	private Integer maxAmount;
	
	private Integer currentMaxAmount;
	/*
	 *
	 */
	private Integer currentAmount;
	/*
	 *
	 */
	private Integer activePayByDay;
	/*
	 *
	 */
	private Integer activePayByWeek;
	/*
	 *
	 */
	private Integer activePayByMonth;
	/*
	 *
	 */
	private Integer lossPayRoles;
	/*
	 *
	 */
	private Integer firstPayRoles;
	/*
	 *
	 */
	private Integer amountByWeek;
	/*
	 *
	 */
	private Integer payRolesByWeek;
	/*
	 *
	 */
	private Integer roleEstablishsByWeek;
	/*
	 *
	 */
	private java.util.Date statDate;
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
	public Integer getClientType(){
		return clientType;
	}
	/**
	 * 设置 [clientType]
	 * @param clientType 
	 */
	public void setClientType(Integer clientType){
		this.clientType=clientType;
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
	public Integer getPlatformId(){
		return platformId;
	}
	/**
	 * 设置 [platformId]
	 * @param platformId 
	 */
	public void setPlatformId(Integer platformId){
		this.platformId=platformId;
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
	/**
	 * @return 
	 */
	public Integer getRoleEstablishs(){
		return roleEstablishs;
	}
	/**
	 * 设置 [roleEstablishs]
	 * @param roleEstablishs 
	 */
	public void setRoleEstablishs(Integer roleEstablishs){
		this.roleEstablishs=roleEstablishs;
	}
	/**
	 * @return 
	 */
	public Integer getRoleLogins(){
		return roleLogins;
	}
	/**
	 * 设置 [roleLogins]
	 * @param roleLogins 
	 */
	public void setRoleLogins(Integer roleLogins){
		this.roleLogins=roleLogins;
	}
	/**
	 * @return 
	 */
	public Integer getRoleFirstLogins(){
		return roleFirstLogins;
	}
	/**
	 * 设置 [roleFirstLogins]
	 * @param roleFirstLogins 
	 */
	public void setRoleFirstLogins(Integer roleFirstLogins){
		this.roleFirstLogins=roleFirstLogins;
	}
	/**
	 * @return 
	 */
	public Integer getLoginTimes(){
		return loginTimes;
	}
	/**
	 * 设置 [loginTimes]
	 * @param loginTimes 
	 */
	public void setLoginTimes(Integer loginTimes){
		this.loginTimes=loginTimes;
	}
	/**
	 * @return 
	 */
	public Integer getLossRoleByDay(){
		return lossRoleByDay;
	}
	/**
	 * 设置 [lossRoleByDay]
	 * @param lossRoleByDay 
	 */
	public void setLossRoleByDay(Integer lossRoleByDay){
		this.lossRoleByDay=lossRoleByDay;
	}
	/**
	 * @return 
	 */
	public Integer getLossRoleByWeek(){
		return lossRoleByWeek;
	}
	/**
	 * 设置 [lossRoleByWeek]
	 * @param lossRoleByWeek 
	 */
	public void setLossRoleByWeek(Integer lossRoleByWeek){
		this.lossRoleByWeek=lossRoleByWeek;
	}
	/**
	 * @return 
	 */
	public Integer getLossRoleByMonth(){
		return lossRoleByMonth;
	}
	/**
	 * 设置 [lossRoleByMonth]
	 * @param lossRoleByMonth 
	 */
	public void setLossRoleByMonth(Integer lossRoleByMonth){
		this.lossRoleByMonth=lossRoleByMonth;
	}
	/**
	 * @return 
	 */
	public Integer getActiveRoles3(){
		return activeRoles3;
	}
	/**
	 * 设置 [activeRoles3]
	 * @param activeRoles3 
	 */
	public void setActiveRoles3(Integer activeRoles3){
		this.activeRoles3=activeRoles3;
	}
	/**
	 * @return 
	 */
	public Integer getActiveByWeek(){
		return activeByWeek;
	}
	/**
	 * 设置 [activeByWeek]
	 * @param activeByWeek 
	 */
	public void setActiveByWeek(Integer activeByWeek){
		this.activeByWeek=activeByWeek;
	}
	/**
	 * @return 
	 */
	public Integer getActiveByMonth(){
		return activeByMonth;
	}
	/**
	 * 设置 [activeByMonth]
	 * @param activeByMonth 
	 */
	public void setActiveByMonth(Integer activeByMonth){
		this.activeByMonth=activeByMonth;
	}
	/**
	 * @return 
	 */
	public Integer getKeepRole1(){
		return keepRole1;
	}
	/**
	 * 设置 [keepRole1]
	 * @param keepRole1 
	 */
	public void setKeepRole1(Integer keepRole1){
		this.keepRole1=keepRole1;
	}
	/**
	 * @return 
	 */
	public Integer getKeepRole2(){
		return keepRole2;
	}
	/**
	 * 设置 [keepRole2]
	 * @param keepRole2 
	 */
	public void setKeepRole2(Integer keepRole2){
		this.keepRole2=keepRole2;
	}
	/**
	 * @return 
	 */
	public Integer getKeepRole3(){
		return keepRole3;
	}
	/**
	 * 设置 [keepRole3]
	 * @param keepRole3 
	 */
	public void setKeepRole3(Integer keepRole3){
		this.keepRole3=keepRole3;
	}
	/**
	 * @return 
	 */
	public Integer getKeepRole4(){
		return keepRole4;
	}
	/**
	 * 设置 [keepRole4]
	 * @param keepRole4 
	 */
	public void setKeepRole4(Integer keepRole4){
		this.keepRole4=keepRole4;
	}
	/**
	 * @return 
	 */
	public Integer getKeepRole5(){
		return keepRole5;
	}
	/**
	 * 设置 [keepRole5]
	 * @param keepRole5 
	 */
	public void setKeepRole5(Integer keepRole5){
		this.keepRole5=keepRole5;
	}
	/**
	 * @return 
	 */
	public Integer getKeepRole6(){
		return keepRole6;
	}
	/**
	 * 设置 [keepRole6]
	 * @param keepRole6 
	 */
	public void setKeepRole6(Integer keepRole6){
		this.keepRole6=keepRole6;
	}
	/**
	 * @return 
	 */
	public Integer getKeepRole7(){
		return keepRole7;
	}
	/**
	 * 设置 [keepRole7]
	 * @param keepRole7 
	 */
	public void setKeepRole7(Integer keepRole7){
		this.keepRole7=keepRole7;
	}
	/**
	 * @return 
	 */
	public Integer getKeepRole15(){
		return keepRole15;
	}
	/**
	 * 设置 [keepRole15]
	 * @param keepRole15 
	 */
	public void setKeepRole15(Integer keepRole15){
		this.keepRole15=keepRole15;
	}
	/**
	 * @return 
	 */
	public Integer getKeepRole30(){
		return keepRole30;
	}
	/**
	 * 设置 [keepRole30]
	 * @param keepRole30 
	 */
	public void setKeepRole30(Integer keepRole30){
		this.keepRole30=keepRole30;
	}
	/**
	 * @return 
	 */
	public Integer getCurrentRoles(){
		return currentRoles;
	}
	/**
	 * 设置 [currentRoles]
	 * @param currentRoles 
	 */
	public void setCurrentRoles(Integer currentRoles){
		this.currentRoles=currentRoles;
	}
	/**
	 * @return 
	 */
	public Integer getRoles(){
		return roles;
	}
	/**
	 * 设置 [roles]
	 * @param roles 
	 */
	public void setRoles(Integer roles){
		this.roles=roles;
	}
	/**
	 * @return 
	 */
	public Integer getAmount(){
		return amount;
	}
	/**
	 * 设置 [amount]
	 * @param amount 
	 */
	public void setAmount(Integer amount){
		this.amount=amount;
	}
	/**
	 * @return 
	 */
	public Integer getPayTimes(){
		return payTimes;
	}
	/**
	 * 设置 [payTimes]
	 * @param payTimes 
	 */
	public void setPayTimes(Integer payTimes){
		this.payTimes=payTimes;
	}
	/**
	 * @return 
	 */
	public Integer getMaxAmount(){
		return maxAmount;
	}
	/**
	 * 设置 [maxAmount]
	 * @param maxAmount 
	 */
	public void setMaxAmount(Integer maxAmount){
		this.maxAmount=maxAmount;
	}
	/**
	 * @return 
	 */
	public Integer getCurrentAmount(){
		return currentAmount;
	}
	/**
	 * 设置 [currentAmount]
	 * @param currentAmount 
	 */
	public void setCurrentAmount(Integer currentAmount){
		this.currentAmount=currentAmount;
	}
	/**
	 * @return 
	 */
	public Integer getActivePayByDay(){
		return activePayByDay;
	}
	/**
	 * 设置 [activePayByDay]
	 * @param activePayByDay 
	 */
	public void setActivePayByDay(Integer activePayByDay){
		this.activePayByDay=activePayByDay;
	}
	/**
	 * @return 
	 */
	public Integer getActivePayByWeek(){
		return activePayByWeek;
	}
	/**
	 * 设置 [activePayByWeek]
	 * @param activePayByWeek 
	 */
	public void setActivePayByWeek(Integer activePayByWeek){
		this.activePayByWeek=activePayByWeek;
	}
	/**
	 * @return 
	 */
	public Integer getActivePayByMonth(){
		return activePayByMonth;
	}
	/**
	 * 设置 [activePayByMonth]
	 * @param activePayByMonth 
	 */
	public void setActivePayByMonth(Integer activePayByMonth){
		this.activePayByMonth=activePayByMonth;
	}
	/**
	 * @return 
	 */
	public Integer getLossPayRoles(){
		return lossPayRoles;
	}
	/**
	 * 设置 [lossPayRoles]
	 * @param lossPayRoles 
	 */
	public void setLossPayRoles(Integer lossPayRoles){
		this.lossPayRoles=lossPayRoles;
	}
	/**
	 * @return 
	 */
	public Integer getFirstPayRoles(){
		return firstPayRoles;
	}
	/**
	 * 设置 [firstPayRoles]
	 * @param firstPayRoles 
	 */
	public void setFirstPayRoles(Integer firstPayRoles){
		this.firstPayRoles=firstPayRoles;
	}
	/**
	 * @return 
	 */
	public Integer getAmountByWeek(){
		return amountByWeek;
	}
	/**
	 * 设置 [amountByWeek]
	 * @param amountByWeek 
	 */
	public void setAmountByWeek(Integer amountByWeek){
		this.amountByWeek=amountByWeek;
	}
	/**
	 * @return 
	 */
	public Integer getPayRolesByWeek(){
		return payRolesByWeek;
	}
	/**
	 * 设置 [payRolesByWeek]
	 * @param payRolesByWeek 
	 */
	public void setPayRolesByWeek(Integer payRolesByWeek){
		this.payRolesByWeek=payRolesByWeek;
	}
	/**
	 * @return 
	 */
	public Integer getRoleEstablishsByWeek(){
		return roleEstablishsByWeek;
	}
	/**
	 * 设置 [roleEstablishsByWeek]
	 * @param roleEstablishsByWeek 
	 */
	public void setRoleEstablishsByWeek(Integer roleEstablishsByWeek){
		this.roleEstablishsByWeek=roleEstablishsByWeek;
	}
	/**
	 * @return 
	 */
	public java.util.Date getStatDate(){
		return statDate;
	}
	/**
	 * 设置 [statDate]
	 * @param statDate 
	 */
	public void setStatDate(java.util.Date statDate){
		this.statDate=statDate;
	}
	public Integer getCurrentMaxAmount() {
		return currentMaxAmount;
	}
	public void setCurrentMaxAmount(Integer currentMaxAmount) {
		this.currentMaxAmount = currentMaxAmount;
	}
	public Integer getPre7dayLogins() {
		return pre7dayLogins;
	}
	public void setPre7dayLogins(Integer pre7dayLogins) {
		this.pre7dayLogins = pre7dayLogins;
	}
	public Integer getPreWeekLogins() {
		return preWeekLogins;
	}
	public void setPreWeekLogins(Integer preWeekLogins) {
		this.preWeekLogins = preWeekLogins;
	}
	public Integer getPreMonthLogins() {
		return preMonthLogins;
	}
	public void setPreMonthLogins(Integer preMonthLogins) {
		this.preMonthLogins = preMonthLogins;
	}
}