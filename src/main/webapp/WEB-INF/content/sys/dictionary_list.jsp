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
      <li class="active">数据字典</li>
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
    
    <div class="tab-content">
	  	<div class="tab-pane active" id="tab_c01">
		<form role="form" id="mainForm" action="dictionary_list.shtml" method="post">
			<input type="hidden" name="dictionary.dtype" value="${dictionary.dtype }"/>
			
			<div class="panel panel-default">
		      <div class="form-inline popover-show panel-body list_toolbar">
		        <div class="form-group    width_input"  data-toggle="popover"  data-placement="top" data-content="请输入<s:property value="dTypeMap.get(dictionary.dtype)"/>名称">
		          <input  class="form-control" type="text"  placeholder="<s:property value="dTypeMap.get(dictionary.dtype)"/>名称" name="dictionary.dname" value="${dictionary.dname}"/>
		        </div>
		        <div class="form-group    width_input"  data-toggle="popover"  data-placement="top" data-content="请输入<s:property value="dTypeMap.get(dictionary.dtype)"/>描述">
		          <input  class="form-control" type="text"  placeholder="<s:property value="dTypeMap.get(dictionary.dtype)"/>描述" name="dictionary.depict" value="${dictionary.depict}"/>
		        </div>
		        <div class="form-group    width_input"  data-toggle="popover"  data-placement="top" data-content="请选择状态">
		          <mt:selectState name="dictionary.state" showType="select" stateType="operState" value="${dictionary.state}" clazz="form-control" emptyString="--请选择状态--"/>
		        </div>
		        <div class="form-group width_btn">
		          <button  type="button" class="btn  btn-primary " onclick="search();"><i class="icon-search"></i> 搜索</button>
		          <button  type="button" class="btn  btn-default " onclick="resetSearch();"><i class="icon-refresh"></i> 重置</button>
		        </div>
		        <div class="form-group width_btn pull-right">
		          <button type="button" class="btn btn-success " id="newitem"><i class="icon-plus"></i> 添加<s:property value="dTypeMap.get(dictionary.dtype)"/></button>
		        </div>
		    </div>
			
			<div class="panel-body ">
		      <div class="table-responsive">
		        <table class="table table-hover table-striped table-bordered table-condensed">
		          <thead>
		            <tr>
		              <th >选择</th>
		              <th >序号</th>
		              <th><s:property value="dTypeMap.get(dictionary.dtype)"/>名称</th>
							<s:if test="dictionary.dtype=='group'">
								<th>所属部门</th>
							</s:if>
							<s:else>
								<th><s:property value="dTypeMap.get(dictionary.dtype)"/>键值</th>
							</s:else>
						<th><s:property value="dTypeMap.get(dictionary.dtype)"/>描述</th>
						<th>状态</th>
						<th>排序</th>
						<th>创建时间</th>
						<th>操作</th>
		            </tr> 
		          </thead>
		          <tbody>
		          	<s:if test="dictionaryPage.result.size>0">
					<s:iterator value="dictionaryPage.result" var="page">
		            <tr>
		              <td><input name="checkedIds" class="checkedIds" value="<s:property value="id"/>" type="checkbox"/></td>
						<td><s:property value="id"/></td>
						<td title="<s:property value="dname"/>">
							<s:if test="dname.length()>15"><pre><s:property value="dname.substring(0,15)+'...'"/></pre></s:if>
							<s:else><pre><s:property value="dname"/></pre></s:else>
						</td>
						<s:if test="dictionary.dtype!='advertisers'">
							<td title="<s:property value="dvalue"/>">
								<s:if test="dvalue.length()>15"><pre><s:property value="dvalue.substring(0,15)+'...'"/></pre></s:if>
								<s:else>
									<s:if test="dictionary.dtype=='group'">
										<mt:selectDic showType="label" dicID="${page.dvalue}"/>
									</s:if>
									<s:else>
										<pre><s:property value="dvalue"/></pre>
									</s:else>
								</s:else>
							</td>
						</s:if>
						<td title="<s:property value="depict"/>">
							<s:if test="depict.length()>15"><pre><s:property value="depict.substring(0,15)+'...'"/></pre></s:if>
							<s:else><pre><s:property value="depict"/></pre></s:else>
						</td>
						<td>
							<mt:selectState showType="label" value="${state}" stateType="operState"/>
						</td>
						<td><s:property value="dsort"/></td>
						<td><s:date name="inserttime" format="yyyy-MM-dd HH:mm:ss"/></td>
		              <td><div class="btn-group btn-group-sm pull-right">
		                  <button type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown"> 操作 <span class="caret"></span> </button>
		                  <ul class="dropdown-menu" role="menu">
		                    <li><a href="dictionary_handle.shtml?id=<s:property value="id"/>">修改</a></li>
		                    <li><a href="javascript:confirmAction('dictionary_updateState.shtml?checkedIds=<s:property value="id"/>&flag=0&dictionary.dtype=${page.dtype }','您确认删除？');">删除</a></li>
		                    <li role="presentation" class="divider"></li>
		                    <li><a href="javascript:confirmAction('dictionary_updateState.shtml?checkedIds=<s:property value="id"/>&flag=1&dictionary.dtype=${page.dtype }','您确认启用？');">启用</a></li>
		                    <li><a href="javascript:confirmAction('dictionary_updateState.shtml?checkedIds=<s:property value="id"/>&flag=2&dictionary.dtype=${page.dtype }','您确认锁定？');">锁定</a></li>
		                    <li role="presentation" class="divider"></li>
											<li><mt:modalDialog remote="/history/history_list.shtml?history.rid=${page.id}&history.omkey=dictionary&t=${r}" id="historyWindow${page.id}" title="历史记录" role="dialog" name="历史记录" type="link" width="700"/></li>
		                  </ul>
		                </div></td>
		            </tr>
		            	</s:iterator>
					</s:if>
					<s:else>
						<tr align="center">
							<td align="center" colspan="9">当前页没有记录！</td>
						</tr>
					</s:else>
		          </tbody>
		        </table>
		      </div>
		       <s:if test="dictionaryPage.result.size>0">
	      		<div class="table_page dropup">
		        <div class="btn-group  dropup btn-group-sm pull-left">
		          <label  class="btn btn-default  table_select_all"  >&nbsp;
		            <input type="checkbox" id="checkedAll">
		          </label>
		          <button type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown"><span class="caret"></span> </button>
		          <ul class="dropdown-menu text-left" role="menu">
		            <li><a id="remove" href="#">批量删除</a></li>
		            <li role="presentation" class="divider"></li>
		            <li><a id="across" href="#">批量启用</a></li>
		            <li><a id="unacross" href="#">批量锁定</a></li>
		          </ul>
		        </div>
		        <c:set var="p" value="dictionaryPage"/>
		        <%@ include file="/common/pagination.jsp" %>
			    </div>
			    </s:if>
		  </div>
		  </div>
		</form>
		</div>
	</div>
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
					checkedIds[i] = this.value;i++;
				}
			});
			if(checkedIds.length==0){
				alert("请选择要删除的数据");
			}else{
				var confirm = window.confirm("确定删除所选记录？");
				if(confirm) {
					location.assign("dictionary_updateState.shtml?checkedIds=" + checkedIds + "&flag=0&dictionary.dtype=${dictionary.dtype }");
				}
			}
		});
		
		$("#across").click(function() {
			var checkedIds = new Array();
			var i = 0;
			$(".checkedIds").each(function() {
				if(this.checked == true) {
					checkedIds[i] = this.value;i++;
				}
			});
			if(checkedIds.length==0){
				alert("请选择要启用的数据");
			}else{
				var confirm = window.confirm("确定启用所选记录？");
				if(confirm) {
					location.assign("dictionary_updateState.shtml?checkedIds=" + checkedIds + "&flag=1&dictionary.dtype=${dictionary.dtype }");
				}
			}
		});
		
		$("#unacross").click(function() {
			var checkedIds = new Array();
			var i = 0;
			$(".checkedIds").each(function() {
				if(this.checked == true) {
					checkedIds[i] = this.value;i++;
				}
			});
			if(checkedIds.length==0){
				alert("请选择要禁止的数据");
			}else{
				var confirm = window.confirm("确定禁止所选记录？");
				if(confirm) {
					location.assign("dictionary_updateState.shtml?checkedIds=" + checkedIds + "&flag=2&dictionary.dtype=${dictionary.dtype }");
				}
			}
		});
		$("#newitem").click(function() {
			location.assign("dictionary_handle.shtml?dictionary.dtype=${dictionary.dtype }");
		});
	});
	function resetSearch(){
       location.assign("dictionary_list.shtml?dictionary.dtype=${dictionary.dtype }");
    }
</script>
</body>
</html>
