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
          <div class="panel-body ">
          <form name="selected" method="post" action="report_zone.shtml" id="mainForm">
          	  <ul class="nav nav-tabs " >
				 <li id="summary"><a href="${ctx}/report/report_summary.shtml" role="tab">平台分析</a></li>
				  <li id="platform"><a href="${ctx}/report/report_platform.shtml" role="tab">渠道分析</a></li>
				  <li id="zone"><a href="${ctx}/report/report_zone.shtml" role="tab">区服分析</a></li>
				  <li style="float:right">
					   <div class="form-group width_btn">
<!-- 			          		<button  type="submit" class="btn  btn-primary "><i class="icon-search"></i> 搜索</button> -->
			          		<button  type="button" class="btn  btn-default " onclick="resetSearch();"><i class="icon-eraser"></i> 重置</button>
		        	   </div>
				  </li>
				  <li id="date"  style="float:right">
					  <div align="right" style="width:300px">
							<div class="form-group   width_input"  style="width: 300px">
								<div  class="input-group  input-append date form_month "  data-date-format="yyyy-mm"  sType="submit" >
									<input class="form-control "  id="yearMonth"  type="text" name="yearMonthStr"   readonly  placeholder="历史累计数据" data-toggle="popover"  data-placement="top" data-content="请选择日期"   value="${yearMonthStr}"/>
									<span class="add-on input-group-addon"><i class="icon-remove"></i></span> 
									<span class="add-on input-group-addon"><i class="icon-calendar"></i></span>
				                </div>
				          </div>
			          </div>
				  </li>
				 
				 <li style="float:right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
				   <li id="table" style="float:right">
				    <select name="type"  class="form-control">
				      <option value="1" <s:if test="type==1">selected</s:if>>区服总计表</option>
				      <option value="3" <s:if test="type==3">selected</s:if>>渠道详细表</option>
				      <option value="2" <s:if test="type==2">selected</s:if>>区服详细表</option>
				    </select>
				  </li>
				 
				 <li style="float:right">&nbsp;&nbsp;&nbsp;&nbsp;</li>
				 
				  <li id="gameName" style="float:right">
				    <select name="gameId" onchange="update()" class="form-control" >
				    <s:iterator value="allGames" var="item" >
				      <option id="appNameOpt" value="${item.id}" <c:if test="${item.id==gameId}">selected</c:if>>${item.gameName}</option>
				    </s:iterator>
				    </select>
				  </li>
			  </ul>
			  </form>
			  <br/>
      		<div class="table-responsive">
      		 <s:if test="type==1 || type==null"> 
        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
							<th>区服</th>
							<th>总创角用户<i class="helpToolTip" data-content="平台所有游戏创角用户数的累加"></i></th>
							<th>总注册用户<i class="helpToolTip" data-content="平台所有游戏注册用户数的累加"></i></th>
							<th>注册设备<i class="helpToolTip" data-content="平台所有游戏注册设备的累加"></i></th>
							<th>活跃用户<i class="helpToolTip" data-content="平台所有游戏活跃用户的累加"></i></th>
							<th>充值金额<i class="helpToolTip" data-content="平台所有游戏充值总额的累加"></i></th>
							<th>充值总次数<i class="helpToolTip" data-content="平台所有游戏充值次数的累加"></i></th>
							<th>付费总人数<i class="helpToolTip" data-content="平台所有游戏充值人数的累加"></i></th>
							<th>总付费ARPU<i class="helpToolTip" data-content="所有游戏的总充值总额/所有游戏的付费总人数"></i></th>
							<th>注册ARPU<i class="helpToolTip" data-content="所有游戏的总充值总额/所有游戏的总注册用户"></i></th>
							<th>操作</th>
            			</tr>
          			</thead>
          			<tbody>
          			<s:if test="sZonePlatforms.size>0  ||  sZonePlatformMonthlies.size>0">	
	          			<s:if test="sZonePlatforms.size>0  && sZonePlatformMonthlies.size==0">
	          				<s:set value="sZonePlatforms" var="valueList"></s:set>
	          			</s:if>
	          			<s:if test="sZonePlatforms.size==0  && sZonePlatformMonthlies.size>0">
	          				<s:set value="sZonePlatformMonthlies" var="valueList"></s:set>
	          			</s:if>
	          			<s:set value="0" var="allRoledUser"></s:set>
	          			<s:set value="0" var="allRegUser"></s:set>
	          			<s:set value="0" var="alldevices"></s:set>
	          			<s:set value="0" var="allpayAmount"></s:set>
	          			<s:set value="0" var="allpayTimes"></s:set>
	          			<s:set value="0" var="allpayUsers"></s:set>
	          			<s:set value="0" var="allactiveUsers"></s:set>
	          			<s:iterator value="valueList" var="itemVal" >
		          			<tr>
		          			
		          			<s:set value="#allRoledUser+#itemVal.totalRoledUser" var="allRoledUser"></s:set>
	          			    <s:set value="#allRegUser+#itemVal.totalRegUser" var="allRegUser"></s:set>
	          			    <s:set value="#alldevices+#itemVal.devices" var="alldevices"></s:set>
	          		
	          			    
		          		    <s:set value="#allpayAmount+#itemVal.payAmount" var="allpayAmount"></s:set>
	          			    <s:set value="#allpayTimes+#itemVal.payTimes" var="allpayTimes"></s:set>
	          			    <s:set value="#allpayUsers+#itemVal.payUsers" var="allpayUsers"></s:set>
		          			<s:set value="#allactiveUsers+#itemVal.activeUsers" var="allactiveUsers"></s:set>
		          				<td><s:property value="#itemVal.zoneName"/></td>
	          			        <td><s:property value="#itemVal.totalRoledUser"/></td>				
			                    <td><s:property value="#itemVal.totalRegUser"/></td>
								<td><s:property value="#itemVal.devices"/></td>
								<td><s:property value="#itemVal.activeUsers"/></td>
								<td><fmt:formatNumber  value="${itemVal.payAmount/100 }" pattern="0.00" /></td>
								<td><s:property value="#itemVal.payTimes"/></td>
								<td><s:property value="#itemVal.payUsers"/></td>
								
								<c:if test="${itemVal.payUsers==0}">
										<td>0.00</td>
										</c:if>
										<c:if test="${itemVal.payUsers>0}">
										<td><fmt:formatNumber  value="${itemVal.payAmount/100/itemVal.payUsers }" pattern="0.00" /></td>
										</c:if>
										
										<c:if test="${itemVal.totalRegUser==0}">
										<td>0.00</td>
										</c:if>
										<c:if test="${itemVal.totalRegUser>0}">					
										<td><fmt:formatNumber  value="${itemVal.payAmount/100/itemVal.totalRegUser }" pattern="0.00" /></td>
								</c:if>	
									<td><a class="basic" href="${ctx}/report/reportDaily_basic.shtml?gameId=${itemVal.gameId}&groupType=1&zoneIds=${itemVal.zoneId}&selectRange=${selectRange}">查看</a></td>
		          			</tr>
	          			</s:iterator>
                        <s:if test="#itemVal.size>1">
	          			<tr>
		          			<td>合计</td>
		          			<td><s:property value="#allRoledUser"/></td>
		          			<td><s:property value="#allRegUser"/></td>
		          			<td><s:property value="#alldevices"/></td>
		          			<td><s:property value="#allactiveUsers"/></td>
		          			<td><fmt:formatNumber  value="${allpayAmount/100 }" pattern="0.00" /></td>
		          			<td><s:property value="#allpayTimes"/></td>
		          			<td><s:property value="#allpayUsers"/></td>  			
		          			<c:if test="${allpayUsers==0}">
										<td>0.00</td>
										</c:if>
										<c:if test="${allpayUsers>0}">
										<td><fmt:formatNumber  value="${allpayAmount/100/allpayUsers}" pattern="0.00" /></td>
										</c:if>
										
										<c:if test="${allRegUser==0}">
										<td>0.00</td>
										</c:if>
										<c:if test="${allRegUser>0}">					
										<td><fmt:formatNumber  value="${allpayAmount/100/allRegUser }" pattern="0.00" /></td>
							</c:if>
	          			<td><a class="basic" href="${ctx}/report/reportDaily_basic.shtml?gameId=${gameId}&selectRange=${selectRange}">查看</a></td>
	          			</tr>
          			</s:if>
          			</s:if>
          			<s:else>
          				<tr>
          					<td colspan="13" style="text-align: center">当前列表没有数据！</td>
          				</tr>
          			</s:else>
					</tbody>
        		</table>        		
			   </s:if> 
			   

			   <s:if test="type==2"> 
        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
							<th>区服</th>
							<th>渠道</th>							
							<th>总创角用户<i class="helpToolTip" data-content="平台所有游戏创角用户数的累加"></i></th>
							<th>总注册用户<i class="helpToolTip" data-content="平台所有游戏注册用户数的累加"></i></th>
							<th>注册设备<i class="helpToolTip" data-content="平台所有游戏注册设备的累加"></i></th>
							<th>活跃用户<i class="helpToolTip" data-content="平台所有游戏活跃用户的累加"></i></th>
							<th>充值金额<i class="helpToolTip" data-content="平台所有游戏充值总额的累加"></i></th>
							<th>充值总次数<i class="helpToolTip" data-content="平台所有游戏充值次数的累加"></i></th>
							<th>付费总人数<i class="helpToolTip" data-content="平台所有游戏充值人数的累加"></i></th>
							<th>总付费ARPU<i class="helpToolTip" data-content="所有游戏的总充值总额/所有游戏的付费总人数"></i></th>
							<th>注册ARPU<i class="helpToolTip" data-content="所有游戏的总充值总额/所有游戏的总注册用户"></i></th>
							<th>操作<i class="helpToolTip" data-content="提示"></i></th>
            			</tr>
          			</thead>
          			<tbody>
          			<s:if test="iterateZonePlatformsMap.size>0  ||  iterateZonePlatformsMonthlyMap.size>0">		
	          			<s:if test="iterateZonePlatformsMap.size>0  &&  iterateZonePlatformsMonthlyMap.size==0">
	          			        <s:set value="iterateZonePlatformsMap"  var="valueMap"></s:set>
	          			</s:if>
	          			<s:if test="iterateZonePlatformsMonthlyMap.size>0  &&  iterateZonePlatformsMap.size==0">
	          			        <s:set value="iterateZonePlatformsMonthlyMap" var="valueMap"></s:set>
	          			</s:if>	 
          			<s:iterator value="valueMap" var="item" >
	          			<s:set value="0" var="allRoledUser"></s:set>
	          			<s:set value="0" var="allRegUser"></s:set>
	          			<s:set value="0" var="alldevices"></s:set>
	          			<s:set value="0" var="allactiveUsers"></s:set>
	          			<s:set value="0" var="allpayAmount"></s:set>
	          			<s:set value="0" var="allpayTimes"></s:set>
	          			<s:set value="0" var="allpayUsers"></s:set>
		 
	          			<s:iterator value="#item.value" var="itemVal" status="st">
		          			<s:set value="#allRoledUser+#itemVal.totalRoledUser" var="allRoledUser"></s:set>
	          			    <s:set value="#allRegUser+#itemVal.totalRegUser" var="allRegUser"></s:set>
	          			    <s:set value="#alldevices+#itemVal.devices" var="alldevices"></s:set>
	          			    <s:set value="#allpayAmount+#itemVal.payAmount" var="allpayAmount"></s:set>
	          			    <s:set value="#allpayTimes+#itemVal.payTimes" var="allpayTimes"></s:set>
	          			    <s:set value="#allpayUsers+#itemVal.payUsers" var="allpayUsers"></s:set>
	          			    <s:set value="#allactiveUsers+#itemVal.activeUsers" var="allactiveUsers"></s:set>
		          			<tr>
		          			
		          				<c:if test="${st.index == 0 }">
		          				<s:if test="#item.value.size >1">
		          				<td rowspan=<s:property value="#item.value.size+1"/>><s:property value="#item.key"/></td>
		          				</s:if>
		          				<s:else>
		          				<td rowspan=<s:property value="#item.value.size"/>><s:property value="#item.key"/></td>
		          				</s:else>
		          				</c:if>
		          				<td><s:property value="#itemVal.platformName"/></td>
	          			        <td><s:property value="#itemVal.totalRoledUser"/></td>				
			                    <td><s:property value="#itemVal.totalRegUser"/></td>
								<td><s:property value="#itemVal.devices"/></td>
								<td><s:property value="#itemVal.activeUsers"/></td>
								<td><fmt:formatNumber  value="${itemVal.payAmount/100 }" pattern="0.00" /></td>
								<td><s:property value="#itemVal.payTimes"/></td>
								<td><s:property value="#itemVal.payUsers"/></td>
								
								<c:if test="${itemVal.payUsers==0}">
										<td>0.00</td>
										</c:if>
										<c:if test="${itemVal.payUsers>0}">
										<td><fmt:formatNumber  value="${itemVal.payAmount/100/itemVal.payUsers }" pattern="0.00" /></td>
										</c:if>
										
										<c:if test="${itemVal.totalRegUser==0}">
										<td>0.00</td>
										</c:if>
										<c:if test="${itemVal.totalRegUser>0}">					
										<td><fmt:formatNumber  value="${itemVal.payAmount/100/itemVal.totalRegUser }" pattern="0.00" /></td>
								</c:if>	
								<td><a class="basic" href="${ctx}/report/reportDaily_basic.shtml?gameId=${itemVal.gameId}&groupType=1&channelIds=${itemVal.platformId}&zoneIds=${itemVal.zoneId}&selectRange=${selectRange}">查看</a></td>
		          			</tr>
	          			</s:iterator>
	          			<s:if test="#item.value.size >1">
	          			<tr>
		          			<td>合计</td>
		          			<td><s:property value="#allRoledUser"/></td>
		          			<td><s:property value="#allRegUser"/></td>
		          			<td><s:property value="#alldevices"/></td>
		          			<td><s:property value="#allactiveUsers"/></td>
		          			<td><fmt:formatNumber  value="${allpayAmount/100 }" pattern="0.00" /></td>
		          			<td><s:property value="#allpayTimes"/></td>
		          			<td><s:property value="#allpayUsers"/></td>
		          			<c:if test="${allpayUsers==0}">
										<td>0.00</td>
										</c:if>
										<c:if test="${allpayUsers>0}">
										<td><fmt:formatNumber  value="${allpayAmount/100/allpayUsers}" pattern="0.00" /></td>
										</c:if>
										
										<c:if test="${allRegUser==0}">
										<td>0.00</td>
										</c:if>
										<c:if test="${allRegUser>0}">					
										<td><fmt:formatNumber  value="${allpayAmount/100/allRegUser }" pattern="0.00" /></td>
							</c:if>
		          			<td><a class="basic" href="${ctx}/report/reportDaily_basic.shtml?gameId=${itemVal.gameId}&groupType=1&zoneName=${item.key}&selectRange=${selectRange}">查看</a></td>
	          			</tr>
	          			</s:if>
				    </s:iterator>
				</s:if>
          			<s:else>
          			<tr><td colspan="13" style="text-align: center">当前列表没有数据！</td></tr>
          			</s:else>
					</tbody>
        		</table>        		
			   </s:if> 
			   

			   <s:if test="type==3"> 
        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
							<th>渠道</th>
							<th>区服</th>	
							<th>总创角用户<i class="helpToolTip" data-content="平台所有游戏创角用户数的累加"></i></th>
							<th>总注册用户<i class="helpToolTip" data-content="平台所有游戏注册用户数的累加"></i></th>
							<th>注册设备<i class="helpToolTip" data-content="平台所有游戏注册设备的累加"></i></th>
							<th>活跃用户<i class="helpToolTip" data-content="平台所有游戏活跃用户的累加"></i></th>
							<th>充值金额<i class="helpToolTip" data-content="平台所有游戏充值总额的累加"></i></th>
							<th>充值总次数<i class="helpToolTip" data-content="平台所有游戏充值次数的累加"></i></th>
							<th>付费总人数<i class="helpToolTip" data-content="平台所有游戏充值人数的累加"></i></th>
							<th>总付费ARPU<i class="helpToolTip" data-content="所有游戏的总充值总额/所有游戏的付费总人数"></i></th>
							<th>注册ARPU<i class="helpToolTip" data-content="所有游戏的总充值总额/所有游戏的总注册用户"></i></th>
							<th>操作<i class="helpToolTip" data-content="提示"></i></th>
            			</tr>
          			</thead>
          			<tbody>
		          		<s:if test="iterateZonePlatformsMap.size>0  ||  iterateZonePlatformsMonthlyMap.size>0">			
		          			<s:if test="iterateZonePlatformsMap.size>0  &&  iterateZonePlatformsMonthlyMap.size==0">
	          			        <s:set value="iterateZonePlatformsMap"  var="valueMap"></s:set>
		          			</s:if>
		          			<s:if test="iterateZonePlatformsMonthlyMap.size>0  &&  iterateZonePlatformsMap.size==0">
	          			        <s:set value="iterateZonePlatformsMonthlyMap" var="valueMap"></s:set>
		          			</s:if>
		          			<s:iterator value="valueMap" var="item" >
			          			<s:set value="0" var="allRoledUser"></s:set>
			          			<s:set value="0" var="allRegUser"></s:set>
			          			<s:set value="0" var="alldevices"></s:set>
			          			<s:set value="0" var="allactiveUsers"></s:set>
			          			<s:set value="0" var="allpayAmount"></s:set>
			          			<s:set value="0" var="allpayTimes"></s:set>
			          			<s:set value="0" var="allpayUsers"></s:set>
			          			<s:iterator value="#item.value" var="itemVal" status="st">
				          			<s:set value="#allRoledUser+#itemVal.totalRoledUser" var="allRoledUser"></s:set>
			          			    <s:set value="#allRegUser+#itemVal.totalRegUser" var="allRegUser"></s:set>
			          			    <s:set value="#alldevices+#itemVal.devices" var="alldevices"></s:set>
			          			    <s:set value="#allactiveUsers+#itemVal.activeUsers" var="allactiveUsers"></s:set>
			          			    <s:set value="#allpayAmount+#itemVal.payAmount" var="allpayAmount"></s:set>
			          			   	<s:set value="#allpayTimes+#itemVal.payTimes" var="allpayTimes"></s:set>
			          			   	<s:set value="#allpayUsers+#itemVal.payUsers" var="allpayUsers"></s:set>
				          			<tr>
				          				<c:if test="${st.index == 0 }">
				          				<s:if test="#item.value.size>1">
				          				<td rowspan=<s:property value="#item.value.size+1"/>><s:property value="#item.key"/></td>
				          				</s:if>
				          				<s:else>
				          				<td rowspan=<s:property value="#item.value.size"/>><s:property value="#item.key"/></td>
				          				</s:else>
				          				</c:if>
				          				<td><s:property value="#itemVal.zoneName"/></td>
			          			        <td><s:property value="#itemVal.totalRoledUser"/></td>				
					                    <td><s:property value="#itemVal.totalRegUser"/></td>
										<td><s:property value="#itemVal.devices"/></td>
			                    		<td><s:property value="#itemVal.activeUsers"/></td>
			                    		<td><fmt:formatNumber  value="${itemVal.payAmount/100 }" pattern="0.00" /></td>
										<td><s:property value="#itemVal.payTimes"/></td>
										<td><s:property value="#itemVal.payUsers"/></td>
										<c:if test="${itemVal.payUsers==0}">
										<td>0.00</td>
										</c:if>
										<c:if test="${itemVal.payUsers>0}">
										<td><fmt:formatNumber  value="${itemVal.payAmount/100/itemVal.payUsers }" pattern="0.00" /></td>
										</c:if>
										
										<c:if test="${itemVal.totalRegUser==0}">
										<td>0.00</td>
										</c:if>
										<c:if test="${itemVal.totalRegUser>0}">					
										<td><fmt:formatNumber  value="${itemVal.payAmount/100/itemVal.totalRegUser }" pattern="0.00" /></td>
								        </c:if>	
										<td><a class="basic" href="${ctx}/report/reportDaily_basic.shtml?gameId=${itemVal.gameId}&groupType=1&channelIds=${itemVal.platformId}&zoneIds=${itemVal.zoneId}&selectRange=${selectRange}">查看</a></td>
				          			</tr>
			          			</s:iterator>
			          			<s:if test="#item.value.size>1">
			          			<tr>
				          			<td>合计</td>
				          			<td><s:property value="#allRoledUser"/></td>
				          			<td><s:property value="#allRegUser"/></td>
				          			<td><s:property value="#alldevices"/></td>
				          			<td><s:property value="#allactiveUsers"/></td>
				          			<td><fmt:formatNumber  value="${allpayAmount/100 }" pattern="0.00" /></td>
				          			<td><s:property value="#allpayTimes"/></td>
				          			<td><s:property value="#allpayUsers"/></td>
				          				<c:if test="${allpayUsers==0}">
										<td>0.00</td>
										</c:if>
										<c:if test="${allpayUsers>0}">
										<td><fmt:formatNumber  value="${allpayAmount/100/allpayUsers}" pattern="0.00" /></td>
										</c:if>
										
										<c:if test="${allRegUser==0}">
										<td>0.00</td>
										</c:if>
										<c:if test="${allRegUser>0}">					
										<td><fmt:formatNumber  value="${allpayAmount/100/allRegUser }" pattern="0.00" /></td>
							</c:if>
				          			<td><a class="basic" href="${ctx}/report/reportDaily_basic.shtml?gameId=${itemVal.gameId}&groupType=2&channelName=${item.key}&selectRange=${selectRange}">查看</a></td>
			          			</tr>
			          			</s:if>
						    </s:iterator>
						</s:if>
		       			<s:else>
		       				<tr>
		       					<td colspan="13" style="text-align: center">当前列表没有数据！</td>
		       				</tr>
		       			</s:else>
					</tbody>
		      		</table>        		
			   </s:if> 
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
			$("#reportLeft_summary").addClass("active");
			$("#zone").addClass("active");
			
			var yearMonth= $("#yearMonth").val();
			if(yearMonth!=""){
			var year=yearMonth.split("-")[0];
			var month=yearMonth.split("-")[1];
			var dayNum = new Date(year,month,0);
			var monthStart=yearMonth+"-01";
			var monthEnd=yearMonth+"-"+dayNum.getDate();
			var selectRange=monthStart+"至"+monthEnd;
			$(".basic").each(function() {
			var href=$(this).attr("href");
			href= href+selectRange;
			$(this).attr("href",href);
			});
			}s
		});
		function resetSearch(){
		    location.assign("report_zone.shtml");
		}
	</script>
</body>
</html>