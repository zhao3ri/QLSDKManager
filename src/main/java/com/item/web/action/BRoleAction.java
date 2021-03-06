package com.item.web.action;

import com.item.domain.BPlatform;
import com.item.domain.BRole;
import com.item.domain.Game;
import com.item.service.BGameService;
import com.item.service.BPlatformService;
import com.item.service.BRoleService;
import com.item.utils.CookieUtils;
import com.item.utils.Excel;
import com.item.utils.DateUtils;
import com.item.utils.ExcelExport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.utils.Struts2Utils;
import core.module.web.Struts2Action;

@Action
public class BRoleAction extends Struts2Action{
	
	private static final long serialVersionUID = 2982346690414234546L;
	
	@Autowired
    private BRoleService bRoleService;
    @Autowired
    private BGameService bGameService;
    @Autowired
    private BPlatformService platformService;

    private Page<BRole> rolePage = new Page<BRole>(10);
    private BRole role;
    private List<Game> games;
    private List<BPlatform> platforms;
    private String selectRange;
	
    public String list(){
        MapBean mb = search();
        
        games = bGameService.list();
        
        platforms = platformService.getAllPlatform();
        
        if (null == mb.get("appId") && !CollectionUtils.isEmpty(games)){
			Long appId = games.get(0).getId();
			String cookieAppId = CookieUtils.getCookieValue(Struts2Utils.getRequest(), "cookie_appId");
			if (StringUtils.isNotBlank(cookieAppId)) {
				appId = Long.valueOf(cookieAppId);
				
			}
			mb.put("appId", appId);
		}
		CookieUtils.setCookieValue(Struts2Utils.getResponse(), "cookie_appId", String.valueOf(mb.get("appId")));
		if (role == null) {
			role = new BRole();
		}
		if (mb.get("appId") != null) {
			role.setAppId((Long)mb.get("appId"));
		}
		rolePage = bRoleService.page(rolePage, mb);
        return "list";
    }
	
 	public void excelExport(){
 		try{
 			MapBean mb = search();
 			List<BRole> list = bRoleService.list(mb);
            ExcelExport ee=new ExcelExport();
            ee.setHead("数据统计报表_"+DateUtils.format(new Date(),"yyyyMMddHHmmss"));
            ee.getEl().add("游戏名称");
            ee.getEl().add("平台名称");
            ee.getEl().add("分区");
            ee.getEl().add("角色ID");
            ee.getEl().add("角色名称");
            ee.getEl().add("创建时间");
            
            for(int i=0; i<list.size(); i++){
            	List<Excel> e=new ArrayList<Excel>();
 	            e.add(new Excel(list.get(i).getAppName(), 20));
	            e.add(new Excel(list.get(i).getPlatformName(), 20));
	            e.add(new Excel(list.get(i).getZoneId(), 20));
	            e.add(new Excel(list.get(i).getRoleId(), 20));
	            e.add(new Excel(list.get(i).getRoleName(), 20));
	            e.add(new Excel(DateUtils.format(list.get(i).getCreateTime()), 20));
                ee.getEll().add(e);
            }
			ee.setFoot("总共："+list.size()+"条记录");
            ee.excelExport();
      	}catch(Exception e){
            e.printStackTrace();
        }
    }

    private MapBean search(){
        MapBean mb=new MapBean();
        if(role != null){
        	if (role.getClientType() != null){
                mb.put("clientType", role.getClientType());
            }
        	if (role.getAppId() != null){
                mb.put("appId", role.getAppId());
            }
        	if (role.getPlatformId() != null){
                mb.put("platformId", role.getPlatformId());
            }
        	if (StringUtils.isNotBlank(role.getZoneId())){
                mb.put("zoneId", role.getZoneId());
            }
        	if (StringUtils.isNotBlank(role.getRoleId())){
                mb.put("roleId", role.getRoleId());
            }
        	if (StringUtils.isNotBlank(role.getRoleName())){
                mb.put("roleName", role.getRoleName());
            }
        }
        if (StringUtils.isBlank(selectRange)) {
        	selectRange = DateUtils.format(new Date(), "yyyy-MM-dd") + " 至 " + DateUtils.format(new Date(), "yyyy-MM-dd");
		}
        mb.put("statStartDate", selectRange.split("至")[0].trim());
        mb.put("statEndDate", selectRange.split("至")[1].trim());
        
        mb.put("orderby","a.createTime desc");
        return mb;
    }

	public Page<BRole> getRolePage() {
		return rolePage;
	}

	public void setRolePage(Page<BRole> rolePage) {
		this.rolePage = rolePage;
	}

	public BRole getRole() {
		return role;
	}

	public void setRole(BRole role) {
		this.role = role;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<BPlatform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<BPlatform> platforms) {
		this.platforms = platforms;
	}

	public String getSelectRange() {
		return selectRange;
	}

	public void setSelectRange(String selectRange) {
		this.selectRange = selectRange;
	}
}