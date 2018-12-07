package com.item.web.action;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.item.domain.Game;
import com.item.domain.SRechargeDistributeDaily;
import com.item.domain.SRechargeHourly;
import com.item.domain.SRoleDaily;
import com.item.service.BGameService;
import com.item.service.SRechargeDistributeDailyService;
import com.item.service.SRechargeHourlyService;
import com.item.service.SRoleDailyService;
import com.item.service.SysGameManagerService;
import com.item.utils.DateUtils;
import com.item.utils.ExcelExport;

import core.module.orm.MapBean;
import core.module.orm.Page;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.item.service.RoleReportService;

public class RoleReportAction extends BaseAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleReportAction.class);

    private static final long serialVersionUID = 5551011746476847421L;
    @Resource
    private SysGameManagerService gameManagerService;
    @Resource
    private BGameService gameService;
    @Resource
    private SRechargeDistributeDailyService rechargeDistributeDailyService;
    @Resource
    private SRechargeHourlyService rechargeHourlyService;
    @Autowired
    private SRoleDailyService sRoleDailyService;
    @Autowired
    private RoleReportService roleReportService;
    @Autowired
    private BGameService bGameService;

    private List<Game> games;
    private List<SRechargeHourly> rechargeHourlies;
    private Long gameId;
    private Integer channelId;
    private String zoneId;
    private String uid;
    private String roleName;
    private List<SRechargeDistributeDaily> rechargeDistributeDailies;
    private String selectRange;
    private Map<String, Object> result;
    private Integer clientType;
    private Page<MapBean> page = new Page<MapBean>(10);

    public String realTimeLogin() {
        if (!initData()) {
            return null;
        }
        result = roleReportService.realTimeLogin(gameId, clientType, channelId, zoneId, selectRange);
        if (!CollectionUtils.isEmpty(result) && result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "realTimeLogin";
    }

    public String dailyLogin() {
        if (!initData()) {
            return null;
        }
        result = roleReportService.dailyLogin(gameId, clientType, channelId, zoneId, selectRange);
        if (!CollectionUtils.isEmpty(result) && result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "dailyLogin";
    }

    public String online() {
        if (!initData()) {
            return null;
        }
        result = roleReportService.online(gameId, clientType, channelId, zoneId, selectRange);
        if (!CollectionUtils.isEmpty(result) && result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "online";
    }

    public void excelExportOnline() {
        try {
            ExcelExport ee = new ExcelExport();
            roleReportService.excelExportOnline(ee, gameId, clientType, channelId, zoneId, selectRange);
            ee.excelExport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String onlineDaily() {
        if (!initData()) {
            return null;
        }
        result = roleReportService.onlineDaily(gameId, clientType, channelId, zoneId, selectRange);
        if (!CollectionUtils.isEmpty(result) && result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "onlineDaily";
    }

    public void excelExportOnlineDaily() {
        try {
            ExcelExport ee = new ExcelExport();
            roleReportService.excelExportOnlineDaily(ee, gameId, clientType, channelId, zoneId, selectRange);
            ee.excelExport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String daily() {
        if (!initData()) {
            return null;
        }
        result = roleReportService.daily(gameId, clientType, channelId, zoneId, selectRange);
        if (!CollectionUtils.isEmpty(result) && result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "daily";
    }

    public void excelExportDaily() {
        try {
            ExcelExport ee = new ExcelExport();
            roleReportService.excelExportDaily(ee, gameId, clientType, channelId, zoneId, selectRange);
            ee.excelExport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String playTime() {
        if (!initData()) {
            return null;
        }
        result = roleReportService.playTime(gameId, clientType, channelId, zoneId, selectRange);
        if (!CollectionUtils.isEmpty(result) && result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "playTime";
    }

    public String totalPlayTime() {
        if (!initData()) {
            return null;
        }
        result = roleReportService.totalPlayTime(page, gameId, clientType, channelId, zoneId, selectRange, roleName, uid);
        if (!CollectionUtils.isEmpty(result) && result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "totalPlayTime";
    }

    public String rechargeRegion() {
        if (!initData()) {
            return null;
        }
        if (StringUtils.isBlank(selectRange)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            selectRange = DateUtils.format(calendar.getTime(), "yyyy-MM-dd") + " 至 " + DateUtils.format(calendar.getTime(), "yyyy-MM-dd");
        }
        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, gameId);
        mb.put(MapBean.CHANNEL_ID, channelId);
        mb.put("zoneId", zoneId);
        mb.put("startDate", selectRange.split("至")[0].trim());
        mb.put("endDate", selectRange.split("至")[1].trim());
        rechargeDistributeDailies = rechargeDistributeDailyService.list(mb);
        return "rechargeRegion";
    }

    public String activeRecharge() {
        if (!initData()) {
            return null;
        }

        result = roleReportService.dailyLogin(gameId, clientType, channelId, zoneId, selectRange);
        if (!CollectionUtils.isEmpty(result) && result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "activeRecharge";
    }

    public String rechargeRealtime() {
        if (!initData()) {
            return null;
        }
        if (StringUtils.isBlank(selectRange))
            selectRange = DateUtils.format(new Date(), "yyyy-MM-dd");

        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, gameId);
        mb.put(MapBean.CHANNEL_ID, channelId);
        mb.put("zoneId", zoneId);
        mb.put("statDate", selectRange);
        rechargeHourlies = rechargeHourlyService.list(mb);
        return "rechargeRealtime";
    }

    public String rechargeDaily() {
        if (!initData()) {
            return null;
        }

        if (StringUtils.isBlank(selectRange)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            selectRange = DateUtils.format(calendar.getTime(), "yyyy-MM-dd") + " 至 " + DateUtils.format(calendar.getTime(), "yyyy-MM-dd");
        }

        MapBean mb = new MapBean();
        mb.put(MapBean.GAME_ID, gameId);
        mb.put(MapBean.CHANNEL_ID, channelId);
        mb.put(MapBean.ZONE_ID, zoneId);
        mb.put("statStartDate", selectRange.split("至")[0].trim());
        mb.put("statEndDate", selectRange.split("至")[1].trim());
        List<SRoleDaily> roleDailies = sRoleDailyService.listDailyLogin(mb);

        mb.remove("statStartDate");
        for (SRoleDaily sRoleDaily : roleDailies) {
            mb.put("statEndDate", DateUtils.format(sRoleDaily.getStatDate(), "yyyy-MM-dd"));
            sRoleDaily.setCurrentMaxAmount(sRoleDailyService.getCurrentMaxAmount(mb));
        }
        result = new HashMap<String, Object>();
        result.put("data", roleDailies);
        return "rechargeDaily";
    }

    public String roleActive() {
        if (!initData()) {
            return null;
        }

        result = roleReportService.dailyLogin(gameId, clientType, channelId, zoneId, selectRange);
        if (!CollectionUtils.isEmpty(result) && result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "roleActive";
    }

    public String keepRoles() {
        if (!initData())
            return null;

        result = roleReportService.dailyLogin(gameId, clientType, channelId, zoneId, selectRange);
        if (!CollectionUtils.isEmpty(result) && result.containsKey("selectRange"))
            selectRange = result.get("selectRange").toString();
        return "keepRoles";
    }

    public String lossRoles() {
        if (!initData())
            return null;

        result = roleReportService.dailyLogin(gameId, clientType, channelId, zoneId, selectRange);
        if (!CollectionUtils.isEmpty(result) && result.containsKey("selectRange"))
            selectRange = result.get("selectRange").toString();
        return "lossRoles";
    }

    @Override
    protected boolean initData() {
        boolean success = super.initData();
        if (success) {
            games = getCurrentIdentityGames();
            if (null == gameId) {
                gameId = getFirstGameId();
            }
        }
        return success;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public List<SRechargeDistributeDaily> getRechargeDistributeDailies() {
        return rechargeDistributeDailies;
    }

    public void setRechargeDistributeDailies(List<SRechargeDistributeDaily> rechargeDistributeDailies) {
        this.rechargeDistributeDailies = rechargeDistributeDailies;
    }

    public String getSelectRange() {
        return selectRange;
    }

    public void setSelectRange(String selectRange) {
        this.selectRange = selectRange;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public RoleReportService getRoleReportService() {
        return roleReportService;
    }

    public void setRoleReportService(RoleReportService roleReportService) {
        this.roleReportService = roleReportService;
    }

    public BGameService getbGameService() {
        return bGameService;
    }

    public void setbGameService(BGameService bGameService) {
        this.bGameService = bGameService;
    }

    public List<SRechargeHourly> getRechargeHourlies() {
        return rechargeHourlies;
    }

    public void setRechargeHourlies(List<SRechargeHourly> rechargeHourlies) {
        this.rechargeHourlies = rechargeHourlies;
    }

    public Page<MapBean> getPage() {
        return page;
    }

    public void setPage(Page<MapBean> page) {
        this.page = page;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
