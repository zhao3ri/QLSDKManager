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
	      <li><a href="${ctx}/bPlatformGame/bPlatformGame_list.shtml">联运游戏关联管理</a></li>
	      <s:if test="id==null">
	      <li class="active">新增渠道游戏分区表信息</li>
	      </s:if>
	      <s:else>
	      <li class="active">修改渠道游戏分区表信息</li>
	      </s:else>
	    </ol>
	    <div class="panel panel-default">
	      	<div class="panel-heading ">
	        	<h3 class="panel-title">${id==null?"新增":"修改"}渠道游戏分区表信息</h3>
	      	</div>
	      	<form role="form" id="inputForm" action="bPlatformGameZone_save.shtml" method="post">
	        	<div class="panel-body ">
	          		<div class=" tooltip-show form-horizontal">
	    				<input type="hidden" name="zoneIds" id="zoneIds"/>
	    				<input type="hidden" name="BPlatformGameZone.gameId" value="${BPlatformGameZone.gameId }"/>
	    				<input type="hidden" name="BPlatformGameZone.platformId" value="${BPlatformGameZone.platformId }"/>
						
						<div class="form-group clearfix" >
							<label  class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>分区：</label>
							<div class=" col-sm-9 col-lg-5"  id="zoneSel">
								<table>
									<c:forEach begin="0" end="${fn:length(gamezones)/4 }" varStatus="index">
										<tr>
											<c:forEach items="${gamezones }" var="item" begin="${index.index * 4 }" end="${(index.index+1)*4-1 }">
												<td><input name="gameZones" type="checkbox" <c:if test="${item.isHave == 1 }">checked</c:if> value="${item.zoneId }">${item.zoneName }&nbsp;&nbsp;&nbsp;</td>
											</c:forEach>
										</tr>
									</c:forEach>
								</table><br/><input onclick="checkAll();" id="checkedAll" type="checkbox">全选
							</div>
						</div>
	     			</div>
  	    		</div>
	    		<div class="panel-footer">
	          		<div class=" text-center">
						<button type="button" class="btn btn-primary" onclick="submitForm();"><i class="icon-ok"></i> 提交</button>
						<a class="btn btn-default" href="/bPlatformGame/bPlatformGame_list.shtml"><i class="icon-remove"></i> 取消</a>
	          		</div>
            	</div>
          	</form>
      	</div>
  	</div>
	</div>
	<%@ include file="/common/footer.jsp" %>    
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				rules: {
					"BPlatformGameZone.gameId":{number:true,maxlength:10,required:true},
					"BPlatformGameZone.platformId":{digits:true,maxlength:10,required:true},
					"gameZones":{required:true}
				}
			});
		});
		
		function submitForm(){
			if($("#inputForm").valid()){
				var checkedIds = new Array();
				var i = 0;
				$("input[name='gameZones']:checkbox").each(function() {
					if(this.checked == true) {
						checkedIds[i] = this.value;
						i++;
					}
				}); 
				$("#zoneIds").val(checkedIds);
				$("#inputForm").submit();
			}
		}
		
		function checkAll(){
			$("input[name='gameZones']:checkbox").each(function(){ 
				if($("#checkedAll").prop("checked")==true){
					this.checked = true;
				}else{
					this.checked = false;
				}
			});
		}
	</script>
</body>
</html>