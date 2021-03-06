package com.item.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.item.constants.Constants;
import com.item.domain.BPlatform;
import com.item.domain.BPlatformApp;
import com.item.domain.Game;
import com.item.domain.Gamezone;
import com.item.domain.SGame;
import com.item.domain.SGameMonthly;
import com.item.domain.SPlatform;
import com.item.domain.SPlatformMonthly;
import com.item.domain.SRechargeRank;
import com.item.domain.SRoleRank;
import com.item.domain.SZonePlatform;
import com.item.domain.SZonePlatformMonthly;
import com.item.domain.report.GameClientMonthlyReport;
import com.item.domain.report.GameClientReport;
import com.item.service.BGameService;
import com.item.service.BPlatformAppService;
import com.item.service.BPlatformService;
import com.item.service.SGameMonthlyService;
import com.item.service.SGameService;
import com.item.service.SPlatformMonthlyService;
import com.item.service.SPlatformService;
import com.item.service.SRechargeRankService;
import com.item.service.SRoleRankService;
import com.item.service.SZonePlatformMonthlyService;
import com.item.service.SZonePlatformService;
import com.item.service.SysroleappauthService;
import com.item.utils.CookieUtils;
import com.item.utils.DateUtils;
import com.item.utils.DecimallFormatUtil;
import com.item.utils.Excel;
import com.item.utils.ExcelExport;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.utils.Struts2Utils;
import core.module.web.Struts2Action;

public class ReportAction extends Struts2Action {
	private static final long serialVersionUID = 5645405406052360424L;
	
	@Autowired
	private BGameService bGameService;
	@Autowired
	private SPlatformService sPlatformService;
	@Autowired
	private SPlatformMonthlyService sPlatformMonthlyService;
	@Autowired
	private SGameService sGameService;
	@Autowired
	private SZonePlatformService sZonePlatformService;
	@Autowired
	private BPlatformAppService bPlatformAppService;
	@Autowired
	private BPlatformService bPlatformService;
	@Autowired
	private SZonePlatformMonthlyService sZonePlatformMonthlyService;
	@Autowired
	private SRechargeRankService sRechargeRankService;
	@Autowired
	private  SGameMonthlyService sGameMonthlyService;
	@Autowired
	private SRoleRankService sRoleRankService;
	@Resource
	private SysroleappauthService roleAppAuthService;

	private List<GameClientReport> gameClientReports = new ArrayList<GameClientReport>();
	private List<GameClientMonthlyReport> gameClientMonthlyReports=new ArrayList<GameClientMonthlyReport>();
	private List<Game> allGames;
	private Long appId;
	private String selectRange;
	private List<Gamezone> gamezones;
	private List<BPlatformApp> platformApps;
	private String yearMonthStr;
	private Integer isCompare;
	private String checkedIds;
	private String platformId;
	private List<BPlatform> platforms;
	private List<Long> appIds;
	// 渠道分析总数Map
	private Map<String, List<SPlatform>> iteratePlatformsMap = new HashMap<String, List<SPlatform>>();
	// 渠道分析月份Map
	private Map<String, List<SPlatformMonthly>> iteratePlatformsMonthlyMap = new HashMap<String, List<SPlatformMonthly>>();
	// 渠道分析报表--游戏数据汇总
	private List<SPlatform> sPlatformsApp;
	// 渠道分析报表--渠道数据汇总
	private List<SPlatform> sPlatformsPlatform = new ArrayList<SPlatform>();
	// 渠道分析月报表--游戏数据汇总
	private List<SPlatformMonthly> sPlatformMonthliesApp;
	// 渠道分析月报表--渠道数据汇总
	private List<SPlatformMonthly> sPlatformMonthliesPlatform = new ArrayList<SPlatformMonthly>();
	/*
	 * 1.区服总计 2.区服详细 3.渠道详细
	 */
	private Long type;

	private List<SZonePlatform> sZonePlatforms = new ArrayList<SZonePlatform>();
	private List<SZonePlatformMonthly> sZonePlatformMonthlies = new ArrayList<SZonePlatformMonthly>();
	// 区服总数Map
	private Map<String, List<SZonePlatform>> iterateZonePlatformsMap = new HashMap<String, List<SZonePlatform>>();
	// 区服月份Map
	private Map<String, List<SZonePlatformMonthly>> iterateZonePlatformsMonthlyMap = new HashMap<String, List<SZonePlatformMonthly>>();

	private Page<SRechargeRank> pageRechargeRank=new Page<SRechargeRank>(10);
	private Page<SRoleRank> pageRoleRank=new Page<SRoleRank>(10);
	private Integer firstResult;
	
	private Boolean initSearch(){
		allGames = bGameService.list(null);
		if (CollectionUtils.isEmpty(allGames)) {
			try {
				Struts2Utils.getResponse().sendRedirect(Struts2Utils.getRequest().getContextPath()+"/common/403.jsp");
				return false;
			} catch (IOException e) {
				return false;
			}
		}
		
		appIds = new ArrayList<Long>();
		for (Game game : allGames) {
			appIds.add(game.getId());
		}
		
		if (null == appId){
			appId = allGames.get(0).getId();
			String cookieAppId = CookieUtils.getCookieValue(Struts2Utils.getRequest(), "cookie_appId");
			if (StringUtils.isNotBlank(cookieAppId) && appIds.contains(Long.valueOf(cookieAppId))) {
				appId = Long.valueOf(cookieAppId);
			}
		}
		CookieUtils.setCookieValue(Struts2Utils.getResponse(), "cookie_appId", String.valueOf(appId));
		
		return true;
	}
	
	public String summary() {
		if (!initSearch()) {
			return null;
		}
		
		MapBean mb = new MapBean();
		mb.put("platformId", Constants.YOULE_PLATFROMID);
		if (StringUtils.isNotBlank(yearMonthStr)){
			mb.put("yearMonth", yearMonthStr.replace("-", ""));
		}else {
			Calendar calendar=Calendar.getInstance();
			calendar.add(Calendar.DATE, -1);
		    mb.put("statDate", DateUtils.format(calendar.getTime(), "yyyy-MM-dd"));
		}
		for (Game game : allGames) {
			//年月有值
			if (StringUtils.isNotBlank(yearMonthStr)) {
				GameClientMonthlyReport gameClientMonthlyReport = new GameClientMonthlyReport();
				gameClientMonthlyReport.setAppId(game.getId());
				gameClientMonthlyReport.setAppName(game.getAppName());
			
				mb.put("appId", game.getId());
				//ANDROID
				mb.put("clientType", Constants.CLIENT_ANDROID);
				
				//CPS
				SGameMonthly sGameMonthlyAnriod = sGameMonthlyService.summary(mb);
				//CPA
				SPlatformMonthly cpapPlatformMonthlyAnriod = sPlatformMonthlyService.getByMap(mb);

				gameClientMonthlyReport.setAndroidCpa(cpapPlatformMonthlyAnriod == null ? new SPlatformMonthly(): cpapPlatformMonthlyAnriod);

				addCpsMonthlyData(cpapPlatformMonthlyAnriod, sGameMonthlyAnriod);
				gameClientMonthlyReport.setAndroidCps(sGameMonthlyAnriod == null ? new SGameMonthly() : sGameMonthlyAnriod);
		
				//IOS
				mb.put("clientType", Constants.CLIENT_IOS);
				
				//CPS
				SGameMonthly sGameMonthlyIOS = sGameMonthlyService.summary(mb);
				//CPA
				SPlatformMonthly cpapPlatformMonthlyIOS= sPlatformMonthlyService.getByMap(mb);
			
				gameClientMonthlyReport.setIosCpa(cpapPlatformMonthlyIOS == null ? new SPlatformMonthly(): cpapPlatformMonthlyIOS);
				
				addCpsMonthlyData(cpapPlatformMonthlyIOS, sGameMonthlyIOS);
				gameClientMonthlyReport.setIosCps(sGameMonthlyIOS == null ? new SGameMonthly() : sGameMonthlyIOS);

	     		gameClientMonthlyReports.add(gameClientMonthlyReport);
	     		
			}else {
				GameClientReport gameClientReport = new GameClientReport();
				gameClientReport.setAppId(game.getId());
				gameClientReport.setAppName(game.getAppName());
			
				mb.put("appId", game.getId());
				//ANDROID
				mb.put("clientType", Constants.CLIENT_ANDROID);
				
				//CPS
				SGame sGameAnriod = sGameService.summary(mb);
				//CPA
				SPlatform cpapPlatformAnriod = sPlatformService.getByMap(mb);

				gameClientReport.setAndroidCpa(cpapPlatformAnriod == null ? new SPlatform(): cpapPlatformAnriod);

				addCpsData(cpapPlatformAnriod, sGameAnriod);
				gameClientReport.setAndroidCps(sGameAnriod == null ? new SGame() : sGameAnriod);
		
				//IOS
				mb.put("clientType", Constants.CLIENT_IOS);
				
				//CPS
				SGame sGameIOS = sGameService.summary(mb);
				//CPA
				SPlatform cpapPlatformIOS = sPlatformService.getByMap(mb);
			
				gameClientReport.setIosCpa(cpapPlatformIOS == null ? new SPlatform(): cpapPlatformIOS);
				
				addCpsData(cpapPlatformIOS, sGameIOS);
				gameClientReport.setIosCps(sGameIOS == null ? new SGame() : sGameIOS);

	     		gameClientReports.add(gameClientReport);
			}
		}
		return "summary";
	}
	/*
	 * CPS=Game-Platform
	 */
	private void addCpsData(SPlatform sPlatform, SGame sGame) {
		if (sGame != null  && sPlatform!=null) {
			sGame.setTotalRoleUser(sGame.getTotalRoleUser()- sPlatform.getTotalRoleUser());
			sGame.setTotalRegUser(sGame.getTotalRegUser()- sPlatform.getTotalRegUser());
			sGame.setDevices(sGame.getDevices()- sPlatform.getDevices());
			sGame.setActiveUsers(sGame.getActiveUsers()- sPlatform.getActiveUsers());
			sGame.setPayAmount(sGame.getPayAmount()- sPlatform.getPayAmount());
			sGame.setPayTimes(sGame.getPayTimes()- sPlatform.getPayTimes());
			sGame.setPayUsers(sGame.getPayUsers()- sPlatform.getPayUsers());
		}
	}
	
	private void addCpsMonthlyData(SPlatformMonthly sPlatformMonthly, SGameMonthly sGameMonthly) {
		if (sGameMonthly != null && sPlatformMonthly != null) {
			sGameMonthly.setTotalRoleUser(sGameMonthly.getTotalRoleUser()- sPlatformMonthly.getTotalRoleUser());
			sGameMonthly.setTotalRegUser(sGameMonthly.getTotalRegUser()- sPlatformMonthly.getTotalRegUser());
			sGameMonthly.setDevices(sGameMonthly.getDevices()- sPlatformMonthly.getDevices());
			sGameMonthly.setActiveUsers(sGameMonthly.getActiveUsers()-sPlatformMonthly.getActiveUsers());
			sGameMonthly.setPayAmount(sGameMonthly.getPayAmount()- sPlatformMonthly.getPayAmount());
			sGameMonthly.setPayTimes(sGameMonthly.getPayTimes()- sPlatformMonthly.getPayTimes());
			sGameMonthly.setPayUsers(sGameMonthly.getPayUsers()- sPlatformMonthly.getPayUsers());
		}
	}

	public String getChannelZone() {
		platformApps = bPlatformAppService.getAllPlatform(appId);
		gamezones = bGameService.getZones(appId);
		return "getChannelZone";
	}
	
	public String getZone(){
		gamezones = bGameService.getZones(appId);
		return "getZone";
	}
	
	public String platform() {
		if (!initSearch()) {
			return null;
		}
		
		MapBean mb = new MapBean();
		if (StringUtils.isNotBlank(yearMonthStr)) {
			mb.put("yearMonth", yearMonthStr.replace("-", ""));
			mb.put("appIds", appIds);
			sPlatformMonthliesApp = sPlatformMonthlyService.listApp(mb);
			sPlatformMonthliesPlatform = sPlatformMonthlyService.listPlatform(mb);

			for (SPlatformMonthly sPlatformMonthly : sPlatformMonthliesApp) {
				if (iteratePlatformsMonthlyMap.get(sPlatformMonthly.getAppName()) == null) {
					List<SPlatformMonthly> thisSPlatformsMonthliesList = new ArrayList<SPlatformMonthly>();
					thisSPlatformsMonthliesList.add(sPlatformMonthly);
					iteratePlatformsMonthlyMap.put(sPlatformMonthly.getAppName(),thisSPlatformsMonthliesList);
				} else {
					List<SPlatformMonthly> thisSPlatformsMonthliesList = iteratePlatformsMonthlyMap.get(sPlatformMonthly.getAppName());
					thisSPlatformsMonthliesList.add(sPlatformMonthly);
					iteratePlatformsMonthlyMap.put(sPlatformMonthly.getAppName(),thisSPlatformsMonthliesList);
				}
			}
		} else {
			Calendar calendar=Calendar.getInstance();
			calendar.add(Calendar.DATE, -1);
		    mb.put("statDate", DateUtils.format(calendar.getTime(), "yyyy-MM-dd"));
		    mb.put("appIds", appIds);
			sPlatformsApp = sPlatformService.getMaxDateDataApp(mb);
			sPlatformsPlatform = sPlatformService.getMaxDateDataPlatform(mb);
			for (SPlatform sPlatform : sPlatformsApp) {
				if (iteratePlatformsMap.get(sPlatform.getAppName()) == null) {
					List<SPlatform> thisSPlatformsList = new ArrayList<SPlatform>();
					thisSPlatformsList.add(sPlatform);
					iteratePlatformsMap.put(sPlatform.getAppName(),thisSPlatformsList);
				} else {
					List<SPlatform> thisSPlatformsList = iteratePlatformsMap.get(sPlatform.getAppName());
					thisSPlatformsList.add(sPlatform);
					iteratePlatformsMap.put(sPlatform.getAppName(),thisSPlatformsList);
				}
			}
		}
		return "platform";
	}

	/*
	 * 区服分析
	 */
	public String zone() {
		if (!initSearch()) {
			return null;
		}
		MapBean mb = new MapBean();
		if (StringUtils.isNotBlank(yearMonthStr)){
			mb.put("yearMonth", yearMonthStr.replace("-", ""));
		}else {
			Calendar calendar=Calendar.getInstance();
			calendar.add(Calendar.DATE, -1);
		    mb.put("statDate", DateUtils.format(calendar.getTime(), "yyyy-MM-dd"));
		}
		mb.put("appId", appId);
		
		if (type == null || type == 1) {												//type==1--- 区服总计--
			if (StringUtils.isNotBlank(yearMonthStr)) {									
				sZonePlatformMonthlies = sZonePlatformMonthlyService.zoneSummary(mb);
			}else {
				sZonePlatforms = sZonePlatformService.zoneSummary(mb);
			}
		}else if (type == 2) {															//type==2--- 渠道详细（使用map（string,list）)
			if (StringUtils.isNotBlank(yearMonthStr)) {
				sZonePlatformMonthlies = sZonePlatformMonthlyService.zoneDetail(mb);
				for (SZonePlatformMonthly sZonePlatformMonthly : sZonePlatformMonthlies) {
					if (iterateZonePlatformsMonthlyMap.get(sZonePlatformMonthly.getZoneName()) == null) {
						List<SZonePlatformMonthly> thisList = new ArrayList<SZonePlatformMonthly>();
						thisList.add(sZonePlatformMonthly);
						iterateZonePlatformsMonthlyMap.put(sZonePlatformMonthly.getZoneName(), thisList);
					} else {
						List<SZonePlatformMonthly> thisList = iterateZonePlatformsMonthlyMap.get(sZonePlatformMonthly.getZoneName());
						thisList.add(sZonePlatformMonthly);

						iterateZonePlatformsMonthlyMap.put(sZonePlatformMonthly.getZoneName(), thisList);
					}
				}
			}else {
				sZonePlatforms = sZonePlatformService.zoneDetail(mb);
				for (SZonePlatform sZonePlatform : sZonePlatforms) {
					if (iterateZonePlatformsMap.get(sZonePlatform.getZoneName()) == null) {
						List<SZonePlatform> thisList = new ArrayList<SZonePlatform>();
						thisList.add(sZonePlatform);

						iterateZonePlatformsMap.put(sZonePlatform.getZoneName(), thisList);
					} else {
						List<SZonePlatform> thisList = iterateZonePlatformsMap.get(sZonePlatform.getZoneName());
						thisList.add(sZonePlatform);
						
						iterateZonePlatformsMap.put(sZonePlatform.getZoneName(), thisList);
					}
				}
			}
		}else {																					//type==3--- 区服详细（使用map（string,list））
			if (StringUtils.isNotBlank(yearMonthStr)) {
				sZonePlatformMonthlies = sZonePlatformMonthlyService.platformDetail(mb);
				for (SZonePlatformMonthly sZonePlatformMonthly : sZonePlatformMonthlies) {
					if (iterateZonePlatformsMonthlyMap.get(sZonePlatformMonthly.getPlatformName()) == null) {
						List<SZonePlatformMonthly> thisList = new ArrayList<SZonePlatformMonthly>();
						thisList.add(sZonePlatformMonthly);
						iterateZonePlatformsMonthlyMap.put(sZonePlatformMonthly.getPlatformName(),thisList);
					} else {
						List<SZonePlatformMonthly> thisList = iterateZonePlatformsMonthlyMap.get(sZonePlatformMonthly.getPlatformName());
						thisList.add(sZonePlatformMonthly);
						iterateZonePlatformsMonthlyMap.put(	sZonePlatformMonthly.getPlatformName(),thisList);
					}
				}
			}else {
				sZonePlatforms = sZonePlatformService.platformDetail(mb);
				for (SZonePlatform sZonePlatform : sZonePlatforms) {
					if (iterateZonePlatformsMap.get(sZonePlatform.getPlatformName()) == null) {
						List<SZonePlatform> thisList = new ArrayList<SZonePlatform>();
						thisList.add(sZonePlatform);
						iterateZonePlatformsMap.put(sZonePlatform.getPlatformName(), thisList);
					} else {
						List<SZonePlatform> thisList = iterateZonePlatformsMap.get(sZonePlatform.getPlatformName());
						thisList.add(sZonePlatform);
						iterateZonePlatformsMap.put(sZonePlatform.getPlatformName(), thisList);
					}
				}
			}
		}
		return "zone";
	}
	
	public String rank() {	
		if (!initSearch()) {
			return null;
		}
//		platforms = bPlatformService.getAllPlatform();
		
		MapBean mb = new MapBean();
		mb.put("appId", appId);
//		mb.put("status", 2);
//		mb.put("notifyStatus", 2);
		mb.put("platformId", platformId);
//		mb.put("checkedIds", StringUtils.split(checkedIds, ","));
//		if (StringUtils.isNotBlank(selectRange)) {
//			mb.put("startDate", selectRange.split("至")[0] + " 00:00:00");
//			mb.put("endDate", selectRange.split("至")[1] +" 23:59:59");
//		}
		
		pageRechargeRank = sRechargeRankService.page(pageRechargeRank, mb);
		
		firstResult = pageRechargeRank.getFirstResult();
		return "rank";
	}
	
	public void rankExcelExport(){
 		try{
 			MapBean mb = new MapBean();
 			mb.put("appId", appId);
 			mb.put("status", 2);
 			mb.put("notifyStatus", 2);
 			mb.put("platformId", platformId);
 			mb.put("checkedIds", checkedIds);
 			if (StringUtils.isNotBlank(selectRange)) {
 				mb.put("startDate", selectRange.split("至")[0] + " 00:00:00");
 				mb.put("endDate", selectRange.split("至")[1] +" 23:59:59");
 			}
 			
            ExcelExport ee = new ExcelExport();
            ee.setHead("数据统计报表_"+DateUtils.format(new Date(),"yyyyMMddHHmmss"));
            ee.getEl().add("用户ID");
        	ee.getEl().add("充值金额");
        	ee.getEl().add("排序");
        	ee.getEl().add("最近登录时间");
        	
        	List<SRechargeRank> ranks = sRechargeRankService.list(mb);
        	int i = 1;
            for (SRechargeRank item : ranks) {
            	List<Excel> e=new ArrayList<Excel>();
              	e.add(new Excel(item.getPlatformName()+"_"+item.getUid(),50));
              	e.add(new Excel(DecimallFormatUtil.format((double)item.getAmount()/100),20));
              	e.add(new Excel(i++,20));
              	e.add(new Excel(DateUtils.format(item.getLastLoginDate(), "yyyy-MM-dd HH:mm:ss"),20));
                ee.getEll().add(e);
            }
			ee.setFoot("总共："+ranks.size()+"条记录");
            ee.excelExport();
      	}catch(Exception e){
            e.printStackTrace();
        } 
	}
	
	public String roleRank() {	
		if (!initSearch()) {
			return null;
		}
		Map<String, String> idToName = new HashMap<String, String>();
		platforms = bPlatformService.getAllPlatform();
		for (BPlatform bPlatform : platforms) {
			idToName.put(String.valueOf(bPlatform.getId()), bPlatform.getPlatformName());
		}
		
		MapBean mb = new MapBean();
		mb.put("appId", appId);
		mb.put("roleName", checkedIds);
//		mb.put("status", 2);
//		mb.put("notifyStatus", 2);
		mb.put("platformId", platformId);
//		if (StringUtils.isNotBlank(selectRange)) {
//			mb.put("startDate", selectRange.split("至")[0] + " 00:00:00");
//			mb.put("endDate", selectRange.split("至")[1] +" 23:59:59");
//		}
		
		pageRoleRank = sRoleRankService.page(pageRoleRank, mb);
		firstResult = pageRoleRank.getFirstResult();
		for (SRoleRank rank : pageRoleRank.getResult()) {
			rank.setPlatformName(idToName.get(String.valueOf(rank.getPlatformId())));
		}
		return "roleRank";
	}
	
	public void roleRankExcelExport(){
 		try{
 			Map<String, String> idToName = new HashMap<String, String>();
 			platforms = bPlatformService.getAllPlatform();
 			for (BPlatform bPlatform : platforms) {
 				idToName.put(String.valueOf(bPlatform.getId()), bPlatform.getPlatformName());
 			}
 			
 			MapBean mb = new MapBean();
 			mb.put("appId", appId);
 			mb.put("roleName", checkedIds);
 			mb.put("status", 2);
 			mb.put("notifyStatus", 2);
 			mb.put("platformId", platformId);
 			if (StringUtils.isNotBlank(selectRange)) {
 				mb.put("startDate", selectRange.split("至")[0] + " 00:00:00");
 				mb.put("endDate", selectRange.split("至")[1] +" 23:59:59");
 			}
 			
            ExcelExport ee = new ExcelExport();
            ee.setHead("数据统计报表_"+DateUtils.format(new Date(),"yyyyMMddHHmmss"));
            ee.getEl().add("游戏");
            ee.getEl().add("平台");
            ee.getEl().add("角色名称");
        	ee.getEl().add("充值金额");
        	ee.getEl().add("排序");
        	ee.getEl().add("最近登录时间");
        	
        	List<SRoleRank> ranks = sRoleRankService.list(mb);
        	
        	int i = 1;
            for (SRoleRank item : ranks) {
            	List<Excel> e=new ArrayList<Excel>();
            	e.add(new Excel(item.getAppName(),30));
            	e.add(new Excel(idToName.get(String.valueOf(item.getPlatformId())),30));
            	e.add(new Excel(item.getRoleName(),30));
              	e.add(new Excel(DecimallFormatUtil.format((double)item.getAmount()/100),20));
              	e.add(new Excel(i++,20));
              	e.add(new Excel(DateUtils.format(item.getLastLoginDate(), "yyyy-MM-dd HH:mm:ss"),20));
                ee.getEll().add(e);
            }
			ee.setFoot("总共："+ranks.size()+"条记录");
            ee.excelExport();
      	}catch(Exception e){
            e.printStackTrace();
        } 
	}
	
	public List<GameClientReport> getGameClientReports() {
		return gameClientReports;
	}
	public void setGameClientReports(List<GameClientReport> gameClientReports) {
		this.gameClientReports = gameClientReports;
	}
	public List<GameClientMonthlyReport> getGameClientMonthlyReports() {
		return gameClientMonthlyReports;
	}
	public void setGameClientMonthlyReports(
			List<GameClientMonthlyReport> gameClientMonthlyReports) {
		this.gameClientMonthlyReports = gameClientMonthlyReports;
	}
	public List<Game> getAllGames() {
		return allGames;
	}
	public void setAllGames(List<Game> allGames) {
		this.allGames = allGames;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getSelectRange() {
		return selectRange;
	}
	public void setSelectRange(String selectRange) {
		this.selectRange = selectRange;
	}
	public List<Gamezone> getGamezones() {
		return gamezones;
	}
	public void setGamezones(List<Gamezone> gamezones) {
		this.gamezones = gamezones;
	}
	public List<BPlatformApp> getPlatformApps() {
		return platformApps;
	}
	public void setPlatformApps(List<BPlatformApp> platformApps) {
		this.platformApps = platformApps;
	}
	public String getYearMonthStr() {
		return yearMonthStr;
	}
	public void setYearMonthStr(String yearMonthStr) {
		this.yearMonthStr = yearMonthStr;
	}
	public Map<String, List<SPlatform>> getIteratePlatformsMap() {
		return iteratePlatformsMap;
	}
	public void setIteratePlatformsMap(
			Map<String, List<SPlatform>> iteratePlatformsMap) {
		this.iteratePlatformsMap = iteratePlatformsMap;
	}
	public Map<String, List<SPlatformMonthly>> getIteratePlatformsMonthlyMap() {
		return iteratePlatformsMonthlyMap;
	}
	public void setIteratePlatformsMonthlyMap(
			Map<String, List<SPlatformMonthly>> iteratePlatformsMonthlyMap) {
		this.iteratePlatformsMonthlyMap = iteratePlatformsMonthlyMap;
	}
	public List<SPlatform> getSPlatformsApp() {
		return sPlatformsApp;
	}
	public void setSPlatformsApp(List<SPlatform> sPlatformsApp) {
		this.sPlatformsApp = sPlatformsApp;
	}
	public List<SPlatform> getSPlatformsPlatform() {
		return sPlatformsPlatform;
	}
	public void setSPlatformsPlatform(List<SPlatform> sPlatformsPlatform) {
		this.sPlatformsPlatform = sPlatformsPlatform;
	}
	public List<SPlatformMonthly> getSPlatformMonthliesApp() {
		return sPlatformMonthliesApp;
	}
	public void setSPlatformMonthliesApp(
			List<SPlatformMonthly> sPlatformMonthliesApp) {
		this.sPlatformMonthliesApp = sPlatformMonthliesApp;
	}
	public List<SPlatformMonthly> getSPlatformMonthliesPlatform() {
		return sPlatformMonthliesPlatform;
	}
	public void setSPlatformMonthliesPlatform(
			List<SPlatformMonthly> sPlatformMonthliesPlatform) {
		this.sPlatformMonthliesPlatform = sPlatformMonthliesPlatform;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public List<SZonePlatform> getSZonePlatforms() {
		return sZonePlatforms;
	}
	public void setSZonePlatforms(List<SZonePlatform> sZonePlatforms) {
		this.sZonePlatforms = sZonePlatforms;
	}
	
	public Map<String, List<SZonePlatform>> getIterateZonePlatformsMap() {
		return iterateZonePlatformsMap;
	}
	public void setIterateZonePlatformsMap(
			Map<String, List<SZonePlatform>> iterateZonePlatformsMap) {
		this.iterateZonePlatformsMap = iterateZonePlatformsMap;
	}
	public Map<String, List<SZonePlatformMonthly>> getIterateZonePlatformsMonthlyMap() {
		return iterateZonePlatformsMonthlyMap;
	}
	public void setIterateZonePlatformsMonthlyMap(
			Map<String, List<SZonePlatformMonthly>> iterateZonePlatformsMonthlyMap) {
		this.iterateZonePlatformsMonthlyMap = iterateZonePlatformsMonthlyMap;
	}
	public Page<SRechargeRank> getPageRechargeRank() {
		return pageRechargeRank;
	}
	public void setPageRechargeRank(Page<SRechargeRank> pageRechargeRank) {
		this.pageRechargeRank = pageRechargeRank;
	}
	public Integer getFirstResult() {
		return firstResult;
	}
	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}
	public List<SZonePlatformMonthly> getSZonePlatformMonthlies() {
		return sZonePlatformMonthlies;
	}
	public void setSZonePlatformMonthlies(List<SZonePlatformMonthly> sZonePlatformMonthlies) {
		this.sZonePlatformMonthlies = sZonePlatformMonthlies;
	}
	public Integer getIsCompare() {
		return isCompare;
	}
	public void setIsCompare(Integer isCompare) {
		this.isCompare = isCompare;
	}
	public String getCheckedIds() {
		return checkedIds;
	}
	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}
	public Page<SRoleRank> getPageRoleRank() {
		return pageRoleRank;
	}
	public void setPageRoleRank(Page<SRoleRank> pageRoleRank) {
		this.pageRoleRank = pageRoleRank;
	}
	public void setAppIds(List<Long> appIds) {
		this.appIds = appIds;
	}
	public List<Long> getAppIds() {
		return appIds;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public List<BPlatform> getPlatforms() {
		return platforms;
	}
	public void setPlatforms(List<BPlatform> platforms) {
		this.platforms = platforms;
	}
}
