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
        	<form action="dataReport_daily.shtml" method="post" id="mainForm">
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
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择客户端">
			          	<mt:selectState name="clientType" showType="select" stateType="clientType" value="${clientType}" clazz="form-control" emptyString="IOS+ANDROID"/>
			        </div>
			       <div class="form-group    width_input"  data-toggle="popover"  data-placement="top" data-content="时间">
		            	<input  class="form-control daterange" type="text" readonly name="selectRange" value="${selectRange }"  placeholder="时间选择" sType="searchAndValid" />
		           </div>
<!-- 		      		<div class="form-group width_btn"> -->
<!-- 		          		<button  type="button" class="btn  btn-primary " onclick="searchAndValid();"><i class="icon-search"></i> 搜索</button> -->
<!-- 		        	</div> -->
		        <div class="form-group width_btn pull-right">
	          		<button type="button" class="btn btn-primary " id="excelExport"><i class="icon-upload-alt"></i> excel导出</button>
	        	</div>
	        		<br />
	        		 <div class="form-group width_input">
						<button type="button" class="btn" id="keep" onclick="getKeepData();">留存数据图</button>
			        </div>
			        <div class="form-group width_input">
						<button type="button" class="btn" id="pay" onclick="getPayData();">付费数据图</button>
			        </div>
		    	</div>
	    	</form>
        </div>
        <div class="panel panel-default">
          <div class="panel-body ">
              <ul class="nav nav-tabs " >
				  <li id="dataDaily"><a href="javascript:void(0);" identity="tab">每日数据汇总</a></li>
				  <li id="dataMonthly"><a href="javascript:jumpMonthly();" identity="tab">每月数据汇总</a></li>
			  </ul><br/>
	          <div class="panel-body " id="chartData"  style="height:400px"> 
	          	统计图输出
	          </div>
      		<div class="table-responsive">
        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
            				<th>时间</th>
            				<th>游戏名称</th>
            				<th>注册人数</th>
            				<th>转化率</th>
            				<th>活跃用户数/活跃率</th>
            				<th>次日留存数/留存率</th>
            				<th>三日留存数/留存率</th>
            				<th>七日留存数/留存率</th>
            				<th>十五日留存数/留存率</th>
            				<th>三十日留存数/留存率</th>
            				<th>付费用户数</th>
            				<th>新增付费用户数</th>
            				<th>总充值金额</th>
            				<th>新增充值金额</th>
            				<th>付费率</th>
            				<th>arpu值</th>
            				<th>注册arpu值</th>
            			</tr>
          			</thead>
          			<tbody>
    					<s:if test="result['data'].size>0">
							        <s:set var="allRegUsers" value="0"></s:set>
			          				<s:set var="allConversionRate" value="0"></s:set>
			          				<s:set var="allActiveUsers" value="0"></s:set>	
			          				<s:set var="allKeepRole1" value="0"></s:set>
			          				<s:set var="allKeepRole3" value="0"></s:set>
			          				<s:set var="allKeepRole7" value="0"></s:set>
			          				<s:set var="allKeepRole15" value="0"></s:set>
			          				<s:set var="allKeepRole30" value="0"></s:set>
			          				<s:set var="allPayUsers" value="0"></s:set>
			          				<s:set var="allIncPayUsers" value="0"></s:set>
			          				<s:set var="allPayAmount" value="0"></s:set>
			          				<s:set var="allIncPayAmount" value="0"></s:set>
			          				<s:set var="allPayRate" value="0"></s:set>
			          				<s:set var="allRoleEstablishs" value="0"></s:set>
									<s:iterator value="result['data']" var="itemVal" status="st">
									<s:set var="allRoleEstablishs" value="#allRoleEstablishs+ #itemVal.roleEstablishs"></s:set>
			          				<s:set var="allRegUsers" value="#allRegUsers + #itemVal.regUsers"></s:set>
			          				<s:set var="allConversionRate" value="#allConversionRate + (#itemVal.conversionRate==null?0:#itemVal.conversionRate)"></s:set>
			          				<s:set var="allActiveUsers" value="#allActiveUsers + #itemVal.activeUsers"></s:set>	
			          				<s:set var="allKeepRole1" value="#allKeepRole1 + #itemVal.keepRole1"></s:set>	
			          				<s:set var="allKeepRole3" value="#allKeepRole3 + #itemVal.keepRole3"></s:set>
			          				<s:set var="allKeepRole7" value="#allKeepRole7 + #itemVal.keepRole7"></s:set>
			          				<s:set var="allKeepRole15" value="#allKeepRole15 + #itemVal.keepRole15"></s:set>
			          				<s:set var="allKeepRole30" value="#allKeepRole30 + #itemVal.keepRole30"></s:set>	
			          				<s:set var="allPayUsers" value="#allPayUsers + #itemVal.payUsers"></s:set>	
			          				<s:set var="allIncPayUsers" value="#allIncPayUsers + #itemVal.incPayUsers"></s:set>	
			          				<s:set var="allPayAmount" value="#allPayAmount + #itemVal.payAmount"></s:set>	
			          				<s:set var="allIncPayAmount" value="#allIncPayAmount + #itemVal.incPayAmount"></s:set>
			          				<s:set var="allPayRate" value="#allPayRate + (#itemVal.activeUsers==0?0:#itemVal.payUsers*1.0/#itemVal.activeUsers)"></s:set>							
										<tr>									
											<td><s:date name="#itemVal.statDate" format="yyyy-MM-dd"/></td>
											<td><s:property value="#itemVal.gameName"/></td>
											<td><s:property value="#itemVal.regUsers"/></td>
											<td><fmt:formatNumber value="${itemVal.conversionRate*100.0}" pattern="0.00" />%</td>
											<td><s:property value="#itemVal.activeUsers"/>(<fmt:formatNumber value="${itemVal.regUsers==0?0:(itemVal.activeUsers*1.0/itemVal.regUsers*100)}" pattern="0.00" />%)</td>
											<td><s:property value="#itemVal.keepRole1"/>(<fmt:formatNumber value="${itemVal.roleEstablishs==0?0:itemVal.keepRole1*100.0/itemVal.roleEstablishs}" pattern="0.00" />%)</td>
											<td><s:property value="#itemVal.keepRole3"/>(<fmt:formatNumber value="${itemVal.roleEstablishs==0?0:itemVal.keepRole3*100.0/itemVal.roleEstablishs}" pattern="0.00" />%)</td>
											<td><s:property value="#itemVal.keepRole7"/>(<fmt:formatNumber value="${itemVal.roleEstablishs==0?0:itemVal.keepRole7*100.0/itemVal.roleEstablishs}" pattern="0.00" />%)</td>
											<td><s:property value="#itemVal.keepRole15"/>(<fmt:formatNumber value="${itemVal.roleEstablishs==0?0:itemVal.keepRole15*100.0/itemVal.roleEstablishs}" pattern="0.00" />%)</td>
											<td><s:property value="#itemVal.keepRole30"/>(<fmt:formatNumber value="${itemVal.roleEstablishs==0?0:itemVal.keepRole30*100.0/itemVal.roleEstablishs}" pattern="0.00" />%)</td>
											<td><s:property value="#itemVal.payUsers"/></td>
											<td><s:property value="#itemVal.incPayUsers"/></td>
											<td><fmt:formatNumber value="${itemVal.payAmount/100}" pattern="0.00" /></td>
											<td><fmt:formatNumber value="${itemVal.incPayAmount/100}" pattern="0.00" /></td>
											<td><fmt:formatNumber value="${itemVal.activeUsers==0?0:(itemVal.payUsers*1.0/itemVal.activeUsers*100)}" pattern="0.00" />%</td>
											<td><fmt:formatNumber value="${itemVal.payUsers==0?0:itemVal.payAmount/100.0/itemVal.payUsers}" pattern="0.00" /></td>
											<td><fmt:formatNumber value="${itemVal.regUsers==0?0:itemVal.payAmount/100.0/itemVal.regUsers}" pattern="0.00" /></td>
										</tr>
									</s:iterator>
				          			<s:if test="result['data'].size >1">
				          				<tr>
					          				<td>汇总</td>
					          				<td></td>
					          				<td>${allRegUsers}</td>
						                    <td><fmt:formatNumber  value="${allConversionRate*100.0/result['data'].size()}" pattern="0.00" />%</td>				
						                    <td>${allActiveUsers}(<fmt:formatNumber value="${allRegUsers==0?0:allActiveUsers*1.0/allRegUsers/result['data'].size()*100}" pattern="0.00" />%)</td>
						                    <td>${allKeepRole1}(<fmt:formatNumber  value="${roleEstablishs==0?0:allKeepRole1*100.0/allRoleEstablishs}" pattern="0.00" />%)</td>
						                    <td>${allKeepRole3}(<fmt:formatNumber  value="${roleEstablishs==0?0:allKeepRole3*100.0/allRoleEstablishs}" pattern="0.00" />%)</td>	
						                    <td>${allKeepRole7}(<fmt:formatNumber  value="${roleEstablishs==0?0:allKeepRole7*100.0/allRoleEstablishs}" pattern="0.00" />%)</td>
						                    <td>${allKeepRole15}(<fmt:formatNumber  value="${roleEstablishs==0?0:allKeepRole15*100.0/allRoleEstablishs}" pattern="0.00" />%)</td>
						                    <td>${allKeepRole30}(<fmt:formatNumber  value="${roleEstablishs==0?0:allKeepRole30*100.0/allRoleEstablishs}" pattern="0.00" />%)</td>
						                    <td>${allPayUsers}</td>
						                    <td>${allIncPayUsers}</td>
						                    <td><fmt:formatNumber value="${allPayAmount/100}" pattern="0.00" /></td>
						                    <td><fmt:formatNumber value="${allIncPayAmount/100}" pattern="0.00" /></td>
						                    <td><fmt:formatNumber value="${allPayRate/result['data'].size()*100}" pattern="0.00" />%</td>
						                    <td><fmt:formatNumber value="${allPayUsers==0?0:allPayAmount/100.0/allPayUsers}" pattern="0.00" /></td>
						                    <td><fmt:formatNumber value="${allRegUsers==0?0:allPayAmount/100.0/allRegUsers}" pattern="0.00" /></td>
					          			</tr>
				          			</s:if>							
							</s:if>
							<s:else>
								<tr>
									<td colspan="17" style="text-align: center;">当前列表没有数据！</td>
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
			$("#roleReportLeft_10").addClass("active");
			$("#dataDaily").addClass("active");
			changeApp();
			
			$("#excelExport").click(function() {
				if(confirm("您确认导出Excel？")){
					$("#mainForm").attr("action","dataReport_excelExportDaily.shtml");
					$("#mainForm").submit();
					$("#mainForm").attr("action","dataReport_daily.shtml");
				}
			});
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
			
			$.post("/bPlatformApp/bPlatformApp_getGamePlatformsAsync.shtml",{id:gameId},function(data){
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
		
		function jumpMonthly(){
			var gameId = $("#gameId").val();
			var platformId = $("#platformId").val();
			var zoneId = $("#zoneId").val();
			location.assign("dataReport_monthly.shtml?gameId=" + gameId + "&platformId=" + platformId + "&zoneId=" + zoneId)
		}
		
		function jumpDaily(){
			var gameId = $("#gameId").val();
			var platformId = $("#platformId").val();
			var zoneId = $("#zoneId").val();
			location.assign("dataReport_daily.shtml?gameId=" + gameId + "&platformId=" + platformId + "&zoneId=" + zoneId)
		}
		
		var myChart;
		
		function getKeepData(){
			myChart.clear();
        	if('${result["optionJson"]}' == ""){
        		$("#chartData").html("<div align='center'>没有数据！</div>");
        		return;
        	}
			var option2 = eval('(${result["optionJson"]})');
			myChart.setOption(option2);
		}
		
		function getPayData(){
			myChart.clear();
        	if('${result["optionJson2"]}' == ""){
        		$("#chartData").html("<div align='center'>没有数据！</div>");
        		return;
        	}
			var option2 = eval('(${result["optionJson2"]})');
			myChart.setOption(option2);
		}
		
	
		require(
	            [
	                'echarts',
	                'echarts/chart/line',
	                'echarts/chart/bar',
	                'echarts/chart/pie',
	                'echarts/chart/funnel'
	            ],
	            
	            function (ec) {
	            	draw(ec);
	            }
		 );
		
		function draw(ec){
        	if('${result["optionJson"]}' == ""){
        		$("#chartData").html("<div align='center'>没有数据！</div>");
        		return;
        	}
            myChart = ec.init(document.getElementById('chartData')); 
			var option = eval('(${result["optionJson"]})');
            myChart.setOption(option); 
		}
	</script>
</body>
</html>