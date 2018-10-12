/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SPlatformService.java  2014-12-25 17:12:37 zhouxb ]
 */
package com.item.service;

import com.item.domain.SPlatform;
import com.item.dao.SPlatformDao;
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
 * @version 1.0 2014-12-25 17:12:37
 * @since JDK 1.5
 */
@Service
@Transactional
public class SPlatformService {

    @Autowired
    private SPlatformDao sPlatformDao;


    public SPlatform getByMap(MapBean mb){
        return sPlatformDao.get("SPlatform.getByMap",mb);
    }
    
    public  List<SPlatform> getMaxDateDataApp(MapBean mb) {
		return sPlatformDao.find("SPlatform.maxDateDataApp", mb);
	}
    
    public List<SPlatform> getMaxDateDataPlatform(MapBean mb) {
		return sPlatformDao.find("SPlatform.maxDateDataPlatform", mb);
	}
}