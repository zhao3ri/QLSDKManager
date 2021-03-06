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
        	<form action="reportLtv_ltv.shtml" method="post" id="mainForm">
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
	            				<th rowspan='<s:property value="result['data'].size + 1"/>' style="background-color: #f2f2f2;"><span style="margin:5px 10px; display:block;">L<br/>T<br/>V</span></th>
	            				<th>时间</th>
	            				<th>注册用户</th>
	            				<th>一日LTV</th>
	            				<th>二日LTV</th>
	            				<th>三日LTV</th>
	            				<th>四日LTV</th>
	            				<th>五日LTV</th>
	            				<th>六日LTV</th>
	            				<th>七日LTV</th>
	            				<th>十四日LTV</th>
	            				<th>三十日LTV</th>
	            				<th>60日LTV</th>
	            				<th>90日LTV</th>
	            			</tr>
	          			<s:iterator value="result['data']" var="item" status="st">
	          				<tr>
	          					<!-- 第一个模块 -->
	          					<td><s:date name="statDate" format="yyyy-MM-dd"/></td>
	          					<td>${registUser}</td>
	          					<!--
	          					    <td>${ltv1/100}</td>
	          					    <td>${(ltv2+ltv1)/100}</td>
	          					    <td>${ltv3/100}</td>
	          					    <td>${ltv4/100}</td>
	          					    <td>${ltv5/100}</td>
	          					    <td>${ltv6/100}</td>
	          					    <td>${ltv7/100}</td>
	          					    <td>${ltv14/100}</td>
	          					    <td>${ltv30/100}</td>
	          					    <td>${ltv60/100}</td>
	          					    <td>${ltv90/100}</td>
                                -->
	          					<c:if test="${registUser > 0 && ltv1> 0}">
                                   <td><fmt:formatNumber  value="${ltv1 / 100/registUser}" pattern="0.00" /></td>
                                </c:if>
	          					<c:if test="${registUser > 0 && ltv2> 0}">
                                   <td><fmt:formatNumber  value="${(ltv2) / 100/registUser}" pattern="0.00" /></td>
                                </c:if>
	          					<c:if test="${registUser > 0 && ltv3> 0}">
                                   <td><fmt:formatNumber  value="${(ltv3) / 100/registUser}" pattern="0.00" /></td>
                                </c:if>
	          					<c:if test="${registUser > 0 && ltv4> 0}">
                                   <td><fmt:formatNumber  value="${(ltv4) / 100/registUser}" pattern="0.00" /></td>
                                </c:if>
	          					<c:if test="${registUser > 0 && ltv5> 0}">
                                   <td><fmt:formatNumber  value="${(ltv5) / 100/registUser}" pattern="0.00" /></td>
                                </c:if>
	          					<c:if test="${registUser > 0 && ltv6> 0}">
                                   <td><fmt:formatNumber  value="${(ltv6) / 100/registUser}" pattern="0.00" /></td>
                                </c:if>
	          					<c:if test="${registUser > 0 && ltv7> 0}">
                                   <td><fmt:formatNumber  value="${(ltv7) / 100/registUser}" pattern="0.00" /></td>
                                </c:if>
	          					<c:if test="${registUser > 0 && ltv14> 0}">
                                   <td><fmt:formatNumber  value="${(ltv14) / 100/registUser}" pattern="0.00" /></td>
                                </c:if>
	          					<c:if test="${registUser > 0 && ltv30> 0}">
                                   <td><fmt:formatNumber  value="${(ltv30) / 100/registUser}" pattern="0.00" /></td>
                                </c:if>
	          					<c:if test="${registUser > 0 && ltv60> 0}">
                                   <td><fmt:formatNumber  value="${(ltv60) / 100/registUser}" pattern="0.00" /></td>
                                </c:if>
	          					<c:if test="${registUser > 0 && ltv90> 0}">
                                   <td><fmt:formatNumber  value="${(ltv90) / 100/registUser}" pattern="0.00" /></td>
                                </c:if>
	          				</tr>
	          			</s:iterator>
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
			$("#reportLeft_13").addClass("active");
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
				$("#mainForm").attr("action","reportLtv_operateExport.shtml");
				$("#mainForm").submit();
				$("#mainForm").attr("action","reportLtv_operate.shtml");
			}
		}
	</script>
</body>
</html>