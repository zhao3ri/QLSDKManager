package com.item.domain.report;

import com.item.domain.SChannel;
import com.item.domain.SGame;

public class SummaryClientReport {
	private int gameCount;
	
	private SGame iosCps;
	
	private SChannel iosCpa;
	
	private SGame androidCps;
	
	private SChannel androidCpa;

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

	public SChannel getIosCpa() {
		return iosCpa;
	}

	public void setIosCpa(SChannel iosCpa) {
		this.iosCpa = iosCpa;
	}

	public SGame getAndroidCps() {
		return androidCps;
	}

	public void setAndroidCps(SGame androidCps) {
		this.androidCps = androidCps;
	}

	public SChannel getAndroidCpa() {
		return androidCpa;
	}

	public void setAndroidCpa(SChannel androidCpa) {
		this.androidCpa = androidCpa;
	}
	
	
	/*private SGame cpsAnriodData;
	
	private SGame cpsIosData;
	
	private SChannel cpaData;*/

	

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

	public SChannel getCpaData() {
		return cpaData;
	}

	public void setCpaData(SChannel cpaData) {
		this.cpaData = cpaData;
	}*/
	
	
}
