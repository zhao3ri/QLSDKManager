package com.item.dao;

import org.springframework.stereotype.Repository;

import com.item.domain.SDataMonthly;

import core.module.orm.ibatis.SqlMapClientDao;

@Repository
public class SDataMonthlyDao extends SqlMapClientDao<SDataMonthly,Long>{

}
