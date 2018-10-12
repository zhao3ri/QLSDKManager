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
    <div class="container">
        <div class="row">
            <table class="table table-bordered">
                <tr>
                    <td>今天充值总额:</td>
                    <td>${dashbord.totalPay/100}</td>
                </tr>
            </table>
        </div>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
<%@ include file="/common/chart.jsp" %>
</body>
</html>