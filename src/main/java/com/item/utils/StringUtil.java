package com.item.utils;

/**
 * 
 * 字符串工具类
 * @author hshong
 * @since 
 */
public class StringUtil {
	/**
	 * 过滤特殊字符
	 * @param content 要过滤的内容
	 * @return
	 */
	public static String filtrateString(String content) {
		if (content == null) {
			content = "";
			return content;
		}
		content = content.trim();
		//content = content.trim().replaceAll("&", "&amp;");
		/*content = content.trim().replaceAll("<", "&lt;");
		content = content.trim().replaceAll(">", "&gt;");
		content = content.trim().replaceAll("\t", "    ");
		content = content.trim().replaceAll("\r\n", "\n");
		content = content.trim().replaceAll("\n", "<br/>");
		content = content.trim().replaceAll("  ", " &nbsp;");
		content = content.trim().replaceAll("'", "&#39;");
		content = content.trim().replaceAll("\\\\", "&#92;");*/
		return content;
	}
	
	
	/**
	 * 判断字符串是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = 0; i<str.length();i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isEmpty(String str){
		if(str==null || "".equals(str)){
			return true;
		}
		return false;
	}
	

	public static void main(String[] arg){

	}
}