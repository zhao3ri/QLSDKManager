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
	      <li><a href="${ctx}/bPlatformApp/bPlatformApp_list.shtml">渠道关联列表</a></li>
	      <s:if test="id==null">
	      <li class="active">新增渠道关联信息</li>
	      </s:if>
	      <s:else>
	      <li class="active">修改渠道关联信息</li>
	      </s:else>
	    </ol>
	    <div class="panel panel-default">
	      	<div class="panel-heading ">
	        	<h3 class="panel-title">${id==null?"新增":"修改"}渠道关联信息</h3>
	      	</div>
	      	<form role="form" id="inputForm" action="bPlatformApp_save.shtml" method="post">
	        	<div class="panel-body ">
	          		<div class=" tooltip-show form-horizontal">
	    				<input type="hidden" name="BPlatformApp.id" value="${id}"/>
	    				
						<div class="form-group clearfix" >
							<label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>渠道：</label>
							<div class=" col-sm-9 col-lg-5">
								<c:if test="${id == null }">
									<select class="form-control" name="BPlatformApp.platformId"  data-original-title="" title="">
										<option value="">请选择渠道</option>
										<s:iterator value="bPlatforms" var="item">
											<option value="${item.id}" <c:if test="${item.id==bPlatformApp.platformId}">selected</c:if>>${item.platformName }</option>
										</s:iterator>								
									</select>
								</c:if>
								<c:if test="${id != null }">
									<input type="hidden" name="BPlatformApp.platformId" value="${bPlatformApp.platformId}"/>
									${bPlatformApp.platformName }
								</c:if>
							</div>
						</div>
					
					<div class="form-group clearfix" >
							<label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>游戏：</label>
							<div class=" col-sm-9 col-lg-5">
								<c:if test="${id == null }">
									<select class="form-control" name="BPlatformApp.appId"  data-original-title="" title="">
										<option value="">请选择游戏</option>
										<s:iterator value="games" var="item">
											<option value="${item.id}" <c:if test="${item.id==bPlatformApp.appId}">selected</c:if>>${item.appName }</option>
										</s:iterator>									
									</select>
								</c:if>
								<c:if test="${id != null }">
									<input type="hidden" name="BPlatformApp.appId" value="${bPlatformApp.appId}"/>
									${bPlatformApp.appName }
								</c:if>
							</div>
						</div>

						<div class="form-group clearfix" >
                        							<label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>是否停充值：</label>
                        							<div class=" col-sm-9 col-lg-5">
                        							<mt:selectState name="BPlatformApp.status" showType="select" stateType="yesNo" value="${BPlatformApp.status}" clazz="form-control" emptyString="--是否停充值--"/>
                        							</div>
                        						</div>
                        <div class="form-group clearfix" >
                                          <label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>是否停新增：</label>
                                                                <div class=" col-sm-9 col-lg-5">
                                                           <mt:selectState name="BPlatformApp.registStatus" showType="select" stateType="yesNo" value="${BPlatformApp.registStatus}" clazz="form-control" emptyString="--是否停新增--"/>
                                                                       </div>
                                                                      </div>

						<div class="form-group clearfix" >
							<label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>分成比例：</label>
							<div class=" col-sm-9 col-lg-5">
								<input class="form-control"  name="BPlatformApp.discount" id="bPlatformApp.discount" value="${bPlatformApp.discount}" placeholder="给渠道的百分比如3折为 30"></input>
							</div>
						</div>

						<div class="form-group clearfix" >
							<label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>配置参数：</label>
							<div class=" col-sm-9 col-lg-5">
								<textarea class="form-control"  name="BPlatformApp.configParams" id="bPlatformApp.configParams">${bPlatformApp.configParams }</textarea>
							</div>
						</div>
	     			</div>
  	    		</div>
	    		<div class="panel-footer">
	          		<div class=" text-center">
						<button type="submit" class="btn btn-primary"><i class="icon-ok"></i> 提交</button>
						<a class="btn btn-default" href="bPlatformApp_list.shtml"><i class="icon-remove"></i> 取消</a>
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
					"BPlatformApp.configParams":{required:true,maxlength:3072},
					"BPlatformApp.platformId":{required:true},
					"BPlatformApp.appId":{required:true}
				}
			});
		});
	</script>
</body>
</html>