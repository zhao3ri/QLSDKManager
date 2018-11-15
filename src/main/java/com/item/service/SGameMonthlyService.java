/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SGameService.java  2014-12-25 17:12:37 zhouxb ]
 */
package com.item.service;

import com.item.domain.SGameMonthly;
import com.item.dao.SGameMonthlyDao;

import com.item.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.module.orm.MapBean;

/**
 * Service类.
 * <br/>
 *
 * @author zhouxb
 * @version 1.0 2014-12-25 17:12:37
 * @since JDK 1.5
 */
@Service
@Transactional
public class SGameMonthlyService {

    @Autowired
    private SGameMonthlyDao sGameMonthlyDao;

    public SGameMonthly summary(MapBean mb) {
        return sGameMonthlyDao.get("sGameMonthly.summary", mb);
    }

    public SGameMonthly stat(MapBean mb) {
        return sGameMonthlyDao.get("sGameMonthly.stat", mb);
    }

    public SGameMonthly getGameMonthly(MapBean mb) {
        SGameMonthly gameMonthly = summary(mb);
        if (gameMonthly == null) {
            gameMonthly = stat(mb);
            if (gameMonthly != null && !StringUtil.isEmpty(mb.getString("yearMonth"))) {
                gameMonthly.setYearMonth(Integer.valueOf(mb.getString("yearMonth")));
//                sGameMonthlyDao.save("sGameMonthly.save", gameMonthly);
            }
        }
        return gameMonthly;
    }
}