package com.item.service;

import java.util.*;

import com.item.domain.*;
import com.item.domain.report.GameStats;
import com.item.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.item.utils.DateUtils;
import com.item.utils.DecimallFormatUtil;
import com.item.utils.EChartUtil;

import core.module.orm.MapBean;

@Service
@Transactional
public class ReportService {
    @Autowired
    private SGameRealtimeService sGameRealtimeService;
    @Autowired
    private SChannelRealtimeService sChannelRealtimeService;
    @Autowired
    private SZoneChannelRealtimeService sZoneChannelRealtimeService;
    @Autowired
    private BChannelService bChannelService;
    @Autowired
    private BGamezoneService bGamezoneService;

    public Map<String, Object> online(Long appId, Integer clientType, String channelIds, String compareChannelIds, String zoneIds, String compareZoneIds, Integer groupType, Integer compareGroupType, String selectRange, String compareSelectRange, String... statDate) {
        Map<String, Object> result = new HashMap<String, Object>();
        MapBean mb = new MapBean();
        mb.put(MapBean.CLIENT_TYPE, clientType);
        mb.put(MapBean.GAME_ID, appId);

        List<BChannel> platforms = bChannelService.getByIds(channelIds);
        List<Gamezone> gamezones = bGamezoneService.getByIds(appId, zoneIds);
        List<BChannel> comparePlatforms = bChannelService.getByIds(compareChannelIds);
        List<Gamezone> compareGamezones = bGamezoneService.getByIds(appId, compareZoneIds);
        result.put("platforms", platforms);
        result.put("gamezones", gamezones);
        result.put("comparePlatforms", comparePlatforms);
        result.put("compareGamezones", compareGamezones);

        if (StringUtils.isNotBlank(selectRange))
            mb.put("statStartDate", selectRange.split("至")[0]);

        if (StringUtils.isNotBlank(selectRange))
            mb.put("statEndDate", selectRange.split("至")[1]);

        if (StringUtils.isBlank(selectRange)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            String startDate = DateUtils.format(calendar.getTime(), "yyyy-MM-dd");
            String endDate = DateUtils.format(new Date(), "yyyy-MM-dd");
//            String startDate = "2018-11-15";
//            String endDate = "2018-11-16";
            mb.put("statStartDate", startDate);
            mb.put("statEndDate", endDate);
            result.put("startDate", startDate);
            result.put("endDate", endDate);
//            mb.put("statDate", DateUtils.format(new Date()));
//            result.put("selectRange", DateUtils.format(new Date(), "yyyy-MM-dd"));
        }

        if (statDate != null && statDate.length > 0) {
            if (statDate.length == 1) {
                mb.put("statDate", statDate[0]);
            }
            if (statDate.length == 2) {
                mb.put("statStartDate", statDate[0]);
                mb.put("statEndDate", statDate[1]);
            }
        }

        if (StringUtils.isBlank(channelIds) && StringUtils.isBlank(compareChannelIds) && StringUtils.isBlank(zoneIds) && StringUtils.isBlank(compareZoneIds)) {
            if (StringUtils.isBlank(compareSelectRange)) {    //没有时间对比
                if (StringUtils.isNotBlank(selectRange)) {        //有选时间段
                    List<SGameRealtime> sGameRealtimes = sGameRealtimeService.listGroupBy(mb);
                    result.put("optionJson", getOnlineOptionJson(sGameRealtimes, null));

                    mb.put("groupType", "statDate");
                    sGameRealtimes = sGameRealtimeService.listGroupBy(mb);
                    Map<String, List<SGameRealtime>> dataMap = new LinkedHashMap<String, List<SGameRealtime>>();
                    for (SGameRealtime sGameRealtime : sGameRealtimes) {
                        if (dataMap.get(DateUtils.format(sGameRealtime.getStatDate(), "HH:mm:ss")) == null) {
                            List<SGameRealtime> list = new ArrayList<SGameRealtime>();
                            list.add(sGameRealtime);
                            dataMap.put(DateUtils.format(sGameRealtime.getStatDate(), "HH:mm:ss"), list);
                        } else {
                            List<SGameRealtime> list = dataMap.get(DateUtils.format(sGameRealtime.getStatDate(), "HH:mm:ss"));
                            list.add(sGameRealtime);
                            dataMap.put(DateUtils.format(sGameRealtime.getStatDate(), "HH:mm:ss"), list);
                        }
                    }
                    result.put("isCompare", 2);
                    result.put("data", dataMap);
                    result.put("groupType", "statDate");
                } else {                                                                                //没选时间段
                    if (!StringUtil.isEmpty(result.get("startDate").toString()) && !StringUtil.isEmpty(result.get("endDate").toString())) {
                        //页面加载初始化时，默认显示当天和前一天的趋势图
                        List<SGameRealtime> sGameRealtimes = sGameRealtimeService.listGroupBy(mb);
                        String optionJson = getDefaultJson(sGameRealtimes);
                        Iterator<SGameRealtime> it = sGameRealtimes.iterator();
                        while (it.hasNext()) {
                            SGameRealtime s = it.next();
                            if (!DateUtils.format(s.getStatDate()).startsWith(DateUtils.format2yyMMdd(new Date()))) {
                                it.remove();
                            }
                        }
                        result.put("data", sGameRealtimes);
                        result.put("optionJson", optionJson);
                        result.put("isCompare", 0);
                    } else {
                        List<SGameRealtime> sGameRealtimes = sGameRealtimeService.listGroupBy(mb);
                        String optionJson = getOnlineOptionJson(sGameRealtimes, null);
                        result.put("data", sGameRealtimes);
                        result.put("optionJson", optionJson);
                        result.put("isCompare", 0);
                    }
                }
            } else {                                                                                //有时间对比
                List<SGameRealtime> sGameRealtimes = sGameRealtimeService.listGroupBy(mb);
                result.put("data", sGameRealtimes);

                mb.put("statStartDate", compareSelectRange.split("至")[0]);
                mb.put("statEndDate", compareSelectRange.split("至")[1]);
                List<SGameRealtime> compareGameRealtimes = sGameRealtimeService.listGroupBy(mb);
                result.put("compareData", compareGameRealtimes);
                String optionJson = getOnlineOptionJson(sGameRealtimes, compareGameRealtimes);
                result.put("optionJson", optionJson);
                result.put("isCompare", 3);
            }
        } else {
            if (StringUtils.isNotBlank(compareChannelIds) || StringUtils.isNotBlank(compareZoneIds)) {   //有渠道或者分区对比
                mb.put("channelIds", channelIds);
                mb.put("zoneIds", zoneIds);
                List<SChannelRealtime> zonePlatformRealtimes = sChannelRealtimeService.listGroupBy(mb);
                result.put("data", zonePlatformRealtimes);

                mb.put("channelIds", compareChannelIds);
                mb.put("zoneIds", compareZoneIds);
                List<SZoneChannelRealtime> compareZonePlatformRealtimes = sZoneChannelRealtimeService.listGroupBy(mb);
                result.put("compareData", compareZonePlatformRealtimes);

                String optionJson = getOnlineOptionJson(zonePlatformRealtimes, compareZonePlatformRealtimes);
                result.put("optionJson", optionJson);
                result.put("isCompare", 1);
				
				/*List<BChannel> platforms = bPlatformService.getByIds(channelIds);
				List<BChannel> comparePlatforms = bPlatformService.getByIds(compareChannelIds);
				List<Gamezone> gamezones = bGamezoneService.getByIds(appId,zoneIds);
				List<Gamezone> compareGamezones = bGamezoneService.getByIds(appId,compareZoneIds);
				result.put("platforms", platforms);
				result.put("comparePlatforms", comparePlatforms);
				result.put("gamezones", gamezones);
				result.put("compareGamezones", compareGamezones);*/
            } else if (StringUtils.isBlank(compareSelectRange)) {  //无对比
                mb.put("channelIds", channelIds);
                mb.put("zoneIds", zoneIds);
                List<SChannelRealtime> zonePlatformRealtimes = sChannelRealtimeService.listGroupBy(mb);
                String optionJson = getOnlineOptionJson(zonePlatformRealtimes, null);
                result.put("optionJson", optionJson);
                result.put("isCompare", 2);

                mb.put("groupType", groupType);
                List<SChannelRealtime> zonePlatformRealtimesGroupByZone = sChannelRealtimeService.listGroupBy(mb);
				/*List<Gamezone> gamezones = bGamezoneService.getByIds(appId,zoneIds);
				List<BChannel> platforms = bPlatformService.getByIds(channelIds);*/
                if (groupType == 1) {
                    for (SChannelRealtime sZonePlatformRealtime : zonePlatformRealtimesGroupByZone) {
                        for (Gamezone gamezone : gamezones) {
//							if (gamezone.getZoneId().equals(sZonePlatformRealtime.getZoneId())) {
//								sZonePlatformRealtime.setZoneName(gamezone.getZoneName());
                            continue;
//							}
                        }
                    }
                } else if (groupType == 2) {
                    for (SChannelRealtime sZonePlatformRealtime : zonePlatformRealtimesGroupByZone) {
                        for (BChannel bChannel : platforms) {
                            if (bChannel.getId().toString().equals(sZonePlatformRealtime.getChannelId().toString())) {
//								sZonePlatformRealtime.setChannelName(bChannel.getChannelName());
                                continue;
                            }
                        }
                    }
                }

                Map<String, List<SChannelRealtime>> dataMap = new LinkedHashMap<String, List<SChannelRealtime>>();
                for (SChannelRealtime sZonePlatformRealtime : zonePlatformRealtimesGroupByZone) {
                    if (dataMap.get(DateUtils.format(sZonePlatformRealtime.getStatDate(), "HH:mm:ss")) == null) {
                        List<SChannelRealtime> list = new ArrayList<SChannelRealtime>();
                        list.add(sZonePlatformRealtime);
                        dataMap.put(DateUtils.format(sZonePlatformRealtime.getStatDate(), "HH:mm:ss"), list);
                    } else {
                        List<SChannelRealtime> list = dataMap.get(DateUtils.format(sZonePlatformRealtime.getStatDate(), "HH:mm:ss"));
                        list.add(sZonePlatformRealtime);
                        dataMap.put(DateUtils.format(sZonePlatformRealtime.getStatDate(), "HH:mm:ss"), list);
                    }
                }
                result.put("data", dataMap);
            } else {                                                                                                    //有时间对比
                List<SZoneChannelRealtime> sGameRealtimes = sZoneChannelRealtimeService.listGroupBy(mb);
                result.put("data", sGameRealtimes);

                mb.put("statStartDate", compareSelectRange.split("至")[0]);
                mb.put("statEndDate", compareSelectRange.split("至")[1]);
                List<SZoneChannelRealtime> compareGameRealtimes = sZoneChannelRealtimeService.listGroupBy(mb);
                result.put("compareData", compareGameRealtimes);
                String optionJson = getOnlineOptionJson(sGameRealtimes, compareGameRealtimes);
                result.put("optionJson", optionJson);
                result.put("isCompare", 3);
            }
        }
        return result;
    }

    private <T extends SGameRealtime, K> String getOnlineOptionJson(List<T> sRealtimes, List<K> sCompareRealtimes) {
        if (CollectionUtils.isEmpty(sRealtimes)) {
            return "";
        }
        Map<String, K> compareData = new HashMap<String, K>();
        EChartUtil eChartUtil = new EChartUtil("", null);
        List<Object> xValue = new ArrayList<Object>();
        Map<String, List<Object>> map = new LinkedHashMap<String, List<Object>>();

        if (!CollectionUtils.isEmpty(sCompareRealtimes)) {
            for (K k : sCompareRealtimes) {
                SGameRealtime item = (SGameRealtime) k;
                compareData.put(DateUtils.format(item.getStatDate()), k);
            }
        }
        for (T t : sRealtimes) {
            SGameRealtime item = (SGameRealtime) t;
            xValue.add(DateUtils.format(item.getStatDate(), "HH:mm"));

            addData(map, "实时在线", item.getOnlineUsers());
            addData(map, "新增创角", item.getRoleUsers());
            addData(map, "新增设备", item.getNewDevices());
            addData(map, "充值金额", DecimallFormatUtil.format((double) item.getPayAmount() / 100));
            addData(map, "活跃用户", item.getActiveUsers());

            if (!CollectionUtils.isEmpty(sCompareRealtimes)) {
                SGameRealtime comPareItem = (SGameRealtime) compareData.get(DateUtils.format(item.getStatDate()));
                if (comPareItem == null) {
                    comPareItem = new SGameRealtime();
                }
                addData(map, "【实时在线】", comPareItem.getOnlineUsers());
                addData(map, "【新增创角】", comPareItem.getRoleUsers());
                addData(map, "【新增设备】", comPareItem.getNewDevices());
                addData(map, "【充值金额】", DecimallFormatUtil.format((double) comPareItem.getPayAmount() / 100));
                addData(map, "【活跃用户】", comPareItem.getActiveUsers());
            }

        }
        List<String> hiddenData = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(sCompareRealtimes)) {
            hiddenData.add("新增创角");
            hiddenData.add("新增设备");
            hiddenData.add("充值金额");
            hiddenData.add("【新增创角】");
            hiddenData.add("【新增设备】");
            hiddenData.add("【充值金额】");
            hiddenData.add("【活跃用户】");
        }
        eChartUtil.setHiddenData(hiddenData);
        return eChartUtil.getLineOrBarOption(map, xValue, false, false, false, EChartUtil.TYPE_LINE);
    }

    private void addData(Map<String, List<Object>> map, String key, Object o) {
        List<Object> data = map.get(key);
        if (data == null) {
            data = new ArrayList<>();
        }
        data.add(o);
        map.put(key, data);

    }

    private static final String TOOLTIP_FORMAT = "function(params){var tip = \"\";\n" +
            "\t\t\tfor (var i = 0; i < params.length; i++) {\n" +
            "\t\t\t\tvar seriesName = params[i].seriesName;\n" +
            "\t\t\t\tvar type = seriesName.substring(seriesName.indexOf(\"-\") + 3);\n" +
            "\t\t\t\tvar day = seriesName.substring(0, seriesName.indexOf(\"-\") + 3);\n" +
            "\t\t\t\tvar time = params[i].name;\n" +
            "\t\t\t\tvar date = day + \" \" + time\n" +
            "\t\t\t\tif (i > 0 && params[i - 1].seriesName.substring(0, params[i - 1].seriesName.indexOf(\"-\") + 3) == day) {\n" +
            "\t\t\t\t    date = \"\"+params[i].marker;\n" +
            "\t\t\t\t} else {\n" +
            "\t\t\t\t    if(i!==0){date=\"<br/>\"+date;}\n" +
            "\t\t\t\t    date = date + \"<br/> \"+params[i].marker;\n" +
            "\t\t\t\t} \n" +
            "\t\t\t\ttip = tip + date + type + \":&nbsp;\" + params[i].data + \"<br/>\";\n" +
            "\t\t\t}return tip;}";

    public <T extends SGameRealtime> String getDefaultJson(List<T> sRealtimes) {
        if (sRealtimes == null || sRealtimes.isEmpty()) {
            return null;
        }
        List<GameStats> gameStats = GameStats.gameRealTime2GameStats((List<SGameRealtime>) sRealtimes);
        List<String> keys = GameStats.getAllKeys(gameStats);
        Map<String, List<Object>> data = GameStats.getData(keys, gameStats, null, "实时在线", "新增设备", "新增创角", "累计活跃", "充值金额");
        List<Object> xValue = new ArrayList<Object>(keys);
        EChartUtil eChartUtil = new EChartUtil("", null);
        String formatter = TOOLTIP_FORMAT;
        String json = eChartUtil.getLineOrBarOption(data, xValue, false, false, false, EChartUtil.TYPE_LINE, formatter);
//        System.err.println(json);
        return json;
    }
}
