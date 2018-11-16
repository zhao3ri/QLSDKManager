package com.item.dao.authority;

import org.springframework.stereotype.Repository;

import com.item.domain.authority.IdentityData;

import core.module.orm.ibatis.SqlMapClientDao;

@Repository
public class IdentityDataDao extends SqlMapClientDao<IdentityData, Long> {

}
