package com.item.domain.authority;

import core.module.annotation.LogAttribute;
import core.module.annotation.LogModule;

@LogModule(name="功能",key="function")
public class Function {

	private Long id;
	
	@LogAttribute(name="功能地址")
	private String functionName;
	
	@LogAttribute(name="功能名称")
	private String description;
	
	
	private Integer isFullPath;
	
	@LogAttribute(name="排序")
	private Integer functionOrder;
	
	private Integer projectType;

	public Function() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsFullPath() {
		return isFullPath;
	}

	public void setIsFullPath(Integer isFullPath) {
		this.isFullPath = isFullPath;
	}

	public Integer getFunctionOrder() {
		return functionOrder;
	}

	public void setFunctionOrder(Integer functionOrder) {
		this.functionOrder = functionOrder;
	}

	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

}
