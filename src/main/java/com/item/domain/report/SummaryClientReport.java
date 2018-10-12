package com.item.domain.report;

import com.item.domain.SGame;
import com.item.domain.SPlatform;

public class SummaryClientReport {
	private int gameCount;
	
	private SGame iosCps;
	
	private SPlatform iosCpa;
	
	private SGame androidCps;
	
	private SPlatform androidCpa;

	public int getGameCount() {
		return gameCount;
	}

	public void setGameCount(int gameCount) {
		this.gameCount = gameCount;
	}

	public SGame getIosCps() {
		return iosCps;
	}

	public void setIosCps(SGame iosCps) {
		this.iosCps = iosCps;
	}

	public SPlatform getIosCpa() {
		return iosCpa;
	}

	public void setIosCpa(SPlatform iosCpa) {
		this.iosCpa = iosCpa;
	}

	public SGame getAndroidCps() {
		return androidCps;
	}

	public void setAndroidCps(SGame androidCps) {
		this.androidCps = androidCps;
	}

	public SPlatform getAndroidCpa() {
		return androidCpa;
	}

	public void setAndroidCpa(SPlatform androidCpa) {
		this.androidCpa = androidCpa;
	}
	
	
	/*private SGame cpsAnriodData;
	
	private SGame cpsIosData;
	
	private SPlatform cpaData;*/

	

/*	public SGame getCpsAnriodData() {
		return cpsAnriodData;
	}

	public void setCpsAnriodData(SGame cpsAnriodData) {
		this.cpsAnriodData = cpsAnriodData;
	}

	public SGame getCpsIosData() {
		return cpsIosData;
	}

	public void setCpsIosData(SGame cpsIosData) {
		this.cpsIosData = cpsIosData;
	}

	public SPlatform getCpaData() {
		return cpaData;
	}

	public void setCpaData(SPlatform cpaData) {
		this.cpaData = cpaData;
	}*/
	
	
}
