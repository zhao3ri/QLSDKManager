/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SPlatformRealtimeService.java  2014-12-25 17:12:36 zhouxb ]
 */
package com.item.service;

import com.item.dao.SPlatformRealtimeDao;
import com.item.domain.SPlatformRealtime;
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
public class SPlatformRealtimeService {

    @Autowired
    private SPlatformRealtimeDao sZonePlatformRealtimeDao;

    public Page<SPlatformRealtime> page(Page<SPlatformRealtime> page,MapBean mb){
    	return sZonePlatformRealtimeDao.find(page,mb,"SPlatformRealtime.count","SPlatformRealtime.page");
    }

    public Long save(SPlatformRealtime entity){
        return (Long)sZonePlatformRealtimeDao.save("SPlatformRealtime.save",entity);
    }

    public void delete(String checkedIds){
    	try{
    		for (String checkedId : StringUtils.split(checkedIds, ",")){
    			sZonePlatformRealtimeDao.delete("SPlatformRealtime.delete", Long.parseLong(checkedId));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
        }
    }

    public void update(SPlatformRealtime entity){
        sZonePlatformRealtimeDao.update("SPlatformRealtime.update",entity);
    }

    public SPlatformRealtime get(Long id){
        return sZonePlatformRealtimeDao.get("SPlatformRealtime.get",id);
    }

    public List<SPlatformRealtime> list(MapBean mb){
        return sZonePlatformRealtimeDao.find("SPlatformRealtime.list",mb);
    }

	public List<SPlatformRealtime> listGroupBy(MapBean mb) {
		return sZonePlatformRealtimeDao.find("SPlatformRealtime.listGroupBy",mb);
	}
}