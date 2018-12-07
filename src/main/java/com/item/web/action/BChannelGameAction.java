package com.item.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.item.domain.BChannelGame;
import com.item.service.BChannelService;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.item.domain.BChannel;
import com.item.domain.Game;
import com.item.service.BGameService;
import com.item.service.BChannelGameService;
import com.item.utils.InitSearchCondition;
import com.item.utils.JsonUtil;
import com.item.utils.RedisClient;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.utils.Struts2Utils;
import core.module.web.Struts2Action;

@Action
public class BChannelGameAction extends Struts2Action {
    private static final long serialVersionUID = 1L;
    @Autowired
    private BChannelService channelService;
    @Autowired
    private BChannelGameService bChannelGameService;
    @Autowired
    private BGameService gameService;

    private Page<BChannelGame> bChannelGamePage = new Page<BChannelGame>(10);

    private Integer keepSearchCondition;

    private BChannelGame bChannelGame;

    private Long id;
    private List<BChannel> bChannels;
    private List<Game> games;

    public String list() {
//        bChannels = bPlatformAppService.getAllChannel();
        bChannels = channelService.getCurrentIdentityChannelList();
        games = gameService.getGameList();

        bChannelGame = InitSearchCondition.initEntity(bChannelGame, keepSearchCondition, "bChannel");
        bChannelGamePage = InitSearchCondition.initPage(bChannelGamePage, keepSearchCondition, "bChannel");

        MapBean mb = search();
        bChannelGameService.pageBChannelGame(bChannelGamePage, mb);
        return "list";
    }

    public void getGameChannelsAsync() {
        List<BChannelGame> platformApps = bChannelGameService.getByGameId(id);
        try {
            Struts2Utils.getResponse().getWriter().write(JsonUtil.toJsonString(platformApps));
        } catch (IOException e) {
            logger.error("getGamePlatforms error", e);
            e.printStackTrace();
        }
    }


    public MapBean search() {
        MapBean mb = new MapBean();
        if (bChannelGame != null) {
            if (bChannelGame.getId() != null) {
                mb.put("id", bChannelGame.getId());
            }
            if (bChannelGame.getGameId() != null) {
                mb.put(MapBean.GAME_ID, bChannelGame.getGameId());
            }
            if (bChannelGame.getChannelId() != null) {
                mb.put(MapBean.CHANNEL_ID, bChannelGame.getChannelId());
            }
            if (bChannelGame.getConfigParams() != null) {
                mb.put("configParams", bChannelGame.getConfigParams());
            }
            if (bChannelGame.getCreateTime() != null) {
                mb.put("createTime", bChannelGame.getCreateTime());
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
        if (bChannelGame.getGameName() != null) {
            MapBean mapBean = new MapBean();
            mapBean.put(MapBean.GAME_NAME, bChannelGame.getGameName());
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
            bChannelGame = bChannelGameService.getBChannelGameById(id);
        }
        bChannelGameService.deleteBChannelGame(id);
        RedisClient.del("rs_pg_" + bChannelGame.getChannelId() + "_" + bChannelGame.getGameId());
        return "delete";
    }

    public String handle() {
        if (id != null) {
            bChannelGame = bChannelGameService.getBChannelGameById(id);
        } else {
//            bChannels = channelService.getAllChannel();
            bChannels = channelService.getCurrentIdentityChannelList();
            games = gameService.getGameList();
        }
        return "handle";
    }

    public String view() {
        if (id != null)
            bChannelGame = bChannelGameService.getBChannelGameById(id);
        return "view";
    }

    public String save() {
        if (bChannelGame.getId() != null) {
            bChannelGameService.updateChannelGame(bChannelGame);
            addActionMessage("修改信息成功");
        } else {
            bChannelGame.setCreateTime(new Date());
            bChannelGameService.saveChannelGame(bChannelGame);
            addActionMessage("保存信息成功");
        }
        logger.debug("del " + "rs_pg_" + bChannelGame.getChannelId() + "_" + bChannelGame.getGameId());

        RedisClient.del("rs_pg_" + bChannelGame.getChannelId() + "_" + bChannelGame.getGameId());
        return "save";
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<BChannel> getBChannels() {
        return bChannels;
    }

    public void setBChannels(List<BChannel> bChannels) {
        this.bChannels = bChannels;
    }

    public BChannelGameService getBChannelGameService() {
        return bChannelGameService;
    }

    public void setBChannelGameService(BChannelGameService bChannelGameService) {
        this.bChannelGameService = bChannelGameService;
    }

    public Page<BChannelGame> getBChannelGamePage() {
        return bChannelGamePage;
    }

    public void setBChannelGamePage(Page<BChannelGame> page) {
        this.bChannelGamePage = page;
    }

    public Integer getKeepSearchCondition() {
        return keepSearchCondition;
    }

    public void setKeepSearchCondition(Integer keepSearchCondition) {
        this.keepSearchCondition = keepSearchCondition;
    }

    public BChannelGame getBChannelGame() {
        return bChannelGame;
    }

    public void setBChannelGame(BChannelGame bChannelGame) {
        this.bChannelGame = bChannelGame;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
