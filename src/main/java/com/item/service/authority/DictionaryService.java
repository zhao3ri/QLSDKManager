/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:ZDOP,Id:DictionaryService.java  2013-01-15 11:32:29 liuxh ]
 */
package com.item.service.authority;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.authority.DictionaryDao;
import com.item.domain.authority.Dictionary;

import core.module.orm.MapBean;
import core.module.orm.Page;

/**
 * 数据字典表 Service类.
 * <br/>
 * 
 * @author liuxh
 * @version 1.0 2013-01-15 11:32:29
 * @since JDK 1.5
 */
@Service
@Transactional
public class DictionaryService {

    @Autowired
    private DictionaryDao dictionaryDao;

    /**
     * 获取列表page
     * @param page
     * @param mb
     * @return
     */
    public Page<Dictionary> page(Page<Dictionary> page,MapBean mb){
    	return dictionaryDao.find(page,mb,"Dictionary.count","Dictionary.page");
    }

    /**
     * 保存
     * @param entity
     * @return
     */
    public Long save(Dictionary entity){
        return (Long)dictionaryDao.save("Dictionary.save",entity);
    }

    /**
     * 删除
     * @param checkedIds
     */
    public void delete(String checkedIds){
    	try{
    		for (String checkedId : StringUtils.split(checkedIds, ",")){
    			dictionaryDao.delete("Dictionary.delete", Long.parseLong(checkedId));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
        }
    }

    /**
     * 更新
     * @param entity
     */
    public void update(Dictionary entity){
        dictionaryDao.update("Dictionary.update",entity);
    }

    /**
     * 获取单个
     * @param id
     * @return
     */
    public Dictionary get(Long id){
        return dictionaryDao.get("Dictionary.get",id);
    }
    
    public Dictionary getforupdate(MapBean mb){
    	return dictionaryDao.get("Dictionary.getforupdate",mb);
    }
    
    public Dictionary getEntity(Long id){
        return dictionaryDao.get("Dictionary.getEntity",id);
    }

    /**
     * 获取列表list
     * @param mb
     * @return
     */
    public List<Dictionary> list(MapBean mb){
        return dictionaryDao.find("Dictionary.list",mb);
    }

    /**
     * 修改状态
     * @param checkedIds
     * @param flag
     */
	public void updateState(String checkedIds, int flag) {
		for (String checkedId : StringUtils.split(checkedIds, ",")){
    		Dictionary d = get(Long.parseLong(checkedId));
        	d.setState(flag);
    		update(d);
		}
	}
	
}