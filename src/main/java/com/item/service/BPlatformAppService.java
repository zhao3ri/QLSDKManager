package com.item.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.BPlatformAppDao;
import com.item.dao.BPlatformDao;
import com.item.domain.BPlatformApp;
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
public class BPlatformAppService {
    @Autowired
    private BPlatformAppDao bPlatformAppDao;
    @Autowired
    private BPlatformDao bPlatformDao;
    @Resource
    private SysGameManagerService gameManagerService;

    /*
     * 分页，获取页内的数据量
     */
    public Page<BPlatformApp> pageBPlatformApp(Page<BPlatformApp> page, MapBean mb) {
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

        return bPlatformAppDao.find(page, mb, "BPlatformApp.count", "BPlatformApp.page");
    }

    /*
     * 根据id删除相应数据
     */
    public void deleteBPlatformApp(Long id) {
        bPlatformAppDao.delete("BPlatformApp.delete", id);
    }

    /*
     * 根据id获取相应平台游戏关联数据
     */
    public BPlatformApp getBPlatformAppById(Long id) {
        return bPlatformAppDao.get("BPlatformApp.getPlatformAppById", id);
    }

    /*
     * 获取某个游戏全部平台
     */
    @SuppressWarnings("unchecked")
    public List<BPlatformApp> getAllPlatform(Long appId) {
        RedisClient.del("platform_app_all");
        Map<String, List<BPlatformApp>> result = (Map<String, List<BPlatformApp>>) RedisClient.get("platform_app_all", Map.class);
        if (result == null) {
            result = new HashMap<String, List<BPlatformApp>>();
            List<BPlatformApp> bPlatforms = bPlatformAppDao.find("BPlatformApp.list", null);
            for (BPlatformApp bPlatform : bPlatforms) {
                if (result.get(bPlatform.getGameId().toString()) == null) {
                    List<BPlatformApp> thisAppbPlatforms = new ArrayList<BPlatformApp>();
                    thisAppbPlatforms.add(bPlatform);
                    result.put(bPlatform.getGameId().toString(), thisAppbPlatforms);
                } else {
                    List<BPlatformApp> thisAppbPlatforms = result.get(bPlatform.getGameId().toString());
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
    public void updatePlatformApp(BPlatformApp bPlatformApp) {
        bPlatformAppDao.update("BPlatformApp.update", bPlatformApp);
    }

    /*
     * 增加平台游戏关联数据
     */
    public void savePlatformApp(BPlatformApp bPlatformApp) {
        bPlatformAppDao.save("BPlatformApp.save", bPlatformApp);
    }

    public List<BPlatformApp> getByAppId(Long appId) {
        return bPlatformAppDao.find("BPlatformApp.GetByAppId", appId);
    }

}
