package com.item.service;

import com.item.domain.SRoleDaily;
import com.item.dao.SRoleDailyDao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.module.orm.MapBean;

@Service
@Transactional
public class SRoleDailyService {

    @Autowired
    private SRoleDailyDao sRoleDailyDao;

    public List<SRoleDaily> listDailyLogin(MapBean mb){
        return sRoleDailyDao.find("SRoleDaily.listDailyLogin", mb);
    }
    
    public Integer getCurrentMaxAmount(MapBean mb){
    	return (int)sRoleDailyDao.countResult("SRoleDaily.getCurrentMaxAmount", mb);
    }
}