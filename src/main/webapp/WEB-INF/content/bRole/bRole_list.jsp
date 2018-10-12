<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
	<div id="wrap">
	<%@ include file="/common/header.jsp" %>
	<div class="container">
	    <ol class="breadcrumb row">
			<li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
	      	<li class="active">报表管理</li>
	      	<li class="active">角色列表</li>
	    </ol>
		<form role="form" action="bRole_list.shtml" method="post" id="mainForm">
	    <div class="panel panel-default">
	      	<div class="panel-heading">角色列表信息查询 </div>
	      	<div class="form-inline popover-show panel-body list_toolbar">
	      		<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">
		          	<select class="form-control" name="role.appId" placeholder="请选择游戏">
						<s:iterator value="games" var="item">
							<option value="${item.id }" <c:if test="${item.id == role.appId }">selected</c:if>>${item.appName }</option>
						</s:iterator>
					</select>
		        </div>
		        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择平台">
		        	<select class="form-control" name="role.platformId" placeholder="请选择平台">
						<option value="">==请选择平台==</option>
						<s:iterator value="platforms" var="item">
							<option value="${item.id }" <c:if test="${item.id == role.platformId }">selected</c:if>>${item.platformName }</option>
						</s:iterator>
					</select>
				</div>
				<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="时间">
		            <input  class="form-control daterange" type="text" readonly name="selectRange" value="${selectRange }"  placeholder="时间选择" sType="search"/>
		         </div>
	      		<div class="form-group width_btn">
 	          		<button  type="button" class="btn  btn-primary " onclick="search();"><i class="icon-search"></i> 搜索</button>
	          		<button  type="button" class="btn  btn-default " onclick="resetSearch();"><i class="icon-eraser"></i> 重置</button>
	        	</div>
	    		<div class="form-group width_btn pull-right">
	          		<button type="button" class="btn btn-primary " id="excelExport"><i class="icon-upload-alt"></i>excel导出</button>
	        	</div>
	    	</div>
	      	<div class="panel-body ">
	      		<div class="table-responsive">
	        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
	          			<thead>
	            			<tr>
								<th>游戏名称</th>
								<th>平台名称</th>
								<th>分区</th>
								<th>角色id</th>
								<th>角色名称</th>
								<th>创建时间</th>
	            			</tr>
	          			</thead>
	          			<tbody>
	            			<s:if test="rolePage.result.size>0">
								<s:iterator value="rolePage.result" var="page">
									<tr>
										<td><s:property value="appName"/></td>
										<td><s:property value="platformName"/></td>
										<td><s:property value="zoneId"/></td>
										<td><s:property value="roleId"/></td>
										<td><s:property value="roleName"/></td>
										<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
							<tr>
								<td colspan="6" style="text-align: center;">当前列表没有数据！</td>
							</tr>
							</s:else>
						</tbody>
	        		</table>
	      		</div>
	      		<s:if test="rolePage.result.size>0">
	      		<div class="table_page dropup">
	      			<c:set var="p" value="rolePage"/>
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
			$("#excelExport").click(function() {
				if(confirm("您确认导出Excel？")){
					$("#mainForm").attr("action","bRole_excelExport.shtml");
					$("#mainForm").submit();
					$("#mainForm").attr("action","bRole_list.shtml");
				}
			});
		});
		
		function resetSearch(){
	       location.assign("bRole_list.shtml");
	    }
	</script>
</body>
</html>