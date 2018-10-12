package com.item.service;


import com.item.domain.BPlatform;
import com.item.domain.Dashbord;
import com.item.domain.Game;
import com.item.utils.DateUtils;
import com.item.utils.RedisClient;
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
    @Resource
    BGameService bApplicationService ;
    @Resource
    BPlatformService bPlatformService ;
    private String redisPayPre = "rs_totolpay_";
    public Dashbord loadDashbord(){
        Dashbord dashbord = new Dashbord()  ;
        List<Game> list = bApplicationService.list(null);
        List<BPlatform> platformList = bPlatformService.getAllPlatform() ;
        String date = DateUtils.format(new Date(),"yyyy-MM-dd");
        for (Game bApplication:list){
            for (BPlatform bPlatform: platformList) {
                String payKey = redisPayPre + date + "_" + bApplication.getId()+"_"+bPlatform.getId();
                Object redisTemp = RedisClient.get(payKey);
                if (redisTemp != null) {
                    int gamePay = Integer.parseInt(RedisClient.get(payKey).toString());
                    dashbord.setTotalPay(dashbord.getTotalPay() + gamePay);
                }
            }
        }
        return  dashbord ;
    }
}
