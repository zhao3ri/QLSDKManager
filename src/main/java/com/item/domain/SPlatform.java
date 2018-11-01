/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SPlatform.java  2014-12-25 17:12:37 zhouxb ]
 */
package com.item.domain;

/**
 * 实体
 * <br/>
 *
 * @author zhouxb
 * @version 1.0 2014-12-25 17:12:37
 * @since JDK 1.5
 */

public class SPlatform {
    /*
     *
     */
    private Long id;
    /*
     *设备类型：Android或者ios
     */
    private Integer clientType;
    /*
     *
     */
    private Long appId;
    /*
     *
     */
    private String appName;
    /*
     *
     */
    private Integer platformId;
    /*
     *
     */
    private String platformName;
    /*
     *
    /*

     *总创角用户
     */
    private Integer totalRoleUser;
    /*
     *总注册用户
     */
    private Integer totalRegUser;
    /*
     *总激活设备
     */
    private Integer devices;
    /*
     *活跃用户
     */
    private Integer activeUsers;
    /**
     * 充值总金额
     */
    private Long payAmount;
    /*
     *充值总次数
     */
    private Integer payTimes;
    /*
     *付费总人数
     */
    private Integer payUsers;

    private Integer totalNewPayUser;

    /**
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 [id]
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return
     */
    public Integer getClientType() {
        return clientType;
    }

    /**
     * 设置 [clientType]
     *
     * @param clientType
     */
    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    /**
     * @return
     */
    public Long getAppId() {
        return appId;
    }

    /**
     * 设置 [appId]
     *
     * @param appId
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * @return
     */
    public Integer getPlatformId() {
        return platformId == null ? 0 : platformId;
    }

    /**
     * 设置 [platformId]
     *
     * @param platformId
     */
    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    /**
     * @return 总创角用户
     */
    public Integer getTotalRoleUser() {
        return totalRoleUser == null ? 0 : totalRoleUser;
    }

    /**
     * 设置 总创角用户[totalRoleUser]
     *
     * @param totalRoleUser 总创角用户
     */
    public void setTotalRoleUser(Integer totalRoleUser) {
        this.totalRoleUser = totalRoleUser;
    }

    /**
     * @return 总注册用户
     */
    public Integer getTotalRegUser() {
        return totalRegUser == null ? 0 : totalRegUser;
    }

    /**
     * 设置 总注册用户[totalRegUser]
     *
     * @param totalRegUser 总注册用户
     */
    public void setTotalRegUser(Integer totalRegUser) {
        this.totalRegUser = totalRegUser;
    }

    /**
     * @return 总激活设备
     */
    public Integer getDevices() {
        return devices == null ? 0 : devices;
    }

    /**
     * 设置 总激活设备[devices]
     *
     * @param devices 总激活设备
     */
    public void setDevices(Integer devices) {
        this.devices = devices;
    }

    /**
     * @return 充值总金额
     */
    public Long getPayAmount() {
        return payAmount == null ? 0 : payAmount;
    }

    /**
     * 设置 充值总金额[payAmount]
     *
     * @param payAmount 充值总金额
     */
    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * @return 充值总次数
     */
    public Integer getPayTimes() {
        return payTimes == null ? 0 : payTimes;
    }

    /**
     * 设置 充值总次数[payTimes]
     *
     * @param payTimes 充值总次数
     */
    public void setPayTimes(Integer payTimes) {
        this.payTimes = payTimes;
    }

    /**
     * @return 付费总人数
     */
    public Integer getPayUsers() {
        return payUsers == null ? 0 : payUsers;
    }

    /**
     * 设置 付费总人数[payUsers]
     *
     * @param payUsers 付费总人数
     */
    public void setPayUsers(Integer payUsers) {
        this.payUsers = payUsers;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public Integer getActiveUsers() {
        return activeUsers == null ? 0 : activeUsers;
    }

    public void setActiveUsers(Integer activeUsers) {
        this.activeUsers = activeUsers;
    }

    public Integer getTotalNewPayUser() {
        return totalNewPayUser == null ? 0 : totalNewPayUser;
    }

    public void setTotalNewPayUser(Integer totalNewPayUser) {
        this.totalNewPayUser = totalNewPayUser;
    }
}