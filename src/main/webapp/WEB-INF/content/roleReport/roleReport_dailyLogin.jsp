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
        	<form action="roleReport_dailyLogin.shtml" method="post" id="mainForm">
	        	<div class="form-inline popover-show panel-body list_toolbar">
		      		<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">
			          	<select class="form-control" name="appId" id="appId" onchange="changeApp();"  placeholder="请选择游戏">
							<s:iterator value="games" var="item">
								<option value="${item.id }" <c:if test="${item.id==appId }">selected</c:if>>${item.appName }</option>
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
			            <input  class="form-control daterange" type="text" readonly name="selectRange" id="selectRange" value="${selectRange }"  placeholder="时间选择" sType="searchAndValid" />
			         </div>
<!-- 		      		<div class="form-group width_btn"> -->
<!-- 		          		<button  type="button" class="btn  btn-primary " onclick="searchAndValid();"><i class="icon-search"></i> 搜索</button> -->
<!-- 		        	</div> -->
		    	</div>
	    	</form>
        </div>
        <div class="panel panel-default">
          <div class="panel-body ">
          	  <ul class="nav nav-tabs " >
				 <li id="realTime"><a href="javascript:realTimeLogin();" role="tab">实时统计表</a></li>
				  <li id="daily"><a href="javascript:dailyLogin();" role="tab">日统计表</a></li>
			  </ul>
			  <br/>
      		<div class="table-responsive">
	      		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
            				<th>时间</th>
            				<th>角色创建数</th>
            				<th>登陆角色数</th>
            				<th>首次登陆角色</th>
            				<th>登陆次数</th>
            			</tr>
          			</thead>
          			<tbody>
    					<s:set var="itemSize" value="#result['data'].size"></s:set>
	     				<s:if test="result['data'].size > 0">
		         				<s:iterator value="result['data']" var="item" status="st">
										<tr>
											<td><s:date name="statDate" format="yyyy-MM-dd"/></td>
											<td>${roleEstablishs}</td>
											<td>${roleLogins}</td>
											<td>${roleFirstLogins}</td>
											<td>${loginTimes}</td>
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
			$("#roleReportLeft_1").addClass("active");
			$("#daily").addClass("active");
			changeApp();
		});
		
		function realTimeLogin(){
			$("#selectRange").val("");
			$("#mainForm").attr("action","/roleReport/roleReport_realTimeLogin.shtml");
			$("#mainForm").submit();
		}
		function dailyLogin(){
			$("#selectRange").val("");
			$("#mainForm").attr("action","/roleReport/roleReport_dailyLogin.shtml");
			$("#mainForm").submit();
		}
		function changeApp(){
			$("#zoneId").empty();
			$("#platformId").empty();
			
			var appId = $("#appId").val();
			var platformId = '${platformId}';
			var zoneId = '${zoneId}';
			$.post("/bGamezone/bGamezone_getGameZonesAsync.shtml",{appId:appId},function(data){
				json = eval(data);
				$("#zoneId").append("<option value=''>==请选择服务器==</option>");
			    for(var i=0; i<json.length; i++){
			    	if(json[i].zoneId == zoneId)
			    		$("#zoneId").append("<option selected value='" + json[i].zoneId + "'>" + json[i].zoneName + "</option>");
			    	else
			    		$("#zoneId").append("<option value='" + json[i].zoneId + "'>" + json[i].zoneName + "</option>");
			    } 
		    });
			
			$.post("/bPlatformApp/bPlatformApp_getGamePlatformsAsync.shtml",{id:appId},function(data){
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