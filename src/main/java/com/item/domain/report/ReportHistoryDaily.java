package com.item.domain.report;

public class ReportHistoryDaily extends ReportDaily{
	private int totalRoleUser;
	private int totalRegUser;
	private int totaldevices;
	private long totalPayAmount;
	private int totalPayTimes;
	private int totalPayUsers;
	private int totalActiveUsers;
	
	public int getTotalRoleUser() {
		return totalRoleUser;
	}
	public void setTotalRoleUser(int totalRoleUser) {
		this.totalRoleUser = totalRoleUser;
	}
	public int getTotalRegUser() {
		return totalRegUser;
	}
	public void setTotalRegUser(int totalRegUser) {
		this.totalRegUser = totalRegUser;
	}
	public int getTotaldevices() {
		return totaldevices;
	}
	public void setTotaldevices(int totaldevices) {
		this.totaldevices = totaldevices;
	}
	public long getTotalPayAmount() {
		return totalPayAmount;
	}
	public void setTotalPayAmount(long totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}
	public int getTotalPayTimes() {
		return totalPayTimes;
	}
	public void setTotalPayTimes(int totalPayTimes) {
		this.totalPayTimes = totalPayTimes;
	}
	public int getTotalPayUsers() {
		return totalPayUsers;
	}
	public void setTotalPayUsers(int totalPayUsers) {
		this.totalPayUsers = totalPayUsers;
	}
	public int getTotalActiveUsers() {
		return totalActiveUsers;
	}
	public void setTotalActiveUsers(int totalActiveUsers) {
		this.totalActiveUsers = totalActiveUsers;
	}
}
