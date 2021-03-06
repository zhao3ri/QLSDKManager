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
        	<form action="roleReport_rechargeRealtime.shtml" method="post" id="mainForm">
	        	<div class="form-inline popover-show panel-body list_toolbar">
		      		<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">
			          	<select class="form-control" name="appId" id="appId" onchange="changeApp();"  placeholder="请选择游戏">
							<s:iterator value="games" var="item">
								<option value="${item.id }" <c:if test="${item.id==appId }">selected</c:if>>${item.appName }</option>
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
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top">
						<div  class="input-group  input-append date form_date "  data-date-format="yyyy-mm-dd" sType="searchAndValid" >
							<input class="form-control "  id="selectRange"  type="text" name="selectRange"   readonly  placeholder="时间选择" data-toggle="popover"  data-placement="top" data-content="请选择日期"   value="${selectRange}"/>
							<span class="add-on input-group-addon"><i class="icon-remove"></i></span> 
							<span class="add-on input-group-addon"><i class="icon-calendar"></i></span>
		                </div>
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
				  <li id="realTime"><a href="javascript:void(0);" role="tab">实时统计表</a></li>
				  <li id="daily"><a href="javascript:jumpDaily();" role="tab">日统计表</a></li>
			  </ul><br/>
      		<div class="table-responsive">
        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
            				<th>时间</th>
            				<th>充值人数</th>
            				<th>充值金额</th>
            				<th>充值次数</th>
            				<th>今日最高单笔充值</th>
            				<th>今日累计充值额</th>
            			</tr>
          			</thead>
          			<tbody>
    					<s:if test="rechargeHourlies.size>0">
								<s:iterator value="rechargeHourlies" var="item">
									<tr>
										<td><s:date name="statDate" format="HH:mm:ss"/></td>
										<td>${item.roles }</td>
										<td><fmt:formatNumber value="${item.amount/100}" pattern="0.00" /></td>
										<td>${item.payTimes }</td>
										<td><fmt:formatNumber value="${item.maxAmount/100}" pattern="0.00" /></td>
										<td><fmt:formatNumber value="${item.currentAmount/100}" pattern="0.00" /></td>
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
		 </div>
        </div>
      </div>
    </div>
  </div>
</div>
    <%@ include file="/common/footer.jsp" %>      
	<script type="text/javascript">
		$(document).ready(function() {
			$("#roleReportLeft_7").addClass("active");
			changeApp();
			$("#realTime").addClass("active");
		});
		
		function changeApp(){
			$("#zoneId").empty();
			$("#platformId").empty();
			
			var appId = $("#appId").val();
			var platformId = '${platformId}';
			var zoneId = '${zoneId}';
			$.post("/bGamezone/bGamezone_getGameZonesAsync.shtml",{appId:appId},function(data){
				json = eval(data);
				$("#zoneId").append("<option value=''>==请选择服务器==</option>");
			    for(var i=0; i<json.length; i++){
			    	if(json[i].zoneId == zoneId)
			    		$("#zoneId").append("<option selected value='" + json[i].zoneId + "'>" + json[i].zoneName + "</option>");
			    	else
			    		$("#zoneId").append("<option value='" + json[i].zoneId + "'>" + json[i].zoneName + "</option>");
			    } 
		    });
			
			$.post("/bPlatformApp/bPlatformApp_getGamePlatformsAsync.shtml",{id:appId},function(data){
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
		
		function jumpDaily(){
			var appId = $("#appId").val();
			var platformId = $("#platformId").val();
			var zoneId = $("#zoneId").val();
			location.assign("roleReport_rechargeDaily.shtml?appId=" + appId + "&platformId=" + platformId + "&zoneId=" + zoneId)
		}
		
		function jumpTwentyMinute(){
			var appId = $("#appId").val();
			var platformId = $("#platformId").val();
			var zoneId = $("#zoneId").val();
			location.assign("roleReport_rechargeTwentyMinute.shtml?appId=" + appId + "&platformId=" + platformId + "&zoneId=" + zoneId)
		}
	</script>
</body>
</html>