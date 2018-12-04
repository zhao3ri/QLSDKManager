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
            <div class="col-lg-8">
                <div class="panel panel-default">
                    <div class="panel-heading "><a
                            href="${ctx}/history/history_list1.shtml?t=${r}&history.oid=<s:property value="#session.sessionUserInfo.id"/>"
                            class="pull-right">更多>></a>
                        <h3 class="panel-title"><i class="icon-time "></i> 操作记录</h3>
                    </div>
                    <div class="table-responsive index_panel" id="history_container">

                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading ">
                        <h3 class="panel-title "><i class="icon-bullhorn   color_green"></i> 用户信息</h3>
                    </div>
                    <div class="panel-body index_panel">
                        <dl class="row dl-horizontal form_show_style1">
                            <div class="clearfix">
                                <div class="col-lg-12">
                                    <dt>账号：</dt>
                                    <dd>
                                        ${sessionUserInfo.userName }
                                    </dd>
                                </div>
                            </div>
                            <div class="clearfix">
                                <div class="col-lg-12">
                                    <dt>真实姓名：</dt>
                                    <dd>
                                        ${sessionUserInfo.realName }
                                    </dd>
                                </div>
                            </div>
                            <div class="clearfix">
                                <div class="col-lg-12">
                                    <dt>部门：</dt>
                                    <dd>
                                        ${sessionUserInfo.dname }
                                    </dd>
                                </div>
                            </div>
                            <div class="clearfix">
                                <div class="col-lg-12">
                                    <dt>身份：</dt>
                                    <dd>
                                        ${sessionUserInfo.name }
                                    </dd>
                                </div>
                            </div>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
        <%-- <div class="row">
           <div class="clearfix col-lg-12">
               <div class="panel panel-default">
                   <div class="panel-heading "><a href="${ctx}/history/history_list1.shtml?t=${r}&history.oid=<s:property value="#session.sessionUserInfo.id"/>" class="pull-right">更多>></a>
                       <h3 class="panel-title"><i class="icon-time "></i> 操作记录</h3>
                   </div>
                   <div class="table-responsive index_panel" id="history_container">

                   </div>
               </div>
           </div>
        </div> --%>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
<%@ include file="/common/chart.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        loadReverse();
        loadHistory();
        //loadNotice();
    });

    //加载待办任务
    function loadReverse() {
        //$("#reverse_container").load("/feedback/feedback_listmain.shtml?t=${r}");
    }

    //加载操作历史
    function loadHistory() {
        $("#history_container").load('${ctx}/history/history_listmain.shtml?t=${r}&history.oid=<s:property value="#session.sessionUserInfo.id"/>');
    }

    //加载系统公告
    function loadNotice() {
        $.ajax({
            type: "POST",
            url: "/notice/notice_getNotice4MainPage.shtml",
            success: function (jsonStr) {
                var json = eval(jsonStr);
                var result = json[0];
                if (result["errMsg"]) {
                    $("#notice_content").val(result["errMsg"]);
                }
                else if (result["notice"]) {
                    var notice = result["notice"];
                    var createDate = new Date(notice.createDate.time);
                    $("#notice_title").html(notice.title);
                    $("#notice_createDate").html(createDate.getFullYear() + "-" + (createDate.getMonth() + 1) + "-" + createDate.getDate());
                    $("#notice_content").html(notice.content);
                }
                else {
                    $("#notice_container").html("<li  class='list-group-item panel'>暂无数据</li>");
                }
            },
            error: function () {

            }
        });
    }
</script>
</body>
</html>