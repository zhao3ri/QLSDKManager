/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SPlatformRealtimeService.java  2014-12-25 17:12:36 zhouxb ]
 */
package com.item.service;

import com.item.dao.SChannelRealtimeDao;
import com.item.domain.SChannelRealtime;
import core.module.orm.MapBean;
import core.module.orm.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class SChannelRealtimeService {

    @Autowired
    private SChannelRealtimeDao sZonePlatformRealtimeDao;

    public Page<SChannelRealtime> page(Page<SChannelRealtime> page, MapBean mb){
    	return sZonePlatformRealtimeDao.find(page,mb,"SChannelRealtime.count","SChannelRealtime.page");
    }

    public Long save(SChannelRealtime entity){
        return (Long)sZonePlatformRealtimeDao.save("SChannelRealtime.save",entity);
    }

    public void delete(String checkedIds){
    	try{
    		for (String checkedId : StringUtils.split(checkedIds, ",")){
    			sZonePlatformRealtimeDao.delete("SChannelRealtime.delete", Long.parseLong(checkedId));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
        }
    }

    public void update(SChannelRealtime entity){
        sZonePlatformRealtimeDao.update("SChannelRealtime.update",entity);
    }

    public SChannelRealtime get(Long id){
        return sZonePlatformRealtimeDao.get("SChannelRealtime.get",id);
    }

    public List<SChannelRealtime> list(MapBean mb){
        return sZonePlatformRealtimeDao.find("SChannelRealtime.list",mb);
    }

	public List<SChannelRealtime> listGroupBy(MapBean mb) {
		return sZonePlatformRealtimeDao.find("SChannelRealtime.listGroupBy",mb);
	}
}