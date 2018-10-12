/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SPlatformService.java  2014-12-25 17:12:37 zhouxb ]
 */
package com.item.service;

import com.item.domain.SPlatformMonthly;
import com.item.dao.SPlatformMonthlyDao;

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
public class SPlatformMonthlyService {

    @Autowired
    private SPlatformMonthlyDao sPlatformMonthlyDao;


    public List<SPlatformMonthly> listApp(MapBean mb){
        return sPlatformMonthlyDao.find("SPlatformMonthly.listApp",mb);
    }
    
    public List<SPlatformMonthly> listPlatform(MapBean mb){
        return sPlatformMonthlyDao.find("SPlatformMonthly.listPlatform",mb);
    }

    public SPlatformMonthly getByMap(MapBean mb){
        return sPlatformMonthlyDao.get("SPlatformMonthly.getByMap",mb);
    }
}