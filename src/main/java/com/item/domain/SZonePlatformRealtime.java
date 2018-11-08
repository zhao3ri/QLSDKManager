/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SZonePlatformRealtime.java  2014-12-25 17:12:35 zhouxb ]
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
public class SZonePlatformRealtime extends SGameRealtime {

    private String appName;
    /*
     *
     */
    private String zoneId;

    private String zoneName;
    /*
     *
     */
    private Integer platformId;

    private String platformName;

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
    public Integer getPlatformId() {
        return platformId;
    }

    /**
     * 设置 [platformId]
     *
     * @param platformId
     */
    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

}