/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SZoneChannelRealtime.java  2014-12-25 17:12:35 zhouxb ]
 */
package com.item.domain;

/**
 * 实体
 * <br/>
 *
 * @author zhouxb
 * @version 1.0 2014-12-25 17:12:35
 * @since JDK 1.5
 */
public class SZoneChannelRealtime extends SGameRealtime {

    private String gameName;
    /*
     *
     */
    private String zoneId;

    private String zoneName;
    /*
     *
     */
    private Integer channelId;

    private String channelName;

    /**
     * @return
     */
    public String getZoneId() {
        return zoneId;
    }

    /**
     * 设置 [zoneId]
     *
     * @param zoneId
     */
    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    /**
     * @return
     */
    public Integer getChannelId() {
        return channelId;
    }

    /**
     * 设置 [channelId]
     *
     * @param channelId
     */
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

}