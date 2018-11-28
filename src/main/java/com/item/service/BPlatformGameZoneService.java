/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:BPlatformGameZoneService.java  2015-01-04 09:55:29 zhouxb ]
 */
package com.item.service;

import com.item.domain.BPlatformGameZone;
import com.item.dao.BPlatformGameZoneDao;

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
 * @version 1.0 2015-01-04 09:55:29
 * @since JDK 1.5
 */
@Service
@Transactional
public class BPlatformGameZoneService {

    @Autowired
    private BPlatformGameZoneDao bPlatformGameZoneDao;

    public Page<BPlatformGameZone> page(Page<BPlatformGameZone> page,MapBean mb){
    	return bPlatformGameZoneDao.find(page,mb,"BPlatformGameZone.count","BPlatformGameZone.page");
    }

    public Long save(BPlatformGameZone entity,String zoneIds){
    	deleteAll(entity.getGameId(),entity.getPlatformId());
    	String zoneId [] = StringUtils.split(zoneIds, ",");
    	for (String string : zoneId) {
    		entity.setZoneId(string);
    		bPlatformGameZoneDao.save("BPlatformGameZone.save",entity);
		}
        return 1L;
    }

    private void deleteAll(Long appId, Integer platformId) {
    	MapBean mb = new MapBean();
    	mb.put(MapBean.GAME_ID, appId);
    	mb.put(MapBean.PLATFORM_ID, platformId);
    	bPlatformGameZoneDao.delete("BPlatformGameZone.deleteAll", mb);
	}

	public void delete(String checkedIds){
    	try{
    		for (String checkedId : StringUtils.split(checkedIds, ",")){
    			bPlatformGameZoneDao.delete("BPlatformGameZone.delete", Long.parseLong(checkedId));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
        }
    }

    public void update(BPlatformGameZone entity,String zoneIds){
    	save(entity, zoneIds);
    }

    public BPlatformGameZone get(Long id){
        return bPlatformGameZoneDao.get("BPlatformGameZone.get",id);
    }
    
    public BPlatformGameZone getByChannelName(MapBean mb){
        return bPlatformGameZoneDao.get("BPlatformGameZone.getByChannelName",mb);
    }
    
    public List<BPlatformGameZone> list(MapBean mb){
        return bPlatformGameZoneDao.find("BPlatformGameZone.list",mb);
    }

	public List<BPlatformGameZone> getPlatformZones(MapBean mb) {
		return bPlatformGameZoneDao.find("BPlatformGameZone.getPlatformZones",mb);
	}

	public List<BPlatformGameZone> getZonePlatforms(MapBean mb) {
		return bPlatformGameZoneDao.find("BPlatformGameZone.getZonePlatforms",mb);
	}
	
	
	//根据appId获取相应数据
	public List<BPlatformGameZone> getGamezoneByappId(MapBean mb) {
		return bPlatformGameZoneDao.find("BPlatformGameZone.getPlatformGamezoneById", mb);
	}
	
}