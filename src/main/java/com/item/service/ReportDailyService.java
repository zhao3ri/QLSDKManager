package com.item.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.item.domain.BChannel;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.item.dao.ReportDailyDao;
import com.item.dao.ReportHistoryDailyDao;
import com.item.domain.Game;
import com.item.domain.Gamezone;
import com.item.domain.report.ReportDaily;
import com.item.domain.report.ReportHistoryDaily;
import com.item.utils.DateUtils;
import com.item.utils.DecimallFormatUtil;
import com.item.utils.EChartUtil;

import core.module.orm.MapBean;

@Service
@Transactional
public class ReportDailyService {
    private final Logger logger = Logger.getLogger(ReportDailyService.class);
    @Autowired
    private ReportDailyDao reportDailyDao;
    @Autowired
    private ReportHistoryDailyDao reportHistoryDailyDao;
    @Autowired
    private BGamezoneService bGamezoneService;
    @Autowired
    private BChannelService bChannelService;
    @Autowired
    private BGameService bGameService;

    public List<ReportDaily> list(MapBean mb) {
        if ("game".equals(mb.get("dimension"))) {
            return reportDailyDao.find("ReportDaily.listGame", mb);
        } else if ("platform".equals(mb.get("dimension"))) {
            return reportDailyDao.find("ReportDaily.listPlatform", mb);
        } else if ("zone".equals(mb.get("dimension"))) {
            return reportDailyDao.find("ReportDaily.listZone", mb);
        } else {
            return null;
        }
    }

    public List<ReportDaily> getPlatformStats(MapBean mb) {
        return reportDailyDao.find("ReportDaily.listGame", mb);
    }

    public List<ReportHistoryDaily> listOperate(MapBean mb) {
        logger.info("--------------" + mb.get("dimension"));
        if ("game".equals(mb.get("dimension"))) {
            return reportHistoryDailyDao.find("ReportHistoryDaily.listGame", mb);
        } else if ("platform".equals(mb.get("dimension"))) {
            return reportHistoryDailyDao.find("ReportHistoryDaily.listPlatform", mb);
        } else if ("zone".equals(mb.get("dimension"))) {
            return reportHistoryDailyDao.find("ReportHistoryDaily.listZone", mb);
        } else if ("chanelSummary".equals(mb.get("dimension"))) {
            return reportHistoryDailyDao.find("ReportHistoryDaily.listPlatformSummary", mb);
        }
        return null;
    }

    public Map<String, Object> basic(Long gameId, Integer clientType, Integer groupType, String channelIds, String compareChannelIds, String zoneIds, String compareZoneIds, String selectRange, String compareSelectRange, Integer type) {
        Map<String, Object> result = new HashMap<String, Object>();
        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, gameId);
        mb.put("clientType", clientType);
        mb.put("channelIds", StringUtils.isBlank(channelIds) ? null : StringUtils.split(channelIds, ","));
        mb.put("zoneIds", StringUtils.isBlank(zoneIds) ? null : StringUtils.split(zoneIds, ","));

        if (StringUtils.isNotBlank(selectRange)) {
            mb.put("statStartDate", selectRange.split("至")[0]);
            mb.put("statEndDate", selectRange.split("至")[1]);
        } else {
            Calendar calendar = Calendar.getInstance();
            //by zhangxibin edit 将时间间隔设置成5天 time 20160503
            calendar.add(Calendar.DATE, -1);
            mb.put("statEndDate", DateUtils.format(calendar.getTime(), "yyyy-MM-dd"));
            calendar.add(Calendar.DATE, -4);
            mb.put("statStartDate", DateUtils.format(calendar.getTime(), "yyyy-MM-dd"));
            result.put("selectRange", mb.get("statStartDate") + " 至 " + mb.get("statEndDate"));
        }
        List<BChannel> platforms = bChannelService.getByIds(channelIds);
        List<Gamezone> gamezones = bGamezoneService.getByIds(gameId, zoneIds);
        List<BChannel> comparePlatforms = bChannelService.getByIds(compareChannelIds);
        List<Gamezone> compareGamezones = bGamezoneService.getByIds(gameId, compareZoneIds);
        result.put("platforms", platforms);
        result.put("gamezones", gamezones);
        result.put("comparePlatforms", comparePlatforms);
        result.put("compareGamezones", compareGamezones);

        if (StringUtils.isBlank(channelIds) && StringUtils.isBlank(compareChannelIds) && StringUtils.isBlank(zoneIds) && StringUtils.isBlank(compareZoneIds)) {
            mb.put("dimension", "game");
        } else if (StringUtils.isBlank(zoneIds) && StringUtils.isBlank(compareZoneIds)) {
            mb.put("dimension", "platform");
        } else {
            mb.put("dimension", "zone");
        }

        if (StringUtils.isNotBlank(compareChannelIds) || StringUtils.isNotBlank(compareZoneIds)) {        //渠道区服对比
            mb.put("groupby", "statDate");
            List<ReportDaily> reportDailies = list(mb);

            mb.put("channelIds", StringUtils.isBlank(compareChannelIds) ? null : StringUtils.split(compareChannelIds, ","));
            mb.put("zoneIds", StringUtils.isBlank(compareZoneIds) ? null : StringUtils.split(compareZoneIds, ","));
            List<ReportDaily> compareReportDailies = list(mb);

            result.put("data", reportDailies);
            result.put("compareData", compareReportDailies);
            result.put("type", 1);
            result.put("group", groupType);
            result.put("optionJson", getOptionJson(reportDailies, compareReportDailies, false, type));
        } else if (StringUtils.isNotBlank(compareSelectRange)) {                                            //时间对比
            mb.put("groupby", "statDate");
            List<ReportDaily> reportDailies = list(mb);
            Date startTime = DateUtils.parse(mb.getString("statStartDate"), "yyyy-MM-dd");
            Date endTime = DateUtils.parse(mb.getString("statEndDate"), "yyyy-MM-dd");
            int day = DateUtils.getIntervalDays(startTime, endTime);

            mb.put("groupby", null);
            result.put("data", list(mb));

            mb.put("groupby", "statDate");
            mb.put("statStartDate", compareSelectRange.split("至")[0]);
            startTime = DateUtils.parse(mb.getString("statStartDate"), "yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startTime);
            calendar.add(Calendar.DATE, day);
            endTime = calendar.getTime();
            mb.put("statEndDate", DateUtils.format(endTime, "yyyy-MM-dd"));
            List<ReportDaily> compareReportDailies = list(mb);

            mb.put("groupby", null);
            result.put("compareData", list(mb));
            result.put("optionJson", getOptionJson(reportDailies, compareReportDailies, false, type));
        } else {                                                                                            //无对比
            if (null != groupType && 1 == groupType) {                                                                        //有选多分区
                mb.put("groupby", "gameId,zoneId,statDate");
                List<ReportDaily> reportDailies = list(mb);
                Map<String, List<ReportDaily>> data = new LinkedHashMap<String, List<ReportDaily>>();
                for (ReportDaily reportDaily : reportDailies) {
                    if (data.get(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd")) == null) {
                        List<ReportDaily> list = new ArrayList<ReportDaily>();
                        list.add(reportDaily);
                        data.put(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"), list);
                    } else {
                        List<ReportDaily> list = data.get(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"));
                        list.add(reportDaily);
                        data.put(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"), list);
                    }
                    for (Gamezone gamezone : gamezones) {
                        if (reportDaily.getZoneId().equals(gamezone.getZoneId())) {
                            reportDaily.setZoneName(gamezone.getZoneName());
                            break;
                        }
                    }
                }

                result.put("data", data);
                result.put("type", 2);
                result.put("group", "zone");

                mb.put("groupby", "statDate");
                result.put("optionJson", getOptionJson(list(mb), null, false, type));
            } else if (null != groupType && 2 == groupType) {                                                                    //有选多渠道
                mb.put("groupby", "gameId,platformId,statDate");
                List<ReportDaily> reportDailies = list(mb);

                Map<String, List<ReportDaily>> data = new LinkedHashMap<String, List<ReportDaily>>();
                for (ReportDaily reportDaily : reportDailies) {
                    if (data.get(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd")) == null) {
                        List<ReportDaily> list = new ArrayList<ReportDaily>();
                        list.add(reportDaily);
                        data.put(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"), list);
                    } else {
                        List<ReportDaily> list = data.get(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"));
                        list.add(reportDaily);
                        data.put(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"), list);
                    }

                    for (BChannel bChannel : platforms) {
                        if (bChannel.getId().toString().equals(reportDaily.getChannelId().toString())) {
                            reportDaily.setChannelName(bChannel.getChannelName());
                            break;
                        }
                    }
                }
                result.put("data", data);
                result.put("type", 2);
                result.put("group", "platform");

                mb.put("groupby", "statDate");
                result.put("optionJson", getOptionJson(list(mb), null, false, type));
            } else {
                mb.put("groupby", "gameId,statDate");
                List<ReportDaily> reportDailies = list(mb);
                List<Game> games = bGameService.getGameList();
                Map<String, List<ReportDaily>> data = new LinkedHashMap<String, List<ReportDaily>>();
                for (ReportDaily reportDaily : reportDailies) {
                    if (data.get(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd")) == null) {
                        List<ReportDaily> list = new ArrayList<ReportDaily>();
                        list.add(reportDaily);
                        data.put(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"), list);
                    } else {
                        List<ReportDaily> list = data.get(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"));
                        list.add(reportDaily);
                        data.put(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"), list);
                    }

                    for (Game game : games) {
                        if (game.getId().intValue() == reportDaily.getGameId().intValue()) {
                            reportDaily.setGameName(game.getGameName());
                            break;
                        }
                    }
                }
                result.put("data", data);
                result.put("type", 2);
                result.put("group", "game");

                mb.put("groupby", "statDate");
                result.put("optionJson", getOptionJson(list(mb), null, true, type));
            }
        }
        return result;
    }

    private String getOptionJson(List<ReportDaily> reportDailies, List<ReportDaily> compareReportDailies, boolean flag, Integer type) {
        if (CollectionUtils.isEmpty(reportDailies))
            return null;

        if (type == 1) {
            return getBasicOptionJson(reportDailies, compareReportDailies, flag);
        } else if (type == 2) {
            return getNewOptionJson(reportDailies, compareReportDailies);
        } else if (type == 3) {
            return getFirstOptionJson(reportDailies, compareReportDailies);
        } else if (type == 4) {
            return null;
        } else if (type == 5) {
            return getBackOptionJson(reportDailies, compareReportDailies);
        } else if (type == 6) {
            return null;
        } else if (type == 7) {
            return getChangeOptionJson(reportDailies, compareReportDailies);
        }
        return null;
    }

    private String getChangeOptionJson(List<ReportDaily> reportDailies, List<ReportDaily> compareReportDailies) {
        EChartUtil eChartUtil = new EChartUtil("", null);
        List<Object> xValue = new ArrayList<Object>();
        Map<String, List<Object>> map = new LinkedHashMap<String, List<Object>>();
        map.put("创角用户", new ArrayList<Object>());
        map.put("注册用户", new ArrayList<Object>());
        map.put("注册设备", new ArrayList<Object>());
        map.put("注册激活转化率(%)", new ArrayList<Object>());
        map.put("创角注册转化率(%)", new ArrayList<Object>());

        for (ReportDaily reportDaily : reportDailies) {
            xValue.add(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"));
            map.get("创角用户").add(reportDaily.getRoleUsers());
            map.get("注册用户").add(reportDaily.getRegUsers());
            map.get("注册设备").add(reportDaily.getRegDevices());
            map.get("注册激活转化率(%)").add(reportDaily.getNewDevices() > 0 ? DecimallFormatUtil.format((double) reportDaily.getRegDevices() * 100 / reportDaily.getNewDevices()) : "0.00");
            map.get("创角注册转化率(%)").add(reportDaily.getRegDevices() > 0 ? DecimallFormatUtil.format((double) reportDaily.getRoleDevices() * 100 / reportDaily.getRegDevices()) : "0.00");
        }

        if (!CollectionUtils.isEmpty(compareReportDailies)) {
            map.put("", new ArrayList<Object>());
            map.put("【创角用户】", new ArrayList<Object>());
            map.put("【注册用户】", new ArrayList<Object>());
            map.put("【注册设备】", new ArrayList<Object>());
            map.put("【注册激活转化率(%)】", new ArrayList<Object>());
            map.put("【创角注册转化率(%)】", new ArrayList<Object>());

            for (ReportDaily reportDaily : compareReportDailies) {
                map.get("【创角用户】").add(reportDaily.getRoleUsers());
                map.get("【注册用户】").add(reportDaily.getRegUsers());
                map.get("【注册设备】").add(reportDaily.getRegDevices());
                map.get("【注册激活转化率(%)】").add(reportDaily.getNewDevices() > 0 ? DecimallFormatUtil.format((double) reportDaily.getRegDevices() * 100 / reportDaily.getNewDevices()) : "0.00");
                map.get("【创角注册转化率(%)】").add(reportDaily.getRegDevices() > 0 ? DecimallFormatUtil.format((double) reportDaily.getRoleDevices() * 100 / reportDaily.getRegDevices()) : "0.00");
            }
        }

        List<String> hiddenData = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(compareReportDailies)) {
            hiddenData.add("注册用户");
            hiddenData.add("注册设备");
            hiddenData.add("注册激活转化率(%)");
            hiddenData.add("创角注册转化率(%)");

            hiddenData.add("【注册用户】");
            hiddenData.add("【注册设备】");
            hiddenData.add("【注册激活转化率(%)】");
            hiddenData.add("【创角注册转化率(%)】");
        }
        eChartUtil.setHiddenData(hiddenData);
        return eChartUtil.getLineOrBarOption(map, xValue, false, false, false, EChartUtil.TYPE_LINE);
    }

    private String getBackOptionJson(List<ReportDaily> reportDailies, List<ReportDaily> compareReportDailies) {
        EChartUtil eChartUtil = new EChartUtil("", null);
        List<Object> xValue = new ArrayList<Object>();
        Map<String, List<Object>> map = new LinkedHashMap<String, List<Object>>();
        map.put("非付费玩家回流", new ArrayList<Object>());
        map.put("付费玩家回流", new ArrayList<Object>());

        for (ReportDaily reportDaily : reportDailies) {
            xValue.add(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"));
            map.get("非付费玩家回流").add(reportDaily.getBackUsers() - reportDaily.getBackPayUsers());
            map.get("付费玩家回流").add(reportDaily.getBackPayUsers());
        }

        if (!CollectionUtils.isEmpty(compareReportDailies)) {
            map.put("", new ArrayList<Object>());
            map.put("【非付费玩家回流】", new ArrayList<Object>());
            map.put("【付费玩家回流】", new ArrayList<Object>());

            for (ReportDaily reportDaily : compareReportDailies) {
                map.get("【非付费玩家回流】").add(reportDaily.getBackUsers() - reportDaily.getBackPayUsers());
                map.get("【付费玩家回流】").add(reportDaily.getBackPayUsers());
            }
        }
        List<String> hiddenData = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(compareReportDailies)) {
            hiddenData.add("付费玩家回流");

            hiddenData.add("【付费玩家回流】");
        }
        eChartUtil.setHiddenData(hiddenData);
        return eChartUtil.getLineOrBarOption(map, xValue, false, false, false, EChartUtil.TYPE_LINE);
    }

    private String getFirstOptionJson(List<ReportDaily> reportDailies, List<ReportDaily> compareReportDailies) {
        EChartUtil eChartUtil = new EChartUtil("", null);
        List<Object> xValue = new ArrayList<Object>();
        Map<String, List<Object>> map = new LinkedHashMap<String, List<Object>>();
        map.put("首次付费用户充值额", new ArrayList<Object>());
        map.put("首次付费用户数", new ArrayList<Object>());
        map.put("首次付费ARPU", new ArrayList<Object>());

        for (ReportDaily reportDaily : reportDailies) {
            xValue.add(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"));
            map.get("首次付费用户充值额").add(DecimallFormatUtil.format((double) reportDaily.getFirstPayAmount() / 100));
            map.get("首次付费用户数").add(reportDaily.getFirstPayUsers());
            map.get("首次付费ARPU").add(reportDaily.getFirstPayUsers() > 0 ? DecimallFormatUtil.format(new BigDecimal((double) reportDaily.getFirstPayAmount() / 100 / reportDaily.getFirstPayUsers())) : "0.00");
        }

        if (!CollectionUtils.isEmpty(compareReportDailies)) {
            map.put("", new ArrayList<Object>());
            map.put("【首次付费用户充值额】", new ArrayList<Object>());
            map.put("【首次付费用户数】", new ArrayList<Object>());
            map.put("【首次付费ARPU】", new ArrayList<Object>());

            for (ReportDaily reportDaily : compareReportDailies) {
                map.get("【首次付费用户充值额】").add(DecimallFormatUtil.format((double) reportDaily.getFirstPayAmount() / 100));
                map.get("【首次付费用户数】").add(reportDaily.getFirstPayUsers());
                map.get("【首次付费ARPU】").add(reportDaily.getFirstPayUsers() > 0 ? DecimallFormatUtil.format(new BigDecimal((double) reportDaily.getFirstPayAmount() / 100 / reportDaily.getFirstPayUsers())) : "0.00");
            }
        }

        List<String> hiddenData = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(compareReportDailies)) {
            hiddenData.add("首次付费用户数");
            hiddenData.add("首次付费ARPU");

            hiddenData.add("【首次付费用户数】");
            hiddenData.add("【首次付费ARPU】");
        }
        eChartUtil.setHiddenData(hiddenData);
        return eChartUtil.getLineOrBarOption(map, xValue, false, false, false, EChartUtil.TYPE_LINE);
    }

    private String getNewOptionJson(List<ReportDaily> reportDailies, List<ReportDaily> compareReportDailies) {
        EChartUtil eChartUtil = new EChartUtil("", null);
        List<Object> xValue = new ArrayList<Object>();
        Map<String, List<Object>> map = new LinkedHashMap<String, List<Object>>();
        map.put("创角用户", new ArrayList<Object>());
        map.put("注册用户", new ArrayList<Object>());
        map.put("注册设备", new ArrayList<Object>());
        map.put("新用户充值额", new ArrayList<Object>());
        map.put("新用户付费次数", new ArrayList<Object>());
        map.put("新用户付费人数", new ArrayList<Object>());
        map.put("新用户付费率(%)", new ArrayList<Object>());
        map.put("新用户ARPU", new ArrayList<Object>());
        map.put("新用户付费ARPU", new ArrayList<Object>());

        for (ReportDaily reportDaily : reportDailies) {
            xValue.add(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"));
            map.get("创角用户").add(reportDaily.getRoleUsers());
            map.get("注册用户").add(reportDaily.getRegUsers());
            map.get("注册设备").add(reportDaily.getRegDevices());
            map.get("新用户充值额").add(DecimallFormatUtil.format((double) reportDaily.getNewUserPayAmount() / 100));
            map.get("新用户付费次数").add(reportDaily.getNewUserPayTimes());
            map.get("新用户付费人数").add(reportDaily.getNewUserPays());
            map.get("新用户付费率(%)").add(reportDaily.getRoleUsers() > 0 ? DecimallFormatUtil.format(new BigDecimal((double) reportDaily.getNewUserPays() * 100 / reportDaily.getRoleUsers())) : "0.00");
            map.get("新用户ARPU").add(reportDaily.getRoleUsers() > 0 ? DecimallFormatUtil.format(new BigDecimal((double) reportDaily.getNewUserPayAmount() / 100 / reportDaily.getRoleUsers())) : "0.00");
            map.get("新用户付费ARPU").add(reportDaily.getNewUserPays() > 0 ? DecimallFormatUtil.format(new BigDecimal((double) reportDaily.getNewUserPayAmount() / 100 / reportDaily.getNewUserPays())) : "0.00");
        }

        if (!CollectionUtils.isEmpty(compareReportDailies)) {
            map.put("", new ArrayList<Object>());
            map.put("【创角用户】", new ArrayList<Object>());
            map.put("【注册用户】", new ArrayList<Object>());
            map.put("【注册设备】", new ArrayList<Object>());
            map.put("【新用户充值额】", new ArrayList<Object>());
            map.put("【新用户付费次数】", new ArrayList<Object>());
            map.put("【新用户付费人数】", new ArrayList<Object>());
            map.put("【新用户付费率(%)】", new ArrayList<Object>());
            map.put("【新用户ARPU】", new ArrayList<Object>());
            map.put("【新用户付费ARPU】", new ArrayList<Object>());

            for (ReportDaily reportDaily : compareReportDailies) {
                map.get("【创角用户】").add(reportDaily.getRoleUsers());
                map.get("【注册用户】").add(reportDaily.getRegUsers());
                map.get("【注册设备】").add(reportDaily.getRegDevices());
                map.get("【新用户充值额】").add(DecimallFormatUtil.format((double) reportDaily.getNewUserPayAmount() / 100));
                map.get("【新用户付费次数】").add(reportDaily.getNewUserPayTimes());
                map.get("【新用户付费人数】").add(reportDaily.getNewUserPays());
                map.get("【新用户付费率(%)】").add(reportDaily.getRoleUsers() > 0 ? DecimallFormatUtil.format(new BigDecimal((double) reportDaily.getNewUserPays() * 100 / reportDaily.getRoleUsers())) : "0.00");
                map.get("【新用户ARPU】").add(reportDaily.getRoleUsers() > 0 ? DecimallFormatUtil.format(new BigDecimal((double) reportDaily.getNewUserPayAmount() / 100 / reportDaily.getRoleUsers())) : "0.00");
                map.get("【新用户付费ARPU】").add(reportDaily.getNewUserPays() > 0 ? DecimallFormatUtil.format(new BigDecimal((double) reportDaily.getNewUserPayAmount() / 100 / reportDaily.getNewUserPays())) : "0.00");
            }
        }

        List<String> hiddenData = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(compareReportDailies)) {
            hiddenData.add("注册用户");
            hiddenData.add("注册设备");
            hiddenData.add("新用户充值额");
            hiddenData.add("新用户付费次数");
            hiddenData.add("新用户付费人数");
            hiddenData.add("新用户付费率(%)");
            hiddenData.add("新用户ARPU");
            hiddenData.add("新用户付费ARPU");

            hiddenData.add("【注册用户】");
            hiddenData.add("【注册设备】");
            hiddenData.add("【新用户充值额】");
            hiddenData.add("【新用户付费次数】");
            hiddenData.add("【新用户付费人数】");
            hiddenData.add("【新用户付费率(%)】");
            hiddenData.add("【新用户ARPU】");
            hiddenData.add("【新用户付费ARPU】");
        }
        eChartUtil.setHiddenData(hiddenData);
        return eChartUtil.getLineOrBarOption(map, xValue, false, false, false, EChartUtil.TYPE_LINE);
    }

    private String getBasicOptionJson(List<ReportDaily> reportDailies, List<ReportDaily> compareReportDailies, boolean flag) {
        EChartUtil eChartUtil = new EChartUtil("", null);
        List<Object> xValue = new ArrayList<Object>();
        Map<String, List<Object>> map = new LinkedHashMap<String, List<Object>>();
        map.put("创角用户", new ArrayList<Object>());
        map.put("注册用户", new ArrayList<Object>());
        map.put("注册设备", new ArrayList<Object>());
        map.put("活跃用户", new ArrayList<Object>());
        if (flag) map.put("游戏启动次数", new ArrayList<Object>());
        map.put("新用户付费率(%)", new ArrayList<Object>());
        map.put("充值额", new ArrayList<Object>());
        map.put("充值人数", new ArrayList<Object>());
        map.put("新用户充值额", new ArrayList<Object>());
        map.put("新用户充值人数", new ArrayList<Object>());
        map.put("玩家平均在线时长", new ArrayList<Object>());
        map.put("活跃日ARPU", new ArrayList<Object>());

        for (ReportDaily reportDaily : reportDailies) {
            xValue.add(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"));
            map.get("创角用户").add(reportDaily.getRoleUsers());
            map.get("注册用户").add(reportDaily.getRegUsers());
            map.get("注册设备").add(reportDaily.getRegDevices());
            map.get("活跃用户").add(reportDaily.getActiveUsers());
            if (flag) map.get("游戏启动次数").add(reportDaily.getStartTimes() == null ? 0 : reportDaily.getStartTimes());
            map.get("新用户付费率(%)").add(reportDaily.getRoleUsers() > 0 ? DecimallFormatUtil.format(new BigDecimal((double) reportDaily.getNewUserPays() * 100 / reportDaily.getRoleUsers())) : "0.00");
            map.get("充值额").add(DecimallFormatUtil.format((double) reportDaily.getPayAmount() / 100));
            map.get("充值人数").add(reportDaily.getPayUsers());
            map.get("新用户充值额").add(DecimallFormatUtil.format((double) reportDaily.getNewUserPayAmount() / 100));
            map.get("新用户充值人数").add(reportDaily.getNewUserPays());
            map.get("玩家平均在线时长").add(DecimallFormatUtil.format(new BigDecimal((double) reportDaily.getAvgOnlineTime() / 3600)));
            map.get("活跃日ARPU").add(reportDaily.getActiveUsers() > 0 ? DecimallFormatUtil.format(new BigDecimal((double) reportDaily.getPayAmount() / 100 / reportDaily.getActiveUsers())) : "0.00");
        }

        if (!CollectionUtils.isEmpty(compareReportDailies)) {
            map.put("", new ArrayList<Object>());
            map.put("【创角用户】", new ArrayList<Object>());
            map.put("【注册用户】", new ArrayList<Object>());
            map.put("【注册设备】", new ArrayList<Object>());
            map.put("【活跃用户】", new ArrayList<Object>());
            if (flag) map.put("【游戏启动次数】", new ArrayList<Object>());
            map.put("【新用户付费率(%)】", new ArrayList<Object>());
            map.put("【充值额】", new ArrayList<Object>());
            map.put("【充值人数】", new ArrayList<Object>());
            map.put("【新用户充值额】", new ArrayList<Object>());
            map.put("【新用户充值人数】", new ArrayList<Object>());
            map.put("【玩家平均在线时长】", new ArrayList<Object>());
            map.put("【活跃日ARPU】", new ArrayList<Object>());

            for (ReportDaily reportDaily : compareReportDailies) {
                map.get("【创角用户】").add(reportDaily.getRoleUsers());
                map.get("【注册用户】").add(reportDaily.getRegUsers());
                map.get("【注册设备】").add(reportDaily.getRegDevices());
                map.get("【活跃用户】").add(reportDaily.getActiveUsers());
                if (flag) map.get("【游戏启动次数】").add(reportDaily.getStartTimes());
                map.get("【新用户付费率(%)】").add(reportDaily.getRoleUsers() > 0 ? DecimallFormatUtil.format(new BigDecimal((double) reportDaily.getNewUserPays() * 100 / reportDaily.getRoleUsers())) : "0.00");
                map.get("【充值额】").add(DecimallFormatUtil.format((double) reportDaily.getPayAmount() / 100));
                map.get("【充值人数】").add(reportDaily.getPayUsers());
                map.get("【新用户充值额】").add(DecimallFormatUtil.format((double) reportDaily.getNewUserPayAmount() / 100));
                map.get("【新用户充值人数】").add(reportDaily.getNewUserPays());
                map.get("【玩家平均在线时长】").add(reportDaily.getAvgOnlineTime());
                map.get("【活跃日ARPU】").add(reportDaily.getActiveUsers() > 0 ? DecimallFormatUtil.format(new BigDecimal((double) reportDaily.getPayAmount() / 100 / reportDaily.getActiveUsers())) : "0.00");
            }
        }
        List<String> hiddenData = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(compareReportDailies)) {
            hiddenData.add("注册用户");
            hiddenData.add("注册设备");
            hiddenData.add("活跃用户");
            hiddenData.add("游戏启动次数");
            hiddenData.add("新用户付费率(%)");
            hiddenData.add("充值额");
            hiddenData.add("充值人数");
            hiddenData.add("新用户充值额");
            hiddenData.add("新用户充值人数");
            hiddenData.add("玩家平均在线时长");
            hiddenData.add("活跃日ARPU");

            hiddenData.add("【注册用户】");
            hiddenData.add("【注册设备】");
            hiddenData.add("【活跃用户】");
            hiddenData.add("【游戏启动次数】");
            hiddenData.add("【新用户付费率(%)】");
            hiddenData.add("【充值额】");
            hiddenData.add("【充值人数】");
            hiddenData.add("【新用户充值额】");
            hiddenData.add("【新用户充值人数】");
            hiddenData.add("【玩家平均在线时长】");
            hiddenData.add("【活跃日ARPU】 ");
        }
        eChartUtil.setHiddenData(hiddenData);
        return eChartUtil.getLineOrBarOption(map, xValue, false, false, false, EChartUtil.TYPE_LINE);
    }

    public Map<String, Object> operate(Long gameId, Integer clientType, String channelIds, String zoneIds, String selectRange) {
        Map<String, Object> result = new HashMap<String, Object>();

        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, gameId);
        mb.put(MapBean.CLIENT_TYPE, clientType);
        mb.put("channelIds", StringUtils.isBlank(channelIds) ? null : channelIds);
        mb.put("zoneIds", StringUtils.isBlank(zoneIds) ? null : zoneIds);
        mb.put("groupby", "a.`statDate`");

        if (StringUtils.isNotBlank(selectRange)) {
            mb.put("statStartDate", selectRange.split("至")[0]);
            mb.put("statEndDate", selectRange.split("至")[1]);
        } else {
            Calendar calendar = Calendar.getInstance();
            //by zhangxibin edit 将时间间隔设置成5天 time 20160503
            calendar.add(Calendar.DATE, -1);
            mb.put("statEndDate", DateUtils.format(calendar.getTime(), "yyyy-MM-dd"));
            calendar.add(Calendar.DATE, -4);
            mb.put("statStartDate", DateUtils.format(calendar.getTime(), "yyyy-MM-dd"));
            result.put("selectRange", mb.get("statStartDate") + " 至 " + mb.get("statEndDate"));
        }
        if (StringUtils.isBlank(channelIds) && StringUtils.isBlank(zoneIds)) {
            mb.put("dimension", "game");
        } else if (StringUtils.isBlank(zoneIds)) {
            mb.put("dimension", "platform");
        } else {
            mb.put("dimension", "zone");
        }
        result.put("data", listOperate(mb));
        return result;
    }

    public Map<String, Object> chanels(Long gameId, Integer clientType, String channelIds, String selectRange) {
        Map<String, Object> result = new HashMap<String, Object>();

        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, gameId);
        mb.put("clientType", clientType);
        mb.put("channelIds", StringUtils.isBlank(channelIds) ? null : channelIds);
        mb.put("groupby", "a.`platformId`");

        if (StringUtils.isNotBlank(selectRange)) {
            mb.put("statStartDate", selectRange.split("至")[0]);
            mb.put("statEndDate", selectRange.split("至")[1]);
        } else {
            Calendar calendar = Calendar.getInstance();
            //by zhangxibin edit 将时间间隔设置成5天 time 20160503
            calendar.add(Calendar.DATE, -1);
            mb.put("statEndDate", DateUtils.format(calendar.getTime(), "yyyy-MM-dd"));
            calendar.add(Calendar.DATE, -4);
            mb.put("statStartDate", DateUtils.format(calendar.getTime(), "yyyy-MM-dd"));
            result.put("selectRange", mb.get("statStartDate") + " 至 " + mb.get("statEndDate"));
        }
        mb.put("dimension", "chanelSummary");
        result.put("data", listOperate(mb));
        return result;
    }

}
