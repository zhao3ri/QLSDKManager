package com.item.service;

import com.item.domain.SRechargeHourly;
import com.item.dao.SRechargeHourlyDao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.module.orm.MapBean;

@Service
@Transactional
public class SRechargeHourlyService {
    @Autowired
    private SRechargeHourlyDao sRechargeHourlyDao;

    public List<SRechargeHourly> list(MapBean mb){
        return sRechargeHourlyDao.find("SRechargeHourly.list",mb);
    }
}