package com.item.utils.component;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

/**
 * @author liuxh
 * @version 创建时间：2013-10-16 上午10:37:55
 *
 * 类说明：
 *
 */
public class ModalDialog extends ControlBase{

	private static final long serialVersionUID = -5098966483659645332L;
	
	private int width;
	private String remote="";
	private String html="";
	private String role="confirm";		//alert(消息提示框),confirm(消息确认框),dialog(弹出框)
	private String title="消息提示框";
	private String type="button";		//控件形式:button(按钮),link(链接),js(脚本)
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out=this.pageContext.getOut();
		StringBuffer modalHtml=new StringBuffer();
		try {
			StringBuffer controlType=new StringBuffer();
			if("button".equals(type)){
				controlType.append("<button class=\"btn btn-primary btn-lg\" data-backdrop=\"static\" #remote# ");
				controlType.append(getName()+"</button>");
			}else if("link".equals(type)){
				controlType.append("<a data-backdrop=\"static\" style=\"cursor:pointer\" #remote# ");
				controlType.append(getName()+"</a>");
			}else if("js".equals(type)){
				controlType.append("$('#"+getId()+" .modal-body').load('"+remote+"');");
				controlType.append("$('#"+getId()+"').modal('show');");
			}
			if(!"js".equals(type)){
				if(remote!=null&&"dialog".equals(role)){
					StringBuffer remoteHtml=new StringBuffer();
					remoteHtml.append("data-load-remote=\""+remote+"\" ");
					remoteHtml.append("data-target=\"#"+getId()+"\" data-toggle=\"modal\">");
					controlType.replace(controlType.indexOf("#remote#"),controlType.indexOf("#remote#")+8, remoteHtml.toString());
				}else{
					StringBuffer remoteHtml=new StringBuffer();
					remoteHtml.append("data-target=\"#"+getId()+"\" data-toggle=\"modal\">");
					controlType.replace(controlType.indexOf("#remote#"),controlType.indexOf("#remote#")+8, remoteHtml.toString());
				}
				modalHtml.append("<script>$(document).ready(function(){$('body').append('");
			}else{
				modalHtml.append("$('body').append('");
			}
			if("dialog".equals(role)){
				modalHtml.append("<div class=\"modal fade\" id=\""+getId()+"\" tabindex=\"-1\" ");
				modalHtml.append("role=\""+role+"\" aria-labelledby=\""+getId()+"-myModalLabel\" aria-hidden=\"true\">");
				modalHtml.append("<div class=\"modal-dialog\" ");
				if(width>0){
					modalHtml.append("style=\"max-width:"+width+"px;width:100%;\"");
				}
				modalHtml.append("><div class=\"modal_align_wrap\">");
				modalHtml.append("<div class=\"modal-content\"><div class=\"modal-header\">");
				modalHtml.append("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\"><i class=\"icon-remove\"></i></button>");
				modalHtml.append("<h4 class=\"modal-title\" id=\""+getId()+"-myModalLabel\">"+title+"</h4></div>");
		        if(remote!=null&&html==null){
		        	modalHtml.append("<div class=\"modal-body\"></div>");
		        }else{
		        	modalHtml.append("<div class=\"modal-body\">"+html+"</div>");
		        }
		        modalHtml.append("</div></div></div></div>");
			}else if("confirm".equals(role)){
				modalHtml.append("<div class=\"modal fade\" id=\""+getId()+"\" tabindex=\"-1\" ");
				modalHtml.append("role=\""+role+"\" aria-labelledby=\""+getId()+"-myModalLabel\" aria-hidden=\"true\">");
				modalHtml.append("<div class=\"modal-dialog\" ");
				if(width>0){
					modalHtml.append("style=\"max-width:"+width+"px;width:100%;\"");
				}
				modalHtml.append("><div class=\"modal_align_wrap\">");
				modalHtml.append("<div class=\"modal-content\"><div class=\"modal-header\">");
				modalHtml.append("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\"><i class=\"icon-remove\"></i></button>");
				modalHtml.append("<h4 class=\"modal-title\" id=\""+getId()+"-myModalLabel\">"+title+"</h4></div>");
				modalHtml.append("<div class=\"modal-body\">"+html+"</div>");
				modalHtml.append("<div class=\"modal-footer\"><a href=\""+remote+"\" class=\"btn btn-primary\"><i class=\"icon-ok\"></i> 确定</a>");
				modalHtml.append("<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\"><i class=\"icon-remove\"></i> 取消</button></div>");
        		modalHtml.append("</div></div></div></div>");
			}else if("alert".equals(role)){
				modalHtml.append("<div class=\"modal fade\" id=\""+getId()+"\" tabindex=\"-1\" ");
				modalHtml.append("role=\""+role+"\" aria-labelledby=\""+getId()+"-myModalLabel\" aria-hidden=\"true\">");
				modalHtml.append("<div class=\"modal-dialog\" ");
				if(width>0){
					modalHtml.append("style=\"max-width:"+width+"px;width:100%;\"");
				}
				modalHtml.append("><div class=\"modal_align_wrap\">");
				modalHtml.append("<div class=\"modal-content\"><div class=\"modal-header\">");
				modalHtml.append("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\"><i class=\"icon-remove\"></i></button>");
				modalHtml.append("<h4 class=\"modal-title\" id=\""+getId()+"-myModalLabel\">"+title+"</h4></div>");
				modalHtml.append("<div class=\"modal-body\">"+html+"</div>");
				modalHtml.append("<div class=\"modal-footer\"><button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\"><i class=\"icon-ok\"></i> 确定</button></div>");
		        modalHtml.append("</div></div></div></div>");
			}
			if(!"js".equals(type)){
				modalHtml.append("');});</script>");
			}else{
				modalHtml.append("');");
			}
			out.print(modalHtml);
			out.print(controlType);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return super.doStartTag();
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getRemote() {
		return remote;
	}
	public void setRemote(String remote) {
		this.remote = remote;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
