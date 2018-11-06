/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SGameRealtimeService.java  2014-12-25 17:12:37 zhouxb ]
 */
package com.item.service;

import com.item.domain.SGameRealtime;
import com.item.dao.SGameRealtimeDao;
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
public class SGameRealtimeService {

    @Autowired
    private SGameRealtimeDao sGameRealtimeDao;

	public List<SGameRealtime> listGroupBy(MapBean mb) {
		return sGameRealtimeDao.find("SGameRealtime.listGroupBy",mb);
	}

	public long getCurrentNewUsers(MapBean mb) {return sGameRealtimeDao.countResult("SGameRealtime.getCurrentNewUsers",mb); }
}