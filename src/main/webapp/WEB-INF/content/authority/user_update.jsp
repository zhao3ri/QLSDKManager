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
	      <li><a href="${ctx}/user/user_list.shtml">管理员列表</a></li>
	      <li class="active">修改用户</li>
	    </ol>
	    <div class="panel panel-default">
	      <div class="panel-heading ">
	        <h3 class="panel-title">修改用户</h3>
	      </div>
		<form role="form" id="inputForm" action="user_save.shtml" method="post">
			<input type="hidden" name="user.id" value="${user.id }" />
			
			<div class="panel-body ">
	          <div class=" tooltip-show form-horizontal">
	          	<div class="form-group clearfix col-lg-6" >
	              <label  class="control-label col-sm-3 text-right"><b class="color_red">*</b>帐号：</label>
	              <div class=" col-sm-9 ">
	                <p class="form-control-static"><s:property value="user.userName"/></p>
	              </div>
              	</div>
              	<div class="form-group clearfix col-lg-6" >
	              <label  class="control-label col-sm-3 text-right"><b class="color_red">*</b>真实姓名：</label>
	              <div class=" col-sm-9 ">
	                <input  class="form-control" type="text" id="realName" name="user.realName" maxlength="20" value="<s:property value="user.realName"/>"  placeholder="请输入真实姓名,如：张三" />
	              </div>
	              <span class=" help-block col-sm-9 col-sm-offset-3">请输入真实姓名,如：张三</span>
              	</div>
              	<div class="form-group clearfix col-lg-6" >
	              <label  class="control-label col-sm-3 text-right">密码：</label>
	              <div class=" col-sm-9 ">
	                <input  class="form-control" type="password" id="password" name="user.password" maxlength="20" value=""  placeholder="需要修改密码请填写"/>
	              </div>
              	</div>
              	<div class="form-group clearfix col-lg-6" >
	              <label  class="control-label col-sm-3 text-right">确认密码：</label>
	              <div class=" col-sm-9 ">
	                <input  class="form-control" type="password" id="password2" name="password2" maxlength="20" value=""  placeholder="输入跟密码一致"/>
	              </div>
              	</div>
              	<div class="form-group clearfix col-lg-6" >
	              <label  class="control-label col-sm-3 text-right"><b class="color_red">*</b>部门：</label>
	              <div class=" col-sm-9 ">
	                <select id="did" name="user.did" onchange="changDid()" class="form-control" size="1">
			        	<option value="" >--请选择部门--</option>
			        	<s:iterator value="departmentList" var="d">
							<option value="<s:property value="id"/>" <s:if test="id==user.did" >selected</s:if>><s:property value="dname"/></option>
						</s:iterator>
			       	</select>
	              </div>
              	</div>
              	<%-- <div class="form-group clearfix col-lg-6" >
	              <label  class="control-label col-sm-3 text-right">分组：</label>
	              <div class=" col-sm-9 ">
	                <select id="gid" name="user.gid" class="form-control" size="1">
			        	<option value="" >--请选择分组--</option>
			        	<s:iterator value="groupList" var="g">
							<option value="<s:property value="id"/>" <s:if test="id==user.gid" >selected</s:if> ><s:property value="dname"/></option>
						</s:iterator>
			       	</select>
	              </div>
              	</div> --%>
              	<div class="form-group clearfix col-lg-6" >
	              <label  class="control-label col-sm-3 text-right"><b class="color_red">*</b>身份：</label>
	              <div class=" col-sm-9 ">
	                <select name="user.identityId"  class="form-control" size="1">
						<option value="">--请选择身份--</option>
						<s:iterator value="identityList" var="identity">
							<option value="${identity.id }" <s:if test="user.identityId==#identity.id">selected</s:if>>${identity.name }</option>
						</s:iterator>
					</select>
	              </div>
              	</div>
              	<%-- <div class="form-group clearfix col-lg-6" >
	              <label  class="control-label col-sm-3 text-right"><b class="color_red">*</b>状态：</label>
	              <div class=" col-sm-9 ">
	              	<p class="form-control-static"><mt:selectState showType="label" value="${user.state}" stateType="userState"/></p>
	              </div>
              	</div> --%>
              </div>
      	    </div>
            <div class="panel-footer">
	          <div class=" text-center">
				<button type="button" id="submitButton" class="btn btn-primary">提交</button>
				<a class="btn btn-default" href="user_list.shtml">取消</a>
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
				"password":{
					required:false,
					maxlength:32
				},
				"password2": {
					equalTo: "#password"
				},
				"user.realName": {
					required: true,
					maxlength:20
				},
				"user.did":{
					required: true,
					selectNone:true
				},
				"user.identityId":{
					required: true,
					selectNone:true
				}
			},
			messages: {
				"password2": {
					equalTo: "两次输入的密码不一致"
				},
				"user.identityId":"请选择身份",
				"user.did":"请选择部门"
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
	
	 //自定义验证规则——增加对select的验证    
       $.validator.addMethod(        
           "selectNone",                             // 规则名称    
            //验证规则  
           function(value, element){       
           	// select默认值需要设置为"0"             
              if (value == ""){        
                 return false;        
              }
              return true;   
           },        
           "必须选择一项" // 默认验证消息 
       ); 
       
	// 异步读取
       function changDid(){
		var sqlWhere="?id="+$("#did").val();
		$.ajax({
   	 		type: "POST",
   	 		url:"${ctx}/user/user_getGroupAjax.shtml"+sqlWhere,
   	 		success:function(jsonStr){
   	 			$("#gid").empty();  //将option元素从select元素中删除
   	 			var option = document.createElement("option");	
   	 			$(option).attr({"value":""});
   	 			$(option).html("--请选择部门--");
  	 				$("#gid").append(option);	
	    	 	var json = eval("(" + jsonStr + ")");	//获得服务器返回(JSON数据格式)
	    	 	$.each(json,function(i){				
		    	 	var option = document.createElement("option");	//创建一个option DOM元素
		    	 	//循环JSON对象列表,并设置option的value和text
    	 			$(option).attr({"value":this.value});
    	 			$(option).html(this.text);
   	 				$("#gid").append(option);	//将option元素添加到select元素中
   	 			});
   	 		},
   	 		error:function(){	
   	 			//alert("系统提示:获取失败!");
   	 		}
	   });
	}
</script>
</body>
</html>
