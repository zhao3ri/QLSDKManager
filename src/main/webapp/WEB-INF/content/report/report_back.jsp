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
        	<form action="reportDaily_back.shtml" method="post" id="mainForm">
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
<!-- 		      		<div class="form-group width_btn"> -->
<!-- 		          		<button  type="button" class="btn  btn-primary " onclick="searchAndValid();"><i class="icon-search"></i> 搜索</button> -->
<!-- 		        	</div> -->
		    	</div>
	    	</form>
	    	<c:if test="${not empty compareSelectRange}">
		    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table panel-body table-bordered " >
		         <tbody>
		           <tr>
			             <th><b>非付费玩家回流 </b> <i class="helpToolTip" data-content="连续7日未登录游戏，在第8日或第8日之后又登录游戏的普通用户即未付费用户(账号为唯一)数量"></i></th>
			             <th><b>付费玩家回流 </b> <i class="helpToolTip" data-content="连续7日未登录游戏，在第8日或第8日之后又登录游戏的付费用户(账号为唯一)数量"></i></th>
			             <th><b>合计回流 </b></th>
		           </tr>
		           <tr>
		           		<td>${result['data'][0].backUsers - result['data'][0].backPayUsers}</td>
		           		<td>${result['data'][0].backPayUsers }</td>
		           		<td>${result['data'][0].backUsers }</td>
		           </tr>
		           <tr>
		           		<td>${result['compareData'][0].backUsers - result['compareData'][0].backPayUsers}</td>
		           		<td>${result['compareData'][0].backPayUsers }</td>
		           		<td>${result['compareData'][0].backUsers }</td>
		           </tr>
		         </tbody><br>
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
      				<s:if test="result['platforms'].size>0">
      					<s:iterator value="result['platforms']" var="item" >
      						${platformName }&nbsp;&nbsp;
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
      						${platformName }&nbsp;&nbsp;
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
							 <th><b>非付费玩家回流 </b> <i class="helpToolTip" data-content="连续7日未登录游戏，在第8日或第8日之后又登录游戏的普通用户即未付费用户(账号为唯一)数量"></i></th>
			                 <th><b>付费玩家回流 </b> <i class="helpToolTip" data-content="连续7日未登录游戏，在第8日或第8日之后又登录游戏的付费用户(账号为唯一)数量"></i></th>
			                 <th><b>合计回流 </b></th>
            			</tr>
          			</thead>
          			<tbody>
          				<c:if test="${result['type'] == 2}">
          					<s:if test="result['data'].size > 0">
								<s:iterator value="result['data']" var="item" >
									<s:set var="allbackUsers" value="0"></s:set>
									<s:set var="allbackPayUsers" value="0"></s:set>
				          			<s:iterator value="#item.value" var="itemVal" status="st">
				          				<s:set var="allbackUsers" value="#allbackUsers + #itemVal.backUsers"></s:set>
				          				<s:set var="allbackPayUsers" value="#allbackPayUsers + #itemVal.backPayUsers"></s:set>
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
					          					<td><s:property value="#itemVal.platformName"/></td>
					          				</c:if>
					          				<c:if test="${result['group'] == 'zone'}">
					          					<td><s:property value="#itemVal.zoneName"/></td>
					          				</c:if>
					          				
					          				<td><s:property value="#itemVal.backUsers - #itemVal.backPayUsers"/></td>
					          				<td><s:property value="#itemVal.backPayUsers"/></td>
											<td><s:property value="#itemVal.backUsers"/></td>
					          			</tr>
				          			</s:iterator>
				          			<s:if test="#item.value.size >1">
				          			<tr>
				          				<td>合计</td>
				          				<td>${allbackUsers - allbackPayUsers }</td>
				          				<td>${allbackPayUsers }</td>
				          				<td>${allbackUsers }</td>
				          			</tr>
				          			</s:if>
							    </s:iterator>
							</s:if>
						    <s:else>
						    	<tr>
						    		<td colspan="5" align="center">没有数据！</td>
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
						      					<s:iterator value="result['platforms']" var="platformItem" >
						      						${platformItem.platformName }&nbsp;
						      					</s:iterator>
											</c:if>
										</td>
										<td><s:property value="#item.backUsers - #item.backPayUsers"/></td>
				          				<td><s:property value="#item.backPayUsers"/></td>
				          				<td><s:property value="#item.backUsers"/></td>
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
						      						${platformItem.platformName }&nbsp;
						      					</s:iterator>
											</c:if>
										</td>
										<s:if test="result['compareData'].size>0">
										<td>${result['compareData'][st.index].backUsers - result['compareData'][st.index].backPayUsers }</td>
										<td>${result['compareData'][st.index].backPayUsers  }</td>
										<td>${result['compareData'][st.index].backUsers  }</td>
										</s:if>
                                       <s:else>
                                       <td></td><td></td><td></td>
                                       </s:else>
									</tr>
								</s:iterator>
							</s:if>
						    <s:else>
						    	<tr>
						    		<td colspan="5" align="center">没有数据！</td>
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
			$("#reportLeft_back").addClass("active");
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