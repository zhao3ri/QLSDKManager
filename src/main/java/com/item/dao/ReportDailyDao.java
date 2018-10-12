package com.item.dao;

import org.springframework.stereotype.Repository;

import com.item.domain.report.ReportDaily;

import core.module.orm.ibatis.SqlMapClientDao;

@Repository
public class ReportDailyDao extends SqlMapClientDao<ReportDaily, Long>{
	
}
