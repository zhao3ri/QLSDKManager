package com.item.utils;

import com.item.constants.CacheConstant;
import com.item.constants.TypeConstant;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author ljqiang
 * 操作枚举工具
 */
public class EnumUtil {
	
	private static Map<String,String> dTypeMap = new LinkedHashMap<String, String>();
	
	private static Map<String,String> cacheIDMap = new LinkedHashMap<String, String>();
	
	/**
	 * 检查缓存ID
	 * @param cacheName
	 * @return
	 */
	public static boolean checkCacheID(String cacheName){
		for (CacheConstant c : CacheConstant.values()) {
			if(c.name().equals(cacheName)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 把缓存ID键和值放入MAP中
	 * @return
	 */
	public static Map<String, String> getCacheIDMap() {
		for (CacheConstant c : CacheConstant.values()) {
			cacheIDMap.put(c.name(), c.getValue());
		}
		return cacheIDMap;
	}
	
	/**
	 * 检查类型
	 * @param dType
	 * @return
	 */
	public static boolean checkDType(String dType){
		for (TypeConstant c : TypeConstant.values()) {
			if(c.name().equals(dType)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 把类型键和值放入MAP中
	 * @return
	 */
	public static Map<String, String> getDTypeMap() {
		for (TypeConstant c : TypeConstant.values()) {
			dTypeMap.put(c.name(), c.getValue());
		}
		return dTypeMap;
	}
	
	public static void main(String[] args) {
		Map<String,String> s = getDTypeMap();
		for(String str : s.keySet()){
			System.out.println(str+":;"+s.get(str));
		}
	}
}
