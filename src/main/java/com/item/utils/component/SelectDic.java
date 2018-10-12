package com.item.utils.component;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.item.domain.authority.Dictionary;
import com.item.service.authority.DictionaryService;

import core.module.orm.MapBean;
import core.module.utils.SpringUtils;

/**
 * @author liuxh
 * @version 创建时间：2013-1-15 下午05:45:46
 *
 * 类说明：数据字典选择器
 *
 */
public class SelectDic extends ControlBase{

	private static final long serialVersionUID = 5211750023825883463L;

	private String dtype="";	//数据字典类型
	private String dvalue;	//数据字典值
	private String showType="select";//展示形式
	private String dicID;	//数据字典ID
	private String blankName="未知数据";//没有数据的返回字符串
	private String emptyString="--请选择--";
	
	@SuppressWarnings("static-access")
	@Override
	public int doStartTag() throws JspException {
		JspWriter out=this.pageContext.getOut();
		try {
			MapBean mb=new MapBean();
			if("select".equals(showType)){
				if(dtype!=null&&!"".equals(dtype.trim())){
					mb.put("dtype", dtype);
				}
				if(dvalue!=null&&!"".equals(dvalue.trim())){
					mb.put("dvalue", dvalue);
				}
				mb.put("state", 1);
				mb.put("orderby", "dsort desc");
				List<Dictionary> dictionarieList=((DictionaryService)SpringUtils.getBeanByName("dictionaryService")).list(mb);
				out.print("<select "+getParameters()+">");
				String key="";
				out.print("<option value=''>"+emptyString+"</option>");
				for(Dictionary d : dictionarieList){
					key=d.getDvalue()==null?"":("key='"+d.getDvalue()+"'");
					out.print("<option value='"+d.getId()+"' "+key);
					if(d.getId().toString().equals(getValue())){
						out.print(" selected='selected' ");
					}
					out.print(">");
					out.print(d.getDname());
					out.print("</option>");
				}
				out.print("</select>");
			}else if("label".equals(showType)){
				String result=blankName;
				if(dicID!=null&&!"".equals(dicID.trim())){
					try {
						Dictionary dictionary=((DictionaryService)SpringUtils.getBeanByName("dictionaryService")).get(Long.parseLong(dicID));
						if(dictionary!=null){
							result=dictionary.getDname();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				out.print(result);
			}else if("radio".equals(showType)){
				if(dtype!=null&&!"".equals(dtype.trim())){
					mb.put("dtype", dtype);
				}
				if(dvalue!=null&&!"".equals(dvalue.trim())){
					mb.put("dvalue", dvalue);
				}
				mb.put("state", 1);
				mb.put("orderby", "dsort desc");
				List<Dictionary> dictionarieList=((DictionaryService)SpringUtils.getBeanByName("dictionaryService")).list(mb);
				String key="";
				for(Dictionary d : dictionarieList){
					key=d.getDvalue()==null?"":("key='"+d.getDvalue()+"'");
					out.print("<input type='radio' "+getParameters()+" value='"+d.getId()+"' "+key);
					if(d.getId().toString().equals(getValue())){
						out.print(" checked ");
					}
					out.print("/>"+d.getDname());
				}
			}else if("eq".equals(showType)){
				if(dicID!=null&&!"".equals(dicID.trim())){
					try {
						Dictionary dictionary=((DictionaryService)SpringUtils.getBeanByName("dictionaryService")).get(Long.parseLong(dicID));
						if(dictionary!=null){
							return (dictionary.getDvalue().equals(dvalue))?super.EVAL_BODY_INCLUDE:super.SKIP_BODY;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			return super.SKIP_BODY;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getDvalue() {
		return dvalue;
	}

	public void setDvalue(String dvalue) {
		this.dvalue = dvalue;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getDicID() {
		return dicID;
	}

	public void setDicID(String dicID) {
		this.dicID = dicID;
	}

	public String getBlankName() {
		return blankName;
	}

	public void setBlankName(String blankName) {
		this.blankName = blankName;
	}

	public String getEmptyString() {
		return emptyString;
	}

	public void setEmptyString(String emptyString) {
		this.emptyString = emptyString;
	}
}
