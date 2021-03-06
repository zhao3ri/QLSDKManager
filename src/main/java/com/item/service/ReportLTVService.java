package com.item.service;

import com.item.dao.ReportDailyDao;
import com.item.dao.ReportHistoryDailyDao;
import com.item.dao.ReportLTVDao;
import com.item.domain.BPlatform;
import com.item.domain.Game;
import com.item.domain.Gamezone;
import com.item.domain.report.LTVGamePlatform;
import com.item.domain.report.LTVGamePlatform;
import com.item.domain.report.ReportDaily;
import com.item.domain.report.ReportHistoryDaily;
import com.item.utils.DateUtils;
import com.item.utils.DecimallFormatUtil;
import com.item.utils.EChartUtil;
import core.module.orm.MapBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class ReportLTVService {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ReportLTVDao reportLtvDao;
	@Autowired
	private ReportDailyService reportDailyService;
	@Autowired
	private ReportHistoryDailyDao reportHistoryDailyDao;
	@Autowired
	private BGamezoneService bGamezoneService;
	@Autowired
	private BPlatformService bPlatformService;
	@Autowired
	private BGameService bGameService;
	
	public List<LTVGamePlatform> list(MapBean mb){
		if ("game".equals(mb.get("dimension"))) {
//			return reportDailyDao.find("LTVGamePlatform.listGame", mb);
		}else if ("platform".equals(mb.get("dimension"))) {
//			return reportDailyDao.find("LTVGamePlatform.listPlatform", mb);
		}else if ("zone".equals(mb.get("dimension"))) {
//			return reportDailyDao.find("LTVGamePlatform.listZone", mb);
		}else {
			return null;
		}
		return null;
	}

	public Map<String, Object> listLtv(Long appId, Integer clientType,  String channelIds, String zoneIds, String selectRange,  Integer type){
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("###############1"+channelIds);
		MapBean mb = new MapBean();
		mb.put("appId", appId);
		mb.put("clientType", clientType);
		mb.put("channelIds", StringUtils.isBlank(channelIds) ? null : StringUtils.split(channelIds,","));
		mb.put("zoneIds", StringUtils.isBlank(zoneIds) ? null : StringUtils.split(zoneIds,","));
		List<BPlatform> platforms = bPlatformService.getByIds(channelIds);
		List<Gamezone> gamezones = bGamezoneService.getByIds(appId,zoneIds);
		result.put("platforms", platforms);
		result.put("gamezones", gamezones);
		if (StringUtils.isBlank(channelIds) && StringUtils.isBlank(zoneIds)) {
			mb.put("dimension", "game");
		}else if (StringUtils.isBlank(zoneIds)) {
			mb.put("dimension", "platform");
		}else {
			mb.put("dimension", "zone");
		}
		if (StringUtils.isNotBlank(selectRange)) {
			mb.put("statStartDate", selectRange.split("至")[0]);
			mb.put("statEndDate", selectRange.split("至")[1]);
		}else {
			Calendar calendar=Calendar.getInstance();
			//by zhangxibin edit 将时间间隔设置成5天 time 20160503
			calendar.add(Calendar.DATE, -1);
			mb.put("statEndDate", DateUtils.format(calendar.getTime() , "yyyy-MM-dd"));
			calendar.add(Calendar.DATE, -4);
			mb.put("statStartDate", DateUtils.format(calendar.getTime() , "yyyy-MM-dd"));
			result.put("selectRange", mb.get("statStartDate")+" 至 "+mb.get("statEndDate"));
		}
		mb.put("groupby", "statDate");
		List<LTVGamePlatform> list = reportLtvDao.find("ReportLtv.list",mb);

        //兼容2个ibatise写法不一样 。实在是坑人啊
		mb.put("channelIds", StringUtils.isBlank(channelIds) ? null : channelIds);
		mb.put("zoneIds", StringUtils.isBlank(zoneIds) ? null : zoneIds);

        List<ReportHistoryDaily> dailyList= reportDailyService.listOperate(mb);
		for (LTVGamePlatform ltv:list){
			for (ReportDaily daily:dailyList){
				if (ltv.getAppId().equals(daily.getAppId())&&ltv.getStatDate().equals(daily.getStatDate())){
					ltv.setRegistUser(daily.getRegUsers());
//					ltv.setRegistUser(1155);
				}
			}
			calculateLtv(ltv);
		}
		result.put("data", list);
        return result ;
	}


	public void calculateLtv(LTVGamePlatform ltv){
		int[] ltvs = ltvListToIntArray(ltv);
		int i=1;
		while (i<=ltvs[0]){
			if (i==1){
				i++ ;
				continue;
			}
			ltvs[i]=ltvs[i-1]+ltvs[i];
			i++ ;
		}
		arrayToLtv(ltvs,ltv);
	}

	private void arrayToLtv(int[] array,LTVGamePlatform ltvGamePlatform){
		ltvGamePlatform.setLtv1(array[1]);
		ltvGamePlatform.setLtv2(array[2]);
		ltvGamePlatform.setLtv3(array[3]);
		ltvGamePlatform.setLtv4(array[4]);
		ltvGamePlatform.setLtv5(array[5]);
		ltvGamePlatform.setLtv6(array[6]);
		ltvGamePlatform.setLtv7(array[7]);
		ltvGamePlatform.setLtv14(array[8]);
		ltvGamePlatform.setLtv30(array[9]);
		ltvGamePlatform.setLtv60(array[10]);
		ltvGamePlatform.setLtv90(array[11]);
	}
	private  int [] ltvListToIntArray(LTVGamePlatform ltv){
		int flag = (int) DateUtils.getInterval(new Date(),ltv.getStatDate());
		if (flag>7&&flag<=14){
			flag=8 ;
		}
		if (flag>14&&flag<=30){
			flag=9 ;
		}
		if (flag>30&&flag<=60){
			flag=10;
		}
		if (flag>60&&flag<=90){
			flag=11 ;
		}
		int [] ltvs = new int[12];
		ltvs[0]=flag ;
		ltvs[1]=ltv.getLtv1();
		ltvs[2]=ltv.getLtv2() ;
		ltvs[3]=ltv.getLtv3() ;
		ltvs[4]=ltv.getLtv4();
		ltvs[5]=ltv.getLtv5();
		ltvs[6]=ltv.getLtv6();
		ltvs[7]=ltv.getLtv7();
		ltvs[8]=ltv.getLtv14();
		ltvs[9]=ltv.getLtv30();
		ltvs[10] = ltv.getLtv60();
		ltvs[11] = ltv.getLtv90();
		return  ltvs;
	}

	public List<ReportHistoryDaily> listOperate(MapBean mb){
		if ("game".equals(mb.get("dimension"))) {
			return reportHistoryDailyDao.find("ReportHistoryDaily.listGame", mb);
		}else if ("platform".equals(mb.get("dimension"))) {
			return reportHistoryDailyDao.find("ReportHistoryDaily.listPlatform", mb);
		}else if ("zone".equals(mb.get("dimension"))) {
			return reportHistoryDailyDao.find("ReportHistoryDaily.listZone", mb);
		}
		return null;
	}

//	public Map<String, Object> basic(Long appId, Integer clientType, Integer groupType, String channelIds,String compareChannelIds, String zoneIds, String compareZoneIds,String selectRange, String compareSelectRange, Integer type) {
//		Map<String, Object> result = new HashMap<String, Object>();
//		MapBean mb = new MapBean();
//		mb.put("appId", appId);
//		mb.put("clientType", clientType);
//		mb.put("channelIds", StringUtils.isBlank(channelIds) ? null : StringUtils.split(channelIds,","));
//		mb.put("zoneIds", StringUtils.isBlank(zoneIds) ? null : StringUtils.split(zoneIds,","));
//
//		if (StringUtils.isNotBlank(selectRange)) {
//			mb.put("statStartDate", selectRange.split("至")[0]);
//			mb.put("statEndDate", selectRange.split("至")[1]);
//		}else {
//			Calendar calendar=Calendar.getInstance();
//			//by zhangxibin edit 将时间间隔设置成5天 time 20160503
//			calendar.add(Calendar.DATE, -1);
//			mb.put("statEndDate", DateUtils.format(calendar.getTime() , "yyyy-MM-dd"));
//			calendar.add(Calendar.DATE, -4);
//			mb.put("statStartDate", DateUtils.format(calendar.getTime() , "yyyy-MM-dd"));
//			result.put("selectRange", mb.get("statStartDate")+" 至 "+mb.get("statEndDate"));
//		}
//		List<BPlatform> platforms = bPlatformService.getByIds(channelIds);
//		List<Gamezone> gamezones = bGamezoneService.getByIds(appId,zoneIds);
//		List<BPlatform> comparePlatforms = bPlatformService.getByIds(compareChannelIds);
//		List<Gamezone> compareGamezones = bGamezoneService.getByIds(appId,compareZoneIds);
//		result.put("platforms", platforms);
//		result.put("gamezones", gamezones);
//		result.put("comparePlatforms", comparePlatforms);
//		result.put("compareGamezones", compareGamezones);
//
//		if (StringUtils.isBlank(channelIds) && StringUtils.isBlank(compareChannelIds) && StringUtils.isBlank(zoneIds) && StringUtils.isBlank(compareZoneIds)) {
//			mb.put("dimension", "game");
//		}else if (StringUtils.isBlank(zoneIds) && StringUtils.isBlank(compareZoneIds)) {
//			mb.put("dimension", "platform");
//		}else {
//			mb.put("dimension", "zone");
//		}
//
//		if (StringUtils.isNotBlank(compareChannelIds) || StringUtils.isNotBlank(compareZoneIds)) {		//渠道区服对比
//			mb.put("groupby", "statDate");
//			List<LTVGamePlatform> reportDailies = list(mb);
//
//			mb.put("channelIds", StringUtils.isBlank(compareChannelIds) ? null : StringUtils.split(compareChannelIds,","));
//			mb.put("zoneIds", StringUtils.isBlank(compareZoneIds) ? null : StringUtils.split(compareZoneIds,","));
//			List<LTVGamePlatform> compareReportDailies = list(mb);
//
//			result.put("data", reportDailies);
//			result.put("compareData", compareReportDailies);
//			result.put("type", 1);
//			result.put("group", groupType);
//			result.put("optionJson", getOptionJson(reportDailies, compareReportDailies, false, type));
//		}else if (StringUtils.isNotBlank(compareSelectRange)) {											//时间对比
//			mb.put("groupby", "statDate");
//			List<LTVGamePlatform> reportDailies = list(mb);
//			Date startTime = DateUtils.parse(mb.getString("statStartDate"), "yyyy-MM-dd");
//			Date endTime = DateUtils.parse(mb.getString("statEndDate"), "yyyy-MM-dd");
//			int day = DateUtils.getIntervalDays(startTime, endTime);
//
//			mb.put("groupby", null);
//			result.put("data", list(mb));
//
//			mb.put("groupby", "statDate");
//			mb.put("statStartDate", compareSelectRange.split("至")[0]);
//			startTime = DateUtils.parse(mb.getString("statStartDate"), "yyyy-MM-dd");
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(startTime);
//			calendar.add(Calendar.DATE, day);
//			endTime = calendar.getTime();
//			mb.put("statEndDate", DateUtils.format(endTime, "yyyy-MM-dd"));
//			List<LTVGamePlatform> compareReportDailies = list(mb);
//
//			mb.put("groupby", null);
//			result.put("compareData", list(mb));
//			result.put("optionJson", getOptionJson(reportDailies, compareReportDailies, false, type));
//		}else {																							//无对比
//			if (null != groupType && 1 == groupType) {																		//有选多分区
//				mb.put("groupby", "appId,zoneId,statDate");
//				List<LTVGamePlatform> reportDailies = list(mb);
//				Map<String, List<LTVGamePlatform>> data = new LinkedHashMap<String, List<LTVGamePlatform>>();
//				for (LTVGamePlatform reportDaily : reportDailies) {
//					if (data.get(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd")) == null) {
//						List<LTVGamePlatform> list = new ArrayList<LTVGamePlatform>();
//						list.add(reportDaily);
//						data.put(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"), list);
//					}else {
//						List<LTVGamePlatform> list = data.get(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"));
//						list.add(reportDaily);
//						data.put(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"), list);
//					}
//					for (Gamezone gamezone : gamezones) {
//						if (reportDaily.getZoneId().equals(gamezone.getZoneId())) {
//							reportDaily.setZoneName(gamezone.getZoneName());
//							break;
//						}
//					}
//				}
//
//				result.put("data", data);
//				result.put("type", 2);
//				result.put("group", "zone");
//
//				mb.put("groupby", "statDate");
//				result.put("optionJson", getOptionJson(list(mb), null, false, type));
//			}else if (null != groupType && 2 == groupType) {																	//有选多渠道
//				mb.put("groupby", "appId,platformId,statDate");
//				List<LTVGamePlatform> reportDailies = list(mb);
//
//				Map<String, List<LTVGamePlatform>> data = new LinkedHashMap<String, List<LTVGamePlatform>>();
//				for (LTVGamePlatform reportDaily : reportDailies) {
//					if (data.get(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd")) == null) {
//						List<LTVGamePlatform> list = new ArrayList<LTVGamePlatform>();
//						list.add(reportDaily);
//						data.put(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"), list);
//					}else {
//						List<LTVGamePlatform> list = data.get(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"));
//						list.add(reportDaily);
//						data.put(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"), list);
//					}
//
//					for (BPlatform bPlatform : platforms) {
//						if (bPlatform.getId().toString().equals(reportDaily.getPlatformId().toString())) {
//							reportDaily.setPlatformName(bPlatform.getPlatformName());
//							break;
//						}
//					}
//				}
//				result.put("data", data);
//				result.put("type", 2);
//				result.put("group", "platform");
//
//				mb.put("groupby", "statDate");
//				result.put("optionJson", getOptionJson(list(mb), null, false, type));
//			}else {
//				mb.put("groupby", "appId,statDate");
//				List<LTVGamePlatform> reportDailies = list(mb);
//				List<Game> games = bGameService.list();
//				Map<String, List<LTVGamePlatform>> data = new LinkedHashMap<String, List<LTVGamePlatform>>();
//				for (LTVGamePlatform reportDaily : reportDailies) {
//					if (data.get(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd")) == null) {
//						List<LTVGamePlatform> list = new ArrayList<LTVGamePlatform>();
//						list.add(reportDaily);
//						data.put(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"), list);
//					}else {
//						List<LTVGamePlatform> list = data.get(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"));
//						list.add(reportDaily);
//						data.put(DateUtils.format(reportDaily.getStatDate(), "yyyy-MM-dd"), list);
//					}
//
//					for (Game game : games) {
//						if (game.getId().intValue() == reportDaily.getAppId().intValue()) {
//							reportDaily.setAppName(game.getAppName());
//							break;
//						}
//					}
//				}
//				result.put("data", data);
//				result.put("type", 2);
//				result.put("group", "game");
//
//				mb.put("groupby", "statDate");
//				result.put("optionJson", getOptionJson(list(mb), null, true, type));
//			}
//		}
//		return result;
//	}
	




	public Map<String, Object> operate(Long appId, Integer clientType, String channelIds, String zoneIds, String selectRange) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		MapBean mb = new MapBean();
		mb.put("appId", appId);
		mb.put("clientType", clientType);
		mb.put("channelIds", StringUtils.isBlank(channelIds) ? null : channelIds);
		mb.put("zoneIds", StringUtils.isBlank(zoneIds) ? null : zoneIds);
		mb.put("groupby", "a.`statDate`");
		
		if (StringUtils.isNotBlank(selectRange)) {
			mb.put("statStartDate", selectRange.split("至")[0]);
			mb.put("statEndDate", selectRange.split("至")[1]);
		}else {
			Calendar calendar=Calendar.getInstance();
			//by zhangxibin edit 将时间间隔设置成5天 time 20160503
			calendar.add(Calendar.DATE, -1);
			mb.put("statEndDate", DateUtils.format(calendar.getTime() , "yyyy-MM-dd"));
			calendar.add(Calendar.DATE, -4);
			mb.put("statStartDate", DateUtils.format(calendar.getTime() , "yyyy-MM-dd"));
			result.put("selectRange", mb.get("statStartDate")+" 至 "+mb.get("statEndDate"));
		}
		if (StringUtils.isBlank(channelIds) && StringUtils.isBlank(zoneIds)) {
			mb.put("dimension", "game");
		}else if (StringUtils.isBlank(zoneIds)) {
			mb.put("dimension", "platform");
		}else {
			mb.put("dimension", "zone");
		}
		result.put("data", listOperate(mb));
		return result;
	}

}
