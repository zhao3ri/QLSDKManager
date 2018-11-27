package com.item.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.item.dao.SRechargeRankDao;
import com.item.domain.BPlatform;
import com.item.domain.SRechargeRank;

import core.module.orm.MapBean;
import core.module.orm.Page;


@Service
@Transactional
public class SRechargeRankService {
	@Autowired
	private SRechargeRankDao sRechargeRankDao;
	@Autowired
	private BPlatformService bPlatformService;
	@Autowired
	private BBehaviorUserService bBehaviorUserService;
	
	public Page<SRechargeRank> page (Page<SRechargeRank> page,MapBean mb) {
		page = sRechargeRankDao.find(page, mb, "SRechargeRank.count", "SRechargeRank.page");
		mb.put("ranks", CollectionUtils.isEmpty(page.getResult()) ? null : page.getResult());
		
		List<BPlatform> platforms = bPlatformService.getCurrentIdentityChannelList();
		Map<String, String> id2name = new HashMap<String, String>();
		for (BPlatform bPlatform : platforms) {
			id2name.put(String.valueOf(bPlatform.getId()), bPlatform.getPlatformName());
		}
		
		for (SRechargeRank sRechargeRank : page.getResult()) {
			if (id2name.containsKey(String.valueOf(sRechargeRank.getPlatformId()))) {
				sRechargeRank.setPlatformName(id2name.get(String.valueOf(sRechargeRank.getPlatformId())));
			}
			
			mb.put("uid", sRechargeRank.getUid());
			mb.put("platformId", sRechargeRank.getPlatformId());
//			sRechargeRank.setLastLoginDate(bBehaviorUserService.getLastLoginDate(mb));
		}
		return page;
	}

	public List<SRechargeRank> list(MapBean mb) {
		List<SRechargeRank> ranks = sRechargeRankDao.find("SRechargeRank.list", mb);
		mb.put("ranks", CollectionUtils.isEmpty(ranks) ? null : ranks);
		
		List<BPlatform> platforms = bPlatformService.getCurrentIdentityChannelList();
		Map<String, String> id2name = new HashMap<String, String>();
		for (BPlatform bPlatform : platforms) {
			id2name.put(String.valueOf(bPlatform.getId()), bPlatform.getPlatformName());
		}
		
		for (SRechargeRank sRechargeRank : ranks) {
			if (id2name.containsKey(String.valueOf(sRechargeRank.getPlatformId()))) {
				sRechargeRank.setPlatformName(id2name.get(String.valueOf(sRechargeRank.getPlatformId())));
			}
			
			mb.put("uid", sRechargeRank.getUid());
			mb.put("platformId", sRechargeRank.getPlatformId());
			sRechargeRank.setLastLoginDate(bBehaviorUserService.getLastLoginDate(mb));
		}
		return ranks;
	}
}
