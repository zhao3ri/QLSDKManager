<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" import="org.apache.struts2.ServletActionContext,core.module.orm.Page"%>
<ul class="pagination">
<%
	HttpServletRequest req = ServletActionContext.getRequest();
	//ajax分页
	String ajaxTableObj = (String)pageContext.getAttribute("ajaxTable");
	if(ajaxTableObj==null || ajaxTableObj.isEmpty()) ajaxTableObj = "ajaxTable";

  	Page<?> pageObj = null;
  	String p = (String)pageContext.getAttribute("p");
  	int pages=5;//当前显示页数必须是奇数，才能保证对称
  	int minPage=2;
  	if(p==null || p.trim().isEmpty()) p= "page";
  	pageObj = (Page<?>)req.getAttribute(p);
  	//ajax分页
  	if(pageObj.getPageNo()>pageObj.getTotalPages()){
  		pageObj.setPageNo((int)pageObj.getTotalPages());
  	}
  	if(pageObj!=null && pageObj.getResult().size()>0){
		if(pageObj.getPageNo()==1) out.print("<li class=\"disabled\"><span title=\"首页\"><i class=\"icon-step-backward\"></i></span></li>"); 
		else out.print("<li><a href=\"javascript:" + ajaxTableObj + ".jumpPage(1," + pageObj.getTotalPages() + ")\" title=\"首页\"><i class=\"icon-step-backward\"></i></a></li>");
		if(pageObj.isHasPre()) out.print("<li><a href=\"javascript:" + ajaxTableObj + ".jumpPage("+pageObj.getPrePage()+ "," + pageObj.getTotalPages() + ")\"  title=\"上一页\"><i class=\"icon-chevron-left\"></i></a></li>");
		else out.print("<li class=\"disabled\"><span title=\"上一页\"><i class=\"icon-chevron-left\"></i></span></li>");
		if(pageObj.getTotalPages()<=pages){
			for(int i=1;i<=pageObj.getTotalPages();i++){
				if(pageObj.getPageNo()==i){
					out.print("<li class=\"active\"><span>"+i+"</span></li>");
				}else{
					out.print("<li><a href=\"javascript:" + ajaxTableObj + ".jumpPage("+i+", "+pageObj.getTotalPages()+");\">"+i+"</a></li>");
				}
			}
		}else{
			int startPage=1;
			int endPage=pages;
			if(pageObj.getPageNo()<=minPage+1){
				startPage=1;
				endPage=pages;
			}else if(pageObj.getPageNo()>=pageObj.getTotalPages()-minPage){
				startPage=(int)pageObj.getTotalPages()-pages+1;
				endPage=(int)pageObj.getTotalPages();
			}else{
				startPage=pageObj.getPageNo()-minPage;
				endPage=pageObj.getPageNo()+minPage;
			}
			for(int i=startPage;i<=endPage;i++){
				if(pageObj.getPageNo()==i){
					out.print("<li class=\"active\"><span>"+i+"</span></li>");
				}else{
					out.print("<li><a href=\"javascript:" + ajaxTableObj + ".jumpPage("+i+", "+pageObj.getTotalPages()+");\">"+i+"</a></li>");
				}
			}
			if(pageObj.getPageNo()<pageObj.getTotalPages()-minPage){
				out.print("<li><span>...</span></li>");
			}
		}
		if(pageObj.isHasNext()) out.print("<li><a href=\"javascript:" + ajaxTableObj + ".jumpPage("+pageObj.getNextPage()+ "," + pageObj.getTotalPages() + ")\" title=\"下一页\"><i class=\"icon-chevron-right\"></i></a></li>"); 
		else out.print("<li class=\"disabled\"><span title=\"下一页\"><i class=\"icon-chevron-right\"></i></span></li>");
		if(pageObj.getPageNo()==pageObj.getTotalPages() || pageObj.getTotalCount()==0) out.print("<li class=\"disabled\"><span title=\"末页\"><i class=\"icon-step-forward\"></i></span></li>");
		else out.print("<li><a href=\"javascript:" + ajaxTableObj + ".jumpPage("+pageObj.getTotalPages()+"," + pageObj.getTotalPages() + ")\"  title=\"末页\"><i class=\"icon-step-forward\"></i></a></li>");
		
		out.print("<li><div  class=\"page_goto\"><div class=\"input-group\">");
		out.print("<input id=\"ajaxJumpPageNo\" type=\"text\" class=\"form-control\" placeholder=\"跳转\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" onafterpaste=\"this.value=this.value.replace(/\\D/g,'')\" onpaste=\"javascript: return false;\">");
		out.print("<span class=\"input-group-btn\">");
		out.print("<button id=\"ajaxButton\"  type=\"button\" class=\"btn  btn-primary\" title=\"跳转\" onclick=\"javascript:" + ajaxTableObj + ".jumpPage($('#ajaxJumpPageNo').val(), "+pageObj.getTotalPages()+");\"><i class=\"icon-share\"></i></button>");
        out.print("</span></div></div></li></ul>");
        
        
        out.print("<span class=\"pull-right page_num_info badge\">共"+pageObj.getTotalPages()+"页，共"+pageObj.getTotalCount()+"条记录</span> </div>");        
        
        out.print("<input type=\"hidden\" name=\""+p+".pageNo\" id=\"pageNo\" value="+pageObj.getPageNo()+" />");
		out.print("<input type=\"hidden\" name=\""+p+".pageSize\" id=\"pageSize\" value="+pageObj.getPageSize()+" />");
		if(pageObj.getOrderBy()!=null) 
			out.print("<input type=\"hidden\" name=\""+p+".orderBy\" id=\"orderBy\" value="+pageObj.getOrderBy()+" />");
		if(pageObj.getOrder()!=null) 
			out.print("<input type=\"hidden\" name=\""+p+".order\" id=\"order\" value="+pageObj.getOrder()+" />");
		
		
	}
%>
