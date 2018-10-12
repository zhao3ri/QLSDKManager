package com.item.utils.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.item.application.StateContext;
import com.item.application.StateVo;

/**
 * @author liuxh
 * @version 创建时间：2013-1-16 下午02:04:22
 *
 * 类说明：状态选择器
 *
 */
public class SelectState extends ControlBase{

	private static final long serialVersionUID = -5098966483659645332L;
	
	private String showType="select";
	private String stateType="";
	private String emptyString="--请选择--";
	private String indexSort;
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out=this.pageContext.getOut();
		try {
			Map<String, StateVo> stateVoMapNoSort=StateContext.getStateConfigs().get(stateType);
			LinkedHashMap<String, StateVo> stateVoMap = new LinkedHashMap<String, StateVo>();
			for (String key : stateVoMapNoSort.keySet()) {
				stateVoMap.put(key, stateVoMapNoSort.get(key));
			}
			if("select".equals(showType)){
				/*if (indexSort != null && !"".equals(indexSort) && indexSort.equalsIgnoreCase("sort")) {*/
					Set<String> keySet = stateVoMapNoSort.keySet();
					List<Integer> keyList = new ArrayList<Integer>();
					for (String key : keySet) {
						keyList.add(Integer.valueOf(key));
					}
					Collections.sort(keyList);
					LinkedHashMap<String, StateVo> stateVoMapTemp = new LinkedHashMap<String, StateVo>();
					for (Integer key : keyList) {
						stateVoMapTemp.put(key.toString(), stateVoMapNoSort.get(key.toString()));
					}
					stateVoMap.clear();
					for (Map.Entry<String, StateVo> entry : stateVoMapTemp.entrySet()) {
						stateVoMap.put(entry.getKey(), entry.getValue());
					}
				/*}*/
				out.print("<select "+getParameters()+">");
				if(emptyString!=null && !"".equals(emptyString)){
					out.print("<option value=''>"+emptyString+"</option>");
				}
				for(Map.Entry<String, StateVo> entry : stateVoMap.entrySet()){
					if(!"false".equals(entry.getValue().getIsshow())){
						out.print("<option value='"+entry.getKey()+"'");
						if(entry.getKey().equals(getValue())){
							out.print(" selected='selected' ");
						}
						String colorStr=entry.getValue().getColor()==null?"":" class='option-"+entry.getValue().getColor()+"'";
						out.print(colorStr+">");
						out.print(entry.getValue().getName());
						out.print("</option>");
					}
				}
				out.print("</select>");
			}else if("label".equals(showType)){
				StateVo stateVo=stateVoMap.get(getValue());
				if(stateVo==null){
					out.print("未知状态");
				}else{
					String colorStr=stateVo.getColor()==null?"":" class='label label-"+stateVo.getColor()+"'";
					out.print("<span "+colorStr+">");
					out.print(stateVo.getName());
					out.print("</span>");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getStateType() {
		return stateType;
	}

	public void setStateType(String stateType) {
		this.stateType = stateType;
	}

	public String getEmptyString() {
		return emptyString;
	}

	public void setEmptyString(String emptyString) {
		this.emptyString = emptyString;
	}

	public String getIndexSort() {
		return indexSort;
	}

	public void setIndexSort(String indexSort) {
		this.indexSort = indexSort;
	}

}
