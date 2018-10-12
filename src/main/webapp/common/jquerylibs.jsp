<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="zh-CN">
<head>
<%@ include file="/common/meta.jsp" %>
<title><s:text name="sys.project.name"/></title>
<!-- 全局样式 -->
<link rel="stylesheet" href="${ctx}/js/bootstrap/css/bootstrap.min.css" type="text/css">
<!-- font-awesome字体图标 -->
<link href="${ctx}/js/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css">
<!-- 自定义bootstrap主题样式 -->
<link href="${ctx}/theme/default/css/bootstrap-theme.css" rel="stylesheet" type="text/css">

<!-- 全局样式 -->
<!-- 页面样式 -->
<!-- bootstrap-switch切换开关 -->
<link href="${ctx}/js/plugins/bootstrap-switch/stylesheets/bootstrap-switch.css" rel="stylesheet" type="text/css">

<!-- datetimepicker日期时间选择插件 -->
<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<!-- messenger-master弹出提示信息插件 -->
<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/messenger-master/css/messenger.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/messenger-master/css/messenger-theme-flat.css" />
<!-- 上传文件插件 -->
<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/bootstrap-fileupload/bootstrap-fileupload.css" />
<!-- 数据加载查询插件 -->
<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/select2/select2.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/select2/select2-bootstrap.css" />
<!--jquery UI插件 -->
<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/jquery-ui/themes/custom-theme/jquery-ui-1.10.0.custom.css" />

<script type="text/javascript" src="${ctx}/scripts/artdialog/artDialog.js?skin=opera"></script>

<script src="${ctx}/js/jquery.js"></script> 
<script src="${ctx}/js/jquery-migrate-1.2.1.min.js"></script> 

<!-- /页面样式 -->
<!-- 自定义样式 -->
<link href="${ctx}/theme/default/css/main.css" rel="stylesheet" type="text/css">

<!-- 项目自定义样式 -->
<link href="${ctx}/styles/main.css" rel="stylesheet" type="text/css">

<!-- 支持IE8 -->
<!--[if lt IE 9]>
  <script src="/js/html5shiv.js"></script>
  <script src="/js/respond.min.js"></script>
<![endif]-->
<!-- 低于IE8时提示升级浏览器 -->
<!--[if lt IE 8]>
  <script src="/js/update_ie/update_ie6.js" ></script>           
<![endif]-->

</head>