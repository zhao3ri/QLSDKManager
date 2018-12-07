/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:BPlatformGameZoneService.java  2015-01-04 09:55:29 zhouxb ]
 */
package com.item.service;

import com.item.domain.BChannelGameZone;
import com.item.dao.BChannelGameZoneDao;

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
public class BChannelGameZoneService {

    @Autowired
    private BChannelGameZoneDao bChannelGameZoneDao;

    public Page<BChannelGameZone> page(Page<BChannelGameZone> page, MapBean mb){
    	return bChannelGameZoneDao.find(page,mb,"BChannelGameZone.count","BChannelGameZone.page");
    }

    public Long save(BChannelGameZone entity, String zoneIds){
    	deleteAll(entity.getGameId(),entity.getChannelId());
    	String zoneId [] = StringUtils.split(zoneIds, ",");
    	for (String string : zoneId) {
    		entity.setZoneId(string);
    		bChannelGameZoneDao.save("BChannelGameZone.save",entity);
		}
        return 1L;
    }

    private void deleteAll(Long appId, Integer channelId) {
    	MapBean mb = new MapBean();
    	mb.put(MapBean.GAME_ID, appId);
    	mb.put(MapBean.CHANNEL_ID, channelId);
    	bChannelGameZoneDao.delete("BChannelGameZone.deleteAll", mb);
	}

	public void delete(String checkedIds){
    	try{
    		for (String checkedId : StringUtils.split(checkedIds, ",")){
    			bChannelGameZoneDao.delete("BChannelGameZone.delete", Long.parseLong(checkedId));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
        }
    }

    public void update(BChannelGameZone entity, String zoneIds){
    	save(entity, zoneIds);
    }

    public BChannelGameZone get(Long id){
        return bChannelGameZoneDao.get("BChannelGameZone.get",id);
    }
    
    public BChannelGameZone getByChannelName(MapBean mb){
        return bChannelGameZoneDao.get("BChannelGameZone.getByChannelName",mb);
    }
    
    public List<BChannelGameZone> list(MapBean mb){
        return bChannelGameZoneDao.find("BChannelGameZone.list",mb);
    }

	public List<BChannelGameZone> getChannelZones(MapBean mb) {
		return bChannelGameZoneDao.find("BChannelGameZone.getChannelZones",mb);
	}

	public List<BChannelGameZone> getZoneChannels(MapBean mb) {
		return bChannelGameZoneDao.find("BChannelGameZone.getZoneChannels",mb);
	}
	
	
	//根据appId获取相应数据
	public List<BChannelGameZone> getGamezoneByGameId(MapBean mb) {
		return bChannelGameZoneDao.find("BChannelGameZone.getChannelGamezoneById", mb);
	}
	
}