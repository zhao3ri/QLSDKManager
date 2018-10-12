package com.item.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.BRoleDao;
import com.item.domain.BRole;

import core.module.orm.MapBean;
import core.module.orm.Page;

@Service
@Transactional
public class BRoleService {
	@Autowired
	private BRoleDao bRoleDao;
	
	public Page<BRole> page(Page<BRole> page, MapBean mb){
		return bRoleDao.find(page, mb, "BRole.count", "BRole.page");
	}
	
	public List<BRole> list(MapBean mb){
		return bRoleDao.find("BRole.list", mb);
	}
	
}
