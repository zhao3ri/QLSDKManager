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
        	<form action="roleReport_keepRoles.shtml" method="post" id="mainForm">
	        	<div class="form-inline popover-show panel-body list_toolbar">
		      		<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">
			          	<select class="form-control" name="gameId" id="gameId" onchange="changeApp();"  placeholder="请选择游戏">
							<s:iterator value="games" var="item">
								<option value="${item.id }" <c:if test="${item.id==gameId }">selected</c:if>>${item.gameName }</option>
							</s:iterator>
						</select>
			        </div>
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择渠道">
						<select class="form-control" name="platformId" id="platformId" placeholder="请选择渠道">
							<option value="">==请选择渠道==</option>
						</select>
			        </div>
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择服务器">
						<select class="form-control" name="zoneId" id="zoneId"  placeholder="请选择服务器">
							<option value="">==请选择服务器==</option>
						</select>
			        </div>
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="时间">
		            	<input  class="form-control daterange" type="text" readonly name="selectRange" value="${selectRange }"  placeholder="时间" sType="searchAndValid" />
		            </div>
<!-- 		      		<div class="form-group width_btn"> -->
<!-- 		          		<button  type="button" class="btn  btn-primary " onclick="searchAndValid();"><i class="icon-search"></i> 搜索</button> -->
<!-- 		        	</div> -->
		    	</div>
	    	</form>
        </div>
        <div class="panel panel-default">
          <div class="panel-body ">
      		<div class="table-responsive">
        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
            				<th>时间</th>
            				<th>新增用户</th>
            				<%--<th>次日留存</th>--%>
            				<th>2日留存</th>
            				<th>3日留存</th>
            				<th>4日留存</th>
            				<th>5日留存</th>
            				<th>6日留存</th>
            				<th>7日留存</th>
            				<th>15日留存</th>
            				<th>30日留存</th>
            			</tr>
          			</thead>
          			<tbody>
    					<s:if test="result['data'].size>0">
								<s:iterator value="result['data']" var="item">
									<tr>
										<td><s:date name="statDate" format="yyyy-MM-dd"/></td>
										<td>${item.roleEstablishs }</td>
										<%--<td>${item.keepRole1 < 0 ? "--" : item.keepRole1 }</td>--%>
										<td>${item.keepRole2 < 0 ? "--" : item.keepRole2 }</td>
										<td>${item.keepRole3 < 0 ? "--" : item.keepRole3 }</td>
										<td>${item.keepRole4 < 0 ? "--" : item.keepRole4 }</td>
										<td>${item.keepRole5 < 0 ? "--" : item.keepRole5 }</td>
										<td>${item.keepRole6 < 0 ? "--" : item.keepRole6 }</td>
										<td>${item.keepRole7 < 0 ? "--" : item.keepRole7 }</td>
										<td>${item.keepRole15 < 0 ? "--" : item.keepRole15 }</td>
										<td>${item.keepRole30 < 0 ? "--" : item.keepRole30 }</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="10" style="text-align: center;">当前列表没有数据！</td>
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
			$("#roleReportLeft_keep").addClass("active");
			changeApp();
		});
		
		function changeApp(){
			$("#zoneId").empty();
			$("#platformId").empty();
			
			var gameId = $("#gameId").val();
			var platformId = '${platformId}';
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
			
			$.post("${ctx}/bPlatformGame/bPlatformGame_getGamePlatformsAsync.shtml",{id:gameId},function(data){
				json = eval(data);
				$("#platformId").append("<option value=''>==请选择渠道==</option>");
			    for(var i=0; i<json.length; i++){
			    	if(json[i].platformId == platformId)
			    		$("#platformId").append("<option selected value='" + json[i].platformId + "'>" + json[i].platformName + "</option>");
			    	else
			    		$("#platformId").append("<option value='" + json[i].platformId + "'>" + json[i].platformName + "</option>");
			    } 
		    });
		}
	</script>
</body>
</html>