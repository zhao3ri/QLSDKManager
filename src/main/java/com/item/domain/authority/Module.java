package com.item.domain.authority;

import core.module.annotation.LogAttribute;
import core.module.annotation.LogModule;

@LogModule(name="模块",key="module")
public class Module {
	private Long id;
	
	@LogAttribute(name="模块名称")
	private String moduleName;
	
	private String moduleURL;
	
	@LogAttribute(name="排序")
	private Integer moduleOrder;
	
	private String authHtml;
	private String datasetHtml;
	
	private Integer projectType;

	public Module() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleURL() {
		return moduleURL;
	}

	public void setModuleURL(String moduleURL) {
		this.moduleURL = moduleURL;
	}

	public String getAuthHtml() {
		return authHtml;
	}

	public void setAuthHtml(String authHtml) {
		this.authHtml = authHtml;
	}

	public Integer getModuleOrder() {
		return moduleOrder;
	}

	public void setModuleOrder(Integer moduleOrder) {
		this.moduleOrder = moduleOrder;
	}
	
	public String getDatasetHtml() {
		return datasetHtml;
	}
	
	public void setDatasetHtml(String datasetHtml) {
		this.datasetHtml = datasetHtml;
	}

	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

}
