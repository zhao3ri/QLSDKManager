package com.item.utils;

import java.util.HashMap;
import java.util.Map;

public class CleanCacheUtil {

	private Integer code;
	
	private String action;
	
	private Long id;
	
	private Integer type;
	
	private Long categoryId;
	
	public CleanCacheUtil(Integer code,String action,Long id,Integer type,Long categoryId) {
		this.code = code;
		this.action = action;
		this.id = id;
		this.type = type;
		this.categoryId = categoryId;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Map<String, Object> getParms(){
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (this.code!=null)
			paramsMap.put("code", this.code);
		if (this.action!=null)
			paramsMap.put("action", this.action);
		if (this.id!=null)
			paramsMap.put("id", this.id);
		if (this.type!=null)
			paramsMap.put("type", this.type);
		if (this.categoryId!=null)
			paramsMap.put("categoryId", this.categoryId);
		
		return paramsMap;
	}
	
	public static void main(String[] args) {
		
	}
}
