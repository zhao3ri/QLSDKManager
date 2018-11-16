<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
	<div id="wrap">
	<%@ include file="/common/header.jsp" %>
	<div class="container">
    <ol class="breadcrumb row">
      <li><i class="icon-home"></i> <a href="#">首页</a></li>
      <li class="active">系统管理</li>
      <li class="active">清除缓存</li>
    </ol>
	<div class="panel panel-default">
      <div class="panel-heading ">清除用户权限缓存 </div>
	      <div class="form-inline popover-show panel-body list_toolbar">
	      	<div class="form-group width_btn">
	          <button  type="button" class="btn  btn-primary " id="cleanAuth"><i class="icon-ok"></i> 确认清除</button>
	        </div>
	        <div class="form-group   "  data-toggle="popover"  data-placement="top" data-content="清除用户权限缓存">
	          	点击'确认清除'按钮即可清除用户权限缓存！
	        </div>
    	</div>
    </div>
    
    <!-- <div class="panel panel-default">
      <div class="panel-heading ">清除应用渠道缓存 </div>
	      <div class="form-inline popover-show panel-body list_toolbar">
	      	<div class="form-group width_btn">
	          <button  type="button" class="btn  btn-primary " id="cleanAppChannelAuth"><i class="icon-ok"></i> 确认清除</button>
	        </div>
	        <div class="form-group   "  data-toggle="popover"  data-placement="top" data-content="清除用户权限缓存">
	          	点击'确认清除'按钮即可清除清除应用渠道缓存！
	        </div>
    	</div>
    </div>	 -->
    		
	<form identity="form" id="mainForm" action="systool_doCleanCache.shtml" method="post">
		
		
		<div class="panel panel-default">
	      <div class="panel-heading ">清除数据库缓存 </div>
	      <div class="form-inline popover-show panel-body list_toolbar">
	    </div>
	    
		<div class="panel-body ">
	      <div class="table-responsive">
			<table class="table table-hover table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>选择</th>
						<th>缓存模块名</th>
						<th>缓存ID</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="cacheIDMap.size>0">
					<s:iterator value="cacheIDMap">
						<tr>
							<th><input name="checkedIds" class="checkedIds" value="${key }.${value }" type="checkbox"/></th>
							<td>${key }</td>
							<td>${value }</td>
						</tr>
					</s:iterator>
					</s:if>
					<s:else>
						<tr align="center">
							<td align="center" colspan="8">当前页没有记录！</td>
						</tr>
					</s:else>
				</tbody>
			</table>
		  </div>
	     	<div class="table_page dropup">
		        <div class="btn-group  dropup btn-group-sm pull-left">
		          <label  class="btn btn-default  table_select_all"  >&nbsp;
		            <input type="checkbox" id="checkedAll">
		          </label>
		          <button type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown"><span class="caret"></span> </button>
		          <ul class="dropdown-menu text-left" identity="menu">
		            <li><a id="clean" href="#">批量清除</a></li>
		          </ul>
		        </div>
		    </div>
	    </div>
	</form>
</div>
</div>
<%@ include file="/common/footer.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {
		$("#checkedAll").click(function(){
			$(".checkedIds").each(function() {
				if($("#checkedAll").prop("checked")==true){
					this.checked = true;
				}else{
					this.checked = false;
				}
			});
		});
		
		
		$("#cleanAuth").click(function() {
			var confirm = window.confirm("确定清除用户权限缓存？");
			if(confirm) {
				location.assign("systool_doCleanAuthCache.shtml");
			}
		});
		
		$("#cleanAppChannelAuth").click(function() {
			var confirm = window.confirm("确定清除应用渠道缓存？");
			if(confirm) {
				location.assign("systool_cleanAppChannelCache.shtml");
			}
		});
		
		
		$("#clean").click(function() {
			var checkedIds = new Array();
			var i = 0;
			$(".checkedIds").each(function() {
				if(this.checked == true) {
					checkedIds[i] = this.value;i++;
				}
			});
			if(checkedIds.length==0){
				alert("请选择要清除的数据");
			}else{
				var confirm = window.confirm("确定清除所选记录？");
				if(confirm) {
					location.assign("systool_doCleanCache.shtml?checkedIds=" + checkedIds);
				}
			}
		});
	});
</script>
</body>
</html>
