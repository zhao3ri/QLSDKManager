package com.item.dao;

import org.springframework.stereotype.Repository;

import com.item.domain.report.ReportHistoryDaily;

import core.module.orm.ibatis.SqlMapClientDao;

@Repository
public class ReportHistoryDailyDao extends SqlMapClientDao<ReportHistoryDaily, Long>{
	
}
