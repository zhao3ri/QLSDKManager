<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
	<input type="hidden" id="appId" value="${appId }">

	<div class="panel-body ">
		<button type="button" id="changeZone" class="btn btn-primary " > &nbsp;&nbsp;区&nbsp;服&nbsp;&nbsp;</button>
        <label style="float: right">全 选：<input type="checkbox" id="checkedAll"/></label>  
		 
		<br/>
		<br/>
		<div class="table-responsive" style="min-height: 200px">
				   		
	 		<table class="table table-hover table-striped table-bordered table-condensed table-big"  id="zones">
	  			<tbody>
					<c:forEach begin="0" end="${fn:length(gamezones)/4 }" varStatus="index">
						<tr>
							<c:forEach items="${gamezones }" var="item" begin="${index.index * 4 }" end="${(index.index+1)*4-1 }">
							<c:if test="${not empty item.zoneId}" >
							<c:if test="${not empty item.zoneName}" >
								<td><input class="zoneId" type="checkbox" value="${item.zoneId }">${item.zoneName }&nbsp;&nbsp;&nbsp;</td>
							</c:if>
							<c:if test="${empty item.zoneName}" >
								<td><input class="zoneId" type="checkbox" value="${item.zoneId }">${item.zoneId}&nbsp;&nbsp;&nbsp;</td>
							</c:if>
							</c:if>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			 </table>
		</div>	
		<button type="button" id="changeZone" class="btn btn-primary " onclick="sure();"><i class="icon-ok"></i> 确定</button>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#checkedAll").click(function(){							
				$(".zoneId").each(function() {
					if($("#checkedAll").prop("checked")==true){
						this.checked = true;
					}else{
						this.checked = false;
					}
				});
			});	
		});
		
		
		function sure(){		
			var checkedIds = new Array();
				var i = 0;
				$(".zoneId").each(function() {
					if(this.checked == true) {
						checkedIds[i] = this.value;
						i++;
					}
				});
				//alert(checkedIds);
				if(i > 0){
					$("#checkedIds", window.parent.document).val(checkedIds);
					parent.search();
				}else{
					parent.errorTip("请选择分区！");
				}			
		}
	</script>
</body>
</html>