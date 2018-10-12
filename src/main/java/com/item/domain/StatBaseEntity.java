package com.item.domain;

public class StatBaseEntity {	

	//用于查询
	private String statStartDate;
	private String statEndDate;
	private String statDate;
	
	public String getStatStartDate() {
		return statStartDate;
	}

	public void setStatStartDate(String statStartDate) {
		this.statStartDate = statStartDate;
	}

	public String getStatEndDate() {
		return statEndDate;
	}

	public void setStatEndDate(String statEndDate) {
		this.statEndDate = statEndDate;
	}

	public String getStatDate() {
		return statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}
}
