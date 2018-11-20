package com.item.web.action;

import com.item.application.StateContext;
import com.item.application.StateVo;
import com.item.domain.BOrder;
import com.item.domain.BPlatform;
import com.item.domain.Game;
import com.item.domain.Gamezone;
import com.item.service.BGameService;
import com.item.service.BGamezoneService;
import com.item.service.BOrderService;
import com.item.service.BPlatformService;
import com.item.utils.DecimallFormatUtil;
import com.item.utils.Excel;
import com.item.utils.DateUtils;
import com.item.utils.ExcelExport;
import com.item.utils.RedisClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.web.Struts2Action;

@Action
public class BOrderAction extends Struts2Action{

    private static final long serialVersionUID = 197266703365933779L;

    @Autowired
    private BOrderService bOrderService;
    @Autowired
    private BGameService bGameService;
    @Autowired
    private BPlatformService bPlatformService;
    @Autowired
    private BGamezoneService gamezoneService;

    private Page<BOrder> bOrderPage=new Page<BOrder>(10);
    private String checkedIds;
    private BOrder bOrder;
    private Long id;
    private List<Game> games;
    private List<BPlatform> platforms;
    private List<Gamezone> gamezones;
    private String selectRange;

    private Double moneyFrom;
    private Double moneyTo;
	
    public String list(){
        MapBean mb = search();
        bOrderPage = bOrderService.page(bOrderPage, mb);
        
        games = bGameService.getGameList();
        platforms = bPlatformService.getAllPlatform();
        
        return "list";
    }
	
    public String save(){
        if(bOrder != null){
            if(bOrder.getId() != null){
                bOrderService.update(bOrder);
                addActionMessage("修改信息成功");
            }else{
                bOrderService.save(bOrder);
                addActionMessage("保存信息成功");
            }
        }
        return "save";
    }
	
    public String handle(){
        if(id != null){
            bOrder = bOrderService.get(id);
        }
        return "handle";
    }
    
    public String view(){
    	if(id != null){
            bOrder = bOrderService.get(id);
        }else{
        	addActionMessage("请选择一个ID");
        }
    	return "view";
    }
    
    public String delete(){
        if(checkedIds != null){
            bOrderService.delete(checkedIds);
            addActionMessage("删除数据成功");
        }else{
            addActionMessage("请选择要删除的数据");
        }
        return "delete";
    }
    
    public String reissue(){
    	bOrder = bOrderService.get(id);
    	if (bOrder == null) {
    		addActionMessage("不存在该订单！");
		}else if (bOrder.getStatus() != 2) {
    		addActionMessage("该订单还未支付成功,不能补发！");
		}else {
			try {
				RedisClient.lpush("rs_Order", bOrder.getOrderId());
				
				BOrder reissue = new BOrder();
				reissue.setId(id);
				reissue.setNotifyStatus(4);
				bOrderService.update(reissue);
				addActionMessage("补发成功！");
			} catch (Exception e) {
				logger.error("补发异常",e);
				addActionMessage("补发异常！");
				e.printStackTrace();
			}
		}
    	return "save";
    }
    
 	public void excelExport(){
 		try{
 			MapBean mb = search();
 			List<BOrder> list = bOrderService.list(mb);
            ExcelExport ee=new ExcelExport();
            ee.setHead("数据统计报表_"+DateUtils.format(new Date(),"yyyyMMddHHmmss"));
            ee.getEl().add("订单号");
            ee.getEl().add("cp订单号");
            ee.getEl().add("游戏名称");
            ee.getEl().add("游戏分区");
            ee.getEl().add("平台名称");
            ee.getEl().add("用户ID");
            ee.getEl().add("金额");
            ee.getEl().add("客户端类型");
            ee.getEl().add("状态");
            ee.getEl().add("通知状态");
            ee.getEl().add("创建时间");
            
            Map<String, Map<String, StateVo>> map = StateContext.getStateConfigs();
            Map<String, StateVo> orderStatus = map.get("orderStatus");
            Map<String, StateVo> orderNotifyStatus = map.get("orderNotifyStatus");
            Map<String, StateVo> clientType = map.get("clientType");
            
            for(int i=0; i<list.size(); i++){
            	List<Excel> e=new ArrayList<Excel>();
            	e.add(new Excel(list.get(i).getOrderId(),20));
 	            e.add(new Excel(list.get(i).getCpOrderId(),20));
	            e.add(new Excel(list.get(i).getAppName(),20));
	            if (StringUtils.isNotBlank(list.get(i).getZoneName())) {
	            	e.add(new Excel(list.get(i).getZoneName(),20));
				}else {
		            e.add(new Excel(list.get(i).getZoneId(),20));
				}    
	            e.add(new Excel(list.get(i).getPlatformName(),20));
	            e.add(new Excel(list.get(i).getUid(),20));
	            e.add(new Excel(DecimallFormatUtil.format((double)list.get(i).getAmount()/100),20));
	            e.add(new Excel(clientType.get(list.get(i).getClientType().toString()).getName(),20));
	            e.add(new Excel(orderStatus.get(list.get(i).getStatus().toString()).getName(),20));
	            e.add(new Excel(orderNotifyStatus.get(list.get(i).getNotifyStatus().toString()).getName(),20));
              	e.add(new Excel(DateUtils.format(list.get(i).getCreateTime(),"yyyy-MM-dd HH:mm:ss"),20));
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
        if (moneyFrom != null) {
			mb.put("moneyFrom", moneyFrom * 100);
		}
        if (moneyTo!=null) {
			mb.put("moneyTo", moneyTo * 100);
		}
        
        if(bOrder!=null){
        	if (bOrder.getId()!=null){
                mb.put("id",bOrder.getId());
            }
        	if (bOrder.getAppId()!=null){
                mb.put("appId",bOrder.getAppId());
            }
        	if (bOrder.getPlatformId()!=null){
                mb.put("platformId",bOrder.getPlatformId());
            }
        	if (bOrder.getUid()!=null&&!"".equals(bOrder.getUid())){
                mb.put("uid",bOrder.getUid());
            }
        	if (bOrder.getZoneId()!=null&&!"".equals(bOrder.getZoneId())){
                mb.put("zoneId",bOrder.getZoneId());
            }
        	if (bOrder.getRoleId()!=null&&!"".equals(bOrder.getRoleId())){
                mb.put("roleId",bOrder.getRoleId());
            }
        	if (bOrder.getRoleName()!=null&&!"".equals(bOrder.getRoleName())){
                mb.put("roleName",bOrder.getRoleName());
            }
        	if (bOrder.getOrderId()!=null&&!"".equals(bOrder.getOrderId())){
                mb.put("orderId",bOrder.getOrderId());
            }
        	if (bOrder.getCpOrderId()!=null&&!"".equals(bOrder.getCpOrderId())){
                mb.put("cpOrderId",bOrder.getCpOrderId());
            }
        	if (bOrder.getCpExtInfo()!=null&&!"".equals(bOrder.getCpExtInfo())){
                mb.put("cpExtInfo",bOrder.getCpExtInfo());
            }
        	if (bOrder.getAmount()!=null){
                mb.put("amount",bOrder.getAmount());
            }
        	if (bOrder.getNotifyUrl()!=null&&!"".equals(bOrder.getNotifyUrl())){
                mb.put("notifyUrl",bOrder.getNotifyUrl());
            }
        	if (bOrder.getFixed()!=null){
                mb.put("fixed",bOrder.getFixed());
            }
        	if (bOrder.getDeviceId()!=null&&!"".equals(bOrder.getDeviceId())){
                mb.put("deviceId",bOrder.getDeviceId());
            }
        	if (bOrder.getClientType()!=null){
                mb.put("clientType",bOrder.getClientType());
            }
        	if (bOrder.getErrorMsg()!=null&&!"".equals(bOrder.getErrorMsg())){
                mb.put("errorMsg",bOrder.getErrorMsg());
            }
        	if (bOrder.getStatus()!=null){
                mb.put("status",bOrder.getStatus());
            }
        	if (bOrder.getNotifyStatus()!=null){
                mb.put("notifyStatus",bOrder.getNotifyStatus());
            }
        	if (bOrder.getCreateTime()!=null){
                mb.put("createTime",bOrder.getCreateTime());
            }
        	if (bOrder.getUpdateTime()!=null){
                mb.put("updateTime",bOrder.getUpdateTime());
            }
        	if (StringUtils.isNotBlank(selectRange)) {
        		mb.put("statStartDate",selectRange.split("至")[0]);
        		mb.put("statEndDate",selectRange.split("至")[1]);
			}
            List<Long> ids = getAppIds();
            if (ids!=null){
                mb.put("appIds",ids) ;
            }
        }
        mb.put("orderby","id desc");
        return mb;
    }
    private List<Long> getAppIds(){
        if (bOrder.getAppName()!=null){
            MapBean mapBean = new MapBean();
            mapBean.put("appName",bOrder.getAppName());
            List<Game> gameList = bGameService.getGameByWhere(mapBean);
            if (gameList==null || gameList.size()==0){
                return null;
            }else{
                List<Long> ids = new ArrayList<Long>();
                for (int i = 0; i < gameList.size(); i++) {
                    ids.add(gameList.get(i).getId());
                }
                return ids ;
            }
        }
        return null;
    }
	public Page<BOrder> getBOrderPage(){
        return bOrderPage;
    }

    public void setCheckedIds(String checkedIds){
        this.checkedIds=checkedIds;
    }

    public BOrder getBOrder(){
        return bOrder;
    }

    public void setBOrder(BOrder bOrder){
        this.bOrder=bOrder;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
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

	public Double getMoneyFrom() {
		return moneyFrom;
	}

	public void setMoneyFrom(Double moneyFrom) {
		this.moneyFrom = moneyFrom;
	}

	public Double getMoneyTo() {
		return moneyTo;
	}
	public void setMoneyTo(Double moneyTo) {
		this.moneyTo = moneyTo;
	}

	public List<Gamezone> getGamezones() {
		return gamezones;
	}

	public void setGamezones(List<Gamezone> gamezones) {
		this.gamezones = gamezones;
	}
}