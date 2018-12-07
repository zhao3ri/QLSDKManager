package com.item.domain.report;

import com.item.domain.Gamezone;
import com.item.domain.SZoneChannel;

public class SummaryZoneReport {
	
	private Long gameId;
	
	private String gameName;
	
	private String zonename;
	
	private SZoneChannel szoneChannel;
	
	private Gamezone gamezone;

	
	public Gamezone getGamezone() {
		return gamezone;
	}

	public void setGamezone(Gamezone gamezone) {
		this.gamezone = gamezone;
	}

	public SZoneChannel getSzoneChannel() {
		return szoneChannel;
	}

	public void setSzoneChannel(SZoneChannel szoneChannel) {
		this.szoneChannel = szoneChannel;
	}

	public SummaryZoneReport() {
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public String getZonename() {
		return zonename;
	}

	public void setZonename(String zonename) {
		this.zonename = zonename;
	}
	
}
