package com.item.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.item.domain.BChannelGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.BChannelGameDao;
import com.item.dao.BChannelDao;
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
public class BChannelGameService {
    @Autowired
    private BChannelGameDao bChannelGameDao;
    @Autowired
    private BChannelDao bChannelDao;
    @Resource
    private SysGameManagerService gameManagerService;

    /*
     * 分页，获取页内的数据量
     */
    public Page<BChannelGame> pageBChannelGame(Page<BChannelGame> page, MapBean mb) {
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

        return bChannelGameDao.find(page, mb, "BChannelGame.count", "BChannelGame.page");
    }

    /*
     * 根据id删除相应数据
     */
    public void deleteBChannelGame(Long id) {
        bChannelGameDao.delete("BChannelGame.delete", id);
    }

    /*
     * 根据id获取相应平台游戏关联数据
     */
    public BChannelGame getBChannelGameById(Long id) {
        return bChannelGameDao.get("BChannelGame.getChannelAppById", id);
    }

    /*
     * 获取某个游戏全部平台
     */
    @SuppressWarnings("unchecked")
    public List<BChannelGame> getAllChannel(Long appId) {
        RedisClient.del("channel_game_all");
        Map<String, List<BChannelGame>> result = (Map<String, List<BChannelGame>>) RedisClient.get("channel_game_all", Map.class);
        if (result == null) {
            result = new HashMap<String, List<BChannelGame>>();
            List<BChannelGame> bChannels = bChannelGameDao.find("BChannelGame.list", null);
            for (BChannelGame bChannel : bChannels) {
                if (result.get(bChannel.getGameId().toString()) == null) {
                    List<BChannelGame> thisAppbChannels = new ArrayList<BChannelGame>();
                    thisAppbChannels.add(bChannel);
                    result.put(bChannel.getGameId().toString(), thisAppbChannels);
                } else {
                    List<BChannelGame> thisAppbChannels = result.get(bChannel.getGameId().toString());
                    thisAppbChannels.add(bChannel);
                    result.put(bChannel.getGameId().toString(), thisAppbChannels);
                }
            }
            RedisClient.set("channel_game_all", result);
        }
        return result.get(appId.toString());
    }

    /*
     *根据id更新相应平台游戏关联数据
     */
    public void updateChannelGame(BChannelGame bChannelGame) {
        bChannelGameDao.update("BChannelGame.update", bChannelGame);
    }

    /*
     * 增加平台游戏关联数据
     */
    public void saveChannelGame(BChannelGame bChannelGame) {
        bChannelGameDao.save("BChannelGame.save", bChannelGame);
    }

    public List<BChannelGame> getByGameId(Long appId) {
        return bChannelGameDao.find("BChannelGame.GetByAppId", appId);
    }

}
