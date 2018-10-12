package core.module.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.item.utils.JsonUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;

/**
 * Struts2 Utils类.
 * 
 * 实现获取Request/Response/Session与绕过jsp/freemaker直接输出文本的简化函数.
 * 
 * @author yjli
 * @since 2010-03-25
 */
public class Struts2Utils {

	// header 常量定义
	private static final String ENCODING_PREFIX = "encoding";
	private static final String NOCACHE_PREFIX = "no-cache";
	private static final String ENCODING_DEFAULT = "UTF-8";
	private static final boolean NOCACHE_DEFAULT = true;

	// content-type 常量定义
	private static final String TEXT_TYPE = "text/plain";
	private static final String JSON_TYPE = "application/json";
	private static final String XML_TYPE = "text/xml";
	private static final String HTML_TYPE = "text/html";

	private static Logger logger = LoggerFactory.getLogger(Struts2Utils.class);

	// 取得Request/Response/Session的简化函数
	/**
	 * 取得HttpSession的简化函数.
	 */
	public static ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	// 取得Request/Response/Session的简化函数
	/**
	 * 取得HttpSession的简化函数.
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	/**
	 * 根据版本标识值，返回相应的映射名称
	 * @return
	 */
//	public static String getVersionReturnStr(String mapping) {
//		String result="";
//		Object obj=getSession().getAttribute(GlobalParameter.SESSION_VERSION);
//		if(obj==null)
//		{  
//			result="wap2_"+mapping;
//		}
//		else
//		{
//			if("1".equals(obj.toString()))
//			{
//				result="wap1_"+mapping;
//			}else if("2".equals(obj.toString()))
//			{
//				result="wap2_"+mapping;
//			}else
//			{
//				result="wap2_"+mapping;
//			}
//		}
//		return result;
//	}

	/**
	 * 取得HttpRequest的简化函数.
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 取得HttpResponse的简化函数.
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 取得Request Parameter的简化方法.
	 */
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	/**
	 * 存储变量的简化函数.
	 */
	public static Map<String, Object> getContext() {
		return ActionContext.getContext().getSession();
	}

	/**
	 * 存储键值对的简化方法.
	 */
	public static void put(String key, Object value) {
		ServletActionContext.getContext().getContextMap().put(key, value);
	}

	// 绕过jsp/freemaker直接输出文本的函数
	/**
	 * 直接输出内容的简便函数.
	 * 
	 * eg. render("text/plain", "hello", "encoding:GBK"); render("text/plain",
	 * "hello", "no-cache:false"); render("text/plain", "hello", "encoding:GBK",
	 * "no-cache:false");
	 * 
	 * @param headers
	 *            可变的header数组, 目前接受的值为"encoding:"或"no-cache:", 默认值分别为UTF-8和true.
	 */
	public static void render(final String contentType, final String content,
			final String... headers) {
		try {
			// 分析headers参数
			String encoding = ENCODING_DEFAULT;
			boolean noCache = NOCACHE_DEFAULT;
			for (String header : headers) {
				String headerName = StringUtils.substringBefore(header, ":");
				String headerValue = StringUtils.substringAfter(header, ":");

				if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
					encoding = headerValue;
				} else if (StringUtils.equalsIgnoreCase(headerName,
						NOCACHE_PREFIX)) {
					noCache = Boolean.parseBoolean(headerValue);
				} else
					throw new IllegalArgumentException(headerName
							+ "不是一个合法的header类型");
			}

			HttpServletResponse response = ServletActionContext.getResponse();

			// 设置headers参数
			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);
			response.getWriter().flush();

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出文本.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final String text, final String... headers) {
		render(TEXT_TYPE, text, headers);
	}

	/**
	 * 直接输出HTML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final String html, final String... headers) {
		render(HTML_TYPE, html, headers);
	}

	/**
	 * 直接输出XML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final String xml, final String... headers) {
		render(XML_TYPE, xml, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param jsonString
	 *            json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final String jsonString,
			final String... headers) {
		render(JSON_TYPE, jsonString, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param map
	 *            Map对象, 将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(@SuppressWarnings("rawtypes") final Map map, final String... headers) {
		String jsonString = JsonUtil.toJsonString(map);
		render(JSON_TYPE, jsonString, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param object
	 *            Java对象, 将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Object object, final String... headers) {
		String jsonString = JsonUtil.toJsonString(object);
		render(JSON_TYPE, jsonString, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param collection
	 *            Java对象集合, 将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Collection<?> collection,
			final String... headers) {
		String jsonString = JsonUtil.toJsonString(collection);
		render(JSON_TYPE, jsonString, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param array
	 *            Java对象数组, 将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Object[] array, final String... headers) {
		String jsonString = JsonUtil.toJsonString(array);
		render(JSON_TYPE, jsonString, headers);
	}

	/**
	 * 获取请求参数,为空时设置默认值
	 * 
	 * @param parameter-请求参数
	 * @param value-为空时默认的值
	 * 
	 */
	public static String getParameter(String parameter, String value) {
		Object temp = getRequest().getParameter(parameter);
		if (temp != null && !temp.toString().equals("")) {
			return temp.toString();
		} else
			return value;
	}

	/**
	 * 通过正则表达式验证参数
	 * 
	 * @param regEx-请求参数
	 * @param str-验证的字符串
	 * 
	 */
	public static Boolean regExpress(String regEx, String str) {
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);

		return m.find();
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date-日期
	 * @param exp-格式化样式 如('yyyy-MM-dd HH-mm:ss:ms')
	 * 
	 */
	public static String dateFormat(Date date, String exp) {
		SimpleDateFormat format=new SimpleDateFormat(exp);
		return format.format(date);
	}
	
	/**
	 * 从request中获取ActionMapping,若没有则返回一个空的ActionMapping
	 * 
	 * @return ActionMapping
	 */
	public static ActionMapping getActionMapping() {
		Object obj = getRequest().getAttribute("struts.actionMapping");
		if(obj != null){
			return (ActionMapping) obj;
		}
		return new ActionMapping();
	}

	public static void main(String[] args){
		System.out.println(regExpress("^[0-9]{11}$", "25255475157"));
	}
	
}