/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SZonePlatformService.java  2014-12-25 17:12:36 zhouxb ]
 */
package com.item.service;

import com.item.domain.SZonePlatform;
import com.item.dao.SZonePlatformDao;

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
public class SZonePlatformService {

    @Autowired
    private SZonePlatformDao sZonePlatformDao;

    //区服分析总计
    public List<SZonePlatform> zoneSummary(MapBean mb) {
		return sZonePlatformDao.find("SZonePlatform.zoneSummary", mb);
	}
    
    //区服详细表
    public List<SZonePlatform> zoneDetail(MapBean mb) {
  		return sZonePlatformDao.find("SZonePlatform.zoneDetail", mb);
  	}
    //渠道详细表
    public List<SZonePlatform> platformDetail(MapBean mb) {
  		return sZonePlatformDao.find("SZonePlatform.platformDetail", mb);
  	}
}