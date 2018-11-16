<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>

<script type="text/javascript" src="${ctx}/scripts/ajaxTable.js"></script>	

<body class="fix_top_nav_padding">
	<div id="wrap">
	<form identity="form" id="mainForm" action="javascript:clickButton();" method="post">
		<input type="hidden" id="rid" name="history.rid" value="${history.rid }"/>
		<input type="hidden" id="omkey" name="history.omkey" value="${history.omkey }"/>
		<input type="hidden" id="uniteOmkey" name="history.uniteOmkey" value="${history.uniteOmkey }"/>
		<input type="hidden" id="uniteRid" name="history.uniteRid" value="${history.uniteRid }"/>
	
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
	      <s:if test="historyPage.result.size>0">
	      <div class="table_page dropup">
	        <c:set var="p" value="historyPage"/>
	        <%@ include file="/common/ajaxPagination.jsp" %>
	      </div>
	      </s:if>
	</form>
	</div>
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
		//首次执行查询，初始化对象属性
		ajaxTable  = new AjaxTable(url,"historyWindow${history.rid } .modal-body");//tabs_box_ajax_div
		ajaxTable.pageNo = pageNo;
		ajaxTable.orderBy = '';
		ajaxTable.order = '';
		ajaxTable.pageObjName = "historyPage";
		//ajaxTable.search();
	}

</script>	
</body>
</html>
