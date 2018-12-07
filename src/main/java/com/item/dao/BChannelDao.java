package com.item.dao;

import org.springframework.stereotype.Repository;

import com.item.domain.BChannel;

import core.module.orm.ibatis.SqlMapClientDao;

@Repository
public class BChannelDao extends SqlMapClientDao<BChannel, Long>{

}
