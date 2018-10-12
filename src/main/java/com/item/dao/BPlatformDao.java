package com.item.dao;

import org.springframework.stereotype.Repository;

import com.item.domain.BPlatform;

import core.module.orm.ibatis.SqlMapClientDao;

@Repository
public class BPlatformDao  extends SqlMapClientDao<BPlatform, Long>{

}
