package com.item.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtils {

	private DateUtils() {
	}

	public static String format(Date date) {
		try {
			return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static String format(Date date,String pattern) {
		try {
			return DateFormatUtils.format(date, pattern);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/**
	 * 日期累加
	 * 
	 * @param format
	 *            返回的日期格式
	 * @param StrDate
	 *            要累加的日期
	 * @param year
	 *            加多少年
	 * @param month
	 *            加多少个月
	 * @param day
	 *            加多少天
	 * @return
	 */
	public static String GetSysDate(String format, String StrDate, int year,
			int month, int day) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sFmt = new SimpleDateFormat(format);
		cal.setTime(sFmt.parse((StrDate), new ParsePosition(0)));

		if (day != 0) {
			cal.add(Calendar.DATE, day);
		}
		if (month != 0) {
			cal.add(Calendar.MONTH, month);
		}
		if (year != 0) {
			cal.add(Calendar.YEAR, year);
		}
		return sFmt.format(cal.getTime());
	}

	public static long getInterval(String begin, String end) {
		try {
			Date beginTime = DateUtils.parse(begin, "yyyy-MM-dd");
			Date endTime = DateUtils.parse(end, "yyyy-MM-dd");
			long day = 0;
			day = (beginTime.getTime() - endTime.getTime()) / (24 * 60 * 60 * 1000);
			return day;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static long getInterval(Date begin,Date end){
		long day = 0;
		day = (begin.getTime() - end.getTime()) / (24 * 60 * 60 * 1000);
		return day ;
	}

	public static String getDelay(String now, Integer delay, String pattern, Integer time) {
		String delayDate = null;
		try {
			Date date = parse(now, pattern);
			long delayTime = (date.getTime() / 1000) + delay * time;
			date.setTime(delayTime * 1000);
			delayDate = DateFormatUtils.format(date, pattern);

			return delayDate;
		} catch (Exception e) {
			return null;
		}
	}

	public static String getPreMonthFirst(String now, Integer delay, String pattern) {
		Calendar c = Calendar.getInstance();
		c.setTime(parse(now, pattern));
		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONTH, -1);
		
		return DateFormatUtils.format(c, "yyyy-MM-dd");
	}
	
	public static String getDelayDay(String now, Integer delay, String pattern) {
		return getDelay(now, delay, pattern, 24 * 60 * 60);
	}

	public static String getDelayHour(String now, Integer delay, String pattern) {
		return getDelay(now, delay, pattern, 60 * 60);
	}

	public static String getDelayMinute(String now, Integer delay,
			String pattern) {
		return getDelay(now, delay, pattern, 60);
	}

	public static Date parse(String date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		ParsePosition pos = new ParsePosition(0);

		return format.parse(date, pos);
	}

	public static String getWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		return DateFormatUtils.format(c, "EEEE");
	}
	// 获取前count天
	public static String getAddCountDay(int count,String day){
			
		SimpleDateFormat simpleDate2 = new SimpleDateFormat("yyyy-MM-dd");
		// 指定日期/时间分析是否不严格 ,false为严格
		simpleDate2.setLenient(false);	
		Date myDate;
		try {
			myDate = simpleDate2.parse(day);
			 Long myTime=(myDate.getTime()/1000)+60*60*24*count;
			 myDate.setTime(myTime*1000);
			 // 返回String
			 return  simpleDate2.format(myDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return day;
	}
	/**
	 * 	对比两个字符串格式的日期，返回相差日期
	 *  如果date1比date2大，返回负数。
	 * **/
	public static int contrastStringDate(String date1,String date2){
		
		   int day=0; 	   
		   
		   SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		  //String s1="2006-04-21";
		   //String s2="2006-04-25";
		   Date xxx1=new Date();
		   Date xxx2=new Date();
		  try {
			  xxx1=sf.parse(date1);
			  xxx2=sf.parse(date2);
			  day = (int) ((xxx2.getTime() - xxx1.getTime()) / 3600 / 24 / 1000);	
		  } catch (ParseException e) {
			  
			  //e.printStackTrace();
		  }
		  
		  return day;
	}
	
	/**
	 * 获取两个时间相差的分钟数
	 * @param start	开始时间
	 * @param end	结束时间
	 * @return		分钟数
	 */
	public static long hasMinute(Date start,Date end){
		try {
			System.out.println(start+"======="+end);
			long result=end.getTime()-start.getTime();
			System.out.println(end.getTime()+"========"+start.getTime());
			result = result / (1000 * 60);//分钟数
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	/**
	 * 获取某年某月的最后一天
	 * @param date 日期 形式yyyy-MM-dd
	 * @author guojt
	 * @return 
	 */
	public static String getLastDayOfMonth(String date) {
		String[] ss = date.split("-");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.valueOf(ss[0]));
		cal.set(Calendar.MONTH, Integer.valueOf(ss[1])-1);
		int maxDay = cal.getActualMaximum(Calendar.DATE);
		return ss[0] + "-" + ss[1] + "-" + maxDay;
	}
	
	/**
	 * 根据年份、周返回指定月份的日期段
	 * @param year 年
	 * @param week 周
	 * @param month 月
	 * @return
	 */
	public static String getDateByWeek(int year,int week,int month){
		String ret = "";
		Calendar c = Calendar.getInstance();
		c.set(year, 0, 1);
		int day_week = c.get(Calendar.DAY_OF_WEEK);
		//c.add(Calendar.DATE, -(day_week-1));
		c.add(Calendar.DATE, 7*week-(day_week-1));
		
		if((month - c.get(Calendar.MONTH)) != 1){
			ret += "1-";
			c.add(Calendar.DATE, 6);
			ret += c.get(Calendar.DAY_OF_MONTH);
		}else{
			int sun_week = c.get(Calendar.DAY_OF_MONTH);
			ret += sun_week + "-";
			c.add(Calendar.DATE, 6);
			int sat_week = c.get(Calendar.DAY_OF_MONTH);
			if(sat_week > sun_week){
				ret += sat_week;
			}else{
				ret += (6 - sat_week + sun_week);
			}
		}
		
		return ret;
	}
	
	/**
	 * 月份相减
	 * @author guojt
	 * @param date1 开始日期 格式yyyy-MM
	 * @param date2 结束日期 格式yyyy-MM
	 * @return 两个日期相差的月数
	 */
	 public static int getMonthInterval(String date1,String date2) {
		 try{
			 SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");
	         Calendar cal1=Calendar.getInstance();
	             cal1.setTime(fmt.parse(date1));
	         Calendar cal2=Calendar.getInstance();
	             cal2.setTime(fmt.parse(date2));
	         //年份之差 + 月份之差
	         return (cal2.get(1)-cal1.get(1))*12+(cal2.get(2)-cal1.get(2));
		 }catch (Exception e) {
			e.printStackTrace();
		 }
		 return 0;
    }
	 
	 /**
	  * 月份相加
	  * @author guojt
	  * @param date 起始日期 格式：yyyy-MM
	  * @param n 月数
	  * @return 起始日期加上n个月后的日期
	  */
	public static String getAddCountMonth(String date,int n){
		try{
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");
			Calendar c = Calendar.getInstance();
	        c.setTime(fmt.parse(date));
	        c.add(Calendar.MONTH, n);
	        return fmt.format(c.getTime());
        }catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}
	
	/**
	 * 获取两个时间相差天数
	 * @param smallDate 小的时间
	 * @param bigDate 大的时间
	 * @return
	 */
	public static int getIntervalDays(Date smallDate, Date bigDate) {
       if (null == smallDate || null == bigDate) {
           return -1;
       }
       smallDate = parse(format(smallDate, "yyyy-MM-dd"), "yyyy-MM-dd");
       bigDate = parse(format(bigDate, "yyyy-MM-dd"), "yyyy-MM-dd");
       long intervalMilli = bigDate.getTime() - smallDate.getTime();
       return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }
	
	/**
	 * 获取两个时间戳相差天数
	 * @param smallDate 小的时间
	 * @param bigDate 大的时间
	 * @return
	 */
	public static int getIntervalDays(Long smallDate, Long bigDate) {
       if (null == smallDate || null == bigDate) {
           return -1;
       }
       return getIntervalDays(new Date(smallDate), new Date(bigDate));
    }
	
	public static void main(String[] agrs){
//		System.out.println(getDateByWeek(2011,9,3));
//		System.out.println(DateUtils.getMonthInterval("2010-05-01","2011-01-09"));
//		System.out.println(DateUtils.getAddCountMonth("2010-05",3));
//		System.out.println(DateUtils.format(new Date(), "yyyyMM"));
//		System.out.println(hasMinute((new Date(2010-1900,11-1,15,10,16,0)),(new Date())));
		String d="15-12月-11 06.10.36.000000 下午";
		Date date=parse(d, "dd-MM月-yy hh.mm.ss.SS a");
		System.out.println(date);
		System.out.println(format(date,"yyyy-MM-dd HH:mm:ss"));
		System.out.println(format(new Date(),"dd-MM月-yy hh.mm.ss.SS a"));
	}
}
