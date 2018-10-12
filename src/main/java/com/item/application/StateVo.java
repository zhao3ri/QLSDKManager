package com.item.application;
/**
 * @author liuxh
 * @version 创建时间：2013-1-16 下午05:27:56
 *
 * 类说明：状态实体
 *
 */
public class StateVo {

	private String id;	//值
	private String name;	//名称
	private String color="black";	//颜色
	private String isshow="true";	//是否显示在下拉框里面
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	
}
