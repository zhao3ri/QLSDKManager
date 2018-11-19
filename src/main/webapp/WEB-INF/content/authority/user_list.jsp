<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>

<%
    java.util.Random C = new java.util.Random();
    int r = C.nextInt();
    pageContext.setAttribute("r", r);
%>

<body class="fix_top_nav_padding">
<div id="wrap">
    <%@ include file="/common/header.jsp" %>
    <div class="container">
        <ol class="breadcrumb row">
            <li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
            <li class="active">系统管理</li>
            <li class="active">管理员列表</li>
        </ol>
        <form role="form" action="user_list.shtml" method="post" id="mainForm">
            <div class="panel panel-default">
                <div class="panel-heading ">管理员列表信息查询</div>
                <div class="form-inline popover-show panel-body list_toolbar">
                    <div class="form-group    width_input" data-toggle="popover" data-placement="top"
                         data-content="请输入用户名">
                        <input class="form-control" type="text" placeholder="用户名" name="searchUser.userName"
                               value="${searchUser.userName}"/>
                    </div>
                    <div class="form-group    width_input" data-toggle="popover" data-placement="top"
                         data-content="请输入真实姓名">
                        <input class="form-control" type="text" placeholder="真实姓名" name="searchUser.realName"
                               value="${searchUser.realName}"/>
                    </div>
                    <div class="form-group    width_input" data-toggle="popover" data-placement="top"
                         data-content="请选择身份">
                        <select size="1" class="form-control" name="searchUser.identityId">
                            <option value="">--请选择身份--</option>
                            <s:iterator value="identityList" var="identity">
                                <option
                                        <s:if test="searchUser.identityId==#identity.id">selected</s:if>
                                        value="${identity.id }">${identity.name }</option>
                            </s:iterator>
                        </select>
                    </div>
                    <div class="form-group width_input" data-toggle="popover" data-placement="top" data-content="请选择状态">
                        <mt:selectState name="searchUser.state" showType="select" stateType="userState"
                                        value="${searchUser.state}" clazz="form-control" emptyString="--请选择状态--"/>
                    </div>
                    <div class="form-group width_btn">
                        <button type="button" class="btn  btn-primary " onclick="search();"><i class="icon-search"></i>
                            搜索
                        </button>
                        <button type="button" class="btn  btn-default " onclick="resetSearch();"><i
                                class="icon-eraser"></i> 重置
                        </button>
                    </div>
                    <div class="form-group width_btn pull-right">
                        <button type="button" class="btn btn-success " id="newitem"><i class="icon-plus"></i> 添加
                        </button>
                    </div>
                </div>
                <div class="panel-body ">
                    <div class="table-responsive">
                        <table id="tab-user"
                               class="table table-hover table-striped table-bordered table-condensed table-big">
                            <thead>
                            <tr>
                                <th>选择</th>
                                <%--<th>序号</th>--%>
                                <th>用户名</th>
                                <th>真实姓名</th>
                                <th>用户身份</th>
                                <th>状态</th>
                                <th>加入时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:if test="page.result.size>0">
                                <s:iterator value="page.result" var="tempUser">
                                    <tr>
                                        <td>
                                            <label>
                                                <c:if test="${tempUser.userName=='admin'&& tempUser.identityId==3}">
                                                    &nbsp;&nbsp;
                                                </c:if>
                                                <c:if test="${tempUser.userName!='admin'}">
                                                    <input type="checkbox" name="checkedIds" class="checkedIds"
                                                           value="${tempUser.id}">
                                                </c:if>
                                            </label>
                                        </td>
                                        <%--<td>${tempUser.id}</td>--%>
                                        <td><s:property value="userName"/></td>
                                        <td><s:property value="realName"/></td>
                                        <td><s:property value="identityName"/></td>
                                        <td><mt:selectState showType="label" value="${tempUser.state}"
                                                            stateType="userState"/></td>
                                        <td><s:date name="#tempUser.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>
                                            <div class="btn-group btn-group-sm pull-right">
                                                <button type="button" class="btn btn-default  dropdown-toggle"
                                                        data-toggle="dropdown"> 操作 <span class="caret"></span></button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <c:if test="${tempUser.userName!='admin'}">
                                                        <li><a href="user_update.shtml?user.id=${tempUser.id }">修改</a>
                                                        </li>
                                                        <li>
                                                            <a href="javascript:confirmAction('user_disabled.shtml?audit=-1&checkedIds=${tempUser.id }','您确认删除？');">删除</a>
                                                        </li>
                                                        <li role="presentation" class="divider"></li>
                                                    </c:if>
                                                    <li>
                                                        <a href="javascript:confirmAction('user_disabled.shtml?audit=0&checkedIds=${tempUser.id }','您确认启用？');">启用</a>
                                                    </li>
                                                    <li>
                                                        <a href="javascript:confirmAction('user_disabled.shtml?audit=1&checkedIds=${tempUser.id }','您确认禁用？');">禁用</a>
                                                    </li>
                                                    <li role="presentation" class="divider"></li>
                                                    <li><mt:modalDialog
                                                            remote="/history/history_list.shtml?history.rid=${tempUser.id}&history.omkey=user&t=${r}"
                                                            id="historyWindow${tempUser.id}" title="历史记录"
                                                            role="dialog"
                                                            name="历史记录" type="link" width="700"/></li>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </s:if>
                            <s:else>
                                <tr align="center">
                                    <td align="center" colspan="7">当前页没有记录！</td>
                                </tr>
                            </s:else>
                            </tbody>
                        </table>
                    </div>
                    <s:if test="page.result.size>0">
                        <div class="table_page dropup">
                            <div class="btn-group btn-group-sm pull-left">
                                <label class="btn btn-default  table_select_all">&nbsp;
                                    <input type="checkbox" id="checkedAll">
                                </label>
                                <button type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"></span></button>
                                <ul class="dropdown-menu text-left" role="menu">
                                    <li><a id="remove" href="#">批量删除</a></li>
                                    <li role="presentation" class="divider"></li>
                                    <li><a id="across" href="#">批量启用</a></li>
                                    <li><a id="unacross" href="#">批量禁用</a></li>
                                </ul>
                            </div>
                            <%@ include file="/common/pagination.jsp" %>
                        </div>
                    </s:if>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#checkedAll").click(function () {
            $(".checkedIds").each(function () {
                if ($("#checkedAll").prop("checked") == true) {
                    this.checked = true;
                } else {
                    this.checked = false;
                }
            });
        });
        $("#remove").click(function () {
            var checkedIds = new Array();
            var i = 0;
            $(".checkedIds").each(function () {
                if (this.checked == true) {
                    checkedIds[i] = this.value;
                    i++;
                }
            });
            if (i == 0) {
                alert("请选择要删除的数据！！");
            } else {
                if (confirm("您确认删除？")) {
                    location.assign("user_disabled.shtml?audit=-1&checkedIds=" + checkedIds);
                }
            }
        });
        $("#across").click(function () {
            var checkedIds = new Array();
            var i = 0;
            $(".checkedIds").each(function () {
                if (this.checked == true) {
                    checkedIds[i] = this.value;
                    i++;
                }
            });
            if (i == 0) {
                alert("请选择要启用的数据！！");
            } else {
                if (confirm("您确认启用该用户吗？")) {
                    location.assign("user_disabled.shtml?audit=0&checkedIds=" + checkedIds);
                }
            }
        });
        $("#unacross").click(function () {
            var checkedIds = new Array();
            var i = 0;
            $(".checkedIds").each(function () {
                if (this.checked == true) {
                    checkedIds[i] = this.value;
                    i++;
                }
            });
            if (i == 0) {
                alert("请选择要禁用的数据！！");
            } else {
                if (confirm("您确认禁用该用户吗？")) {
                    location.assign("user_disabled.shtml?audit=1&checkedIds=" + checkedIds);
                }
            }
        });
        $("#newitem").click(function () {
            location.assign("user_create.shtml");
        });
        // $("#tab-user").find("td").click(function () {
        //     var row = $(this).parent().index(); // 行位置
        //     var col = $(this).index(); // 列位置
        //     alert("当前位置：第" + (row + 1) + "行，第" + (col + 1) + "列");
        // });
    })
    ;

    function resetSearch() {
        location.assign("user_list.shtml");
    }

    function updateTable() {
        var userList = new Array();
        <c:forEach items="${page.result}" var="item" varStatus="status">
        userList.put("${item}")
        </c:forEach>
        for (var i = 0; i < userList.length; i++) {
            var user = userList[i];
            if ("${user.userName}" == 'admin' && "${user.realName}" == 'admin' && "${user.identityId}" == 3) {
                var tr = document.getElementById("table-user").getElementsByTagName("tr")[i];
                tr.getElementsByTagName("td")[0].getElementsByTagName("input").visibility = "hidden";
            }
        }
    }
</script>

</body>
</html>