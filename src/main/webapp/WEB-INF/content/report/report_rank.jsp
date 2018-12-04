<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
<div id="wrap" > 
  <%@ include file="/common/header.jsp" %>
  <div class="container">
   <div class="row"> 
   	<div class="col-md-1 ">
   		<%@ include file="/common/reportLeft.jsp" %>
	</div>
    <div class=" col-md-11">
        <div class="panel panel-default">
        	<form role="form"  action="report_rank.shtml" method="post" id="mainForm">
        	<input type="hidden" id="checkedIds" name="checkedIds" value="${checkedIds }"/>
	        	<div class="form-inline popover-show panel-body list_toolbar">
		        	<div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择游戏">  
			          	<select class="form-control" name="gameId"  placeholder="请选择游戏" id="appNameSel">
							<s:iterator value="allGames" var="item">
								<option value="${item.id}" <c:if test="${item.id == gameId }">selected</c:if>>${item.gameName }</option>
							</s:iterator>
						</select>	 
			        </div>
			        <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="请选择平台">
			             <select class="select2 form-control select2_sample1" placeholder="请选择平台" name="platformId" data-original-title="" title="">
					         <option value=""></option>
					         <s:iterator value="platforms" var="item">
						    	 <option value="${item.id}" <c:if test="${item.id==platformId}">selected</c:if>>${item.platformName }</option>
					         </s:iterator>								
			             </select>
			        </div>	
			     <!--   <div class="form-group"><button  type="button" class="btn " onclick="selectZone();"> 区服</button></div>-->
		         <!--   <div class="form-group width_input"  data-toggle="popover"  data-placement="top" data-content="时间">
			            <input  class="form-control daterange" type="text" readonly name="selectRange" id="selectRange" value="${selectRange }"  placeholder="时间选择" sType="search" />
			        </div>-->
			         <div class="form-group width_btn">
 		          		<button  type="submit" class="btn  btn-primary " onclick="search();"><i class="icon-search"></i> 搜索</button>
		          		<button  id="resetBtn"  type="button" class="btn  btn-default " ><i class="icon-eraser"></i> 重置</button>
		        	</div>
		    	</div>
		<div class="panel panel-default">
          <div class="panel-heading ">
            <h3 class="panel-title">  充值排行版 排列前200名
              <button type="button" id="excelExport" class=" badge badge-info btn  pull-right  " data-toggle="modal" data-target="#modal_alert" data-backdrop="static"><i class="icon-table"></i> Excel表导出</button>
            </h3>
          </div>
          <div class="panel-body ">
      		<div class="table-responsive">
        		<table class="table table-hover table-striped table-bordered table-condensed table-big">
          			<thead>
            			<tr>
							<th>用户ID</th>
							<th>充值总额</th>
							<th>排序</th>
							<th>最近登录时间</th>
							<th>操作</th>
            			</tr>
          			</thead>
          			<tbody>
            			<s:if test="pageRechargeRank.result.size>0">
						<s:iterator value="pageRechargeRank.result" var="item" status="st">
						<tr>
							<td><s:property value="platformName"/>_<s:property value="uid"/></td>
							<td><fmt:formatNumber  value="${amount/100 }" pattern="0.00" /></td>
							<td><s:property value="#st.count+firstResult"/></td>
							<td><s:date name="lastLoginDate" format="yyyy-MM-dd HH:mm:ss"/></td>
							<td><a href="javascript:view('${uid }',${platformId })">查看订单</a></td>
						</tr>
						</s:iterator>
						</s:if>
						<s:else>
						<tr>
							<td colspan="5" style="text-align: center;">当前列表没有数据！</td>
						</tr>
						</s:else>
					</tbody>
        		</table>
      		</div>
      		
      			<s:if test="pageRechargeRank.result.size>0">
		      		<div class="table_page dropup">
		      			<c:set var="p" value="pageRechargeRank"/>
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
</div>
    <%@ include file="/common/footer.jsp" %>      
	<script type="text/javascript">
		$(document).ready(function() {
			$("#reportLeft_rechargeRank").addClass("active");
			
			$("#resetBtn").click(function(){
				location.assign("report_rank.shtml"); 
			});
			
			$("#excelExport").click(function() {
				if(confirm("您确认导出Excel？")){
					$("#mainForm").attr("action","report_rankExcelExport.shtml");
					$("#mainForm").submit();
					$("#mainForm").attr("action","report_rank.shtml");
				}
			});
		});
		
		function selectZone(){
			var gameId = $("#appNameSel").val();
			if($.trim(gameId) == ""){
				errorTip("请先选择游戏！");
				return;
			}
			art.dialog.open("${ctx}/report/report_getZone.shtml?gameId="+gameId, {title:'筛选',id:'addBox',fixed:true,lock:true,background:'#000',opacity:0,height:'60%',width:'60%',fited:true
				
			});
		}
		
		function view(uid,platformId){
			var gameId = $("#appNameSel").val();
			var selectRange = $("#selectRange").val();
			location.assign("/bOrder/bOrder_list.shtml?BOrder.status=2&BOrder.notifyStatus=2&BOrder.uid=" + uid + "&BOrder.platformId=" + platformId + "&BOrder.gameId=" + gameId )
		}
	</script>
</body>
</html>