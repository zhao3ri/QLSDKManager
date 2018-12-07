/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SPlatformService.java  2014-12-25 17:12:37 zhouxb ]
 */
package com.item.service;

import com.item.domain.SChannelMonthly;
import com.item.dao.SChannelMonthlyDao;

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
public class SChannelMonthlyService {

    @Autowired
    private SChannelMonthlyDao sChannelMonthlyDao;


    public List<SChannelMonthly> listApp(MapBean mb){
        return sChannelMonthlyDao.find("SChannelMonthly.listApp",mb);
    }
    
    public List<SChannelMonthly> listPlatform(MapBean mb){
        return sChannelMonthlyDao.find("SChannelMonthly.listPlatform",mb);
    }

    public SChannelMonthly getByMap(MapBean mb){
        return sChannelMonthlyDao.get("SChannelMonthly.getByMap",mb);
    }

    public SChannelMonthly statMonthly(MapBean mb){
        return sChannelMonthlyDao.get("SChannelMonthly.stat",mb);
    }
}