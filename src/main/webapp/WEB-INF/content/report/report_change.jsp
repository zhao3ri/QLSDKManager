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
        	<form action="reportDaily_change.shtml" method="post" id="mainForm">
	        	<div class="form-inline popover-show panel-body list_toolbar">
		      		<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">
		      		
		      			<input type="hidden" id="type" name="groupType" value="${groupType }">
		      			<input type="hidden" id="compareType" name="compareGroupType" value="${compareGroupType }">
		      			
		      			<input type="hidden" id="channelIds" name="channelIds" value="${channelIds }">
		      			<input type="hidden" id="compareChannelIds" name="compareChannelIds" value="${compareChannelIds }">
		      			<input type="hidden" id="zoneIds" name="zoneIds" value="${zoneIds }">
		      			<input type="hidden" id="compareZoneIds" name="compareZoneIds" value="${compareZoneIds }">
		      			
			          	<select class="form-control" name="gameId" id="gameId"  placeholder="请选择游戏">
							<s:iterator value="allGames" var="item">
								<option value="${item.id }" <c:if test="${item.id==gameId }">selected</c:if>>${item.gameName }</option>
							</s:iterator>
						</select>
			        </div>
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择客户端">
			          	<mt:selectState name="clientType" showType="select" stateType="clientType" value="${clientType}" clazz="form-control" emptyString="IOS+ANDROID"/>
			        </div>
			        <button  type="button" class="btn " onclick="selectChannelZone();"> 渠道、区服</button>
			        <button  type="button" class="btn " onclick="selectCompareChannelZone();"> 对比渠道、区服</button>
		          <div class="form-group    width_input"  data-toggle="popover"  data-placement="top" data-content="时间">
		            <input  class="form-control daterange" type="text" readonly name="selectRange" value="${selectRange }"  placeholder="时间选择" sType="searchAndValid" />
		          </div>
		          <div class="form-group    width_input"  data-toggle="popover"  data-placement="top" data-content="对比时间">
		            <input  class="form-control daterange" type="text" readonly name="compareSelectRange" value="${compareSelectRange }"  placeholder="对比时间" sType="searchAndValid" />
		          </div>
 		      		<div class="form-group width_btn">
 		          		<button  type="button" class="btn  btn-primary " onclick="searchAndValid();"><i class="icon-search"></i> 搜索</button>
 		        	</div>
		    	</div>
	    	</form>
	    	
	    	
	    	<c:if test="${not empty compareSelectRange}">
		    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table panel-body table-bordered " >
		         <tbody>
		           <tr>
		                  <th><b>创角用户 </b> <i class="helpToolTip" data-content="统计所选时期内，每日玩家激活游戏后，进行了注册并创建了游戏角色的账户数量"></i></th>
				          <th><b>注册用户 </b> <i class="helpToolTip" data-content="统计所选时期内，每日玩家激活游戏后，进行了注册有ID信息或者账户信息的玩家账户数量"></i></th>
				          <th><b>注册设备 </b> <i class="helpToolTip" data-content="当日玩家激活游戏后，进行了注册有ID信息或者账户信息的玩家设备数量"></i></th>
				          <th><b>注册激活转化率 </b> <i class="helpToolTip" data-content="新增注册设备/游戏激活"></i></th>
				          <th><b>创角注册转化率 </b> <i class="helpToolTip" data-content="创角设备数/新增注册设备数"></i></th>
		           </tr>
		           <tr>
		           		<td>${result['data'][0].roleUsers }</td>
		           		<td>${result['data'][0].regUsers }</td>
		           		<td>${result['data'][0].regDevices }</td>
		           		<c:if test="${result['data'][0].newDevices > 0 }">
							<td><fmt:formatNumber  value="${result['data'][0].regDevices/result['data'][0].newDevices }" pattern="0.00" />%</td>
						</c:if>
						<c:if test="${result['data'][0].regDevices > 0 }">
							<td><fmt:formatNumber  value="${result['data'][0].roleDevices/result['data'][0].regDevices }" pattern="0.00" />%</td>
						</c:if>
						
						<c:if test="${result['data'][0].newDevices == 0 }">
							<td>0.00%</td>
						</c:if>
						<c:if test="${result['data'][0].regDevices == 0 }">
							<td>0.00%</td>
						</c:if>
		           </tr>
		           <tr>
		           		<td>${result['compareData'][0].roleUsers }</td>
		           		<td>${result['compareData'][0].regUsers }</td>
		           		<td>${result['compareData'][0].regDevices }</td>
		           		<c:if test="${result['compareData'][0].newDevices > 0 }">
							<td><fmt:formatNumber  value="${result['compareData'][0].regDevices * 100/result['compareData'][0].newDevices }" pattern="0.00" />%</td>
						</c:if>
						<c:if test="${result['compareData'][0].regDevices > 0 }">
							<td><fmt:formatNumber  value="${result['compareData'][0].roleDevices * 100/result['compareData'][0].regDevices }" pattern="0.00" />%</td>
						</c:if>
						<c:if test="${result['compareData'][0].newDevices == 0 }">
							<td>0.00%</td>
						</c:if>
						<c:if test="${result['compareData'][0].regDevices == 0 }">
							<td>0.00%</td>
						</c:if>
		           </tr>
		         </tbody><br/>
		       </table>
	       </c:if>
          <div class="panel-body " id="chartData"  style="height:400px"> 
          		
          </div>
        </div>
        <c:if test="${empty compareSelectRange}">
        <div class="panel panel-default">
          <div class="panel-heading ">
                 <h3 class="panel-title"> 
            	            <!-- 渠道 -->
	      		  <c:if test="${groupType == 1 }">
      				<c:if test="${not empty channelIds}">
      				<s:if test="result['channels'].size>0">
      					<s:iterator value="result[channels]" var="item" >
      						${channelName }&nbsp;&nbsp;
      					</s:iterator>
      				</s:if>
      				</c:if>
      				<c:if test="${empty channelIds && not empty zoneIds }">
      				<s:if test="result['gamezones'].size>0">
      					<s:iterator value="result['gamezones']" var="item" >
      						${zoneName }&nbsp;&nbsp;
      					</s:iterator>
      				</s:if>
      					所有渠道&nbsp;&nbsp;
      				</c:if>
      				
      				<c:if test="${not empty compareChannelIds}"><br>对比<br>
      				<s:if test="result['comparePlatforms'].size>0">
      					<s:iterator value="result['comparePlatforms']" var="item" >
      						${channelName }&nbsp;&nbsp;
      					</s:iterator>
      					</s:if>
      				</c:if>
      				<c:if test="${empty compareChannelIds &&  not empty  compareZoneIds}">
      				<br>对比<br>
      				<s:if test="result['compareGamezones'].size>0">
      					<s:iterator value="result['compareGamezones']" var="item" >
      						${zoneName }&nbsp;&nbsp;
      					</s:iterator>
      					</s:if>
      					所有比较渠道
      				</c:if>
      			</c:if>
      			<c:if test="${groupType == 2 }">
      				<c:if test="${not empty zoneIds}">
      				<s:if test="result['gamezones'].size>0">
      					<s:iterator value="result['gamezones']" var="item" >
      						${zoneName }&nbsp;&nbsp;
      					</s:iterator>
      					</s:if>
      				</c:if>
      				<c:if test="${empty zoneIds && not empty channelIds}">
      					所有分区&nbsp;&nbsp;
      				</c:if>
      				
      				<c:if test="${not empty compareZoneIds}">
      				<br>对比<br>
      				<s:if test="result['compareGamezones'].size>0">
      					<s:iterator value="result['compareGamezones']" var="item" >
      						${zoneName }&nbsp;&nbsp;
      					</s:iterator>
      					</s:if>
      				</c:if>
      				<c:if test="${empty compareZoneIds &&  not empty compareChannelIds}">
      					所有比较分区&nbsp;&nbsp;
      				</c:if>
      			</c:if>
      			<c:if test="${groupType != 1 && groupType != 2}">
      			所有渠道、所有分区
      			</c:if>
            </h3>
          </div>
          <div class="panel-body ">
      		<div class="table-responsive">		
	      		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
            				<th>日期</th>
            				<c:if test="${result['type'] == 2}">
            					<th>明细</th>
            				</c:if>
            				<c:if test="${result['type'] == 1}">
            					<th>对比</th>
            				</c:if>
							<th><b>创角用户 </b> <i class="helpToolTip" data-content="统计所选时期内，每日玩家激活游戏后，进行了注册并创建了游戏角色的账户数量"></i></th>
				            <th><b>注册用户 </b> <i class="helpToolTip" data-content="统计所选时期内，每日玩家激活游戏后，进行了注册有ID信息或者账户信息的玩家账户数量"></i></th>
				            <th><b>注册设备 </b> <i class="helpToolTip" data-content="当日玩家激活游戏后，进行了注册有ID信息或者账户信息的玩家设备数量"></i></th>
				            <th><b>注册激活转化率 </b> <i class="helpToolTip" data-content="注册设备数/新增设备数"></i></th>
				            <th><b>创角注册转化率 </b> <i class="helpToolTip" data-content="创角设备数/注册设备数"></i></th>
            			</tr>
          			</thead>
          			<tbody>
          				<c:if test="${result['type'] == 2}">
          					<s:if test="result['data'].size > 0">
								<s:iterator value="result['data']" var="item" >
									<s:set var="allroleUsers" value="0"></s:set>
									<s:set var="allregUsers" value="0"></s:set>
									<s:set var="allnewDevices" value="0"></s:set>
									<s:set var="allregDevices" value="0"></s:set>
									<s:set var="allroleDevices" value="0"></s:set>
				          			<s:iterator value="#item.value" var="itemVal" status="st">
				          				<s:set var="allroleUsers" value="#allroleUsers + #itemVal.roleUsers"></s:set>
				          				<s:set var="allregUsers" value="#allregUsers + #itemVal.regUsers"></s:set>
				          				<s:set var="allnewDevices" value="#allnewDevices + #itemVal.newDevices"></s:set>
				          				<s:set var="allregDevices" value="#allregDevices + #itemVal.regDevices"></s:set>
				          				<s:set var="allroleDevices" value="#allroleDevices + #itemVal.roleDevices"></s:set>
					          			<tr>
					          				<c:if test="${st.index == 0 }">
					          				<s:if test="#item.value.size >1">
					          				<td rowspan=<s:property value="#item.value.size+1"/>><s:property value="#item.key"/></td>
					          				</s:if>
					          				<s:else>
					          				<td rowspan=<s:property value="#item.value.size"/>><s:property value="#item.key"/></td>
					          				</s:else>
					          				</c:if>
					          				<c:if test="${result['group'] == 'game'}">
					          					<td><s:property value="#itemVal.gameName"/></td>
					          				</c:if>
					          				<c:if test="${result['group'] == 'platform'}">
					          					<td><s:property value="#itemVal.channelName"/></td>
					          				</c:if>
					          				<c:if test="${result['group'] == 'zone'}">
					          					<td><s:property value="#itemVal.zoneName"/></td>
					          				</c:if>
					          				<td><s:property value="#itemVal.roleUsers"/></td>
					          				<td><s:property value="#itemVal.regUsers"/></td>
					          				<td><s:property value="#itemVal.regDevices"/></td>
											<c:if test="${newDevices > 0 }">
												<td><fmt:formatNumber  value="${regDevices * 100/newDevices }" pattern="0.00" />%</td>
											</c:if>
											<c:if test="${regDevices > 0 }">
												<td><fmt:formatNumber  value="${roleDevices * 100/regDevices }" pattern="0.00" />%</td>
											</c:if>
											<c:if test="${newDevices == 0 }">
												<td>0.00%</td>
											</c:if>
											<c:if test="${regDevices == 0 }">
												<td>0.00%</td>
											</c:if>
					          			</tr>
				          			</s:iterator>
				          			<s:if test="#item.value.size >1">
				          			<tr>
				          				<td>合计</td>
				          				<td>${allroleUsers }</td>
				          				<td>${allregUsers }</td>
				          				<td>${allregDevices }</td>
										<c:if test="${allnewDevices > 0 }">
											<td><fmt:formatNumber  value="${allregDevices * 100/allnewDevices }" pattern="0.00" />%</td>
											</c:if>
											<c:if test="${allregDevices > 0 }">
											<td><fmt:formatNumber  value="${allroleDevices * 100/allregDevices }" pattern="0.00" />%</td>
										</c:if>
										<c:if test="${allnewDevices == 0 }">
											<td>0.00%</td>
											</c:if>
											<c:if test="${allregDevices == 0 }">
											<td>0.00%</td>
										</c:if>
				          			</tr>
				          			</s:if>
							    </s:iterator>
						    </s:if>
						    <s:else>
						    	<tr>
						    		<td colspan="7" align="center">没有数据！</td>
						    	</tr>
						    </s:else>
						</c:if>
						
						<!-- 对比 -->
						<c:if test="${result['type'] == 1}">
							<s:if test="result['data'].size > 0">
								<s:iterator value="result['data']" var="item" status="st">
									<tr>
										<td rowspan="2"><s:date name="statDate" format="yyyy-MM-dd"/></td>
										<td>
											<c:if test="${result['group'] == 1 }">
						      					<s:iterator value="result['gamezones']" var="gameZoneItem" >
						      						${gameZoneItem.zoneName }&nbsp;
						      					</s:iterator>
											</c:if>
											<c:if test="${result['group'] == 2 }">
						      					<s:iterator value="result[channels]" var="platformItem" >
						      						${platformItem.channelName }&nbsp;
						      					</s:iterator>
											</c:if>
										</td>
										<td><s:property value="#item.roleUsers"/></td>
				          				<td><s:property value="#item.regUsers"/></td>
				          				<td><s:property value="#item.regDevices"/></td>
										<c:if test="${newDevices > 0 }">
											<td><fmt:formatNumber  value="${regDevices * 100/newDevices }" pattern="0.00" />%</td>
											</c:if>
											<c:if test="${regDevices > 0 }">
											<td><fmt:formatNumber  value="${roleDevices * 100/regDevices }" pattern="0.00" />%</td>
										</c:if>
										<c:if test="${newDevices == 0 }">
											<td>0.00%</td>
											</c:if>
											<c:if test="${regDevices == 0 }">
											<td>0.00%</td>
										</c:if>
									</tr>
									
									<tr>
										<td>
											<c:if test="${result['group'] == 1 }">
												<s:iterator value="result['compareGamezones']" var="gameZoneItem" >
						      						${gameZoneItem.zoneName }&nbsp;
						      					</s:iterator>
											</c:if>
											<c:if test="${result['group'] == 2 }">
												<s:iterator value="result['comparePlatforms']" var="platformItem" >
						      						${platformItem.channelName }&nbsp;
						      					</s:iterator>
											</c:if>
										</td>
										<s:if test="result['compareData'].size>0">
										<td>${result['compareData'][st.index].roleUsers }</td>
										<td>${result['compareData'][st.index].regUsers }</td>
										<td>${result['compareData'][st.index].regDevices }</td>
										<c:if test="${result['compareData'][st.index].newDevices > 0 }">
											<td><fmt:formatNumber  value="${result['compareData'][st.index].regDevices * 100/result['compareData'][st.index].newDevices }" pattern="0.00" />%</td>
											</c:if>
											<c:if test="${result['compareData'][st.index].regDevices > 0 }">
											<td><fmt:formatNumber  value="${result['compareData'][st.index].roleDevices * 100/result['compareData'][st.index].regDevices }" pattern="0.00" />%</td>
										</c:if>
										<c:if test="${result['compareData'][st.index].newDevices == 0 }">
											<td>0.00%</td>
											</c:if>
											<c:if test="${result['compareData'][st.index].regDevices == 0 }">
											<td>0.00%</td>
										</c:if>
										</s:if>
										<s:else>
										<td></td><td></td><td></td><td></td><td></td>
										</s:else>
									</tr>
								</s:iterator>
							 </s:if>
						    <s:else>
						    	<tr>
						    		<td colspan="7" align="center">没有数据！</td>
						    	</tr>
						    </s:else>
						</c:if>
          			</tbody>
         		</table>
      		</div>
		 </div>
        </div>
        </c:if>
      </div>
    </div>
  </div>
</div>
    <%@ include file="/common/footer.jsp" %>      
	<script type="text/javascript">
		$(document).ready(function() {
			$("#reportLeft_change").addClass("active");
			$("#zone").addClass("active");
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
            	if('${result["optionJson"]}' == ""){
            		$("#chartData").html("<div align='center'>没有数据！</div>");
            		return;
            	}
                var myChart = ec.init(document.getElementById('chartData')); 
				var option = eval('(${result["optionJson"]})');
                myChart.setOption(option); 
            }
		 );
		
		function selectChannelZone(){
			var gameId = $("#gameId").val();
			if($.trim(gameId) == ""){
				errorTip("请先选择游戏！");
				return;
			}
			art.dialog.open("${ctx}/report/report_getChannelZone.shtml?gameId="+gameId+"&isCompare=0",
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
			var gameId = $("#gameId").val();
			if($.trim(gameId) == ""){
				errorTip("请先选择游戏！");
				return;
			}
			
			art.dialog.open("${ctx}/report/report_getChannelZone.shtml?gameId="+gameId+"&isCompare=1",
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
		
	</script>
</body>
</html>