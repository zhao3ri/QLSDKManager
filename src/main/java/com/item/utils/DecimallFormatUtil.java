package com.item.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

public class DecimallFormatUtil {

	public static String format(BigDecimal bigDecimal){
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		return decimalFormat.format(bigDecimal);
	}
	
	public static String format(double data){
		BigDecimal bigDecimal = new BigDecimal(data);
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		return decimalFormat.format(bigDecimal);
	}
	
	public static String format(BigDecimal bigDecimal,String pattern){
		if (StringUtils.isBlank(pattern)) {
			return format(bigDecimal);
		}
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		return decimalFormat.format(bigDecimal);
	}
	
	public static String format(Double data){
		BigDecimal bigDecimal = new BigDecimal(data);
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		return decimalFormat.format(bigDecimal);
	}
	
	public static void main(String[] args) {
		System.out.println(DecimallFormatUtil.format(new BigDecimal(0.50000)));
	}
}
