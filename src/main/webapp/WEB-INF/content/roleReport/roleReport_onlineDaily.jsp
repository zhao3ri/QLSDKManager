<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
<div id="wrap" > 
  <%@ include file="/common/header.jsp" %>
  <div class="container">
   <div class="row"> 
   	<div class="col-md-1 ">
   		<%@ include file="/common/roleReportLeft.jsp" %>
	</div>
    
    <div class=" col-md-11 ">
        <div class="panel panel-default">
        	<form action="roleReport_onlineDaily.shtml" method="post" id="mainForm">
	        	<div class="form-inline popover-show panel-body list_toolbar">
		      		<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">
			          	<select class="form-control" name="gameId" id="gameId" onchange="changeApp();"  placeholder="请选择游戏">
							<s:iterator value="games" var="item">
								<option value="${item.id }" <c:if test="${item.id==gameId }">selected</c:if>>${item.gameName }</option>
							</s:iterator>
						</select>
			        </div>
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择渠道">
						<select class="form-control" name="channelId" id="channelId" placeholder="请选择渠道">
							<option value="">==请选择渠道==</option>
						</select>
			        </div>
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择服务器">
						<select class="form-control" name="zoneId" id="zoneId"  placeholder="请选择服务器">
							<option value="">==请选择服务器==</option>
						</select>
			        </div>
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="时间">
			            <input  class="form-control daterange" type="text" readonly name="selectRange"  id="selectRange" value="${selectRange }"  placeholder="时间选择" sType="searchAndValid" />
			          </div>
<!-- 		      		<div class="form-group width_btn"> -->
<!-- 		          		<button  type="button" class="btn  btn-primary " onclick="searchAndValid();"><i class="icon-search"></i> 搜索</button> -->
<!-- 		        	</div> -->
		    	</div>
	    	</form>
        </div>
        <div class="panel panel-default">
          <div class="panel-body ">
          	  <ul class="nav nav-tabs " >
				  <li id="online"><a href="javascript:online();" role="tab">实时在线趋势图</a></li>
				  <li id="onlineDaily"><a href="javascript:onlineDaily()" role="tab">实时在线统计表</a></li>
				  <li id="daily"><a href="javascript:daily();" role="tab">日统计趋势图</a></li>
				  <div class="statistics">
				    <div id="online_chart" class="record">
				    <div class="msg"><P> 当前在线人数：${result['roleOnlines']}</P></div>
				        <div class="msg">
				    <P> 历史最高在线人数：${result['historyTopOnlines']}<c:if test="${result['historyTopOnlines'] > 0}">(<s:date name="result['historyTimeOnlines']" format="yyyy-MM-dd"/>)</c:if></P> </div>
				         <div class="msg"><p>日最高在线：${result['dailyTopOnlines']}</p></div>
				    <div class="msg"><p>日最低在线：${result['dailyLastOnlines']}</p></div>
				    <div class="msg"><p>日平均在线：${result['dailyAvgOnlines']}</p></div>
					</div>
					<div class="msg"><button type="button" class="btn btn-default " onclick="searchAndValid();"> 刷新</button></div>
					<div class="msg"><button type="button" class="btn btn-default " id="excelExport" > 导出</button></div>
				</div>
			  </ul>
      		<div class="table-responsive">
	      		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
            				<th>时间</th>
            				<th>最高在线</th>
            				<th>最低在线</th>
            				<th>平均在线</th>
            			</tr>
          			</thead>
          			<tbody>
          				<s:set var="itemSize" value="#result['data'].size"></s:set>
	     				<s:if test="result['data'].size > 0">
		         				<s:iterator value="result['data']" var="item" status="st">
										<tr>
											<td><s:date name="statDate" format="yyyy-MM-dd"/></td>
											<td>${maxRoleOnlines}</td>
											<td>${minRoleOnlines}</td>
											<td>${avgRoleOnlines}</td>
										</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="5" style="text-align: center;">当前列表没有数据！</td>
							</tr>
						</s:else>
          			</tbody>
         		</table>
      		</div>
		 </div>
        </div>
      </div>
    </div>
  </div>
</div>
    <%@ include file="/common/footer.jsp" %>      
	<script type="text/javascript">
		$(document).ready(function() {
			$("#roleReportLeft_online").addClass("active");
			$("#onlineDaily").addClass("active");
			changeApp();
		});
		
		$("#excelExport").click(function() {
			if(confirm("您确认导出Excel？")){
				$("#mainForm").attr("action","roleReport_excelExportOnlineDaily.shtml");
				$("#mainForm").submit();
				$("#mainForm").attr("action","roleReport_daily.shtml");
			}
		});
		
		function online(){
			$("#selectRange").val("");
			$("#mainForm").attr("action","${ctx}/roleReport/roleReport_online.shtml");
			$("#mainForm").submit();
		}
		function onlineDaily(){
			$("#selectRange").val("");
			$("#mainForm").attr("action","${ctx}/roleReport/roleReport_onlineDaily.shtml");
			$("#mainForm").submit();
		}
		function daily(){
			$("#selectRange").val("");
			$("#mainForm").attr("action","${ctx}/roleReport/roleReport_daily.shtml");
			$("#mainForm").submit();
		}
		
		function changeApp(){
			$("#zoneId").empty();
			$("#channelId").empty();
			
			var gameId = $("#gameId").val();
			var channelId = '${channelId}';
			var zoneId = '${zoneId}';
			$.post("${ctx}/bGamezone/bGamezone_getGameZonesAsync.shtml",{gameId:gameId},function(data){
				json = eval(data);
				$("#zoneId").append("<option value=''>==请选择服务器==</option>");
			    for(var i=0; i<json.length; i++){
			    	if(json[i].zoneId == zoneId)
			    		$("#zoneId").append("<option selected value='" + json[i].zoneId + "'>" + json[i].zoneName + "</option>");
			    	else
			    		$("#zoneId").append("<option value='" + json[i].zoneId + "'>" + json[i].zoneName + "</option>");
			    } 
		    });
			
			$.post("${ctx}/bChannelGame/bPlatformGame_getGamePlatformsAsync.shtml",{id:gameId},function(data){
				json = eval(data);
				$("#channelId").append("<option value=''>==请选择渠道==</option>");
			    for(var i=0; i<json.length; i++){
			    	if(json[i].channelId == channelId)
			    		$("#channelId").append("<option selected value='" + json[i].channelId + "'>" + json[i].channelName + "</option>");
			    	else
			    		$("#channelId").append("<option value='" + json[i].channelId + "'>" + json[i].channelName + "</option>");
			    } 
		    });
		}
	</script>
</body>
</html>