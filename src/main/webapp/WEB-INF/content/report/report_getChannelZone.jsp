<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
	<input type="hidden" id="gameId" value="${gameId }">

	<div class="panel-body ">
		<button type="button" id="changeZone" class="btn btn-primary " onclick="change();"> &nbsp;&nbsp;区&nbsp;服&nbsp;&nbsp;</button>
		<button id="changeChannel"  type="button" class="btn" onclick="change();"> &nbsp;&nbsp;渠&nbsp;道&nbsp;&nbsp;</button>
        <label style="float: right">全 选：<input type="checkbox" id="checkedAll"/></label>  

		 
		<br/>
		<br/>
		<div class="table-responsive" style="min-height: 200px">
	 		<table class="table table-hover table-striped table-bordered table-condensed table-big" style="display:none" id="channels">
	   			<tbody>
	   			<!-- <tr><td><label>全 选：</label><input type="checkbox" id="checkedAll"/></td></tr> -->
					<c:forEach begin="0" end="${fn:length(channelGames)/4 }" varStatus="index">
						<tr>
							<c:forEach items="${channelGames }" var="item" begin="${index.index * 4 }" end="${(index.index+1)*4-1 }">
							<c:if test="${not empty item.channelId}" >
							<c:if test="${not empty item.channelName }">
								<td><input class="channelId" type="checkbox" value="${item.channelId }">${item.channelName }&nbsp;&nbsp;&nbsp;</td>
							</c:if>
							<c:if test="${empty item.channelName }">
							<td><input class="channelId" type="checkbox" value="${item.channelId }">${item.channelId }&nbsp;&nbsp;&nbsp;</td>
							</c:if>
							</c:if>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			   		
	 		<table class="table table-hover table-striped table-bordered table-condensed table-big" style="display:none" id="zones">
	  			<tbody>
	  			<!-- <tr><td><label>全 选：</label><input type="checkbox" id="checkedAll"/></td></tr> -->
					<c:forEach begin="0" end="${fn:length(gamezones)/4 }" varStatus="index">
						<tr>
							<c:forEach items="${gamezones }" var="item" begin="${index.index * 4 }" end="${(index.index+1)*4-1 }">
							<c:if test="${not empty item.zoneId}" >
							<c:if test="${not empty item.zoneName}">
                                 <td><input class="zoneId" type="checkbox" value="${item.zoneId }">${item.zoneName }&nbsp;&nbsp;&nbsp;</td>
                              </c:if>
                              <c:if test="${empty item.zoneName}">
                                 <td><input class="zoneId" type="checkbox" value="${item.zoneId }">${item.zoneId }&nbsp;&nbsp;&nbsp;</td>
                              </c:if>
							</c:if>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			 </table>
		</div>	
		<button type="button" id="changeZone" class="btn btn-primary " onclick="sure();"><i class="icon-ok"></i> 确定</button>
		<button type="button" id="changeZone" class="btn btn-primary " onclick="next();"> 下一步</button>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#zones").show();
			$("#checkedAll").click(function(){
				
				if($("#channels").is(":visible")){
				$(".channelId").each(function() {
					if($("#checkedAll").prop("checked")==true){
						this.checked = true;
					}else{
						this.checked = false;
					}
				});
				//区服不选
				$(".zoneId").each(function() {
						this.checked = false;
				});
				
				}else{
					$(".zoneId").each(function() {
						if($("#checkedAll").prop("checked")==true){
							this.checked = true;
						}else{
							this.checked = false;
						}
					});
					//渠道不选 
					$(".channelId").each(function() {
							this.checked = false;
					});
					
				}
			});	
		});
		
		function change(){
			if($("#channels").is(":visible")){
				if($(".channelId").prop("checked") ){
					$("#checkedAll").prop("checked",false);
				}else{
					$("#checkedAll").prop("checked",true);
				}
				
				$("#zones").show();
				$("#channels").hide();
				
				$("#changeZone").addClass("btn-primary");
				$("#changeChannel").removeClass("btn-primary");
			}else {
				if($(".zoneId").prop("checked")){ 
					$("#checkedAll").prop("checked",false);
				}else{
					$("#checkedAll").prop("checked",true);
				}
				
				$("#zones").hide();
				$("#channels").show();
				
				$("#changeZone").removeClass("btn-primary");
				$("#changeChannel").addClass("btn-primary");
			}
		}
		
		function sure(){
			var isCompare = '${isCompare}';
			//渠道
			if($("#channels").is(":visible")){
				var checkedIds = new Array();
				var i = 0;
				$(".channelId").each(function() {
					if(this.checked == true) {
						checkedIds[i] = this.value;
						i++;
					}
				});
				//alert(isCompare);
				if(i > 0){
					if(isCompare == 0){
						$("#channelIds", window.parent.document).val(checkedIds);
						$("#zoneIds", window.parent.document).val("");
						$("#type", window.parent.document).val(2);
					}else if(isCompare == 1){
						$("#compareChannelIds", window.parent.document).val(checkedIds);
						$("#compareZoneIds", window.parent.document).val("");
						$("#compareType", window.parent.document).val(2);
					}
					parent.searchAndValid();
				}else{
					parent.errorTip("请选择渠道！");
				}
			}
			//分区 
			else{
				var checkedIds = new Array();
				var i = 0;
				$(".zoneId").each(function() {
					if(this.checked == true) {
						checkedIds[i] = this.value;
						i++;
					}
				});
				//alert(isCompare);
				if(i > 0){
					if(isCompare == 0){
						$("#zoneIds", window.parent.document).val(checkedIds);
						$("#channelIds", window.parent.document).val("");
						$("#type", window.parent.document).val(1);
					}else if(isCompare == 1){
						$("#compareZoneIds", window.parent.document).val(checkedIds);
						$("#compareChannelIds", window.parent.document).val("");
						$("#compareType", window.parent.document).val(1);
					}
					parent.searchAndValid();
				}else{
					parent.errorTip("请选择分区！");
				}
			}
		}
		
		
		function next(){
			var gameId = $("#gameId").val();
			var isCompare = '${isCompare}';
			//渠道可见 
			if($("#channels").is(":visible")){
				var checkedIds = new Array();
				var i = 0;
				$(".channelId").each(function() {
					if(this.checked == true) {
						checkedIds[i] = this.value;
						i++;
					}
				});
				//alert(isCompare);
				//alert(checkedIds);
				if(i > 0){
					if(isCompare == 0){
						$("#channelIds", window.parent.document).val(checkedIds);
						$("#zoneIds", window.parent.document).val("");
						$("#type", window.parent.document).val(2);
					}else if(isCompare == 1){
						$("#compareChannelIds", window.parent.document).val(checkedIds);
						$("#compareZoneIds", window.parent.document).val("");
						$("#compareType", window.parent.document).val(2);
					}
					document.location.href="${ctx}/bChannelGameZone/bChannelGameZone_getChannelZones.shtml?gameId="+gameId+"&channelIds="+checkedIds+"&isCompare="+isCompare;
				}else{
					parent.errorTip("请选择渠道！");
				}
			}
			//分区可见 
			else{
				var checkedIds = new Array();
				var i = 0;
				$(".zoneId").each(function() {
					if(this.checked == true) {
						checkedIds[i] = this.value;
						i++;
					}
				});
				//alert(isCompare);
				//alert(checkedIds);
				if(i > 0){
					if(isCompare == 0){
						$("#zoneIds", window.parent.document).val(checkedIds);
						$("#channelIds", window.parent.document).val("");
						$("#type", window.parent.document).val(1);
					}else if(isCompare == 1){
						$("#compareZoneIds", window.parent.document).val(checkedIds);
						$("#compareChannelIds", window.parent.document).val("");
						$("#compareType", window.parent.document).val(1);
					}
					document.location.href="${ctx}/bChannelGameZone/bChannelGameZone_getZoneChannels.shtml?gameId="+${gameId}+"&zoneIds="+checkedIds+"&isCompare="+isCompare;
				}else{
					parent.errorTip("请选择分区！");
				}
			}
		}
	</script>
</body>
</html>