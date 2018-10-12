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
	      <li><a href="${ctx}/dictionary/dictionary_list.shtml">数据字典列表</a></li>
	      <s:if test="id==null">
				<li class="active">新增操作</li>
			</s:if>
			<s:else>
				<li class="active">修改操作</li>
			</s:else>
	    </ol>
	    
	    <ul class="nav nav-tabs2" id="myTab">
		  <s:iterator value="dTypeMap">
				<s:if test="dictionary.dtype==key">
					<li class="active"><a href="dictionary_list.shtml?dictionary.dtype=${key }">${value }</a></li>
				</s:if>
				<s:else>
					<li><a href="dictionary_list.shtml?dictionary.dtype=${key }">${value }</a></li>
				</s:else>
			</s:iterator> 
		</ul>
	    <div class="panel panel-default">
	      <div class="panel-heading ">
	        <s:if test="id==null">
				<h3 class="panel-title">新增操作</h3>
			</s:if>
			<s:else>
				<h3 class="panel-title">修改操作</h3>
			</s:else>
	      </div>
	      
			<form id="inputForm" action="dictionary_save.shtml" method="post">
				<input type="hidden" name="dictionary.id" value="${id }"/>
				<input type="hidden" name="dictionary.dtype" value="${dictionary.dtype }"/>
				<div class="panel-body ">
		          <div class=" tooltip-show form-horizontal">
		          	<div class="form-group clearfix" >
		              <label  class="control-label col-sm-3  col-lg-2 text-right"><b class="color_red">*</b><s:property value="dTypeMap.get(dictionary.dtype)"/>名称：</label>
		              <div class=" col-sm-9 col-lg-5">
		                <input  class="form-control" type="text" id="dictionary.dname" name="dictionary.dname" maxlength="20" value="<s:property value="dictionary.dname"/>"  placeholder="请输入<s:property value="dTypeMap.get(dictionary.dtype)"/>名称"/>
		              </div>
		            </div>
		            <!-- no advertisers -->
					<s:if test="dictionary.dtype!='advertisers'">
						<div class="form-group clearfix" >
			              <label  class="control-label col-sm-3  col-lg-2 text-right"><b class="color_red">*</b><s:property value="dTypeMap.get(dictionary.dtype)"/>键值：</label>
			              	<s:if test="dictionary.dtype=='group'">
				              <div class=" col-sm-9 col-lg-5">
				                <mt:selectDic name="dictionary.dvalue" dtype="department" value="${dictionary.dvalue}"/>
				              </div>
				            </s:if>
							<s:else>
				              <div class=" col-sm-9 col-lg-5">
				                <input  class="form-control" type="text" id="dictionary.dvalue" name="dictionary.dvalue" maxlength="20" value="<s:property value="dictionary.dvalue"/>"  placeholder="请输入<s:property value="dTypeMap.get(dictionary.dtype)"/>键值"/>
				              </div>
			              	</s:else>
			            </div>
					</s:if>
		          	<div class="form-group clearfix" >
			          	<s:if test="id==null">
			              <label  class="control-label col-sm-3  col-lg-2 text-right"><b class="color_red">*</b>状态：</label>
			              <div class=" col-sm-9 col-lg-5">
			                <mt:selectState name="dictionary.state" showType="select" stateType="operState" value="1" clazz="form-control"/>
			              </div>
			            </s:if>
						<s:else>  
			              <label  class="control-label col-sm-3  col-lg-2 text-right">状态：</label>
			              <div class=" col-sm-9 col-lg-5">
			              	<p class="form-control-static"><mt:selectState showType="label" value="${dictionary.state}" stateType="operState"/></p>
			              </div>
			            </s:else>
		            </div>
		            <div class="form-group clearfix" >
		              <label  class="control-label col-sm-3  col-lg-2 text-right"><b class="color_red">*</b>排序：</label>
		              <div class=" col-sm-9 col-lg-5">
		                <input  class="form-control" type="text" name="dictionary.dsort" maxlength="5" value="<s:if test='dictionary.dsort==null'>5</s:if><s:else>${dictionary.dsort}</s:else>" id="dictionary.dsort"  placeholder="请输入排序"/>
		              </div>
		              <span class=" help-block col-sm-9 col-sm-offset-3  col-lg-5 col-lg-offset-0">请输入排序</span>
		            </div>
		            <div class="form-group clearfix" >
		              <label  class="control-label col-sm-3 col-lg-2 text-right">描述：</label>
		              <div class=" col-sm-9  col-lg-5">
		                <textarea class="form-control" name="dictionary.depict" id="dictionary.depict" rows="4"><s:property value="dictionary.depict.replace('<br/>','\r\n')" escape="false"/></textarea>
		              </div>
		              <span class=" help-block col-sm-9 col-sm-offset-3  col-lg-5 col-lg-offset-0">请输入描述</span> 
		            </div>
	             </div>
	     	    </div>
	           <div class="panel-footer">
	          <div class=" text-center">
				<button type="button" id="submitButton" class="btn btn-primary">提交</button>
				<a class="btn btn-default" href="dictionary_list.shtml?dictionary.dtype=${dictionary.dtype }">取消</a>
	          </div>
	           </div>
			</form>
	  	</div>
	  </div>
	</div>	
<%@ include file="/common/footer.jsp" %>
<script type="text/javascript" src="${ctx}/scripts/jqueryValidate.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		appendValidate();
	
		<s:if test="dictionary.dtype=='advertisers'">
			// 广告主
			$("#inputForm").validate({
				rules: {
					"dictionary.dname":{maxlength:50,required:true},
					"dictionary.depict":{maxlength:100},
					"dictionary.state":{digits:true,maxlength:7,required:true},
					"dictionary.dsort":{digits:true,maxlength:7,required:true}
				}
			});
		</s:if>
		<s:elseif test="dictionary.dtype=='accountPeriod' || dictionary.dtype=='roleLevel'">
			// 账期
			$("#inputForm").validate({
				rules: {
					"dictionary.dname":{maxlength:50,required:true},
					"dictionary.depict":{maxlength:100},
					"dictionary.dvalue":{digits:true,maxlength:7,required:true},
					"dictionary.state":{digits:true,maxlength:7,required:true},
					"dictionary.dsort":{digits:true,maxlength:7,required:true}
				}
			});
		</s:elseif>
		<s:else>
			// 部门、分组、计费
			$("#inputForm").validate({
				rules: {
					"dictionary.dname":{maxlength:50,required:true},
					"dictionary.depict":{maxlength:100},
					"dictionary.dvalue":{nochinese:true,maxlength:30,required:true},
					"dictionary.state":{digits:true,maxlength:7,required:true},
					"dictionary.dsort":{digits:true,maxlength:7,required:true}
				}
			});
		</s:else>
		
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
	
</script>
</body>
</html>