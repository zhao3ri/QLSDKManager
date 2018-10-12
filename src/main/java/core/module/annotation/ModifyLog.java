package core.module.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import com.item.application.LogAnnotation;
import com.item.application.StateContext;
import com.item.application.StateVo;
import com.item.domain.authority.User;
import com.item.domain.authority.History;
import com.item.service.authority.HistoryService;
import com.item.utils.DataUtils;
import com.item.utils.DateUtils;
import com.item.utils.StringUtil;

import core.module.utils.SpringUtils;
import core.module.utils.Struts2Utils;

/**
 * @author liuxh
 * @version 创建时间：2013-1-24 下午02:48:50
 *
 * 类说明：操作对象被修改的值
 *
 */
public class ModifyLog {
	
	public static void ModifyLog(Object object){
		try {
			String className=object.getClass().getName();
			className=className.contains("$$")?className.substring(0,className.indexOf("$$")):className;
			if(LogAnnotation.entityLogMap.containsKey(className)){
				EntityLog entityLog = LogAnnotation.entityLogMap.get(className);
				Long id=(Long)getValue(object, StringUtil.isEmpty(entityLog.getPrimarykeyName())?"id":entityLog.getPrimarykeyName());
				Method method =entityLog.getServiceClass().getMethod("getEntity", Long.class);
				Object entity=method.invoke(SpringUtils.getBeanByClass(entityLog.getServiceClass()),id);
				for(Map.Entry<String, AttributeLog> entry : entityLog.getAttributeLogMap().entrySet()){
					Object o = getValue(object, entry.getValue().getOpkey(),null);
					if(o!=null&&!o.equals(getValue(entity, entry.getValue().getOpkey(),null))){
						History history=new History();
						User user=(User)Struts2Utils.getSession().getAttribute("sessionUserInfo");
						history.setOid(user.getId());
						history.setRid(id);
						history.setOmkey(entityLog.getOmkey());
						history.setOmname(entityLog.getOmname());
						history.setOaction("修改");
						AttributeLog attributeLog=entityLog.getAttributeLogMap().get(entry.getValue().getOpkey());
						history.setOpkey(attributeLog.getOpkey());
						history.setOpname(attributeLog.getOpname());
						if(!StringUtil.isEmpty(attributeLog.getState())&&!StringUtil.isEmpty(attributeLog.getRentity())){
							Method rmethod =attributeLog.getRserviceClass().getMethod("get", Long.class);
							Object roentity=rmethod.invoke(SpringUtils.getBeanByClass(attributeLog.getRserviceClass()),getValue(entity, entry.getValue().getOpkey()));
							StateVo ostateVo=StateContext.getStateConfigs().get(attributeLog.getState()).get(getValue(roentity, attributeLog.getRname()).toString());
							Object rnentity=rmethod.invoke(SpringUtils.getBeanByClass(attributeLog.getRserviceClass()),getValue(object, entry.getValue().getOpkey()));
							StateVo pstateVo=StateContext.getStateConfigs().get(attributeLog.getState()).get(getValue(rnentity, attributeLog.getRname()).toString());
							history.setOvalue(ostateVo==null?"":ostateVo.getName()+"["+ostateVo.getId()+"]");
							history.setPvalue(pstateVo==null?"":pstateVo.getName()+"["+pstateVo.getId()+"]");
							
						}else if(!StringUtil.isEmpty(attributeLog.getState())){
							StateVo ostateVo=StateContext.getStateConfigs().get(attributeLog.getState()).get(getValue(entity, entry.getValue().getOpkey()).toString());
							StateVo pstateVo=StateContext.getStateConfigs().get(attributeLog.getState()).get(getValue(object, entry.getValue().getOpkey()).toString());
							history.setOvalue(ostateVo==null?"":ostateVo.getName()+"["+getValue(entity, entry.getValue().getOpkey())+"]");
							history.setPvalue(pstateVo==null?"":pstateVo.getName()+"["+getValue(object, entry.getValue().getOpkey())+"]");
						}else if(!StringUtil.isEmpty(attributeLog.getRentity())){
							Method rmethod =attributeLog.getRserviceClass().getMethod("get", Long.class);
							Object roentity=rmethod.invoke(SpringUtils.getBeanByClass(attributeLog.getRserviceClass()),getValue(entity, entry.getValue().getOpkey()));
							history.setOvalue(getValue(roentity, attributeLog.getRname())+"["+getValue(entity, entry.getValue().getOpkey())+"]");
							Object rnentity=rmethod.invoke(SpringUtils.getBeanByClass(attributeLog.getRserviceClass()),getValue(object, entry.getValue().getOpkey()));
							history.setPvalue(getValue(rnentity, attributeLog.getRname())+"["+getValue(object, entry.getValue().getOpkey())+"]");
						}else{
							history.setOvalue(getValue(entity, entry.getValue().getOpkey()).toString());
							history.setPvalue(getValue(object, entry.getValue().getOpkey()).toString());
						}
						history.setInserttime(new Date());
						((HistoryService)SpringUtils.getBeanByName("historyService")).save(history);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object getValue(Object object,String param) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		return getValue(object,param,"");
	}
	
	public static Object getValue(Object object,String param,String def) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Object result=def;
		if(object!=null&&param!=null){
			Method m=object.getClass().getMethod("get"+param.substring(0,1).toUpperCase() + param.substring(1));
			result=m.invoke(object, null);
			if(result==null){
				result=def;
			}else{
				if("Date".equals(result.getClass().getSimpleName())){
					result=DateUtils.format((Date)result);
				}else if("Double".equals(result.getClass().getSimpleName())){ // 应收账款
					result = DataUtils.double2String((Double)result);
				}
			}
		}
		return result;
	}
	
}
