package com.item.web.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.item.domain.Game;
import com.item.service.BGameService;
import com.item.utils.InitSearchCondition;
import com.item.utils.RandomTool;
import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.web.Struts2Action;

@Action
public class BGameAction extends Struts2Action{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BGameService gameService;
	private Page<Game> page=new Page<Game>(10);
	private Game game;
	private String checkedIds;
	private Long id;
	private Integer keepSearchCondition;
	private List<Game> games;
	
	public String list(){
		game = InitSearchCondition.initEntity(game,keepSearchCondition,"game");
		page = InitSearchCondition.initPage(page,keepSearchCondition,"game");
		
		MapBean mb=search();
		page = gameService.page(page, mb);
		return "list";
    }
	
	private MapBean search(){
		MapBean mb=new MapBean();
		if(game!=null){
			if(game.getId()!=null)
				mb.put("id", game.getId());
			if(StringUtils.isNotBlank(game.getGameName()))
				mb.put(MapBean.GAME_NAME, game.getGameName());
			if(game.getServiceTime()!=null)
				mb.put("serviceTime", game.getServiceTime());
		}
		mb.put("orderby","id desc");
		return mb;
	}
	
	public String save(){
        if(game!=null){
        	
        	MapBean mb = new MapBean();
        	mb.put(MapBean.GAME_NAME, game.getGameName());
        	mb.put("exceptId", game.getId() == null ? -1 : game.getId());
        	if (gameService.count(mb) > 0) {
        		 addActionMessage("游戏名称不能重复！");
        		 id = game.getId();
        		 return handle();
			}
        	
            if(game.getId()!=null){
            	gameService.update(game);
                addActionMessage("修改信息成功");
            }else{
            	game.setId(Long.valueOf(RandomTool.randomAppId()));
            	game.setGameName(game.getGameName());
            	game.setCreateTime(new Date());
            	game.setSecretKey(RandomTool.randomSecretKey());
            	game.setServiceTime(game.getServiceTime());
            	gameService.save(game);
                addActionMessage("保存信息成功");
            }
        }
        return "save";
    }
	
	public String handle(){
        if(id!=null){
        	game=gameService.getGameById(id);
        }
        return "handle";
    }
	
	public String batchDelete(){
        if(checkedIds!=null){
        	try {
				gameService.bacthDelete(checkedIds);
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
		gameService.delete(id);
		addActionMessage("删除数据成功");
		return "delete";
	}
	
	public Page<Game> getPage() {
		return page;
	}
	public void setPage(Page<Game> page) {
		this.page = page;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
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

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
}
