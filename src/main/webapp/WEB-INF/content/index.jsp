<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>

<%
    java.util.Random C = new java.util.Random();
    int r = C.nextInt();
    pageContext.setAttribute("r", r);
%>
<html>
<body class="fix_top_nav_padding">
<!-- 页面代码开始 -->
<div id="wrap">
    <%@ include file="/common/header.jsp" %>
    <div class="container" style="margin-top: 10px">
        <div class="row">
            <div class="table-responsive" style="width: 100%;">
                <table class="table-hover table-striped table-bordered table-condensed table-big"
                       style="width:90%;margin:auto;background-color: rgba(215,232,245,0.7)">
                    <tr>
                        <td><b>今日充值总额:</b></td>
                        <td>${dashbord.totalPay/100}</td>
                    </tr>
                    <tr>
                        <td><b>今日总新增用户:</b></td>
                        <td>${dashbord.currentNewUser}</td>
                    </tr>
                    <tr>
                        <td><b>本月充值总额:</b></td>
                        <td>${dashbord.totalMonthlyPay/100}</td>
                    </tr>
                    <tr>
                        <td><b>本月总新增用户:</b></td>
                        <td>${dashbord.totalMonthlyNewUser}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
<%@ include file="/common/chart.jsp" %>
</body>
</html>