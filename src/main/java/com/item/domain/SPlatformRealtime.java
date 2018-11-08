/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SPlatformRealtime.java  2014-12-25 17:12:36 zhouxb ]
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
public class SPlatformRealtime extends SGameRealtime {
    private Integer platformId;

    /**
     * @return
     */
    public Integer getPlatformId() {
        return platformId;
    }

    /**
     * 设置 [platformId]
     *
     * @param platformId
     */
    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

}