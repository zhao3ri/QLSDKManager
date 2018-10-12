package com.item.dao;


import org.springframework.stereotype.Repository;

import com.item.domain.Gamezone;

import core.module.orm.ibatis.SqlMapClientDao;

@Repository
public class GamezoneDao extends SqlMapClientDao<Gamezone, Long>{
	
}
