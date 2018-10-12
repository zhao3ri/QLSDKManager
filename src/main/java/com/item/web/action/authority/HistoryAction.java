/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:ZDOP,Id:HistoryDao.java  2013-01-15 10:48:28 liuxh ]
 */
package com.item.web.action.authority;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.item.domain.authority.User;
import com.item.domain.authority.History;
import com.item.service.authority.HistoryService;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.utils.Struts2Utils;
import core.module.web.Struts2Action;

/**
 * 历史记录表 Action类.
 * <br/>
 *
 * @author liuxh
 * @version 1.0 2013-01-15 10:48:28
 * @since JDK 1.5
 */
@Action
public class HistoryAction extends Struts2Action{

    private static final long serialVersionUID = 197266703365933779L;

    @Autowired
    private HistoryService historyService;

    private Page<History> historyPage=new Page<History>(10);
    private String checkedIds;
    private History history;
    private Long id;
	
    public String listmain(){
    	MapBean mb=new MapBean();
    	mb.put("oid",((User)Struts2Utils.getSession().getAttribute("sessionUserInfo")).getId());
    	mb.put("orderby","inserttime desc");
        historyPage=historyService.page(historyPage, mb);
        return "listmain";
    }
    
    public String list(){
    	MapBean mb=search();
    	historyPage=historyService.page(historyPage, mb);
    	return "list";
    }
    
    public String list1(){
    	MapBean mb=search();
    	historyPage=historyService.page(historyPage, mb);
    	return "list1";
    }
	
    public String save(){
        if(history!=null){
            if(history.getId()!=null){
                historyService.update(history);
                addActionMessage("修改信息成功");
            }else{
                historyService.save(history);
                addActionMessage("保存信息成功");
            }
        }
        return "save";
    }
	
    public String handle(){
        if(id!=null){
            history=historyService.get(id);
        }
        return "handle";
    }
    
    public String delete(){
        if(checkedIds!=null){
            historyService.delete(checkedIds);
            addActionMessage("删除数据成功");
        }else{
            addActionMessage("请选择要删除的数据");
        }
        return "delete";
    }
    
    public String view(){
    	if(id!=null){
            history=historyService.get(id);
        }else{
        	addActionMessage("请选择一个ID");
        }
    	return "view";
    }

    private MapBean search(){
        MapBean mb=new MapBean();
        if(history!=null){
        	if (history.getId()!=null){
                mb.put("id",history.getId());
            }
        	if (history.getOid()!=null){
                mb.put("oid",history.getOid());
            }
        	if (history.getRid()!=null){
                mb.put("rid",history.getRid());
            }
        	if (history.getUniteRid()!=null&&!"".equals(history.getUniteRid())){
                mb.put("uniteRid",history.getUniteRid());
            }
        	if (history.getOmkey()!=null&&!"".equals(history.getOmkey())){
                mb.put("omkey",history.getOmkey());
            }
        	if (history.getUniteOmkey()!=null&&!"".equals(history.getUniteOmkey())){
        		if(history.getUniteOmkey().indexOf(",")!=-1){
        			mb.put("uniteOmkey",assembleOmkey(history.getUniteOmkey()));
        		}else{
        			mb.put("uniteOmkey",history.getUniteOmkey());
        		}
            }
        	if (history.getOmname()!=null&&!"".equals(history.getOmname())){
                mb.put("omname",history.getOmname());
            }
        	if (history.getOaction()!=null&&!"".equals(history.getOaction())){
                mb.put("oaction",history.getOaction());
            }
        	if (history.getOpkey()!=null&&!"".equals(history.getOpkey())){
                mb.put("opkey",history.getOpkey());
            }
        	if (history.getOpname()!=null&&!"".equals(history.getOpname())){
                mb.put("opname",history.getOpname());
            }
        	if (history.getOvalue()!=null&&!"".equals(history.getOvalue())){
                mb.put("ovalue",history.getOvalue());
            }
        	if (history.getPvalue()!=null&&!"".equals(history.getPvalue())){
                mb.put("pvalue",history.getPvalue());
            }
        	if (history.getInserttime()!=null){
                mb.put("inserttime",history.getInserttime());
            }
        	if (history.getStatStartDate()!=null&&!"".equals(history.getStatStartDate())){
                mb.put("statStartDate",history.getStatStartDate());
            }
        	if (history.getStatEndDate()!=null&&!"".equals(history.getStatEndDate())){
                mb.put("statEndDate",history.getStatEndDate());
            }
        }
        mb.put("orderby","id desc");
        return mb;
    }
    
    /**
     * 组合Omkey
     * @param uniteOmkey
     * @return
     */
    private static String assembleOmkey(String uniteOmkey){
		String newString = "";
		String[] temp = uniteOmkey.split(",");
		for(String t : temp){
			newString += "'"+t+"',";
		}
		return newString.substring(0, newString.length()-1);
    }

	public Page<History> getHistoryPage(){
        return historyPage;
    }

    public void setCheckedIds(String checkedIds){
        this.checkedIds=checkedIds;
    }

    public History getHistory(){
        return history;
    }

    public void setHistory(History history){
        this.history=history;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }
}