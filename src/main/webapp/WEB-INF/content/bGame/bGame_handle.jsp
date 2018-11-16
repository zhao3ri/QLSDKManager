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
	      <li><a href="${ctx}/bGame/bGame_list.shtml">游戏列表</a></li>
	      <s:if test="id==null">
	      <li class="active">新增游戏信息</li>
	      </s:if>
	      <s:else>
	      <li class="active">修改游戏信息</li>
	      </s:else>
	    </ol>
	    <div class="panel panel-default">
	      	<div class="panel-heading ">
	        	<h3 class="panel-title">${id==null?"新增":"修改"}游戏信息</h3>
	      	</div>
	      	<form identity="form" id="inputForm" action="bGame_save.shtml" method="post">
	        	<div class="panel-body ">
	          		<div class=" tooltip-show form-horizontal">
	    				<input type="hidden" name="game.id" value="${id }"/>
						<div class="form-group clearfix" >
							<label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>游戏名称：</label>
							<div class=" col-sm-9 col-lg-5">
							<input class="form-control" type="text" id="game.appName" name="game.appName" value="${game.appName}"  placeholder="请输入游戏名称"/>
							</div>
						</div>
						<div class="form-group clearfix" >
							<label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>开服时间：</label>
							<div  class="input-group  input-append date form_date col-sm-9 col-lg-5"  data-date-format="yyyy-mm-dd" >
									<input class="form-control "  id="game.serviceTime"  type="text" name="game.serviceTime"   readonly  placeholder="开服时间" data-toggle="popover"  data-placement="top" data-content="请选择日期"   value="<s:date name='game.serviceTime'  format='yyyy-MM-dd'/>"/>
									<span class="add-on input-group-addon"><i class="icon-remove"></i></span> 
									<span class="add-on input-group-addon"><i class="icon-calendar"></i></span>
				                </div>
						</div>
	     			</div>
  	    		</div>
	    		<div class="panel-footer">
	          		<div class=" text-center">
						<button type="submit" class="btn btn-primary"><i class="icon-ok"></i> 提交</button>
						<a class="btn btn-default" href="bGame_list.shtml"><i class="icon-remove"></i> 取消</a>
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
					"game.appName":{required:true,maxlength:20},
			        "game.serviceTime":{required:true}
				}
			});
		});
	</script>
</body>
</html>