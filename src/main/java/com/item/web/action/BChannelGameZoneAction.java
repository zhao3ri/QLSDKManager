/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:RealeaseSdkManage,Id:BPlatformGameZoneDao.java  2015-01-04 09:55:30 zhouxb ]
 */
package com.item.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.item.domain.BChannel;
import com.item.domain.BChannelGameZone;
import com.item.domain.Game;
import com.item.domain.Gamezone;
import com.item.service.BGameService;
import com.item.service.BGamezoneService;
import com.item.service.BChannelGameZoneService;
import com.item.service.BChannelService;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.web.Struts2Action;

/**
 * Action类.
 * <br/>
 *
 * @author zhouxb
 * @version 1.0 2015-01-04 09:55:30
 * @since JDK 1.5
 */
@Action
public class BChannelGameZoneAction extends Struts2Action {

    private static final long serialVersionUID = 197266703365933779L;

    @Autowired
    private BChannelGameZoneService bChannelGameZoneService;
    @Autowired
    private BGameService bGameService;
    @Autowired
    private BChannelService bChannelService;
    @Autowired
    private BGamezoneService bGamezoneService;

    private Page<BChannelGameZone> bPlatformGameZonePage = new Page<BChannelGameZone>(10);
    private String checkedIds;
    private BChannelGameZone bChannelGameZone;
    private String channelIds;
    private String zoneIds;
    private Long gameId;
    private List<BChannelGameZone> channelGameZones;
    private List<Game> games;
    private List<BChannel> channels;
    private Long id;
    private Integer isCompare;
    private List<Gamezone> gamezones;
    private Long platformId;

    public String list() {
        MapBean mb = search();
        bPlatformGameZonePage = bChannelGameZoneService.page(bPlatformGameZonePage, mb);
        games = bGameService.getGameList();
        channels = bChannelService.getCurrentIdentityChannelList();
        bPlatformGameZonePage = bChannelGameZoneService.page(bPlatformGameZonePage, mb);
        return "list";
    }


    public String getPlatformZones() {
        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, gameId);
        mb.put(MapBean.CHANNEL_IDS, channelIds.split(","));
        channelGameZones = bChannelGameZoneService.getChannelZones(mb);
        return "getPlatformZones";
    }

    public String getZonePlatforms() {
        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, gameId);
        mb.put("zoneIds", zoneIds.split(","));
        channelGameZones = bChannelGameZoneService.getZoneChannels(mb);
        return "getZonePlatforms";
    }


    public String save() {
        if (bChannelGameZone != null) {
            if (bChannelGameZone.getId() != null) {
                bChannelGameZoneService.update(bChannelGameZone, zoneIds);
                addActionMessage("修改信息成功");
            } else {
                bChannelGameZoneService.save(bChannelGameZone, zoneIds);
                addActionMessage("保存信息成功");
            }
        }
        return handle();
    }

    public String handle() {
        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, bChannelGameZone.getGameId());
        mb.put(MapBean.CHANNEL_ID, bChannelGameZone.getChannelId());
        gamezones = bGamezoneService.getGamezoneByGameId(bChannelGameZone.getGameId());

        Map<String, String> map = new HashMap<String, String>();
        List<BChannelGameZone> bChannelGameZones = bChannelGameZoneService.list(mb);
        for (BChannelGameZone bChannelGameZone : bChannelGameZones) {
            map.put(bChannelGameZone.getZoneId(), "");
        }

        for (Gamezone gamezone : gamezones) {
            if (map.get(gamezone.getZoneId()) != null) {
                gamezone.setIsHave(1);
            } else {
                gamezone.setIsHave(0);
            }
        }
        return "handle";
    }

    public String view() {
        if (id != null) {
            bChannelGameZone = bChannelGameZoneService.get(id);
        } else {
            addActionMessage("请选择一个ID");
        }
        return "view";
    }

    public String delete() {
        if (checkedIds != null) {
            bChannelGameZoneService.delete(checkedIds);
            addActionMessage("删除数据成功");
        } else {
            addActionMessage("请选择要删除的数据");
        }
        return "delete";
    }

    private MapBean search() {
        MapBean mb = new MapBean();
        if (bChannelGameZone != null) {
            if (bChannelGameZone.getId() != null) {
                mb.put("id", bChannelGameZone.getId());
            }
            if (bChannelGameZone.getChannelId() != null) {
                mb.put(MapBean.CHANNEL_ID, bChannelGameZone.getChannelId());
            }
            if (bChannelGameZone.getGameId() != null) {
                mb.put(MapBean.GAME_ID, bChannelGameZone.getGameId());
            }
            if (bChannelGameZone.getZoneId() != null && !"".equals(bChannelGameZone.getZoneId())) {
                mb.put("zoneId", bChannelGameZone.getZoneId());
            }
        }
        mb.put("orderby", "id desc");
        return mb;
    }

    public Page<BChannelGameZone> getBPlatformGameZonePage() {
        return bPlatformGameZonePage;
    }

    public void setCheckedIds(String checkedIds) {
        this.checkedIds = checkedIds;
    }

    public BChannelGameZone getBPlatformGameZone() {
        return bChannelGameZone;
    }

    public void setBPlatformGameZone(BChannelGameZone bChannelGameZone) {
        this.bChannelGameZone = bChannelGameZone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelIds() {
        return channelIds;
    }

    public void setChannelIds(String channelIds) {
        this.channelIds = channelIds;
    }

    public String getZoneIds() {
        return zoneIds;
    }

    public void setZoneIds(String zoneIds) {
        this.zoneIds = zoneIds;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public List<BChannelGameZone> getChannelGameZones() {
        return channelGameZones;
    }

    public void setChannelGameZones(List<BChannelGameZone> channelGameZones) {
        this.channelGameZones = channelGameZones;
    }

    public Integer getIsCompare() {
        return isCompare;
    }


    public void setIsCompare(Integer isCompare) {
        this.isCompare = isCompare;
    }


    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<BChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<BChannel> channels) {
        this.channels = channels;
    }

    public List<Gamezone> getGamezones() {
        return gamezones;
    }

    public void setGamezones(List<Gamezone> gamezones) {
        this.gamezones = gamezones;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }
}