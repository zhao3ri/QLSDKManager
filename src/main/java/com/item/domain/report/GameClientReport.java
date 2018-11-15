package com.item.domain.report;

import com.item.domain.SGame;
import com.item.domain.SPlatform;

public class GameClientReport {

    private Long appId;

    private String appName;

    private SGame iosCps;

    private SPlatform iosCpa;

    private SGame androidCps;

    private SPlatform androidCpa;

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

}
