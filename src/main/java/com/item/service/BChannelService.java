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

import com.item.dao.BChannelDao;
import com.item.domain.BChannel;

import core.module.orm.MapBean;
import core.module.orm.Page;

/*
 * 联运渠道service类
 */
@Service
@Transactional
public class BChannelService {
    private static final Logger logger = LoggerFactory.getLogger(BChannelService.class);
    @Autowired
    private BChannelDao bChannelDao;

    @Autowired
    private SBalanceDao sBalanceDao;

    public int countChannel(MapBean mb) {
        return (int) bChannelDao.countResult("BChannel.count", mb);
    }

    public long saveChannel(BChannel bChannel) {
        return (Long) bChannelDao.save("BChannel.save", bChannel);
    }

    public void updateChannel(BChannel bChannel) {
        bChannelDao.update("BChannel.update", bChannel);
    }

    public void deleteChannel(Long id) {
        bChannelDao.delete("BChannel.delete", id);
    }

    public Page<BChannel> pagePlarform(Page<BChannel> page, MapBean mb) {
        return bChannelDao.find(page, mb, "BChannel.count", "BChannel.page");
    }

    public Page<SBalance> pageBalance(Page<SBalance> page, MapBean mb) {
        return sBalanceDao.find(page, mb, "SBalance.count", "SBalance.page");
    }

    public BChannel getChannelById(Long platformId) {
        return bChannelDao.get("BChannel.getChannelById", platformId);
    }

    public List<BChannel> getAllChannel() {
        return bChannelDao.find("BChannelGame.getAllChannel", null);
    }

    public List<SBalance> getAllBalance(MapBean mb) {
        return sBalanceDao.find("SBalance.getAllBalance", mb);
    }

    public List<BChannel> getByIds(String channelIds) {
        if (StringUtils.isBlank(channelIds)) {
            return null;
        }
        return bChannelDao.find("BChannel.getByIds", channelIds);
    }

    //根据渠道名查询渠道名是否唯一
    public Long getChannelCountByName(MapBean mb) {
        return bChannelDao.countResult("BChannel.getChannelByName", mb);
    }

    public BChannel get(MapBean mb) {
        return bChannelDao.get("BChannel.get", mb);
    }

    public int controllerBalance(float money, int type, long platformid, String user) {
        MapBean mapBean = new MapBean();
        mapBean.put("id", platformid);
        BChannel channel = bChannelDao.get("BChannel.get", mapBean);
        int amount = Math.abs((int) (money * 100));
        if (type == 0) {
            channel.setBalance(channel.getBalance() + amount);
        } else {
            if (channel.getBalance() >= amount) {
                channel.setBalance(channel.getBalance() - amount);
            } else {
                return -1;
            }
        }
        channel.setNewversion(channel.getVersion() + 1);
        Object o = bChannelDao.update("BChannel.update", channel);
        if (Integer.parseInt(o.toString()) == 1) {
            SBalance sBalance = new SBalance();
            sBalance.setAmount(amount);
            sBalance.setCreateTime(new Date());
            sBalance.setChannelId(platformid);
            sBalance.setType(type);
            sBalance.setUser(user);
            sBalance.setBalance(channel.getBalance());
            Object ob = sBalanceDao.save("SBalance.save", sBalance);
            logger.info(ob + "----------");
            return 1;
        }
        return 0;
    }

    public void saveIdentityChannel(MapBean mb) {
        bChannelDao.save("BChannel.saveChannel", mb);
    }

    public void saveIdentityChannel(Long identityId, Long channelId) {
        MapBean mb = new MapBean();
        mb.put("identityId", identityId);
        mb.put(MapBean.CHANNEL_ID, channelId);
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
            mb.put(MapBean.CHANNEL_ID, channelId);
            saveIdentityChannel(mb);
        }
    }

    public List<BChannel> getIdentityChannelList(long identityId) {
        return bChannelDao.find("BChannel.getChannelList", identityId);
    }

    public List<BChannel> getCurrentIdentityChannelList() {
        return getIdentityChannelList(AuthCacheManager.getInstance().getUser().getIdentityId());
    }

    public void deleteIdentityChannel(long identityId) {
        bChannelDao.delete("BChannel.deleteIdentityChannel", identityId);
    }

    public void deleteIdentitiesChannel(String identityIds) {
        for (String identityId : StringUtils.split(identityIds, ",")) {
            deleteIdentityChannel(Long.valueOf(identityId));
        }
    }
}
