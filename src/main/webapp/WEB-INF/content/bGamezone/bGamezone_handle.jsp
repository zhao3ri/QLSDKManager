<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
	<div id="wrap">
	<%@ include file="/common/header.jsp" %>
	<div class="container">
	    <ol class="breadcrumb row">
	      <li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
	      <li class="active">游戏管理</li>
	      <li><a href="${ctx}/bGamezone/bGamezone_list.shtml">游戏分区列表</a></li>
	      <s:if test="id==null">
	      <li class="active">新增游戏分区信息</li>
	      </s:if>
	      <s:else>
	      <li class="active">修改游戏分区信息</li>
	      </s:else>
	    </ol>
	    <div class="panel panel-default">
	      	<div class="panel-heading ">
	        	<h3 class="panel-title">${id==null?"新增":"修改"}游戏分区信息</h3>
	      	</div>
	      	<form identity="form" id="inputForm" action="bGamezone_save.shtml" method="post">
	        	<div class="panel-body ">
	          		<div class=" tooltip-show form-horizontal">
	    				<input type="hidden" name="gamezone.id" value="${id }"/>
	    				
	    				<div class="form-group clearfix" >
							<label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>游戏：</label>
							<div class=" col-sm-9 col-lg-5">
							 <select class="form-control" name="gamezone.gameId"  data-original-title="" title="">
						         <option value="">请选择游戏</option>
						         <s:iterator value="gameList" var="item">
							     <option value="${item.id}" <c:if test="${item.id==gamezone.gameId}">selected</c:if>>${item.gameName }</option>
						         </s:iterator>								
				             </select>
							</div>
						</div>
	    				
						<div class="form-group clearfix" >
							<label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>分区ID：</label>
							<div class=" col-sm-9 col-lg-5">
							<input class="form-control" type="text" id="gamezone.zoneId" name="gamezone.zoneId" value="${gamezone.zoneId}"  placeholder="请输入分区ID"/>
							</div>
						</div>
						<div class="form-group clearfix" >
							<label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>分区名称：</label>
							<div class=" col-sm-9 col-lg-5">
							<input class="form-control" type="text" id="gamezone.zoneName" name="gamezone.zoneName" value="${gamezone.zoneName}"  placeholder="请输入分区名称"/>
							<label id="zoneNameinfo"></label>
							</div>
						</div>
	     			</div>
  	    		</div>
	    		<div class="panel-footer">
	          		<div class=" text-center">
						<button type="submit" class="btn btn-primary"><i class="icon-ok"></i> 提交</button>
						<a class="btn btn-default" href="bGamezone_list.shtml"><i class="icon-remove"></i> 取消</a>
	          		</div>
            	</div>
          	</form>
      	</div>
  	</div>
	</div>
	<%@ include file="/common/footer.jsp" %>    
	<script type="text/javascript">
		$(document).ready(function() {
			appendValidate();
			$("#inputForm").validate({
				rules: {
					"gamezone.zoneId":{required:true},
			        "gamezone.zoneName":{required:true},
					"gamezone.gameId":{required:true}
				}
			});
		});
	</script>
</body>
</html>