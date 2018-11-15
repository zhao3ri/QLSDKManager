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
	      <li><a href="${ctx}/role/role_list.shtml">身份列表</a></li>
	      <li class="active">新增身份</li>
	    </ol>
	    <div class="panel panel-default">
	      <div class="panel-heading ">
	        <h3 class="panel-title">新增身份</h3>
	      </div>
			<style type="text/css">
				fieldset {
					border:1px solid #ccc;
					background:#f5f5f5;
					margin:8px 0;
					line-height:32px;
					padding:0 10px 8px 10px;
				}
				legend {
					position:relative;
					bottom:0;
					right:9px;
				}
			</style>
			<form role="form" id="inputForm" action="role_save.shtml" method="post">
				<div class="panel-body ">
		          <div class=" tooltip-show form-horizontal">
		          	<div class="form-group clearfix" >
		              <label  class="control-label col-sm-3  col-lg-2 text-right"><b class="color_red">*</b>身份名称：</label>
		              <div class=" col-sm-9 col-lg-5">
		                <input  class="form-control" type="text" id="roleName" name="role.roleName" maxlength="50" value="<s:property value="role.roleName"/>"  placeholder="请输入身份名称"/>
		              </div>
		              <span class=" help-block col-sm-9 col-sm-offset-3  col-lg-5 col-lg-offset-0">请输入身份名称</span>
		            </div>
		            <div class="form-group clearfix" >
		              <label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>身份描述：</label>
		              <div class=" col-sm-9  col-lg-5">
		                <textarea class="form-control" name="role.description" id="role.description" rows="4"></textarea>
		              </div>
		              <span class=" help-block col-sm-9 col-sm-offset-3  col-lg-5 col-lg-offset-0">请输入身份描述</span>
		            </div>
		            <div class="form-group clearfix" >
		              <label  class="control-label col-sm-3  col-lg-2 text-right">权限设置：</label>
		              <div class=" col-sm-9">
		              	<fieldset>
		                	<legend><b>应用授权</b></legend>
		                	<fieldset>
	                            <legend>	                            	              
	                            	<input type="checkbox" onclick="selectAll(this,'game')"/>全选
	                            </legend>
	                            <s:iterator value="games" var="game">
	                            	<input type="checkbox" name="gameIds" lang="game" value="${game.id }"/>&nbsp;${game.appName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                            </s:iterator>
	                     	</fieldset>
	                    </fieldset>
		                <s:iterator value="moduleList" var="module">
			                <fieldset>
			                	<legend><b>${module.moduleName }</b></legend>
			                	<fieldset>
		                            <legend>	                            	              
		                            	<input type="checkbox" onclick="selectAll(this,${module.id })"/>功能权限
		                            </legend>
		                            ${module.authHtml }
		                     	</fieldset>
		                    </fieldset>
	                	</s:iterator>
		              </div>
		            </div>
	             </div>
	     	    </div>
	           <div class="panel-footer">
	          <div class=" text-center">
				<button type="button" id="submitButton" class="btn btn-primary">提交</button>
				<a class="btn btn-default" href="role_list.shtml">取消</a>
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
			submitHandler: function(form){
				$.ajax({
					url: "role_checkRoleName.shtml?roleName="+$('#roleName').val(),
					type: "post",
					success: function(data, textStatus) {
						// 验证 返回data 
						if(data == "true"){
							alert("该用户身份已存在，请修正后再提交！");
							$('#roleName').focus();
							return false;
						}
						form.submit();
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						
					}
				});
               },
			rules: {
				"role.roleName":{
					required:true,
					maxlength:50
				},
				"role.description": {
					required: true,
					maxlength:100
				}
			}
		});
		
		$("#submitButton").click(function(){
			$("#submitButton").attr("disabled","disabled");
			if($("#inputForm").valid())
			{
				$("#inputForm").submit();
				art.dialog.tips("<font color='red'>正在保存数据，请耐心等候...</font>");
			} else{
				$("#submitButton").removeAttr("disabled");
			}
		});
	});
	
	//全选或不先
	function selectAll(obj,moduleId){
		if(obj.checked){
			select(moduleId);
		}else{
			cancel(moduleId);
		}
	}
	
	//全选
	function select(moduleId){
		  var cb = document.getElementsByTagName("input");
		  for(var i=0; i < cb.length; i++){ 
		 	if(cb[i].lang==moduleId){		
		 		cb[i].checked = true;	  
		 	}
		 }
	}

	//取消选择
	function cancel(moduleId){
		   var cb = document.getElementsByTagName("input");
		  for(var i=0; i < cb.length; i++){ 
		 	if(cb[i].lang==moduleId){		
		 		cb[i].checked = false;	  
		 	}
		 }
	}
</script>
</body>
</html>
