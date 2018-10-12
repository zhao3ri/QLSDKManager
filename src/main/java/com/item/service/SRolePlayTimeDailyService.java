/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SRolePlayTimeDailyService.java  2015-05-19 09:54:36 zhouxb ]
 */
package com.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.SRolePlayTimeDailyDao;

import core.module.orm.MapBean;
import core.module.orm.Page;

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
public class SRolePlayTimeDailyService {

    @Autowired
    private SRolePlayTimeDailyDao sRolePlayTimeDailyDao;

    public Page<MapBean> pagePlayTime(Page<MapBean> page,MapBean mb){
    	return sRolePlayTimeDailyDao.query(page, mb, "SRolePlayTimeDaily.countPlayTime", "SRolePlayTimeDaily.pagePlayTime");
    }
}