package com.item.domain.report;

import com.item.domain.SGame;
import com.item.domain.SPlatform;

public class GameClientReport {

    private Long gameId;

    private String gameName;

    private SGame iosCps;

    private SPlatform iosCpa;

    private SGame androidCps;

    private SPlatform androidCpa;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long appId) {
        this.gameId = appId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
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
