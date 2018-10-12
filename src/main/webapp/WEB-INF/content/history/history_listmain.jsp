<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>

<script type="text/javascript" src="${ctx}/scripts/ajaxTable.js"></script>	

<body class="fix_top_nav_padding">
	        <table class="table table-hover table-striped table-bordered  table-big">
	          <thead>
	            <tr>
					<th>用户</th>
					<th>动作</th>
					<th>字段</th>
					<th>原值</th>
					<th>新值</th>
					<th>记录时间</th>
	            </tr>
	          </thead>
	          <tbody>
	          	<s:if test="historyPage.result.size>0">
					<s:iterator value="historyPage.result" var="page">
		            <tr>
		              <td>${page.user.realName}</td>
		              <td >${page.oaction }</td>
					  <td >${page.opname }</td>
					  <td><s:if test="%{ovalue.length()>20}"><pre><s:property value="ovalue.substring(0,20)+'...'"/></pre></s:if><s:else><pre><s:property value="ovalue"/></pre></s:else></td>
					  <td><s:if test="%{pvalue.length()>20}"><pre><s:property value="pvalue.substring(0,20)+'...'"/></pre></s:if><s:else><pre><s:property value="pvalue"/></pre></s:else></td>
					  <td ><s:date name="inserttime" format="yyyy-MM-dd HH:mm:ss"/></td>
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
<script type="text/javascript">
	//ajaxtable
	var ajaxTable = null;
	
	$(document).ready(function() {
		//加载ajax列表分页
		searchHistory();
	});
	
	function clickButton(){
		$("#ajaxButton").click();
	}
	
	function searchHistory() {
		var pageNo = 1;
		
		var url = "/history/history_list.shtml";
		var params = getParams();
		//首次执行查询，初始化对象属性
		ajaxTable  = new AjaxTable(url,params,"historyWindow${history.rid } .modal-body");//tabs_box_ajax_div
		ajaxTable.pageNo = pageNo;
		ajaxTable.orderBy = '';
		ajaxTable.order = '';
		ajaxTable.pageObjName = "historyPage";
		//ajaxTable.search();
	}

	function getParams(){
		var params ={
			"history.rid":$("#rid").val(),
			"history.omkey":$("#omkey").val(),
			"history.uniteRid":$("#uniteRid").val(),
			"history.uniteOmkey":$("#uniteOmkey").val()
		}
		return params;
	}
</script>	
</body>
</html>
