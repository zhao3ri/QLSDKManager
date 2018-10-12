/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:BOrderService.java  2014-12-23 14:41:55 zhouxb ]
 */
package com.item.service;

import com.item.domain.BOrder;
import com.item.dao.BOrderDao;

import java.util.List;

import javax.annotation.Resource;

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
 * @version 1.0 2014-12-23 14:41:55
 * @since JDK 1.5
 */
@Service
@Transactional
public class BOrderService {

    @Autowired
    private BOrderDao bOrderDao;
    @Resource
    private SysroleappauthService roleAppAuthService;

    public Page<BOrder> page(Page<BOrder> page, MapBean mb){
    	if (null == mb)
			mb = new MapBean();
        if (mb.containsKey("appIds")) {
            List<Long> permitionIds = roleAppAuthService.getAuthAppIdsByRoleId();
            List<Long> apps = (List<Long>) mb.get("appIds");
            for (int i = 0; i < apps.size(); i++) {
                if (permitionIds.contains(apps.get(i))){
                    continue;
                }else {
                    apps.remove(i);
                    i--;
                }
            }
        }else {
            mb.put("appIds", roleAppAuthService.getAuthAppIdsByRoleId());
        }
//    	mb.put("appIds", roleAppAuthService.getAuthAppIdsByRoleId());
    	return bOrderDao.find(page,mb,"BOrder.count","BOrder.page");
    }

    public Long save(BOrder entity){
        return (Long)bOrderDao.save("BOrder.save",entity);
    }

    public void delete(String checkedIds){
    	try{
    		for (String checkedId : StringUtils.split(checkedIds, ",")){
    			bOrderDao.delete("BOrder.delete", Long.parseLong(checkedId));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
        }
    }

    public void update(BOrder entity){
        bOrderDao.update("BOrder.update",entity);
    }

    public BOrder get(Long id){
        return bOrderDao.get("BOrder.get",id);
    }

    public List<BOrder> list(MapBean mb){
    	if (null == mb)
			mb = new MapBean();
    	mb.put("appIds", roleAppAuthService.getAuthAppIdsByRoleId());
        return bOrderDao.find("BOrder.list",mb);
    }
}