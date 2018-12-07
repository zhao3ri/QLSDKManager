package com.item.web.action;

import com.item.domain.BChannel;
import com.item.domain.Game;
import com.item.domain.report.LTVGameChannel;
import com.item.service.*;
import com.item.utils.*;
import core.module.orm.MapBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReportLTVAction extends BaseAction {
    private static final long serialVersionUID = 5645405406052360424L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportLTVAction.class);

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

    @Autowired
    private ReportDailyService reportDailyService;
    @Autowired
    private ReportLTVService reportLtvService;
    @Autowired
    private BGameService bGameService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private BGamezoneService gamezoneService;
    @Autowired
    private BChannelService platformService;
    @Resource
    private SysGameManagerService gameManagerService;

    public String ltv() {
        MapBean mb = new MapBean();
        if (StringUtils.isNotBlank(channelName)) {
            mb.put("platformName", channelName);
            BChannel platform = platformService.get(mb);
            channelIds = platform.getId().toString();
        }
        if (StringUtils.isNotBlank(zoneName)) {
            mb.put("zoneName", zoneName);
            mb.put("gameId", gameId);
            zoneIds = gamezoneService.get(mb).getZoneId();
        }

        if (!initData())
            return null;
        logger.info("chanelid=" + channelIds);
//		result = reportLtvService.basic(gameId,clientType,groupType,channelIds,compareChannelIds,zoneIds,compareZoneIds,selectRange,compareSelectRange,1);
        result = reportLtvService.listLtv(gameId, clientType, channelIds, zoneIds, selectRange, 1);
        if (result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        logger.debug(result.toString());
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

    @SuppressWarnings("unchecked")
    public void operateExport() {
        if (!initData())
            return;
        //result = reportDailyService.operate(gameId,clientType,channelIds,zoneIds,selectRange);
        result = reportLtvService.listLtv(gameId, clientType, channelIds, zoneIds, selectRange, 1);
//		List<ReportHistoryDaily> dailies = (List<ReportHistoryDaily>)result.get("data");
        List<LTVGameChannel> ltvDetail = (List<LTVGameChannel>) result.get("data");

        ExcelExport ee = new ExcelExport();
        ee.setHead("LTV报统计报表_" + DateUtils.format(new Date(), "yyyyMMddHHmmss"));
        ee.getEl().add("时间");
        ee.getEl().add("ltv1");
        ee.getEl().add("ltv2");
        ee.getEl().add("ltv3");
        ee.getEl().add("ltv4");
        ee.getEl().add("ltv5");
        ee.getEl().add("ltv6");
        ee.getEl().add("ltv7");
        ee.getEl().add("ltv8");
        ee.getEl().add("ltv9");
        ee.getEl().add("ltv10");
        ee.getEl().add("ltv11");
        ee.getEl().add("ltv12");
        ee.getEl().add("ltv13");
        ee.getEl().add("ltv14");
        ee.getEl().add("ltv15");
        ee.getEl().add("ltv16");
        ee.getEl().add("ltv17");
        ee.getEl().add("ltv18");
        ee.getEl().add("ltv19");
        ee.getEl().add("ltv20");
        ee.getEl().add("ltv21");
        ee.getEl().add("ltv22");
        ee.getEl().add("ltv23");
        ee.getEl().add("ltv24");
        ee.getEl().add("ltv25");
        ee.getEl().add("ltv26");
        ee.getEl().add("ltv27");
        ee.getEl().add("ltv28");
        ee.getEl().add("ltv29");
        ee.getEl().add("ltv30");
        ee.getEl().add("ltv60");
        ee.getEl().add("ltv90");
        for (int i = 0; i < ltvDetail.size(); i++) {
            List<Excel> e = new ArrayList<Excel>();
            int ltv2 = ltvDetail.get(i).getLtv1() + ltvDetail.get(i).getLtv2();
            int ltv3 = ltv2 + ltvDetail.get(i).getLtv3();
            int ltv4 = ltv3 + ltvDetail.get(i).getLtv4();
            int ltv5 = ltv4 + ltvDetail.get(i).getLtv5();
            int ltv6 = ltv5 + ltvDetail.get(i).getLtv6();
            int ltv7 = ltv6 + ltvDetail.get(i).getLtv7();
            int ltv14 = ltv7 + ltvDetail.get(i).getLtv14();
            int ltv30 = ltv14 + ltvDetail.get(i).getLtv30();
            e.add(new Excel(DateUtils.format(ltvDetail.get(i).getStatDate(), "yyyy-MM-dd"), 20));
            e.add(new Excel(ltvDetail.get(i).getLtv1() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv2() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv3() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv4() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv5() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv6() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv7() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv8() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv9() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv10() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv11() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv12() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv13() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv14() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv15() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv16() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv17() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv18() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv20() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv21() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv22() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv23() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv24() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv25() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv26() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv27() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv28() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv29() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv30() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv60() / 100, 20));
            e.add(new Excel(ltvDetail.get(i).getLtv90() / 100, 20));
//            e.add(new Excel(dailies.get(i).getRegUsers(),20));
//            e.add(new Excel(dailies.get(i).getRegDevices(),20));
//            e.add(new Excel(dailies.get(i).getStartTimes(),20));
//            e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getAvgOnlineTime()/3600),20));
//            if (dailies.get(i).getNewDevices() > 0) {
//            	e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getRegDevices() * 100/dailies.get(i).getNewDevices()) + "%",20));
//			}else {
//				e.add(new Excel("0.00%",20));
//			}
//            if (dailies.get(i).getRegDevices() > 0) {
//            	 e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getRoleDevices() * 100/dailies.get(i).getRegDevices()) + "%",20));
//			}else {
//				e.add(new Excel("0.00%",20));
//			}
//
//            e.add(new Excel(dailies.get(i).getActiveUsers(),20));
//
//            if (dailies.get(i).getRoleUsers() > 0) {
//            	if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 1) {
//            		e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getKeepUser1() * 100/dailies.get(i).getRoleUsers()) + "%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//            	if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 2) {
//            		e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getKeepUser3() * 100/dailies.get(i).getRoleUsers()) + "%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//            	if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 3) {
//            		e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getKeepUser4() * 100/dailies.get(i).getRoleUsers()) + "%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//            	if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 4) {
//            		e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getKeepUser5() * 100/dailies.get(i).getRoleUsers()) + "%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//            	if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 5) {
//            		e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getKeepUser6() * 100/dailies.get(i).getRoleUsers()) + "%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//            	if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 6) {
//            		e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getKeepUser7() * 100/dailies.get(i).getRoleUsers()) + "%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//            	if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 13) {
//            		e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getKeepUser14() * 100/dailies.get(i).getRoleUsers()) + "%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//            	if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 29) {
//            		e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getKeepUser30() * 100/dailies.get(i).getRoleUsers()) + "%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//			}else {
//				if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 1) {
//					e.add(new Excel("0.00%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//				if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 2) {
//					e.add(new Excel("0.00%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//				if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 3) {
//					e.add(new Excel("0.00%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//				if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 4) {
//					e.add(new Excel("0.00%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//				if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 5) {
//					e.add(new Excel("0.00%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//				if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 6) {
//					e.add(new Excel("0.00%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//				if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 13) {
//					e.add(new Excel("0.00%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//				if (DateUtils.getIntervalDays(dailies.get(i).getStatDate().getTime(), System.currentTimeMillis()) >= 29) {
//					e.add(new Excel("0.00%",20));
//				}else {
//					e.add(new Excel("--",20));
//				}
//			}
//
//            e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getPayAmount()/100),20));
//            e.add(new Excel(dailies.get(i).getPayTimes(),20));
//            e.add(new Excel(dailies.get(i).getPayUsers(),20));
//            e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getNewUserPayAmount()/100),20));
//            e.add(new Excel(dailies.get(i).getNewUserPays(),20));
//            e.add(new Excel(dailies.get(i).getNewUserPayTimes(),20));
//            if (dailies.get(i).getRoleUsers() > 0) {
//            	e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getNewUserPays() * 100/dailies.get(i).getRoleUsers()) + "%",20));
//                e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getNewUserPayAmount()/dailies.get(i).getRoleUsers()/100),20));
//			}else {
//				e.add(new Excel("0.00%",20));
//				e.add(new Excel("0.00",20));
//			}
//            if (dailies.get(i).getNewUserPays() > 0) {
//            	 e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getNewUserPayAmount()/dailies.get(i).getNewUserPays()/100),20));
//			}else {
//				e.add(new Excel("0.00",20));
//			}
//
//            e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getFirstPayAmount()/100),20));
//            e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getFirstPayUsers()/100),20));
//            if (dailies.get(i).getFirstPayUsers() > 0) {
//            	 e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getFirstPayAmount()/100/dailies.get(i).getFirstPayUsers()),20));
//			}else {
//				e.add(new Excel("0.00",20));
//			}
//
//            e.add(new Excel(dailies.get(i).getTotaldevices(),20));
//            e.add(new Excel(dailies.get(i).getTotalRoleUser(),20));
//            e.add(new Excel(dailies.get(i).getTotalRegUser(),20));
//            e.add(new Excel(dailies.get(i).getTotalActiveUsers(),20));
//            e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getTotalPayAmount()/100),20));
//            e.add(new Excel(dailies.get(i).getTotalPayTimes(),20));
//            e.add(new Excel(dailies.get(i).getTotalPayUsers(),20));
//            if (dailies.get(i).getTotalPayUsers() > 0) {
//            	e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getTotalPayAmount()/100/dailies.get(i).getTotalPayUsers()),20));
//			}else {
//				e.add(new Excel("0.00",20));
//			}
//            if (dailies.get(i).getTotalRegUser() > 0) {
//            	e.add(new Excel(DecimallFormatUtil.format((double)dailies.get(i).getTotalPayAmount()/100/dailies.get(i).getTotalRegUser()),20));
//			}else {
//				e.add(new Excel("0.00",20));
//			}

            ee.getEll().add(e);
        }
        ee.setFoot("总共：" + ltvDetail.size() + "条记录");
        ee.excelExport();
    }

    public String online() {
        if (!initData())
            return null;
        result = reportService.online(gameId, clientType, channelIds
                , compareChannelIds, zoneIds, compareZoneIds, groupType
                , compareGroupType, selectRange, compareSelectRange);
        if (result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString() + " 至 " + result.get("selectRange").toString();
        }
        return "online";
    }
    
    @Override
    protected boolean initData() {
        boolean success = super.initData();
        if (success) {
            allGames = getCurrentIdentityGames();
            if (null == gameId) {
                gameId = getFirstGameId();
            }
        }
        return success;
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
}
