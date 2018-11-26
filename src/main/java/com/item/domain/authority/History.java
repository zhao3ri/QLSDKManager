/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:ZDOP,Id:History.java  2013-01-15 10:48:28 liuxh ]
 */
package com.item.domain.authority;

/**
 * 历史记录表 实体
 * <br/>
 * 
 * @author liuxh
 * @version 1.0 2013-01-15 10:48:28
 * @since JDK 1.5
 */
 public class History extends StatBaseEntity{
	/*
	 *主键
	 */
	private Long id;
	/*
	 *操作者ID
	 */
	private Long oid;
	/*
	 *操作者名称
	 */
	private String sysName;
	/*
	 * 记录ID
	 */
	private Long rid;
	/*
	 *操作模块key
	 */
	private String omkey;
	/*
	 *操作模块名称
	 */
	private String omname;
	/*
	 *操作动作
	 */
	private String oaction;
	/*
	 *操作属性key
	 */
	private String opkey;
	/*
	 *操作属性名称
	 */
	private String opname;
	/*
	 *属性原值
	 */
	private String ovalue;
	/*
	 *属性现值
	 */
	private String pvalue;
	/*
	 *创建时间
	 */
	private java.util.Date inserttime;
	
	//关联查询用户
	private User user;
	
	/*
	 * 联合 操作模块key
	 */
	private String uniteOmkey;
	
	/*
	 * 联合 记录ID
	 */
	private String uniteRid;
	
	/**
	 * @return 主键
	 */
	public Long getId(){
		return id;
	}
	/**
	 * 设置 主键[id]
	 * @param id 主键
	 */
	public void setId(Long id){
		this.id=id;
	}
	/**
	 * @return 操作者ID
	 */
	public Long getOid(){
		return oid;
	}
	/**
	 * 设置 操作者ID[oid]
	 * @param oid 操作者ID
	 */
	public void setOid(Long oid){
		this.oid=oid;
	}
	/**
	 * @return 操作模块key
	 */
	public String getOmkey(){
		return omkey;
	}
	/**
	 * 设置 操作模块key[omkey]
	 * @param omkey 操作模块key
	 */
	public void setOmkey(String omkey){
		this.omkey=omkey;
	}
	/**
	 * @return 操作模块名称
	 */
	public String getOmname(){
		return omname;
	}
	/**
	 * 设置 操作模块名称[omname]
	 * @param omname 操作模块名称
	 */
	public void setOmname(String omname){
		this.omname=omname;
	}
	/**
	 * @return 操作动作
	 */
	public String getOaction(){
		return oaction;
	}
	/**
	 * 设置 操作动作[oaction]
	 * @param oaction 操作动作
	 */
	public void setOaction(String oaction){
		this.oaction=oaction;
	}
	/**
	 * @return 操作属性key
	 */
	public String getOpkey(){
		return opkey;
	}
	/**
	 * 设置 操作属性key[opkey]
	 * @param opkey 操作属性key
	 */
	public void setOpkey(String opkey){
		this.opkey=opkey;
	}
	/**
	 * @return 操作属性名称
	 */
	public String getOpname(){
		return opname;
	}
	/**
	 * 设置 操作属性名称[opname]
	 * @param opname 操作属性名称
	 */
	public void setOpname(String opname){
		this.opname=opname;
	}
	/**
	 * @return 属性原值
	 */
	public String getOvalue(){
		return ovalue;
	}
	/**
	 * 设置 属性原值[ovalue]
	 * @param ovalue 属性原值
	 */
	public void setOvalue(String ovalue){
		this.ovalue=ovalue;
	}
	/**
	 * @return 属性现值
	 */
	public String getPvalue(){
		return pvalue;
	}
	/**
	 * 设置 属性现值[pvalue]
	 * @param pvalue 属性现值
	 */
	public void setPvalue(String pvalue){
		this.pvalue=pvalue;
	}
	/**
	 * @return 创建时间
	 */
	public java.util.Date getInserttime(){
		return inserttime;
	}
	/**
	 * 设置 创建时间[inserttime]
	 * @param inserttime 创建时间
	 */
	public void setInserttime(java.util.Date inserttime){
		this.inserttime=inserttime;
	}
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUniteOmkey() {
		return uniteOmkey;
	}
	public void setUniteOmkey(String uniteOmkey) {
		this.uniteOmkey = uniteOmkey;
	}
	public String getUniteRid() {
		return uniteRid;
	}
	public void setUniteRid(String uniteRid) {
		this.uniteRid = uniteRid;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	
	
}