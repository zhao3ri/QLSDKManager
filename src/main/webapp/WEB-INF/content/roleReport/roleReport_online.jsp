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
        	<form action="roleReport_online.shtml" method="post" id="mainForm">
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
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top">
							<div  class="input-group  input-append date form_date "  data-date-format="yyyy-mm-dd"  sType="searchAndValid" >
								<input class="form-control "  id="selectRange"  type="text" name="selectRange" id="selectRange" readonly  placeholder="时间选择" data-toggle="popover"  data-placement="top" data-content="请选择日期"   value="${selectRange}"/>
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
			</div>
	         <div class="panel-body " id="chartData"  style="height:400px"></div>
		 </div>
        </div>
      </div>
    </div>
 </div>
    <%@ include file="/common/footer.jsp" %>      
	<script type="text/javascript">
		$(document).ready(function() {
			$("#roleReportLeft_online").addClass("active");
			$("#online").addClass("active");
			changeApp();
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
		
		
		$("#excelExport").click(function() {
			if(confirm("您确认导出Excel？")){
				$("#mainForm").attr("action","roleReport_excelExportOnline.shtml");
				$("#mainForm").submit();
				$("#mainForm").attr("action","roleReport_daily.shtml");
			}
		});
		
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
			
			$.post("${ctx}/bChannelGame/bChannelGame_getGameChannelsAsync.shtml",{id:gameId},function(data){
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
		
		require(
	            [
	                'echarts',
	                'echarts/chart/line',
	                'echarts/chart/bar',
	                'echarts/chart/pie',
	                'echarts/chart/funnel'
	            ],
	            function (ec) {
	            	if('${result["optionJson"]}' == ""){
	            		$("#chartData").html("<div align='center'>没有数据！</div>");
	            		return;
	            	}
	                var myChart = ec.init(document.getElementById('chartData')); 
					var option = eval('(${result["optionJson"]})');
	                myChart.setOption(option); 
	            }
		 );
	</script>
</body>
</html>