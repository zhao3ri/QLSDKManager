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
                    <form action="reportDaily_basic.shtml" method="post" id="mainForm">
                        <div class="form-inline popover-show panel-body list_toolbar">
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
                                <select class="form-control" name="gameId" id="gameId" placeholder="请选择游戏">
                                    <s:iterator value="allGames" var="item">
                                        <option value="${item.id }"
                                                <c:if test="${item.id==gameId }">selected</c:if>>${item.gameName }</option>
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
                            <div class="form-group    width_input" data-toggle="popover" data-placement="top"
                                 data-content="对比时间">
                                <input class="form-control daterange" type="text" readonly name="compareSelectRange"
                                       value="${compareSelectRange }" placeholder="对比时间" sType="searchAndValid"/>
                            </div>
                            <div class="form-group width_btn">
                                <button type="button" class="btn  btn-primary " onclick="searchAndValid();"><i
                                        class="icon-search"></i> 搜索
                                </button>
                            </div>
                        </div>
                    </form>
                    <c:if test="${not empty compareSelectRange}">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0"
                               class="table panel-body table-bordered ">
                            <tbody>
                            <tr>

                                <th>创角用户 <i class="helpToolTip" data-content="统计所选时期内，每日玩家激活游戏后，进行了注册并创建了游戏角色的账户数量"></i>
                                </th>
                                <th>注册用户 <i class="helpToolTip"
                                            data-content="统计所选时期内，每日玩家激活游戏后，进行了注册有ID信息或者账户信息的玩家账户数量"></i></th>
                                <th>注册设备 <i class="helpToolTip" data-content="当日玩家激活游戏后，进行了注册有ID信息或者账户信息的玩家设备数量"></i>
                                </th>
                                <th>活跃用户 <i class="helpToolTip" data-content="统计所选时期内，每日成功登录游戏的老玩家（账号唯一）数量"></i></th>
                                <c:if test="${empty channelIds && empty compareChannelIds && empty zoneIds && empty compareZoneIds }">
                                    <th>总游戏启动次数 <i class="helpToolTip"
                                                   data-content="以设备为唯一，记录用户启动游戏总次数；游戏启动至加载完我们SDK后开始记录启动次数"></i></th>
                                </c:if>
                                <th>充值额 <i class="helpToolTip"
                                           data-content="统计所选时期内，每日玩家（账号为唯一）成功充值的金额总值。包括新老用户的充值"></i></th>
                                <th>充值人数 <i class="helpToolTip" data-content="统计所选时期内，每日成功充值的用户（账号唯一）数量"></i></th>
                                <th>新用户充值额 <i class="helpToolTip" data-content="统计所选时期内，当天新用户（账号唯一）充值总额"></i></th>
                                <th>新用户充值人数<i class="helpToolTip" data-content="统计所选时期内，当天新用户付费总人数"></i></th>
                                <th>平均在线时长 <i class="helpToolTip" data-content="全部使用日在线时长/日在线用户数"></i></th>
                                <th>登录付费率 <i class="helpToolTip" data-content="当日总充值人数/活跃用户"></i></th>
                                <th>新用户付费率 <i class="helpToolTip" data-content="新用户充值人数/创角用户数"></i></th>
                                <th>活跃日ARPU<i class="helpToolTip" data-content="充值总额/活跃用户"></i></th>
                                <th>付费ARPU<i class="helpToolTip" data-content="游戏充值金额/付费用户"></i></th>
                            </tr>
                            <tr>
                                <td>${result['data'][0].roleUsers }</td>
                                <td>${result['data'][0].regUsers }</td>
                                <td>${result['data'][0].newActiveDevices }</td>
                                <td>${result['data'][0].activeUsers }</td>
                                <c:if test="${empty channelIds && empty compareChannelIds && empty zoneIds && empty compareZoneIds }">
                                    <td>${result['data'][0].startTimes }</td>
                                </c:if>
                                <td><fmt:formatNumber value="${result['data'][0].payAmount/100 }" pattern="0.00"/></td>
                                <td>${result['data'][0].payUsers }</td>
                                <td><fmt:formatNumber value="${result['data'][0].newUserPayAmount/100 }"
                                                      pattern="0.00"/></td>
                                <td>${result['data'][0].newUserPays }</td>

                                <fmt:formatNumber value="${result['data'][0].avgOnlineTime}" pattern="0" var="total"/>

                                <fmt:formatNumber value="${total/3600-0.5+ 0.0001}" pattern="0" var="totalHour"/>


                                <fmt:formatNumber value="${(total-totalHour*3600)/60-0.5+ 0.0001}" pattern="0"
                                                  var="totalMinute"/>


                                <fmt:formatNumber value="${total-totalHour*3600-totalMinute*60}" pattern="0"
                                                  var="totalSecond"/>

                                <td>
                                    <fmt:formatNumber value="${result['data'][0].avgOnlineTime/3600 }" pattern="0.00"/>
                                </td>

                                <c:if test="${result['data'][0].roleUsers > 0 }">
                                    <td><fmt:formatNumber
                                            value="${result['data'][0].newUserPays * 100/result['data'][0].roleUsers }"
                                            pattern="0.00"/>%
                                    </td>
                                </c:if>
                                <c:if test="${result['data'][0].roleUsers == 0 }">
                                    <td>0.00%</td>
                                </c:if>

                                <c:if test="${result['data'][0].activeUsers > 0 }">
                                    <td><fmt:formatNumber
                                            value="${result['data'][0].payAmount/100/result['data'][0].activeUsers }"
                                            pattern="0.00"/></td>
                                </c:if>
                                <c:if test="${result['data'][0].activeUsers == 0 }">
                                    <td>0.00</td>
                                </c:if>
                            </tr>
                            <tr>
                                <td>${result['compareData'][0].roleUsers }</td>
                                <td>${result['compareData'][0].regUsers }</td>
                                <td>${result['compareData'][0].newActiveDevices }</td>
                                <td>${result['compareData'][0].activeUsers }</td>
                                <c:if test="${empty channelIds && empty compareChannelIds && empty zoneIds && empty compareZoneIds }">
                                    <td>${result['compareData'][0].startTimes }</td>
                                </c:if>
                                <td><fmt:formatNumber value="${result['compareData'][0].payAmount/100 }"
                                                      pattern="0.00"/></td>
                                <td>${result['compareData'][0].payUsers }</td>
                                <td><fmt:formatNumber value="${result['compareData'][0].newUserPayAmount/100 }"
                                                      pattern="0.00"/></td>
                                <td>${result['compareData'][0].newUserPays }</td>


                                <fmt:formatNumber value="${result['compareData'][0].avgOnlineTime}" pattern="0"
                                                  var="total"/>

                                <fmt:formatNumber value="${total/3600-0.5+ 0.0001}" pattern="0" var="totalHour"/>

                                <fmt:formatNumber value="${(total-totalHour*3600)/60-0.5+ 0.0001}" pattern="0"
                                                  var="totalMinute"/>

                                <fmt:formatNumber value="${total-totalHour*3600-totalMinute*60}" pattern="0"
                                                  var="totalSecond"/>

                                <td>
                                    <fmt:formatNumber value="${result['compareData'][0].avgOnlineTime/3600 }"
                                                      pattern="0.00"/>
                                </td>
                                <!--登录付费率-->
                                <td>
                                    <c:if test="${result['compareData'][0].activeUsers==0}">0.00</c:if>
                                    <c:if test="${result['compareData'][0].activeUsers > 0 }">
                                        <fmt:formatNumber value="${result['compareData'][0].payUsers/100/result['compareData'][0].activeUsers }"
                                                          pattern="0.00"/>
                                    </c:if>
                                </td>

                                <c:if test="${result['compareData'][0].roleUsers > 0 }">
                                    <td><fmt:formatNumber
                                            value="${result['compareData'][0].newUserPays * 100/result['compareData'][0].roleUsers }"
                                            pattern="0.00"/>%
                                    </td>
                                </c:if>
                                <c:if test="${result['compareData'][0].roleUsers == 0 }">
                                    <td>0.00%</td>
                                </c:if>

                                <c:if test="${result['compareData'][0].activeUsers > 0 }">
                                    <td><fmt:formatNumber
                                            value="${result['compareData'][0].payAmount/100/result['compareData'][0].activeUsers }"
                                            pattern="0.00"/></td>
                                </c:if>
                                <c:if test="${result['compareData'][0].activeUsers == 0 }">
                                    <td>0.00</td>
                                </c:if>
                                <!--付费ARPU-->
                                <td>
                                    <c:if test="${result['compareData'][0].roleUsers==0}">0.00</c:if>
                                    <c:if test="${result['compareData'][0].roleUsers > 0 }">
                                        <fmt:formatNumber value="${result['compareData'][0].payAmount/100/result['compareData'][0].roleUsers }"
                                                          pattern="0.00"/>
                                    </c:if>
                                </td>
                            </tr>
                            </tbody>
                            <br>
                        </table>
                    </c:if>
                    <div class="panel-body " id="chartData" style="height:400px">
                        统计图输出
                    </div>
                </div>
                <c:if test="${empty compareSelectRange}">
                    <div class="panel panel-default">
                        <div class="panel-heading ">
                            <h3 class="panel-title">
                                <!-- 渠道 -->
                                <c:if test="${groupType == 1 }">
                                    <c:if test="${not empty channelIds}">
                                        <s:if test="result['channels'].size>0">
                                            <s:iterator value="result[pplatforms" var="item">
                                                ${channelName }&nbsp;&nbsp;
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
                                                ${channelName }&nbsp;&nbsp;
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
                                        <th>创角用户 <i class="helpToolTip" data-content="统计所选时期内，每日玩家激活游戏后，进行了注册并创建了游戏角色的账户数量"></i>
                                        </th>
                                        <th>注册用户 <i class="helpToolTip"
                                                    data-content="统计所选时期内，每日玩家激活游戏后，进行了注册有ID信息或者账户信息的玩家账户数量"></i></th>
                                        <th>注册设备 <i class="helpToolTip" data-content="当日玩家激活游戏后，进行了注册有ID信息或者账户信息的玩家设备数量"></i>
                                        </th>
                                        <th>活跃用户 <i class="helpToolTip" data-content="统计所选时期内，每日成功登录游戏的老玩家（账号唯一）数量"></i></th>
                                        <c:if test="${empty channelIds && empty compareChannelIds && empty zoneIds && empty compareZoneIds }">
                                            <th>游戏启动次数 <i class="helpToolTip"
                                                           data-content="以设备为唯一，记录用户启动游戏总次数；游戏启动至加载完我们SDK后开始记录启动次数"></i></th>
                                        </c:if>
                                        <th>充值额 <i class="helpToolTip"
                                                   data-content="统计所选时期内，每日玩家（账号为唯一）成功充值的金额总值。包括新老用户的充值"></i></th>
                                        <th>充值人数 <i class="helpToolTip" data-content="统计所选时期内，每日成功充值的用户（账号唯一）数量"></i></th>
                                        <th>新用户充值额 <i class="helpToolTip" data-content="统计所选时期内，当天新用户（账号唯一）充值总额"></i></th>
                                        <th>新用户充值人数<i class="helpToolTip" data-content="统计所选时期内，当天新用户付费总人数"></i></th>
                                        <th>平均在线时长 <i class="helpToolTip" data-content="全部使用日在线时长/日在线用户数"></i></th>
                                        <th>登录付费率 <i class="helpToolTip" data-content="当日总充值人数/活跃用户"></i></th>
                                        <th>新用户付费率 <i class="helpToolTip" data-content="新用户充值人数/创角用户数"></i></th>
                                        <th>活跃日ARPU<i class="helpToolTip" data-content="充值总额/活跃用户"></i></th>
                                        <th>付费ARPU<i class="helpToolTip" data-content="游戏充值金额/付费用户"></i></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${result['type'] == 2}">
                                        <s:if test="result['data'].size > 0">
                                            <s:iterator value="result['data']" var="item">
                                                <s:set var="allroleUsers" value="0"></s:set>
                                                <s:set var="allregUsers" value="0"></s:set>
                                                <s:set var="allnewActiveDevices" value="0"></s:set>
                                                <s:set var="allactiveUsers" value="0"></s:set>
                                                <s:set var="allstartTimes" value="0"></s:set>
                                                <s:set var="allpayAmount" value="0"></s:set>
                                                <s:set var="allpayUsers" value="0"></s:set>
                                                <s:set var="allnewUserPayAmount" value="0"></s:set>
                                                <s:set var="allavgOnlineTime" value="0"></s:set>
                                                <s:set var="itemSize" value="#item.value.size"></s:set>
                                                <s:set var="allnewUserPays" value="0"> </s:set>
                                                <s:iterator value="#item.value" var="itemVal" status="st">
                                                    <s:set var="allroleUsers"
                                                           value="#allroleUsers + #itemVal.roleUsers"></s:set>
                                                    <s:set var="allregUsers"
                                                           value="#allregUsers + #itemVal.regUsers"></s:set>
                                                    <s:set var="allnewActiveDevices"
                                                           value="#allnewActiveDevices + #itemVal.newActiveDevices"></s:set>
                                                    <s:set var="allactiveUsers"
                                                           value="#allactiveUsers + #itemVal.activeUsers"></s:set>
                                                    <s:set var="allstartTimes"
                                                           value="#allstartTimes + #itemVal.startTimes"></s:set>
                                                    <s:set var="allpayAmount"
                                                           value="#allpayAmount + #itemVal.payAmount"></s:set>
                                                    <s:set var="allpayUsers"
                                                           value="#allpayUsers + #itemVal.payUsers"></s:set>
                                                    <s:set var="allnewUserPayAmount"
                                                           value="#allnewUserPayAmount + #itemVal.newUserPayAmount"></s:set>
                                                    <s:set var="allnewUserPays"
                                                           value="#allnewUserPays+ #itemVal.newUserPays"> </s:set>
                                                    <s:set var="allavgOnlineTime"
                                                           value="#allavgOnlineTime + #itemVal.avgOnlineTime"></s:set>
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
                                                            <td><s:property value="#itemVal.gameName"/></td>
                                                        </c:if>
                                                        <c:if test="${result['group'] == 'channel'}">
                                                            <td><s:property value="#itemVal.channelName"/></td>
                                                        </c:if>
                                                        <c:if test="${result['group'] == 'zone'}">
                                                            <td><s:property value="#itemVal.zoneName"/></td>
                                                        </c:if>

                                                        <td><s:property value="#itemVal.roleUsers"/></td>
                                                        <td><s:property value="#itemVal.regUsers"/></td>
                                                        <td><s:property value="#itemVal.newActiveDevices"/></td>
                                                        <td><s:property value="#itemVal.activeUsers"/></td>
                                                        <c:if test="${empty channelIds && empty compareChannelIds && empty zoneIds && empty compareZoneIds }">
                                                            <td><s:property value="#itemVal.startTimes"/></td>
                                                        </c:if>
                                                        <td><fmt:formatNumber value="${itemVal.payAmount/100 }"
                                                                              pattern="0.00"/></td>
                                                        <td><s:property value="#itemVal.payUsers"/></td>
                                                        <td><fmt:formatNumber value="${itemVal.newUserPayAmount/100 }"
                                                                              pattern="0.00"/></td>
                                                        <td><s:property value="#itemVal.newUserPays"/></td>

                                                        <s:if test="#itemVal.avgOnlineTime==0">
                                                            <td>0.00</td>
                                                        </s:if>
                                                        <s:else>
                                                            <s:set value="#itemVal.avgOnlineTime"
                                                                   var="totalSecond"></s:set>
                                                            <s:set value="#totalSecond/3600" var="hour"></s:set>
                                                            <s:set value="(#totalSecond-#hour*3600)/60"
                                                                   var="minute"></s:set>
                                                            <s:set value="#totalSecond-#hour*3600-#minute*60"
                                                                   var="second"></s:set>

                                                            <td>
                                                                <fmt:formatNumber value="${itemVal.avgOnlineTime/3600 }"
                                                                                  pattern="0.00"/>
                                                            </td>
                                                        </s:else>

                                                        <td>
                                                            <c:if test="${itemVal.activeUsers==0}">0.00</c:if>
                                                            <c:if test="${itemVal.activeUsers > 0 }">
                                                                <fmt:formatNumber value="${itemVal.payUsers/100/itemVal.activeUsers }"
                                                                                  pattern="0.00"/>
                                                            </c:if>
                                                        </td>

                                                        <c:if test="${roleUsers > 0 }">
                                                            <td><fmt:formatNumber
                                                                    value="${newUserPays * 100/roleUsers }"
                                                                    pattern="0.00"/>%
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${roleUsers == 0 }">
                                                            <td>0.00%</td>
                                                        </c:if>

                                                        <c:if test="${activeUsers > 0 }">
                                                            <td><fmt:formatNumber value="${payAmount/100/activeUsers }"
                                                                                  pattern="0.00"/></td>
                                                        </c:if>
                                                        <c:if test="${activeUsers == 0 }">
                                                            <td>0.00</td>
                                                        </c:if>
                                                        <td>
                                                            <c:if test="${itemVal.roleUsers==0}">0.00</c:if>
                                                            <c:if test="${itemVal.roleUsers > 0 }">
                                                                <fmt:formatNumber value="${itemVal.payAmount/100/itemVal.roleUsers }"
                                                                                  pattern="0.00"/>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                </s:iterator>
                                                <s:if test="#item.value.size >1">
                                                    <tr>
                                                        <td>合计</td>
                                                        <td>${allroleUsers }</td>
                                                        <td>${allregUsers }</td>
                                                        <td>${allnewActiveDevices }</td>
                                                        <td>${allactiveUsers }</td>
                                                        <c:if test="${empty channelIds && empty compareChannelIds && empty zoneIds && empty compareZoneIds }">
                                                            <td>${allstartTimes }</td>
                                                        </c:if>
                                                        <td><fmt:formatNumber value="${allpayAmount/100 }"
                                                                              pattern="0.00"/></td>
                                                        <td>${allpayUsers }</td>
                                                        <td><fmt:formatNumber value="${allnewUserPayAmount/100 }"
                                                                              pattern="0.00"/></td>
                                                        <td>${allnewUserPays}</td>


                                                        <fmt:formatNumber value="${allavgOnlineTime/itemSize }"
                                                                          pattern="0" var="total"/>


                                                        <fmt:formatNumber value="${total/3600-0.5+ 0.0001}" pattern="0"
                                                                          var="totalHour"/>


                                                        <fmt:formatNumber
                                                                value="${(total-totalHour*3600)/60-0.5+ 0.0001}"
                                                                pattern="0" var="totalMinute"/>


                                                        <fmt:formatNumber value="${total-totalHour*3600-totalMinute*60}"
                                                                          pattern="0" var="totalSecond"/>


                                                        <td>
                                                            <fmt:formatNumber value="${allavgOnlineTime/3600 }"
                                                                              pattern="0.00"/>
                                                        </td>


                                                        <c:if test="${allroleUsers > 0 }">
                                                            <td><fmt:formatNumber
                                                                    value="${allnewUserPays * 100/allroleUsers }"
                                                                    pattern="0.00"/>%
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${allroleUsers == 0 }">
                                                            <td>0.00%</td>
                                                        </c:if>

                                                        <c:if test="${allactiveUsers > 0 }">
                                                            <td><fmt:formatNumber
                                                                    value="${allpayAmount/100/allactiveUsers }"
                                                                    pattern="0.00"/></td>
                                                        </c:if>
                                                        <c:if test="${allactiveUsers == 0 }">
                                                            <td>0.00</td>
                                                        </c:if>
                                                    </tr>
                                                </s:if>
                                            </s:iterator>
                                        </s:if>
                                        <s:else>
                                            <tr>
                                                <td colspan="14" align="center">没有数据！</td>
                                            </tr>
                                        </s:else>
                                    </c:if>

                                    <!-- 对比 -->
                                    <c:if test="${result['type'] == 1}">
                                        <s:if test="result['data'].size > 0">
                                            <s:iterator value="result['data']" var="item" status="st">
                                                <tr>
                                                    <td rowspan="2"><s:date name="statDate" format="yyyy-MM-dd"/></td>
                                                    <td>
                                                        <c:if test="${result['group'] == 1 }">
                                                            <s:iterator value="result['gamezones']" var="gameZoneItem">
                                                                ${gameZoneItem.zoneName }&nbsp;
                                                            </s:iterator>
                                                        </c:if>
                                                        <c:if test="${result['group'] == 2 }">
                                                            <s:iterator value="result['plplatforms" var="platformItem">
                                                                ${platformItem.channelName }&nbsp;
                                                            </s:iterator>
                                                        </c:if>
                                                    </td>
                                                    <td><s:property value="roleUsers"/></td>
                                                    <td><s:property value="regUsers"/></td>
                                                    <td><s:property value="newActiveDevices"/></td>
                                                    <td><s:property value="activeUsers"/></td>
                                                    <c:if test="${empty channelIds && empty compareChannelIds && empty zoneIds && empty compareZoneIds }">
                                                        <td><s:property value="startTimes"/></td>
                                                    </c:if>
                                                    <td><fmt:formatNumber value="${payAmount/100 }"
                                                                          pattern="0.00"/></td>
                                                    <td><s:property value="payUsers"/></td>
                                                    <td><fmt:formatNumber value="${newUserPayAmount/100 }"
                                                                          pattern="0.00"/></td>
                                                    <td><s:property value="newUserPays"/></td>

                                                    <s:if test="avgOnlineTime==0">
                                                        <td>00:00:00</td>
                                                    </s:if>
                                                    <s:else>
                                                        <s:set value="avgOnlineTime" var="totalSecond"></s:set>
                                                        <s:set value="#totalSecond/3600" var="hour"></s:set>
                                                        <s:set value="(#totalSecond-#hour*3600)/60"
                                                               var="minute"></s:set>
                                                        <s:set value="#totalSecond-#hour*3600-#minute*60"
                                                               var="second"></s:set>
                                                        <td>
                                                            <fmt:formatNumber value="${avgOnlineTime/3600 }"
                                                                              pattern="0.00"/>
                                                        </td>

                                                    </s:else>

                                                    <c:if test="${roleUsers > 0 }">
                                                        <td><fmt:formatNumber value="${newUserPays * 100/roleUsers }"
                                                                              pattern="0.00"/>%
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${roleUsers == 0 }">
                                                        <td>0.00%</td>
                                                    </c:if>
                                                    <c:if test="${activeUsers > 0 }">
                                                        <td><fmt:formatNumber value="${payAmount/100/activeUsers }"
                                                                              pattern="0.00"/></td>
                                                    </c:if>
                                                    <c:if test="${activeUsers == 0 }">
                                                        <td>0.00</td>
                                                    </c:if>
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
                                                            ${platformItem.channelName }&nbsp;
                                                        </s:iterator>
                                                    </c:if>
                                                </td>
                                                <s:if test="result['compareData'].size>0">
                                                    <td>${result['compareData'][st.index].roleUsers }</td>
                                                    <td>${result['compareData'][st.index].regUsers }</td>
                                                    <td>${result['compareData'][st.index].newActiveDevices }</td>
                                                    <td>${result['compareData'][st.index].activeUsers }</td>
                                                    <c:if test="${empty channelIds && empty compareChannelIds && empty zoneIds && empty compareZoneIds }">
                                                        <td>${result['compareData'][st.index].startTimes }</td>
                                                    </c:if>
                                                    <td><fmt:formatNumber
                                                            value="${result['compareData'][st.index].payAmount /100 }"
                                                            pattern="0.00"/></td>
                                                    <td>${result['compareData'][st.index].payUsers }</td>
                                                    <td><fmt:formatNumber
                                                            value="${result['compareData'][st.index].newUserPayAmount /100 }"
                                                            pattern="0.00"/></td>
                                                    <td>${result['compareData'][st.index].newUserPays }</td>


                                                    <fmt:formatNumber
                                                            value="${result['compareData'][st.index].avgOnlineTime }"
                                                            pattern="0" var="total"/>

                                                    <fmt:formatNumber value="${total/3600-0.5+ 0.0001}" pattern="0"
                                                                      var="totalHour"/>

                                                    <fmt:formatNumber value="${(total-totalHour*3600)/60-0.5+ 0.0001}"
                                                                      pattern="0" var="totalMinute"/>

                                                    <fmt:formatNumber value="${total-totalHour*3600-totalMinute*60}"
                                                                      pattern="0" var="totalSecond"/>

                                                    <td>
                                                        <fmt:formatNumber
                                                                value="${result['compareData'][st.index].avgOnlineTime/3600 }"
                                                                pattern="0.00"/>
                                                    </td>


                                                    <c:if test="${result['compareData'][st.index].roleUsers > 0 }">
                                                        <td><fmt:formatNumber
                                                                value="${result['compareData'][st.index].newUserPays * 100/result['compareData'][st.index].roleUsers }"
                                                                pattern="0.00"/>%
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${result['compareData'][st.index].roleUsers == 0 }">
                                                        <td>0.00%</td>
                                                    </c:if>

                                                    <c:if test="${result['compareData'][st.index].activeUsers > 0 }">
                                                        <td><fmt:formatNumber
                                                                value="${result['compareData'][st.index].payAmount/100/result['compareData'][st.index].activeUsers }"
                                                                pattern="0.00"/></td>
                                                    </c:if>
                                                    <c:if test="${result['compareData'][st.index].activeUsers == 0 }">
                                                        <td>0.00</td>
                                                    </c:if>
                                                </s:if>
                                                <s:else>
                                                    <tr>
                                                        <td colspan="14" align="center">没有数据！</td>
                                                    </tr>

                                                    <c:if test="${empty channelIds && empty compareChannelIds && empty zoneIds && empty compareZoneIds }">
                                                        <td colspan="14" align="center">没有数据！</td>
                                                    </c:if>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                </s:else>
                                                </tr>
                                            </s:iterator>
                                        </s:if>
                                        <s:else>
                                            <tr>
                                                <td colspan="14" align="center">没有数据！</td>
                                            </tr>
                                        </s:else>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#reportLeft_basic").addClass("active");
        $("#zone").addClass("active");
    });

    require(
        [
            'echarts',
            'echarts/chart/line',
            'echarts/chart/bar',
            'echarts/chart/pie',
            'echarts/chart/funnel'
        ],
        function (ec) {
            if ('${result["optionJson"]}' == "") {
                $("#chartData").html("<div align='center'>没有数据！</div>");
                return;
            }
            var myChart = ec.init(document.getElementById('chartData'));
            var option = eval('(${result["optionJson"]})');
            myChart.setOption(option);
        }
    );

    function selectChannelZone() {
        var gameId = $("#gameId").val();
        if ($.trim(gameId) == "") {
            errorTip("请先选择游戏！");
            return;
        }
        art.dialog.open("${ctx}/report/report_getChannelZone.shtml?gameId=" + gameId + "&isCompare=0",
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
        var gameId = $("#gameId").val();
        if ($.trim(gameId) == "") {
            errorTip("请先选择游戏！");
            return;
        }
        art.dialog.open("${ctx}/report/report_getChannelZone.shtml?gameId=" + gameId + "&isCompare=1",
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