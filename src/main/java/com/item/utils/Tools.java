package com.item.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class Tools {

	/**
	 * 根据浏览器的不同，下载文件名的中文的转换
	 * Date: 2010-10-14
	 * 
	 * @author liuxh
	 * @param filename
	 * @return
	 */
	public static String saveFileName(String filename){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String userAgent = request.getHeader("user-agent");

			try {
				String new_filename = new String(filename.getBytes("UTF-8"),"ISO8859-1");
			
			    // 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的  
			    String rtn = new_filename;  
			    if (userAgent != null)  
			    {  
			        userAgent = userAgent.toLowerCase();  
			        // IE浏览器，只能采用URLEncoder编码  
			        if (userAgent.indexOf("msie") != -1)  
			        {  
			           rtn = URLEncoder.encode(filename, "UTF8");
			       }  
			       // Opera浏览器只能采用filename*  
			       else if (userAgent.indexOf("opera") != -1)  
			       {  
			           rtn = "*=UTF-8''" + URLEncoder.encode(filename, "UTF8");  
			       }  
			       // Safari浏览器，只能采用ISO编码的中文输出  
			       else if (userAgent.indexOf("safari") != -1 )  
			       {  
			           rtn = new String(filename.getBytes("UTF-8"),"ISO8859-1");  
			       }  
			       // Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出  
//			       else if (userAgent.indexOf("applewebkit") != -1 )  
//			       {  
//			           new_filename = MimeUtility.encodeText(filename, "UTF8", "B");  
//			           rtn =  new_filename ;  
//			       }  
			       // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出  
			       else if (userAgent.indexOf("mozilla") != -1)  
			       {  
			           rtn = new String(filename.getBytes("UTF-8"),"ISO8859-1");  
			       }  
			        return rtn;
			    }  
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return null;
	}
	
	private static Pattern tokenSplitPattern = Pattern.compile(",");
	public static Set<String> str2set(String str){
		Set<String> set = new HashSet<String>();
		if(str!=null && !str.trim().isEmpty()){
			if(str.contains(",")){
				String[] entry = tokenSplitPattern.split(str);
				for(String id : entry){
					if(id.trim().isEmpty()) continue;
					set.add(id.trim());
				}
			}else{
				set.add(str.trim());
			}
				
		}
		return set;
	}
}
