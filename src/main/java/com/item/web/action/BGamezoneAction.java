package com.item.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.item.domain.Game;
import com.item.domain.Gamezone;
import com.item.service.BGameService;
import com.item.service.BGamezoneService;
import com.item.utils.InitSearchCondition;
import com.item.utils.JsonUtil;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.utils.Struts2Utils;
import core.module.web.Struts2Action;

@Action
public class BGamezoneAction extends Struts2Action{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BGameService gameService;
	@Autowired
	private BGamezoneService gamezoneService;
	
	private Page<Gamezone> page=new Page<Gamezone>(10);
	private Gamezone gamezone;
	private String checkedIds;
	private Long id;
	private List<Game> gameList;
	private List<Gamezone> gamezones;
	private Game game;
	private Integer keepSearchCondition;
	private Long gameId;
	public String list(){
		gamezones = gamezoneService.getGamezoneByGameId(gameId);
		
		gamezone = InitSearchCondition.initEntity(gamezone, keepSearchCondition, "gamezone");
		page = InitSearchCondition.initPage(page, keepSearchCondition, "gamezone");
		
		gameList = gameService.getGameList();
		MapBean mb = search();
		page = gamezoneService.page(page, mb);
		return "list";
    }
	
	public void getGameZonesAsync(){
		gamezones = gamezoneService.getGamezoneByGameId(gameId);
		try {
			Struts2Utils.getResponse().getWriter().write(JsonUtil.toJsonString(gamezones));
		} catch (IOException e) {
			logger.error("getGameZonesAsync error", e);
			e.printStackTrace();
		}
    }
	
	private MapBean search(){
		MapBean mb = new MapBean();
		if(gamezone!=null){
			if(gamezone.getId()!=null)
				mb.put("id", gamezone.getId());
			if(gamezone.getZoneId()!=null&&"".equals(gamezone.getZoneId())==false)
				mb.put("zoneId", gamezone.getZoneId());
			if(gamezone.getZoneName()!=null&&"".equals(gamezone.getZoneName())==false)
				mb.put("zoneName", gamezone.getZoneName());
			if(gamezone.getGameId()!=null)
				mb.put(MapBean.GAME_ID, gamezone.getGameId());
		}
		mb.put("orderby","id desc");
		return mb;
	}
	
	public String save(){
        if(gamezone!=null){
        	MapBean mb = new MapBean();
        	mb.put(MapBean.GAME_ID, gamezone.getGameId());
        	mb.put("zoneId", gamezone.getZoneId());
        	mb.put("exceptId", gamezone.getId()==null ? -1 : gamezone.getId());
        	if (gamezoneService.count(mb) > 0) {
        		 addActionMessage("该游戏分区已经存在！");
        		 id = gamezone.getId();
        		 return handle();
			}
            if(gamezone.getId()!=null){
            	gamezoneService.update(gamezone);
                addActionMessage("修改信息成功");
            }else{
            	gamezone.setZoneId(gamezone.getZoneId());
            	gamezone.setZoneName(gamezone.getZoneName());
            	gamezone.setGameId(gamezone.getGameId());
            	gamezoneService.save(gamezone);
                addActionMessage("保存信息成功");
            }
        }
        return "save";
    }
	
	public String handle(){
		gameList=gameService.getGameList();
        if(id!=null){
        	gamezone=gamezoneService.getGamezoneById(id);
        }
        return "handle";
    }
	
	public String batchDelete(){
        if(checkedIds!=null){
        	try {
        		gamezoneService.bacthDelete(checkedIds);
	            addActionMessage("删除数据成功");
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else{
            addActionMessage("请选择要删除的数据");
        }
        return "delete";
    }
	
	public String delete() {
		gamezoneService.delete(id);
		addActionMessage("删除数据成功");
		return "delete";
	}
	
	public Page<Gamezone> getPage() {
		return page;
	}
	public void setPage(Page<Gamezone> page) {
		this.page = page;
	}
	public Gamezone getGamezone() {
		return gamezone;
	}
	public void setGamezone(Gamezone gamezone) {
		this.gamezone = gamezone;
	}
	public String getCheckedIds() {
		return checkedIds;
	}
	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getKeepSearchCondition() {
		return keepSearchCondition;
	}
	public void setKeepSearchCondition(Integer keepSearchCondition) {
		this.keepSearchCondition = keepSearchCondition;
	}

	public List<Game> getGameList() {
		return gameList;
	}

	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public List<Gamezone> getGamezones() {
		return gamezones;
	}

	public void setGamezones(List<Gamezone> gamezones) {
		this.gamezones = gamezones;
	}
}
