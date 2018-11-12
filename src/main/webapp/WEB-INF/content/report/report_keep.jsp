<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<jsp:useBean id="nowDate" class="java.util.Date"/>
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
                    <form action="reportDaily_keep.shtml" method="post" id="mainForm">
                        <input type="hidden" id="keepType" value="${keepStatType}"/>
                        <div class="form-inline popover-show panel-body list_toolbar">
                            <div class="form-group width_input" data-toggle="popover" data-placement="top">
                                <select class="form-control" name="keepStatType" id="keepStatType"
                                        onchange="selectKeepType()">
                                    <option value="0" <c:if test='${keepStatType == "0"}'>selected</c:if>>新增留存</option>
                                    <option value="1" <c:if test='${keepStatType == "1"}'>selected</c:if>>活跃留存</option>
                                    <option value="2" <c:if test='${keepStatType == "2"}'>selected</c:if>>付费留存</option>
                                </select>

                            </div>
                            <div class="form-group width_input" data-toggle="popover" data-placement="top"
                                 data-content="请选择游戏">
                                <input type="hidden" id="type" name="groupType" value="${groupType }">
                                <input type="hidden" id="compareType" name="compareGroupType"
                                       value="${compareGroupType }">
                                <input type="hidden" id="channelIds" name="channelIds" value="${channelIds }">
                                <input type="hidden" id="compareChannelIds" name="compareChannelIds"
                                       value="${compareChannelIds }">
                                <input type="hidden" id="zoneIds" name="zoneIds" value="${zoneIds }">
                                <input type="hidden" id="compareZoneIds" name="compareZoneIds"
                                       value="${compareZoneIds }">
                                <select class="form-control" name="appId" id="appId" placeholder="请选择游戏">
                                    <s:iterator value="allGames" var="item">
                                        <option value="${item.id }"
                                                <c:if test="${item.id==appId }">selected</c:if>>${item.appName }</option>
                                    </s:iterator>
                                </select>
                            </div>
                            <div class="form-group width_input" data-toggle="popover" data-placement="top"
                                 data-content="请选择客户端">
                                <mt:selectState name="clientType" showType="select" stateType="clientType"
                                                value="${clientType}" clazz="form-control" emptyString="IOS+ANDROID"/>
                            </div>
                            <button type="button" class="btn " onclick="selectChannelZone();"> 渠道、区服</button>
                            <button type="button" class="btn " onclick="selectCompareChannelZone();"> 对比渠道、区服</button>
                            <div class="form-group    width_input" data-toggle="popover" data-placement="top"
                                 data-content="时间">
                                <input class="form-control daterange" type="text" readonly name="selectRange"
                                       value="${selectRange }" placeholder="时间选择" sType="searchAndValid"/>
                            </div>
                            <div class="form-group width_btn">
                                <button type="button" class="btn  btn-primary " onclick="searchAndValid();"><i
                                        class="icon-search"></i> 搜索
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading ">
                        <h3 class="panel-title">
                            <!-- 渠道 -->
                            <c:if test="${groupType == 1 }">
                                <c:if test="${not empty channelIds}">
                                    <s:if test="result['platforms'].size>0">
                                        <s:iterator value="result['platforms']" var="item">
                                            ${platformName }&nbsp;&nbsp;
                                        </s:iterator>
                                    </s:if>
                                </c:if>
                                <c:if test="${empty channelIds && not empty zoneIds }">
                                    <s:if test="result['gamezones'].size>0">
                                        <s:iterator value="result['gamezones']" var="item">
                                            ${zoneName }&nbsp;&nbsp;
                                        </s:iterator>
                                    </s:if>
                                    所有渠道&nbsp;&nbsp;
                                </c:if>

                                <c:if test="${not empty compareChannelIds}"><br>对比<br>
                                    <s:if test="result['comparePlatforms'].size>0">
                                        <s:iterator value="result['comparePlatforms']" var="item">
                                            ${platformName }&nbsp;&nbsp;
                                        </s:iterator>
                                    </s:if>
                                </c:if>
                                <c:if test="${empty compareChannelIds &&  not empty  compareZoneIds}">
                                    <br>对比<br>
                                    <s:if test="result['compareGamezones'].size>0">
                                        <s:iterator value="result['compareGamezones']" var="item">
                                            ${zoneName }&nbsp;&nbsp;
                                        </s:iterator>
                                    </s:if>
                                    所有比较渠道
                                </c:if>
                            </c:if>
                            <c:if test="${groupType == 2 }">
                                <c:if test="${not empty zoneIds}">
                                    <s:if test="result['gamezones'].size>0">
                                        <s:iterator value="result['gamezones']" var="item">
                                            ${zoneName }&nbsp;&nbsp;
                                        </s:iterator>
                                    </s:if>
                                </c:if>
                                <c:if test="${empty zoneIds && not empty channelIds}">
                                    所有分区&nbsp;&nbsp;
                                </c:if>

                                <c:if test="${not empty compareZoneIds}">
                                    <br>对比<br>
                                    <s:if test="result['compareGamezones'].size>0">
                                        <s:iterator value="result['compareGamezones']" var="item">
                                            ${zoneName }&nbsp;&nbsp;
                                        </s:iterator>
                                    </s:if>
                                </c:if>
                                <c:if test="${empty compareZoneIds &&  not empty compareChannelIds}">
                                    所有比较分区&nbsp;&nbsp;
                                </c:if>
                            </c:if>
                            <c:if test="${groupType != 1 && groupType != 2}">
                                所有渠道、所有分区
                            </c:if>
                        </h3>
                    </div>
                    <div class="panel-body ">
                        <div class="table-responsive">
                            <fmt:formatDate var="nowStr" value="${nowDate}" pattern="yyyy-MM-dd"/>
                            <fmt:parseDate var="nowDate" value="${nowStr}" pattern="yyyy-MM-dd"/>
                            <table class="table table-hover table-striped table-bordered table-condensed table-big">
                                <thead>
                                <tr>
                                    <th>日期</th>
                                    <c:if test="${result['type'] == 2}">
                                        <th>明细</th>
                                    </c:if>
                                    <c:if test="${result['type'] == 1}">
                                        <th>对比</th>
                                    </c:if>
                                    <c:if test='${keepStatType =="0"}'>
                                        <s:set var="userType" name="userType" value="'新增'"/>
                                        <th><b>新增创角用户 </b> <i class="helpToolTip"
                                                              data-content="统计所选时期内，每日玩家激活游戏后，进行了注册并创建了游戏角色的账户数量"></i>
                                        </th>
                                    </c:if>
                                    <c:if test='${keepStatType =="1"}'>
                                        <s:set var="userType" name="userType" value="'活跃'"/>
                                        <th><b>活跃用户数 </b> <i class="helpToolTip"
                                                             data-content="统计时间内的活跃用户，在统计日之后第N天有登录行为的用户比率"></i>
                                        </th>
                                    </c:if>
                                    <c:if test='${keepStatType =="2"}'>
                                        <s:set var="userType" name="userType" value="'付费'"/>
                                        <th><b>付费用户数 </b> <i class="helpToolTip"
                                                             data-content="统计时间内的付费用户，在统计日之后第N天有登录行为的用户比率"></i>
                                        </th>
                                    </c:if>
                                    <th><b>次日留存 </b> <i class="helpToolTip"
                                                        data-content="次日登录玩家数/当日<s:property value="userType"/>玩家"></i>
                                    </th>
                                    <th><b>三日留存 </b> <i class="helpToolTip" data-content="第三日登录玩家数/当日<s:property value="userType"/>玩家"></i></th>
                                    <th><b>四日留存 </b> <i class="helpToolTip" data-content="第四日登录玩家数/当日<s:property value="userType"/>玩家"></i></th>
                                    <th><b>五日留存 </b> <i class="helpToolTip" data-content="第五日登录玩家数/当日<s:property value="userType"/>玩家"></i></th>
                                    <th><b>六日留存 </b> <i class="helpToolTip" data-content="第六日登录玩家数/当日<s:property value="userType"/>玩家"></i></th>
                                    <th><b>七日留存 </b> <i class="helpToolTip" data-content="第七日登录玩家数/当日<s:property value="userType"/>玩家"></i></th>
                                    <th><b>十四日留存 </b> <i class="helpToolTip" data-content="第十四日登录玩家数/当日<s:property value="userType"/>玩家"></i></th>
                                    <th><b>三十日留存 </b> <i class="helpToolTip" data-content="第三十日登录玩家数/当日<s:property value="userType"/>玩家"></i></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${result['type'] == 2}">
                                    <s:if test="result['data'].size > 0">
                                        <s:iterator value="result['data']" var="item">
                                            <s:set var="allUsers" value="0"></s:set>
                                            <s:set var="allkeepUser1" value="0"></s:set>
                                            <s:set var="allkeepUser3" value="0"></s:set>
                                            <s:set var="allkeepUser4" value="0"></s:set>
                                            <s:set var="allkeepUser5" value="0"></s:set>
                                            <s:set var="allkeepUser6" value="0"></s:set>
                                            <s:set var="allkeepUser7" value="0"></s:set>
                                            <s:set var="allkeepUser14" value="0"></s:set>
                                            <s:set var="allkeepUser30" value="0"></s:set>

                                            <s:set var="itemSize" value="#item.value.size"></s:set>

                                            <s:set var="itemKey" value="#item.key"></s:set>
                                            <s:iterator value="#item.value" var="itemVal" status="st">
                                                <c:if test='${keepStatType == "0"}'>
                                                    <s:set var="user" value="#itemVal.roleUsers"/>
                                                </c:if>
                                                <c:if test='${keepStatType == "1"}'>
                                                    <s:set var="user" value="#itemVal.activeUsers"/>
                                                </c:if>
                                                <c:if test='${keepStatType == "2"}'>
                                                    <s:set var="user" value="#itemVal.payUsers"/>
                                                </c:if>
                                                <fmt:parseDate var="someDate" value="${itemKey}" pattern="yyyy-MM-dd"/>
                                                <s:set var="allUsers"
                                                       value="#allUsers + #user"></s:set>
                                                <s:set var="allkeepUser1"
                                                       value="#allkeepUser1 + #itemVal.keepUser1"></s:set>
                                                <s:set var="allkeepUser3"
                                                       value="#allkeepUser3 + #itemVal.keepUser3"></s:set>
                                                <s:set var="allkeepUser3"
                                                       value="#allkeepUser4 + #itemVal.keepUser4"></s:set>
                                                <s:set var="allkeepUser3"
                                                       value="#allkeepUser5 + #itemVal.keepUser5"></s:set>
                                                <s:set var="allkeepUser3"
                                                       value="#allkeepUser6 + #itemVal.keepUser6"></s:set>
                                                <s:set var="allkeepUser7"
                                                       value="#allkeepUser7 + #itemVal.keepUser7"></s:set>
                                                <s:set var="allkeepUser14"
                                                       value="#allkeepUser14 + #itemVal.keepUser14"></s:set>
                                                <s:set var="allkeepUser30"
                                                       value="#allkeepUser30 + #itemVal.keepUser30"></s:set>

                                                <tr>
                                                    <c:if test="${st.index == 0 }">
                                                        <s:if test="#item.value.size >1">
                                                            <td rowspan=<s:property value="#item.value.size+1"/>>
                                                                <s:property value="#item.key"/></td>
                                                        </s:if>
                                                        <s:else>
                                                            <td rowspan=<s:property value="#item.value.size"/>>
                                                                <s:property value="#item.key"/></td>
                                                        </s:else>
                                                    </c:if>
                                                    <c:if test="${result['group'] == 'game'}">
                                                        <td><s:property value="#itemVal.appName"/></td>
                                                    </c:if>
                                                    <c:if test="${result['group'] == 'platform'}">
                                                        <td><s:property value="#itemVal.platformName"/></td>
                                                    </c:if>
                                                    <c:if test="${result['group'] == 'zone'}">
                                                        <td><s:property value="#itemVal.zoneName"/></td>
                                                    </c:if>
                                                    <td><s:property value="#user"/></td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 1 }"><s:property
                                                                value="#itemVal.keepUser1"/>&nbsp;&nbsp;
                                                            <font color="red">
                                                                <c:if test="${user==0}">0.00%</c:if>
                                                                <c:if test="${user>0}">
                                                                    <fmt:formatNumber
                                                                            value="${itemVal.keepUser1/user }"
                                                                            type="percent" minFractionDigits="2"
                                                                            maxFractionDigits="2"/></c:if>
                                                            </font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 1 }">--</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 2 }"><s:property
                                                                value="#itemVal.keepUser3"/>&nbsp;&nbsp;<font color="red"><c:if
                                                                test="${user==0}">0.00%</c:if>
                                                            <c:if test="${user>0}"><fmt:formatNumber
                                                                    value="${itemVal.keepUser3/user }"
                                                                    minFractionDigits="2" type="percent"/></c:if></font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 2 }">--</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 3 }"><s:property
                                                                value="#itemVal.keepUser4"/>&nbsp;&nbsp;<font color="red"><c:if
                                                                test="${user==0}">0.00%</c:if>
                                                            <c:if test="${user>0}"><fmt:formatNumber
                                                                    value="${itemVal.keepUser4/user }"
                                                                    type="percent" pattern="0.00" minFractionDigits="2"
                                                                    maxFractionDigits="2"/></c:if></font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 3 }">--</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 4 }"><s:property
                                                                value="#itemVal.keepUser5"/>&nbsp;&nbsp;<font color="red"><c:if
                                                                test="${user==0}">0.00%</c:if>
                                                            <c:if test="${user>0}"><fmt:formatNumber
                                                                    value="${itemVal.keepUser5/user }"
                                                                    type="percent" pattern="0.00" minFractionDigits="2"
                                                                    maxFractionDigits="2"/></c:if></font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 4 }">--</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 5 }"><s:property
                                                                value="#itemVal.keepUser6"/>&nbsp;&nbsp;<font color="red"><c:if
                                                                test="${user==0}">0.00%</c:if>
                                                            <c:if test="${user>0}"><fmt:formatNumber
                                                                    value="${itemVal.keepUser6/user }"
                                                                    type="percent" pattern="0.00"
                                                                    maxFractionDigits="2"/></c:if></font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 5 }">--</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 6 }"><s:property
                                                                value="#itemVal.keepUser7"/>&nbsp;&nbsp;<font color="red"><c:if
                                                                test="${user==0}">0.00%</c:if>
                                                            <c:if test="${user>0}"><fmt:formatNumber
                                                                    value="${itemVal.keepUser7/user }"
                                                                    type="percent" maxFractionDigits="2"/></c:if></font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 6 }">--</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 13 }"><s:property
                                                                value="#itemVal.keepUser14"/>&nbsp;&nbsp;<font color="red"><c:if
                                                                test="${user==0}">0.00%</c:if>
                                                            <c:if test="${user>0}"><fmt:formatNumber
                                                                    value="${itemVal.keepUser14/user }"
                                                                    type="percent" pattern="0.00"
                                                                    maxFractionDigits="2"/></c:if></font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 13 }">--</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 29 }"><s:property
                                                                value="#itemVal.keepUser30"/>&nbsp;&nbsp;<font color="red"><c:if
                                                                test="${user==0}">0.00%</c:if>
                                                            <c:if test="${user>0}"><fmt:formatNumber
                                                                    value="${itemVal.keepUser30/user }"
                                                                    type="percent" pattern="0.00" minFractionDigits="2"
                                                                    maxFractionDigits="2"/><</c:if>/font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 29 }">--</c:if>
                                                    </td>
                                                </tr>
                                            </s:iterator>
                                            <s:if test="#item.value.size >1">
                                                <tr>
                                                    <td>合计</td>
                                                    <td>${allUsers }</td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 1 }">${allkeepUser1 }&nbsp;&nbsp;<font color="red">
                                                            <c:if test="${allUsers==0}">0.00%</c:if>
                                                            <c:if test="${allUsers>0}">
                                                                <fmt:formatNumber
                                                                        value="${allkeepUser1/allUsers }"
                                                                        type="percent" pattern="0.00"
                                                                        maxFractionDigits="2"/></c:if></font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 1 }">--</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 2 }">${allkeepUser3 }&nbsp;&nbsp;<font color="red"><c:if
                                                                test="${allUsers==0}">0.00%</c:if>
                                                            <c:if test="${allUsers>0}"></c:if><fmt:formatNumber
                                                                    value="${allkeepUser3/allUsers }"
                                                                    type="percent" minFractionDigits="2"
                                                                    maxFractionDigits="2"/></font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 2 }">--</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 3 }">${allkeepUser4 }&nbsp;&nbsp;<font color="red"><c:if
                                                                test="${allUsers==0}">0.00%</c:if>
                                                            <c:if test="${allUsers>0}"><fmt:formatNumber
                                                                    value="${allkeepUser4/allUsers }"
                                                                    type="percent" minFractionDigits="2"
                                                                    maxFractionDigits="2"/></c:if></font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 3 }">--</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 4 }">${allkeepUser5 }&nbsp;&nbsp;<font color="red"><c:if
                                                                test="${allUsers==0}">0.00%</c:if>
                                                            <c:if test="${allUsers>0}"><fmt:formatNumber
                                                                    value="${allkeepUser5/allUsers }"
                                                                    type="percent" minFractionDigits="2"
                                                                    maxFractionDigits="2"/></c:if></font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 4 }">--</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 5 }">${allkeepUser6 }&nbsp;&nbsp;<font color="red"><c:if
                                                                test="${allUsers==0}">0.00%</c:if>
                                                            <c:if test="${allUsers>0}"><fmt:formatNumber
                                                                    value="${allkeepUser6/allUsers }"
                                                                    type="percent" minFractionDigits="2"
                                                                    maxFractionDigits="2"/></c:if></font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 5 }">--</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 6 }">${allkeepUser7 }&nbsp;&nbsp;<font color="red"><c:if
                                                                test="${allUsers==0}">0.00%</c:if>
                                                            <c:if test="${allUsers>0}"><fmt:formatNumber
                                                                    value="${allkeepUser7/allUsers }"
                                                                    type="percent" minFractionDigits="2"
                                                                    maxFractionDigits="2"/></c:if></font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 6 }">--</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 13 }">${allkeepUser14 }&nbsp;&nbsp;<font color="red"><fmt:formatNumber
                                                                value="${allkeepUser14/allUsers }"
                                                                type="percent" minFractionDigits="2"
                                                                maxFractionDigits="2"/></font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 13 }">--</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 >= 30 }">${allkeepUser30 }&nbsp;&nbsp;<font color="red"><c:if
                                                                test="${allUsers==0}">0.00%</c:if>
                                                            <c:if test="${allUsers>0}"><fmt:formatNumber
                                                                    value="${allkeepUser30/allUsers }"
                                                                    type="percent" minFractionDigits="2"
                                                                    maxFractionDigits="2"/></c:if></font></c:if>
                                                        <c:if test="${(nowDate.time - someDate.time)/60/1000/60/24 < 30 }">--</c:if>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                    </s:if>
                                    <s:else>
                                        <tr>
                                            <td colspan="11" align="center">没有数据！</td>
                                        </tr>
                                    </s:else>
                                </c:if>

                                <!-- 对比 -->
                                <c:if test="${result['type'] == 1}">
                                    <s:if test="result['data'].size > 0">
                                        <s:iterator value="result['data']" var="item" status="st">
                                            <c:if test='${keepStatType == "0"}'>
                                                <s:set var="userItem" value="#item.roleUsers"></s:set>
                                            </c:if>
                                            <c:if test='${keepStatType == "1"}'>
                                                <s:set var="userItem" value="#item.activeUsers"></s:set>
                                            </c:if>
                                            <c:if test='${keepStatType == "2"}'>
                                                <s:set var="userItem" value="#item.payUsers"></s:set>
                                            </c:if>
                                            <tr>
                                                <td rowspan="2"><s:date name="statDate" format="yyyy-MM-dd"/></td>
                                                <td>
                                                    <c:if test="${result['group'] == 1 }">
                                                        <s:iterator value="result['gamezones']" var="gameZoneItem">
                                                            ${gameZoneItem.zoneName }&nbsp;
                                                        </s:iterator>
                                                    </c:if>
                                                    <c:if test="${result['group'] == 2 }">
                                                        <s:iterator value="result['platforms']" var="platformItem">
                                                            ${platformItem.platformName }&nbsp;
                                                        </s:iterator>
                                                    </c:if>
                                                </td>
                                                <td><s:property value="#userItem"/></td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 1 }"><s:property
                                                            value="#item.keepUser1"/>&nbsp;&nbsp;<font color="red">
                                                        <c:if test="${userItem==0}">0.00%</c:if>
                                                        <c:if test="${userItem>0}"><fmt:formatNumber
                                                                value="${item.keepUser1/userItem }"
                                                                type="percent" minFractionDigits="2"
                                                                maxFractionDigits="2"/></c:if></font></c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 1 }">--</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 2 }"><s:property
                                                            value="#item.keepUser3"/>&nbsp;&nbsp;<font color="red">
                                                        <c:if test="${userItem==0}">0.00%</c:if>
                                                        <c:if test="${userItem>0}"><fmt:formatNumber
                                                                value="${item.keepUser3/userItem }"
                                                                type="percent" minFractionDigits="2"
                                                                maxFractionDigits="2"/></c:if></font></c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 2 }">--</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 3 }"><s:property
                                                            value="#item.keepUser4"/>&nbsp;&nbsp;<font color="red">
                                                        <c:if test="${userItem==0}">0.00%</c:if>
                                                        <c:if test="${userItem>0}"><fmt:formatNumber
                                                                value="${item.keepUser4/userItem }"
                                                                type="percent" minFractionDigits="2"
                                                                maxFractionDigits="2"/></c:if></font></c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 3 }">--</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 4 }"><s:property
                                                            value="#item.keepUser5"/>&nbsp;&nbsp;<font color="red">
                                                        <c:if test="${userItem==0}">0.00%</c:if>
                                                        <c:if test="${userItem>0}"><fmt:formatNumber
                                                                value="${item.keepUser5/userItem }"
                                                                type="percent" minFractionDigits="2"
                                                                maxFractionDigits="2"/></c:if></font></c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 4 }">--</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 5 }"><s:property
                                                            value="#item.keepUser6"/>&nbsp;&nbsp;<font color="red">
                                                        <c:if test="${userItem==0}">0.00%</c:if>
                                                        <c:if test="${userItem>0}"><fmt:formatNumber
                                                                value="${item.keepUser6/userItem }"
                                                                type="percent" minFractionDigits="2"
                                                                maxFractionDigits="2"/></font></c:if></c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 5 }">--</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 6 }"><s:property
                                                            value="#item.keepUser7"/>&nbsp;&nbsp;<font color="red">
                                                        <c:if test="${userItem==0}">0.00%</c:if>
                                                        <c:if test="${userItem>0}"><fmt:formatNumber
                                                                value="${item.keepUser7/userItem }"
                                                                type="percent" minFractionDigits="2"
                                                                maxFractionDigits="2"/></c:if></font></c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 6 }">--</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 13 }"><s:property
                                                            value="#item.keepUser14"/>&nbsp;&nbsp;<font color="red"><fmt:formatNumber
                                                            value="${item.keepUser14/userItem }"
                                                            type="percent" minFractionDigits="2"
                                                            maxFractionDigits="2"/></font></c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 13 }">--</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 29 }"><s:property
                                                            value="#item.keepUser30"/>&nbsp;&nbsp;<font color="red">
                                                        <c:if test="${userItem==0}">0.00%</c:if>
                                                        <c:if test="${userItem>0}"><fmt:formatNumber
                                                                value="${item.keepUser30/userItem }"
                                                                type="percent" minFractionDigits="2"
                                                                maxFractionDigits="2"/></c:if></font></c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 29 }">--</c:if>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <c:if test="${result['group'] == 1 }">
                                                        <s:iterator value="result['compareGamezones']"
                                                                    var="gameZoneItem">
                                                            ${gameZoneItem.zoneName }&nbsp;
                                                        </s:iterator>
                                                    </c:if>
                                                    <c:if test="${result['group'] == 2 }">
                                                        <s:iterator value="result['comparePlatforms']"
                                                                    var="platformItem">
                                                            ${platformItem.platformName }&nbsp;
                                                        </s:iterator>
                                                    </c:if>
                                                </td>
                                                <c:if test='${keepStatType == "0"}'>
                                                    <s:set var="userCompareItem"
                                                           value="result['compareData'][st.index].roleUsers"></s:set>
                                                </c:if>
                                                <c:if test='${keepStatType == "1"}'>
                                                    <s:set var="userCompareItem"
                                                           value="result['compareData'][st.index].activeUsers"></s:set>
                                                </c:if>
                                                <c:if test='${keepStatType == "2"}'>
                                                    <s:set var="userCompareItem"
                                                           value="result['compareData'][st.index].payUsers"></s:set>
                                                </c:if>
                                                <td>${userCompareItem }</td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 1 }">
                                                        ${result['compareData'][st.index].keepUser1 }&nbsp;&nbsp;<font color="red">
                                                        <c:if test="${userCompareItem==0}">0.00%</c:if>
                                                        <c:if test="${userCompareItem>0}"><fmt:formatNumber
                                                                value="${result['compareData'][st.index].keepUser1/userCompareItem * 100 }"
                                                                pattern="0.00"/>%</c:if></font>
                                                    </c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 1 }">--</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 2 }">
                                                        ${result['compareData'][st.index].keepUser3 }&nbsp;&nbsp;<font color="red"><c:if
                                                            test="${userCompareItem==0}">0.00%</c:if>
                                                        <c:if test="${userCompareItem>0}"><fmt:formatNumber
                                                                value="${result['compareData'][st.index].keepUser3/userCompareItem }"
                                                                type="percent" minFractionDigits="2"
                                                                maxFractionDigits="2"/></c:if></font>
                                                    </c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 2 }">--</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 3 }">
                                                        ${result['compareData'][st.index].keepUser4 }&nbsp;&nbsp;<font color="red"><c:if
                                                            test="${userCompareItem==0}">0.00%</c:if>
                                                        <c:if test="${userCompareItem>0}"><fmt:formatNumber
                                                                value="${result['compareData'][st.index].keepUser4/userCompareItem }"
                                                                type="percent" minFractionDigits="2"
                                                                maxFractionDigits="2"/><</c:if>/font>
                                                    </c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 3 }">--</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 4 }">
                                                        ${result['compareData'][st.index].keepUser5 }&nbsp;&nbsp;<font color="red"><c:if
                                                            test="${userCompareItem==0}">0.00%</c:if>
                                                        <c:if test="${userCompareItem>0}"><fmt:formatNumber
                                                                value="${result['compareData'][st.index].keepUser5/userCompareItem }"
                                                                type="percent" minFractionDigits="2"
                                                                maxFractionDigits="2"/></c:if></font>
                                                    </c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 4 }">--</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 5 }">
                                                        ${result['compareData'][st.index].keepUser6 }&nbsp;&nbsp;<font color="red"><c:if
                                                            test="${userCompareItem==0}">0.00%</c:if>
                                                        <c:if test="${userCompareItem>0}"><fmt:formatNumber
                                                                value="${result['compareData'][st.index].keepUser6/userCompareItem }"
                                                                type="percent" minFractionDigits="2"
                                                                maxFractionDigits="2"/></c:if></font>
                                                    </c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 5 }">--</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 6 }">
                                                        ${result['compareData'][st.index].keepUser7 }&nbsp;&nbsp;<font color="red"><c:if
                                                            test="${userCompareItem==0}">0.00%</c:if>
                                                        <c:if test="${userCompareItem>0}"><fmt:formatNumber
                                                                value="${result['compareData'][st.index].keepUser7/userCompareItem }"
                                                                type="percent" minFractionDigits="2"
                                                                maxFractionDigits="2"/></c:if></font>
                                                    </c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 6 }">--</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 13 }">
                                                        ${result['compareData'][st.index].keepUser14 }&nbsp;&nbsp;<font color="red"><c:if
                                                            test="${userCompareItem==0}">0.00%</c:if>
                                                        <c:if test="${userCompareItem>0}"><fmt:formatNumber
                                                                value="${result['compareData'][st.index].keepUser14/userCompareItem }"
                                                                type="percent" minFractionDigits="2"
                                                                maxFractionDigits="2"/></c:if></font>
                                                    </c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 13 }">--</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 >= 29 }">
                                                        ${result['compareData'][st.index].keepUser30 }&nbsp;&nbsp;<font color="red"><c:if
                                                            test="${userCompareItem==0}">0.00%</c:if>
                                                        <c:if test="${userCompareItem>0}"><fmt:formatNumber
                                                                value="${result['compareData'][st.index].keepUser30/userCompareItem }"
                                                                type="percent" minFractionDigits="2"
                                                                maxFractionDigits="2"/></c:if></font>
                                                    </c:if>
                                                    <c:if test="${(nowDate.time - statDate.time)/60/1000/60/24 < 29 }">--</c:if>
                                                </td>
                                            </tr>
                                        </s:iterator>
                                    </s:if>
                                    <s:else>
                                        <tr>
                                            <td colspan="11" align="center">没有数据！</td>
                                        </tr>
                                    </s:else>
                                </c:if>
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
        $("#reportLeft_7").addClass("active");
        $("#zone").addClass("active");
    });

    function simulateChange(el) {
        if ("createEvent" in document) {
            var evt = document.createEvent("HTMLEvents");
            evt.initEvent("change", false, true);
            el.dispatchEvent(evt);
        } else
            el.fireEvent("onchange");
    }

    function selectKeepType() {
        var options = $("#keepStatType option:selected");
        // alert(options.val());
        $("#mainForm").submit();
    }

    function selectChannelZone() {
        var appId = $("#appId").val();
        if ($.trim(appId) == "") {
            errorTip("请先选择游戏！");
            return;
        }
        art.dialog.open("${ctx}/report/report_getChannelZone.shtml?appId=" + appId + "&isCompare=0",
            {
                title: '筛选',
                id: 'addBox',
                fixed: true,
                lock: true,
                background: '#000',
                opacity: 0,
                height: '60%',
                width: '60%',
                fited: true
            });
    }

    function selectCompareChannelZone() {
        var appId = $("#appId").val();
        if ($.trim(appId) == "") {
            errorTip("请先选择游戏！");
            return;
        }
        art.dialog.open("${ctx}/report/report_getChannelZone.shtml?appId=" + appId + "&isCompare=1",
            {
                title: '筛选',
                id: 'addBox',
                fixed: true,
                lock: true,
                background: '#000',
                opacity: 0,
                height: '60%',
                width: '60%',
                fited: true
            }
        );
    }
</script>
</body>
</html>