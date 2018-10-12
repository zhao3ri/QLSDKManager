/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SZonePlatformRealtimeService.java  2014-12-25 17:12:36 zhouxb ]
 */
package com.item.service;

import com.item.domain.SZonePlatformRealtime;
import com.item.dao.SZonePlatformRealtimeDao;
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
public class SZonePlatformRealtimeService {

    @Autowired
    private SZonePlatformRealtimeDao sZonePlatformRealtimeDao;

    public Page<SZonePlatformRealtime> page(Page<SZonePlatformRealtime> page,MapBean mb){
    	return sZonePlatformRealtimeDao.find(page,mb,"SZonePlatformRealtime.count","SZonePlatformRealtime.page");
    }

    public Long save(SZonePlatformRealtime entity){
        return (Long)sZonePlatformRealtimeDao.save("SZonePlatformRealtime.save",entity);
    }

    public void delete(String checkedIds){
    	try{
    		for (String checkedId : StringUtils.split(checkedIds, ",")){
    			sZonePlatformRealtimeDao.delete("SZonePlatformRealtime.delete", Long.parseLong(checkedId));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
        }
    }

    public void update(SZonePlatformRealtime entity){
        sZonePlatformRealtimeDao.update("SZonePlatformRealtime.update",entity);
    }

    public SZonePlatformRealtime get(Long id){
        return sZonePlatformRealtimeDao.get("SZonePlatformRealtime.get",id);
    }

    public List<SZonePlatformRealtime> list(MapBean mb){
        return sZonePlatformRealtimeDao.find("SZonePlatformRealtime.list",mb);
    }

	public List<SZonePlatformRealtime> listGroupBy(MapBean mb) {
		return sZonePlatformRealtimeDao.find("SZonePlatformRealtime.listGroupBy",mb);
	}
}