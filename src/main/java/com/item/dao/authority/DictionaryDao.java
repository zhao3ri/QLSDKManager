/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:ZDOP,Id:DictionaryDao.java  2013-01-15 11:32:29 liuxh ]
 */
package com.item.dao.authority;

import org.springframework.stereotype.Repository;

import com.item.domain.authority.Dictionary;

import core.module.orm.ibatis.SqlMapClientDao;

/**
 * 数据字典表 Dao类.
 * <br/>
 * 
 * @author liuxh
 * @version 1.0 2013-01-15 11:32:29
 * @since JDK 1.5
 */
@Repository
public class DictionaryDao extends SqlMapClientDao<Dictionary,Long>{

}