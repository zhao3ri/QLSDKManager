package com.item.constants;

public enum TypeConstant {
	department("部门"), 
	group("分组")
	;
	
	private String value; 
	
	private TypeConstant(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
