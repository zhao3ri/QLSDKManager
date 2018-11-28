package com.item.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.item.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.item.dao.GameDao;
import com.item.domain.Game;
import com.item.domain.Gamezone;
import com.item.domain.authority.User;
import com.item.utils.RedisClient;
import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.utils.Struts2Utils;

@Service
@Transactional
public class BGameService {
    @Autowired
    private GameDao gameDao;
    @Autowired
    private BGamezoneService gamezoneService;
    @Resource
    private SysGameManagerService gameManagerService;

    public Page<Game> page(Page<Game> page, MapBean mb) {
        User userInfo = (User) Struts2Utils.getRequest().getSession().getAttribute("sessionUserInfo");
        List<Long> allGameIds = gameManagerService.getGameIdsByIdentityId(userInfo.getIdentityId());
        mb.put(MapBean.GAME_IDS, allGameIds);
        return gameDao.find(page, mb, "BGame.count", "BGame.page");
    }

    public Game getGameById(Long GameId) {
        return gameDao.get("BGame.getGameById", GameId);
    }

    public List<Game> getGameByWhere(MapBean mb) {
        return gameDao.find("BGame.getGameByWhere", mb);
    }

    public void bacthDelete(String checkedIds) {
        try {
            for (String checkedId : StringUtils.split(checkedIds, ",")) {
                gameDao.delete("BGame.delete", Long.parseLong(checkedId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        gameDao.delete("BGame.delete", id);
    }

    public void save(Game entity) {
        Long gameId = entity.getId();
        gameDao.save("BGame.save", entity);
        Long gameIds[] = {gameId};
        //新增游戏默认给roleId为3的超级管理员分配权限
        gameManagerService.save((long) Constants.ADMIN_IDENTITY_ID, gameIds);
    }

    public void update(Game entity) {
        gameDao.update("BGame.update", entity);
    }

    public int count(MapBean mb) {
        return (int) gameDao.countResult("BGame.count", mb);
    }

    public List<Game> getGameList() {
        return getGameList(null);
    }

    public List<Game> getGameList(MapBean mb) {
        User userInfo = (User) Struts2Utils.getRequest().getSession().getAttribute("sessionUserInfo");
        if (userInfo == null) {
            return new ArrayList<>();
        }
        return getGameList(userInfo.getIdentityId(), mb);
    }

    public List<Game> getGameList(long identityId) {
        return getGameList(identityId, null);
    }

    public List<Game> getGameList(long identityId, MapBean mb) {
        if (mb == null)
            mb = new MapBean();
        List<Long> gameIds = gameManagerService.getGameIdsByIdentityId(identityId);
        if (CollectionUtils.isEmpty(gameIds)) {
            gameIds = new ArrayList<Long>();
            gameIds.add(-1L);
        }
        mb.put(MapBean.GAME_IDS, gameIds);
        return gameDao.find("BGame.list", mb);
    }

    @SuppressWarnings("unchecked")
    public List<Gamezone> getZones(Long appId) {
        RedisClient.del("game_zones_all");
        Map<String, List<Gamezone>> result = (Map<String, List<Gamezone>>) RedisClient.get("game_zones_all", Map.class);
        if (result == null) {
            result = new HashMap<String, List<Gamezone>>();
            List<Gamezone> gamezones = gamezoneService.list();
            for (Gamezone gamezone : gamezones) {
                if (result.get(gamezone.getGameId().toString()) == null) {
                    List<Gamezone> appGamezones = new ArrayList<Gamezone>();
                    appGamezones.add(gamezone);
                    result.put(gamezone.getGameId().toString(), appGamezones);
                } else {
                    List<Gamezone> appGamezones = result.get(gamezone.getGameId().toString());
                    appGamezones.add(gamezone);
                    result.put(gamezone.getGameId().toString(), appGamezones);
                }
            }
            RedisClient.set("game_zones_all", result);
        }
        return result.get(appId.toString());
    }
}
