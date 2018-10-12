<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta name="viewport" content="target-densitydpi=high-dpi,width=480,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0, user-scalable=no" />
		<title>404错误-页面没有找到</title>
		<style>
			body{ margin:0; padding:0; font-family:"微软雅黑","宋体",Arial; font-size:16px; background-color:#f5f5f5;}
			.body_wrap{ width:400px; margin:auto; overflow:hidden;}
			.title{ display:block; text-align:center; font-weight:bold; font-size:18px; margin:20px 0; color:#CC0000;}
			.content{ background:url(/images/404_top.jpg) no-repeat top center; position:relative;}
			.code1,.code2,.code3{ font-size:110px; line-height:110px; overflow:hidden; font-weight:bold; display:block; position:absolute; color:#3f3f3f;}
			.code1{ top:70px; left:77px;}
			.code2{ top:180px; left:144px;}
			.code3{ top:70px; right:120px;}
			.code_info{ position:absolute; width:53px; height:53px; overflow:hidden;top:47px; font-weight:bold;font-size:20px; line-height:25px; text-align:center; right:56px; color:#fff;}
			.code_con{ padding-top:300px; overflow:hidden;}
			.code_con fieldset{ border:1px solid #666; border-radius:5px; padding: 15px 10px; color:#c00; font-size:16px;}
			.code_con legend{ border:1px solid #666; border-radius:5px; margin-left:15px; padding:3px 5px; background-color:#666; color:#fff;}
			.links{ text-align:center; margin-top:20px; display:block;}
			.links a{ margin:0px 10px;font-weight:bold;font-size:20px; padding-left:22px;display:inline-block; color:#333!important; text-decoration:none;}
			.links a:hover{ text-decoration:underline;}
		</style>	
	</head> 
	<body>
		<div class="body_wrap">
			<div class="title">HTTP 错误 404 - Not Found</div>
			<div class="content">
				<span class="code1">4</span>
				<span class="code2">0</span>
				<span class="code3">4</span>
				<span class="code_info">请求失败</span>
				<div class="code_con">
					<fieldset>
						<legend>错误摘要:</legend> 
						您要找的资源已被删除、已更名或暂时不可用。
					</fieldset>
				</div>
			</div>
			<div class="links">
				<a href="/index.shtml" class="l_home">首页</a> <a href="javascript:history.go(-1)" class="l_back">后退</a>
			</div>
		</div>
	</body>
</html>