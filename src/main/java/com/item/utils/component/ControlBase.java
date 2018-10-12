package com.item.utils.component;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author liuxh
 * @version 创建时间：2013-1-16 下午03:53:41
 *
 * 类说明：
 *
 */
public class ControlBase extends TagSupport{

	private static final long serialVersionUID = -3202671241651840810L;
	
	private String name;	//控件名称
	private String id;		//控件ID
	private String value="";//需要选中的项
	private String clazz;	//控件的class
	
	private String parameters;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getParameters() {
		String nameStr=name==null?"":" name='"+name+"'";
		String idStr=id==null?"":" id='"+id+"'";
		String clazzStr=clazz==null?"":" class='"+clazz+"'";
		
		this.parameters=nameStr+idStr+clazzStr;
		
		return parameters;
	}
}
