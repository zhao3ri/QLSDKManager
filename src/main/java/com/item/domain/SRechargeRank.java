package com.item.domain;

import java.util.Date;

/**
 * @author 作者 lilc
 * @version 创建时间：2015年1月9日 下午5:25:45
 * 类说明
 */
public class SRechargeRank {
    private Integer id;
    private String uid;
    private Integer gameId;
    private String gameName;
    private Integer channelId;
    private String channelName;
    private Integer amount;
    private Date lastlogin;
    private String startDate;
    private String endDate;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getPlatformId() {
        return channelId;
    }

    public void setPlatformId(Integer platformId) {
        this.channelId = platformId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Date getLastLoginDate() {
        return lastlogin;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastlogin = lastLoginDate;
    }
}
