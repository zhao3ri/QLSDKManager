package com.item.web.action;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.item.domain.Game;
import com.item.service.BGameService;
import com.item.service.SDataDailyService;
import com.item.service.SDataMonthlyService;
import com.item.utils.DateUtils;
import com.item.utils.ExcelExport;

public class DataReportAction extends BaseAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataReportAction.class);

    private static final long serialVersionUID = 147087466892521642L;

    private List<Game> games;
    private Long gameId;
    private Integer clientType;
    private Integer channelId;
    private String zoneId;
    private String selectRange;
    private String optionJson;
    private Map<String, Object> result;
    private String zoneName;
    private String yearMonthStr;
    private String yearMonthStr2;

    @Resource
    private BGameService gameService;

    @Resource
    private SDataDailyService dataDailyService;

    @Resource
    private SDataMonthlyService dataMonthlyService;

    public String daily() {
        if (!initData()) {
            return null;
        }
        if (StringUtils.isBlank(selectRange)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            selectRange = DateUtils.format(calendar.getTime(), "yyyy-MM-dd") + " 至 " + DateUtils.format(calendar.getTime(), "yyyy-MM-dd");
        }
        result = dataDailyService.dataDaily(gameId, clientType, channelId, zoneId, selectRange);
        if (!CollectionUtils.isEmpty(result) && result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "daily";
    }

    public String monthly() {
        if (!initData()) {
            return null;
        }
        if (StringUtils.isBlank(yearMonthStr)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1);
            yearMonthStr = DateUtils.format(calendar.getTime(), "yyyy-MM");
        }
        if (StringUtils.isBlank(yearMonthStr2)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1);
            yearMonthStr2 = DateUtils.format(calendar.getTime(), "yyyy-MM");
        }
        if (yearMonthStr.compareTo(yearMonthStr2) > 0) {
            String temp = yearMonthStr;
            this.setYearMonthStr(yearMonthStr2);
            this.setYearMonthStr2(temp);
        }
        //超过12个月则取前12个月
        String y1 = yearMonthStr.replace("-", "");
        String y2 = yearMonthStr2.replace("-", "");
        Integer year1 = Integer.parseInt(y1);
        Integer year2 = Integer.parseInt(y2);
        Integer year3 = year2 - 100; //减去一年
        if (year3 > year1) {
            this.setYearMonthStr(year3 / 100 + "-" + (year3 % 100 < 10 ? "0" + year3 % 100 : year3 % 100));
        }

        result = dataMonthlyService.dataMonthly(gameId, clientType, channelId, zoneId, yearMonthStr, yearMonthStr2);
        if (!CollectionUtils.isEmpty(result) && result.containsKey("selectRange")) {
            selectRange = result.get("selectRange").toString();
        }
        return "monthly";
    }

    public void excelExportDaily() {
        try {
            ExcelExport ee = new ExcelExport();
            dataDailyService.excelExportDaily(ee, gameId, clientType, channelId, zoneId, selectRange);
            ee.excelExport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excelExportMonthly() {
        try {
            if (StringUtils.isBlank(yearMonthStr)) {
                Calendar calendar = Calendar.getInstance();
                yearMonthStr = DateUtils.format(calendar.getTime(), "yyyy-MM");
            }
            if (StringUtils.isBlank(yearMonthStr2)) {
                Calendar calendar = Calendar.getInstance();
                yearMonthStr2 = DateUtils.format(calendar.getTime(), "yyyy-MM");
            }
            ExcelExport ee = new ExcelExport();
            dataMonthlyService.excelExportMonthly(ee, gameId, clientType, channelId, zoneId, yearMonthStr, yearMonthStr2);
            ee.excelExport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean initData() {
        boolean success = super.initData();
        if (success) {
            games = getCurrentIdentityGames();
            gameId = getFirstGameId();
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

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
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

    public String getSelectRange() {
        return selectRange;
    }

    public String getYearMonthStr() {
        return yearMonthStr;
    }

    public void setYearMonthStr(String yearMonthStr) {
        this.yearMonthStr = yearMonthStr;
    }

    public String getYearMonthStr2() {
        return yearMonthStr2;
    }

    public void setYearMonthStr2(String yearMonthStr2) {
        this.yearMonthStr2 = yearMonthStr2;
    }

    public void setSelectRange(String selectRange) {
        this.selectRange = selectRange;
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

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

}
