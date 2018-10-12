package com.item.domain.report;

import com.item.domain.SGameMonthly;
import com.item.domain.SPlatformMonthly;

public class GameClientMonthlyReport {
	
	private Long appId;
	
	private String appName;
	
	private SGameMonthly iosCps;
	
	private SPlatformMonthly iosCpa;
	
	private SGameMonthly androidCps;
	
	private SPlatformMonthly androidCpa;

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public SGameMonthly getIosCps() {
		return iosCps;
	}

	public void setIosCps(SGameMonthly iosCps) {
		this.iosCps = iosCps;
	}

	public SPlatformMonthly getIosCpa() {
		return iosCpa;
	}

	public void setIosCpa(SPlatformMonthly iosCpa) {
		this.iosCpa = iosCpa;
	}

	public SGameMonthly getAndroidCps() {
		return androidCps;
	}

	public void setAndroidCps(SGameMonthly androidCps) {
		this.androidCps = androidCps;
	}

	public SPlatformMonthly getAndroidCpa() {
		return androidCpa;
	}

	public void setAndroidCpa(SPlatformMonthly androidCpa) {
		this.androidCpa = androidCpa;
	}



}
