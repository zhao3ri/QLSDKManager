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
        	<form action="sZonePlatformDaily_translate.shtml" method="post" id="mainForm">
	        	<div class="form-inline popover-show panel-body list_toolbar">
		      		<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">
			          	<select class="form-control" name="SZonePlatformDaily.appId"  placeholder="请选择游戏">
							<option value="">所有游戏</option>
							<s:iterator value="gameList" var="item">
								<option value="${item.id}" <c:if test="${item.id==SZonePlatformDaily.appId }">selected</c:if>>${item.appName }</option>
							</s:iterator>
						</select>
			        </div>
			        
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择客户端">
			          	<select class="form-control" name="SZonePlatformDaily.clientType"  placeholder="请选择客户端">
							<option value="">所有平台</option>
							<option value="1" <c:if test="${SZonePlatformDaily.clientType==1 }">selected</c:if>>Android </option>
							<option value="2" <c:if test="${SZonePlatformDaily.clientType==2 }">selected</c:if>>IOS</option>
						</select>
			        </div>
			        
			        <button  type="button" class="btn " onclick="selectChannelZone();"> 渠道、区服</button>
			        
			        <div class="form-group   width_input" >
							<div class="input-group  input-append date form_date "  data-date-format="yyyy-mm-dd" >
								<input  class="form-control " type="text" name="SZonePlatformDaily.startDate" value="${sZonePlatformDaily.startDate}"  readonly  placeholder="开始日期" data-toggle="popover"  data-placement="top" data-content="请选择开始日期"/>
								<span class="add-on input-group-addon"><i class="icon-remove"></i></span> 
								<span class="add-on input-group-addon"><i class="icon-calendar"></i></span>
			                </div>
			          </div>
			          	至
			          <div class="form-group   width_input" >
							<div class="input-group  input-append date form_date "  data-date-format="yyyy-mm-dd" >
								<input  class="form-control " name="SZonePlatformDaily.endDate" value="${sZonePlatformDaily.endDate}"  type="text"  readonly  placeholder="结束日期" data-toggle="popover"  data-placement="top" data-content="请选择结束日期"/>
								<span class="add-on input-group-addon"><i class="icon-remove"></i></span> 
								<span class="add-on input-group-addon"><i class="icon-calendar"></i></span>
			                </div>
			          </div>

			         <button  type="button" class="btn " onclick="selectCompareChannelZone();"> 对比渠道、区服</button>
			          
		          	 <div class="form-group   width_input" >
							<div class="input-group  input-append date form_date "  data-date-format="yyyy-mm-dd" >
								<input  class="form-control " type="text" name="compareStartDate" value="${compareStartDate}"  readonly  placeholder="对比开始日期" data-toggle="popover"  data-placement="top" data-content="请选择开始日期"/>
								<span class="add-on input-group-addon"><i class="icon-remove"></i></span> 
								<span class="add-on input-group-addon"><i class="icon-calendar"></i></span>
			                </div>
			          </div>
			          	至
			          <div class="form-group   width_input" >
							<div class="input-group  input-append date form_date "  data-date-format="yyyy-mm-dd" >
								<input  class="form-control " name="compareEndDate" value="${compareEndDate}"  type="text"  readonly  placeholder="对比结束日期" data-toggle="popover"  data-placement="top" data-content="请选择结束日期"/>
								<span class="add-on input-group-addon"><i class="icon-remove"></i></span> 
								<span class="add-on input-group-addon"><i class="icon-calendar"></i></span>
			                </div>
			          </div> 
			          
		      		<div class="form-group width_btn">
		          		<button  type="submit" class="btn  btn-primary " onclick="search();"><i class="icon-search"></i> 搜索</button>
		          		<button  id="resetBtn"  type="button" class="btn  btn-default " ><i class="icon-eraser"></i> 重置</button>
		        	</div>	
		          		
		        	
		    	</div>
	    	</form>
          
              <div class="panel-heading ">
            <h3 class="panel-title"> 统计图</h3>
          </div>
          <div class="panel-body " > 
          <s:if test="sZonePlatformDailies.size>0">
			  <div id="chartData"  style="height:400px"></div>
		  </s:if>
		  <s:else>
          <div class="table-responsive">
          <table class="table table-hover table-striped table-bordered table-condensed table-big">
         <tr><td colspan="5" style="text-align: center;"> 当前列表没有数据！</td></tr>
          </table>
          </div>
          </s:else>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="panel-heading ">
            <h3 class="panel-title"> XX区服—渠道表
              <button type="button" id="excelExport" class=" badge badge-info btn  pull-right  " data-toggle="modal" data-target="#modal_alert" data-backdrop="static"><i class="icon-table"></i> Excel表导出</button>
            </h3>
          </div>
          <div class="panel-body ">
      		<div class="table-responsive">
        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
							<th>时间</th>
							<th>渠道/区服</th>
							<th>创角用户</th>
							<th>注册用户</th>
							<th>新增激活设备</th>
							<th>注册激活转化率</th>
							<th>创角注册转化率</th>
            			</tr>
          			</thead>
          			<tbody>
            			<s:if test="sZonePlatformDailies.size>0">
						<s:iterator value="sZonePlatformDailies" var="item">
						<tr>
							<td><s:date name="#item.statDate" format="yyyy-MM-dd"/></td>
							<td><s:property value="zoneName"/></td>
							<td><s:property value="roleUsers"/></td>
							<td><s:property value="regUsers"/></td>
							<td><s:property value="newDevices"/></td>
							<td><s:property value="regDevices/newDevices"/></td>
							<td><s:property value="roleDevices/regDevices"/></td>
						</tr>
						</s:iterator>
						</s:if>
						<s:else>
						<tr>
							<td colspan="7" style="text-align: center;">当前列表没有数据！</td>
						</tr>
						</s:else>
					</tbody>
        		</table>
      		</div>
      		
      		<div class="table-responsive">
        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
							<th>时间</th>
							<th>渠道/区服</th>
							<th>创角用户</th>
							<th>注册用户</th>
							<th>新增激活设备</th>
							<th>注册激活转化率</th>
							<th>创角注册转化率</th>
            			</tr>
          			</thead>
          			<tbody>
            			<s:if test="recharges.size>0">
						<s:iterator value="recharges" var="item">
						<tr>
							<td><s:date name="dater" format="yyyy-MM-dd"/></td>
							<td><s:property value="firstRecharge"/></td>
							<td><s:property value="firstRechargePerson"/></td>
						</tr>
						</s:iterator>
						</s:if>
						<s:else>
						<tr>
							<td colspan="7" style="text-align: center;">当前列表没有数据！</td>
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
				location.assign("sZonePlatformDaily_translate.shtml"); 
				});
			$("#reportLeft_8").addClass("active");
			$("#excelExport").click(function() {
				if(confirm("您确认导出Excel？")){
					$("#mainForm").attr("action","sZonePlatformDaily_excelExport.shtml?chartType=1");
					$("#mainForm").submit();
					$("#mainForm").attr("action","sZonePlatformDaily_translate.shtml");
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