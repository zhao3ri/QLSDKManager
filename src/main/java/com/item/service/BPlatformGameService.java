package com.item.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.item.domain.BPlatformGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.BPlatformGameDao;
import com.item.dao.BPlatformDao;
import com.item.utils.RedisClient;

import core.module.orm.MapBean;
import core.module.orm.Page;

/**
 * @author 作者 lilc
 * @version 创建时间：2014年12月24日 上午11:22:45
 * 类说明
 */
@Service
@Transactional
public class BPlatformGameService {
    @Autowired
    private BPlatformGameDao bPlatformGameDao;
    @Autowired
    private BPlatformDao bPlatformDao;
    @Resource
    private SysGameManagerService gameManagerService;

    /*
     * 分页，获取页内的数据量
     */
    public Page<BPlatformGame> pageBPlatformGame(Page<BPlatformGame> page, MapBean mb) {
        if (mb == null) {
            mb = new MapBean();
        }
        if (mb.containsKey(MapBean.GAME_IDS)) {
            List<Long> permitionIds = gameManagerService.getGameIdsByIdentityId();
            List<Long> apps = (List<Long>) mb.get(MapBean.GAME_IDS);
            for (int i = 0; i < apps.size(); i++) {
                if (permitionIds.contains(apps.get(i))) {
                    continue;
                } else {
                    apps.remove(i);
                    i--;
                }
            }
        } else {
            mb.put(MapBean.GAME_IDS, gameManagerService.getGameIdsByIdentityId());
        }

        return bPlatformGameDao.find(page, mb, "BPlatformGame.count", "BPlatformGame.page");
    }

    /*
     * 根据id删除相应数据
     */
    public void deleteBPlatformGame(Long id) {
        bPlatformGameDao.delete("BPlatformGame.delete", id);
    }

    /*
     * 根据id获取相应平台游戏关联数据
     */
    public BPlatformGame getBPlatformGameById(Long id) {
        return bPlatformGameDao.get("BPlatformGame.getPlatformAppById", id);
    }

    /*
     * 获取某个游戏全部平台
     */
    @SuppressWarnings("unchecked")
    public List<BPlatformGame> getAllPlatform(Long appId) {
        RedisClient.del("platform_app_all");
        Map<String, List<BPlatformGame>> result = (Map<String, List<BPlatformGame>>) RedisClient.get("platform_app_all", Map.class);
        if (result == null) {
            result = new HashMap<String, List<BPlatformGame>>();
            List<BPlatformGame> bPlatforms = bPlatformGameDao.find("BPlatformGame.list", null);
            for (BPlatformGame bPlatform : bPlatforms) {
                if (result.get(bPlatform.getGameId().toString()) == null) {
                    List<BPlatformGame> thisAppbPlatforms = new ArrayList<BPlatformGame>();
                    thisAppbPlatforms.add(bPlatform);
                    result.put(bPlatform.getGameId().toString(), thisAppbPlatforms);
                } else {
                    List<BPlatformGame> thisAppbPlatforms = result.get(bPlatform.getGameId().toString());
                    thisAppbPlatforms.add(bPlatform);
                    result.put(bPlatform.getGameId().toString(), thisAppbPlatforms);
                }
            }
            RedisClient.set("platform_app_all", result);
        }
        return result.get(appId.toString());
    }

    /*
     *根据id更新相应平台游戏关联数据
     */
    public void updatePlatformGame(BPlatformGame bPlatformGame) {
        bPlatformGameDao.update("BPlatformGame.update", bPlatformGame);
    }

    /*
     * 增加平台游戏关联数据
     */
    public void savePlatformGame(BPlatformGame bPlatformGame) {
        bPlatformGameDao.save("BPlatformGame.save", bPlatformGame);
    }

    public List<BPlatformGame> getByGameId(Long appId) {
        return bPlatformGameDao.find("BPlatformGame.GetByAppId", appId);
    }

}
