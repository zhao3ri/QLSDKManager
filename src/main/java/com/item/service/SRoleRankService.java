package com.item.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.item.dao.SRoleRankDao;
import com.item.domain.SRoleRank;
import core.module.orm.MapBean;
import core.module.orm.Page;

@Service
@Transactional
public class SRoleRankService {
	@Autowired
	private SRoleRankDao sRoleRankDao;
	@Autowired
	private BBehaviorUserService bBehaviorUserService;

	private static final Logger logger = Logger.getLogger(RoleReportService.class);
	public Page<SRoleRank> page (Page<SRoleRank> page, MapBean mb) {
		page = sRoleRankDao.find(page, mb, "SRoleRank.count", "SRoleRank.page");
//		for (SRoleRank rank : page.getResult()) {
//			mb.put("uid", rank.getUid());
//			mb.put("platformId", rank.getChannelId());
//			mb.put("clientType", rank.getClientType());
//			mb.put("appId", rank.getGameId());
//			logger.info("Set last Login Time");
//			rank.setLastLoginDate(bBehaviorUserService.getLastLoginDate(mb));
//		}
		return page;
	}

	public List<SRoleRank> list(MapBean mb) {
		List<SRoleRank> ranks = sRoleRankDao.find("SRoleRank.list", mb);
		return ranks;
	}
}
