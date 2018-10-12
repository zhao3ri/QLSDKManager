package com.item.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @author liuxh
 * @version 创建时间：2012-4-25 下午03:35:08
 *
 * 类说明：发送Http请求工具类
 *
 */
public class HttpUtil {

	/**
	 * 不带参数的请求地址
	 * @param urlAddr
	 * @return
	 */
	public static String send(String urlAddr){
		return send(urlAddr,"");
	}
	
	/**
	 * 带参数的请求地址
	 * @param urlAddr
	 * @param params
	 * @return
	 */
	public static String send(String urlAddr,String params){
		String content = "";
		URL url = null;
		HttpURLConnection huc = null;
		try{
		    url = new URL(urlAddr);	    	    
		    huc = (HttpURLConnection) url.openConnection();   
		    huc.setRequestMethod("POST");
		    huc.setDoOutput(true);
		    huc.setDoInput(true);
		    StringBuffer param = new StringBuffer(params);  //请求URL的查询参数  
		    OutputStream os = huc.getOutputStream();  
		    os.write(param.toString().getBytes());  
		    os.flush();  
		    os.close();  
		    
		    InputStream is = huc.getInputStream();  
		    InputStreamReader inReader = new InputStreamReader(is, "UTF-8");
		    char[] charInData = new char[50];
		    int length = 0;
		    while ((length = inReader.read(charInData)) != -1) {
			   		content=content+String.valueOf(charInData, 0, length);
			}
		   
		   inReader.close();
		   return content;
		   
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
            if (huc!= null) {
                huc.disconnect();
            }
        }
	}
	
	/**
	 * 需要抛异常
	 */
	public static String sendXml(String urlstr, String xml){
		StringBuffer sb = new StringBuffer();
		HttpURLConnection conn = null;
		try {
			URL url = new URL(urlstr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setRequestMethod("POST");
			conn.addRequestProperty("Content-type", "application/xml; charset=UTF-8");
			conn.setDoInput(true);
		    conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());   
			writer.write(xml);   
			writer.flush();   
			writer.close(); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(),"UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("IOException:" + e.toString());
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return sb.toString();
	}
	
	public static String getPostString(HttpServletRequest request){
		StringBuffer sb = new StringBuffer();
		try {
			//获取request接收到的流长度，因为这里如果是使用Struts2框架，当发送方把Content-type
	        //设置成application/x-www-form-urlencoded会导致传送过来的数据流被过滤掉
	        //如果这里len不为-1，而下面的br又为空的话，说明被过滤掉了。
	        int len = request.getContentLength();
	        System.out.println("数据流长度:" +len);
	        //获取HTTP请求的输入流
	        InputStream is = request.getInputStream();
	        //已HTTP请求输入流建立一个BufferedReader对象
	        BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	        //BufferedReader br = request.getReader();
	        //读取HTTP请求内容
	        String buffer = null;
	        while ((buffer = br.readLine()) != null) {
	        //在页面中显示读取到的请求参数
	            sb.append(buffer);
	        }
		} catch (Exception e) {
			return "";
		}
		
        return sb.toString();
	}
	
	public static String post(String url, Map<String, Object> params) {
		StringBuilder sb = new StringBuilder();
		String p = "";
		if (params != null) {
			for (Entry<String, Object> e : params.entrySet()) {
				sb.append(e.getKey() + "=" + e.getValue().toString().trim() + "&");
			}
			p = sb.substring(0, sb.length() - 1);
		}
		
		HttpURLConnection con = null;
		try {
			URL u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(p.trim());
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

		StringBuilder buffer = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer.toString();
	}
}
