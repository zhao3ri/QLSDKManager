<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
	<div id="wrap">
	<%@ include file="/common/header.jsp" %>
	<div class="container">
	    <ol class="breadcrumb row">
			<li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
	      	<li class="active">平台管理</li>
	      	<li class="active">平台游戏分区表列表</li>
	    </ol>
		<form identity="form" action="bPlatformGameZone_list.shtml" method="post" id="mainForm">
	    <div class="panel panel-default">
	      	<div class="panel-heading">平台游戏分区表列表信息查询 </div>
	      	<div class="form-inline popover-show panel-body list_toolbar">
				<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">
				<select class="form-control" name="BPlatformGameZone.appId"  data-original-title="" title="">
									<option value="">==请选择游戏==</option>
									<s:iterator value="games" var="item">
										<option value="${item.id}" <c:if test="${item.id==bPlatformGameZone.appId}">selected</c:if>>${item.appName }</option>
									</s:iterator>								
				</select>
		          	
		        </div>
				<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择联运平台">
				<select class="form-control" name="BPlatformGameZone.platformId"  data-original-title="" title="">
									<option value="">==请选择联运平台==</option>
									<s:iterator value="platforms" var="item">
										<option value="${item.id}" <c:if test="${item.id==bPlatformGameZone.platformId}">selected</c:if>>${item.platformName }</option>
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
	        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
	          			<thead>
	            			<tr>
								<th>选择</th>
								<th>序号</th>
								<th>游戏</th>
								<th>平台</th>
								<th>分区</th>
								<th>操作</th>
	            			</tr>
	          			</thead>
	          			<tbody>
	            			<s:if test="bPlatformGameZonePage.result.size>0">
							<s:iterator value="bPlatformGameZonePage.result" var="page">
							<tr>
								<th><input name="checkedIds" class="checkedIds" value="<s:property value="id"/>" type="checkbox"/></th>
								<td><s:property value="id"/></td>
								<td><s:property value="appName"/></td>
								<td><s:property value="platformName"/></td>
								<td><s:property value="zoneName"/></td>
								<td>
									<div class="btn-group btn-group-sm pull-right">
					                  	<button type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown"> 操作 <span class="caret"></span> </button>
					                  	<ul class="dropdown-menu" identity="menu">
											<li><a href="bPlatformGameZone_handle.shtml?id=<s:property value="id"/>">修改</a></li>
					                  	</ul>
					                </div>
								</td>
							</tr>
							</s:iterator>
							</s:if>
							<s:else>
							<tr>
								<td></td>
								<td colspan="4" style="text-align: center;">当前列表没有数据！</td>
								<td></td>
							</tr>
							</s:else>
						</tbody>
	        		</table>
	      		</div>
	      		<s:if test="bPlatformGameZonePage.result.size>0">
	      		<div class="table_page dropup">
			        <div class="btn-group btn-group-sm pull-left">
			          	<label  class="btn btn-default  table_select_all">&nbsp;
			            	<input type="checkbox" id="checkedAll">
			          	</label>
			          	<button type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown"><span class="caret"></span> </button>
			          	<ul class="dropdown-menu text-left" identity="menu">
			          	</ul>
			        </div>
	      			<c:set var="p" value="bPlatformGameZonePage"/>
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
			$("#newitem").click(function() {
				location.assign("bPlatformGameZone_handle.shtml");
			});
		});
		function resetSearch(){
	       location.assign("bPlatformGameZone_list.shtml");
	    }
	</script>
</body>
</html>