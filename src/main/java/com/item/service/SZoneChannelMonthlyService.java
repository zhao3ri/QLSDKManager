/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SZonePlatformService.java  2014-12-25 17:12:36 zhouxb ]
 */
package com.item.service;

import com.item.domain.SZoneChannelMonthly;
import com.item.dao.SZoneChannelMonthlyDao;

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
public class SZoneChannelMonthlyService {

    @Autowired
    private SZoneChannelMonthlyDao sZoneChannelMonthlyDao;
    
    //区服分析总计--按月
    public List<SZoneChannelMonthly> zoneSummary(MapBean mb) {
		return sZoneChannelMonthlyDao.find("sZonePlatformMonthly.zoneSummary", mb);
	}
  
    //区服详细表--按月
    public List<SZoneChannelMonthly> zoneDetail(MapBean mb) {
		return sZoneChannelMonthlyDao.find("sZonePlatformMonthly.zoneDetail", mb);
	}
    //渠道详细表
    public List<SZoneChannelMonthly> platformDetail(MapBean mb) {
		return sZoneChannelMonthlyDao.find("sZonePlatformMonthly.platformDetail", mb);
	}
}