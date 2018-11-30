<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<%
java.util.Random C=new java.util.Random();
int rt = C.nextInt();
pageContext.setAttribute("r", rt);
%>
<body class="fix_top_nav_padding">
	<div id="wrap">
	<%@ include file="/common/header.jsp" %>
	<div class="container">
	    <ol class="breadcrumb row">
			<li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
	      	<li class="active">系统管理</li>
	      	<li class="active">操作历史记录列表</li>
	    </ol>
		<form identity="form" action="history_list1.shtml" method="post" id="mainForm">
		<input type="hidden" id="oid" name="history.oid" value="${request.session.sessionUserInfo.id }"/>
	    <div class="panel panel-default">
	      	<div class="panel-heading">操作历史记录信息查询 </div>
	      	<div class="form-inline popover-show panel-body list_toolbar">
				<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="操作模块">
		          	<input  class="form-control" type="text"  placeholder="操作模块" name="history.omname" value="${history.omname}"/>
		        </div>
		        <div class="form-group   width_input" >
					<div class="input-group  input-append date form_date "  data-date-format="yyyy-mm-dd" >
						<input  class="form-control " type="text" name="history.statStartDate" value="${history.statStartDate}"  readonly  placeholder="开始日期" data-toggle="popover"  data-placement="top" data-content="请选择开始日期"/>
						<span class="add-on input-group-addon"><i class="icon-remove"></i></span> 
						<span class="add-on input-group-addon"><i class="icon-calendar"></i></span>
		             </div>
	        	</div>
	        	至
		        <div class="form-group   width_input" >
					<div class="input-group  input-append date form_date "  data-date-format="yyyy-mm-dd" >
						<input  class="form-control " name="history.statEndDate" value="${history.statEndDate}"  type="text"  readonly  placeholder="结束日期" data-toggle="popover"  data-placement="top" data-content="请选择结束日期"/>
						<span class="add-on input-group-addon"><i class="icon-remove"></i></span> 
						<span class="add-on input-group-addon"><i class="icon-calendar"></i></span>
		              </div>
		        </div>
	      		 <div class="form-group width_btn">
	          		<button  type="button" class="btn  btn-primary " onclick="search();"><i class="icon-search"></i> 搜索</button>
	          		<button  type="button" class="btn  btn-default " onclick="resetSearch();"><i class="icon-eraser"></i> 重置</button>
	        	</div>
	    		<div class="form-group width_btn pull-right">
	        	</div>
	    	</div>
	      	<div class="panel-body ">
	      		<div class="table-responsive">
	        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
	          			<thead>
	            			<tr>
								<th>用户</th>
								<th>操作模块</th>
								<th>动作</th>
								<th>字段</th>
								<th>原值</th>
								<th>新值</th>
								<th>记录时间</th>
								<th>操作</th>
	            			</tr>
	          			</thead>
	          			<tbody>
	            			<s:if test="historyPage.result.size>0">
							<s:iterator value="historyPage.result" var="page">
							<tr>
								  <td>${page.user.realName}</td>
					              <td >${page.omname }</td>
					              <td >${page.oaction }</td>
								  <td >${page.opname }</td>
								  <td><s:if test="%{ovalue.length()>20}"><pre><s:property value="ovalue.substring(0,20)+'...'"/></pre></s:if><s:else><pre><s:property value="ovalue"/></pre></s:else></td>
								  <td><s:if test="%{pvalue.length()>20}"><pre><s:property value="pvalue.substring(0,20)+'...'"/></pre></s:if><s:else><pre><s:property value="pvalue"/></pre></s:else></td>
								  <td ><s:date name="inserttime" format="yyyy-MM-dd HH:mm:ss"/></td>
								  <td>
									<div class="btn-group btn-group-sm pull-right">
					                  	<button type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown"> 操作 <span class="caret"></span> </button>
					                  	<ul class="dropdown-menu" role="menu">
					                    	<li><a href="history_view.shtml?id=<s:property value="id"/>">查看</a></li>
					                  	</ul>
					                </div>
								</td>
							</tr>
							</s:iterator>
							</s:if>
							<s:else>
							<tr>
								<td colspan="8" style="text-align: center;">当前列表没有数据！</td>
							</tr>
							</s:else>
						</tbody>
	        		</table>
	      		</div>
	      		<s:if test="historyPage.result.size>0">
	      		<div class="table_page dropup">
	      			<c:set var="p" value="historyPage"/>
			        <%@ include file="/common/pagination.jsp" %>
			    </div>
	      		</s:if>
		  	</div>
		</div>
		</form>
	</div>
	</div>
    <%@ include file="/common/footer.jsp" %>      
	<script type="text/javascript">
		$(document).ready(function() {
			$("#checkedAll").click(function(){
				$(".checkedIds").each(function() {
					if($("#checkedAll").prop("checked")==true){
						this.checked = true;
					}else{
						this.checked = false;
					}
				});
			});
		});
		function resetSearch(){
	       location.assign("history_list1.shtml");
	    }
	</script>
</body>
</html>