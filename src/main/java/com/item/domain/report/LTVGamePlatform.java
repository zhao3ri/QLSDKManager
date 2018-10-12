package com.item.domain.report;

import java.io.Serializable;
import java.util.Date;

public class LTVGamePlatform implements Serializable {
    private Long id;

    public int getRegistUser() {
        return registUser;
    }

    public void setRegistUser(int registUser) {
        this.registUser = registUser;
    }

    private int registUser ;
    private Byte clientType;

    private Long appId;

    private String zoneId;

    private Integer platformId;

    private Date statDate;

    private Integer ltv1=0;
    private Integer ltv2=0;

    private Integer ltv3=0;

    private Integer ltv4=0;

    private Integer ltv5=0;

    private Integer ltv6=0;

    private Integer ltv7=0;

    private Integer ltv14=0;

    private Integer ltv30=0;
    private Integer ltv60=0;
    private Integer ltv90=0;

    private static final long serialVersionUID = 1L;

    public Integer getLtv2() {
        return ltv2;
    }

    public void setLtv2(Integer ltv2) {
        this.ltv2 = ltv2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getClientType() {
        return clientType;
    }

    public void setClientType(Byte clientType) {
        this.clientType = clientType;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId == null ? null : zoneId.trim();
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Date getStatDate() {
        return statDate;
    }

    public void setStatDate(Date statDate) {
        this.statDate = statDate;
    }

    public Integer getLtv1() {
        return ltv1;
    }

    public void setLtv1(Integer ltv1) {
        this.ltv1 = ltv1;
    }

    public Integer getLtv3() {
        return ltv3;
    }

    public void setLtv3(Integer ltv3) {
        this.ltv3 = ltv3;
    }

    public Integer getLtv4() {
        return ltv4;
    }

    public void setLtv4(Integer ltv4) {
        this.ltv4 = ltv4;
    }

    public Integer getLtv5() {
        return ltv5;
    }

    public void setLtv5(Integer ltv5) {
        this.ltv5 = ltv5;
    }

    public Integer getLtv6() {
        return ltv6;
    }

    public void setLtv6(Integer ltv6) {
        this.ltv6 = ltv6;
    }

    public Integer getLtv7() {
        return ltv7;
    }

    public void setLtv7(Integer ltv7) {
        this.ltv7 = ltv7;
    }

    public Integer getLtv14() {
        return ltv14;
    }

    public void setLtv14(Integer ltv14) {
        this.ltv14 = ltv14;
    }

    public Integer getLtv30() {
        return ltv30;
    }

    public void setLtv30(Integer ltv30) {
        this.ltv30 = ltv30;
    }

    public Integer getLtv60() {
        return ltv60;
    }

    public void setLtv60(Integer ltv60) {
        this.ltv60 = ltv60;
    }

    public Integer getLtv90() {
        return ltv90;
    }

    public void setLtv90(Integer ltv90) {
        this.ltv90 = ltv90;
    }
}