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
          <form action="report_summary.shtml" method="post" id="mainForm">
          	  <ul class="nav nav-tabs " >
				  <li id="summary"><a href="/report/report_summary.shtml" role="tab">平台分析</a></li>
				  <li id="platform"><a href="/report/report_platform.shtml" role="tab">渠道分析</a></li>
				  <li id="zone"><a href="/report/report_zone.shtml" role="tab">区服分析</a></li>
				  <li id="reset"  role="tab" style="float: right;">
			          <div class="form-group width_btn">
<!-- 				          <button  type="submit" class="btn  btn-primary "  id="yearMonthStr"><i class="icon-search"></i> 搜索</button> -->
			          	<button  id="resetBtn"  type="button" class="btn  btn-default " ><i class="icon-eraser"></i> 重置</button>
			          </div>
			      </li>	
				  <li  id="date"  role="tab" style="float: right;">
				   		<div class="form-group   width_input"  style="width: 300px">
							<div  class="input-group  input-append date form_month "  data-date-format="yyyy-mm"  sType="submit">
								<input class="form-control "  id="yearMonth"  type="text" name="yearMonthStr"   readonly  placeholder="历史累计数据" data-toggle="popover"  data-placement="top" data-content="请选择日期"   value="${yearMonthStr}"/>
								<span class="add-on input-group-addon"><i class="icon-remove"></i></span> 
								<span class="add-on input-group-addon"><i class="icon-calendar"></i></span>
			                </div>
			          </div>
			      </li>       
			  </ul>
			  </form>
			  <br/>
      		<div class="table-responsive">
        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
            				<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
							<th>合计方式</th>
							<th>总创角用户<i class="helpToolTip" data-content="平台所有游戏创角用户（游戏统计维度）数的累加"></i></th>
							<th>总注册用户<i class="helpToolTip" data-content="平台所有游戏注册用户（游戏统计维度）数的累加"></i></th>
							<th>注册设备<i class="helpToolTip" data-content="平台所有游戏注册设备（游戏统计维度）的累加"></i></th>
							<th>活跃用户<i class="helpToolTip" data-content="平台所有游戏活跃用户（游戏统计维度）的累加"></i></th>
							<th>充值金额<i class="helpToolTip" data-content="平台所有游戏充值总额（游戏统计维度）的累加"></i></th>
							<th>充值总次数<i class="helpToolTip" data-content="平台所有游戏充值次数（游戏统计维度）的累加"></i></th>
							<th>付费总人数<i class="helpToolTip" data-content="平台所有游戏充值人数（游戏统计维度）的累加"></i></th>
							<th>总付费ARPU<i class="helpToolTip" data-content="所有游戏的总充值总额/所有游戏的付费总人数"></i></th>
							<th>注册ARPU<i class="helpToolTip" data-content="所有游戏的总充值总额/所有游戏的总注册用户"></i></th>
            			</tr>
          			</thead>
          			<tbody>
          			
          			<!-- cps -->
          			<s:set value="0" var="totalCpstotalRoleUser"></s:set>
          			<s:set value="0" var="totalCpstotalRegUser"></s:set>
          			<s:set value="0" var="totalCpsdevices"></s:set>
          			<s:set value="0" var="totalCpsactiveUsers"></s:set>
          			<s:set value="0" var="totalCpspayAmount"></s:set>
          			<s:set value="0" var="totalCpspayTimes"></s:set>
          			<s:set value="0" var="totalCpspayUsers"></s:set>
          			
          			<!-- cpa -->
          			<s:set value="0" var="totalCpatotalRoleUser"></s:set>
          			<s:set value="0" var="totalCpatotalRegUser"></s:set>
          			<s:set value="0" var="totalCpadevices"></s:set>
          			<s:set value="0" var="totalCpaactiveUsers"></s:set>
          			<s:set value="0" var="totalCpapayAmount"></s:set>
          			<s:set value="0" var="totalCpapayTimes"></s:set>
          			<s:set value="0" var="totalCpapayUsers"></s:set>
          			
          			<!-- andriod -->
          			<s:set value="0" var="totalAndriodtotalRoleUser"></s:set>
          			<s:set value="0" var="totalAndriodtotalRegUser"></s:set>
          			<s:set value="0" var="totalAndrioddevices"></s:set>
          			<s:set value="0" var="totalAndriodactiveUsers"></s:set>
          			<s:set value="0" var="totalAndriodpayAmount"></s:set>
          			<s:set value="0" var="totalAndriodpayTimes"></s:set>
          			<s:set value="0" var="totalAndriodpayUsers"></s:set>
          			
          			<!-- ios -->
          			<s:set value="0" var="totalIostotalRoleUser"></s:set>
          			<s:set value="0" var="totalIostotalRegUser"></s:set>
          			<s:set value="0" var="totalIosdevices"></s:set>
          			<s:set value="0" var="totalIosactiveUsers"></s:set>
          			<s:set value="0" var="totalIospayAmount"></s:set>
          			<s:set value="0" var="totalIospayTimes"></s:set>
          			<s:set value="0" var="totalIospayUsers"></s:set>
          			
                    <s:if test="gameClientReports.size>0  ||  gameClientMonthlyReports.size>0">			
          			<s:if test="gameClientReports.size>0  &&  gameClientMonthlyReports.size==0">
          			        <s:set value="gameClientReports"  var="valueList"></s:set>
          			</s:if>
          			
          			<s:if test="gameClientMonthlyReports.size>0  &&  gameClientReports.size==0">
          			        <s:set value="gameClientMonthlyReports" var="valueList"></s:set>
          			</s:if>
                    	
						<s:iterator value="valueList" var="item">
						<!-- CPS -->
						  <s:set value="#totalCpstotalRoleUser+#item.androidCps.totalRoleUser+#item.iosCps.totalRoleUser" var="totalCpstotalRoleUser"></s:set>
						  <s:set value="#totalCpstotalRegUser+#item.androidCps.totalRegUser+#item.iosCps.totalRegUser" var="totalCpstotalRegUser"></s:set>
						  <s:set value="#totalCpsdevices+#item.androidCps.devices+#item.iosCps.devices" var="totalCpsdevices"></s:set>
						  <s:set value="#totalCpsactiveUsers+#item.androidCps.activeUsers+#item.iosCps.activeUsers" var="totalCpsactiveUsers"></s:set>
						  <s:set value="#totalCpspayAmount+#item.androidCps.payAmount+#item.iosCps.payAmount" var="totalCpspayAmount"></s:set>
						  <s:set value="#totalCpspayTimes+#item.androidCps.payTimes+#item.iosCps.payTimes" var="totalCpspayTimes"></s:set>
						  <s:set value="#totalCpspayUsers+#item.androidCps.payUsers+#item.iosCps.payUsers" var="totalCpspayUsers"></s:set>
						  
						  <!-- cpa -->
						   <s:set value="#totalCpatotalRoleUser+#item.androidCpa.totalRoleUser+#item.iosCpa.totalRoleUser" var="totalCpatotalRoleUser"></s:set>
						  <s:set value="#totalCpatotalRegUser+#item.androidCpa.totalRegUser+#item.iosCpa.totalRegUser" var="totalCpatotalRegUser"></s:set>
						  <s:set value="#totalCpadevices+#item.androidCpa.devices+#item.iosCpa.devices" var="totalCpadevices"></s:set>
						  <s:set value="#totalCpaactiveUsers+#item.androidCpa.activeUsers+#item.iosCpa.activeUsers" var="totalCpaactiveUsers"></s:set>
						  <s:set value="#totalCpapayAmount+#item.androidCpa.payAmount+#item.iosCpa.payAmount" var="totalCpapayAmount"></s:set>
						  <s:set value="#totalCpapayTimes+#item.androidCpa.payTimes+#item.iosCpa.payTimes" var="totalCpapayTimes"></s:set>
						  <s:set value="#totalCpapayUsers+#item.androidCpa.payUsers+#item.iosCpa.payUsers" var="totalCpapayUsers"></s:set>
						  
						  <!-- andriod -->
						   <s:set value="#totalAndriodtotalRoleUser+#item.androidCps.totalRoleUser+#item.androidCpa.totalRoleUser" var="totalAndriodtotalRoleUser"></s:set>
						  <s:set value="#totalAndriodtotalRegUser+#item.androidCps.totalRegUser+#item.androidCpa.totalRegUser" var="totalAndriodtotalRegUser"></s:set>
						  <s:set value="#totalAndrioddevices+#item.androidCps.devices+#item.androidCpa.devices" var="totalAndrioddevices"></s:set>
						  <s:set value="#totalAndriodactiveUsers+#item.androidCps.activeUsers+#item.androidCpa.activeUsers" var="totalAndriodactiveUsers"></s:set>
						  <s:set value="#totalAndriodpayAmount+#item.androidCps.payAmount+#item.androidCpa.payAmount" var="totalAndriodpayAmount"></s:set>
						  <s:set value="#totalAndriodpayTimes+#item.androidCps.payTimes+#item.androidCpa.payTimes" var="totalAndriodpayTimes"></s:set>
						  <s:set value="#totalAndriodpayUsers+#item.androidCps.payUsers+#item.androidCpa.payUsers" var="totalAndriodpayUsers"></s:set>
						  
						  <!-- ios -->
						   <s:set value="#totalIostotalRoleUser+#item.iosCps.totalRoleUser+#item.iosCpa.totalRoleUser" var="totalIostotalRoleUser"></s:set>
						  <s:set value="#totalIostotalRegUser+#item.iosCps.totalRegUser+#item.iosCpa.totalRegUser" var="totalIostotalRegUser"></s:set>
						  <s:set value="#totalIosdevices+#item.iosCps.devices+#item.iosCpa.devices" var="totalIosdevices"></s:set>
						  <s:set value="#totalIosactiveUsers+#item.iosCps.activeUsers+#item.iosCpa.activeUsers" var="totalIosactiveUsers"></s:set>
						  <s:set value="#totalIospayAmount+#item.iosCps.payAmount+#item.iosCpa.payAmount" var="totalIospayAmount"></s:set>
						  <s:set value="#totalIospayTimes+#item.iosCps.payTimes+#item.iosCpa.payTimes" var="totalIospayTimes"></s:set>
						  <s:set value="#totalIospayUsers+#item.iosCps.payUsers+#item.iosCpa.payUsers" var="totalIospayUsers"></s:set>
						</s:iterator>
					</s:if>
                   			
					
					<tr>						
					<td rowspan='5'>曜月平台<br>共<s:property value="#valueList.size"/>款游戏</td>
					<td>CPS</td>
					<!-- cps -->
          			<td><s:property value="#totalCpstotalRoleUser"></s:property></td>
          			<td><s:property value="#totalCpstotalRegUser"></s:property></td>
          			<td><s:property value="#totalCpsdevices"></s:property></td>
          			<td><s:property value="#totalCpsactiveUsers"></s:property></td>
          			<td><fmt:formatNumber  value="${totalCpspayAmount/100 }" pattern="0.00" /></td>
          			<td><s:property value="#totalCpspayTimes"></s:property></td>
          			<td><s:property value="#totalCpspayUsers"></s:property></td>
          			
          			 <c:if test="${totalCpspayUsers==0}"><td>0.00</td></c:if>
                    <c:if test="${totalCpspayUsers>0}"><td><fmt:formatNumber  value="${totalCpspayAmount/100/totalCpspayUsers}" pattern="0.00" /></td> </c:if>         			
                    <c:if test="${totalCpstotalRegUser==0}"><td>0.00</td></c:if>
                    <c:if test="${totalCpstotalRegUser>0}"><td><fmt:formatNumber  value="${totalCpspayAmount/100/totalCpstotalRegUser}" pattern="0.00" /></td> </c:if>         			
					</tr>
						
					
					<tr>
					<td>CPA</td>
					<td><s:property value="#totalCpatotalRoleUser"></s:property></td>
          			<td><s:property value="#totalCpatotalRegUser"></s:property></td>
          			<td><s:property value="#totalCpadevices"></s:property></td>
          			<td><s:property value="#totalCpaactiveUsers"></s:property></td>
          			<td><fmt:formatNumber  value="${totalCpapayAmount/100 }" pattern="0.00" /></td>
          			<td><s:property value="#totalCpapayTimes"></s:property></td>
          			<td><s:property value="#totalCpapayUsers"></s:property></td>
          			
					 <c:if test="${totalCpapayUsers==0}"><td>0.00</td></c:if>
                    <c:if test="${totalCpapayUsers>0}"><td><fmt:formatNumber  value="${totalCpapayAmount/100/totalCpapayUsers}" pattern="0.00" /></td> </c:if>      
          			
                    <c:if test="${totalCpatotalRegUser==0}"><td>0.00</td></c:if>
                    <c:if test="${totalCpatotalRegUser>0}"><td><fmt:formatNumber  value="${totalCpapayAmount/100/totalCpatotalRegUser}" pattern="0.00" /></td> </c:if> 
					</tr>
						
						<tr>
					<td>安卓</td>
					<td><s:property value="#totalAndriodtotalRoleUser"></s:property></td>
          			<td><s:property value="#totalAndriodtotalRegUser"></s:property></td>
          			<td><s:property value="#totalAndrioddevices"></s:property></td>
          			<td><s:property value="#totalAndriodactiveUsers"></s:property></td>
          			<td><fmt:formatNumber  value="${totalAndriodpayAmount/100 }" pattern="0.00" /></td>
          			<td><s:property value="#totalAndriodpayTimes"></s:property></td>
          			<td><s:property value="#totalAndriodpayUsers"></s:property></td>
				 	<c:if test="${totalAndriodpayUsers==0}"><td>0.00</td></c:if>
                    <c:if test="${totalAndriodpayUsers>0}"><td><fmt:formatNumber  value="${totalAndriodpayAmount/100/totalAndriodpayUsers}" pattern="0.00" /></td> </c:if>      
          			
                    <c:if test="${totalAndriodtotalRegUser==0}"><td>0.00</td></c:if>
                    <c:if test="${totalAndriodtotalRegUser>0}"><td><fmt:formatNumber  value="${totalAndriodpayAmount/100/totalAndriodtotalRegUser}" pattern="0.00" /></td> </c:if> 
						</tr>
						<tr>
					<td>苹果</td>
					<td><s:property value="#totalIostotalRoleUser"></s:property></td>
          			<td><s:property value="#totalIostotalRegUser"></s:property></td>
          			<td><s:property value="#totalIosdevices"></s:property></td>
          			<td><s:property value="#totalIosactiveUsers"></s:property></td>
          			<td><fmt:formatNumber  value="${totalIospayAmount/100 }" pattern="0.00" /></td>
          			<td><s:property value="#totalIospayTimes"></s:property></td>
          			<td><s:property value="#totalIospayUsers"></s:property></td>
					 <c:if test="${totalIospayUsers==0}"><td>0.00</td></c:if>
                    <c:if test="${totalIospayUsers>0}"><td><fmt:formatNumber  value="${totalIospayAmount/100/totalIospayUsers}" pattern="0.00" /></td> </c:if>      
          			
                    <c:if test="${totalIostotalRegUser==0}"><td>0.00</td></c:if>
                    <c:if test="${totalIostotalRegUser>0}"><td><fmt:formatNumber  value="${totalIospayAmount/100/totalIostotalRegUser}" pattern="0.00" /></td> </c:if> 
						</tr>
						
						<tr>
					<td>合计</td>
					<td><s:property value="#totalCpstotalRoleUser+#totalCpatotalRoleUser"></s:property></td>
          			<td><s:property value="#totalCpstotalRegUser+#totalCpatotalRegUser"></s:property></td>
          			<td><s:property value="#totalCpsdevices+#totalCpadevices"></s:property></td>
          			<td><s:property value="#totalCpsactiveUsers+#totalCpaactiveUsers"></s:property></td>
          			<td><fmt:formatNumber  value="${(totalCpspayAmount+totalCpapayAmount)/100 }" pattern="0.00" /></td>
          			<td><s:property value="#totalCpspayTimes+#totalCpapayTimes"></s:property></td>
          			<td><s:property value="#totalCpspayUsers+#totalCpapayUsers"></s:property></td>
					 <c:if test="${(totalCpspayUsers+totalCpapayUsers)==0}"><td>0.00</td></c:if>
                    <c:if test="${(totalCpspayUsers+totalCpapayUsers)>0}"><td><fmt:formatNumber  value="${(totalCpspayAmount+totalCpapayAmount)/100/(totalCpspayUsers+totalCpapayUsers)}" pattern="0.00" /></td> </c:if>              			
                    <c:if test="${(totalCpstotalRegUser+totalCpatotalRegUser)==0}"><td>0.00</td></c:if>
                    <c:if test="${(totalCpstotalRegUser+totalCpatotalRegUser)>0}"><td><fmt:formatNumber  value="${(totalCpspayAmount+totalCpapayAmount)/100/(totalCpstotalRegUser+totalCpatotalRegUser)}" pattern="0.00" /></td> </c:if> 
					</tr>
					</tbody>
					
        		</table>
      		</div>
      		
      		
      		
      		
      		
      		
      		
      		
      		<div class="table-responsive">
        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
            				<th colspan="2"></th>
							<th>合作模式</th>
							<th>创角用户<i class="helpToolTip" data-content="玩家激活游戏后，进行了注册并创建了游戏角色的账户数量"></i></th>
							<th>注册用户<i class="helpToolTip" data-content="玩家激活游戏后，进行了注册有ID信息或者账户信息的玩家账户数量"></i></th>
							<th>注册设备<i class="helpToolTip" data-content="玩家激活游戏后，进行了注册有ID信息或者账户信息的玩家设备数量"></i></th>
							<th>活跃用户<i class="helpToolTip" data-content="一个时间段内最近7天有3天登录的用户（账号唯一）总量"></i></th>
							<th>充值金额<i class="helpToolTip" data-content="单个游戏的总收入金额"></i></th>
							<th>充值次数<i class="helpToolTip" data-content="所选时期内，单个游戏所有玩家成功充值总次数"></i></th>
							<th>付费人数<i class="helpToolTip" data-content="每日成功充值的用户（账号唯一）数量，去重"></i></th>
							<th>付费ARPU<i class="helpToolTip" data-content="游戏充值总额/游戏付费人数"></i></th>
							<th>注册ARPU<i class="helpToolTip" data-content="游戏充值总额/游戏注册用户"></i></th>
            			</tr>
          			</thead>
          			<tbody>
          			
          			<s:if test="gameClientReports.size>0  ||  gameClientMonthlyReports.size>0">			
          			<s:if test="gameClientReports.size>0  &&  gameClientMonthlyReports.size==0">
          			        <s:set value="gameClientReports"  var="valueList"></s:set>
          			</s:if>
          			
          			<s:if test="gameClientMonthlyReports.size>0  &&  gameClientReports.size==0">
          			        <s:set value="gameClientMonthlyReports" var="valueList"></s:set>
          			</s:if>
          			
						<s:iterator value="valueList" var="item">
								<tr>
									<td rowspan='5'>${item.appName}</td>
									<td rowspan='2'>安卓</td>
									<td>CPS</td>
									<td>${item.androidCps.totalRoleUser}</td>
									<td>${item.androidCps.totalRegUser }</td>
									<td>${item.androidCps.devices}</td>
									<td>${item.androidCps.activeUsers}</td>
									<td><fmt:formatNumber  value="${item.androidCps.payAmount/100 }" pattern="0.00" /></td>
									<td>${item.androidCps.payTimes }</td>
									<td>${item.androidCps.payUsers}</td>
									<td>
										<c:if test="${item.androidCps.payUsers== 0 }">0.00</c:if>
										<c:if test="${item.androidCps.payUsers> 0 }"><fmt:formatNumber  value="${(item.androidCps.payAmount )/100/(item.androidCps.payUsers ) }" pattern="0.00" /></c:if>
									</td>
									<td>
										<c:if test="${item.androidCps.totalRegUser== 0 }">0.00</c:if>
										<c:if test="${item.androidCps.totalRegUser> 0 }"><fmt:formatNumber  value="${(item.androidCps.payAmount )/100/(item.androidCps.totalRegUser) }" pattern="0.00" /></c:if>
									</td>
								</tr>
								<tr>
									<td>CPA</td>
									<td>${item.androidCpa.totalRoleUser }</td>
									<td>${item.androidCpa.totalRegUser }</td>
									<td>${item.androidCpa.devices }</td>
									<td>${item.androidCpa.activeUsers}</td>
									<td><fmt:formatNumber  value="${item.androidCpa.payAmount/100 }" pattern="0.00" /></td>
									<td>${item.androidCpa.payTimes }</td>
									<td>${item.androidCpa.payUsers }</td>
									<td>
										<c:if test="${item.androidCpa.payUsers==0 }">0.00</c:if>
										<c:if test="${item.androidCpa.payUsers> 0 }"><fmt:formatNumber  value="${item.androidCpa.payAmount/100/item.androidCpa.payUsers }" pattern="0.00" /></c:if>
									</td>
									<td>
										<c:if test="${item.androidCpa.totalRegUser == 0 }">0.00</c:if>
										<c:if test="${item.androidCpa.totalRegUser> 0 }"><fmt:formatNumber  value="${item.androidCpa.payAmount/100/item.androidCpa.totalRegUser }" pattern="0.00" /></c:if>
									</td>
								</tr>
								<tr>
									<td rowspan='2'>苹果</td>
									<td>CPS</td>
									<td>${item.iosCps.totalRoleUser}</td>
									<td>${item.iosCps.totalRegUser }</td>
									<td>${item.iosCps.devices}</td>
									<td>${item.iosCps.activeUsers}</td>
									<td><fmt:formatNumber  value="${item.iosCps.payAmount/100 }" pattern="0.00" /></td>
									<td>${item.iosCps.payTimes }</td>
									<td>${item.iosCps.payUsers }</td>
									<td>
										<c:if test="${item.iosCps.payUsers ==0 }">0.00</c:if>
										<c:if test="${item.iosCps.payUsers > 0 }"><fmt:formatNumber  value="${(item.iosCps.payAmount )/100/(item.iosCps.payUsers ) }" pattern="0.00" /></c:if>
									</td>
									<td>
										<c:if test="${item.iosCps.totalRegUser ==0 }">0.00</c:if>
										<c:if test="${item.iosCps.totalRegUser >0 }"><fmt:formatNumber  value="${(item.iosCps.payAmount )/100/(item.iosCps.totalRegUser ) }" pattern="0.00" /></c:if>
									</td>
								</tr>
								<tr>
									<td>CPA</td>
									<td>${item.iosCpa.totalRoleUser }</td>
									<td>${item.iosCpa.totalRegUser }</td>
									<td>${item.iosCpa.devices }</td>
									<td>${item.iosCpa.activeUsers}</td>
									<td><fmt:formatNumber  value="${item.iosCpa.payAmount/100 }" pattern="0.00" /></td>
									<td>${item.iosCpa.payTimes }</td>
									<td>${item.iosCpa.payUsers }</td>
									<td>
										<c:if test="${item.iosCpa.payUsers == 0 }">0.00</c:if>
										<c:if test="${item.iosCpa.payUsers > 0 }"><fmt:formatNumber  value="${item.iosCpa.payAmount/100/item.iosCpa.payUsers }" pattern="0.00" /></c:if>
									</td>
									<td>
										<c:if test="${item.iosCpa.totalRegUser == 0 }">0.00</c:if>
										<c:if test="${item.iosCpa.totalRegUser > 0 }"><fmt:formatNumber  value="${item.iosCpa.payAmount/100/item.iosCpa.totalRegUser }" pattern="0.00" /></c:if>
									</td>
								</tr>
								<tr>
									<td>合计</td>
									<td></td>
									<td>${item.iosCps.totalRoleUser + item.androidCps.totalRoleUser+item.iosCpa.totalRoleUser+item.androidCpa.totalRoleUser}</td>
									<td>${item.iosCps.totalRegUser +  item.androidCps.totalRegUser+item.iosCpa.totalRegUser +  item.androidCpa.totalRegUser}</td>
									<td>${item.iosCps.devices + item.androidCps.devices+item.iosCpa.devices + item.androidCpa.devices}</td>
									<td>${item.iosCps.activeUsers + item.androidCps.activeUsers+item.iosCpa.activeUsers + item.androidCpa.activeUsers}</td>
									<td><fmt:formatNumber  value="${(item.iosCps.payAmount + item.androidCps.payAmount+item.iosCpa.payAmount + item.androidCpa.payAmount)/100 }" pattern="0.00" /></td>
									<td>${item.iosCps.payTimes + item.androidCps.payTimes+item.iosCpa.payTimes + item.androidCpa.payTimes }</td>
									<td>${item.iosCps.payUsers +  item.androidCps.payUsers+item.iosCpa.payUsers +  item.androidCpa.payUsers}</td>
									<td>
										<c:if test="${item.iosCps.payUsers +  item.androidCps.payUsers+item.iosCpa.payUsers +  item.androidCpa.payUsers == 0 }">0.00</c:if>
										<c:if test="${item.iosCps.payUsers +  item.androidCps.payUsers+item.iosCpa.payUsers +  item.androidCpa.payUsers > 0 }">
										<fmt:formatNumber  value="${(item.iosCps.payAmount + item.androidCps.payAmount+item.iosCpa.payAmount + item.androidCpa.payAmount)/100/(item.iosCps.payUsers +  item.androidCps.payUsers+item.iosCpa.payUsers +  item.androidCpa.payUsers) }" pattern="0.00" /></c:if>
									</td>
									<td>
										<c:if test="${item.iosCps.totalRegUser +  item.androidCps.totalRegUser == 0 }">0.00</c:if>
										<c:if test="${item.iosCps.totalRegUser +  item.androidCps.totalRegUser > 0 }">
										<fmt:formatNumber  value="${(item.iosCps.payAmount + item.androidCps.payAmount+item.iosCpa.payAmount + item.androidCpa.payAmount)/100/(item.iosCps.totalRegUser +  item.androidCps.totalRegUser+item.iosCpa.totalRegUser +  item.androidCpa.totalRegUser) }" pattern="0.00" /></c:if>
									</td>
								</tr>
								
						</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="12" style="text-align: center;">当前列表没有数据！</td>
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
				location.assign("report_summary.shtml"); 
				});
			$("#reportLeft_1").addClass("active");
			$("#summary").addClass("active");
		});
	</script>
</body>
</html>