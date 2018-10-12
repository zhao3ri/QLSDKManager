package com.item.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.item.domain.BPlatform;
import com.item.domain.BPlatformApp;
import com.item.domain.Game;
import com.item.service.BGameService;
import com.item.service.BPlatformAppService;
import com.item.utils.InitSearchCondition;
import com.item.utils.JsonUtil;
import com.item.utils.RedisClient;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.utils.Struts2Utils;
import core.module.web.Struts2Action;
@Action
public class BPlatformAppAction extends Struts2Action {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BPlatformAppService bPlatformAppService;
	@Autowired
	private BGameService gameService;
	
	private Page<BPlatformApp> bPlatformAppPage=new Page<BPlatformApp>(10);
	
	private Integer keepSearchCondition;
	
	private BPlatformApp bPlatformApp;

	private Long id;
	private List<BPlatform>bPlatforms;
	private List<Game> games;
	
	public String list() {
		bPlatforms = bPlatformAppService.getAllPlatform();
		games = gameService.list();
		
		bPlatformApp = InitSearchCondition.initEntity(bPlatformApp, keepSearchCondition, "bPlatform");
		bPlatformAppPage = InitSearchCondition.initPage(bPlatformAppPage, keepSearchCondition, "bPlatform");
		
		MapBean mb = search();
		bPlatformAppService.pageBPlatformApp(bPlatformAppPage, mb);
		return "list";
	}
	
	public void getGamePlatformsAsync() {
		List<BPlatformApp> platformApps = bPlatformAppService.GetByAppId(id);
		try {
			Struts2Utils.getResponse().getWriter().write(JsonUtil.toJsonString(platformApps));
		} catch (IOException e) {
			logger.error("getGamePlatforms error", e);
			e.printStackTrace();
		}
	}


	public MapBean search() {
		MapBean mb=new MapBean();
		if (bPlatformApp!=null) {
			if (bPlatformApp.getId()!=null) {
				mb.put("id", bPlatformApp.getId());
			}
			if (bPlatformApp.getAppId()!=null) {
				mb.put("appId", bPlatformApp.getAppId());
			}
			if (bPlatformApp.getPlatformId()!=null) {
				mb.put("platformId", bPlatformApp.getPlatformId());
			}
			if (bPlatformApp.getConfigParams()!=null) {
				mb.put("configParams", bPlatformApp.getConfigParams());
			}
			if (bPlatformApp.getCreateTime()!=null) {
				mb.put("createTime", bPlatformApp.getCreateTime());
			}
			List<Long> ids = getAppIds();
			if (ids!=null){
				mb.put("appIds",ids) ;
			}
		}
		mb.put("orderby","id desc");
		logger.info("搜索条件 {}",JsonUtil.toJsonString(mb));
		return mb;
	}

	private List<Long> getAppIds(){
		if (bPlatformApp.getAppName()!=null){
			MapBean mapBean = new MapBean();
			mapBean.put("appName",bPlatformApp.getAppName());
			List<Game> gameList = gameService.getGameByWhere(mapBean);
			if (gameList==null || gameList.size()==0){
				return null;
			}else{
				List<Long> ids = new ArrayList<Long>();
				for (int i = 0; i < gameList.size(); i++) {
					ids.add(gameList.get(i).getId());
				}
				return ids ;
			}
		}
		return null;
	}

	public String delete() {
		if(id!=null){
			bPlatformApp=bPlatformAppService.getBPlatformAppById(id);
		}
		bPlatformAppService.deleteBPlatformApp(id);
		RedisClient.del("rs_pg_" + bPlatformApp.getPlatformId() + "_" + bPlatformApp.getAppId());
		return "delete";
	}
	
	public String handle() {
		if(id!=null){
			bPlatformApp=bPlatformAppService.getBPlatformAppById(id);
		}else {
			bPlatforms=bPlatformAppService.getAllPlatform();
			games = gameService.list();
		}
		return "handle";
	}
	
	public String save() {
		if(bPlatformApp.getId()!=null){
			bPlatformAppService.updatePlatformApp(bPlatformApp);
			addActionMessage("修改信息成功");
		}else {
			bPlatformApp.setCreateTime(new Date());
			bPlatformAppService.savePlatformApp(bPlatformApp);
			addActionMessage("保存信息成功");
		}
		logger.debug("del "+"rs_pg_" + bPlatformApp.getPlatformId() + "_" + bPlatformApp.getAppId());

		RedisClient.del("rs_pg_" + bPlatformApp.getPlatformId() + "_" + bPlatformApp.getAppId());
		return "save";
	}
	
	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	public List<BPlatform> getBPlatforms() {
		return bPlatforms;
	}

	public void setBPlatforms(List<BPlatform> bPlatforms) {
		this.bPlatforms = bPlatforms;
	}

	public BPlatformAppService getBPlatformAppService() {
		return bPlatformAppService;
	}

	public void setBPlatformAppService(BPlatformAppService bPlatformAppService) {
		this.bPlatformAppService = bPlatformAppService;
	}

	public Page<BPlatformApp> getBPlatformAppPage() {
		return bPlatformAppPage;
	}

	public void setBPlatformAppPage(Page<BPlatformApp> bPlatformAppPage) {
		this.bPlatformAppPage = bPlatformAppPage;
	}

	public Integer getKeepSearchCondition() {
		return keepSearchCondition;
	}

	public void setKeepSearchCondition(Integer keepSearchCondition) {
		this.keepSearchCondition = keepSearchCondition;
	}

	public BPlatformApp getBPlatformApp() {
		return bPlatformApp;
	}

	public void setBPlatformApp(BPlatformApp bPlatformApp) {
		this.bPlatformApp = bPlatformApp;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}
}
