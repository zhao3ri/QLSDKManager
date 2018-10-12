package com.item.dao.authority;

import org.springframework.stereotype.Repository;

import com.item.domain.authority.Data;

import core.module.orm.ibatis.SqlMapClientDao;

/**
 * 系统数据集信息dao类
 * @author Administrator
 * @since 2013-10-11
 */
@Repository
public class DataDao extends SqlMapClientDao<Data, Long> {

}
