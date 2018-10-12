<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
	<div id="wrap">
	<%@ include file="/common/header.jsp" %>
	<div class="container">
	    <ol class="breadcrumb row">
	    	<li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
	      	<li class="active">操作历史管理</li>
	      	<li><a href="${ctx}/history/history_list1.shtml">操作历史列表</a></li>
	      	<li class="active">操作历史详细信息</li>
	    </ol>
	    <div class="panel panel-default">
	      	<div class="panel-heading ">
	        	<h3 class="panel-title">操作历史详细信息</h3>
	      	</div>
	      	<div class="panel-body ">
		        <dl class="row dl-horizontal form_show_style1">
					<div class="clearfix">
						<div  class="col-lg-6">
		        			<dt>用户：</dt>
		        			<dd>
								<s:property value="history.user.realName"/>
							</dd>
		        		</div>
						<div  class="col-lg-6">
		        			<dt>操作模块：</dt>
		        			<dd>
								<s:property value="history.omname"/>
							</dd>
		        		</div>
					</div>
					<div class="clearfix">
						<div  class="col-lg-6">
		        			<dt>动作：</dt>
		        			<dd>
								<s:property value="history.oaction"/>
							</dd>
		        		</div>
		        		<div  class="col-lg-6">
		        			<dt>字段：</dt>
		        			<dd>
								<s:property value="history.opname"/>
							</dd>
		        		</div>
					</div>
					<div class="clearfix">
						<div  class="col-lg-6">
		        			<dt>原值：</dt>
		        			<dd>
								<s:property value="history.ovalue.replace('\r\n','<br/>').replace('  ',' &nbsp;')" escape="false"/>
							</dd>
		        		</div>
						<div  class="col-lg-6">
		        			<dt>新值：</dt>
		        			<dd>
								<s:property value="history.pvalue.replace('\r\n','<br/>').replace('  ',' &nbsp;')" escape="false"/>
							</dd>
		        		</div>
					</div>
					<div class="clearfix">
						<div  class="col-lg-6">
		        			<dt>记录时间：</dt>
		        			<dd>
								<s:date name="history.inserttime" format="yyyy-MM-dd HH:mm:ss"/>
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