/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:BBehaviorUserDao.java  2015-01-16 17:27:03 zhouxb ]
 */
package com.item.dao;

import org.springframework.stereotype.Repository;
import com.item.domain.BBehaviorUser;
import core.module.orm.ibatis.SqlMapClientDao;

/**
 *  Dao类.
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2015-01-16 17:27:03
 * @since JDK 1.5
 */
@Repository
public class BBehaviorUserDao extends SqlMapClientDao<BBehaviorUser,Long>{

}