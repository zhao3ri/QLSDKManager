/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SGameRealtime.java  2014-12-25 17:12:37 zhouxb ]
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
public class SGameRealtime {
    /*
     *
     */
    private Long id;
    /*
     *
     */
    private Integer clientType;
    /*
     *
     */
    private Long gameId;
    /*
     *
     */
    private java.util.Date statDate;
    /*
     *
     */
    private Integer onlineUsers;
    /*
     *
     */
    private Integer roleUsers;
    /*
     *
     */
    private Integer newDevices;
    /*
     *
     */
    private Integer payAmount;

    private Integer activeUsers;

    /*
     *新增用户数
     */
    private Integer newUsers;

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
    public long getGameId() {
        return gameId == null ? 0 : gameId;
    }

    /**
     * 设置 [gameId]
     *
     * @param gameId
     */
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    /**
     * @return
     */
    public java.util.Date getStatDate() {
        return statDate;
    }

    /**
     * 设置 [statDate]
     *
     * @param statDate
     */
    public void setStatDate(java.util.Date statDate) {
        this.statDate = statDate;
    }

    /**
     * @return
     */
    public Integer getOnlineUsers() {
        return onlineUsers == null ? 0 : onlineUsers;
    }

    /**
     * 设置 [onlineUsers]
     *
     * @param onlineUsers
     */
    public void setOnlineUsers(Integer onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    /**
     * @return
     */
    public Integer getRoleUsers() {
        return roleUsers == null ? 0 : roleUsers;
    }

    /**
     * 设置 [roleUsers]
     *
     * @param roleUsers
     */
    public void setRoleUsers(Integer roleUsers) {
        this.roleUsers = roleUsers;
    }

    /**
     * @return
     */
    public Integer getNewDevices() {
        return newDevices == null ? 0 : newDevices;
    }

    /**
     * 设置 [newDevices]
     *
     * @param newDevices
     */
    public void setNewDevices(Integer newDevices) {
        this.newDevices = newDevices;
    }

    /**
     * @return
     */
    public Integer getPayAmount() {
        return payAmount == null ? 0 : payAmount;
    }

    /**
     * 设置 [payAmount]
     *
     * @param payAmount
     */
    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getActiveUsers() {
        return activeUsers == null ? 0 : activeUsers;
    }

    public void setActiveUsers(Integer activeUsers) {
        this.activeUsers = activeUsers;
    }

    public Integer getNewUsers() {
        return newUsers == null ? 0 : newUsers;
    }

    public void setNewUsers(Integer newUsers) {
        this.newUsers = newUsers;
    }
}