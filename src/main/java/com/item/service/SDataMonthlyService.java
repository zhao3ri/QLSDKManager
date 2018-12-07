package com.item.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.item.domain.BChannel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.item.dao.SDataMonthlyDao;
import com.item.domain.Game;
import com.item.domain.Gamezone;
import com.item.domain.SDataMonthly;
import com.item.utils.DateUtils;
import com.item.utils.DecimallFormatUtil;
import com.item.utils.EChartUtil;
import com.item.utils.Excel;
import com.item.utils.ExcelExport;

import core.module.orm.MapBean;

@Service
@Transactional
public class SDataMonthlyService {
	
	@Autowired
	private SDataMonthlyDao dao;
	
	@Resource
	private BGameService gameService;
	
	@Resource
	private BChannelService platformService;
	
	@Resource
	private BGamezoneService gamezoneService;
	
	public Map<String, Object> dataMonthly(Long appId, Integer clientType,
			Integer platformId, String zoneId,
			String yearMonthStr, String yearMonthStr2) {
		Map<String, Object> result = new HashMap<String, Object>();
		MapBean mb = new MapBean();
		mb.put(MapBean.GAME_ID, appId);
		mb.put(MapBean.CLIENT_TYPE, clientType);
		mb.put(MapBean.CHANNEL_ID, platformId);
		mb.put("zoneId", StringUtils.isBlank(zoneId) ? null : zoneId);
		
		mb.put("statStartDate", yearMonthStr.replace("-", ""));
		mb.put("statEndDate", yearMonthStr2.replace("-", ""));	
		
		List<SDataMonthly> dataMonthlyList = dao.find("SDataMonthly.list", mb);
		result.put("optionJson", getDataMonthlyOptionJson(dataMonthlyList));
		result.put("optionJson2", getDataMonthlyOptionJson2(dataMonthlyList));

		result.put("data", dataMonthlyList);
		return result;
	}
	
	private <T> String getDataMonthlyOptionJson(List<T> dataMonthlyList){
		if (CollectionUtils.isEmpty(dataMonthlyList)) {
			return "";
		}
		
		EChartUtil eChartUtil = new EChartUtil("",null);
		List<Object> xValue = new ArrayList<Object>();
		Map<String, List<Object>> map = new LinkedHashMap<String, List<Object>>();
		map.put("注册人数", new ArrayList<Object>());
		map.put("活跃用户数", new ArrayList<Object>());
		
		for (T t : dataMonthlyList) {
				SDataMonthly dataMonthly = (SDataMonthly)t;
				xValue.add((dataMonthly.getYearMonth()+"").substring(4, 6)+"月");
				map.get("注册人数").add(dataMonthly.getRegUsers());
				map.get("活跃用户数").add(dataMonthly.getMonthLoginRoles());
		}
		
		List<Object> xValue2 = new ArrayList<Object>();
		Map<String, List<Object>> map2 = new LinkedHashMap<String, List<Object>>();
		map2.put("转化率", new ArrayList<Object>());
		map2.put("活跃率", new ArrayList<Object>());
		
		for (T t : dataMonthlyList) {
				SDataMonthly dataMonthly = (SDataMonthly)t;
				xValue2.add((dataMonthly.getYearMonth()+"").substring(4, 6)+"月");
				map2.get("转化率").add(new DecimalFormat("0.0000").format(dataMonthly.getConversionRate()==null?0:dataMonthly.getConversionRate()));
				if (dataMonthly.getRegUsers() == 0){
					map2.get("活跃率").add(0);
				}else {
					map2.get("活跃率").add(new DecimalFormat("0.0000").format(dataMonthly.getMonthLoginRoles()*1.0/dataMonthly.getRegUsers()));
				}
		}
				
		return eChartUtil.getLineAndBarOption(map, xValue, false, false, false, EChartUtil.TYPE_BAR, map2,xValue2,false,false,false, EChartUtil.TYPE_LINE);
	}
	
	private <T> String getDataMonthlyOptionJson2(List<T> dataMonthlyList){
		if (CollectionUtils.isEmpty(dataMonthlyList)) {
			return "";
		}
		
		EChartUtil eChartUtil = new EChartUtil("",null);
		List<Object> xValue = new ArrayList<Object>();
		Map<String, List<Object>> map = new LinkedHashMap<String, List<Object>>();
		map.put("付费用户数", new ArrayList<Object>());
		map.put("新增付费用户数", new ArrayList<Object>());
		
		for (T t : dataMonthlyList) {
				SDataMonthly dataMonthly = (SDataMonthly)t;
				xValue.add((dataMonthly.getYearMonth()+"").substring(4, 6)+"月");
				map.get("付费用户数").add(dataMonthly.getPayUsers());
				map.get("新增付费用户数").add(dataMonthly.getIncPayUsers());
		}
		
		List<Object> xValue2 = new ArrayList<Object>();
		Map<String, List<Object>> map2 = new LinkedHashMap<String, List<Object>>();
		map2.put("总充值金额", new ArrayList<Object>());
		
		for (T t : dataMonthlyList) {
				SDataMonthly dataMonthly = (SDataMonthly)t;
				xValue2.add((dataMonthly.getYearMonth()+"").substring(4, 6)+"月");
				map2.get("总充值金额").add(DecimallFormatUtil.format((double)dataMonthly.getPayAmount()/100));
		}
				
		return eChartUtil.getLineAndBarOption(map, xValue, false, false, false, EChartUtil.TYPE_BAR, map2,xValue2,false,false,false, EChartUtil.TYPE_LINE);
	}
	
	public void excelExportMonthly(ExcelExport ee,Long appId, Integer clientType, Integer platformId
			, String zoneId, String yearMonthStr, String yearMonthStr2){
		ee.setHead("每月数据汇总统计报表_"+DateUtils.format(new Date(),"yyyyMMddHHmmss"));
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
		if (StringUtils.isNotBlank(yearMonthStr)){
			mb.put("statStartDate", yearMonthStr.replace("-", ""));
		}
		if (StringUtils.isNotBlank(yearMonthStr2)){
			mb.put("statEndDate", yearMonthStr2.replace("-", ""));
		}	
		
		List<SDataMonthly> dataMonthlyList = dao.find("SDataMonthly.list", mb);
        for(int i=0; i<dataMonthlyList.size(); i++){
            BChannel channel = platformService.getChannelById(dataMonthlyList.get(i).getChannelId()+0L);
            MapBean mb2 = new MapBean();
    		mb2.put(MapBean.GAME_ID, appId);
    		mb2.put("zoneId", dataMonthlyList.get(i).getZoneId()+"");
            Gamezone gamezone = gamezoneService.get(mb2);
        	List<Excel> e = new ArrayList<Excel>();
        	e.add(new Excel(game.getGameName(),20));
        	e.add(new Excel(appId,20));
        	e.add(new Excel(dataMonthlyList.get(i).getClientType()==1?"android":"ios", 20));
        	e.add(new Excel(channel.getChannelName(),20));
        	e.add(new Excel(gamezone==null?"":gamezone.getZoneName(),20));
        	String date = dataMonthlyList.get(i).getYearMonth()+"";
        	e.add(new Excel(date.substring(0, 4)+"-"+date.substring(4, 6), 20));
        	e.add(new Excel(dataMonthlyList.get(i).getRegUsers(), 20));
        	if (dataMonthlyList.get(i).getConversionRate() == null){
        		e.add(new Excel("0.00%",20));
        	}else {
        		e.add(new Excel(DecimallFormatUtil.format((double)dataMonthlyList.get(i).getConversionRate()*100.0)+"%", 20));
        	}
        	e.add(new Excel(dataMonthlyList.get(i).getMonthLoginRoles(), 20));
        	if (dataMonthlyList.get(i).getRegUsers() == 0){
        		e.add(new Excel("0.00%",20));
        	}else {
        		e.add(new Excel(DecimallFormatUtil.format((double)dataMonthlyList.get(i).getMonthLoginRoles()*1.0/dataMonthlyList.get(i).getRegUsers()*100)+"%", 20));
        	}
        	e.add(new Excel(dataMonthlyList.get(i).getPayUsers(), 20));
        	e.add(new Excel(dataMonthlyList.get(i).getIncPayUsers(), 20));
        	e.add(new Excel(dataMonthlyList.get(i).getPayAmount()/100.0, 20));
        	e.add(new Excel(dataMonthlyList.get(i).getIncPayAmount()/100.0, 20));
        	if (dataMonthlyList.get(i).getMonthLoginRoles() == 0){
        		e.add(new Excel("0.00%",20));
        	}else {
        		e.add(new Excel(DecimallFormatUtil.format((double)dataMonthlyList.get(i).getPayUsers()*1.0/dataMonthlyList.get(i).getMonthLoginRoles()*100)+"%", 20));
        	}
        	if (dataMonthlyList.get(i).getPayUsers() == 0){
        		e.add(new Excel(0,20));
        	}else {
        		e.add(new Excel(DecimallFormatUtil.format((double)dataMonthlyList.get(i).getPayAmount()/100.0/dataMonthlyList.get(i).getPayUsers()), 20));
        	}
        	if (dataMonthlyList.get(i).getRegUsers() == 0){
        		e.add(new Excel(0,20));
        	}else {
        		e.add(new Excel(DecimallFormatUtil.format((double)dataMonthlyList.get(i).getPayAmount()/100.0/dataMonthlyList.get(i).getRegUsers()), 20));
        	}
        	ee.getEll().add(e);
        }
		ee.setFoot("总共："+dataMonthlyList.size()+"条记录");
	}
}
