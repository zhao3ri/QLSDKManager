<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>

<body class="fix_top_nav_padding">
<div id="wrap">
	<%@ include file="/common/header.jsp" %>
	<div class="container">
	    <ol class="breadcrumb row">
			<li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
	      	<li class="active">游戏管理</li>
	      	<li class="active">游戏列表</li>
	    </ol>
		<form role="form" action="bGame_list.shtml" method="post" id="mainForm">
	    <div class="panel panel-default">
	    <div class="panel-heading">游戏列表信息查询 </div>
	      	<div class="form-inline popover-show panel-body list_toolbar">
		        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请输入游戏标识">
				    <input  class="form-control" type="text"  placeholder="游戏标识" name="game.id" value="${game.id }"/>
		        </div>
		        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">
				    <input  class="form-control" type="text"  placeholder="游戏名称" name="game.gameName" value="${game.gameName }"/>
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
								<th>游戏标识</th>
								<th>游戏名称</th>
								<th>密钥</th>
								<th>创建时间</th>
								<th>游戏开服时间</th>
								<th>操作</th>
	            			</tr>
	          			</thead>
	          			<tbody>
	            			<s:if test="page.result.size>0">
							<s:iterator value="page.result" var="page">
							<tr>
								<th><input name="checkedIds" class="checkedIds" value="<s:property value="id"/>" type="checkbox"/></th>
								<td><s:property value="id"/></td>
								<td><s:property value="gameName"/></td>
								<td><s:property value="secretKey"/></td>
								<td><s:date name="#page.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td><s:date name="#page.serviceTime" format="yyyy-MM-dd"/></td>
								<td>
									<div class="btn-group btn-group-sm pull-right">
					                  	<button type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown"> 操作 <span class="caret"></span> </button>
					                  	<ul class="dropdown-menu" role="menu">
											<li><a href="bGame_handle.shtml?id=<s:property value="id"/>">修改</a></li>
											<li><a href="bGame_delete.shtml?id=<s:property value="id"/>">删除</a></li>
					                  	</ul>
					                </div>
								</td>
							</tr>
							</s:iterator>
							</s:if>
							<s:else>
							<tr>
								<td></td>
								<td colspan="6" style="text-align: center;">当前列表没有数据！</td>
								
							</tr>
							</s:else>
						</tbody>
	        		</table>
	      		</div>	
	      		<s:if test="page.result.size>0">
	      		<div class="table_page dropup">
			        <div class="btn-group btn-group-sm pull-left">
			          	<label  class="btn btn-default  table_select_all">&nbsp;
			            	<input type="checkbox" id="checkedAll">
			          	</label>
			          	<button type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown"><span class="caret"></span> </button>
			          	<ul class="dropdown-menu text-left" role="menu">
			            	<li><a id="delete" href="javascript:void(0);">批量删除</a></li>
			          	</ul>
			        </div>
	      			<c:set var="p" value="page"/>
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
			$("#delete").click(function() {
				var checkedIds = new Array();
				var i = 0;
				$(".checkedIds").each(function() {
					if(this.checked == true) {
						checkedIds[i] = this.value;i++;
					}
				});
				if(checkedIds.length==0){
					alert("请选择要删除的数据");
				}else{
					var confirm = window.confirm("确定删除所选记录？");
					if(confirm) {
						location.assign("bGame_batchDelete.shtml?checkedIds=" + checkedIds);
					}
				}
			});
			$("#newitem").click(function() {
				location.assign("bGame_handle.shtml");
			});
		});
		function resetSearch(){
	       location.assign("bGame_list.shtml");
	    }
		/**
		function search(){
		   location.assign("bGame_list.shtml");
		}
		**/
	</script>
</body>
</html>