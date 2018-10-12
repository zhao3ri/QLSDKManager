/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:ZDOP,Id:HistoryService.java  2013-01-15 10:48:28 liuxh ]
 */
package com.item.service.authority;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.authority.HistoryDao;
import com.item.domain.authority.History;

import core.module.orm.MapBean;
import core.module.orm.Page;

/**
 * 历史记录表 Service类.
 * <br/>
 * 
 * @author liuxh
 * @version 1.0 2013-01-15 10:48:28
 * @since JDK 1.5
 */
@Service
@Transactional
public class HistoryService {

    @Autowired
    private HistoryDao historyDao;

    public Page<History> page(Page<History> page,MapBean mb){
    	return historyDao.find(page,mb,"History.count","History.page");
    }

    public Long save(History entity){
        return (Long)historyDao.save("History.save",entity);
    }

    public void delete(String checkedIds){
    	try{
    		for (String checkedId : StringUtils.split(checkedIds, ",")){
    			historyDao.delete("History.delete", Long.parseLong(checkedId));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
        }
    }

    public void update(History entity){
        historyDao.update("History.update",entity);
    }

    public History get(Long id){
        return historyDao.get("History.get",id);
    }

    public List<History> list(MapBean mb){
        return historyDao.find("History.list",mb);
    }
}