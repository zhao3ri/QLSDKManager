package com.item.dao.authority;

import org.springframework.stereotype.Repository;

import com.item.domain.authority.Role;

import core.module.orm.ibatis.SqlMapClientDao;

/**
 * 
 * 角色管理dao类
 * @author guojt
 * @since 2011-02-20
 *
 */
@Repository
public class RoleDao extends SqlMapClientDao<Role, Long>{
}
