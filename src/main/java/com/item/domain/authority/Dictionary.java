/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:ZDOP,Id:Dictionary.java  2013-01-15 11:32:29 liuxh ]
 */
package com.item.domain.authority;

import core.module.annotation.LogAttribute;
import core.module.annotation.LogModule;

/**
 * 数据字典表 实体
 * <br/>
 * 
 * @author liuxh
 * @version 1.0 2013-01-15 11:32:29
 * @since JDK 1.5
 */
@LogModule(name="数据字典",key="dictionary")
 public class Dictionary extends SystemModule{
	/*
	 *类型 (部门、分组、计费方式、账期、广告主类型等等)
	 */
	private String dtype;
	/*
	 *名称
	 */
	@LogAttribute(name="部门名称")
	private String dname;
	/*
	 *描述
	 */
	@LogAttribute(name="描述")
	private String depict;
	/*
	 *键值
	 */
	@LogAttribute(name="部门键值")
	private String dvalue;
	/*
	 *状态(0删除；1正常（默认）；2锁定；)
	 */
	@LogAttribute(name="状态")
	private Integer state;
	/*
	 *排序(从小到大排序，默认50)
	 */
	@LogAttribute(name="排序")
	private Integer dsort;
	/*
	 *创建时间
	 */
	private java.util.Date inserttime;
	/*
	 * 
	 */
	private Dictionary dictionaryObj;
	
	/**
	 * @return 类型 (部门、分组、计费方式、账期、广告主类型等等)
	 */
	public String getDtype(){
		return dtype;
	}
	/**
	 * 设置 类型 (部门、分组、计费方式、账期、广告主类型等等)[dtype]
	 * @param dtype 类型 (部门、分组、计费方式、账期、广告主类型等等)
	 */
	public void setDtype(String dtype){
		this.dtype=dtype;
	}
	/**
	 * @return 名称
	 */
	public String getDname(){
		return dname;
	}
	/**
	 * 设置 名称[dname]
	 * @param dname 名称
	 */
	public void setDname(String dname){
		this.dname=dname;
	}
	/**
	 * @return 描述
	 */
	public String getDepict(){
		return depict;
	}
	/**
	 * 设置 描述[depict]
	 * @param depict 描述
	 */
	public void setDepict(String depict){
		this.depict=depict;
	}
	/**
	 * @return 键值
	 */
	public String getDvalue(){
		return dvalue;
	}
	/**
	 * 设置 键值[dvalue]
	 * @param dvalue 键值
	 */
	public void setDvalue(String dvalue){
		this.dvalue=dvalue;
	}
	/**
	 * @return 状态(0删除；1正常（默认）；2锁定；)
	 */
	public Integer getState(){
		return state;
	}
	/**
	 * 设置 状态(0删除；1正常（默认）；2锁定；)[state]
	 * @param state 状态(0删除；1正常（默认）；2锁定；)
	 */
	public void setState(Integer state){
		this.state=state;
	}
	/**
	 * @return 排序(从小到大排序，默认50)
	 */
	public Integer getDsort(){
		return dsort;
	}
	/**
	 * 设置 排序(从小到大排序，默认50)[dsort]
	 * @param dsort 排序(从小到大排序，默认50)
	 */
	public void setDsort(Integer dsort){
		this.dsort=dsort;
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
	public Dictionary getDictionaryObj() {
		return dictionaryObj;
	}
	public void setDictionaryObj(Dictionary dictionaryObj) {
		this.dictionaryObj = dictionaryObj;
	}
	
}