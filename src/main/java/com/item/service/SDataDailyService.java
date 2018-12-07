package com.item.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.item.dao.SDataDailyDao;
import com.item.domain.BChannel;
import com.item.domain.Game;
import com.item.domain.Gamezone;
import com.item.domain.SDataDaily;
import com.item.utils.DateUtils;
import com.item.utils.DecimallFormatUtil;
import com.item.utils.EChartUtil;
import com.item.utils.Excel;
import com.item.utils.ExcelExport;

import core.module.orm.MapBean;

@Service
@Transactional
public class SDataDailyService {
	
	@Autowired
	private SDataDailyDao dao;
	
	@Resource
	private BGameService gameService;
	
	@Resource
	private BChannelService platformService;
	
	@Resource
	private BGamezoneService gamezoneService;
	
	public Map<String, Object> dataDaily(Long appId, Integer clientType,
			Integer platformId, String zoneId,
			String selectRange) {
		Map<String, Object> result = new HashMap<String, Object>();
		MapBean mb = new MapBean();
		mb.put(MapBean.GAME_ID, appId);
		mb.put(MapBean.CLIENT_TYPE, clientType);
		mb.put(MapBean.CHANNEL_ID, platformId);
		mb.put("zoneId", StringUtils.isBlank(zoneId) ? null : zoneId);
		
		mb.put("statStartDate", selectRange.split("至")[0]);
		mb.put("statEndDate", selectRange.split("至")[1]);
		
		List<SDataDaily> dataDailyList = dao.find("SDataDaily.list", mb);
		result.put("optionJson", getDataDailyOptionJson(dataDailyList));
		result.put("optionJson2", getDataDailyOptionJson2(dataDailyList));

		result.put("data", dataDailyList);
		return result;
	}
	
	private <T> String getDataDailyOptionJson(List<T> dataDailyList){
		if (CollectionUtils.isEmpty(dataDailyList)) {
			return "";
		}
		
		EChartUtil eChartUtil = new EChartUtil("",null);
		List<Object> xValue = new ArrayList<Object>();
		Map<String, List<Object>> map = new LinkedHashMap<String, List<Object>>();
		map.put("次日留存率", new ArrayList<Object>());
		map.put("三日留存率", new ArrayList<Object>());
		map.put("七日留存率", new ArrayList<Object>());
		map.put("十五日留存率", new ArrayList<Object>());
		map.put("三十日留存率", new ArrayList<Object>());
		
		for (T t : dataDailyList) {
				SDataDaily dataDaily = (SDataDaily)t;
				xValue.add(DateUtils.format(dataDaily.getStatDate(),"MM-dd"));
				map.get("次日留存率").add(dataDaily.getRoleEstablishs()==0?0:DecimallFormatUtil.format(dataDaily.getKeepRole1()*1.0/dataDaily.getRoleEstablishs()));
				map.get("三日留存率").add(dataDaily.getRoleEstablishs()==0?0:DecimallFormatUtil.format(dataDaily.getKeepRole3()*1.0/dataDaily.getRoleEstablishs()));
				map.get("七日留存率").add(dataDaily.getRoleEstablishs()==0?0:DecimallFormatUtil.format(dataDaily.getKeepRole7()*1.0/dataDaily.getRoleEstablishs()));
				map.get("十五日留存率").add(dataDaily.getRoleEstablishs()==0?0:DecimallFormatUtil.format(dataDaily.getKeepRole15()*1.0/dataDaily.getRoleEstablishs()));
				map.get("三十日留存率").add(dataDaily.getRoleEstablishs()==0?0:DecimallFormatUtil.format(dataDaily.getKeepRole30()*1.0/dataDaily.getRoleEstablishs()));
		}
		return eChartUtil.getLineOrBarOption(map, xValue, false, false, false, EChartUtil.TYPE_BAR);
	}
	
	private <T> String getDataDailyOptionJson2(List<T> dataDailyList){
		if (CollectionUtils.isEmpty(dataDailyList)) {
			return "";
		}
		
		EChartUtil eChartUtil = new EChartUtil("",null);
		List<Object> xValue = new ArrayList<Object>();
		Map<String, List<Object>> map = new LinkedHashMap<String, List<Object>>();
		map.put("日新增充值用户数", new ArrayList<Object>());
		map.put("日充值用户数", new ArrayList<Object>());
		
		for (T t : dataDailyList) {
				SDataDaily dataDaily = (SDataDaily)t;
				xValue.add(DateUtils.format(dataDaily.getStatDate(),"MM-dd"));
				map.get("日新增充值用户数").add(dataDaily.getIncPayUsers());
				map.get("日充值用户数").add(dataDaily.getPayUsers());
		}
		
		List<Object> xValue2 = new ArrayList<Object>();
		Map<String, List<Object>> map2 = new LinkedHashMap<String, List<Object>>();
		map2.put("日充值金额", new ArrayList<Object>());
		
		for (T t : dataDailyList) {
				SDataDaily dataDaily = (SDataDaily)t;
				xValue2.add(DateUtils.format(dataDaily.getStatDate(),"MM-dd"));
				map2.get("日充值金额").add(DecimallFormatUtil.format((double)dataDaily.getPayAmount()/100));
		}
				
		return eChartUtil.getLineAndBarOption(map, xValue, false, false, false, EChartUtil.TYPE_BAR, map2,xValue2,false,false,false, EChartUtil.TYPE_LINE);
	}
	
	public void excelExportDaily(ExcelExport ee,Long appId, Integer clientType, Integer platformId
			, String zoneId, String selectRange){
		ee.setHead("每日数据汇总统计报表_"+DateUtils.format(new Date(),"yyyyMMddHHmmss"));
        ee.getEl().add("游戏名称");
        ee.getEl().add("游戏编号");
        ee.getEl().add("客户端类型");
        ee.getEl().add("渠道名称");
        ee.getEl().add("区服名称");
        ee.getEl().add("日期");
        ee.getEl().add("注册用户");
        ee.getEl().add("转换率");
        ee.getEl().add("活跃用户数");
        ee.getEl().add("活跃率");
        ee.getEl().add("次日留存数");
        ee.getEl().add("次日留存率");
        ee.getEl().add("三日留存数");
        ee.getEl().add("三日留存率");
        ee.getEl().add("七日留存数");
        ee.getEl().add("七日留存率");
        ee.getEl().add("十五日留存数");
        ee.getEl().add("十五日留存率");
        ee.getEl().add("三十日留存数");
        ee.getEl().add("三十日留存率");
        ee.getEl().add("日付费用户数");
        ee.getEl().add("新增付费用户数");
        ee.getEl().add("日充值金额");
        ee.getEl().add("新增充值金额");
        ee.getEl().add("活跃付费率");
        ee.getEl().add("arpu");
        ee.getEl().add("注册arpu");
        
        Game game = gameService.getGameById(appId);
        MapBean mb = new MapBean();
		mb.put(MapBean.GAME_ID, appId);
		mb.put(MapBean.CLIENT_TYPE, clientType);
		mb.put(MapBean.CHANNEL_ID, platformId);
		mb.put("zoneId", StringUtils.isBlank(zoneId) ? null : zoneId);
		if (StringUtils.isNotBlank(selectRange)){
			mb.put("statStartDate", selectRange.split("至")[0]);
			mb.put("statEndDate", selectRange.split("至")[1]);
		}
		if (StringUtils.isBlank(selectRange)){
			mb.put("statDate", DateUtils.format(new Date()));
		}
		
		List<SDataDaily> dataDailyList = dao.find("SDataDaily.list", mb);
        for(int i=0; i<dataDailyList.size(); i++){
            BChannel platform = platformService.getChannelById(dataDailyList.get(i).getChannelId()+0L);
            MapBean mb2 = new MapBean();
    		mb2.put(MapBean.GAME_ID, appId);
    		mb2.put("zoneId", dataDailyList.get(i).getZoneId()+"");
            Gamezone gamezone = gamezoneService.get(mb2);
        	List<Excel> e = new ArrayList<Excel>();
        	e.add(new Excel(game.getGameName(),20));
        	e.add(new Excel(appId,20));
        	e.add(new Excel(dataDailyList.get(i).getClientType()==1?"android":"ios", 20));
        	e.add(new Excel(platform.getChannelName(),20));
        	e.add(new Excel(gamezone==null?"":gamezone.getZoneName(),20));
        	e.add(new Excel(DateUtils.format(dataDailyList.get(i).getStatDate(), "yyyy-MM-dd"), 20));
        	e.add(new Excel(dataDailyList.get(i).getRegUsers(), 20));
        	if (dataDailyList.get(i).getConversionRate() == null){
        		e.add(new Excel(0, 20));
        	}else {
        		e.add(new Excel((int)(dataDailyList.get(i).getConversionRate()*100)+"%", 20));
        	}
        	e.add(new Excel(dataDailyList.get(i).getActiveUsers(), 20));
        	if (dataDailyList.get(i).getRegUsers() == 0){
        		e.add(new Excel(0, 20));
        	}else {
        		e.add(new Excel(DecimallFormatUtil.format((double)dataDailyList.get(i).getActiveUsers()*1.0/dataDailyList.get(i).getRegUsers()*100)+"%", 20));
        	}
        	e.add(new Excel(dataDailyList.get(i).getKeepRole1(), 20));
        	e.add(new Excel((dataDailyList.get(i).getRoleEstablishs()==0?0:DecimallFormatUtil.format(dataDailyList.get(i).getKeepRole1()*100.0/dataDailyList.get(i).getRoleEstablishs()))+"%", 20));
        	e.add(new Excel(dataDailyList.get(i).getKeepRole3(), 20));
        	e.add(new Excel((dataDailyList.get(i).getRoleEstablishs()==0?0:DecimallFormatUtil.format(dataDailyList.get(i).getKeepRole3()*100.0/dataDailyList.get(i).getRoleEstablishs()))+"%", 20));
        	e.add(new Excel(dataDailyList.get(i).getKeepRole7(), 20));
        	e.add(new Excel((dataDailyList.get(i).getRoleEstablishs()==0?0:DecimallFormatUtil.format(dataDailyList.get(i).getKeepRole7()*100.0/dataDailyList.get(i).getRoleEstablishs()))+"%", 20));
        	e.add(new Excel(dataDailyList.get(i).getKeepRole15(), 20));
        	e.add(new Excel((dataDailyList.get(i).getRoleEstablishs()==0?0:DecimallFormatUtil.format(dataDailyList.get(i).getKeepRole15()*100.0/dataDailyList.get(i).getRoleEstablishs()))+"%", 20));
        	e.add(new Excel(dataDailyList.get(i).getKeepRole30(), 20));
        	e.add(new Excel((dataDailyList.get(i).getRoleEstablishs()==0?0:DecimallFormatUtil.format(dataDailyList.get(i).getKeepRole30()*100.0/dataDailyList.get(i).getRoleEstablishs()))+"%", 20));
        	e.add(new Excel(dataDailyList.get(i).getPayUsers(), 20));
        	e.add(new Excel(dataDailyList.get(i).getIncPayUsers(), 20));
        	e.add(new Excel(dataDailyList.get(i).getPayAmount()/100.0, 20));
        	e.add(new Excel(dataDailyList.get(i).getIncPayAmount()/100.0, 20));
        	if (dataDailyList.get(i).getActiveUsers() == 0){
        		e.add(new Excel("0.00%", 20));
        	}else {
        		e.add(new Excel(DecimallFormatUtil.format((double)dataDailyList.get(i).getPayUsers()*1.0/dataDailyList.get(i).getActiveUsers())+"%", 20));
        	}
        	if (dataDailyList.get(i).getPayUsers() == 0){
        		e.add(new Excel(0, 20));
        	}else {
        		e.add(new Excel(DecimallFormatUtil.format((double)dataDailyList.get(i).getPayAmount()/100.0/dataDailyList.get(i).getPayUsers()), 20));
        	}
        	if (dataDailyList.get(i).getRegUsers() == 0){
        		e.add(new Excel(0, 20));
        	}else{
        		e.add(new Excel(DecimallFormatUtil.format((double)dataDailyList.get(i).getPayAmount()/100.0/dataDailyList.get(i).getRegUsers()), 20));
        	}
        	ee.getEll().add(e);
        }
		ee.setFoot("总共："+dataDailyList.size()+"条记录");
	}

}
