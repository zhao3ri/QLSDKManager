/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SChannelRealtime.java  2014-12-25 17:12:36 zhouxb ]
 */
package com.item.domain;

/**
 * 实体
 * <br/>
 *
 * @author zhouxb
 * @version 1.0 2014-12-25 17:12:36
 * @since JDK 1.5
 */
public class SChannelRealtime extends SGameRealtime {
    private Integer channelId;

    /**
     * @return
     */
    public Integer getChannelId() {
        return channelId;
    }

    /**
     * 设置 [channelId]
     *
     * @param channelId
     */
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

}