package com.item.dao.authority;

import org.springframework.stereotype.Repository;

import com.item.domain.authority.Module;

import core.module.orm.ibatis.SqlMapClientDao;

/**
 * 
 * 系统模块dao类
 * @author guojt
 * @since 2011-02-20
 *
 */
@Repository
public class ModuleDao extends SqlMapClientDao<Module, Long>{
}
