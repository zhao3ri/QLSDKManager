<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
	<div id="wrap">
	<%@ include file="/common/header.jsp" %>
	<div class="container">
	    <ol class="breadcrumb row">
	      <li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
	      <li class="active">渠道管理</li>
	      <li><a href="${ctx}/bChannel/bPlatform_list.shtml">渠道列表</a></li>
	      <s:if test="id==null">
	      <li class="active">新增渠道信息</li>
	      </s:if>
	      <s:else>
	      <li class="active">修改渠道信息</li>
	      </s:else>
	    </ol>
	    <div class="panel panel-default">
	      	<div class="panel-heading ">
	        	<h3 class="panel-title">${id==null?"新增":"修改"}渠道信息</h3>
	      	</div>
	      	<form role="form" id="inputForm" action="bPlatform_save.shtml" method="post">
	        	<div class="panel-body ">
	          		<div class=" tooltip-show form-horizontal">
	    				<input type="hidden" name="BPlatform.id" value="${id}"/>
						
						<div class="form-group clearfix" >
							<label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>渠道名称：</label>
							<div class=" col-sm-9 col-lg-5">
							<input class="form-control" type="text" id="bChannel.channelName" name="BPlatform.channelName"  value="${bChannel.channelName}" placeholder="请输入渠道名称"/>
							<label id="platformNameinfo"></label>
							</div>
						</div>
						
						<div class="form-group clearfix" >
							<label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>商务：</label>
							<div class=" col-sm-9 col-lg-5">
							<input class="form-control" type="text" id="bChannel.business" name="BPlatform.business"  value="${bChannel.business}" placeholder="商务"/>
							<label id="platformNameinfo"></label>
							</div>
						</div>

						<div class="form-group clearfix" >
							<label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>手机号：</label>
							<div class=" col-sm-9 col-lg-5">
							<input class="form-control" type="number" id="bChannel.phone" name="BPlatform.phone"  value="${bChannel.phone}" placeholder="手机号"/>
							<label id="platformNameinfo"></label>
							</div>
						</div>

						<div class="form-group clearfix" >
							<label  class="control-label col-sm-3 col-lg-2 text-right">回调地址：</label>
							<div class=" col-sm-9 col-lg-5">
							<input class="form-control" type="text" id="bChannel.channelCallbackUrl" name="BPlatform.channelCallbackUrl"  value="${bChannel.channelCallbackUrl}" placeholder="请输入回调地址"/>
							<label id="platformNameinfo"></label>
							</div>
						</div>

						<div class="form-group clearfix" >
							<label  class="control-label col-sm-3 col-lg-2 text-right">第三方认证地址：</label>
							<div class=" col-sm-9 col-lg-5">
								<input class="form-control" type="text" id="bChannel.verifyUrl" name="BPlatform.verifyUrl"  value="${bChannel.verifyUrl}" placeholder="请输入渠道认证地址"/>
								<label id="platformNameinfo"></label>
							</div>
						</div>
					
	     			</div>
  	    		</div>
	    		<div class="panel-footer">
	          		<div class=" text-center">
						<button class="btn btn-primary"  id="checkPlatformName"  type="button"><i class="icon-ok"></i> 提交</button>
						<a class="btn btn-default" href="bPlatform_list.shtml"><i class="icon-remove"></i> 取消</a>
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
					"BPlatform.channelName":{required:true,maxlength:20}
				}
			});
			
			
		 $("#checkPlatformName").click(function(){
			var channelName=$("input[name='BChannel.channelName']").val();
			//alert(channelName);
			var url="/bChannel/bPlatform_platformNameIsExisted.shtml";
			var params={"cache":"false","BPlatform.channelName":channelName};
			$("#platformNameinfo").html("");
			if(channelName!="${bChannel.channelName}"){
			$.post(url, params, function(data){
				if(data=="0"){			
					$("#platformNameinfo").html("<font color='green'>该联运渠道名称可以使用</font>");
					$("#inputForm").submit();
				}else if (data=="1") {	
					$("#platformNameinfo").html("<font color='red'>该联运渠道名称已存在</font>");
				}else{
					$("#platformNameinfo").html("<font color='red'>服务器错误</font>");
					
				}
			});
			}else{
				$("#inputForm").submit();
			}
		}); 
		 
		});
	</script>
</body>
</html>