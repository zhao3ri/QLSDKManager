package com.item.web.action;

import java.util.*;

import javax.annotation.Resource;

import com.item.domain.BChannel;
import com.item.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.item.domain.Game;
import com.item.domain.report.ReportHistoryDaily;
import com.item.service.BGameService;
import com.item.service.BGamezoneService;
import com.item.service.BChannelService;
import com.item.service.ReportDailyService;
import com.item.service.ReportService;
import com.item.service.SysGameManagerService;

import core.module.orm.MapBean;

public class ReportDailyAction extends BaseAction {
    private static final long serialVersionUID = 5645405406052360424L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportDailyAction.class);

    private List<Game> allGames;
    private Long gameId;
    private Integer clientType;
    private Integer groupType;
    private Integer compareGroupType;
    private String channelIds;
    private String compareChannelIds;
    private String zoneIds;
    private String compareZoneIds;
    private String selectRange;
    private String compareSelectRange;
    private String optionJson;
    private Map<String, Object> result;
    private String channelName;
    private String zoneName;
    private String startDate;
    private String endDate;
    private String keepStatType;

    @Autowired
    private ReportDailyService reportDailyService;
    @Autowired
    private BGameService bGameService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private BGamezoneService gamezoneService;
    @Autowired
    private BChannelService channelService;
    @Resource
    private SysGameManagerService gameManagerService;

    @Override
    protected boolean initData() {
        boolean success= super.initData();
        if (success){
            allGames = getCurrentIdentityGames();
            if (null == gameId) {
                gameId = getFirstGameId();
            }
            if (StringUtil.isEmpty(keepStatType))
                keepStatType = "0";
        }
        return success;
    }

    public String basic() {
        MapBean mb = new MapBean();
        if (StringUtils.isNotBlank(channelName)) {
            mb.put(MapBean.CHANNEL_NAME, channelName);
            BChannel channel = channelService.get(mb);
            channelIds = channel.getId().toString();
        }
        if (StringUtils.isNotBlank(zoneName)) {
            mb.put(MapBean.ZONE_NAME, zoneName);
            mb.put(MapBean.GAME_ID, gameId);
            zoneIds = gamezoneService.get(mb).getZoneId();
        }

        if (!initData())
            return null;
        result = reportDailyService.basic(gameId, clientType, groupType, channelIds, compareChannelIds, zoneIds, compareZoneIds, selectRange, compareSelectRange, 1);
        if (result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "basic";
    }

    public String newly() {
        if (!initData())
            return null;
        result = reportDailyService.basic(gameId, clientType, groupType, channelIds, compareChannelIds, zoneIds, compareZoneIds, selectRange, compareSelectRange, 2);
        if (result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "newly";
    }

    public String first() {
        if (!initData())
            return null;
        result = reportDailyService.basic(gameId, clientType, groupType, channelIds, compareChannelIds, zoneIds, compareZoneIds, selectRange, compareSelectRange, 3);
        if (result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "first";
    }

    public String keep() {
        if (!initData())
            return null;
        result = reportDailyService.basic(gameId, clientType, groupType, channelIds, compareChannelIds, zoneIds, compareZoneIds, selectRange, null, 4);
        if (result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "keep";
    }

    public String loss() {
        if (!initData())
            return null;
        result = reportDailyService.basic(gameId, clientType, null, channelIds, null, zoneIds, null, null, null, 6);
        if (result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "loss";
    }

    public String back() {
        if (!initData())
            return null;
        result = reportDailyService.basic(gameId, clientType, groupType, channelIds, compareChannelIds, zoneIds, compareZoneIds, selectRange, compareSelectRange, 5);
        if (result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "back";
    }

    public String change() {
        if (!initData())
            return null;
        result = reportDailyService.basic(gameId, clientType, groupType, channelIds, compareChannelIds, zoneIds, compareZoneIds, selectRange, compareSelectRange, 7);
        if (result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "change";
    }

    public String operate() {
        if (!initData())
            return null;

        result = reportDailyService.operate(gameId, clientType, channelIds, zoneIds, selectRange);
        if (result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "operate";
    }

    public String chanels() {
        if (!initData())
            return null;

        result = reportDailyService.chanels(gameId, clientType, channelIds, selectRange);
        if (result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "operate";
    }


    public String online() {
        if (!initData())
            return null;
        result = reportService.online(gameId, clientType, channelIds
                , compareChannelIds, zoneIds, compareZoneIds, groupType
                , compareGroupType, selectRange, compareSelectRange);
        if (result.containsKey("selectRange")) {
            selectRange = String.format("%s至%s", result.get("selectRange"), result.get("selectRange"));
        } else if (result.containsKey("startDate") && result.containsKey("endDate")) {
            selectRange = String.format("%s至%s", result.get("startDate"), result.get("endDate"));
        }
        return "online";
    }

    @SuppressWarnings("unchecked")
    public void operateExport() {
        if (!initData())
            return;
        result = reportDailyService.operate(gameId, clientType, channelIds, zoneIds, selectRange);
        List<ReportHistoryDaily> dailies = (List<ReportHistoryDaily>) result.get("data");

        ExcelExport ee = new ExcelExport();
        ee.setHead("运营日报统计报表_" + DateUtils.format(new Date(), "yyyyMMddHHmmss"));
        ee.getEl().add("时间");
        ee.getEl().add("创角用户");
        ee.getEl().add("注册用户");
        ee.getEl().add("新增激活设备");
        ee.getEl().add("游戏启动次数");
        ee.getEl().add("玩家平均在线时长(小时)");
        ee.getEl().add("注册激活转化率");
        ee.getEl().add("创角注册转化率");
        ee.getEl().add("活跃用户");
        ee.getEl().add("次日留存");
        ee.getEl().add("三日留存");
        ee.getEl().add("四日留存");
        ee.getEl().add("五日留存");
        ee.getEl().add("六日留存");
        ee.getEl().add("七日留存");
        ee.getEl().add("十四日留存");
        ee.getEl().add("三十日留存");
        ee.getEl().add("充值金额");
        ee.getEl().add("充值次数");
        ee.getEl().add("充值人数");
        ee.getEl().add("新用户充值额");
        ee.getEl().add("新用户付费人数");
        ee.getEl().add("新用户付费次数");
        ee.getEl().add("新用户付费率");
        ee.getEl().add("新用户ARPU");
        ee.getEl().add("新用户付费ARPU");
        ee.getEl().add("首次付费充值额");
        ee.getEl().add("首次充值用户数");
        ee.getEl().add("首次付费ARPU");
        ee.getEl().add("累计注册设备");
        ee.getEl().add("累计创角用户");
        ee.getEl().add("累计注册用户");
        ee.getEl().add("总活跃用户");
        ee.getEl().add("充值总额");
        ee.getEl().add("充值总次数");
        ee.getEl().add("充值总人数 ");
        ee.getEl().add("总付费ARPU ");
        ee.getEl().add("总注册ARPU ");
        for (int i = 0; i < dailies.size(); i++) {
            List<Excel> e = new ArrayList<Excel>();
            e.add(new Excel(DateUtils.format(dailies.get(i).getStatDate(), "yyyy-MM-dd"), 20));
            e.add(new Excel(dailies.get(i).getRoleUsers(), 20));
            e.add(new Excel(dailies.get(i).getRegUsers(), 20));
            e.add(new Excel(dailies.get(i).getRegDevices(), 20));
            e.add(new Excel(dailies.get(i).getStartTimes(), 20));
            e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getAvgOnlineTime() / 3600), 20));
            if (dailies.get(i).getNewDevices() > 0) {
                e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getRegUsers() * 100 / dailies.get(i).getNewDevices()) + "%", 20));
            } else {
                e.add(new Excel("0.00%", 20));
            }
            if (dailies.get(i).getRegDevices() > 0) {
                e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getRoleUsers() * 100 / dailies.get(i).getRegUsers()) + "%", 20));
            } else {
                e.add(new Excel("0.00%", 20));
            }

            e.add(new Excel(dailies.get(i).getActiveUsers(), 20));

            if (dailies.get(i).getRoleUsers() > 0) {
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 1) {
                    e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getKeepUser1() * 100 / dailies.get(i).getRoleUsers()) + "%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 2) {
                    e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getKeepUser3() * 100 / dailies.get(i).getRoleUsers()) + "%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 3) {
                    e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getKeepUser4() * 100 / dailies.get(i).getRoleUsers()) + "%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 4) {
                    e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getKeepUser5() * 100 / dailies.get(i).getRoleUsers()) + "%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 5) {
                    e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getKeepUser6() * 100 / dailies.get(i).getRoleUsers()) + "%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 6) {
                    e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getKeepUser7() * 100 / dailies.get(i).getRoleUsers()) + "%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 13) {
                    e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getKeepUser14() * 100 / dailies.get(i).getRoleUsers()) + "%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 29) {
                    e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getKeepUser30() * 100 / dailies.get(i).getRoleUsers()) + "%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
            } else {
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 1) {
                    e.add(new Excel("0.00%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 2) {
                    e.add(new Excel("0.00%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 3) {
                    e.add(new Excel("0.00%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 4) {
                    e.add(new Excel("0.00%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 5) {
                    e.add(new Excel("0.00%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 6) {
                    e.add(new Excel("0.00%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 13) {
                    e.add(new Excel("0.00%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
                if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 29) {
                    e.add(new Excel("0.00%", 20));
                } else {
                    e.add(new Excel("--", 20));
                }
            }

            e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getPayAmount() / 100), 20));
            e.add(new Excel(dailies.get(i).getPayTimes(), 20));
            e.add(new Excel(dailies.get(i).getPayUsers(), 20));
            e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getNewUserPayAmount() / 100), 20));
            e.add(new Excel(dailies.get(i).getNewUserPays(), 20));
            e.add(new Excel(dailies.get(i).getNewUserPayTimes(), 20));
            if (dailies.get(i).getRoleUsers() > 0) {
                e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getNewUserPays() * 100 / dailies.get(i).getRoleUsers()) + "%", 20));
                e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getNewUserPayAmount() / dailies.get(i).getRoleUsers() / 100), 20));
            } else {
                e.add(new Excel("0.00%", 20));
                e.add(new Excel("0.00", 20));
            }
            if (dailies.get(i).getNewUserPays() > 0) {
                e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getNewUserPayAmount() / dailies.get(i).getNewUserPays() / 100), 20));
            } else {
                e.add(new Excel("0.00", 20));
            }

            e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getFirstPayAmount() / 100), 20));
            e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getFirstPayUsers() / 100), 20));
            if (dailies.get(i).getFirstPayUsers() > 0) {
                e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getFirstPayAmount() / 100 / dailies.get(i).getFirstPayUsers()), 20));
            } else {
                e.add(new Excel("0.00", 20));
            }

            e.add(new Excel(dailies.get(i).getTotaldevices(), 20));
            e.add(new Excel(dailies.get(i).getTotalRoleUser(), 20));
            e.add(new Excel(dailies.get(i).getTotalRegUser(), 20));
            e.add(new Excel(dailies.get(i).getTotalActiveUsers(), 20));
            e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getTotalPayAmount() / 100), 20));
            e.add(new Excel(dailies.get(i).getTotalPayTimes(), 20));
            e.add(new Excel(dailies.get(i).getTotalPayUsers(), 20));
            if (dailies.get(i).getTotalPayUsers() > 0) {
                e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getTotalPayAmount() / 100 / dailies.get(i).getTotalPayUsers()), 20));
            } else {
                e.add(new Excel("0.00", 20));
            }
            if (dailies.get(i).getTotalRegUser() > 0) {
                e.add(new Excel(DecimallFormatUtil.format((double) dailies.get(i).getTotalPayAmount() / 100 / dailies.get(i).getTotalRegUser()), 20));
            } else {
                e.add(new Excel("0.00", 20));
            }

            ee.getEll().add(e);
        }
        ee.setFoot("总共：" + dailies.size() + "条记录");
        ee.excelExport();
    }

    public List<Game> getAllGames() {
        return allGames;
    }

    public void setAllGames(List<Game> allGames) {
        this.allGames = allGames;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public Integer getCompareGroupType() {
        return compareGroupType;
    }

    public void setCompareGroupType(Integer compareGroupType) {
        this.compareGroupType = compareGroupType;
    }

    public String getChannelIds() {
        return channelIds;
    }

    public void setChannelIds(String channelIds) {
        this.channelIds = channelIds;
    }

    public String getCompareChannelIds() {
        return compareChannelIds;
    }

    public void setCompareChannelIds(String compareChannelIds) {
        this.compareChannelIds = compareChannelIds;
    }

    public String getZoneIds() {
        return zoneIds;
    }

    public void setZoneIds(String zoneIds) {
        this.zoneIds = zoneIds;
    }

    public String getCompareZoneIds() {
        return compareZoneIds;
    }

    public void setCompareZoneIds(String compareZoneIds) {
        this.compareZoneIds = compareZoneIds;
    }

    public String getSelectRange() {
        return selectRange;
    }

    public void setSelectRange(String selectRange) {
        this.selectRange = selectRange;
    }

    public String getCompareSelectRange() {
        return compareSelectRange;
    }

    public void setCompareSelectRange(String compareSelectRange) {
        this.compareSelectRange = compareSelectRange;
    }

    public String getOptionJson() {
        return optionJson;
    }

    public void setOptionJson(String optionJson) {
        this.optionJson = optionJson;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getKeepStatType() {
        return keepStatType;
    }

    public void setKeepStatType(String keepStatType) {
        this.keepStatType = keepStatType;
    }
}
