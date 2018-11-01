package com.item.domain.report;

import com.item.domain.SGame;
import com.item.domain.SGameMonthly;
import com.item.domain.SPlatform;
import com.item.domain.SPlatformMonthly;

public class GameClientMonthlyReport extends GameClientReport {

    private SGameMonthly iosCps;

    private SPlatformMonthly iosCpa;

    private SGameMonthly androidCps;

    private SPlatformMonthly androidCpa;

    @Override
    public SGameMonthly getIosCps() {
        return iosCps;
    }

    @Override
    public void setIosCps(SGame iosCps) {
        if (iosCps instanceof SGame)
            this.iosCps = (SGameMonthly) iosCps;
        else
            super.setIosCps(iosCps);
    }

    @Override
    public SPlatformMonthly getIosCpa() {
        return iosCpa;
    }

    @Override
    public void setIosCpa(SPlatform iosCpa) {
        if (iosCpa instanceof SPlatformMonthly)
            this.iosCpa = (SPlatformMonthly) iosCpa;
        else
            super.setIosCpa(iosCpa);
    }

    @Override
    public SGameMonthly getAndroidCps() {
        return androidCps;
    }

    @Override
    public void setAndroidCps(SGame androidCps) {
        if (androidCps instanceof SGameMonthly)
            this.androidCps = (SGameMonthly) androidCps;
        else
            super.setAndroidCps(androidCps);
    }

    @Override
    public SPlatformMonthly getAndroidCpa() {
        return androidCpa;
    }

    @Override
    public void setAndroidCpa(SPlatform androidCpa) {
        if (androidCpa instanceof SPlatformMonthly)
            this.androidCpa = (SPlatformMonthly) androidCpa;
        else
            super.setAndroidCpa(androidCpa);
    }

}
