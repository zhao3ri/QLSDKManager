/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SPlayTimeDistributeDailyService.java  2015-05-19 09:54:36 zhouxb ]
 */
package com.item.service;

import com.item.dao.SPlayTimeDistributeDailyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import core.module.orm.MapBean;

/**
 *  Service类.
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2015-05-19 09:54:36
 * @since JDK 1.5
 */
@Service
@Transactional
public class SPlayTimeDistributeDailyService {

    @Autowired
    private SPlayTimeDistributeDailyDao sPlayTimeDistributeDailyDao;

    public MapBean playTime(MapBean mb){
        return sPlayTimeDistributeDailyDao.findUnique("SPlayTimeDistributeDaily.playTime",mb);
    }
}