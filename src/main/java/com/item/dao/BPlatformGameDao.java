package com.item.dao;

import com.item.domain.BPlatformGame;
import org.springframework.stereotype.Repository;

import core.module.orm.ibatis.SqlMapClientDao;

/** 
 *@author 作者 lilc
 * @version 创建时间：2014年12月24日 上午10:35:00
 * 类说明
 */
@Repository
public class BPlatformGameDao extends SqlMapClientDao<BPlatformGame, Long> {

}
