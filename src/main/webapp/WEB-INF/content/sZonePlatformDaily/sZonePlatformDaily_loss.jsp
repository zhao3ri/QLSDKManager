<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
<div id="wrap" > 
  <%@ include file="/common/header.jsp" %>
  <div class="container">
   <div class="row"> 
   	<div class="col-md-1 ">
   		<%@ include file="/common/reportLeft.jsp" %>
	</div>
    
    <div class=" col-md-10">
        <div class="panel panel-default">
        <div class="panel-body ">
          	  <ul class="nav nav-tabs " >
				  <li id="keep"><a href="${ctx}/sZonePlatformDaily/sZonePlatformDaily_keep.shtml" role="tab">留存统计</a></li>
				  <li id="loss"><a href="${ctx}/sZonePlatformDaily/sZonePlatformDaily_loss.shtml" role="tab">流失统计</a></li>
				  <li id="back"><a href="${ctx}/sZonePlatformDaily/sZonePlatformDaily_back.shtml" role="tab">回流统计</a></li>
			  </ul>
			  <br/>
        	<form action="sZonePlatformDaily_loss.shtml" method="post" id="mainForm">
	        	<div class="form-inline popover-show panel-body list_toolbar">
		      		<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">
			          	<select class="form-control" name="SZonePlatformDaily.gameId"  placeholder="请选择游戏">
							<option value="">所有游戏</option>
							<s:iterator value="gameList" var="item">
								<option value="${item.id}" <c:if test="${item.id==SZonePlatformDaily.gameId }">selected</c:if>>${item.gameName }</option>
							</s:iterator>
						</select>
			        </div>
			        
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择客户端">
			          	<select class="form-control" name="SZonePlatformDaily.clientType"  placeholder="请选择客户端">
							<option value="">客户端类型</option>
							<option value="1" <c:if test="${SZonePlatformDaily.clientType==1 }">selected</c:if>>Android </option>
							<option value="2" <c:if test="${SZonePlatformDaily.clientType==2 }">selected</c:if>>IOS</option>
						</select>
			        </div>
			        
			        <button  type="button" class="btn " onclick="selectChannelZone();"> 渠道、区服</button>
			        	          
		      		<div class="form-group width_btn">
		          		<button  type="submit" class="btn  btn-primary " onclick="search();"><i class="icon-search"></i> 搜索</button>
		          		<button  id="resetBtn"  type="button" class="btn  btn-default " ><i class="icon-eraser"></i> 重置</button>
		        	</div>	
		          		
		        	
		    	</div>
	    	</form>
	    	
        </div>
        <div class="panel panel-default">
          <div class="panel-heading ">
            <h3 class="panel-title"> 游乐渠道用户流失表
              <button type="button" id="excelExport" class=" badge badge-info btn  pull-right  " data-toggle="modal" data-target="#modal_alert" data-backdrop="static"><i class="icon-table"></i> Excel表导出</button>
            </h3>
          </div>
          <div class="panel-body ">
      		<div class="table-responsive">
        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
							<th>游戏</th>
							<th>流失普通用户</th>
							<th>流失付费用户</th>
							<th>流失总用户</th>
            			</tr>
          			</thead>
          			<tbody>
            			<s:if test="sZonePlatformDailies.size>0">
						<s:iterator value="sZonePlatformDailies" var="item">
						<tr>
							<td><s:property value="gameName"/></td>
							<td><s:property value="lossUsers"/></td>
							<td><s:property value="lossPayUsers"/></td>
							<td><s:property value="lossUsers+lossPayUsers"/></td>
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
			$("#resetBtn").click(function(){
				location.assign("sZonePlatformDaily_loss.shtml"); 
				});
			$("#reportLeft_keep").addClass("active");
			$("#loss").addClass("active");
			$("#excelExport").click(function() {
				if(confirm("您确认导出Excel？")){
					$("#mainForm").attr("action","sZonePlatformDaily_excelExport.shtml?chartType=2");
					$("#mainForm").submit();
					$("#mainForm").attr("action","sZonePlatformDaily_loss.shtml");
				}
			});
		});
		require(
            [
                'echarts',
                'echarts/chart/line',
                'echarts/chart/bar',
                'echarts/chart/pie',
                'echarts/chart/funnel'
            ],
            function (ec) {
                var myChart = ec.init(document.getElementById('chartData')); 
				var option = eval('(${optionJson})');
                myChart.setOption(option); 
            }
	 );
	</script>
</body>
</html>