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

                        <form action="report_platform.shtml" method="post" id="mainForm">
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
                            </ul>
                        </form>
                        <br/>

                        <div class="table-responsive">
                            <table class="table table-hover table-striped table-bordered table-condensed table-big">
                                <thead>
                                <tr>
                                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                    <th>渠道</th>
                                    <th>总创角用户<i class="helpToolTip" data-content="平台所有游戏创角用户（游戏统计维度）数的累加"></i></th>
                                    <th>总注册用户<i class="helpToolTip" data-content="平台所有游戏注册用户（游戏统计维度）数的累加"></i></th>
                                    <th>注册设备<i class="helpToolTip" data-content="平台所有游戏注册设备（游戏统计维度）的累加"></i></th>
                                    <th>活跃用户<i class="helpToolTip" data-content="平台所有游戏活跃用户（游戏统计维度）的累加"></i></th>
                                    <th>充值金额<i class="helpToolTip" data-content="平台所有游戏充值总额（游戏统计维度）的累加"></i></th>
                                    <th>充值总次数<i class="helpToolTip" data-content="平台所有游戏充值次数（游戏统计维度）的累加"></i></th>
                                    <th>付费总人数<i class="helpToolTip" data-content="平台所有游戏充值人数（游戏统计维度）的累加"></i></th>
                                    <th>新用户充值人数<i class="helpToolTip" data-content="所选日期内，当天新用户付费总人数"></i></th>
                                    <th>总付费ARPU<i class="helpToolTip" data-content="所有游戏的总充值总额/所有游戏的付费总人数"></i></th>

                                    <th>登录ARPU<i class="helpToolTip" data-content="游戏充值总额/活跃用户"></i></th>
                                    <th>注册ARPU<i class="helpToolTip" data-content="所有游戏的总充值总额/所有游戏的总注册用户"></i></th>

                                    <th>登录付费率<i class="helpToolTip" data-content="当日总充值人数/活跃用户"></i></th>
                                    <th>新用户付费率<i class="helpToolTip" data-content="新用户充值人数/创角用户数"></i></th>
                                </tr>
                                </thead>
                                <tbody>
                                <s:if test="iteratePlatformsMap.size>0  ||  iteratePlatformsMonthlyMap.size>0">
                                    <s:if test="iteratePlatformsMap.size>0  &&  iteratePlatformsMonthlyMap.size==0">
                                        <s:set value="iteratePlatformsMap"  var="valueMap"></s:set>
                                    </s:if>
                                    <s:if test="iteratePlatformsMonthlyMap.size>0  &&  iteratePlatformsMap.size==0">
                                        <s:set value="iteratePlatformsMonthlyMap" var="valueMap"></s:set>
                                    </s:if>
                                    <s:iterator value="valueMap" var="item" >
                                        <s:iterator value="#item.value" var="itemVal" status="st">
                                            <tr>
                                                <c:if test="${st.index == 0 }"><td rowspan=<s:property value="#item.value.size"/>><s:property value="#item.key.appName"/></td></c:if>
                                                <td><s:property value="#itemVal.platformName"/></td>
                                                <td><s:property value="#itemVal.totalRoleUser"/></td>
                                                <td><s:property value="#itemVal.totalRegUser"/></td>
                                                <td><s:property value="#itemVal.devices"/></td>
                                                <td><s:property value="#itemVal.activeUsers"/></td>
                                                <td><fmt:formatNumber value="${itemVal.payAmount/100 }"
                                                                      pattern="0.00"/></td>
                                                <td><s:property value="#itemVal.payTimes"/></td>
                                                <td><s:property value="#itemVal.payUsers"/></td>
                                                <!---->
                                                <td><s:property value="#itemVal.totalNewPayUser"/></td>

                                                <td>
                                                    <c:if test="${itemVal.payUsers==0}">0.00</c:if>
                                                    <c:if test="${itemVal.payUsers>0}">
                                                        <fmt:formatNumber value="${itemVal.payAmount/100/itemVal.payUsers }"
                                                                          pattern="0.00"/>
                                                    </c:if>
                                                </td>

                                                <!---->
                                                <td>
                                                    <c:if test="${itemVal.activeUsers==0}">0.00</c:if>
                                                    <c:if test="${itemVal.activeUsers>0}">
                                                        <fmt:formatNumber value="${itemVal.payAmount/100/itemVal.activeUsers }"
                                                                          pattern="0.00"/>
                                                    </c:if>
                                                </td>

                                                <td>
                                                    <c:if test="${itemVal.totalRegUser==0}">0.00</c:if>
                                                    <c:if test="${itemVal.totalRegUser>0}">
                                                        <fmt:formatNumber
                                                                value="${itemVal.payAmount/100/itemVal.totalRegUser }"
                                                                pattern="0.00"/>
                                                    </c:if>
                                                </td>
                                                <!---->
                                                <td>
                                                    <c:if test="${itemVal.totalRegUser==0}">0.00</c:if>
                                                    <c:if test="${itemVal.totalRegUser>0}">
                                                        <fmt:formatNumber value="${itemVal.payUsers/100/itemVal.totalRegUser }"
                                                                          pattern="0.00"/>
                                                    </c:if>
                                                </td>
                                                <!---->
                                                <td>
                                                    <c:if test="${itemVal.payUsers==0}">0.00</c:if>
                                                    <c:if test="${itemVal.payUsers>0}">
                                                        <fmt:formatNumber value="${itemVal.totalNewPayUser/100/itemVal.payUsers }"
                                                                          pattern="0.00"/>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </s:iterator>
                                    </s:iterator>
                                </s:if>
                                <s:else>
                                    <tr>
                                        <td colspan="15" style="text-align: center">当前列表没有数据！</td>
                                    </tr>
                                </s:else>

                                </tbody>
                            </table>
                        </div>

                        <%--<div class="table-responsive">--%>
                            <%--<table class="table table-hover table-striped table-bordered table-condensed table-big">--%>
                                <%--<thead>--%>
                                <%--<tr>--%>
                                    <%--<th>渠道</th>--%>
                                    <%--<th>总创角用户<i class="helpToolTip" data-content="平台所有游戏创角用户（游戏统计维度）数的累加"></i></th>--%>
                                    <%--<th>总注册用户<i class="helpToolTip" data-content="平台所有游戏注册用户（游戏统计维度）数的累加"></i></th>--%>
                                    <%--<th>注册设备<i class="helpToolTip" data-content="平台所有游戏注册设备（游戏统计维度）的累加"></i></th>--%>
                                    <%--<th>活跃用户<i class="helpToolTip" data-content="平台所有游戏活跃用户（游戏统计维度）的累加"></i></th>--%>
                                    <%--<th>充值金额<i class="helpToolTip" data-content="平台所有游戏充值总额（游戏统计维度）的累加"></i></th>--%>
                                    <%--<th>充值总次数<i class="helpToolTip" data-content="平台所有游戏充值次数（游戏统计维度）的累加"></i></th>--%>
                                    <%--<th>付费总人数<i class="helpToolTip" data-content="平台所有游戏充值人数（游戏统计维度）的累加"></i></th>--%>

                                    <%--<th>新用户充值人数<i class="helpToolTip" data-content="所选日期内，当天新用户付费总人数"></i></th>--%>
                                    <%--<th>总付费ARPU<i class="helpToolTip" data-content="所有游戏的总充值总额/所有游戏的付费总人数"></i></th>--%>

                                    <%--<th>登录ARPU<i class="helpToolTip" data-content="游戏充值总额/活跃用户"></i></th>--%>
                                    <%--<th>注册ARPU<i class="helpToolTip" data-content="所有游戏的总充值总额/所有游戏的总注册用户"></i></th>--%>

                                    <%--<th>登录付费率<i class="helpToolTip" data-content="当日总充值人数/活跃用户"></i></th>--%>
                                    <%--<th>新用户付费率<i class="helpToolTip" data-content="新用户充值人数/创角用户数"></i></th>--%>
                                <%--</tr>--%>
                                <%--</thead>--%>
                                <%--<tbody>--%>
                                <%--<s:if test="sPlatformsPlatform.size>0  || sPlatformMonthliesPlatform.size>0">--%>
                                    <%--<s:if test="sPlatformsPlatform.size>0  &&  sPlatformMonthliesPlatform.size==0">--%>
                                        <%--<s:set value="sPlatformsPlatform" var="valueList"></s:set>--%>
                                    <%--</s:if>--%>
                                    <%--<s:if test="sPlatformMonthliesPlatform.size>0  &&  sPlatformsPlatform.size==0">--%>
                                        <%--<s:set value="sPlatformMonthliesPlatform" var="valueList"></s:set>--%>
                                    <%--</s:if>--%>
                                    <%--<s:iterator value="valueList" var="itemVal">--%>
                                        <%--<tr>--%>
                                            <%--<td><s:property value="#itemVal.platformName"/></td>--%>
                                            <%--<td><s:property value="#itemVal.totalRoleUser"/></td>--%>
                                            <%--<td><s:property value="#itemVal.totalRegUser"/></td>--%>
                                            <%--<td><s:property value="#itemVal.devices"/></td>--%>
                                            <%--<td><s:property value="#itemVal.activeUsers"/></td>--%>
                                            <%--<td><fmt:formatNumber value="${itemVal.payAmount/100 }"--%>
                                                                  <%--pattern="0.00"/></td>--%>
                                            <%--<td><s:property value="#itemVal.payTimes"/></td>--%>
                                            <%--<td><s:property value="#itemVal.payUsers"/></td>--%>
                                            <%--<!---->--%>
                                            <%--<td><s:property value="#itemVal.totalNewPayUser"/></td>--%>

                                            <%--<td>--%>
                                                <%--<c:if test="${itemVal.payUsers==0}">0.00</c:if>--%>
                                                <%--<c:if test="${itemVal.payUsers>0}">--%>
                                                    <%--<fmt:formatNumber value="${itemVal.payAmount/100/itemVal.payUsers }"--%>
                                                                      <%--pattern="0.00"/>--%>
                                                <%--</c:if>--%>
                                            <%--</td>--%>

                                            <%--<!---->--%>
                                            <%--<td>--%>
                                                <%--<c:if test="${itemVal.activeUsers==0}">0.00</c:if>--%>
                                                <%--<c:if test="${itemVal.activeUsers>0}">--%>
                                                    <%--<fmt:formatNumber value="${itemVal.payAmount/100/itemVal.activeUsers }"--%>
                                                                      <%--pattern="0.00"/>--%>
                                                <%--</c:if>--%>
                                            <%--</td>--%>

                                            <%--<td>--%>
                                                <%--<c:if test="${itemVal.totalRegUser==0}">0.00</c:if>--%>
                                                <%--<c:if test="${itemVal.totalRegUser>0}">--%>
                                                    <%--<fmt:formatNumber--%>
                                                            <%--value="${itemVal.payAmount/100/itemVal.totalRegUser }"--%>
                                                            <%--pattern="0.00"/>--%>
                                                <%--</c:if>--%>
                                            <%--</td>--%>
                                            <%--<!---->--%>
                                            <%--<td>--%>
                                                <%--<c:if test="${itemVal.totalRegUser==0}">0.00</c:if>--%>
                                                <%--<c:if test="${itemVal.totalRegUser>0}">--%>
                                                    <%--<fmt:formatNumber value="${itemVal.payUsers/100/itemVal.totalRegUser }"--%>
                                                                      <%--pattern="0.00"/>--%>
                                                <%--</c:if>--%>
                                            <%--</td>--%>
                                            <%--<!---->--%>
                                            <%--<td>--%>
                                                <%--<c:if test="${itemVal.payUsers==0}">0.00</c:if>--%>
                                                <%--<c:if test="${itemVal.payUsers>0}">--%>
                                                    <%--<fmt:formatNumber value="${itemVal.totalNewPayUser/100/itemVal.payUsers }"--%>
                                                                      <%--pattern="0.00"/>--%>
                                                <%--</c:if>--%>
                                            <%--</td>--%>
                                        <%--</tr>--%>
                                    <%--</s:iterator>--%>
                                <%--</s:if>--%>
                                <%--<s:else>--%>
                                    <%--<tr>--%>
                                        <%--<td colspan="15" style="text-align: center;">当前列表没有数据！</td>--%>
                                    <%--</tr>--%>
                                <%--</s:else>--%>
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
            location.assign("report_platform.shtml");
        });
        $("#reportLeft_1").addClass("active");
        $("#platform").addClass("active");

    });
</script>
</body>
</html>