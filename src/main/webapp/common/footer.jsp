<%@ page contentType="text/html;charset=UTF-8" %>
<div id="footer"class="footer_bar">
  <div class="con"><s:text name="sys.project.copyright"/></div>
</div>
<!-- 页面代码结束 --> 
<!-- jquery 表单验证 -->
<script type="text/javascript" src="${ctx}/scripts/jquery/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery/validate/messages_cn.js"></script>
<script src="/js/bootstrap/js/bootstrap.min.js"></script> 

<!-- 全局脚本 --> 
<!-- 页面脚本 --> 
<script type="text/javascript" src="/js/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script> <!-- bootstrap-switch切换开关 --> 
<!-- 日期选择插件 --> 
<script type="text/javascript" src="/js/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script> 
<script type="text/javascript" src="/js/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/plugins/bootstrap-daterangepicker/moment.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/plugins/bootstrap-daterangepicker/daterangepicker.js" charset="UTF-8"></script>
<!-- messenger-master弹出提示信息插件 -->
<script type="text/javascript" src="/js/plugins/messenger-master/js/messenger.min.js" charset="UTF-8"></script> 
<script type="text/javascript" src="/js/plugins/messenger-master/js/messenger-theme-flat.js" charset="UTF-8"></script> 
<!-- 上传文件插件 -->
<script type="text/javascript" src="/js/plugins/bootstrap-fileupload/bootstrap-fileupload.js" charset="UTF-8"></script>
<!-- 数据加载查询插件 -->
<script type="text/javascript" src="/js/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="/js/plugins/select2/select2_locale_zh-CN.js"  charset="UTF-8"></script>
<!--jquery UI插件 -->
<script type="text/javascript" src="/js/plugins/jquery-ui/jquery-ui.min.js" ></script>
<!--富文本编辑器 -->
<script src="/js/plugins/bootstrap-wysiwyg-master/bootstrap-wysiwyg.js" type="text/javascript"></script>
<script src="/js/plugins/bootstrap-wysiwyg-master/external/jquery.hotkeys.js" type="text/javascript"></script>
<!-- 兼容IE8以下 -->
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="js/excanvas.min.js"></script><![endif]-->

<script type="text/javascript" src="/js/echart/echarts.js"></script>

<!-- 自定义脚本 --> 
<script src="/js/main.js"></script> 
<script src="/scripts/table.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jqueryValidate.js"></script>
<script type="text/javascript" src="${ctx}/scripts/common.js"></script>
<script type="text/javascript" src="${ctx}/scripts/cookie.js"></script>
<s:actionmessage theme="custom"/>
<!-- /页面脚本 --> 
<script>
require.config({
    paths: {
        echarts: '${ctx}/js/echart'
    }
});
</script>