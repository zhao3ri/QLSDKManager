package com.item.domain;

public class Gamezone {
	/** 
	 * 自增
	 * */
	private Long id;
	/** 
	 * 分区ID
	 * 唯一
	 * */
	private String zoneId;
	/** 
	 * 分区名称
	 * */
	private String zoneName;
	/** 
	 * 游戏ID
	 * */
	private Long gameId;
	
	private String gameName;
	
	private int isHave;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getZoneId() {
		return zoneId;
	}
	
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	
	public String getZoneName() {
		return zoneName;
	}
	
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
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

	public int getIsHave() {
		return isHave;
	}

	public void setIsHave(int isHave) {
		this.isHave = isHave;
	}

}
