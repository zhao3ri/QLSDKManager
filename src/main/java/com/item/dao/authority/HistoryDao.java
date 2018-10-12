/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:ZDOP,Id:HistoryDao.java  2013-01-15 10:48:28 liuxh ]
 */
package com.item.dao.authority;

import org.springframework.stereotype.Repository;

import com.item.domain.authority.History;

import core.module.orm.ibatis.SqlMapClientDao;

/**
 * 历史记录表 Dao类.
 * <br/>
 * 
 * @author liuxh
 * @version 1.0 2013-01-15 10:48:28
 * @since JDK 1.5
 */
@Repository
public class HistoryDao extends SqlMapClientDao<History,Long>{

}