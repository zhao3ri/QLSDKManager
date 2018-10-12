package com.item.service;

import com.item.domain.SRechargeDistributeDaily;
import com.item.dao.SRechargeDistributeDailyDao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.module.orm.MapBean;

@Service
@Transactional
public class SRechargeDistributeDailyService {

    @Autowired
    private SRechargeDistributeDailyDao sRechargeDistributeDailyDao;

    public List<SRechargeDistributeDaily> list(MapBean mb){
        return sRechargeDistributeDailyDao.find("SRechargeDistributeDaily.list",mb);
    }
}