package com.item.dao;

import org.springframework.stereotype.Repository;

import com.item.domain.BRole;

import core.module.orm.ibatis.SqlMapClientDao;

@Repository
public class BRoleDao extends SqlMapClientDao<BRole,Long>{

}