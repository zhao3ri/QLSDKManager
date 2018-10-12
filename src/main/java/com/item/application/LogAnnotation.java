package com.item.application;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.item.utils.StringUtil;

import core.module.annotation.AttributeLog;
import core.module.annotation.EntityLog;
import core.module.annotation.LogAttribute;
import core.module.annotation.LogModule;
import core.module.utils.PackageUtil;

/**
 * @author liuxh
 * @version 创建时间：2013-1-21 下午02:35:33
 *
 * 类说明：操作日志注解初始化类
 *
 */
public class LogAnnotation {
	
	public static Map<String, EntityLog> entityLogMap = new HashMap<String, EntityLog>();
	
	public static void init(){
		initEntityLog();
	}
	
	public static void initEntityLog(){
		String packageName = "com.item.domain";
		String servicePackName = "com.item.service";
		try {
			//加载所有的注解包
			List<String> classNames = PackageUtil.getClassName(packageName, true);  
	        if (classNames != null) {  
	            for (String className : classNames) {  
	                Class<?> clazz = Class.forName(className);
	                
	                
	                boolean hasAnnotation=clazz.isAnnotationPresent(LogModule.class);
	                
	                if (hasAnnotation) {  
	                	
	                	LogModule logModule = clazz.getAnnotation(LogModule.class);  
//                        System.out.println("Test( class = " + clazz.getName()  
//                                + " , name = " + logModule.name()
//                                + " , key = " + logModule.key());
//                        if(!"advertiserWap".equals(logModule.key())){
//                        	continue;
//                        }
                        EntityLog entityLog =new EntityLog();
                        entityLog.setEntityClass(clazz);
                        String servicePath=clazz.getName().replace("domain", "service");
                        entityLog.setServiceClass(Class.forName(servicePath+"Service"));
                        entityLog.setOmkey(logModule.key());
                        entityLog.setOmname(logModule.name());
                        entityLogMap.put(clazz.getName(), entityLog);
                        
	                	//遍历方法
		                Field[] fields = clazz.getDeclaredFields();  
		                for (Field field : fields) {
		                	LogAttribute logAttribute = field.getAnnotation(LogAttribute.class);
		                	
		                	if(logAttribute!=null){
		                		AttributeLog attributeLog=new AttributeLog();
		                		if(logAttribute.primarykey()){
		                			entityLog.setPrimarykeyName(field.getName());
		                		}
		                		attributeLog.setOpkey(StringUtil.isEmpty(logAttribute.key())?field.getName():logAttribute.key());
		                		attributeLog.setOpname(StringUtil.isEmpty(logAttribute.name())?field.getName():logAttribute.name());
		                		attributeLog.setState(logAttribute.state());
		                		if(!StringUtil.isEmpty(logAttribute.rentity())){
		                			String serviceClassName="";
		                			if(logAttribute.rentity().contains("/")){
		                				serviceClassName=servicePackName+"."+logAttribute.rentity().replace("/", "")+"Service";
		                			}else{
		                				serviceClassName=servicePath.substring(0,servicePath.lastIndexOf(".")+1)+logAttribute.rentity()+"Service";
		                			}
		                			attributeLog.setRserviceClass(Class.forName(serviceClassName));
			                		attributeLog.setRentity(logAttribute.rentity());
			                		attributeLog.setRname(logAttribute.rname());
		                		}
//		                	 System.out.println("Test( method = " + field.getName()  
//		                                + " , name = " + logAttribute.name()
//		                                + " , key = " + (logAttribute.key().isEmpty()?field.getName():logAttribute.key()));
		                	 	entityLogMap.get(clazz.getName()).getAttributeLogMap().put(field.getName(), attributeLog);
		                	}
		                }
	                }
	            }  
	        }
		} catch (Exception e) {
			// 暂时啥也不做
			e.printStackTrace();
		}
	}
}
