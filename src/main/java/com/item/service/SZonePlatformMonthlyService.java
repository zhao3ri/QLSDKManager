/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SZonePlatformService.java  2014-12-25 17:12:36 zhouxb ]
 */
package com.item.service;

import com.item.domain.SZonePlatformMonthly;
import com.item.dao.SZonePlatformMonthlyDao;

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
public class SZonePlatformMonthlyService {

    @Autowired
    private SZonePlatformMonthlyDao sZonePlatformMonthlyDao;
    
    //区服分析总计--按月
    public List<SZonePlatformMonthly> zoneSummary(MapBean mb) {
		return sZonePlatformMonthlyDao.find("sZonePlatformMonthly.zoneSummary", mb);
	}
  
    //区服详细表--按月
    public List<SZonePlatformMonthly> zoneDetail(MapBean mb) {
		return sZonePlatformMonthlyDao.find("sZonePlatformMonthly.zoneDetail", mb);
	}
    //渠道详细表
    public List<SZonePlatformMonthly> platformDetail(MapBean mb) {
		return sZonePlatformMonthlyDao.find("sZonePlatformMonthly.platformDetail", mb);
	}
}