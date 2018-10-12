package com.item.dao;

import org.springframework.stereotype.Repository;

import com.item.domain.Game;

import core.module.orm.ibatis.SqlMapClientDao;

@Repository
public class GameDao extends SqlMapClientDao<Game, Long>{
	
}
