/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:BBehaviorUserService.java  2015-01-16 17:27:03 zhouxb ]
 */
package com.item.service;

import com.item.domain.BBehaviorUser;
import com.item.dao.BBehaviorUserDao;
import com.item.utils.JsonUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import core.module.orm.MapBean;

/**
 *  Service类.
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2015-01-16 17:27:03
 * @since JDK 1.5
 */
@Service
@Transactional
public class BBehaviorUserService {
    @Autowired
    private BBehaviorUserDao bBehaviorUserDao;

	public Date getLastLoginDate(MapBean mb) {
		Long dateTime = 0L;
		
		List<BBehaviorUser> list = bBehaviorUserDao.find("BBehaviorUser.list",mb);
		if (!CollectionUtils.isEmpty(list)) {
			for (BBehaviorUser bBehaviorUser : list) {
				BBehaviorUser data = JsonUtil.stringToObject(bBehaviorUser.getData(), BBehaviorUser.class);
				if (data.getLastLoginTime() != null && data.getLastLoginTime() > dateTime) {
					dateTime = data.getLastLoginTime();
				}
			}
		}
		if (dateTime == 0) {
			return null;
		}
		return new Date(dateTime);
	}
}