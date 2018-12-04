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
	      <li class="active">修改密码</li>
	    </ol>
	    <div class="panel panel-default">
	      <div class="panel-heading ">
	        <h3 class="panel-title">修改密码</h3>
	      </div>
			<form role="form" id="inputForm" action="user_saveMyPassword.shtml" method="post">
				<input type="hidden" name="user.id" value="${user.id }"/>
				<div class="panel-body ">
		          <div class=" tooltip-show form-horizontal">
		          	<div class="form-group clearfix" >
		              <label  class="control-label col-sm-3  col-lg-2 text-right"><b class="color_red">*</b>旧密码：</label>
		              <div class=" col-sm-9   col-lg-5">
		                <input  class="form-control" type="password" id="oldPassword" name="oldPassword" maxlength="32" value="" placeholder="(请输入现使用的密码)"/>
		              </div>
		              <span class=" help-block col-sm-9 col-sm-offset-3  col-lg-5 col-lg-offset-0">(请输入现使用的密码)</span>
		            </div>
		          	<div class="form-group clearfix" >
		              <label  class="control-label col-sm-3  col-lg-2 text-right"><b class="color_red">*</b>新密码：</label>
		              <div class=" col-sm-9  col-lg-5">
		                <input  class="form-control" type="password" id="password" name="user.password" maxlength="32" value=""  placeholder="(请输入新密码)"/>
		              </div>
		              <span class=" help-block col-sm-9 col-sm-offset-3  col-lg-5 col-lg-offset-0">(请输入新密码)</span>
		            </div>
		            <div class="form-group clearfix" >
		              <label  class="control-label col-sm-3  col-lg-2 text-right"><b class="color_red">*</b>重复密码：</label>
		              <div class=" col-sm-9   col-lg-5">
		                <input  class="form-control" type="password" id="password2" name="password2" maxlength="32" value=""  placeholder="(再次输入新密码)"/>
		              </div>
		              <span class=" help-block col-sm-9 col-sm-offset-3  col-lg-5 col-lg-offset-0">(再次输入新密码)</span>
		            </div>
	             </div>
	     	    </div>
	           <div class="panel-footer">
	          <div class=" text-center">
				<button type="submit" class="btn btn-primary">提交</button>
				<a class="btn btn-default" href="${ctx }/index.shtml">取消</a>
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
				"user.password":{
					required:true,
					maxlength:32
				},
				"password2": {
					equalTo: "#password"
				},
				"oldPassword": {
					required:true,
					maxlength:32
				}
			},
			messages: {
				"password2": {
					equalTo: "两次输入的密码不一致"
				}
			}
		});
		
	});
</script>
</body>
</html>
