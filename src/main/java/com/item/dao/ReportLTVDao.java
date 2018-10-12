package com.item.dao;

import com.item.domain.report.LTVGamePlatform;
import com.item.domain.report.ReportDaily;
import core.module.orm.ibatis.SqlMapClientDao;
import org.springframework.stereotype.Repository;

@Repository
public class ReportLTVDao extends SqlMapClientDao<LTVGamePlatform, Long>{
	
}
