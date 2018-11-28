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
				  <li id="keep"><a href="/sZonePlatformDaily/sZonePlatformDaily_keep.shtml" identity="tab">留存统计</a></li>
				  <li id="loss"><a href="/sZonePlatformDaily/sZonePlatformDaily_loss.shtml" identity="tab">流失统计</a></li>
				  <li id="back"><a href="/sZonePlatformDaily/sZonePlatformDaily_back.shtml" identity="tab">回流统计</a></li>
			  </ul>
			  <br/>
        	<form action="sZonePlatformDaily_keep.shtml" method="post" id="mainForm">
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
   
           <br> 
                   <div class="panel panel-default">
          <div class="panel-heading ">
            <h3 class="panel-title"> 留存统计表
              <button type="button" id="excelExport" class=" badge badge-info btn  pull-right  " data-toggle="modal" data-target="#modal_alert" data-backdrop="static"><i class="icon-table"></i> Excel表导出</button>
            </h3>
          </div>
          <div class="panel-body ">
      		<div class="table-responsive">
        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
            			    <th></th>
            			    <th>游戏/渠道/区服</th>
							<th>新增创角用户</th>
							<th>次日留存</th>
							<th>三日留存</th>
							<th>七日留存</th>
							<th>十四日留存</th>
							<th>三十日留存</th>
            			</tr>
          			</thead>
          			<tbody>
            			<s:if test="sZonePlatformDailies.size>0">
						<s:iterator value="sZonePlatformDailies" var="item">
						<tr>
						   <td><s:date name="#item.statDate" format="yyyy-MM-dd"/></td>
							<td><s:property value="gameName"/></td>
							<td><s:property value="roleUsers"/></td>
							<td><s:property value="keepRate1"/></td>
							<td><s:property value="keepRate3"/></td>
							<td><s:property value="keepRate7"/></td>
							<td><s:property value="keepRate14"/></td>
							<td><s:property value="keepRate30"/></td>
						</tr>
						</s:iterator>
						</s:if>
						<s:else>
						<tr>
							<td colspan="8" style="text-align: center;">当前列表没有数据！</td>
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
  </div>
</div>
    <%@ include file="/common/footer.jsp" %>      
	<script type="text/javascript">
		$(document).ready(function() {
			$("#resetBtn").click(function(){
				location.assign("sZonePlatformDaily_keep.shtml"); 
				});
			$("#reportLeft_7").addClass("active");
			$("#keep").addClass("active");
			$("#excelExport").click(function() {
				if(confirm("您确认导出Excel？")){
					$("#mainForm").attr("action","sZonePlatformDaily_excelExport.shtml?chartType=2");
					$("#mainForm").submit();
					$("#mainForm").attr("action","sZonePlatformDaily_keep.shtml");
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