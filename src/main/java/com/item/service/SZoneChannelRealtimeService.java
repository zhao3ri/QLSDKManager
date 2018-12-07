/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SZonePlatformRealtimeService.java  2014-12-25 17:12:36 zhouxb ]
 */
package com.item.service;

import com.item.domain.SZoneChannelRealtime;
import com.item.dao.SZoneChannelRealtimeDao;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.module.orm.MapBean;
import core.module.orm.Page;

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
public class SZoneChannelRealtimeService {

    @Autowired
    private SZoneChannelRealtimeDao sZoneChannelRealtimeDao;

    public Page<SZoneChannelRealtime> page(Page<SZoneChannelRealtime> page, MapBean mb){
    	return sZoneChannelRealtimeDao.find(page,mb,"SZoneChannelRealtime.count","SZoneChannelRealtime.page");
    }

    public Long save(SZoneChannelRealtime entity){
        return (Long) sZoneChannelRealtimeDao.save("SZoneChannelRealtime.save",entity);
    }

    public void delete(String checkedIds){
    	try{
    		for (String checkedId : StringUtils.split(checkedIds, ",")){
    			sZoneChannelRealtimeDao.delete("SZoneChannelRealtime.delete", Long.parseLong(checkedId));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
        }
    }

    public void update(SZoneChannelRealtime entity){
        sZoneChannelRealtimeDao.update("SZoneChannelRealtime.update",entity);
    }

    public SZoneChannelRealtime get(Long id){
        return sZoneChannelRealtimeDao.get("SZoneChannelRealtime.get",id);
    }

    public List<SZoneChannelRealtime> list(MapBean mb){
        return sZoneChannelRealtimeDao.find("SZoneChannelRealtime.list",mb);
    }

	public List<SZoneChannelRealtime> listGroupBy(MapBean mb) {
		return sZoneChannelRealtimeDao.find("SZoneChannelRealtime.listGroupBy",mb);
	}
}