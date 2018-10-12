package com.item.dao;

import org.springframework.stereotype.Repository;

import com.item.domain.SDataDaily;

import core.module.orm.ibatis.SqlMapClientDao;

@Repository
public class SDataDailyDao extends SqlMapClientDao<SDataDaily,Long>{

}
