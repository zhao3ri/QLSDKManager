package com.item.dao.authority;

import org.springframework.stereotype.Repository;

import com.item.domain.authority.Function;

import core.module.orm.ibatis.SqlMapClientDao;

/**
 * 
 * 模块下的功能管理dao类
 * @author guojt
 * @since 2011-02-20
 *
 */
@Repository
public class FunctionDao extends SqlMapClientDao<Function, Long>{
}
