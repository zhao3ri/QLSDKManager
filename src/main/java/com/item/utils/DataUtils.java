package com.item.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

import org.apache.log4j.Logger;


/**
 * @author liuxh
 * @version 创建时间：2012-7-12 下午03:59:43
 *
 * 类说明：数字处理工具类
 *
 */
public class DataUtils {
	private static Logger logger = Logger.getLogger("DataUtils");
	
	/**
	 * 获取不大于的最大整数（例如：1.5 取 1）
	 * @param num
	 * @return
	 */
	public static long floor(double num){
		return (long)Math.floor(num);
	}
	
	/**
	 * 获取四舍五入（例如：1.5 取 2）
	 * @param num
	 * @return
	 */
	public static int round(double num){
		return (int)Math.floor(num);
	}
	
	/**
	 * 获取不小于的最小整数（例如：1.4 取 2）
	 * @param num
	 * @return
	 */
	public static int ceil(double num){
		return (int)Math.ceil(num);
	}
	
	/**
	 * 金额保留3位小数，并且四舍五入
	 * @param num
	 * @return
	 */
	public static double money(double num){
		BigDecimal b = new BigDecimal(num);  
		return  b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();  
	}
	
	/**
	 * 金额保留3位小数，并且四舍五入
	 * @param num
	 * @return
	 */
	public static double scale(double num,int count){
		BigDecimal b = new BigDecimal(num);  
		return  b.setScale(count, BigDecimal.ROUND_HALF_UP).doubleValue();  
	}
	
	/**
	 * 两个整数相除，保留2为小数点位数
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double division(int d1,int d2){
		return division((double)(d1),d2,2);
	}
	
	/**
	 * 两个整数相除，可以控制小数点位数
	 * @param d1
	 * @param d2
	 * @param count 保留小数点位数
	 * @return
	 */
	public static double division(int d1,int d2,int count){
		return division((double)(d1),d2,count);
	}
	
	/**
	 * 两个double数相除，可以控制小数点位数
	 * @param d1
	 * @param d2
	 * @param count 保留小数点位数
	 * @return
	 */
	public static double division(double d1,double d2,int count){
		if(d2==0){
			d2=1;
		}
		return DataUtils.scale(d1/d2,count);
	}
	
	/**
	 * 两个整数相除得出一个百分比,保留2位小数位
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static String percent(int d1,int d2){
		return scale((division(d1,d2,4)*100),2)+"%";
	}
	
	public static String percentParameterDouble(double d1,double d2){
		Double scaleDoule = scale((division(d1,d2,4)*100),2);
		DecimalFormat df = new DecimalFormat();  
		df.setMaximumFractionDigits(5); // 设置最大小数位  
		String percentParameterDouble = df.format(scaleDoule);  
		return percentParameterDouble+"%";
	}
	
	/**
	 * 得出一个double数的百分比,保留2位小数位
	 * @param d1
	 * @return
	 */
	public static String percent(double d1){
		return scale(d1*100,2)+"%";
	}
	
	/**
	 * 得到N位随机数字 num就是N
	 * @param num
	 * @return
	 */
	public static String getRandomNumber(int num){
		int a[] = new int[num];
		String temp = "";
		for(int i=0;i<a.length;i++ ) {
			a[i] = (int)(10*(Math.random()));
			temp += a[i]+"";
		}
		return temp;
	}
	
	/**
	 * 得到N位随机数字+英文字母 num就是N
	 * @param num
	 * @return
	 */
	public static String getRandomString(int num){
		Random r = new Random();
		int i = 0;
		String str = "";
		String s = null;
		while (i < num) {    //这个地方的30控制产生几位随机数，这里是产生30位随机数
			switch (r.nextInt(37)) {
				case (0): s = "0"; break;
				case (1): s = "1"; break;
				case (2): s = "2"; break;
				case (3): s = "3"; break;
				case (4): s = "4"; break;
				case (5): s = "5"; break;
				case (6): s = "6"; break;
				case (7): s = "7"; break;
				case (8): s = "8"; break;
				case (9): s = "9"; break;
				case (10): s = "a"; break;
				case (11): s = "b"; break;
				case (12): s = "c"; break;
				case (13): s = "d"; break;
				case (14): s = "e"; break;
				case (15): s = "f"; break;
				case (16): s = "g"; break;
				case (17): s = "h"; break;
				case (18): s = "i"; break;
				case (19): s = "j"; break;
				case (20): s = "k"; break;
				case (21): s = "m"; break;
				case (23): s = "n"; break;
				case (24): s = "o"; break;
				case (25): s = "p"; break;
				case (26): s = "q"; break;
				case (27): s = "r"; break;
				case (28): s = "s"; break;
				case (29): s = "t"; break;
				case (30): s = "u"; break;
				case (31): s = "v"; break;
				case (32): s = "w"; break;
				case (33): s = "l"; break;
				case (34): s = "x"; break;
				case (35): s = "y"; break;
				case (36): s = "z"; break;
			}
			i++;
	   		str = s + str;
		}
		return str;
	}
	
	/**
	 * 计算运行时长
	 * @param msg
	 * @param start
	 * @param end
	 */
	public static void spends(String msg,long start,long end){
		long dis = end-start;
		if(dis>1000){
			BigDecimal b=new BigDecimal(dis);
			BigDecimal bc=new BigDecimal(1000);	
			logger.info(msg+"spends "+b.divide(bc,3,BigDecimal.ROUND_HALF_DOWN).doubleValue()+" sec.");
		}else{
			logger.info(msg+"spends "+dis+" millis.");
		}
	}
	
	/**
	 * 安全保存为INT
	 * @param str
	 * @return
	 */
	public static int safeInt(String str){
		int r = 0;
		try{
			if(str!=null && !str.trim().isEmpty())
				r = Integer.parseInt(str);
		}catch(Exception e){
			logger.error(e.getMessage()+"[ str:"+str+" to INT]",e);
			return r;
		}
		return r;
	}
	
	/**
	 * 安全保存为Long
	 * @param str
	 * @return
	 */
	public static Long safeLong(String str){
		Long r = 0L;
		try{
			if(str!=null && !str.trim().isEmpty())
				r = Long.parseLong(str);
		}catch(Exception e){
			logger.error(e.getMessage()+"[ str:"+str+" to Long]",e);
			return r;
		}
		return r;
	}
	
	/**
	 * 安全保存为Double
	 * @param str
	 * @return
	 */
	public static Double safeDouble(String str){
		Double r = 0d;
		try{
			if(str!=null && !str.trim().isEmpty())
				r = Double.parseDouble(str);
		}catch(Exception e){
			logger.error(e.getMessage()+"[ str:"+str+" to Double]",e);
			return r;
		}
		return r;
	}
	
	/**
	 * 输入一个最多15位整数位，两小数位的数字，转换成整数，这方法可以去除科学计数法
	 * @param d
	 * @return
	 */
	public static String double2String(Double d){
		try{
			DecimalFormat df =  new DecimalFormat("###############0.00");// 15位整数位，两小数位
			return  df.format(d);
		}catch(Exception e){
			logger.error("去除科学计数法 出错："+e.getMessage(),e);
		}
		return d.toString();
	}
	
	public static double subtract(Double v1,Double v2){
		 BigDecimal b1 = new BigDecimal(Double.toString(v1));
	     BigDecimal b2 = new BigDecimal(Double.toString(v2));
	     return b1.subtract(b2).doubleValue();
	}
	
	public static void main(String[] args){
//		System.out.println(percent(767,11146));
//		System.out.println(scale(0.0007*100,2));
//		System.out.println(getRandomNumber(6));
//		System.out.println(getRandomString(6));
		
//		double d = 12345123451234578d; ; // test
//		DecimalFormat df =  new DecimalFormat("###############0.00");// 15位整数位，两小数位
//		String temp  = df.format(d);
//		System.out.println(temp);
		
//		double d = 1234567891.789D;
//	    System.out.println(String.format("%.3f", d));
		
		String a = percentParameterDouble(1111111111111111111.0,1.0);
		System.out.println(a);
	}

}
