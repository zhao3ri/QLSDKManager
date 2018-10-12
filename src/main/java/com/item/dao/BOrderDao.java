/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:BOrderDao.java  2014-12-23 14:41:55 zhouxb ]
 */
package com.item.dao;

import org.springframework.stereotype.Repository;
import com.item.domain.BOrder;
import core.module.orm.ibatis.SqlMapClientDao;

/**
 *  Dao类.
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2014-12-23 14:41:55
 * @since JDK 1.5
 */
@Repository
public class BOrderDao extends SqlMapClientDao<BOrder,Long>{

}