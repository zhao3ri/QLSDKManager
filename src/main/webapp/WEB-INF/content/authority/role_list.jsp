<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>

<%
java.util.Random C=new java.util.Random();
int r = C.nextInt();
pageContext.setAttribute("r", r);
%>

<body class="fix_top_nav_padding">
	<div id="wrap">
	<%@ include file="/common/header.jsp" %>
	<div class="container">
    <ol class="breadcrumb row">
      <li><i class="icon-home"></i> <a href="#">首页</a></li>
      <li class="active">系统管理</li>
      <li class="active">角色列表</li>
    </ol>
	<form id="mainForm" action="role_list.shtml"  method="post" id="mainForm">
	    <div class="panel panel-default">
	      <div class="panel-heading ">角色列表信息查询 </div>
	      <div class="form-inline popover-show panel-body list_toolbar">
	        <div class="form-group    width_input"  data-toggle="popover"  data-placement="top" data-content="请输入角色名称">
	          <input  class="form-control" type="text"  placeholder="角色名称" name="searchRole.roleName" value="${searchRole.roleName}"/>
	        </div>
	        <div class="form-group width_btn">
	          <button  type="button" class="btn  btn-primary " onclick="search();"><i class="icon-search"></i> 搜索</button>
	       	  <button  type="button" class="btn  btn-default " onclick="resetSearch();"><i class="icon-eraser"></i> 重置</button>	
	        </div>
	        <div class="form-group width_btn  pull-right">
	          <button type="button" class="btn btn-success " id="newitem"><i class="icon-plus"></i> 添加</button>
	        </div>
	    </div>
		<div class="panel-body ">
	      <div class="table-responsive">
	        <table class="table table-hover table-striped table-bordered table-condensed table-big">
	          <thead>
	            <tr>
	            	<th>选择</th>
	              	<th>序号</th>
					<th>角色名称</th>
					<th>角色描述</th>
					<th>应用权限</th>
					<th>操作</th>
	            </tr>
	          </thead>
	          <tbody>
	          	<s:if test="page.result.size>0">
					<s:iterator value="page.result" var="tempRole">
			            <tr>
			              	<td><label><input type="checkbox" name="checkedIds" class="checkedIds" value="${tempRole.id}"></label></td>
			              	<td>${tempRole.id}</td>
							<td><s:property value="roleName"/></td>
							<td><s:if test="%{description.length()>20}"><pre><s:property value="description.substring(0,20)+'...'"/></pre></s:if><s:else><pre><s:property value="description"/></pre></s:else></td>
							<td>
								<s:iterator value="authGames" var="game">
									${game.appName }，
								</s:iterator>
							</td>
							<td>
								<div class="btn-group btn-group-sm pull-right">
				                  <button type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown"> 操作 <span class="caret"></span> </button>
				                  <ul class="dropdown-menu" role="menu">
				                    <li><a href="role_update.shtml?role.id=${tempRole.id }">修改</a></li>
				                    <li><a href="javascript:confirmAction('role_delete.shtml?checkedIds=${tempRole.id }','您确认删除？');">删除</a></li>
				                    <li role="presentation" class="divider"></li>
									<li><mt:modalDialog remote="/history/history_list.shtml?history.rid=${tempRole.id}&history.omkey=role&t=${r}" id="historyWindow${tempRole.id}" title="历史记录" role="dialog" name="历史记录" type="link" width="700"/></li>
				                  </ul>
				                </div>
							</td>
			            </tr>
	            	</s:iterator>
				</s:if>
				<s:else>
					<tr align="center">
						<td align="center" colspan="6">当前页没有记录！</td>
					</tr>
				</s:else>
	          </tbody>
	        </table>
	      </div>
	      <s:if test="page.result.size>0">
      		<div class="table_page dropup">
	        <div class="btn-group  dropup btn-group-sm pull-left">
	          <label  class="btn btn-default  table_select_all"  >&nbsp;
	            <input type="checkbox" id="checkedAll">
	          </label>
	          <button type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown"><span class="caret"></span> </button>
	          <ul class="dropdown-menu text-left" role="menu">
	            <li><a id="remove" href="#">批量删除</a></li>
	          </ul>
	        </div>
	        <%@ include file="/common/pagination.jsp" %>
	    </div>
	    </s:if>
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
		
		$("#remove").click(function() {
			var checkedIds = new Array();
			var i = 0;
			$(".checkedIds").each(function() {
				if(this.checked == true) {
					checkedIds[i] = this.value;
					i++;
				}
			});
			if(i==0){
				alert("请选择要删除的数据！！");
			}else{
				if(confirm("删除角色将同时删除该角色下的所有用户，您确认删除？")){
					location.assign("role_delete.shtml?checkedIds=" + checkedIds);
				}
			}
		});
		$("#newitem").click(function() {
			location.assign("role_create.shtml");
		});
	});
	function resetSearch(){
       location.assign("role_list.shtml?mid=${mid}");
    }
</script>	

</body>
</html>
