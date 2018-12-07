/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SZonePlatformService.java  2014-12-25 17:12:36 zhouxb ]
 */
package com.item.service;

import com.item.domain.SZoneChannel;
import com.item.dao.SZoneChannelDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.module.orm.MapBean;

/**
 *  Service类.
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2014-12-25 17:12:36
 * @since JDK 1.5
 */
@Service
@Transactional
public class SZoneChannelService {

    @Autowired
    private SZoneChannelDao sZoneChannelDao;

    //区服分析总计
    public List<SZoneChannel> zoneSummary(MapBean mb) {
		return sZoneChannelDao.find("SZoneChannel.zoneSummary", mb);
	}
    
    //区服详细表
    public List<SZoneChannel> zoneDetail(MapBean mb) {
  		return sZoneChannelDao.find("SZoneChannel.zoneDetail", mb);
  	}
    //渠道详细表
    public List<SZoneChannel> platformDetail(MapBean mb) {
  		return sZoneChannelDao.find("SZoneChannel.platformDetail", mb);
  	}
}