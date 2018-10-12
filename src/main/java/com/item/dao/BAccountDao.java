package com.item.dao;

import org.springframework.stereotype.Repository;

import com.item.domain.BAccount;

import core.module.orm.ibatis.SqlMapClientDao;

@Repository
public class BAccountDao extends SqlMapClientDao<BAccount, Long>{
	
}
