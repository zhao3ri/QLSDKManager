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
	      <li><a href="${ctx}/function/function_list.shtml">功能列表</a></li>
	      <li class="active">新增/修改功能</li>
	    </ol>
	    <div class="panel panel-default">
	      <div class="panel-heading ">
	        <h3 class="panel-title">新增/修改功能</h3>
	      </div>
			<form id="inputForm" action="function_save.shtml" method="post">
				<input type="hidden" name="function.id" value="${function.id }"/>
				<div class="panel-body ">
		          <div class=" tooltip-show form-horizontal">
		          	<s:if test="function.id==null">
		          	<div class="form-group clearfix" >
		              <label  class="control-label col-sm-3  col-lg-2 text-right">所属模块：</label>
		              <div class=" col-sm-9 col-lg-5">
		                <s:select name="auth.moduleID" list="moduleList" listKey="id" listValue="moduleName" onselect="请选择所属模块"  cssClass="form-control"/>
		              </div>
		            </div>
		            </s:if>
		            <div class="form-group clearfix" >
		              <label  class="control-label col-sm-3  col-lg-2 text-right"><b class="color_red">*</b>功能地址：</label>
		              <div class=" col-sm-9 col-lg-5">
		                <input  class="form-control" type="text" id="function.functionName" name="function.functionName" maxlength="100" value="<s:property value="function.functionName"/>"  placeholder="请输入功能地址"/>
		              </div>
		              <span class=" help-block col-sm-9 col-sm-offset-3  col-lg-5 col-lg-offset-0">请输入功能地址</span>
		            </div>
		          	<div class="form-group clearfix" >
		              <label  class="control-label col-sm-3  col-lg-2 text-right">功能名称：</label>
		              <div class=" col-sm-9 col-lg-5">
		                <input  class="form-control" type="text" id="function.description" name="function.description" maxlength="20" value="<s:property value="function.description"/>"  placeholder="请输入功能名称"/>
		              </div>
		              <span class=" help-block col-sm-9 col-sm-offset-3  col-lg-5 col-lg-offset-0">请输入功能名称</span>
		            </div>
		            <div class="form-group clearfix" >
		              <label  class="control-label col-sm-3  col-lg-2 text-right"><b class="color_red">*</b>排序：</label>
		              <div class=" col-sm-9 col-lg-5">
		                <input  class="form-control" type="text" id="function.functionOrder" name="function.functionOrder" maxlength="5" value="${function.functionOrder }"  placeholder="请输入排序"/>
		              </div>
		              <span class=" help-block col-sm-9 col-sm-offset-3  col-lg-5 col-lg-offset-0">请输入排序</span>
		            </div>
	             </div>
	     	    </div>
	           <div class="panel-footer">
	          <div class=" text-center">
				<button type="submit" class="btn btn-primary">提交</button>
				<a class="btn btn-default" href="function_list.shtml">取消</a>
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
				"function.functionName": {
					required: true,
					maxlength:100
				},
				"function.functionOrder":{
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
