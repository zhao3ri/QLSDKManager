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
        	<form action="roleReport_rechargeDaily.shtml" method="post" id="mainForm">
	        	<div class="form-inline popover-show panel-body list_toolbar">
		      		<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">
			          	<select class="form-control" name="gameId" id="gameId" onchange="changeApp();"  placeholder="请选择游戏">
							<s:iterator value="games" var="item">
								<option value="${item.id }" <c:if test="${item.id==gameId }">selected</c:if>>${item.gameName }</option>
							</s:iterator>
						</select>
			        </div>
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择渠道">
						<select class="form-control" name="platformId" id="platformId" placeholder="请选择渠道">
							<option value="">==请选择渠道==</option>
						</select>
			        </div>
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择服务器">
						<select class="form-control" name="zoneId" id="zoneId"  placeholder="请选择服务器">
							<option value="">==请选择服务器==</option>
						</select>
			        </div>
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="时间">
		            	<input  class="form-control daterange" type="text" readonly name="selectRange" value="${selectRange }"  placeholder="时间" sType="searchAndValid"/>
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
				  <li id="realTime"><a href="javascript:jumpRealTime();" role="tab">实时统计表</a></li>
				  <li id="daily"><a href="javascript:void(0);" role="tab">日统计表</a></li>
			  </ul><br/>
      		<div class="table-responsive">
        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
            				<th>时间</th>
            				<th>充值角色数</th>
            				<th>充值金额</th>
            				<th>充值次数</th>
            				<th>日活跃充值角色数</th>
            				<th>周活跃充值角色数</th>
            				<th>月活跃充值角色数</th>
            				<th>新增充值用户</th>
            				<th>充值率（1次）</th>
            				<th>充值率（3次）</th>
            				<th>充值ARPU</th>
            				<th>新增角色ARPU</th>
            				<th>今日最高单笔充值</th>
            				<th>历史最高单笔充值</th>
            				<th>历史累计充值</th>
            			</tr>
          			</thead>
          			<tbody>
    					<s:if test="result['data'].size>0">
								<s:iterator value="result['data']" var="item">
									<tr>
										<td><s:date name="statDate" format="yyyy-MM-dd"/></td>
										<td>${item.roles }</td>
										<td><fmt:formatNumber value="${item.amount/100}" pattern="0.00" /></td>
										<td>${item.payTimes }</td>
										<td>${item.activePayByDay }</td>
										<td>${item.activePayByWeek }</td>
										<td>${item.activePayByMonth }</td>
										<td>${item.firstPayRoles }</td>
										<td><fmt:formatNumber value="${item.roleLogins > 0 ? item.roles * 100/item.roleLogins : 0}" pattern="0.00" />%</td>
										<td><fmt:formatNumber value="${item.activeRoles3 > 0 ? item.roles * 100/item.activeRoles3 : 0}" pattern="0.00" />%</td>
										<td><fmt:formatNumber value="${item.roles > 0 ? item.amount/100/item.roles : 0}" pattern="0.00" /></td>
										<td><fmt:formatNumber value="${item.roleEstablishs > 0 ? item.amount/100/item.roleEstablishs : 0}" pattern="0.00" /></td>
										<td><fmt:formatNumber value="${item.maxAmount/100}" pattern="0.00" /></td>
										<td><fmt:formatNumber value="${item.currentMaxAmount/100}" pattern="0.00" /></td>
										<td><fmt:formatNumber value="${item.currentAmount/100}" pattern="0.00" /></td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="15" style="text-align: center;">当前列表没有数据！</td>
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
			$("#roleReportLeft_recharge").addClass("active");
			$("#daily").addClass("active");
			changeApp();
		});
		
		function changeApp(){
			$("#zoneId").empty();
			$("#platformId").empty();
			
			var gameId = $("#gameId").val();
			var platformId = '${platformId}';
			var zoneId = '${zoneId}';
			$.post("/bGamezone/bGamezone_getGameZonesAsync.shtml",{gameId:gameId},function(data){
				json = eval(data);
				$("#zoneId").append("<option value=''>==请选择服务器==</option>");
			    for(var i=0; i<json.length; i++){
			    	if(json[i].zoneId == zoneId)
			    		$("#zoneId").append("<option selected value='" + json[i].zoneId + "'>" + json[i].zoneName + "</option>");
			    	else
			    		$("#zoneId").append("<option value='" + json[i].zoneId + "'>" + json[i].zoneName + "</option>");
			    } 
		    });
			
			$.post("/bPlatformGame/bPlatformGame_getGamePlatformsAsync.shtml",{id:gameId},function(data){
				json = eval(data);
				$("#platformId").append("<option value=''>==请选择渠道==</option>");
			    for(var i=0; i<json.length; i++){
			    	if(json[i].platformId == platformId)
			    		$("#platformId").append("<option selected value='" + json[i].platformId + "'>" + json[i].platformName + "</option>");
			    	else
			    		$("#platformId").append("<option value='" + json[i].platformId + "'>" + json[i].platformName + "</option>");
			    } 
		    });
		}
		
		function jumpRealTime(){
			var gameId = $("#gameId").val();
			var platformId = $("#platformId").val();
			var zoneId = $("#zoneId").val();
			location.assign("roleReport_rechargeRealtime.shtml?gameId=" + gameId + "&platformId=" + platformId + "&zoneId=" + zoneId)
		}
		
		function jumpTwentyMinute(){
			var gameId = $("#gameId").val();
			var platformId = $("#platformId").val();
			var zoneId = $("#zoneId").val();
			location.assign("roleReport_rechargeTwentyMinute.shtml?gameId=" + gameId + "&platformId=" + platformId + "&zoneId=" + zoneId)
		}
	</script>
</body>
</html>