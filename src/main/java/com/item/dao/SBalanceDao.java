package com.item.dao;

import com.item.domain.BPlatform;
import com.item.domain.SBalance;
import core.module.orm.ibatis.SqlMapClientDao;
import org.springframework.stereotype.Repository;

@Repository
public class SBalanceDao extends SqlMapClientDao<SBalance, Long>{

}
