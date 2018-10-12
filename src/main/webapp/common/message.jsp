<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Zidian3g - 信息提示</title>
	<link type="text/css" rel="stylesheet" href="${ctx}/styles/main.css" />
	<%@ include file="/common/jquerylibs.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#index").click(function() {
				window.parent.location.reload("/my_website.shtml");
			});
			$("#logout").click(function() {
				window.parent.location.reload("/j_spring_security_logout");
			});
		});
	</script>
</head>

<body>
	<div id="main">
		<div id="main_search">
			<div id="search_header">信息提示</div>
			<div id="search_data">
				<div id="message">
					<div class="error_message"><b><s:property value="message" /><b></div>
					<b><s:actionmessage theme="custom" cssClass="error_message" /></b>
				</div>
				<div>
					<a id="index" href="#">返回首页</a> 
					<a id="logout" href="<c:url value="/j_spring_security_logout"/>">退出登录</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>