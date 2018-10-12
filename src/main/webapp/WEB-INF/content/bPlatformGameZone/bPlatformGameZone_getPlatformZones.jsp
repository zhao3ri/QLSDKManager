<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
	<input type="hidden" id="appId" value="${appId }">
	<div class="panel-body ">
	  <div> <label style="float: right">全 选：<input type="checkbox" id="checkedAll"/></label></div>  
		<div class="table-responsive">
	 		<table class="table table-hover table-striped table-bordered table-condensed table-big">
	  			<tbody>
					<c:forEach begin="0" end="${fn:length(platformGameZones)/4 }" varStatus="index">
						<tr>
							<c:forEach items="${platformGameZones}" var="item" begin="${index.index * 4 }" end="${(index.index+1)*4-1 }">
								<td><input class="zoneId" type="checkbox" value="${item.zoneId }">${item.zoneName }&nbsp;&nbsp;&nbsp;</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			 </table>
			 <button type="button" id="changeZone" class="btn btn-primary " onclick="sure();"><i class="icon-ok"></i> 确定</button>
			 <button type="button" class="btn btn-primary " onclick="javascript :history.back(-1);"><i class="icon-ok"></i> 返回</button>
		</div>	
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
			var isCompare = '${isCompare}';
			var checkedIds = new Array();
			var i = 0;
			$(".zoneId").each(function() {
				if(this.checked == true) {
					checkedIds[i] = this.value;
					i++;
				}
			});
			if(i > 0){
				if(isCompare == 0){
					$("#zoneIds", window.parent.document).val(checkedIds);
					$("#type", window.parent.document).val(1);
				}else if(isCompare == 1){
					$("#compareZoneIds", window.parent.document).val(checkedIds);
					$("#compareType", window.parent.document).val(1);
				}
				parent.searchAndValid();
			}else{
				parent.errorTip("请选择分区！");
			}
		}
	</script>
</body>
</html>