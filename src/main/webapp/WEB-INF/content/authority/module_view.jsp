<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
	<div id="wrap">
	<%@ include file="/common/header.jsp" %>
	<div class="container">
	    <ol class="breadcrumb row">
	      <li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
	      <li class="active">系统管理</li>
	      <li><a href="${ctx}/module/module_list.shtml">模块列表</a></li>
	      <li class="active">新增/修改模块</li>
	    </ol>
	    <div class="panel panel-default">
	      <div class="panel-heading ">
	        <h3 class="panel-title">新增/修改模块</h3>
	      </div>
		<form role="form" id="inputForm" action="module_save.shtml" method="post">
			<input type="hidden" name="module.id" value="${module.id }"/>
			<div class="panel-body ">
	          <div class=" tooltip-show form-horizontal">
	          	<div class="form-group clearfix" >
	              <label  class="control-label col-sm-3  col-lg-2 text-right"><b class="color_red">*</b>模块名称：</label>
	              <div class=" col-sm-9   col-lg-5">
	                <input  class="form-control" type="text" id="module.moduleName" name="module.moduleName" maxlength="20" value="${module.moduleName }"  placeholder="请输入模块名称"/>
	              </div>
	              <span class=" help-block col-sm-9 col-sm-offset-3  col-lg-5 col-lg-offset-0">请输入模块名称</span>
	            </div>
	            <div class="form-group clearfix" >
	              <label  class="control-label col-sm-3  col-lg-2 text-right"><b class="color_red">*</b>排序：</label>
	              <div class=" col-sm-9   col-lg-5">
	                <input  class="form-control" type="text" id="module.moduleOrder" name="module.moduleOrder" maxlength="5" value="${module.moduleOrder }"  placeholder="请输入排序"/>
	              </div>
	              <span class=" help-block col-sm-9 col-sm-offset-3  col-lg-5 col-lg-offset-0">请输入排序</span>
	            </div>
             </div>
     	    </div>
           <div class="panel-footer">
          <div class=" text-center">
			<button type="submit" class="btn btn-primary">提交</button>
			<a class="btn btn-default" href="module_list.shtml">取消</a>
          </div>
           </div>
		</form>
  	</div>
  </div>
</div>	
<%@ include file="/common/footer.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {
		$("#inputForm").validate({
			rules: {
				"module.moduleName": {
					required: true,
					maxlength:20
				},
				"module.moduleOrder":{
					required: true,
					digits:true,
					maxlength:7
				}
			}
		});
	});
</script>
</body>
</html>
