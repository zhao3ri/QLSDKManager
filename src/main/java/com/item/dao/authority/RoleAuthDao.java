package com.item.dao.authority;

import org.springframework.stereotype.Repository;

import com.item.domain.authority.RoleAuth;

import core.module.orm.ibatis.SqlMapClientDao;

/**
 * 
 * 角色权限dao类
 * @author guojt
 * @since 2011-02-20
 *
 */
@Repository
public class RoleAuthDao extends SqlMapClientDao<RoleAuth, Long>{
}
