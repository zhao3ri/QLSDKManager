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
        	<form action="roleReport_playTime.shtml" method="post" id="mainForm">
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
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top">
							<div  class="input-group  input-append date form_date "  data-date-format="yyyy-mm-dd" sType="searchAndValid" >
								<input class="form-control "  id="selectRange"  type="text" name="selectRange"   readonly  placeholder="时间选择" data-toggle="popover"  data-placement="top" data-content="请选择日期"   value="${selectRange}"/>
								<span class="add-on input-group-addon"><i class="icon-remove"></i></span> 
								<span class="add-on input-group-addon"><i class="icon-calendar"></i></span>
			                </div>
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
				  <li id="playTime"><a href="${ctx}/roleReport/roleReport_playTime.shtml" role="tab">单次使用时长表</a></li>
				  <li id="totalPlayTime"><a href="${ctx}/roleReport/roleReport_totalPlayTime.shtml" role="tab">总使用时长表</a></li>
			  </ul>
      		<div class="table-responsive">
	      		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
            				<th>使用时长</th>
            				<th>用户数</th>
            				<th>使用次数</th>
            				<th>用户比例</th>
            			</tr>
          			</thead>
          			<tbody>
          			<s:if test="result['data'].size>0">
							<tr><td>1-30秒</td><td>${result['data'].su1}</td><td>${result['data'].st1}</td>
							<td><fmt:formatNumber  value="${result['data'].sumSu>0 ? result['data'].su1 * 100/result['data'].sumSu : 0  }" pattern="0.00" />%
							<tr><td>31-60秒</td><td>${result['data'].su2}</td><td>${result['data'].st2}</td>
							<td><fmt:formatNumber  value="${result['data'].sumSu>0 ? result['data'].su2 * 100/result['data'].sumSu : 0  }" pattern="0.00" />%
							<tr><td>1-3分钟</td><td>${result['data'].su3}</td><td>${result['data'].st3}</td>
							<td><fmt:formatNumber  value="${result['data'].sumSu>0 ? result['data'].su3 * 100/result['data'].sumSu : 0  }" pattern="0.00" />%
							<tr><td>3-10分钟</td><td>${result['data'].su4}</td><td>${result['data'].st4}</td>
							<td><fmt:formatNumber  value="${result['data'].sumSu>0 ? result['data'].su4 * 100/result['data'].sumSu : 0  }" pattern="0.00" />%
							<tr><td>10-30分钟</td><td>${result['data'].su5}</td><td>${result['data'].st5}</td>
							<td><fmt:formatNumber  value="${result['data'].sumSu>0 ? result['data'].su5 * 100/result['data'].sumSu : 0  }" pattern="0.00" />%
							<tr><td>30分-1小时</td><td>${result['data'].su6}</td><td>${result['data'].st6}</td>
							<td><fmt:formatNumber  value="${result['data'].sumSu>0 ? result['data'].su6 * 100/result['data'].sumSu : 0  }" pattern="0.00" />%
							<tr><td>1-2小时</td><td>${result['data'].su7}</td><td>${result['data'].st7}</td>
							<td><fmt:formatNumber  value="${result['data'].sumSu>0 ? result['data'].su7 * 100/result['data'].sumSu : 0  }" pattern="0.00" />%
							<tr><td>2小时以上</td><td>${result['data'].su8}</td><td>${result['data'].st8}</td>
							<td><fmt:formatNumber  value="${result['data'].sumSu>0 ? result['data'].su8 * 100/result['data'].sumSu : 0  }" pattern="0.00" />%
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
			$("#roleReportLeft_duration").addClass("active");
			$("#playTime").addClass("active");
			changeApp();
		});
		
		function changeApp(){
			$("#zoneId").empty();
			$("#platformId").empty();
			
			var gameId = $("#gameId").val();
			var platformId = '${platformId}';
			var zoneId = '${zoneId}';
			$.post("/bGamezone/bGamezone_getGameZonesAsync.shtml",{gameId:gameId},function(data){
				json = eval(data);
				$("#zoneId").append("<option value=''>==请选择服务器==</option>");
			    for(var i=0; i<json.length; i++){
			    	if(json[i].zoneId == zoneId)
			    		$("#zoneId").append("<option selected value='" + json[i].zoneId + "'>" + json[i].zoneName + "</option>");
			    	else
			    		$("#zoneId").append("<option value='" + json[i].zoneId + "'>" + json[i].zoneName + "</option>");
			    } 
		    });
			
			$.post("/bPlatformGame/bPlatformGame_getGamePlatformsAsync.shtml",{id:gameId},function(data){
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