package com.item.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.item.domain.Game;
import com.item.domain.SRoleDaily;
import com.item.domain.SRoleTwentyMinute;
import com.item.utils.DateUtils;
import com.item.utils.EChartUtil;
import com.item.utils.Excel;
import com.item.utils.ExcelExport;

import core.module.orm.MapBean;
import core.module.orm.Page;

@Service
@Transactional
public class RoleReportService {

    @Autowired
    private SRoleTwentyMinuteService sRoleTwentyMinuteService;

    @Autowired
    private SRoleDailyService sRoleDailyService;

    @Autowired
    private SPlayTimeDistributeDailyService sPlayTimeDistributeDailyService;

    @Autowired
    private SRolePlayTimeDailyService sRolePlayTimeDailyService;

    @Autowired
    private BGameService bGameService;

    public Map<String, Object> realTimeLogin(Long appId, Integer clientType, Integer platformId
            , String zoneId, String selectRange) {
        Map<String, Object> result = new HashMap<String, Object>();
        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, appId);
        mb.put(MapBean.CHANNEL_ID, platformId);
        mb.put("zoneId", StringUtils.isBlank(zoneId) ? null : zoneId);

        if (StringUtils.isNotBlank(selectRange)) {
            mb.put("statDate", selectRange);
        } else {
            mb.put("statDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
            result.put("selectRange", mb.get("statDate"));
        }
        result.put("data", sRoleTwentyMinuteService.listRealTimeLogin(mb));
        return result;
    }


    public Map<String, Object> dailyLogin(Long appId, Integer clientType, Integer platformId, String zoneId, String selectRange) {
        Map<String, Object> result = new HashMap<String, Object>();
        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, appId);
        mb.put(MapBean.CHANNEL_ID, platformId);
        mb.put("zoneId", StringUtils.isBlank(zoneId) ? null : zoneId);
        mb.put("orderby", "`statDate`");

        if (StringUtils.isNotBlank(selectRange)) {
            mb.put("statStartDate", selectRange.split("至")[0]);
            mb.put("statEndDate", selectRange.split("至")[1]);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            mb.put("statStartDate", DateUtils.format(calendar.getTime(), "yyyy-MM-dd"));
            mb.put("statEndDate", DateUtils.format(calendar.getTime(), "yyyy-MM-dd"));
            result.put("selectRange", mb.get("statStartDate") + " 至 " + mb.get("statEndDate"));
        }
        result.put("data", sRoleDailyService.listDailyLogin(mb));
        return result;
    }

    public Map<String, Object> online(Long appId, Integer clientType, Integer platformId
            , String zoneId, String selectRange) {
        Map<String, Object> result = new HashMap<String, Object>();
        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, appId);
        mb.put(MapBean.CHANNEL_ID, platformId);
        mb.put("zoneId", StringUtils.isBlank(zoneId) ? null : zoneId);

        SRoleTwentyMinute history = sRoleTwentyMinuteService.historyTopOnlines(mb);
        result.put("historyTopOnlines", history == null ? 0 : history.getRoleOnlines());
        result.put("historyTimeOnlines", history == null ? 0 : history.getStatDate());
        mb.put("statDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        result.put("dailyTopOnlines", sRoleTwentyMinuteService.maxRoleOnlines(mb));
        result.put("dailyLastOnlines", sRoleTwentyMinuteService.minRoleOnlines(mb));
        result.put("dailyAvgOnlines", sRoleTwentyMinuteService.avgRoleOnlines(mb));
        result.put("roleOnlines", sRoleTwentyMinuteService.newRoleOnlines(mb));

        if (StringUtils.isNotBlank(selectRange)) {
            mb.put("statDate", selectRange);
        }
        List<SRoleTwentyMinute> roleOnlinesList = sRoleTwentyMinuteService.listRealTimeLogin(mb);
        result.put("data", roleOnlinesList);
        result.put("optionJson", getOnlineOptionJson(roleOnlinesList));
        return result;
    }

    public void excelExportOnline(ExcelExport ee, Long appId, Integer clientType, Integer platformId
            , String zoneId, String selectRange) {
        ee.setHead("实时在线报表_" + DateUtils.format(new Date(), "yyyyMMddHHmmss"));
        ee.getEl().add("游戏名称");
        ee.getEl().add("游戏编号");
        ee.getEl().add("时间");
        ee.getEl().add("实时在线");

        Game game = bGameService.getGameById(appId);
        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, appId);
        mb.put(MapBean.CHANNEL_ID, platformId);
        mb.put("zoneId", StringUtils.isBlank(zoneId) ? null : zoneId);
        if (StringUtils.isNotBlank(selectRange)) {
            mb.put("statDate", selectRange);
        } else {
            mb.put("statDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        }

        List<SRoleTwentyMinute> roleOnlinesList = sRoleTwentyMinuteService.listRealTimeLogin(mb);
        for (int i = 0; i < roleOnlinesList.size(); i++) {
            List<Excel> e = new ArrayList<Excel>();
            e.add(new Excel(game.getGameName(), 20));
            e.add(new Excel(appId, 20));
            e.add(new Excel(DateUtils.format(roleOnlinesList.get(i).getStatDate(), "yyyy-MM-dd HH:mm:ss"), 20));
            e.add(new Excel(roleOnlinesList.get(i).getRoleOnlines(), 20));
            ee.getEll().add(e);
        }
        ee.setFoot("总共：" + roleOnlinesList.size() + "条记录");
    }


    private String getOnlineOptionJson(List<SRoleTwentyMinute> onlineList) {
        if (CollectionUtils.isEmpty(onlineList)) {
            return "";
        }
        EChartUtil eChartUtil = new EChartUtil("", null);
        List<Object> xValue = new ArrayList<Object>();
        Map<String, List<Object>> map = new LinkedHashMap<String, List<Object>>();
        map.put("实时在线", new ArrayList<Object>());

        for (SRoleTwentyMinute obj : onlineList) {
            xValue.add(DateUtils.format(obj.getStatDate(), "HH:mm"));
            map.get("实时在线").add(obj.getRoleOnlines());
        }
        return eChartUtil.getLineOrBarOption(map, xValue, false, false, false, EChartUtil.TYPE_LINE);
    }

    public Map<String, Object> onlineDaily(Long appId, Integer clientType, Integer platformId
            , String zoneId, String selectRange) {
        Map<String, Object> result = new HashMap<String, Object>();
        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, appId);
        mb.put(MapBean.CHANNEL_ID, platformId);
        mb.put("zoneId", StringUtils.isBlank(zoneId) ? null : zoneId);

        SRoleTwentyMinute history = sRoleTwentyMinuteService.historyTopOnlines(mb);
        result.put("historyTopOnlines", history == null ? 0 : history.getRoleOnlines());
        result.put("historyTimeOnlines", history == null ? 0 : history.getStatDate());

        mb.put("statDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        result.put("dailyTopOnlines", sRoleTwentyMinuteService.maxRoleOnlines(mb));
        result.put("dailyLastOnlines", sRoleTwentyMinuteService.minRoleOnlines(mb));
        result.put("dailyAvgOnlines", sRoleTwentyMinuteService.avgRoleOnlines(mb));
        result.put("roleOnlines", sRoleTwentyMinuteService.newRoleOnlines(mb));
        mb.remove("statDate");

        if (StringUtils.isNotBlank(selectRange)) {
            mb.put("statStartDate", selectRange.split("至")[0]);
            mb.put("statEndDate", selectRange.split("至")[1]);
        } else {
            mb.put("statStartDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
            mb.put("statEndDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
            result.put("selectRange", mb.get("statStartDate") + " 至 " + mb.get("statEndDate"));
        }
        result.put("data", sRoleTwentyMinuteService.onlineDaily(mb));
        return result;
    }

    public void excelExportOnlineDaily(ExcelExport ee, Long appId, Integer clientType, Integer platformId
            , String zoneId, String selectRange) {
        ee.setHead("实时在线统计报表_" + DateUtils.format(new Date(), "yyyyMMddHHmmss"));
        ee.getEl().add("游戏名称");
        ee.getEl().add("游戏编号");
        ee.getEl().add("时间");
        ee.getEl().add("最高在线");
        ee.getEl().add("最低在线");
        ee.getEl().add("平均在线");

        Game game = bGameService.getGameById(appId);
        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, appId);
        mb.put(MapBean.CHANNEL_ID, platformId);
        mb.put("zoneId", StringUtils.isBlank(zoneId) ? null : zoneId);
        if (StringUtils.isNotBlank(selectRange)) {
            mb.put("statStartDate", selectRange.split("至")[0]);
            mb.put("statEndDate", selectRange.split("至")[1]);
        } else {
            mb.put("statStartDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
            mb.put("statEndDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        }
        List<MapBean> roleDailyList = sRoleTwentyMinuteService.onlineDaily(mb);

        for (int i = 0; i < roleDailyList.size(); i++) {
            List<Excel> e = new ArrayList<Excel>();
            e.add(new Excel(game.getGameName(), 20));
            e.add(new Excel(appId, 20));
            e.add(new Excel(DateUtils.format((Date) roleDailyList.get(i).get("statDate"), "yyyy-MM-dd"), 20));
            e.add(new Excel(roleDailyList.get(i).get("maxRoleOnlines"), 20));
            e.add(new Excel(roleDailyList.get(i).get("minRoleOnlines"), 20));
            e.add(new Excel(roleDailyList.get(i).get("avgRoleOnlines"), 20));
            ee.getEll().add(e);
        }
        ee.setFoot("总共：" + roleDailyList.size() + "条记录");
    }

    public Map<String, Object> daily(Long appId, Integer clientType, Integer platformId
            , String zoneId, String selectRange) {
        Map<String, Object> result = new HashMap<String, Object>();
        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, appId);
        mb.put(MapBean.CHANNEL_ID, platformId);
        mb.put("zoneId", StringUtils.isBlank(zoneId) ? null : zoneId);

        SRoleTwentyMinute history = sRoleTwentyMinuteService.historyTopOnlines(mb);
        result.put("historyTopOnlines", history == null ? 0 : history.getRoleOnlines());
        result.put("historyTimeOnlines", history == null ? 0 : history.getStatDate());

        mb.put("statDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        result.put("dailyTopOnlines", sRoleTwentyMinuteService.maxRoleOnlines(mb));
        result.put("dailyLastOnlines", sRoleTwentyMinuteService.minRoleOnlines(mb));
        result.put("dailyAvgOnlines", sRoleTwentyMinuteService.avgRoleOnlines(mb));
        result.put("roleOnlines", sRoleTwentyMinuteService.newRoleOnlines(mb));
        mb.remove("statDate");

        if (StringUtils.isNotBlank(selectRange)) {
            mb.put("statStartDate", selectRange.split("至")[0]);
            mb.put("statEndDate", selectRange.split("至")[1]);
        } else {
            Calendar calerndar = Calendar.getInstance();
            calerndar.add(Calendar.DATE, -1);
            mb.put("statStartDate", DateUtils.format(calerndar.getTime(), "yyyy-MM-dd"));
            mb.put("statEndDate", DateUtils.format(calerndar.getTime(), "yyyy-MM-dd"));
            result.put("selectRange", mb.get("statStartDate") + " 至 " + mb.get("statEndDate"));
        }
        List<SRoleDaily> roleDailyList = sRoleDailyService.listDailyLogin(mb);
        result.put("optionJson", getRoleDailyJson(roleDailyList));
        return result;
    }

    public void excelExportDaily(ExcelExport ee, Long appId, Integer clientType, Integer platformId
            , String zoneId, String selectRange) {
        ee.setHead("日统计报表_" + DateUtils.format(new Date(), "yyyyMMddHHmmss"));
        ee.getEl().add("游戏名称");
        ee.getEl().add("游戏编号");
        ee.getEl().add("登录人数");
        ee.getEl().add("创建时间");

        Game game = bGameService.getGameById(appId);

        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, appId);
        mb.put(MapBean.CHANNEL_ID, platformId);
        mb.put("zoneId", StringUtils.isBlank(zoneId) ? null : zoneId);
        if (StringUtils.isNotBlank(selectRange)) {
            mb.put("statStartDate", selectRange.split("至")[0]);
            mb.put("statEndDate", selectRange.split("至")[1]);
        } else {
            Calendar calerndar = Calendar.getInstance();
            calerndar.add(Calendar.DATE, -1);
            mb.put("statStartDate", DateUtils.format(calerndar.getTime(), "yyyy-MM-dd"));
            mb.put("statEndDate", DateUtils.format(calerndar.getTime(), "yyyy-MM-dd"));
        }
        List<SRoleDaily> roleDailyList = sRoleDailyService.listDailyLogin(mb);

        for (int i = 0; i < roleDailyList.size(); i++) {
            List<Excel> e = new ArrayList<Excel>();
            e.add(new Excel(game.getGameName(), 20));
            e.add(new Excel(appId, 20));
            e.add(new Excel(roleDailyList.get(i).getRoleLogins(), 20));
            e.add(new Excel(DateUtils.format(roleDailyList.get(i).getStatDate(), "yyyy-MM-dd HH:mm:ss"), 20));
            ee.getEll().add(e);
        }
        ee.setFoot("总共：" + roleDailyList.size() + "条记录");
    }

    private String getRoleDailyJson(List<SRoleDaily> roleDailyList) {
        if (CollectionUtils.isEmpty(roleDailyList)) {
            return "";
        }
        EChartUtil eChartUtil = new EChartUtil("", null);
        List<Object> xValue = new ArrayList<Object>();
        Map<String, List<Object>> map = new LinkedHashMap<String, List<Object>>();
        map.put("登录人数", new ArrayList<Object>());

        for (SRoleDaily obj : roleDailyList) {
            xValue.add(DateUtils.format(obj.getStatDate(), "yyyy-MM-dd"));
            map.get("登录人数").add(obj.getRoleLogins());
        }
        return eChartUtil.getLineOrBarOption(map, xValue, false, false, false, EChartUtil.TYPE_LINE);
    }

    public Map<String, Object> playTime(Long appId, Integer clientType, Integer platformId
            , String zoneId, String selectRange) {
        Map<String, Object> result = new HashMap<String, Object>();
        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, appId);
        mb.put(MapBean.CHANNEL_ID, platformId);
        mb.put("zoneId", StringUtils.isBlank(zoneId) ? null : zoneId);

        if (StringUtils.isNotBlank(selectRange)) {
            mb.put("statDate", selectRange);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            mb.put("statDate", DateUtils.format(calendar.getTime(), "yyyy-MM-dd"));
            result.put("selectRange", mb.get("statDate"));
        }
        result.put("data", sPlayTimeDistributeDailyService.playTime(mb));
        return result;
    }

    public Map<String, Object> totalPlayTime(Page<MapBean> page, Long appId, Integer clientType, Integer platformId
            , String zoneId, String selectRange, String roleName, String uid) {
        Map<String, Object> result = new HashMap<String, Object>();
        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, appId);
        mb.put(MapBean.CHANNEL_ID, platformId);
        mb.put("zoneId", StringUtils.isBlank(zoneId) ? null : zoneId);
        mb.put("roleName", StringUtils.isBlank(roleName) ? null : roleName);
        mb.put("uid", StringUtils.isBlank(uid) ? null : uid);

        if (StringUtils.isNotBlank(selectRange)) {
            mb.put("statStartDate", selectRange.split("至")[0]);
            mb.put("statEndDate", selectRange.split("至")[1]);
        } else {
            Calendar calerndar = Calendar.getInstance();
            calerndar.add(Calendar.DATE, -1);
            mb.put("statStartDate", DateUtils.format(calerndar.getTime(), "yyyy-MM-dd"));
            mb.put("statEndDate", DateUtils.format(calerndar.getTime(), "yyyy-MM-dd"));
            result.put("selectRange", mb.get("statStartDate") + " 至 " + mb.get("statEndDate"));
        }
        result.put("data", sRolePlayTimeDailyService.pagePlayTime(page, mb));
        return result;
    }


}
