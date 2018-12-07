package com.item.dao;

import com.item.domain.report.LTVGameChannel;
import core.module.orm.ibatis.SqlMapClientDao;
import org.springframework.stereotype.Repository;

@Repository
public class ReportLTVDao extends SqlMapClientDao<LTVGameChannel, Long>{
	
}
