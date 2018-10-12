package com.item.domain.authority;

public class StatBaseEntity {	

	//用于查询
	private String statStartDate;
	private String statEndDate;
	
	private String channelCompanyName;//渠道商户名称
	
	public StatBaseEntity()
	{
		
	}
	
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

	public String getChannelCompanyName() {
		return channelCompanyName;
	}

	public void setChannelCompanyName(String channelCompanyName) {
		this.channelCompanyName = channelCompanyName;
	}

}
