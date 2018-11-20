package com.item.service;


import com.item.domain.BPlatform;
import com.item.domain.Dashbord;
import com.item.domain.Game;
import com.item.domain.SGame;
import com.item.utils.DateUtils;
import com.item.utils.RedisClient;
import core.module.orm.MapBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by engine on 2017/2/21.
 */
@Service
@Transactional
public class DashbordService {
    private Logger logger = LoggerFactory.getLogger(DashbordService.class);
    private long lastTime = 0;
    private boolean isInit = false;
    @Resource
    BGameService bApplicationService;
    @Resource
    BPlatformService bPlatformService;
    @Resource
    SGameRealtimeService sGameRealtimeService;
    @Resource
    SGameService sGameService;

    private static final String REDIS_PREFIX = "rs_";
    private static final String REDIS_SEPARATOR = "_";
    private static final long TIME_INTERVAL = 3 * 60 * 1000;//实时统计更新时间间隔

    private String redisPayPre = REDIS_PREFIX + "totalpay" + REDIS_SEPARATOR;

    private static final String QUERY_PARAM_LIKE = "like";
    private Dashbord dashbord;

    public Dashbord loadDashbord() {
        if (dashbord == null)
            dashbord = new Dashbord();
        List<Game> list = bApplicationService.list(null);
        List<BPlatform> platformList = bPlatformService.getAllPlatform();
        String date = DateUtils.format(new Date(), "yyyy-MM-dd");
        for (Game bApplication : list) {
            for (BPlatform bPlatform : platformList) {
                String payKey = redisPayPre + date + REDIS_SEPARATOR + bApplication.getId() + REDIS_SEPARATOR + bPlatform.getId();
                Object redisTemp = RedisClient.get(payKey);
                if (redisTemp != null) {
                    int gamePay = Integer.parseInt(RedisClient.get(payKey).toString());
                    dashbord.setTotalPay(dashbord.getTotalPay() + gamePay);
                }
            }
        }
        getCurrentGameStats(date);
        getMonthlyGameStats(date);
        return dashbord;
    }

    /**
     * 获取截止到今天之前本月的新增用户和总支付金额
     */
    private void getMonthlyGameStats(String date) {
        if (isInit) {
            return;
        }
        String month = date.substring(0, date.lastIndexOf("-"));
        MapBean mb = MapBean.getBean();
        mb.put(QUERY_PARAM_LIKE, month);
        SGame sGame = sGameService.summary(mb);
        dashbord.setTotalMonthlyNewUser(sGame.getTotalRegUser());
        dashbord.setTotalMonthlyPay(sGame.getPayAmount());
        MapBean.cleanUp(mb);
        isInit = true;
    }

    /**
     * 获取今日新增用户
     */
    private void getCurrentGameStats(String date) {
        if (System.currentTimeMillis() < lastTime + TIME_INTERVAL) {
            return;
        }
        MapBean mb = MapBean.getBean();
        mb.put(QUERY_PARAM_LIKE, date);
        long newUsers = sGameRealtimeService.getCurrentNewUsers(mb);
        dashbord.setCurrentNewUser((int) newUsers);
        MapBean.cleanUp(mb);
        lastTime = System.currentTimeMillis();
    }

}
