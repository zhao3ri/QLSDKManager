package com.item.dao;

import org.springframework.stereotype.Repository;

import com.item.domain.BPlatformApp;

import core.module.orm.ibatis.SqlMapClientDao;

/** 
 *@author 作者 lilc
 * @version 创建时间：2014年12月24日 上午10:35:00
 * 类说明
 */
@Repository
public class BPlatformAppDao extends SqlMapClientDao<BPlatformApp, Long> {

}
