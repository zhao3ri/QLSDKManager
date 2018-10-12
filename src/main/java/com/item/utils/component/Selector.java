package com.item.utils.component;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.item.utils.StringUtil;

/**
 * @author liuxh
 * @version 创建时间：2013-1-28 下午05:47:51
 *
 * 类说明：选择器组件
 *
 */
public class Selector extends ControlBase{

	private static final long serialVersionUID = -5098966483659645332L;
	
	private String title;					//显示文字
	private String selectorNodes;			//传入的选择项数组对象,必须传入,格式为{name:"名称",pId:"父id",id:"id",open:true//节点是否展开}
	private String initSelectedNodes="";	//传入的默认已选择项,可为空
	private String selectiveRule="";		//传入选择规则函数,可以为空,使用ztree方法遍历树对象treeObj与selectedTreeObj
	private String maxSelected="1";			//限制选择的个数,默认为0,0为无限制,如果有限制数量,则会对初始化已选择项数量有影响
	private String isSelectedNode="0";		//是否开启父节点选择,1可以选择,0为不可选择,默认为0
	private String isSelectNull="1";		//是否允许选择为空,1为允许,0为不允许,默认为0
	private String isShow="0";				//选择框是否为显示,1为显示,0为隐藏,默认为0
	private String selectorTitle="资源选择器";//selector标题
	private String callback="";				//存放返回值的回调函数,这里需要自定义函数,可为空
	private String callBackId="returnId";	//返回id给文本框
	private String callBackName="returnName";//返回name给文本框
	
	private String selectType;		//选择器选择类型：user(用户),media(媒体),advertiser(广告主)
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out=this.pageContext.getOut();
		try {
			init();
			out.print("<a href='javascript:void(0)' "+getParameters()+">"+title+"</a>");
			out.print("<script>$(document).ready(function(){$.fn.mySelector({");
			out.print("showSelectorBtnId:'"+getId()+"',");
			out.print("selectorNodes:"+selectorNodes+",");
			out.print("initSelectedNodes:'"+initSelectedNodes+"',");
			out.print("selectiveRule:'"+selectiveRule+"',");
			out.print("maxSelected:"+maxSelected+",");
			out.print("isSelectedNode:"+isSelectedNode+",");
			out.print("isSelectNull:"+isSelectNull+",");
			out.print("isShow:"+isShow+",");
			out.print("selectorTitle:'"+selectorTitle+"',");
			out.print("callback:"+(StringUtil.isEmpty(callback)?"''":callback)+",");
			out.print("callBackId:'"+callBackId+"',");
			out.print("callBackName:'"+callBackName+"'");
			out.print("});});</script>");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
	
	private void init(){
		if(!StringUtil.isEmpty(selectType)){
			Map<String, String> selectNodeMap=new HashMap<String, String>(); 
//			if("user".equals(selectType)){		//员工选择器数据
//				MapBean mb=new MapBean();
//				mb.put("state", 0);
//				mb.put("orderby", "realName asc,createTime asc");
//				List<User> userList=((UserService)SpringUtils.getBeanByName("userService")).list(mb);
//				for(User user : userList){
//					if(!selectNodeMap.containsKey("d"+user.getDid())){
//						Dictionary dictionary=((DictionaryService)SpringUtils.getBeanByName("dictionaryService")).get(user.getDid());
//						selectNodeMap.put("d"+user.getDid(), "{name:'"+dictionary.getDname()+"',pId:'',id:'d"+user.getDid()+"',open:true}");
//					}
//					if(user.getGid()!=null){
//						if(!selectNodeMap.containsKey("g"+user.getGid())){
//							Dictionary dictionary=((DictionaryService)SpringUtils.getBeanByName("dictionaryService")).get(user.getGid());
//							selectNodeMap.put("g"+user.getGid(), "{name:'"+dictionary.getDname()+"',pId:'d"+user.getDid()+"',id:'g"+user.getGid()+"'}");
//						}
//						selectNodeMap.put(user.getId().toString(), "{name:'"+user.getRealName()+"',pId:'g"+user.getGid()+"',id:'"+user.getId()+"'}");
//					}else{
//						selectNodeMap.put(user.getId().toString(), "{name:'"+user.getRealName()+"',pId:'d"+user.getDid()+"',id:'"+user.getId()+"'}");
//					}
//				}
//			}else if("media".equals(selectType)){		//媒体选择数据
//				MapBean mb=new MapBean();
//				mb.put("state", 1);
//				mb.put("orderby", "channelno asc,inserttime asc");
//				List<Mediator> mediatorList=((MediatorService)SpringUtils.getBeanByName("mediatorService")).list(mb);
//				for(Mediator mediator : mediatorList){
//					if(!selectNodeMap.containsKey("ms"+mediator.getMsid())){
//						Dictionary dictionary=((DictionaryService)SpringUtils.getBeanByName("dictionaryService")).get(mediator.getMsid());
//						selectNodeMap.put("ms"+mediator.getMsid(), "{name:'"+dictionary.getDname()+"',pId:'',id:'ms"+mediator.getMsid()+"',open:true}");
//					}
//					if(!selectNodeMap.containsKey("mt"+mediator.getMtid())){
//						Dictionary dictionary=((DictionaryService)SpringUtils.getBeanByName("dictionaryService")).get(mediator.getMtid());
//						selectNodeMap.put("mt"+mediator.getMtid(), "{name:'"+dictionary.getDname()+"',pId:'ms"+mediator.getMsid()+"',id:'mt"+mediator.getMtid()+"'}");
//					}
//					selectNodeMap.put(mediator.getId().toString(), "{name:'"+mediator.getMname()+"',pId:'mt"+mediator.getMtid()+"',id:'"+mediator.getId()+"'}");
//				}
//			}else if("advertiser".equals(selectType)){
//				MapBean mb=new MapBean();
//				mb.put("state", 1);
//				mb.put("orderby", "aname asc,inserttime asc");
//				List<Advertiser> advertiserList=((AdvertiserService)SpringUtils.getBeanByName("advertiserService")).list(mb);
//				for(Advertiser advertiser : advertiserList){
//					if(!selectNodeMap.containsKey("at"+advertiser.getAtid())){
//						Dictionary dictionary=((DictionaryService)SpringUtils.getBeanByName("dictionaryService")).get(advertiser.getAtid());
//						selectNodeMap.put("at"+advertiser.getAtid(), "{name:'"+dictionary.getDname()+"',pId:'',id:'at"+advertiser.getAtid()+"',open:true}");
//					}
//					selectNodeMap.put(advertiser.getId().toString(), "{name:'"+advertiser.getAname()+"',pId:'at"+advertiser.getAtid()+"',id:'"+advertiser.getId()+"'}");
//				}
//			}
			selectorNodes="[";
			for(Map.Entry<String, String> entry : selectNodeMap.entrySet()){
				selectorNodes+=entry.getValue()+",";
			}
			if(selectorNodes.endsWith(",")){
				selectorNodes=selectorNodes.substring(0, selectorNodes.length()-1);
			}
			selectorNodes+="]";
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSelectorNodes() {
		return selectorNodes;
	}

	public void setSelectorNodes(String selectorNodes) {
		this.selectorNodes = selectorNodes;
	}

	public String getInitSelectedNodes() {
		return initSelectedNodes;
	}

	public void setInitSelectedNodes(String initSelectedNodes) {
		this.initSelectedNodes = initSelectedNodes;
	}

	public String getSelectiveRule() {
		return selectiveRule;
	}

	public void setSelectiveRule(String selectiveRule) {
		this.selectiveRule = selectiveRule;
	}

	public String getMaxSelected() {
		return maxSelected;
	}

	public void setMaxSelected(String maxSelected) {
		this.maxSelected = maxSelected;
	}

	public String getIsSelectedNode() {
		return isSelectedNode;
	}

	public void setIsSelectedNode(String isSelectedNode) {
		this.isSelectedNode = isSelectedNode;
	}

	public String getIsSelectNull() {
		return isSelectNull;
	}

	public void setIsSelectNull(String isSelectNull) {
		this.isSelectNull = isSelectNull;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getSelectorTitle() {
		return selectorTitle;
	}

	public void setSelectorTitle(String selectorTitle) {
		this.selectorTitle = selectorTitle;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getCallBackId() {
		return callBackId;
	}

	public void setCallBackId(String callBackId) {
		this.callBackId = callBackId;
	}

	public String getCallBackName() {
		return callBackName;
	}

	public void setCallBackName(String callBackName) {
		this.callBackName = callBackName;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}
}
