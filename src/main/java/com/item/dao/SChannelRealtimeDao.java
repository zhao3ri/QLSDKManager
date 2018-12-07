/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SPlatformRealtimeDao.java  2014-12-25 17:12:36 zhouxb ]
 */
package com.item.dao;

import com.item.domain.SChannelRealtime;
import org.springframework.stereotype.Repository;
import core.module.orm.ibatis.SqlMapClientDao;

/**
 *  Dao类.
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2014-12-25 17:12:36
 * @since JDK 1.5
 */
@Repository
public class SChannelRealtimeDao extends SqlMapClientDao<SChannelRealtime,Long>{

}