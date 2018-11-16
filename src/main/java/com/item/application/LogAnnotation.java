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
 * <p>
 * 类说明：操作日志注解初始化类
 */
public class LogAnnotation {
    private static final String DOMAIN_PACKAGE_NAME = "com.item.domain";
    private static final String SERVICE_PACKAGE_NAME = "com.item.service";
    public static Map<String, EntityLog> entityLogMap = new HashMap<String, EntityLog>();

    public static void init() {
        initEntityLog();
    }

    public static void initEntityLog() {

        try {
            //加载所有的注解包
            List<String> classNames = PackageUtil.getClassName(DOMAIN_PACKAGE_NAME, true);
            if (classNames != null) {
                for (String className : classNames) {
                    Class<?> clazz = Class.forName(className);


                    boolean hasAnnotation = clazz.isAnnotationPresent(LogModule.class);

                    if (hasAnnotation) {

                        LogModule logModule = clazz.getAnnotation(LogModule.class);
//                        System.out.println("Test( class = " + clazz.getName()  
//                                + " , name = " + logModule.name()
//                                + " , key = " + logModule.key());
//                        if(!"advertiserWap".equals(logModule.key())){
//                        	continue;
//                        }
                        EntityLog entityLog = new EntityLog();
                        entityLog.setEntityClass(clazz);
                        String servicePath = clazz.getName().replace("domain", "service");
                        entityLog.setServiceClass(Class.forName(servicePath + "Service"));
                        entityLog.setOmkey(logModule.key());
                        entityLog.setOmname(logModule.name());
                        entityLogMap.put(clazz.getName(), entityLog);
                        System.out.println("class == " + className);
                        System.out.println("service == " + servicePath + "Service");

                        //遍历方法
                        Field[] fields = clazz.getDeclaredFields();
                        for (Field field : fields) {
                            LogAttribute logAttribute = field.getAnnotation(LogAttribute.class);

                            if (logAttribute != null) {
                                AttributeLog attributeLog = new AttributeLog();
                                if (logAttribute.primarykey()) {
                                    entityLog.setPrimarykeyName(field.getName());
                                }
                                attributeLog.setOpkey(StringUtil.isEmpty(logAttribute.key()) ? field.getName() : logAttribute.key());
                                attributeLog.setOpname(StringUtil.isEmpty(logAttribute.name()) ? field.getName() : logAttribute.name());
                                attributeLog.setState(logAttribute.state());
                                if (!StringUtil.isEmpty(logAttribute.rentity())) {
                                    String serviceClassName = "";
                                    if (logAttribute.rentity().contains("/")) {
                                        serviceClassName = SERVICE_PACKAGE_NAME + "." + logAttribute.rentity().replace("/", "") + "Service";
                                    } else {
                                        serviceClassName = servicePath.substring(0, servicePath.lastIndexOf(".") + 1) + logAttribute.rentity() + "Service";
                                    }
                                    attributeLog.setRserviceClass(Class.forName(serviceClassName));
                                    System.out.println("className == " + serviceClassName);
                                    attributeLog.setRentity(logAttribute.rentity());
                                    attributeLog.setRname(logAttribute.rname());
                                }
//                                System.out.println("Test( method = " + field.getName()
//                                        + " , name = " + logAttribute.name()
//                                        + " , key = " + (logAttribute.key().isEmpty() ? field.getName() : logAttribute.key()));
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
