package com.item.domain.authority;

public class Auth {
	private Long id;
	private Long moduleID;
	private Long functionID;
	
	public Auth(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getModuleID() {
		return moduleID;
	}

	public void setModuleID(Long moduleID) {
		this.moduleID = moduleID;
	}

	public Long getFunctionID() {
		return functionID;
	}

	public void setFunctionID(Long functionID) {
		this.functionID = functionID;
	}
	
	
}
