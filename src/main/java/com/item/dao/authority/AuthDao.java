package com.item.dao.authority;

import org.springframework.stereotype.Repository;

import com.item.domain.authority.Auth;

import core.module.orm.ibatis.SqlMapClientDao;

/**
 * 
 * 权限dao类
 * @author guojt
 * @since 2011-02-20
 *
 */
@Repository
public class AuthDao extends SqlMapClientDao<Auth, Long>{
}
