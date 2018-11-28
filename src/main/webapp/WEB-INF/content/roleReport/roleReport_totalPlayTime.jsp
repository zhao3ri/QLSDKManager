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
  		<form action="roleReport_totalPlayTime.shtml" method="post" id="mainForm">
        <div class="panel panel-default">
        	
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
			        <br/>
			         <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请输入用户id">
			        	<input  class="form-control" type="text"  placeholder="用户id" name="uid" value="${uid }"/>
			        </div>
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请输入角色名称">
			        	<input  class="form-control" type="text"  placeholder="角色名称" name="name" value="${name }"/>
			        </div>
			        
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="时间">
			            <input  class="form-control daterange" type="text" readonly name="selectRange" value="${selectRange }"  placeholder="时间选择" sType="searchAndValid" />
			          </div>
<!-- 		      		<div class="form-group width_btn"> -->
<!-- 		          		<button  type="button" class="btn  btn-primary " onclick="searchAndValid();"><i class="icon-search"></i> 搜索</button> -->
<!-- 		        	</div> -->
		    	</div>
          <div class="panel-body ">
          	  <ul class="nav nav-tabs " >
				  <li id="playTime"><a href="/roleReport/roleReport_playTime.shtml" identity="tab">单次使用时长表</a></li>
				  <li id="totalPlayTime"><a href="/roleReport/roleReport_totalPlayTime.shtml" identity="tab">总使用时长表</a></li>
			  </ul>
			  <br/>
      		<div class="table-responsive">
	      		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
            				<th>账号id</th>
            				<th>角色名</th>
            				<th>服务器</th>
            				<th>渠道名</th>
            				<th>当前时段使用时长</th>
            				<th>总使用时长</th>
            			</tr>
          			</thead>
          			<tbody>
         				<s:if test="page.result.size>0">
						<s:iterator value="page.result" var="page">
						<tr>
							<td><s:property value="uid"/></td>
							<td><s:property value="name"/></td>
							<td><s:property value="zoneName"/></td>
							<td><s:property value="platformName"/></td>
							<td><s:property value="playTime"/></td>
							<td><s:property value="countPlayTime"/></td>
						</tr>
						</s:iterator>
						</s:if>
						<s:else>
						<tr>
							<td></td>
							<td colspan="6" style="text-align: center;">当前列表没有数据！</td>
						</tr>
						</s:else>
          			</tbody>
         		</table>
      		</div>
      		<s:if test="page.result.size>0">
	      		<div class="table_page dropup">
	      			<c:set var="p" value="page"/>
			        <%@ include file="/common/pagination.jsp" %>
			    </div>
      		</s:if>
		 </div>
        </div>
        </form>
      </div>
    </div>
  </div>
</div>
    <%@ include file="/common/footer.jsp" %>      
	<script type="text/javascript">
		$(document).ready(function() {
			$("#roleReportLeft_3").addClass("active");
			$("#totalPlayTime").addClass("active");
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
			
			$.post("/bPlatformApp/bPlatformApp_getGamePlatformsAsync.shtml",{id:gameId},function(data){
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