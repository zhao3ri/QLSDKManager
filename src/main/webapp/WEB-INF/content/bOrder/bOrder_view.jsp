<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
	<div id="wrap">
	<%@ include file="/common/header.jsp" %>
	<div class="container">
	    <ol class="breadcrumb row">
	    	<li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
	      	<li class="active">充值管理</li>
	      	<li><a href="${ctx}/bOrder/bOrder_list.shtml">充值列表列表</a></li>
	      	<li class="active">充值列表详细信息</li>
	    </ol>
	    <div class="panel panel-default">
	      	<div class="panel-heading ">
	        	<h3 class="panel-title">充值列表详细信息</h3>
	      	</div>
	      	<div class="panel-body ">
		        <dl class="row dl-horizontal form_show_style1">
					<div class="clearfix">
						<div  class="col-lg-6">
		        			<dt>序号：</dt>
		        			<dd>
								<s:property value="bOrder.id"/>
							</dd>
		        		</div>
						<div  class="col-lg-6">
		        			<dt>游戏名称：</dt>
		        			<dd>
								<s:property value="bOrder.gameName"/>
							</dd>
		        		</div>
					</div>
					<div class="clearfix">
						<div  class="col-lg-6">
		        			<dt>渠道名称：</dt>
		        			<dd>
								<s:property value="bOrder.channelName"/>
							</dd>
		        		</div>
						<div  class="col-lg-6">
		        			<dt>用户Id：</dt>
		        			<dd>
								<s:property value="bOrder.uid"/>
							</dd>
		        		</div>
					</div>
					<div class="clearfix">
						<div  class="col-lg-6">
		        			<dt>游戏分区：</dt>
		        			<dd>
		        			<c:if test="${!empty bOrder.zoneName}">
								<s:property value="bOrder.zoneName"/>
							</c:if>
							<c:if test="${empty bOrder.zoneName}">
								<s:property value="bOrder.zoneId"/>
							</c:if>
							</dd>
		        		</div>
						<div  class="col-lg-6">
							<dt>订单号：</dt>
							<dd>
								<s:property value="bOrder.orderId"/>
							</dd>
						</div>
					</div>
					<div class="clearfix">
						<div  class="col-lg-6">
							<dt>角色Id：</dt>
							<dd>
								<s:property value="bOrder.roleId"/>
							</dd>
						</div>
						<div  class="col-lg-6">
							<dt>角色名称：</dt>
							<dd>
						<s:property value="bOrder.roleName"/>
						</dd>
						</div>
					</div>
					<div class="clearfix">
						<div  class="col-lg-6">
							<dt>商品编号：</dt>
							<dd>
								<s:property value="bOrder.goodsId"/>
							</dd>
						</div>
						<div  class="col-lg-6">
							<dt>商品名称：</dt>
							<dd>
								<s:property value="bOrder.goodsName"/>
							</dd>
						</div>
					</div>
					<div class="clearfix">
					<div  class="col-lg-6">
		        			<dt>渠道订单号：</dt>
		        			<dd>
								<s:property value="bOrder.channelOrderId"/>
							</dd>
		        		</div>
						<div  class="col-lg-6">
		        			<dt>自定义参数：</dt>
		        			<dd>
								<s:property value="bOrder.extInfo"/>
							</dd>
		        		</div>
					</div>
					<div class="clearfix">
					<div  class="col-lg-6">
		        			<dt>充值金额：</dt>
		        			<dd>
		        				<fmt:formatNumber  value="${bOrder.amount/100 }" pattern="0.00" />
							</dd>
		        		</div>
						<div  class="col-lg-6">
		        			<dt>支付结果通知地址：</dt>
		        			<dd>
								<s:property value="bOrder.notifyUrl"/>
							</dd>
		        		</div>
					</div>
					<div class="clearfix">
					<div  class="col-lg-6">
		        			<dt>是否定额支付：</dt>
		        			<dd>
		        			  <mt:selectState stateType="fixed" showType="label" value="${bOrder.fixed }"></mt:selectState>
							</dd>
		        		</div>
						<div  class="col-lg-6">
		        			<dt>设备码：</dt>
		        			<dd>
								<s:property value="bOrder.deviceId"/>
							</dd>
		        		</div>
					</div>
					<div class="clearfix">
					<div  class="col-lg-6">
		        			<dt>游戏客户端类型：</dt>
		        			<dd>
		        			   <mt:selectState stateType="clientType" showType="label" value="${bOrder.clientType }"></mt:selectState>
							</dd>
		        		</div>
						<div  class="col-lg-6">
		        			<dt>异常信息：</dt>
		        			<dd>
								<s:property value="bOrder.errorMsg"/>
							</dd>
		        		</div>
					</div>
					<div class="clearfix">
						<div  class="col-lg-6">
		        			<dt>状态：</dt>
		        			<dd>
								<mt:selectState showType="label" value="${bOrder.status}" stateType="orderStatus"/>
							</dd>
		        		</div>
						<div  class="col-lg-6">
		        			<dt>通知状态：</dt>
		        			<dd>
								<mt:selectState showType="label" value="${bOrder.notifyStatus}" stateType="orderNotifyStatus"/>
							</dd>
		        		</div>
					</div>
					<div class="clearfix">
						<div  class="col-lg-6">
		        			<dt>创建时间：</dt>
		        			<dd>
								<s:date name="bOrder.createTime" format="yyyy-MM-dd HH:mm:ss"/>
							</dd>
		        		</div>
		        		<div  class="col-lg-6">
		        			<dt>更新时间：</dt>
		        			<dd>
								<s:date name="bOrder.updateTime" format="yyyy-MM-dd HH:mm:ss"/>
							</dd>
		        		</div>
					</div>
				</dl>
	    	</div>
	    	 <div class="panel-footer text-center">
	        	<a href="javascript:history.back();" class="btn btn-default"><i class="icon-reply-all"></i> 返回列表</a>
	        </div>
        </div>
    </div>
    </div>
    <%@ include file="/common/footer.jsp" %>   
</body>
</html>