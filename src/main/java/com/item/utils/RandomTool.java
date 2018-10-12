package com.item.utils;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @author hezb
 * @version 2013-3-9 下午3:50:01
 * 类说明 
 **/
public class RandomTool {
	//时间格式
	final static String DATE_FORMAT = "yyMMdd";
	
	/**
	 * 生成MerchantId（10位）
	 * @return
	 */
	public static String randomMerchantId(){
		return DateFormatUtils.format(new Date(), DATE_FORMAT)+RandomStringUtils.randomNumeric(4);
	}
	
	/**
	 * 生成AppSpreadChannelCode（11位）
	 * @return
	 */
	public static String randomAppSpreadChannelCode(){
		return DateFormatUtils.format(new Date(), DATE_FORMAT)+RandomStringUtils.randomNumeric(5);
	}
	
	/**
	 * 生成AppId（12位）
	 * @return
	 */
	public static String randomAppId(){
		return DateFormatUtils.format(new Date(), DATE_FORMAT)+RandomStringUtils.randomNumeric(6);
	}
	
	/**
	 * 生成SecretKey（32位）
	 * @return
	 */
	public static String randomSecretKey(){
		return RandomStringUtils.randomAlphanumeric(32).toLowerCase();
	}
	
	public static void main(String[] args) {
		System.out.println(randomSecretKey());
	}
}
