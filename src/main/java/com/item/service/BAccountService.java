package com.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.BAccountDao;
import com.item.domain.BAccount;

import core.module.orm.MapBean;
import core.module.orm.Page;

@Service
@Transactional
public class BAccountService {
	@Autowired
	private BAccountDao bAccountDao;

	public Page<BAccount> page(Page<BAccount> page, MapBean mb) {
		return bAccountDao.find(page, mb, "BAccount.count", "BAccount.page");
	}

	public List<BAccount> list(MapBean mb) {
		return bAccountDao.find("BAccount.list", mb);
	}
}
