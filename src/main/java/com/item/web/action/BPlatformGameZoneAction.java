/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:BPlatformGameZoneDao.java  2015-01-04 09:55:30 zhouxb ]
 */
package com.item.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.item.domain.BPlatform;
import com.item.domain.BPlatformGameZone;
import com.item.domain.Game;
import com.item.domain.Gamezone;
import com.item.service.BGameService;
import com.item.service.BGamezoneService;
import com.item.service.BPlatformGameZoneService;
import com.item.service.BPlatformService;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.web.Struts2Action;

/**
 *  Action类.
 * <br/>
 *
 * @author zhouxb
 * @version 1.0 2015-01-04 09:55:30
 * @since JDK 1.5
 */
@Action
public class BPlatformGameZoneAction extends Struts2Action{

    private static final long serialVersionUID = 197266703365933779L;

    @Autowired
    private BPlatformGameZoneService bPlatformGameZoneService;
    @Autowired
    private BGameService bGameService;
    @Autowired
    private BPlatformService bPlatformService;
    @Autowired
    private BGamezoneService bGamezoneService;

    private Page<BPlatformGameZone> bPlatformGameZonePage=new Page<BPlatformGameZone>(10);
    private String checkedIds;
    private BPlatformGameZone bPlatformGameZone;
    private String platformIds;
	private String zoneIds;
	private Long gameId;
	private List<BPlatformGameZone> platformGameZones;
	private List<Game> games;
	private List<BPlatform> platforms;
    private Long id;
    private Integer isCompare;
    private List<Gamezone> gamezones;
    private Long platformId;
	
    public String list(){
        MapBean mb=search();
        bPlatformGameZonePage=bPlatformGameZoneService.page(bPlatformGameZonePage, mb);
        games=bGameService.getGameList();
        platforms=bPlatformService.getCurrentIdentityChannelList();
        bPlatformGameZonePage=bPlatformGameZoneService.page(bPlatformGameZonePage, mb);
        return "list";
    }
    
    
    public String getPlatformZones(){
    	MapBean mb = new MapBean();
    	mb.put(MapBean.GAME_ID, gameId);
    	mb.put("platformIds", platformIds.split(","));
    	platformGameZones = bPlatformGameZoneService.getPlatformZones(mb);
    	return "getPlatformZones";
    }
    
    public String getZonePlatforms(){
    	MapBean mb = new MapBean();
    	mb.put(MapBean.GAME_ID, gameId);
    	mb.put("zoneIds", zoneIds.split(","));
    	platformGameZones = bPlatformGameZoneService.getZonePlatforms(mb);
    	return "getZonePlatforms";
    }
	
    
    public String save(){
        if(bPlatformGameZone!=null){
            if(bPlatformGameZone.getId()!=null){
                bPlatformGameZoneService.update(bPlatformGameZone,zoneIds);
                addActionMessage("修改信息成功");
            }else{
                bPlatformGameZoneService.save(bPlatformGameZone,zoneIds);
                addActionMessage("保存信息成功");
            }
        }
        return handle();
    }
	
    public String handle(){
    	MapBean mb = new MapBean();
    	mb.put("gameId", bPlatformGameZone.getGameId());
    	mb.put("platformId", bPlatformGameZone.getPlatformId());
    	gamezones = bGamezoneService.getGamezoneByGameId(bPlatformGameZone.getGameId());

    	Map<String, String> map = new HashMap<String, String>();
    	List<BPlatformGameZone> bPlatformGameZones = bPlatformGameZoneService.list(mb);
    	for (BPlatformGameZone bPlatformGameZone : bPlatformGameZones) {
			map.put(bPlatformGameZone.getZoneId(), "");
		}
    	
    	for (Gamezone gamezone: gamezones) {
			if (map.get(gamezone.getZoneId()) != null) {
				gamezone.setIsHave(1);
			}else {
				gamezone.setIsHave(0);
			}
		}
        return "handle";
    }
    
    public String view(){
    	if(id!=null){
            bPlatformGameZone=bPlatformGameZoneService.get(id);
        }else{
        	addActionMessage("请选择一个ID");
        }
    	return "view";
    }
    
    public String delete(){
        if(checkedIds!=null){
            bPlatformGameZoneService.delete(checkedIds);
            addActionMessage("删除数据成功");
        }else{
            addActionMessage("请选择要删除的数据");
        }
        return "delete";
    }
    
    private MapBean search(){
        MapBean mb=new MapBean();
        if(bPlatformGameZone!=null){
        	if (bPlatformGameZone.getId()!=null){
                mb.put("id",bPlatformGameZone.getId());
            }
        	if (bPlatformGameZone.getPlatformId()!=null){
                mb.put("platformId",bPlatformGameZone.getPlatformId());
            }
        	if (bPlatformGameZone.getGameId()!=null){
                mb.put("gameId",bPlatformGameZone.getGameId());
            }
        	if (bPlatformGameZone.getZoneId()!=null&&!"".equals(bPlatformGameZone.getZoneId())){
                mb.put("zoneId",bPlatformGameZone.getZoneId());
            }
        }
        mb.put("orderby","id desc");
        return mb;
    }

	public Page<BPlatformGameZone> getBPlatformGameZonePage(){
        return bPlatformGameZonePage;
    }

    public void setCheckedIds(String checkedIds){
        this.checkedIds=checkedIds;
    }

    public BPlatformGameZone getBPlatformGameZone(){
        return bPlatformGameZone;
    }

    public void setBPlatformGameZone(BPlatformGameZone bPlatformGameZone){
        this.bPlatformGameZone=bPlatformGameZone;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

	public String getPlatformIds() {
		return platformIds;
	}

	public void setPlatformIds(String platformIds) {
		this.platformIds = platformIds;
	}

	public String getZoneIds() {
		return zoneIds;
	}

	public void setZoneIds(String zoneIds) {
		this.zoneIds = zoneIds;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public List<BPlatformGameZone> getPlatformGameZones() {
		return platformGameZones;
	}

	public void setPlatformGameZones(List<BPlatformGameZone> platformGameZones) {
		this.platformGameZones = platformGameZones;
	}



	public Integer getIsCompare() {
		return isCompare;
	}


	public void setIsCompare(Integer isCompare) {
		this.isCompare = isCompare;
	}


	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<BPlatform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<BPlatform> platforms) {
		this.platforms = platforms;
	}

	public List<Gamezone> getGamezones() {
		return gamezones;
	}

	public void setGamezones(List<Gamezone> gamezones) {
		this.gamezones = gamezones;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
}