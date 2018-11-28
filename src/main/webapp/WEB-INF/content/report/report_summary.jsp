<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
<div id="wrap">
    <%@ include file="/common/header.jsp" %>
    <div class="container">
        <div class="row">
            <div class="col-md-1 ">
                <%@ include file="/common/reportLeft.jsp" %>
            </div>
            <div class=" col-md-11 ">
                <div class="panel panel-default">
                    <div class="panel-body ">
                        <form action="report_summary.shtml" method="post" id="mainForm">
                            <ul class="nav nav-tabs ">
                                <li id="summary"><a href="/report/report_summary.shtml" identity="tab">平台分析</a></li>
                                <li id="platform"><a href="/report/report_platform.shtml" identity="tab">渠道分析</a></li>
                                <li id="zone"><a href="/report/report_zone.shtml" identity="tab">区服分析</a></li>
                                <li id="reset" identity="tab" style="float: right;">
                                    <div class="form-group width_btn">
                                        <!-- 				          <button  type="submit" class="btn  btn-primary "  id="yearMonthStr"><i class="icon-search"></i> 搜索</button> -->
                                        <button id="resetBtn" type="button" class="btn  btn-default "><i
                                                class="icon-eraser"></i> 重置
                                        </button>
                                    </div>
                                </li>

                                <li id="date" identity="tab" style="float: right;">
                                    <div class="form-group   width_input" style="width: 300px">
                                        <div class="input-group  input-append date form_month "
                                             data-date-format="yyyy-mm" sType="submit">
                                            <input class="form-control " id="yearMonth" type="text" name="yearMonthStr"
                                                   readonly placeholder="历史累计数据" data-toggle="popover"
                                                   data-placement="top" data-content="请选择日期" value="${yearMonthStr}"/>
                                            <span class="add-on input-group-addon"><i class="icon-remove"></i></span>
                                            <span class="add-on input-group-addon"><i class="icon-calendar"></i></span>
                                        </div>
                                    </div>
                                </li>
                                <li id="os" style="float: right;right: 10px">
                                    <div class="form-group width_input" data-toggle="popover" data-placement="top"
                                         data-content="请选择平台">
                                        <select class="form-control" name="clientType" id="clientType"
                                                onchange="select()"
                                                placeholder="请选择平台">
                                            <option value="">==请选择平台==</option>
                                            <option value="1">Android</option>
                                            <option value="2">IOS</option>
                                        </select>
                                    </div>
                                </li>
                            </ul>
                        </form>
                        <br/>

                        <div class="table-responsive">
                            <table id="table-os"
                                   class="table table-hover table-striped table-bordered table-condensed table-big">
                                <thead>
                                <tr>
                                    <th colspan="2"></th>
                                    <th>合作模式</th>
                                    <th>创角用户<i class="helpToolTip" data-content="玩家激活游戏后，进行了注册并创建了游戏角色的账户数量"></i></th>
                                    <th>注册用户<i class="helpToolTip" data-content="玩家激活游戏后，进行了注册有ID信息或者账户信息的玩家账户数量"></i>
                                    </th>
                                    <th>注册设备<i class="helpToolTip" data-content="玩家激活游戏后，进行了注册有ID信息或者账户信息的玩家设备数量"></i>
                                    </th>
                                    <th>活跃用户<i class="helpToolTip" data-content="一个时间段内最近7天有3天登录的用户（账号唯一）总量"></i></th>
                                    <th>充值金额<i class="helpToolTip" data-content="单个游戏的总收入金额"></i></th>
                                    <th>充值次数<i class="helpToolTip" data-content="所选时期内，单个游戏所有玩家成功充值总次数"></i></th>
                                    <th>新用户充值人数<i class="helpToolTip" data-content="所选日期内，当天新用户付费总人数"></i></th>
                                    <th>付费人数<i class="helpToolTip" data-content="每日成功充值的用户（账号唯一）数量，去重"></i></th>
                                    <th>付费ARPU<i class="helpToolTip" data-content="游戏充值总额/游戏付费人数"></i></th>
                                    <th>登录ARPU<i class="helpToolTip" data-content="游戏充值总额/活跃用户"></i></th>
                                    <th>注册ARPU<i class="helpToolTip" data-content="游戏充值总额/游戏注册用户"></i></th>
                                    <th>登录付费率<i class="helpToolTip" data-content="当日总充值人数/活跃用户"></i></th>
                                    <th>新用户付费率<i class="helpToolTip" data-content="新用户充值人数/创角用户数"></i></th>
                                </tr>
                                </thead>
                                <tbody>

                                <s:if test="gameClientReports.size>0  ||  gameClientMonthlyReports.size>0">
                                    <s:if test="gameClientReports.size>0  &&  gameClientMonthlyReports.size==0">
                                        <s:set value="gameClientReports" var="valueList"></s:set>
                                    </s:if>

                                    <s:if test="gameClientMonthlyReports.size>0  &&  gameClientReports.size==0">
                                        <s:set value="gameClientMonthlyReports" var="valueList"></s:set>
                                    </s:if>

                                    <s:iterator value="valueList" var="item">
                                        <tr>
                                            <td rowspan='6'>${item.gameName}</td>
                                        </tr>
                                        <tr>
                                            <td rowspan='2' id="android">安卓</td>
                                            <td>CPS</td>
                                            <td>${item.androidCps.totalRoleUser}</td>
                                            <td>${item.androidCps.totalRegUser }</td>
                                            <td>${item.androidCps.devices}</td>
                                            <td>${item.androidCps.activeUsers}</td>
                                            <td><fmt:formatNumber value="${item.androidCps.payAmount/100 }"
                                                                  pattern="0.00"/></td>
                                            <td>${item.androidCps.payTimes }</td>
                                            <!--新用户充值人数-->
                                            <td>${item.androidCps.totalNewPayUser}</td>
                                            <td>${item.androidCps.payUsers}</td>
                                            <td>
                                                    <c:if test="${item.androidCps.payUsers== 0 }">0.00</c:if>
                                                    <c:if test="${item.androidCps.payUsers> 0 }"><fmt:formatNumber
                                                    value="${(item.androidCps.payAmount )/100/(item.androidCps.payUsers ) }"
                                                    pattern="0.00"/></c:if>

                                            </td>
                                            <!--登录ARPU-->
                                            <td>
                                                    <c:if test="${item.androidCps.activeUsers== 0 }">0.00</c:if>
                                                    <c:if test="${item.androidCps.activeUsers> 0 }">
                                                    <fmt:formatNumber
                                                    value="${(item.androidCps.payAmount)/100/(item.androidCps.activeUsers) }"
                                                    pattern="0.00"/></c:if>
                                            </td>
                                            <td>
                                                    <c:if test="${item.androidCps.totalRegUser== 0 }">0.00</c:if>
                                                    <c:if test="${item.androidCps.totalRegUser> 0 }">
                                                    <fmt:formatNumber
                                                    value="${(item.androidCps.payAmount )/100/(item.androidCps.totalRegUser) }"
                                                    pattern="0.00"/></c:if>
                                            </td>
                                            <!--登录付费率-->
                                            <td>
                                                <c:if test="${item.androidCps.activeUsers== 0 }">0.00</c:if>
                                                <c:if test="${item.androidCps.activeUsers> 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.androidCps.payUsers)/100/(item.androidCps.activeUsers) }"
                                                            pattern="0.00"/></c:if>
                                            </td>
                                            <!--新用户付费率-->
                                            <td>
                                                <c:if test="${item.androidCps.totalRoleUser== 0 }">0.00</c:if>
                                                <c:if test="${item.androidCps.totalRoleUser> 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.androidCps.totalNewPayUser)/100/(item.androidCps.totalRoleUser) }"
                                                            pattern="0.00"/>
                                                </c:if>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>CPA</td>
                                            <td>${item.androidCpa.totalRoleUser }</td>
                                            <td>${item.androidCpa.totalRegUser }</td>
                                            <td>${item.androidCpa.devices }</td>
                                            <td>${item.androidCpa.activeUsers}</td>
                                            <td><fmt:formatNumber value="${item.androidCpa.payAmount/100 }"
                                                                  pattern="0.00"/></td>
                                            <td>${item.androidCpa.payTimes }</td>
                                            <!--新用户充值人数-->
                                            <td>${item.androidCpa.totalNewPayUser}</td>
                                            <td>${item.androidCpa.payUsers }</td>
                                            <td>
                                                <c:if test="${item.androidCpa.payUsers==0 }">0.00</c:if>
                                                <c:if test="${item.androidCpa.payUsers> 0 }"><fmt:formatNumber
                                                        value="${item.androidCpa.payAmount/100/item.androidCpa.payUsers }"
                                                        pattern="0.00"/></c:if>
                                            </td>
                                            <td>
                                                <c:if test="${item.androidCpa.activeUsers== 0 }">0.00</c:if>
                                                <c:if test="${item.androidCpa.activeUsers> 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.androidCpa.payAmount)/100/(item.androidCpa.activeUsers) }"
                                                            pattern="0.00"/>
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test="${item.androidCpa.totalRegUser == 0 }">0.00</c:if>
                                                <c:if test="${item.androidCpa.totalRegUser> 0 }"><fmt:formatNumber
                                                        value="${item.androidCpa.payAmount/100/item.androidCpa.totalRegUser }"
                                                        pattern="0.00"/></c:if>
                                            </td>
                                            <td>
                                                <c:if test="${item.androidCpa.activeUsers== 0 }">0.00</c:if>
                                                <c:if test="${item.androidCpa.activeUsers> 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.androidCpa.payUsers)/100/(item.androidCpa.activeUsers) }"
                                                            pattern="0.00"/>
                                                </c:if>
                                            </td>
                                            <!--新用户付费率-->
                                            <td>
                                                <c:if test="${item.androidCpa.totalRoleUser== 0 }">0.00</c:if>
                                                <c:if test="${item.androidCpa.totalRoleUser> 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.androidCpa.totalNewPayUser)/100/(item.androidCpa.totalRoleUser) }"
                                                            pattern="0.00"/>
                                                </c:if>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td rowspan='2'>苹果</td>
                                            <td>CPS</td>
                                            <td>${item.iosCps.totalRoleUser}</td>
                                            <td>${item.iosCps.totalRegUser }</td>
                                            <td>${item.iosCps.devices}</td>
                                            <td>${item.iosCps.activeUsers}</td>
                                            <td><fmt:formatNumber value="${item.iosCps.payAmount/100 }"
                                                                  pattern="0.00"/></td>
                                            <td>${item.iosCps.payTimes }</td>
                                            <!--新用户充值人数-->
                                            <td>${item.iosCps.totalNewPayUser}</td>
                                            <td>${item.iosCps.payUsers }</td>
                                            <td>
                                                <c:if test="${item.iosCps.payUsers ==0 }">0.00</c:if>
                                                <c:if test="${item.iosCps.payUsers > 0 }"><fmt:formatNumber
                                                        value="${(item.iosCps.payAmount )/100/(item.iosCps.payUsers ) }"
                                                        pattern="0.00"/></c:if>
                                            </td>
                                            <td>
                                                <c:if test="${item.iosCps.activeUsers== 0 }">0.00</c:if>
                                                <c:if test="${item.iosCps.activeUsers> 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.iosCps.payAmount)/100/(item.iosCps.activeUsers) }"
                                                            pattern="0.00"/>
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test="${item.iosCps.totalRegUser ==0 }">0.00</c:if>
                                                <c:if test="${item.iosCps.totalRegUser >0 }"><fmt:formatNumber
                                                        value="${(item.iosCps.payAmount )/100/(item.iosCps.totalRegUser ) }"
                                                        pattern="0.00"/></c:if>
                                            </td>
                                            <!--登录付费率-->
                                            <td>
                                                <c:if test="${item.iosCps.activeUsers== 0 }">0.00</c:if>
                                                <c:if test="${item.iosCps.activeUsers> 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.iosCps.payUsers)/100/(item.iosCps.activeUsers) }"
                                                            pattern="0.00"/>
                                                </c:if>
                                            </td>
                                            <!--新用户付费率-->
                                            <td>
                                                <c:if test="${item.iosCps.totalRoleUser== 0 }">0.00</c:if>
                                                <c:if test="${item.iosCps.totalRoleUser> 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.iosCps.totalNewPayUser)/100/(item.iosCps.totalRoleUser) }"
                                                            pattern="0.00"/>
                                                </c:if>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>CPA</td>
                                            <td>${item.iosCpa.totalRoleUser }</td>
                                            <td>${item.iosCpa.totalRegUser }</td>
                                            <td>${item.iosCpa.devices }</td>
                                            <td>${item.iosCpa.activeUsers}</td>
                                            <td><fmt:formatNumber value="${item.iosCpa.payAmount/100 }"
                                                                  pattern="0.00"/></td>
                                            <td>${item.iosCpa.payTimes }</td>
                                            <!--新用户充值人数-->
                                            <td>${item.iosCpa.totalNewPayUser}</td>
                                            <td>${item.iosCpa.payUsers }</td>
                                            <td>
                                                <c:if test="${item.iosCpa.payUsers == 0 }">0.00</c:if>
                                                <c:if test="${item.iosCpa.payUsers > 0 }"><fmt:formatNumber
                                                        value="${item.iosCpa.payAmount/100/item.iosCpa.payUsers }"
                                                        pattern="0.00"/></c:if>
                                            </td>
                                            <td>
                                                <c:if test="${item.iosCpa.activeUsers== 0 }">0.00</c:if>
                                                <c:if test="${item.iosCpa.activeUsers> 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.iosCpa.payAmount)/100/(item.iosCpa.activeUsers) }"
                                                            pattern="0.00"/>
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test="${item.iosCpa.totalRegUser == 0 }">0.00</c:if>
                                                <c:if test="${item.iosCpa.totalRegUser > 0 }"><fmt:formatNumber
                                                        value="${item.iosCpa.payAmount/100/item.iosCpa.totalRegUser }"
                                                        pattern="0.00"/></c:if>
                                            </td>
                                            <td>
                                                <c:if test="${item.iosCpa.activeUsers== 0 }">0.00</c:if>
                                                <c:if test="${item.iosCpa.activeUsers> 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.iosCpa.payUsers)/100/(item.iosCpa.activeUsers) }"
                                                            pattern="0.00"/>
                                                </c:if>
                                            </td>
                                            <!--新用户付费率-->
                                            <td>
                                                <c:if test="${item.iosCpa.totalRoleUser== 0 }">0.00</c:if>
                                                <c:if test="${item.iosCpa.totalRoleUser> 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.iosCpa.totalNewPayUser)/100/(item.iosCpa.totalRoleUser) }"
                                                            pattern="0.00"/>
                                                </c:if>
                                            </td>
                                        </tr>

                                        <tr>
                                                <%--<td>&nbsp;</td>--%>
                                            <td colspan="2" style="text-align: center;"><b>合计</b></td>
                                            <td>${item.iosCps.totalRoleUser + item.androidCps.totalRoleUser+item.iosCpa.totalRoleUser+item.androidCpa.totalRoleUser}</td>
                                            <td>${item.iosCps.totalRegUser +  item.androidCps.totalRegUser+item.iosCpa.totalRegUser +  item.androidCpa.totalRegUser}</td>
                                            <td>${item.iosCps.devices + item.androidCps.devices+item.iosCpa.devices + item.androidCpa.devices}</td>
                                            <td>${item.iosCps.activeUsers + item.androidCps.activeUsers+item.iosCpa.activeUsers + item.androidCpa.activeUsers}</td>
                                            <td><fmt:formatNumber
                                                    value="${(item.iosCps.payAmount + item.androidCps.payAmount+item.iosCpa.payAmount + item.androidCpa.payAmount)/100 }"
                                                    pattern="0.00"/></td>
                                            <td>${item.iosCps.payTimes + item.androidCps.payTimes+item.iosCpa.payTimes + item.androidCpa.payTimes }</td>
                                            <!--新用户充值人数-->
                                            <td>${item.androidCps.totalNewPayUser + item.androidCpa.totalNewPayUser+item.iosCps.totalNewPayUser + item.iosCpa.totalNewPayUser}</td>
                                            <td>${item.iosCps.payUsers +  item.androidCps.payUsers+item.iosCpa.payUsers +  item.androidCpa.payUsers}</td>
                                            <td>
                                                <c:if test="${item.iosCps.payUsers +  item.androidCps.payUsers+item.iosCpa.payUsers +  item.androidCpa.payUsers == 0 }">0.00</c:if>
                                                <c:if test="${item.iosCps.payUsers +  item.androidCps.payUsers+item.iosCpa.payUsers +  item.androidCpa.payUsers > 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.iosCps.payAmount + item.androidCps.payAmount+item.iosCpa.payAmount + item.androidCpa.payAmount)/100/(item.iosCps.payUsers +  item.androidCps.payUsers+item.iosCpa.payUsers +  item.androidCpa.payUsers) }"
                                                            pattern="0.00"/></c:if>
                                            </td>
                                            <td>
                                                <c:if test="${item.iosCps.activeUsers +  item.androidCps.activeUsers == 0 }">0.00</c:if>
                                                <c:if test="${item.iosCps.activeUsers +  item.androidCps.activeUsers > 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.iosCps.payAmount + item.androidCps.payAmount+item.iosCpa.payAmount + item.androidCpa.payAmount)/100/(item.iosCps.activeUsers +  item.androidCps.activeUsers+item.iosCpa.activeUsers +  item.androidCpa.activeUsers) }"
                                                            pattern="0.00"/>
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test="${item.iosCps.totalRegUser +  item.androidCps.totalRegUser == 0 }">0.00</c:if>
                                                <c:if test="${item.iosCps.totalRegUser +  item.androidCps.totalRegUser > 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.iosCps.payAmount + item.androidCps.payAmount+item.iosCpa.payAmount + item.androidCpa.payAmount)/100/(item.iosCps.totalRegUser +  item.androidCps.totalRegUser+item.iosCpa.totalRegUser +  item.androidCpa.totalRegUser) }"
                                                            pattern="0.00"/></c:if>
                                            </td>
                                            <td>
                                                <c:if test="${item.iosCps.activeUsers +  item.androidCps.activeUsers == 0 }">0.00</c:if>
                                                <c:if test="${item.iosCps.activeUsers +  item.androidCps.activeUsers > 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.iosCps.payUsers + item.androidCps.payUsers+item.iosCpa.payUsers + item.androidCpa.payUsers)/100/(item.iosCps.activeUsers +  item.androidCps.activeUsers+item.iosCpa.activeUsers +  item.androidCpa.activeUsers) }"
                                                            pattern="0.00"/>
                                                </c:if>
                                            </td>
                                            <!--新用户付费率-->
                                            <td>
                                                <c:if test="${item.iosCps.totalRoleUser +  item.androidCps.totalRoleUser == 0 }">0.00</c:if>
                                                <c:if test="${item.iosCps.totalRoleUser +  item.androidCps.totalRoleUser > 0 }">
                                                    <fmt:formatNumber
                                                            value="${(item.iosCps.totalNewPayUser + item.androidCps.totalNewPayUser+item.iosCpa.totalNewPayUser + item.androidCpa.totalNewPayUser)/100/(item.iosCps.totalRoleUser +  item.androidCps.totalRoleUser+item.iosCpa.totalRoleUser +  item.androidCpa.totalRoleUser) }"
                                                            pattern="0.00"/>
                                                </c:if>
                                            </td>
                                        </tr>

                                    </s:iterator>
                                </s:if>
                                <s:else>
                                    <tr>
                                        <td colspan="15" style="text-align: center;">当前列表没有数据！</td>
                                    </tr>
                                </s:else>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#resetBtn").click(function () {
            location.assign("report_summary.shtml");
        });
        $("#reportLeft_1").addClass("active");
        $("#summary").addClass("active");
        // updateTable();
    });

    function select() {
        //每个游戏占的行数
        var minGameRows = 6
        var android = false
        var ios = false
        if (document.getElementById("clientType").value == "1") {
            android = true
            ios = false
        } else if (document.getElementById("clientType").value == "2") {
            android = false
            ios = true
        } else {
            android = true
            ios = true
        }
        var tab = document.getElementById("table-os");
        var trs = tab.getElementsByTagName("tr");
        for (var i = 1; i < trs.length; i++) {
            var tr = trs[i]
            var row = i;
            if (row < minGameRows)
                row = row + minGameRows
            if (row % minGameRows == 0) {//合计
                setStyle(tr, android && ios)
            } else if (row % minGameRows == 1) {
                var tds = tr.getElementsByTagName("td")
                if (android && ios)
                    tds[0].rowSpan = minGameRows
                else
                    tds[0].rowSpan = minGameRows / 2
            } else {
                if (row % minGameRows < minGameRows - 2) {//android
                    setStyle(tr, android)
                } else {
                    setStyle(tr, ios)
                }
            }
        }
    }

    function setStyle(tr, show) {
        if (show)
            tr.style.display = "";
        else
            tr.style.display = "none";
    }

    function updateTable() {
        var minGameRows = 6
        var tab = document.getElementById("table-os");
        var trs = tab.getElementsByTagName("tr");
        for (var i = 1; i < trs.length; i++) {
            var tr = trs[i];
            var row = i;
            var tds = tr.getElementsByTagName("td");
            if (row < minGameRows)
                row = row + minGameRows

            var item
            if (row % minGameRows == 0) {//合计

            } else if (row % minGameRows == 1) {

            } else {
                var payARPU = 10;
                var isAndroid = false;
                var isCPA = false;
                if ((row % minGameRows) % 2 == 1) {
                    payARPU = payARPU - 1
                    isCPA = true;
                }
                if (row % minGameRows < minGameRows - 2) {
                    isAndroid = true;
                }
                for (var j = payARPU; j < tds.length; j++) {
                    var td = tds[j];
                    setColumn(td, i, j, payARPU, isAndroid, isCPA);
                }
            }
        }
    }

    function setColumn(td, row, column, payARPU, android, cpa) {
        var payAmount;
        var payUsers;
        var regUsers;
        var activeUsers;
        var totalNewPayUser;
        var totalRoleUser;

        if (android && cpa) {
            payAmount =${valueList[row / minGameRows].androidCpa.payAmount/100};
            payUsers =${valueList[row / minGameRows].androidCpa.payUsers};
            activeUsers =${valueList[row / minGameRows].androidCpa.activeUsers};
            regUsers =${valueList[row / minGameRows].androidCpa.totalRegUser};
            totalNewPayUser =${valueList[row / minGameRows].androidCpa.totalNewPayUser};
            totalRoleUser =${valueList[row / minGameRows].androidCpa.totalRoleUser};
            // td.innerText = "Android CPA";
        } else if (android && !cpa) {
            payAmount =${valueList[row / minGameRows].androidCps.payAmount/100};
            payUsers =${valueList[row / minGameRows].androidCps.payUsers};
            activeUsers =${valueList[row / minGameRows].androidCps.activeUsers};
            regUsers =${valueList[row / minGameRows].androidCps.totalRegUser};
            totalNewPayUser =${valueList[row / minGameRows].androidCps.totalNewPayUser};
            totalRoleUser =${valueList[row / minGameRows].androidCps.totalRoleUser};
        } else if (!android && cpa) {
            payAmount =${valueList[row / minGameRows].iosCpa.payAmount/100};
            payUsers =${valueList[row / minGameRows].iosCpa.payUsers};
            activeUsers =${valueList[row / minGameRows].iosCpa.activeUsers};
            regUsers =${valueList[row / minGameRows].iosCpa.totalRegUser};
            totalNewPayUser =${valueList[row / minGameRows].iosCpa.totalNewPayUser};
            totalRoleUser =${valueList[row / minGameRows].iosCpa.totalRoleUser};
            // td.innerText = "ios CPA";
        } else if (!android && !cpa) {
            payAmount =${valueList[row / minGameRows].iosCps.payAmount/100};
            payUsers =${valueList[row / minGameRows].iosCps.payUsers};
            activeUsers =${valueList[row / minGameRows].iosCps.activeUsers};
            regUsers =${valueList[row / minGameRows].iosCps.totalRegUser};
            totalNewPayUser =${valueList[row / minGameRows].iosCps.totalNewPayUser};
            totalRoleUser =${valueList[row / minGameRows].iosCps.totalRoleUser};
        }

        if (column == payARPU) {//付费ARPU
            setTableContent(td, payAmount, payUsers, "0.00")
        } else if (column == payARPU + 1) {//登录ARPU
            setTableContent(td, payAmount, activeUsers, "0.00")
        } else if (column == payARPU + 2) {//注册ARPU
            setTableContent(td, payAmount, regUsers, "0.00")
        } else if (column == payARPU + 3) {//登录付费率
            setTableContent(td, payUsers, activeUsers, "0.00")
        } else if (column == payARPU + 4) {//新用户付费率
            setTableContent(td, totalNewPayUser, totalRoleUser, "0.00")
        }
    }

    function setTableContent(element, dividend, divisor, def) {
        if (divisor == 0) {
            element.innerText = def;
        } else {
            var result = dividend / divisor;
            element.innerText = result;
        }
    }
</script>

</body>
</html>