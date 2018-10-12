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
    
    <div class=" col-md-11 ">
        <div class="panel panel-default">
        	<form action="reportDaily_operate.shtml" method="post" id="mainForm">
	        	<div class="form-inline popover-show panel-body list_toolbar">
		      		<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">
		      			<input type="hidden" id="type" name="groupType" value="${groupType }">
		      			<input type="hidden" id="compareType" name="compareGroupType" value="${compareGroupType }">
		      			<input type="hidden" id="channelIds" name="channelIds" value="${channelIds }">
		      			<input type="hidden" id="compareChannelIds" name="compareChannelIds" value="${compareChannelIds }">
		      			<input type="hidden" id="zoneIds" name="zoneIds" value="${zoneIds }">
		      			<input type="hidden" id="compareZoneIds" name="compareZoneIds" value="${compareZoneIds }">
			          	<select class="form-control" name="appId" id="appId"  placeholder="请选择游戏">
							<s:iterator value="allGames" var="item">
								<option value="${item.id }" <c:if test="${item.id==appId }">selected</c:if>>${item.appName }</option>
							</s:iterator>
						</select>
			        </div>
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择客户端">
			          	<mt:selectState name="clientType" showType="select" stateType="clientType" value="${clientType}" clazz="form-control" emptyString="IOS+ANDROID"/>
			        </div>
			        <button  type="button" class="btn " onclick="selectChannelZone();"> 渠道、区服</button>
		          <div class="form-group    width_input"  data-toggle="popover"  data-placement="top" data-content="时间">
		            <input  class="form-control daterange" type="text" readonly name="selectRange" value="${selectRange }"  placeholder="时间选择"  sType="search" />
		          </div>
		      		
 		      		<div class="form-group width_btn">
 		          		<button  type="button" class="btn  btn-primary " onclick="search();"><i class="icon-search"></i> 搜索</button>
 		        	</div>
		        	<button type="button" id="excelExport" onclick="excelExportData();" class=" badge badge-info btn  pull-right  " data-toggle="modal" data-target="#modal_alert" data-backdrop="static"><i class="icon-table"></i> Excel表导出</button>
		    	</div>
	    	</form>
        </div>
        <div class="panel panel-default">
          <div class="panel-heading ">
            <h3 class="panel-title"> 
            	详细信息
            </h3>
          </div>
          <div class="panel-body ">
      		<div class="table-responsive">
      			<s:set var="itemSize" value="#result['data'].size"></s:set>
      			<s:if test="result['data'].size > 0">
      			<div class="table-responsive" style="margin-bottom: 10px; overflow-x: scroll; overflow-y: hidden; width: 100%;">
      				
		      		 <table style="white-space: nowrap;" class="table table-hover table-striped table-bordered table-condensed">
	            			<tr>
	            				<th rowspan='<s:property value="result['data'].size + 1"/>' style="background-color: #f2f2f2;"><span style="margin:5px 10px; display:block;">基<br/>本<br/>数<br/>据</span></th>
	            				<th>时间</th>
	            				<th>创角用户</th>
	            				<th>注册用户</th>
	            				<th>新增激活设备</th>
	            				<th>游戏启动次数</th>
	            				<!--<th>玩家平均在线时长(小时)</th>-->
	            				<th>注册激活转化率</th>
	            				<th>创角注册转化率</th>
	            				<th>活跃用户</th>
	            				<th>次日留存</th>
	            				<th>三日留存</th>
	            				<th>四日留存</th>
	            				<th>五日留存</th>
	            				<th>六日留存</th>
	            				<th>七日留存</th>
	            				<th>十四日留存</th>
	            				<th>三十日留存</th>
	            				<th rowspan='<s:property value="result['data'].size + 1"/>' style="background-color: #f2f2f2"><span style="margin:5px 10px; display:block;">付<br>费<br>数<br>据</span></th>
	            				<th>充值金额</th>
	            				<th>充值次数</th>
	            				<th>充值人数</th>
	            				<th>新用户充值额</th>
	            				<th>新用户付费人数</th>
	            				<th>新用户付费次数</th>
	            				<th>新用户付费率</th>
	            				<th>新用户ARPU</th>
	            				<th>新用户付费ARPU</th>
	            				<th>首次付费充值额</th>
	            				<th>首次充值用户数</th>
	            				<th>首次付费ARPU</th>

	            				<th rowspan='<s:property value="result['data'].size + 1"/>' style="background-color: #f2f2f2"><span style="margin:5px 10px; display:block;">累<br>计<br>数<br>据</span></th>
	            				<th>累计注册设备</th>
								<th>累计创角用户</th>
								<th>累计注册用户</th>
								<th>总活跃用户</th>
								<th>充值总额</th>
								<th>充值总次数 </th>
								<th>充值总人数 </th>
								<th>总付费ARPU</th>
								<th>总注册ARPU</th>
	            			</tr>
	            			<!-- 第一个模块 -->
			          		<s:set value="0" var="roleUsersTotal"></s:set>
		          			<s:set value="0" var="regUsersTotal"></s:set>
		          			<s:set value="0" var="regDevicesTotal"></s:set>
		          			<s:set value="0" var="startTimesTotal"></s:set>
		          			<s:set value="0" var="avgOnlineTimeTotal"></s:set>
		          			<s:set value="0" var="newDevicesTotal"></s:set>
		          			<s:set value="0" var="roleDevicesTotal"></s:set>
		          			<s:set value="0" var="activeUsersTotal"></s:set>
		          			<!-- 第二个模块 -->
		          			<s:set value="0" var="payAmountTotal"></s:set>
		          			<s:set value="0" var="payTimesTotal"></s:set>
		          			<s:set value="0" var="payUsersTotal"></s:set>
		          			<s:set value="0" var="newUserPayAmountTotal"></s:set>
		          			<s:set value="0" var="newUserPaysTotal"></s:set>
		          			<s:set value="0" var="newUserPayTimesTotal"></s:set>
		          			<s:set value="0" var="firstPayAmountTotal"></s:set>
	          				<s:set value="0" var="firstPayUsersTotal"></s:set>
	          				<!-- 第三个模块 -->
	          				<s:set value="0" var="totaldevicesTotal"></s:set>
	          				<s:set value="0" var="totalRoleUserTotal"></s:set>
	          				<s:set value="0" var="totalRegUserTotal"></s:set>
	          				<s:set value="0" var="totalActiveUsersTotal"></s:set>
	          				<s:set value="0" var="totalPayAmountTotal"></s:set>
	          				<s:set value="0" var="totalPayTimesTotal"></s:set>
	          				<s:set value="0" var="totalPayUsersTotal"></s:set>
	          			<s:iterator value="result['data']" var="item" status="st">
	          				<!-- 第一个模块 -->
	          				<s:set value="#item.roleUsers+#roleUsersTotal" var="roleUsersTotal"></s:set>
	          				<s:set value="#item.regUsers+#regUsersTotal" var="regUsersTotal"></s:set>
	          				<s:set value="#item.regDevices+#regDevicesTotal" var="regDevicesTotal"></s:set>
	          				<s:set value="(#item.startTimes!=null?#item.startTimes:0)+#startTimesTotal" var="startTimesTotal"></s:set>
	          				<s:set value="#item.avgOnlineTime+#avgOnlineTimeTotal" var="avgOnlineTimeTotal"></s:set>
	          				<s:set value="#item.newDevices+#newDevicesTotal" var="newDevicesTotal"></s:set>
	          				<s:set value="#item.roleDevices+#roleDevicesTotal" var="roleDevicesTotal"></s:set>
	          				<s:set value="#item.activeUsers+#activeUsersTotal" var="activeUsersTotal"></s:set>
	          				
	          				<!-- 第二个模块 -->
	          				<s:set value="#item.payAmount+#payAmountTotal" var="payAmountTotal"></s:set>
	          				<s:set value="#item.payTimes+#payTimesTotal" var="payTimesTotal"></s:set>
	          				<s:set value="#item.payUsers+#payUsersTotal" var="payUsersTotal"></s:set>
	          				<s:set value="#item.newUserPayAmount+#newUserPayAmountTotal" var="newUserPayAmountTotal"></s:set>
	          				<s:set value="#item.newUserPays+#newUserPaysTotal" var="newUserPaysTotal"></s:set>
	          				<s:set value="#item.newUserPayTimes+#newUserPayTimesTotal" var="newUserPayTimesTotal"></s:set>
	          				<s:set value="#item.firstPayAmount+#firstPayAmountTotal" var="firstPayAmountTotal"></s:set>
	          				<s:set value="#item.firstPayUsers+#firstPayUsersTotal" var="firstPayUsersTotal"></s:set>
	          				
	          				<!-- 第三个模块 -->
	          				<s:set value="#item.totaldevices" var="totaldevicesTotal"></s:set>
	          				<s:set value="#item.totalRoleUser" var="totalRoleUserTotal"></s:set>
	          				<s:set value="#item.totalRegUser" var="totalRegUserTotal"></s:set>
	          				<s:set value="#item.totalActiveUsers" var="totalActiveUsersTotal"></s:set>
	          				<s:set value="#item.totalPayAmount" var="totalPayAmountTotal"></s:set>
	          				<s:set value="#item.totalPayTimes" var="totalPayTimesTotal"></s:set>
	          				<s:set value="#item.totalPayUsers" var="totalPayUsersTotal"></s:set>
	          				
	          				<tr>
	          					<!-- 第一个模块 -->
	          					<td><s:date name="statDate" format="yyyy-MM-dd"/></td>
	          					<td>${roleUsers }</td>
	          					<td>${regUsers }</td>
	          					<td>${regDevices }</td>
	          					<td>${startTimes }</td>
	          					<!--<td><fmt:formatNumber  value="${avgOnlineTime/3600 }" pattern="0.00" /></td>-->
          						<c:if test="${newDevices > 0 }">
          							<!--<td><fmt:formatNumber  value="${regDevices * 100/newDevices }" pattern="0.00" />%</td>-->
          							<td><fmt:formatNumber  value="${regUsers * 100/regDevices }" pattern="0.00" />%</td>
          						</c:if>
          						<c:if test="${newDevices == 0 }">
          							<td>0.00%</td>
          						</c:if>
          						<c:if test="${regDevices > 0 }">
          							<!--<td><fmt:formatNumber  value="${roleDevices * 100/regDevices }" pattern="0.00" />%</td>-->
          							<td><fmt:formatNumber  value="${roleUsers * 100/regUsers}" pattern="0.00" />%</td>
          						</c:if>
          						<c:if test="${regDevices== 0 }">
          							<td>0.00%</td>
          						</c:if>
          						<td>${activeUsers }</td>
          						<td>
          							<c:if test="${delDay >= 1 }"><fmt:formatNumber  value="${keepUser1/roleUsers * 100 }" pattern="0.00" />%</c:if>
          							<c:if test="${delDay < 1 }">--</c:if>
          						</td>
          						<td>
          							<c:if test="${delDay >= 2 }"><fmt:formatNumber  value="${keepUser3/roleUsers * 100 }" pattern="0.00" />%</c:if>
          							<c:if test="${delDay < 2 }">--</c:if>
          						</td>
          						<td>
          							<c:if test="${delDay >= 3 }"><fmt:formatNumber  value="${keepUser4/roleUsers * 100 }" pattern="0.00" />%</c:if>
          							<c:if test="${delDay < 3 }">--</c:if>
          						</td>
          						<td>
          							<c:if test="${delDay >= 4 }"><fmt:formatNumber  value="${keepUser5/roleUsers * 100 }" pattern="0.00" />%</c:if>
          							<c:if test="${delDay < 4 }">--</c:if>
          						</td>
          						<td>
          							<c:if test="${delDay >= 5 }"><fmt:formatNumber  value="${keepUser6/roleUsers * 100 }" pattern="0.00" />%</c:if>
          							<c:if test="${delDay < 5 }">--</c:if>
          						</td>
          						<td>
          							<c:if test="${delDay >= 6 }"><fmt:formatNumber  value="${keepUser7/roleUsers * 100 }" pattern="0.00" />%</c:if>
          							<c:if test="${delDay < 6 }">--</c:if>
          						</td>
          						<td>
          							<c:if test="${delDay >= 13 }"><fmt:formatNumber  value="${keepUser14/roleUsers * 100 }" pattern="0.00" />%</c:if>
          							<c:if test="${delDay < 13 }">--</c:if>
          						</td>
          						<td>
          							<c:if test="${delDay >= 29 }"><fmt:formatNumber  value="${keepUser30/roleUsers * 100 }" pattern="0.00" />%</c:if>
          							<c:if test="${delDay < 29 }">--</c:if>
          						</td>
          						<!-- 第二个模块 -->
          						<td><fmt:formatNumber  value="${payAmount/100 }" pattern="0.00" /></td>
          						<td>${payTimes }</td>
          						<td>${payUsers }</td>
          						<td><fmt:formatNumber  value="${newUserPayAmount/100 }" pattern="0.00" /> </td>
          						<td>${newUserPays }</td>
          						<td>${newUserPayTimes }</td>
          						<c:if test="${roleUsers > 0 }">
          							<td><fmt:formatNumber  value="${newUserPays * 100/roleUsers }" pattern="0.00" />%</td>
          						</c:if>
          						<c:if test="${roleUsers == 0 }">
          							<td>0.00%</td>
          						</c:if>
          						<c:if test="${roleUsers > 0 }">
          							<td><fmt:formatNumber  value="${newUserPayAmount/100/roleUsers }" pattern="0.00" /></td>
          						</c:if>
          						<c:if test="${roleUsers == 0 }">
          							<td>0.00</td>
          						</c:if>
          						<c:if test="${newUserPays > 0 }">
          							<td><fmt:formatNumber  value="${newUserPayAmount/100/newUserPays }" pattern="0.00" /></td>
          						</c:if>
          						<c:if test="${newUserPays == 0 }">
          							<td>0.00</td>
          						</c:if>
          						<td><fmt:formatNumber  value="${firstPayAmount/100 }" pattern="0.00" /></td>
          						<td>${firstPayUsers }</td>
          						<c:if test="${firstPayUsers > 0 }">
          							<td><fmt:formatNumber  value="${firstPayAmount/100/firstPayUsers }" pattern="0.00" /></td>
          						</c:if>
          						<c:if test="${firstPayUsers == 0 }">
          							<td>0.00</td>
          						</c:if>
          						<!-- 第三个模块 -->
          						<td>${totaldevices }</td>
          						<td>${totalRoleUser }</td>
          						<td>${totalRegUser }</td>
          						<td>${totalActiveUsers }</td>
          						<td><fmt:formatNumber  value="${totalPayAmount/100 }" pattern="0.00" /></td>
          						<td>${totalPayTimes }</td>
          						<td>${totalPayUsers }</td>
          						<c:if test="${totalPayUsers > 0 }">
          							<td><fmt:formatNumber  value="${totalPayAmount/100/totalPayUsers }" pattern="0.00" /></td>
          						</c:if>
          						<c:if test="${totalPayUsers == 0 }">
          							<td>0.00</td>
          						</c:if>
          						<c:if test="${totalRegUser > 0 }">
          							<td><fmt:formatNumber  value="${totalPayAmount/100/totalRegUser }" pattern="0.00" /></td>
          						</c:if>
          						<c:if test="${totalRegUser == 0 }">
          							<td>0.00</td>
          						</c:if>
	          				</tr>
	          				</s:iterator>
	          				<tr>
	          					<!-- 第一个模块 -->
	          					<td>合计</td>
	          					<td></td>
	          					<td>${roleUsersTotal }</td>
	          					<td>${regUsersTotal }</td>
	          					<td>${regDevicesTotal }</td>
	          					<td>${startTimesTotal }</td>
	          					<!--<td><fmt:formatNumber  value="${avgOnlineTimeTotal/3600 }" pattern="0.00" /></td>-->
          						<c:if test="${newDevicesTotal > 0 }">
          							<td><fmt:formatNumber  value="${regDevicesTotal * 100/newDevicesTotal }" pattern="0.00" />%</td>
          						</c:if>
          						<c:if test="${newDevicesTotal == 0 }">
          							<td>0.00%</td>
          						</c:if>
          						<c:if test="${regDevicesTotal > 0 }">
          							<td><fmt:formatNumber  value="${roleDevicesTotal * 100/regDevicesTotal }" pattern="0.00" />%</td>
          						</c:if>
          						<c:if test="${regDevicesTotal== 0 }">
          							<td>0.00%</td>
          						</c:if>
          						<td>${activeUsersTotal }</td>
          						<td></td>
          						<td></td>
          						<td></td>
          						<td></td>
          						<td></td>
          						<td></td>
          						<td></td>
          						<td></td>
          						<!-- 第二个模块 -->
          						<td>合计</td>
          						<td><fmt:formatNumber  value="${payAmountTotal/100 }" pattern="0.00" /></td>
          						<td>${payTimesTotal }</td>
          						<td>${payUsersTotal }</td>
          						<td><fmt:formatNumber  value="${newUserPayAmountTotal/100 }" pattern="0.00" /> </td>
          						<td>${newUserPaysTotal }</td>
          						<td>${newUserPayTimesTotal }</td>
          						<c:if test="${roleUsersTotal > 0 }">
          							<td><fmt:formatNumber  value="${newUserPaysTotal * 100/roleUsersTotal }" pattern="0.00" />%</td>
          						</c:if>
          						<c:if test="${roleUsersTotal == 0 }">
          							<td>0.00%</td>
          						</c:if>
          						<c:if test="${roleUsersTotal > 0 }">
          							<td><fmt:formatNumber  value="${newUserPayAmountTotal/100/roleUsersTotal }" pattern="0.00" /></td>
          						</c:if>
          						<c:if test="${roleUsersTotal == 0 }">
          							<td>0.00</td>
          						</c:if>
          						<c:if test="${newUserPaysTotal > 0 }">
          							<td><fmt:formatNumber  value="${newUserPayAmountTotal/100/newUserPaysTotal }" pattern="0.00" /></td>
          						</c:if>
          						<c:if test="${newUserPaysTotal == 0 }">
          							<td>0.00</td>
          						</c:if>
          						
          						<td><fmt:formatNumber  value="${firstPayAmountTotal/100 }" pattern="0.00" /></td>
          						<td>${firstPayUsersTotal }</td>
          						<c:if test="${firstPayUsersTotal > 0 }">
          							<td><fmt:formatNumber  value="${firstPayAmountTotal/100/firstPayUsersTotal }" pattern="0.00" /></td>
          						</c:if>
          						<c:if test="${firstPayUsersTotal == 0 }">
          							<td>0.00</td>
          						</c:if>
          						<!-- 第三个模块 -->
          						<td>合计</td>
          						<td>${totaldevicesTotal }</td>
          						<td>${totalRoleUserTotal }</td>
          						<td>${totalRegUserTotal }</td>
          						<td>${totalActiveUsersTotal }</td>
          						<td><fmt:formatNumber  value="${totalPayAmountTotal/100 }" pattern="0.00" /></td>
          						<td>${totalPayTimesTotal }</td>
          						<td>${totalPayUsersTotal }</td>
          						<c:if test="${totalPayUsersTotal > 0 }">
          							<td><fmt:formatNumber  value="${totalPayAmountTotal/100/totalPayUsersTotal }" pattern="0.00" /></td>
          						</c:if>
          						<c:if test="${totalPayUsersTotal == 0 }">
          							<td>0.00</td>
          						</c:if>
          						<c:if test="${totalRegUserTotal > 0 }">
          							<td><fmt:formatNumber  value="${totalPayAmountTotal/100/totalRegUserTotal }" pattern="0.00" /></td>
          						</c:if>
          						<c:if test="${totalRegUserTotal == 0 }">
          							<td>0.00</td>
          						</c:if>
	          				</tr>
	          			<%-- <thead>
	            			<tr>
	            				<th></th>
	            				<s:iterator value="result['data']" var="item" status="st">
	            					<th><b><s:date name="statDate" format="yyyy-MM-dd"/> </b></th>
	            				</s:iterator>
	            			</tr>
	            			<tr>
	            				<th colspan='<s:property value="result['data'].size + 1"/>'>基本数据</th>
	            			</tr>
	          			</thead>
	          			<tbody>
	          				<tr>
	          					<td>创角用户</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${roleUsers }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>注册用户</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${regUsers }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>新增注册设备</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${regDevices }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>游戏启动次数</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${startTimes }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>玩家平均在线时长(小时)</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          					
	          						<fmt:formatNumber  value="${avgOnlineTime}" pattern="0"  var="total"/>
				          				<td>
				          					<fmt:formatNumber  value="${avgOnlineTime/3600 }" pattern="0.00" />
				          				</td>		
	          						<td>${avgOnlineTime }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>注册激活转化率</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<c:if test="${newDevices > 0 }">
	          							<td><fmt:formatNumber  value="${regDevices * 100/newDevices }" pattern="0.00" />%</td>
	          						</c:if>
	          						<c:if test="${newDevices == 0 }">
	          							<td>0.00%</td>
	          						</c:if>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>创角注册转化率</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<c:if test="${regDevices > 0 }">
	          							<td><fmt:formatNumber  value="${roleDevices * 100/regDevices }" pattern="0.00" />%</td>
	          						</c:if>
	          						<c:if test="${regDevices== 0 }">
	          							<td>0.00%</td>
	          						</c:if>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>活跃用户</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${activeUsers }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>次日留存</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td><fmt:formatNumber  value="${keepUser1/roleUsers * 100 }" pattern="0.00" />%</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>三日留存</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td><fmt:formatNumber  value="${keepUser3/roleUsers * 100 }" pattern="0.00" />%</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>七日留存</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td><fmt:formatNumber  value="${keepUser7/roleUsers * 100 }" pattern="0.00" />%</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>十四日留存</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td><fmt:formatNumber  value="${keepUser14/roleUsers * 100 }" pattern="0.00" />%</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>三十日留存</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td><fmt:formatNumber  value="${keepUser30/roleUsers * 100 }" pattern="0.00" />%</td>
	          					</s:iterator>
	          				</tr>
	          				<thead>
		          				<tr>
		            				<th colspan='<s:property value="result['data'].size + 1"/>'>付费数据</th>
		            			</tr>
	            			</thead>
	            			<tr>
	          					<td>充值金额</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td><fmt:formatNumber  value="${payAmount/100 }" pattern="0.00" /></td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>充值次数</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${payTimes }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>充值人数</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${payUsers }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>新用户充值额</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td><fmt:formatNumber  value="${newUserPayAmount/100 }" pattern="0.00" /> </td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>新用户付费人数</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${newUserPays }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>新用户付费次数</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${newUserPayTimes }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>新用户付费率</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<c:if test="${regUsers > 0 }">
	          							<td><fmt:formatNumber  value="${newUserPays * 100/roleUsers }" pattern="0.00" />%</td>
	          						</c:if>
	          						<c:if test="${regUsers == 0 }">
	          							<td>0.00%</td>
	          						</c:if>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>新用户ARPU</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<c:if test="${roleUsers > 0 }">
	          							<td><fmt:formatNumber  value="${newUserPayAmount/100/roleUsers }" pattern="0.00" /></td>
	          						</c:if>
	          						<c:if test="${roleUsers == 0 }">
	          							<td>0.00</td>
	          						</c:if>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>新用户付费ARPU</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<c:if test="${newUserPays > 0 }">
	          							<td><fmt:formatNumber  value="${newUserPayAmount/100/newUserPays }" pattern="0.00" /></td>
	          						</c:if>
	          						<c:if test="${newUserPays == 0 }">
	          							<td>0.00</td>
	          						</c:if>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>首次付费充值额</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td><fmt:formatNumber  value="${firstPayAmount/100 }" pattern="0.00" /></td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>首次充值用户数</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${firstPayUsers }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>首次付费ARPU</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<c:if test="${firstPayUsers > 0 }">
	          							<td><fmt:formatNumber  value="${firstPayAmount/100/firstPayUsers }" pattern="0.00" /></td>
	          						</c:if>
	          						<c:if test="${firstPayUsers == 0 }">
	          							<td>0.00</td>
	          						</c:if>
	          					</s:iterator>
	          				</tr>
	          				<thead>
		          				<tr>
		            				<th colspan='<s:property value="result['data'].size + 1"/>'>累计数据</th>
		            			</tr>
	            			</thead>
	            			<tr>
	          					<td>累计注册设备</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${totaldevices }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>累计创角用户</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${totalRoleUser }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>累计注册用户</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${totalRegUser }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>总活跃用户</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${totalActiveUsers }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>充值总额</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td><fmt:formatNumber  value="${totalPayAmount/100 }" pattern="0.00" /></td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>充值总次数</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${totalPayTimes }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>充值总人数</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<td>${totalPayUsers }</td>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>总付费ARPU</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<c:if test="${totalPayUsers > 0 }">
	          							<td><fmt:formatNumber  value="${totalPayAmount/100/totalPayUsers }" pattern="0.00" /></td>
	          						</c:if>
	          						<c:if test="${totalPayUsers == 0 }">
	          							<td>0.00</td>
	          						</c:if>
	          					</s:iterator>
	          				</tr>
	          				<tr>
	          					<td>总注册ARPU</td>
	          					<s:iterator value="result['data']" var="item" status="st">
	          						<c:if test="${totalRegUser > 0 }">
	          							<td><fmt:formatNumber  value="${totalPayAmount/100/totalRegUser }" pattern="0.00" /></td>
	          						</c:if>
	          						<c:if test="${totalRegUser == 0 }">
	          							<td>0.00</td>
	          						</c:if>
	          					</s:iterator>
	          				</tr>
          			</tbody> --%>
         		</table>
         		</div>
         		</s:if>
				<s:else>
					<table class="table table-hover table-striped table-bordered table-condensed table-big">
				    	<tr>
				    		<td align="center">没有数据！</td>
				    	</tr>
			    	</table>
			    </s:else>
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
			$("#reportLeft_3").addClass("active");
			$("#zone").addClass("active");
		});
		
		function selectChannelZone(){
			var appId = $("#appId").val();
			if($.trim(appId) == ""){
				errorTip("请先选择游戏！");
				return;
			}
			art.dialog.open("${ctx}/report/report_getChannelZone.shtml?appId="+appId+"&isCompare=0", 
					{
						title:'筛选',
					    id:'addBox',
					    fixed:true,
					    lock:true,
					    background:'#000',
					    opacity:0,
					    height:'60%',
					    width:'60%',
					    fited:true				
					});
		}
		
		function selectCompareChannelZone(){
			var appId = $("#appId").val();
			if($.trim(appId) == ""){
				errorTip("请先选择游戏！");
				return;
			}
			art.dialog.open("${ctx}/report/report_getChannelZone.shtml?appId="+appId+"&isCompare=1", 
				{
					title:'筛选',
					id:'addBox',
					fixed:true,
					lock:true,
					background:'#000',
					opacity:0,
					height:'60%',
					width:'60%',
					fited:true
				}
			);
		}
		
		function excelExportData(){
			if(confirm("您确认导出Excel？")){
				$("#mainForm").attr("action","reportDaily_operateExport.shtml");
				$("#mainForm").submit();
				$("#mainForm").attr("action","reportDaily_operate.shtml");
			}
		}
	</script>
</body>
</html>