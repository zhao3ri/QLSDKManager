package com.item.domain.report;

import com.item.domain.*;
import com.item.domain.SChannel;
import com.item.domain.SChannelMonthly;

public class GameClientMonthlyReport extends GameClientReport {

    private SGameMonthly iosCps;

    private SChannelMonthly iosCpa;

    private SGameMonthly androidCps;

    private SChannelMonthly androidCpa;

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
    public SChannelMonthly getIosCpa() {
        return iosCpa;
    }

    @Override
    public void setIosCpa(SChannel iosCpa) {
        if (iosCpa instanceof SChannelMonthly)
            this.iosCpa = (SChannelMonthly) iosCpa;
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
    public SChannelMonthly getAndroidCpa() {
        return androidCpa;
    }

    @Override
    public void setAndroidCpa(SChannel androidCpa) {
        if (androidCpa instanceof SChannelMonthly)
            this.androidCpa = (SChannelMonthly) androidCpa;
        else
            super.setAndroidCpa(androidCpa);
    }

}
