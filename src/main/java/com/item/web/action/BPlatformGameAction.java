package com.item.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.item.domain.BPlatformGame;
import com.item.service.BPlatformService;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.item.domain.BPlatform;
import com.item.domain.Game;
import com.item.service.BGameService;
import com.item.service.BPlatformGameService;
import com.item.utils.InitSearchCondition;
import com.item.utils.JsonUtil;
import com.item.utils.RedisClient;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.utils.Struts2Utils;
import core.module.web.Struts2Action;

@Action
public class BPlatformGameAction extends Struts2Action {
    private static final long serialVersionUID = 1L;
    @Autowired
    private BPlatformService channelService;
    @Autowired
    private BPlatformGameService bPlatformGameService;
    @Autowired
    private BGameService gameService;

    private Page<BPlatformGame> bPlatformGamePage = new Page<BPlatformGame>(10);

    private Integer keepSearchCondition;

    private BPlatformGame bPlatformGame;

    private Long id;
    private List<BPlatform> bPlatforms;
    private List<Game> games;

    public String list() {
//        bPlatforms = bPlatformAppService.getAllPlatform();
        bPlatforms = channelService.getCurrentIdentityChannelList();
        games = gameService.getGameList();

        bPlatformGame = InitSearchCondition.initEntity(bPlatformGame, keepSearchCondition, "bPlatform");
        bPlatformGamePage = InitSearchCondition.initPage(bPlatformGamePage, keepSearchCondition, "bPlatform");

        MapBean mb = search();
        bPlatformGameService.pageBPlatformGame(bPlatformGamePage, mb);
        return "list";
    }

    public void getGamePlatformsAsync() {
        List<BPlatformGame> platformApps = bPlatformGameService.getByGameId(id);
        try {
            Struts2Utils.getResponse().getWriter().write(JsonUtil.toJsonString(platformApps));
        } catch (IOException e) {
            logger.error("getGamePlatforms error", e);
            e.printStackTrace();
        }
    }


    public MapBean search() {
        MapBean mb = new MapBean();
        if (bPlatformGame != null) {
            if (bPlatformGame.getId() != null) {
                mb.put("id", bPlatformGame.getId());
            }
            if (bPlatformGame.getGameId() != null) {
                mb.put(MapBean.GAME_ID, bPlatformGame.getGameId());
            }
            if (bPlatformGame.getPlatformId() != null) {
                mb.put(MapBean.PLATFORM_ID, bPlatformGame.getPlatformId());
            }
            if (bPlatformGame.getConfigParams() != null) {
                mb.put("configParams", bPlatformGame.getConfigParams());
            }
            if (bPlatformGame.getCreateTime() != null) {
                mb.put("createTime", bPlatformGame.getCreateTime());
            }
            List<Long> ids = getGameIds();
            if (ids != null) {
                mb.put(MapBean.GAME_IDS, ids);
            }
        }
        mb.put("orderby", "id desc");
        logger.info("搜索条件 {}", JsonUtil.toJsonString(mb));
        return mb;
    }

    private List<Long> getGameIds() {
        if (bPlatformGame.getGameName() != null) {
            MapBean mapBean = new MapBean();
            mapBean.put(MapBean.GAME_NAME, bPlatformGame.getGameName());
            List<Game> gameList = gameService.getGameByWhere(mapBean);
            if (gameList == null || gameList.size() == 0) {
                return null;
            } else {
                List<Long> ids = new ArrayList<Long>();
                for (int i = 0; i < gameList.size(); i++) {
                    ids.add(gameList.get(i).getId());
                }
                return ids;
            }
        }
        return null;
    }

    public String delete() {
        if (id != null) {
            bPlatformGame = bPlatformGameService.getBPlatformGameById(id);
        }
        bPlatformGameService.deleteBPlatformGame(id);
        RedisClient.del("rs_pg_" + bPlatformGame.getPlatformId() + "_" + bPlatformGame.getGameId());
        return "delete";
    }

    public String handle() {
        if (id != null) {
            bPlatformGame = bPlatformGameService.getBPlatformGameById(id);
        } else {
//            bPlatforms = channelService.getAllPlatform();
            bPlatforms = channelService.getCurrentIdentityChannelList();
            games = gameService.getGameList();
        }
        return "handle";
    }

    public String view() {
        if (id != null)
            bPlatformGame = bPlatformGameService.getBPlatformGameById(id);
        return "view";
    }

    public String save() {
        if (bPlatformGame.getId() != null) {
            bPlatformGameService.updatePlatformGame(bPlatformGame);
            addActionMessage("修改信息成功");
        } else {
            bPlatformGame.setCreateTime(new Date());
            bPlatformGameService.savePlatformGame(bPlatformGame);
            addActionMessage("保存信息成功");
        }
        logger.debug("del " + "rs_pg_" + bPlatformGame.getPlatformId() + "_" + bPlatformGame.getGameId());

        RedisClient.del("rs_pg_" + bPlatformGame.getPlatformId() + "_" + bPlatformGame.getGameId());
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

    public BPlatformGameService getBPlatformGameService() {
        return bPlatformGameService;
    }

    public void setBPlatformGameService(BPlatformGameService bPlatformGameService) {
        this.bPlatformGameService = bPlatformGameService;
    }

    public Page<BPlatformGame> getBPlatformGamePage() {
        return bPlatformGamePage;
    }

    public void setBPlatformGamePage(Page<BPlatformGame> bPlatformAppPage) {
        this.bPlatformGamePage = bPlatformAppPage;
    }

    public Integer getKeepSearchCondition() {
        return keepSearchCondition;
    }

    public void setKeepSearchCondition(Integer keepSearchCondition) {
        this.keepSearchCondition = keepSearchCondition;
    }

    public BPlatformGame getBPlatformGame() {
        return bPlatformGame;
    }

    public void setBPlatformGame(BPlatformGame bPlatformGame) {
        this.bPlatformGame = bPlatformGame;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
