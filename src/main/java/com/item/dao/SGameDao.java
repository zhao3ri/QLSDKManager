/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:SGameDao.java  2014-12-25 17:12:37 zhouxb ]
 */
package com.item.dao;

import org.springframework.stereotype.Repository;
import com.item.domain.SGame;
import core.module.orm.ibatis.SqlMapClientDao;

/**
 *  Dao类.
 * <br/>
 * 
 * @author zhouxb
 * @version 1.0 2014-12-25 17:12:37
 * @since JDK 1.5
 */
@Repository
public class SGameDao extends SqlMapClientDao<SGame,Long>{

}