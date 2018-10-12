package com.item.domain.authority;

public class Data {

	private Long id;
	private String datasetName;
	private String datasetKey;
	private Long moduleID;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDatasetName() {
		return datasetName;
	}
	public void setDatasetName(String datasetName) {
		this.datasetName = datasetName;
	}
	public String getDatasetKey() {
		return datasetKey;
	}
	public void setDatasetKey(String datasetKey) {
		this.datasetKey = datasetKey;
	}
	public Long getModuleID() {
		return moduleID;
	}
	public void setModuleID(Long moduleID) {
		this.moduleID = moduleID;
	}
	
}
