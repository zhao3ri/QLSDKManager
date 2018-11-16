package com.item.dao.authority;

import org.springframework.stereotype.Repository;

import com.item.domain.authority.IdentityPermission;

import core.module.orm.ibatis.SqlMapClientDao;

/**
 * 
 * 角色权限dao类
 * @author guojt
 * @since 2011-02-20
 *
 */
@Repository
public class IdentityPermissionDao extends SqlMapClientDao<IdentityPermission, Long>{
}
