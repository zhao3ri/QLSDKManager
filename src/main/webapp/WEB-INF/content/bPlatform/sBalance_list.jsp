<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
	<div id="wrap">
	<%@ include file="/common/header.jsp" %>
	<div class="container">
	    <ol class="breadcrumb row">
			<li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
	      	<li class="active">渠道统计</li>
	      	<li class="active">加币记录</li>
	    </ol>
	    <s:debug></s:debug>
		<form role="form" action="bPlatform_listbalance.shtml" method="post" id="mainForm">
	    <div class="panel panel-default">
	      	<div class="panel-heading">加币记录 </div>
	      	<div class="form-inline popover-show panel-body list_toolbar">
				<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="渠道">
		          	<input  class="form-control" type="text"  placeholder="请输入渠道名称" name="BPlatform.platformName" value="${bPlatform.platformName }"/>
		        </div>
		        
	      		 <div class="form-group width_btn">
	          		<button  type="button" class="btn  btn-primary " onclick="search();"><i class="icon-search"></i> 搜索</button>
	          		<button  type="button" class="btn  btn-default " onclick="resetSearch();"><i class="icon-eraser"></i> 重置</button>
	        	</div>
	    		<%--<div class="form-group width_btn pull-right">--%>
	          		<%--<button type="button" class="btn btn-success " id="newitem"><i class="icon-plus"></i> 新增</button>--%>
	        	<%--</div>--%>
	    	</div>
	      	<div class="panel-body ">
	      		<div class="table-responsive">
	        		<table class="table table-hover table-striped table-bordered table-condensed table-big" style="table-layout: fixed;">
	          			<thead>
	            			<tr>
								<th style="width: 50px">序号</th>
								<th style="width: 150px">渠道</th>
								<th style="width: 180px">创建时间</th>
								<th>类型</th>
								<th>金额</th>
								<th>余额(元)</th>
	            			</tr>
	          			</thead>
	          			<tbody>
	            			<s:if test="pageb.result.size>0">
							<s:iterator value="pageb.result" var="page">
							<tr>
								<td><s:property value="id"/></td>
								<td><s:property value="platformName"/></td>					
								<td><s:date name="#page.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
								<s:if test="#page.type==1">
								    扣币
                                </s:if>
                                <s:else>
                                    加币
                                </s:else>
                                </td>
								<td><s:property value="amount/100"/></td>
								<td><s:property value="balance/100"/></td>
							</tr>
							</s:iterator>
							</s:if>	
							<s:else>
							<tr>
								<td colspan="7" style="text-align: center;">当前列表没有数据！</td>
							</tr>
							</s:else>
						</tbody>
	        		</table>
	      		</div>
	      		<s:if test="pageb.result.size>0">
	      		<div class="table_page dropup">
	      			<c:set var="p" value="pageb"/>
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
			$("#newitem").click(function() {
				location.assign("bPlatform_handle.shtml");
			});
		});
		function resetSearch(){
	       location.assign("bPlatform_listbalance.shtml");
	    }
	</script>
</body>
</html>