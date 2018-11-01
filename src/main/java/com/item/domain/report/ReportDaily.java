package com.item.domain.report;

import java.util.Date;

import com.item.utils.DateUtils;

public class ReportDaily {
    private Long id;
    private Integer clientType;
    private Long appId;
    private String appName;
    private String zoneId;
    private String zoneName;
    private Integer platformId;
    private String platformName;
    private java.util.Date statDate;
    private Integer roleUsers;
    private Integer regUsers;
    private Integer newDevices;
    private Integer newActiveDevices;
    private Integer activeUsers;
    private Integer payAmount;
    private Integer payUsers;
    private Integer payTimes;
    private Integer avgOnlineTime;
    private Integer startTimes;
    private Integer newUserPayAmount;
    private Integer newUserPays;
    private Integer newUserPayTimes;
    private Integer firstPayAmount;
    private Integer firstPayUsers;
    private String startDate;
    private String endDate;
    private Integer keepUser1;
    private Integer keepUser3;
    private Integer keepUser4;
    private Integer keepUser5;
    private Integer keepUser6;
    private Integer keepUser7;
    private Integer keepUser14;
    private Integer keepUser30;
    private Integer lossUsers;
    private Integer lossPayUsers;
    private Integer backUsers;
    private Integer backPayUsers;
    private Integer regDevices;
    private Integer roleDevices;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
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
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public java.util.Date getStatDate() {
        return statDate;
    }

    public void setStatDate(java.util.Date statDate) {
        this.statDate = statDate;
    }

    public Integer getRoleUsers() {
        return roleUsers;
    }

    public void setRoleUsers(Integer roleUsers) {
        this.roleUsers = roleUsers;
    }

    public Integer getRegUsers() {
        return regUsers;
    }

    public void setRegUsers(Integer regUsers) {
        this.regUsers = regUsers;
    }

    public Integer getNewDevices() {
        return newDevices;
    }

    public void setNewDevices(Integer newDevices) {
        this.newDevices = newDevices;
    }

    public Integer getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(Integer activeUsers) {
        this.activeUsers = activeUsers;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getPayUsers() {
        return payUsers;
    }

    public void setPayUsers(Integer payUsers) {
        this.payUsers = payUsers;
    }

    public Integer getPayTimes() {
        return payTimes;
    }

    public void setPayTimes(Integer payTimes) {
        this.payTimes = payTimes;
    }

    public Integer getNewUserPayAmount() {
        return newUserPayAmount;
    }

    public void setNewUserPayAmount(Integer newUserPayAmount) {
        this.newUserPayAmount = newUserPayAmount;
    }

    public Integer getNewUserPays() {
        return newUserPays == null ? 0 :newUserPays;
    }

    public void setNewUserPays(Integer newUserPays) {
        this.newUserPays = newUserPays;
    }

    public Integer getFirstPayAmount() {
        return firstPayAmount;
    }

    public void setFirstPayAmount(Integer firstPayAmount) {
        this.firstPayAmount = firstPayAmount;
    }

    public Integer getFirstPayUsers() {
        return firstPayUsers;
    }

    public void setFirstPayUsers(Integer firstPayUsers) {
        this.firstPayUsers = firstPayUsers;
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

    public Integer getLossUsers() {
        return lossUsers;
    }

    public void setLossUsers(Integer lossUsers) {
        this.lossUsers = lossUsers;
    }

    public Integer getLossPayUsers() {
        return lossPayUsers;
    }

    public void setLossPayUsers(Integer lossPayUsers) {
        this.lossPayUsers = lossPayUsers;
    }

    public Integer getBackUsers() {
        return backUsers;
    }

    public void setBackUsers(Integer backUsers) {
        this.backUsers = backUsers;
    }

    public Integer getBackPayUsers() {
        return backPayUsers;
    }

    public void setBackPayUsers(Integer backPayUsers) {
        this.backPayUsers = backPayUsers;
    }

    public Integer getRegDevices() {
        return regDevices;
    }

    public void setRegDevices(Integer regDevices) {
        this.regDevices = regDevices;
    }

    public Integer getRoleDevices() {
        return roleDevices;
    }

    public void setRoleDevices(Integer roleDevices) {
        this.roleDevices = roleDevices;
    }

    public Integer getAvgOnlineTime() {
        return avgOnlineTime;
    }

    public void setAvgOnlineTime(Integer avgOnlineTime) {
        this.avgOnlineTime = avgOnlineTime;
    }

    public Integer getStartTimes() {
        return startTimes;
    }

    public void setStartTimes(Integer startTimes) {
        this.startTimes = startTimes;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getNewUserPayTimes() {
        return newUserPayTimes;
    }

    public void setNewUserPayTimes(Integer newUserPayTimes) {
        this.newUserPayTimes = newUserPayTimes;
    }

    public Integer getKeepUser1() {
        return keepUser1;
    }

    public void setKeepUser1(Integer keepUser1) {
        this.keepUser1 = keepUser1;
    }

    public Integer getKeepUser3() {
        return keepUser3;
    }

    public void setKeepUser3(Integer keepUser3) {
        this.keepUser3 = keepUser3;
    }

    public Integer getKeepUser7() {
        return keepUser7;
    }

    public void setKeepUser7(Integer keepUser7) {
        this.keepUser7 = keepUser7;
    }

    public Integer getKeepUser14() {
        return keepUser14;
    }

    public void setKeepUser14(Integer keepUser14) {
        this.keepUser14 = keepUser14;
    }

    public Integer getKeepUser30() {
        return keepUser30;
    }

    public void setKeepUser30(Integer keepUser30) {
        this.keepUser30 = keepUser30;
    }

    public Integer getNewActiveDevices() {
        return newActiveDevices;
    }

    public void setNewActiveDevices(Integer newActiveDevices) {
        this.newActiveDevices = newActiveDevices;
    }

    public Integer getKeepUser4() {
        return keepUser4;
    }

    public void setKeepUser4(Integer keepUser4) {
        this.keepUser4 = keepUser4;
    }

    public Integer getKeepUser5() {
        return keepUser5;
    }

    public void setKeepUser5(Integer keepUser5) {
        this.keepUser5 = keepUser5;
    }

    public Integer getKeepUser6() {
        return keepUser6;
    }

    public void setKeepUser6(Integer keepUser6) {
        this.keepUser6 = keepUser6;
    }

    public int getDelDay() {
        return DateUtils.getIntervalDays(statDate, new Date());
    }

}
