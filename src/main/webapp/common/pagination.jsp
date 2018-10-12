<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" import="org.apache.struts2.ServletActionContext,core.module.orm.Page"%>
<ul class="pagination">
<%
	HttpServletRequest req = ServletActionContext.getRequest();
  	Page<?> pageObj = null;
  	String p = (String)pageContext.getAttribute("p");
  	int pages=5;//当前显示页数必须是奇数，才能保证对称
  	int minPage=2;
  	if(p==null || p.trim().isEmpty()) p= "page";
  	pageObj = (Page<?>)req.getAttribute(p);
  	if(pageObj!=null && pageObj.getResult().size()>0){
		if(pageObj.getPageNo()==1) out.print("<li class=\"disabled\"><span title=\"首页\"><i class=\"icon-step-backward\"></i></span></li>"); 
		else out.print("<li><a href=\"javascript:jumpPage(1)\" title=\"首页\"><i class=\"icon-step-backward\"></i></a></li>");
		if(pageObj.isHasPre()) out.print("<li><a href=\"javascript:jumpPage("+pageObj.getPrePage()+")\"  title=\"上一页\"><i class=\"icon-chevron-left\"></i></a></li>");
		else out.print("<li class=\"disabled\"><span title=\"上一页\"><i class=\"icon-chevron-left\"></i></span></li>");
		if(pageObj.getTotalPages()<=pages){
			for(int i=1;i<=pageObj.getTotalPages();i++){
				if(pageObj.getPageNo()==i){
					out.print("<li class=\"active\"><span>"+i+"</span></li>");
				}else{
					out.print("<li><a href=\"javascript:jumpPage("+i+", "+pageObj.getTotalPages()+");\">"+i+"</a></li>");
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
					out.print("<li><a href=\"javascript:jumpPage("+i+", "+pageObj.getTotalPages()+");\">"+i+"</a></li>");
				}
			}
			if(pageObj.getPageNo()<pageObj.getTotalPages()-minPage){
				out.print("<li><span>...</span></li>");
			}
		}
		if(pageObj.isHasNext()) out.print("<li><a href=\"javascript:jumpPage("+pageObj.getNextPage()+")\" title=\"下一页\"><i class=\"icon-chevron-right\"></i></a></li>"); 
		else out.print("<li class=\"disabled\"><span title=\"下一页\"><i class=\"icon-chevron-right\"></i></span></li>");
		if(pageObj.getPageNo()==pageObj.getTotalPages() || pageObj.getTotalCount()==0) out.print("<li class=\"disabled\"><span title=\"末页\"><i class=\"icon-step-forward\"></i></span></li>");
		else out.print("<li><a href=\"javascript:jumpPage("+pageObj.getTotalPages()+")\"  title=\"末页\"><i class=\"icon-step-forward\"></i></a></li>");
		
		out.print("<li><div  class=\"page_goto\"><div class=\"input-group\">");
		out.print("<input id=\"jumpPageNo\" type=\"text\" class=\"form-control\" placeholder=\"跳转\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" onafterpaste=\"this.value=this.value.replace(/\\D/g,'')\" onpaste=\"javascript: return false;\">");
		out.print("<span class=\"input-group-btn\">");
		out.print("<button  type=\"button\" class=\"btn  btn-primary\" title=\"跳转\" onclick=\"jumpPage($('#jumpPageNo').val(), "+pageObj.getTotalPages()+");\"><i class=\"icon-share\"></i></button>");
        out.print("</span></div></div></li>");
        //设置每页的条数
        out.print("<li><div class=\"page_onepage_num dropup\"><div class=\"btn-group pull-right \">");
        out.print("<button type=\"button\" class=\"btn btn-info  dropdown-toggle\" data-toggle=\"dropdown\">每页"+pageObj.getPageSize()+"条 <span class=\"caret\"></span></button>");
        out.print("<ul class=\"dropdown-menu text-left\" role=\"menu\">");
        out.print("<li><a href=\"javascript:setPageSize(5)\">每页5条</a></li>");
        out.print("<li><a href=\"javascript:setPageSize(10)\">每页10条</a></li>");
        out.print("<li><a href=\"javascript:setPageSize(20)\">每页20条</a></li>");
        out.print("<li><a href=\"javascript:setPageSize(30)\">每页30条</a></li>");
        out.print("<li><a href=\"javascript:setPageSize(40)\">每页40条</a></li>");
        out.print("<li><a href=\"javascript:setPageSize(50)\">每页50条</a></li>");
        out.print("<li><a href=\"javascript:setPageSize(100)\">每页100条</a></li>");
      	out.print("</ul></div></div></li></ul>");
        
        out.print("<span class=\"pull-right page_num_info badge\">共"+pageObj.getTotalPages()+"页，共"+pageObj.getTotalCount()+"条记录</span> </div>");
        
        out.print("<input type=\"hidden\" name=\""+p+".pageNo\" id=\"pageNo\" value="+pageObj.getPageNo()+" />");
		out.print("<input type=\"hidden\" name=\""+p+".pageSize\" id=\"pageSize\" value="+pageObj.getPageSize()+" />");
		if(pageObj.getOrderBy()!=null) 
			out.print("<input type=\"hidden\" name=\""+p+".orderBy\" id=\"orderBy\" value="+pageObj.getOrderBy()+" />");
		if(pageObj.getOrder()!=null) 
			out.print("<input type=\"hidden\" name=\""+p+".order\" id=\"order\" value="+pageObj.getOrder()+" />");
	}
%>
</ul>
