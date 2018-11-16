package com.item.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.item.dao.GamezoneDao;
import com.item.domain.Game;
import com.item.domain.Gamezone;
import core.module.orm.MapBean;
import core.module.orm.Page;

/*
 * 游戏service类
 */
@Service
@Transactional
public class BGamezoneService {
	@Autowired
	private GamezoneDao gamezoneDao;
	@Autowired
	private BGameService gameService;
	@Autowired
	private SysGameManagerService roleAppAuthService;
	
	public Page<Gamezone> page(Page<Gamezone> page,MapBean mb){
		if (null == mb)
			mb = new MapBean();
		mb.put("appIds", roleAppAuthService.getAppIdsByIdentityId());
		return gamezoneDao.find(page, mb, "BGamezone.count", "BGamezone.page");
	}
	
	public Gamezone getGamezoneById(Long GamezoneId) {
		return gamezoneDao.get("BGamezone.getGamezoneById", GamezoneId);
	}
	
	public Gamezone getGamezoneByzoneName(String GamezonezoneName) {
		return gamezoneDao.get("BGamezone.getGamezoneByzoneName", GamezonezoneName);
	}
	
	//根据appId获取相应数据
	public List<Gamezone> getGamezoneByappId(Long appId) {
		return gamezoneDao.find("BGamezone.getGamezoneByappId", appId);
	}
	
	/*
	 *批量删除
	 */
	public void bacthDelete(String checkedIds){
    	try{
    		for (String checkedId : StringUtils.split(checkedIds, ",")){
    			gamezoneDao.delete("BGamezone.delete", Long.parseLong(checkedId));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
        }
    }
	//删除
	public void delete(Long id) {
		gamezoneDao.delete("BGamezone.delete", id);
	}
	
	
	public void save(Gamezone entity){
		Game game=gameService.getGameById(entity.getAppId());
		if(game!=null)
			gamezoneDao.save("BGamezone.save", entity);
	}
	
	public void update(Gamezone entity){
		gamezoneDao.update("BGamezone.update", entity);
	}
	
	public int count(MapBean mb) {
		return (int)gamezoneDao.countResult("BGamezone.count", mb);
	}
	
	public List<Gamezone> list() {
		return gamezoneDao.find("BGamezone.list", null);
	}
	
	public List<Gamezone> getByIds(Long appId, String zoneIds) {
		if (StringUtils.isBlank(zoneIds)) {
			return null;
		}
		MapBean mb = new MapBean();
		mb.put("appId", appId);
		mb.put("zoneIds", zoneIds);
		return gamezoneDao.find("BGamezone.getByIds", mb);
	}
	
	//根据分区名查询分区名是否唯一
	public Long getGamezoneCountByName(MapBean mb) {
		return gamezoneDao.countResult("BGamezone.getGamezoneByName", mb);
	}
	
	public Gamezone get(MapBean mb) {
		return gamezoneDao.get("BGamezone.get", mb);
	}
}
