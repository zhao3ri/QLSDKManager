package com.item.service;

import java.util.Date;
import java.util.List;

import com.item.dao.SBalanceDao;
import com.item.domain.SBalance;
import com.item.service.authority.AuthCacheManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.BPlatformDao;
import com.item.domain.BPlatform;

import core.module.orm.MapBean;
import core.module.orm.Page;

/*
 * 联运平台service类
 */
@Service
@Transactional
public class BPlatformService {
    private static final Logger logger = LoggerFactory.getLogger(BPlatformService.class);
    @Autowired
    private BPlatformDao bPlatformDao;

    @Autowired
    private SBalanceDao sBalanceDao;

    public int countPlatform(MapBean mb) {
        return (int) bPlatformDao.countResult("BPlatform.count", mb);
    }

    public long savePlatform(BPlatform bPlatform) {
        return (Long) bPlatformDao.save("BPlatform.save", bPlatform);
    }

    public void updatePlatform(BPlatform bPlatform) {
        bPlatformDao.update("BPlatform.update", bPlatform);
    }

    public void deletePlatform(Long id) {
        bPlatformDao.delete("BPlatform.delete", id);
    }

    public Page<BPlatform> pagePlarform(Page<BPlatform> page, MapBean mb) {
        return bPlatformDao.find(page, mb, "BPlatform.count", "BPlatform.page");
    }

    public Page<SBalance> pageBalance(Page<SBalance> page, MapBean mb) {
        return sBalanceDao.find(page, mb, "SBalance.count", "SBalance.page");
    }

    public BPlatform getPlatformById(Long platformId) {
        return bPlatformDao.get("BPlatform.getPlatformById", platformId);
    }

    public List<BPlatform> getAllPlatform() {
        return bPlatformDao.find("BPlatformGame.getAllPlatform", null);
    }

    public List<SBalance> getAllBalance(MapBean mb) {
        return sBalanceDao.find("SBalance.getAllBalance", mb);
    }

    public List<BPlatform> getByIds(String channelIds) {
        if (StringUtils.isBlank(channelIds)) {
            return null;
        }
        return bPlatformDao.find("BPlatform.getByIds", channelIds);
    }

    //根据平台名查询平台名是否唯一
    public Long getPlatformCountByName(MapBean mb) {
        return bPlatformDao.countResult("BPlatform.getPlatformByName", mb);
    }

    public BPlatform get(MapBean mb) {
        return bPlatformDao.get("BPlatform.get", mb);
    }

    public int controllerBalance(float money, int type, long platformid, String user) {
        MapBean mapBean = new MapBean();
        mapBean.put("id", platformid);
        BPlatform platform = bPlatformDao.get("BPlatform.get", mapBean);
        int amount = Math.abs((int) (money * 100));
        if (type == 0) {
            platform.setBalance(platform.getBalance() + amount);
        } else {
            if (platform.getBalance() >= amount) {
                platform.setBalance(platform.getBalance() - amount);
            } else {
                return -1;
            }
        }
        platform.setNewversion(platform.getVersion() + 1);
        Object o = bPlatformDao.update("BPlatform.update", platform);
        if (Integer.parseInt(o.toString()) == 1) {
            SBalance sBalance = new SBalance();
            sBalance.setAmount(amount);
            sBalance.setCreateTime(new Date());
            sBalance.setChannelId(platformid);
            sBalance.setType(type);
            sBalance.setUser(user);
            sBalance.setBalance(platform.getBalance());
            Object ob = sBalanceDao.save("SBalance.save", sBalance);
            logger.info(ob + "----------");
            return 1;
        }
        return 0;
    }

    public void saveIdentityChannel(MapBean mb) {
        bPlatformDao.save("BPlatform.saveChannel", mb);
    }

    public void saveIdentityChannel(Long identityId, Long channelId) {
        MapBean mb = new MapBean();
        mb.put("identityId", identityId);
        mb.put("channelId", channelId);
        saveIdentityChannel(mb);
    }

    public void saveIdentityChannel(Long identityId, Long[] channelIds) {
        if (null == channelIds) {
            return;
        }
        deleteIdentityChannel(identityId);
        MapBean mb = new MapBean();
        mb.put("identityId", identityId);
        for (Long channelId : channelIds) {
            mb.put("channelId", channelId);
            saveIdentityChannel(mb);
        }
    }

    public List<BPlatform> getIdentityChannelList(long identityId) {
        return bPlatformDao.find("BPlatform.getChannelList", identityId);
    }

    public List<BPlatform> getCurrentIdentityChannelList() {
        return getIdentityChannelList(AuthCacheManager.getInstance().getUser().getIdentityId());
    }

    public void deleteIdentityChannel(long identityId) {
        bPlatformDao.delete("BPlatform.deleteIdentityChannel", identityId);
    }

    public void deleteIdentitiesChannel(String identityIds) {
        for (String identityId : StringUtils.split(identityIds, ",")) {
            deleteIdentityChannel(Long.valueOf(identityId));
        }
    }
}
