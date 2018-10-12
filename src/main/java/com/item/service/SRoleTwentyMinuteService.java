/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SRoleTwentyMinuteService.java  2015-05-19 09:54:36 zhouxb ]
 */
package com.item.service;

import com.item.domain.SRoleTwentyMinute;
import com.item.dao.SRoleTwentyMinuteDao;
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
 * @version 1.0 2015-05-19 09:54:36
 * @since JDK 1.5
 */
@Service
@Transactional
public class SRoleTwentyMinuteService {

    @Autowired
    private SRoleTwentyMinuteDao sRoleTwentyMinuteDao;
    
    public List<SRoleTwentyMinute> listRealTimeLogin(MapBean mb){
    	return sRoleTwentyMinuteDao.find("SRoleTwentyMinute.listRealTimeLogin",mb);
	}
    
    public SRoleTwentyMinute historyTopOnlines(MapBean mb){
    	return sRoleTwentyMinuteDao.get("SRoleTwentyMinute.historyTopOnlines",mb);
    }
    
    public List<MapBean> onlineDaily(MapBean mb){
    	return sRoleTwentyMinuteDao.query(mb,"SRoleTwentyMinute.onlineDaily");
    }
    
    public Long newRoleOnlines(MapBean mb){
        return sRoleTwentyMinuteDao.countResult("SRoleTwentyMinute.newRoleOnlines",mb);
    }
    
    public Long maxRoleOnlines(MapBean mb){
    	return sRoleTwentyMinuteDao.countResult("SRoleTwentyMinute.maxRoleOnlines", mb);
    }
    
    public Long minRoleOnlines(MapBean mb){
    	return sRoleTwentyMinuteDao.countResult("SRoleTwentyMinute.minRoleOnlines", mb);
    }
    
    public Long avgRoleOnlines(MapBean mb){
    	return sRoleTwentyMinuteDao.countResult("SRoleTwentyMinute.avgRoleOnlines", mb);
    }
}