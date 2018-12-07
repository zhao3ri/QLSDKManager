/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SPlatformService.java  2014-12-25 17:12:37 zhouxb ]
 */
package com.item.service;

import com.item.domain.SChannel;
import com.item.dao.SChannelDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.module.orm.MapBean;

/**
 * Service类.
 * <br/>
 *
 * @author zhouxb
 * @version 1.0 2014-12-25 17:12:37
 * @since JDK 1.5
 */
@Service
@Transactional
public class SChannelService {

    @Autowired
    private SChannelDao sChannelDao;


    public SChannel getByMap(MapBean mb) {
        return sChannelDao.get("SChannel.getByMap", mb);
    }

    public List<SChannel> getMaxDateDataApp(MapBean mb) {
        return sChannelDao.find("SChannel.maxDateDataApp", mb);
    }

    public List<SChannel> getMaxDateDataPlatform(MapBean mb) {
        return sChannelDao.find("SChannel.maxDateDataPlatform", mb);
    }

    public List<SChannel> getLastDayAppData(MapBean mb) {
        return sChannelDao.find("SChannel.getLastDayAppData", mb);
    }

    public List<SChannel> getLastDayPlatformData(MapBean mb) {
        return sChannelDao.find("SChannel.getLastDayPlatformData", mb);
    }
}