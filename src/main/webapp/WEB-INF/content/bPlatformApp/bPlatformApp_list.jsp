<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
	<div id="wrap">
	<%@ include file="/common/header.jsp" %>
	<div class="container">
	    <ol class="breadcrumb row">
			<li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
	      	<li class="active">联运游戏关联管理</li>
	      	<li class="active">联运游戏关联列表</li>
	    </ol>
		<form role="form" action="bPlatformApp_list.shtml" method="post" id="mainForm">
	    <div class="panel panel-default">
	      	<div class="panel-heading">联运游戏关联列表信息查询 </div>
	      	<div class="form-inline popover-show panel-body list_toolbar">

		      <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择联运平台">
		          	<select class="form-control" name="BPlatformApp.platformId"  data-original-title="" title="">
									<option value="">==请选择联运平台==</option>
									<s:iterator value="bPlatforms" var="item">
										<option value="${item.id}" <c:if test="${item.id==bPlatformApp.platformId}">selected</c:if>>${item.platformName }</option>
									</s:iterator>								
							</select>
		        </div>

		       <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请输入游戏">
               				    <input  class="form-control" type="text"  placeholder="游戏名称" name="BPlatformApp.appName" value="${BPlatformApp.appName}"/>
               		        </div>

		        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择联运游戏">
		          	<select class="form-control" name="BPlatformApp.appId"  data-original-title="" title="">
									<option value="">==请选择联运游戏==</option>
									<s:iterator value="games" var="item">
										<option value="${item.id}" <c:if test="${item.id==bPlatformApp.appId}">selected</c:if>>${item.appName }</option>
									</s:iterator>									
							</select>
		        </div>

	      		 <div class="form-group width_btn">
	          		<button  type="button" class="btn  btn-primary " onclick="search();"><i class="icon-search"></i> 搜索</button>
	          		<button  type="button" class="btn  btn-default " onclick="resetSearch();"><i class="icon-eraser"></i> 重置</button>
	        	</div>
	    		<div class="form-group width_btn pull-right">
	          		<button type="button" class="btn btn-success " id="newitem"><i class="icon-plus"></i> 新增</button>
	        	</div>
	    	</div>
	      	<div class="panel-body ">
	      		<div class="table-responsive">
	        		<table class="table table-hover table-striped table-bordered table-condensed table-big" style="table-layout:fixed">
	          			<thead>
	            			<tr>
								<th style="width: 30px;">序号</th>
								<th style="width: 100px;">联运平台</th>
								<th style="width: 100px;">联运游戏</th>
								<th style="width: 100px;">分成比例</th>
								<th>配置参数</th>
								<th style="width: 150px;">创建时间</th>
								<th style="width: 80px;">操作</th>
	            			</tr>
	          			</thead>
	          			<tbody>
	            			<s:if test="bPlatformAppPage.result.size>0">
							<s:iterator value="bPlatformAppPage.result" var="page">
							<tr>
								<td><s:property value="id"/></td>
								<td><s:property value="platformName"/></td>
								<td><s:property value="appName"/></td>
								<td><s:property value="discount"/></td>
								<td style="overflow: scroll;white-space: nowrap;" title="<s:property value="configParams"/>"><s:property value="configParams"/></td>
								<td><s:date name="#page.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<div class="btn-group btn-group-sm pull-right">
					                  	<button type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown"> 操作 <span class="caret"></span> </button>
					                  	<ul class="dropdown-menu" role="menu">
											<li><a href="bPlatformApp_handle.shtml?id=<s:property value="id"/>">修改</a></li>
											<li><a href="javascript:confirmAction('bPlatformApp_delete.shtml?id=<s:property value="id"/>','您确认删除？');">删除</a></li>
					                  		<li><a href="/bPlatformGameZone/bPlatformGameZone_handle.shtml?BPlatformGameZone.appId=${appId }&BPlatformGameZone.platformId=${platformId}">分区管理</a></li>
					                  	</ul>
					                </div>
								</td>
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
	      		<s:if test="bPlatformAppPage.result.size>0">
	      		<div class="table_page dropup">
	      			<c:set var="p" value="bPlatformAppPage"/>
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
				location.assign("bPlatformApp_handle.shtml");
			});
		});
		function resetSearch(){
	       location.assign("bPlatformApp_list.shtml");
	    }
	</script>
</body>
</html>