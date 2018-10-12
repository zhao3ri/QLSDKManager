<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<html>
<!-- 全局脚本 -->
<script src="/js/jquery.js"></script>
<body class="login_page">
<!-- 页面代码开始 -->
<div id="wrap">
    <div class="container">
        <div class="login_logo">
            <h1><s:text name="sys.project.name"/></h1>
        </div>
        <div class="login_content  tooltip-show popover-show">
            <!-- 登录框开始 -->
            <form class="login-form" id="loginForm" action="${ctx}/login.shtml" method="post">
                <h2 class="form-title">用户登录</h2>
                <div class="form-group ">
                    <div class="input-group" data-toggle="popover" data-placement="top"
                         data-content="请输入帐号，数字和字母组成，长度5~15个字符。">
                        <label class="input-group-addon"><span class="glyphicon glyphicon-user"></span> 帐号：</label>
                        <input type="text" class="form-control" name="userName" placeholder="请输入帐号">
                    </div>
                </div>
                <div class="form-group ">
                    <div class="input-group" data-toggle="popover" data-placement="top" data-content="请输入密码，密码区分大小写。">
                        <label class="input-group-addon"><i class="icon-key "></i> 密码：</label>
                        <input class="form-control" type="password" placeholder="请输入密码" name="password"/>
                    </div>
                </div>
                <div class="form-group text-right">
                    <!--  记住密码
                     <div class="make-switch switch-small" data-on="success" data-off="primary" data-on-label="开" data-off-label="关"  data-text-label="">
                           <input type="checkbox" name="autoLogin" checked id="autoLogin" >
                   </div> -->
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"><span class="glyphicon glyphicon-ok"></span>
                        登 录
                    </button>
                </div>
            </form>
            <!-- 登录框结束 -->
        </div>
    </div>
</div>

<%@ include file="/common/footer.jsp" %>
<script type="text/javascript" src="${ctx }/scripts/cookie.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#loginForm").validate({
            rules: {
                "userName": {maxlength: 32, required: true},
                "password": {maxlength: 32, required: true}
            }
        });
    });
</script>
</body>
</html>