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
        	<form action="dataReport_monthly.shtml" method="post" id="mainForm">
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
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择客户端">
			          	<mt:selectState name="clientType" showType="select" stateType="clientType" value="${clientType}" clazz="form-control" emptyString="IOS+ANDROID"/>
			        </div>
<%-- 			       <div class="form-group    width_input"  data-toggle="popover"  data-placement="top" data-content="时间">
		            	<input  class="form-control daterange" type="text" readonly name="selectRange" value="${selectRange }"  placeholder="时间选择" data-toggle="popover"/>
		           </div> --%>
		           <br />	
		           <div class="form-group   width_input"  style="width: 200px">
					   <div  class="input-group  input-append date form_month "  data-date-format="yyyy-mm" >
							<input class="form-control"  type="text" name="yearMonthStr"   readonly  placeholder="请选择月份" data-toggle="popover"  data-placement="top" data-content="请选择月份"   value="${yearMonthStr}"/>
							<span class="add-on input-group-addon"><i class="icon-remove"></i></span> 
							<span class="add-on input-group-addon"><i class="icon-calendar"></i></span>
				        </div>
				    </div>
		           <div class="form-group   width_input"  style="width: 200px">
					   <div  class="input-group  input-append date form_month "  data-date-format="yyyy-mm" >
							<input class="form-control"  type="text" name="yearMonthStr2"   readonly  placeholder="请选择月份" data-toggle="popover"  data-placement="top" data-content="请选择月份"   value="${yearMonthStr2}"/>
							<span class="add-on input-group-addon"><i class="icon-remove"></i></span> 
							<span class="add-on input-group-addon"><i class="icon-calendar"></i></span>
				        </div>
				    </div>
		      		<div class="form-group width_btn">
		          		<button  type="button" class="btn  btn-primary " onclick="searchAndValid();"><i class="icon-search"></i> 搜索</button>
		        	</div>
		        <div class="form-group width_btn pull-right">
	          		<button type="button" class="btn btn-primary " id="excelExport"><i class="icon-upload-alt"></i> excel导出</button>
	        	</div>
	        		<br />
	        		 <div class="form-group width_input">
						<button type="button" class="btn" id="reg" onclick="getRegData();">注册转化数据图</button>
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
				  <li id="dataDaily"><a href="javascript:jumpDaily();" role="tab">每日数据汇总</a></li>
				  <li id="dataMonthly"><a href="javascript:void(0);" role="tab">每月数据汇总</a></li>
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
            				<th>活跃用户数</th>
            				<th>活跃率</th>
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
									<s:iterator value="result['data']" var="itemVal" status="st">							
										<tr>									
											<td><s:property value="(#itemVal.yearMonth+'').substring(0,4)+'-'+(#itemVal.yearMonth+'').substring(4,6)"/></td>
											<td><s:property value="#itemVal.appName"/></td>
											<td><s:property value="#itemVal.regUsers"/></td>
											<td><fmt:formatNumber value="${itemVal.conversionRate*100.0}" pattern="0.00" />%</td>
											<td><s:property value="#itemVal.monthLoginRoles"/></td>
											<td><fmt:formatNumber value="${itemVal.regUsers==0?0:(itemVal.monthLoginRoles*1.0/itemVal.regUsers*100)}" pattern="0.00" />%</td>
											<td><s:property value="#itemVal.payUsers"/></td>
											<td><s:property value="#itemVal.incPayUsers"/></td>
											<td><fmt:formatNumber value="${itemVal.payAmount/100}" pattern="0.00" /></td>
											<td><fmt:formatNumber value="${itemVal.incPayAmount/100}" pattern="0.00" /></td>
											<td><fmt:formatNumber value="${itemVal.regUsers==0?0:(itemVal.payUsers*1.0/itemVal.regUsers*100)}" pattern="0.00" />%</td>
											<td><fmt:formatNumber value="${itemVal.payUsers==0?0:itemVal.payAmount/100.0/itemVal.payUsers}" pattern="0.00" /></td>
											<td><fmt:formatNumber value="${itemVal.regUsers==0?0:itemVal.payAmount/100.0/itemVal.regUsers}" pattern="0.00"/></td>
										</tr>
									</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="13" style="text-align: center;">当前列表没有数据！</td>
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
			$("#dataMonthly").addClass("active");
			changeApp();
			
			$("#excelExport").click(function() {
				if(confirm("您确认导出Excel？")){
					$("#mainForm").attr("action","dataReport_excelExportMonthly.shtml");
					$("#mainForm").submit();
					$("#mainForm").attr("action","dataReport_monthly.shtml");
				}
			});
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
		
		function jumpMonthly(){
			var appId = $("#appId").val();
			var platformId = $("#platformId").val();
			var zoneId = $("#zoneId").val();
			location.assign("dataReport_monthly.shtml?appId=" + appId + "&platformId=" + platformId + "&zoneId=" + zoneId);
		}
		
		function jumpDaily(){
			var appId = $("#appId").val();
			var platformId = $("#platformId").val();
			var zoneId = $("#zoneId").val();
			location.assign("dataReport_daily.shtml?appId=" + appId + "&platformId=" + platformId + "&zoneId=" + zoneId);
		}
		
		var myChart;
		
		function getRegData(){
			myChart.clear();
        	if('${result["optionJson"]}' == ""){
        		$("#chartData").html("<div align='center'>没有数据！</div>");
        		return;
        	}
			var option = eval('(${result["optionJson"]})');
            myChart.setOption(option);
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