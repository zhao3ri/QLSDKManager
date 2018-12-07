package com.item.domain.report;

import com.item.domain.SChannel;
import com.item.domain.SGame;

public class GameClientReport {

    private Long gameId;

    private String gameName;

    private SGame iosCps;

    private SChannel iosCpa;

    private SGame androidCps;

    private SChannel androidCpa;

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

}
