<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
	<div id="wrap">
	<%@ include file="/common/header.jsp" %>
	<div class="container">
	    <ol class="breadcrumb row">
			<li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
	      	<li class="active">渠道关联管理</li>
	      	<li class="active">渠道关联列表</li>
	    </ol>
		<form identity="form" action="bPlatformGame_list.shtml" method="post" id="mainForm">
	    <div class="panel panel-default">
	      	<div class="panel-heading">渠道关联列表信息查询 </div>
	      	<div class="form-inline popover-show panel-body list_toolbar">

		      <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择渠道">
		          	<select class="form-control" name="BPlatformGame.platformId"  data-original-title="" title="">
									<option value="">==请选择渠道==</option>
									<s:iterator value="bPlatforms" var="item">
										<option value="${item.id}" <c:if test="${item.id==bPlatformGame.platformId}">selected</c:if>>${item.platformName }</option>
									</s:iterator>
							</select>
		        </div>

		       <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请输入游戏">
               				    <input  class="form-control" type="text"  placeholder="游戏名称" name="BPlatformGame.gameName" value="${BPlatformGame.gameName}"/>
               		        </div>

		        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">
		          	<select class="form-control" name="BPlatformGame.gameId"  data-original-title="" title="">
									<option value="">==请选择游戏==</option>
									<s:iterator value="games" var="item">
										<option value="${item.id}" <c:if test="${item.id==bPlatformGame.gameId}">selected</c:if>>${item.gameName }</option>
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
								<th style="width: 100px;">渠道</th>
								<th style="width: 100px;">游戏</th>
								<th style="width: 100px;">分成比例</th>
								<%--<th>配置参数</th>--%>
								<th>AppKey</th>
								<th>AppID</th>
								<th>SecretKey</th>
								<th>PublicKey</th>
								<th>PrivateKey</th>
								<th style="width: 150px;">创建时间</th>
								<th style="width: 80px;">操作</th>
	            			</tr>
	          			</thead>
	          			<tbody>
	            			<s:if test="bPlatformGamePage.result.size>0">
							<s:iterator value="bPlatformGamePage.result" var="page">
							<tr>
								<td><s:property value="id"/></td>
								<td><s:property value="platformName"/></td>
								<td><s:property value="gameName"/></td>
                                <td><s:property value="discount"/></td>
                                <td><s:property value="appKey"/></td>
                                <td><s:property value="appID"/></td>
                                <td style="overflow: scroll;white-space: nowrap;"
                                    title="<s:property value="secretKey"/>"><s:property value="secretKey"/></td>
                                <td style="overflow: scroll;white-space: nowrap;"
                                    title="<s:property value="publicKey"/>"><s:property value="publicKey"/></td>
                                <td style="overflow: scroll;white-space: nowrap;"
                                    title="<s:property value="privateKey"/>"><s:property
                                        value="privateKey"/></td>
								<%--<td style="overflow: scroll;white-space: nowrap;" title="<s:property value="configParams"/>"><s:property value="configParams"/></td>--%>
								<td><s:date name="#page.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<div class="btn-group btn-group-sm pull-right">
					                  	<button type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown"> 操作 <span class="caret"></span> </button>
					                  	<ul class="dropdown-menu" role="menu">
											<li><a href="bPlatformGame_handle.shtml?id=<s:property value="id"/>">修改</a></li>
											<li><a href="javascript:confirmAction('bPlatformGame_delete.shtml?id=<s:property value="id"/>','您确认删除？');">删除</a></li>
					                  		<li><a href="/bPlatformGameZone/bPlatformGameZone_handle.shtml?BPlatformGameZone.gameId=${gameId }&BPlatformGameZone.platformId=${platformId}">分区管理</a></li>
					                  	</ul>
					                </div>
								</td>
							</tr>
							</s:iterator>
							</s:if>
							<s:else>
							<tr>
								<td colspan="11" style="text-align: center;">当前列表没有数据！</td>
							</tr>
							</s:else>
						</tbody>
	        		</table>
	      		</div>
	      		<s:if test="bPlatformGamePage.result.size>0">
	      		<div class="table_page dropup">
	      			<c:set var="p" value="bPlatformGamePage"/>
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
				location.assign("bPlatformGame_handle.shtml");
			});
		});
		function resetSearch(){
	       location.assign("bPlatformGame_list.shtml");
	    }
	</script>
</body>
</html>