package com.item.dao.authority;

import org.springframework.stereotype.Repository;

import com.item.domain.authority.User;

import core.module.orm.ibatis.SqlMapClientDao;

/**
 * 
 * 管理员dao类
 * @author guojt
 * @since 2010-02-14
 *
 */
@Repository
public class UserDao extends SqlMapClientDao<User, Long> {

}
