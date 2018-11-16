<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
	<div id="wrap">
	<%@ include file="/common/header.jsp" %>
	<div class="container">
	    <ol class="breadcrumb row">
			<li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
	      	<li class="active">充值管理</li>
	      	<li class="active">充值列表</li>
	    </ol>
		<form identity="form" action="bOrder_list.shtml" method="post" id="mainForm">
	    <div class="panel panel-default">
	      	<div class="panel-heading">充值列表信息查询 </div>
	      	<div class="form-inline popover-show panel-body list_toolbar">
	      		<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请输入订单号">
		        	<input  class="form-control" type="text"  placeholder="订单号" name="BOrder.orderId" value="${BOrder.orderId }"/>
		        </div>
		        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请输入CP订单号">
		        	<input  class="form-control" type="text"  placeholder="CP订单号" name="BOrder.cpOrderId" value="${BOrder.cpOrderId }"/>
		        </div>
				<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">
		          	<select class="form-control" name="BOrder.appId" id="appId" onchange="changeGame();" placeholder="请选择游戏">
						<option value="">==请选择游戏==</option>
						<s:iterator value="games" var="item">
							<option value="${item.id }" <c:if test="${item.id == bOrder.appId }">selected</c:if>>${item.appName }</option>
						</s:iterator>
					</select>
		        </div>

		        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">
                				    <input  class="form-control" type="text"  placeholder="游戏名称" name="BOrder.appName" value="${BOrder.appName }"/>
                		        </div>

		       <!-- <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择服务器">
					<select class="form-control" name="BOrder.zoneId" id="zoneId"  placeholder="请选择服务器">
						<option value="">==请选择服务器==</option>
					</select>
		        </div> -->
				<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请输入平台ID">
		          	<select class="form-control" name="BOrder.platformId" placeholder="请选择平台">
						<option value="">==请选择平台==</option>
						<s:iterator value="platforms" var="item">
							<option value="${item.id }" <c:if test="${item.id == bOrder.platformId }">selected</c:if>>${item.platformName }</option>
						</s:iterator>
					</select>
		        </div>
		        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请输入用户ID">
		        	<input  class="form-control" type="text"  placeholder="用户ID" name="BOrder.uid" value="${BOrder.uid }"/>
		        </div>
		        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请输入角色名称">
		        	<input  class="form-control" type="text"  placeholder="角色名称" name="BOrder.name" value="${BOrder.name }"/>
		        </div>
		        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择客户端类型">
		          <mt:selectState name="BOrder.clientType" showType="select" stateType="clientType" value="${bOrder.clientType}" clazz="form-control" emptyString="--请选择客户端类型--"/>
		        </div>
		        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择状态">
		          <mt:selectState name="BOrder.status" showType="select" stateType="orderStatus" value="${bOrder.status}" clazz="form-control" emptyString="--请选择状态--"/>
		        </div>
		        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择通知状态">
		          <mt:selectState name="BOrder.notifyStatus" showType="select" stateType="orderNotifyStatus" value="${bOrder.notifyStatus}" clazz="form-control" emptyString="--请选择通知状态--"/>
		        </div>
		        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="时间">
		            <input  class="form-control daterange" type="text" readonly name="selectRange" value="${selectRange }"  placeholder="时间选择" />
		         </div>
		         
		          <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请输入开始金额">
		        	<input  id="moneyFrom"  class="form-control" type="text"  placeholder="开始金额" name="moneyFrom" value="${moneyFrom }"/>
		        </div>
		         至
		          <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请输入结束金额">
		        	<input  id="moneyTo" class="form-control" type="text"  placeholder="结束金额" name="moneyTo" value="${moneyTo }"/>
		        </div>
		         
	      		 <div class="form-group width_btn">
	          		<button  type="button" class="btn  btn-primary " onclick="searchPro();"><i class="icon-search"></i> 搜索</button>
	          		<button  type="button" class="btn  btn-default " onclick="resetSearch();"><i class="icon-eraser"></i> 重置</button>
	        	</div>
	    		<div class="form-group width_btn pull-right">
	          		<button type="button" class="btn btn-primary " id="excelExport"><i class="icon-upload-alt"></i> excel导出</button>
	        	</div>
	    	</div>
	      	<div class="panel-body ">
	      		<div class="table-responsive">
	        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
	          			<thead>
	            			<tr>
	            				<th>订单号</th>
								<th>游戏名称</th>
								<th>游戏分区</th>
								<th>平台名称</th>
								<th>用户ID</th>
								<th>角色名称</th>
								<th>金额</th>
								<th>元宝数量</th>
								<th>客户端类型</th>
								<th>状态</th>
								<th>通知状态</th>
								<th>创建时间</th>
								<th>操作</th>
	            			</tr>
	          			</thead>
	          			<tbody>
	            			<s:if test="bOrderPage.result.size>0">
		            			<s:set value="0" var="allAmount"></s:set>
		            			<s:set value="0" var="allGold"></s:set>
								<s:iterator value="bOrderPage.result" var="page">
									<s:set value="#allAmount + #page.amount" var="allAmount"></s:set>
									<s:set value="#allGold + #page.gold" var="allGold"></s:set>
									<tr>
										<td><s:property value="orderId"/></td>
										<td><s:property value="appName"/></td>
										<c:if test="${empty page.zoneName}">
											<td><s:property value="zoneId"/></td>
										</c:if>
										<c:if test="${!empty page.zoneName}">
											<td><s:property value="zoneName"/></td>
										</c:if>
										<td><s:property value="platformName"/></td>
										<td><s:property value="uid"/></td>
										<td><s:property value="name"/></td>
										<td><fmt:formatNumber  value="${amount/100 }" pattern="0.00" /></td>
										<td><s:property value="gold"/></td>
										<td><mt:selectState showType="label" value="${clientType}" stateType="clientType"/></td>
										<td><mt:selectState showType="label" value="${status}" stateType="orderStatus"/></td>
										<td><mt:selectState showType="label" value="${notifyStatus}" stateType="orderNotifyStatus"/></td>
										<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
										<td>
											<div class="btn-group btn-group-sm pull-right">
							                  	<button type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown"> 操作 <span class="caret"></span> </button>
							                  	<ul class="dropdown-menu" identity="menu">
							                    	<li><a href="bOrder_view.shtml?id=<s:property value="id"/>">查看</a></li>
							                    	<c:if test="${status == 2 }">
							                    		<li><a href="bOrder_reissue.shtml?id=<s:property value="id"/>">充值补发</a></li>
							                    	</c:if>
							                  	</ul>
							                </div>
										</td>
									</tr>
								</s:iterator>
								<tr>
									<td>总和</td>
									<td colspan="5"></td>
									<td><fmt:formatNumber  value="${allAmount/100 }" pattern="0.00" /></td>
									<td>${allGold }</td>
									<td colspan="5"></td>
								</tr>
							</s:if>
							<s:else>
							<tr>
								<td></td>
								<td colspan="10" style="text-align: center;">当前列表没有数据！</td>
								<td></td>
							</tr>
							</s:else>
						</tbody>
	        		</table>
	      		</div>
	      		<s:if test="bOrderPage.result.size>0">
	      		<div class="table_page dropup">
	      			<c:set var="p" value="bOrderPage"/>
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
					$("#mainForm").attr("action","bOrder_excelExport.shtml");
					$("#mainForm").submit();
					$("#mainForm").attr("action","bOrder_list.shtml");
				}
			});
			
			changeGame();
		});
		
		function resetSearch(){
	       location.assign("bOrder_list.shtml");
	    }
		
		function searchPro(){
			var moneyFrom = $("#moneyFrom").val();
			var moneyTo = $("#moneyTo").val();
			if(parseInt(moneyTo, 10) < parseInt(moneyFrom, 10)){
				alert("开始金额大于结束金额!");
			}else{
				search();
			}
		}
		
		function changeGame(){
			var appId = $("#appId").val();
			var zoneId = '${bOrder.zoneId}';
			$("#zoneId").html("");
			if(appId == ""){
				return;
			}
			$.post("/bGamezone/bGamezone_getGameZonesAsync.shtml",{appId : appId},function(data){
				json = eval(data);
				$("#zoneId").append("<option value=''>==请选择服务器==</option>");
			    for(var i=0; i<json.length; i++){
			    	if(json[i].zoneId == zoneId)
			    		$("#zoneId").append("<option selected value='" + json[i].zoneId + "'>" + json[i].zoneName + "</option>");
			    	else
			    		$("#zoneId").append("<option value='" + json[i].zoneId + "'>" + json[i].zoneName + "</option>");
			    } 
		    });
		}
	</script>
</body>
</html>