package com.item.dao.authority;

import org.springframework.stereotype.Repository;

import com.item.domain.authority.RoleData;

import core.module.orm.ibatis.SqlMapClientDao;

@Repository
public class RoleDataDao extends SqlMapClientDao<RoleData, Long> {

}
