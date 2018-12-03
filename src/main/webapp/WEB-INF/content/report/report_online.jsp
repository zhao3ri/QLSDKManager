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
                    <form action="reportDaily_online.shtml" method="post" id="mainForm">
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
                                <select class="form-control" name="gameId" id="gameId" placeholder="请选择游戏"
                                        onchange="selectGame(this)">
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
                    <div class="panel-heading ">
                        <h3 class="panel-title"> 统计图</h3>
                    </div>
                    <div class="panel-body " id="chartData" style="height:400px">
                        统计图输出
                    </div>
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

                            <table class="table table-hover table-striped table-bordered table-condensed table-big">
                                <thead>
                                <tr>
                                    <th>时间</th>
                                    <c:if test="${result['isCompare'] == 1 || result['isCompare'] == 3 }">
                                        <th>对比</th>
                                    </c:if>
                                    <c:if test="${result['isCompare'] == 2 }">
                                        <th>明细</th>
                                    </c:if>
                                    <th>实时在线 <i class="helpToolTip" data-content="实时在线的创角账号数（账号唯一）"></i></th>
                                    <th>新增创角 <i class="helpToolTip" data-content="当日实时新增的创角玩家账号的数量"></i></th>
                                    <th>新增设备 <i class="helpToolTip"
                                                data-content="当日玩家激活游戏后，进行了注册有ID信息或者账户信息的玩家设备数量"></i></th>
                                    <th>充值金额 <i class="helpToolTip" data-content="实时计算当日各账号的累计充值金额，不需排重"></i></th>
                                    <th>累计活跃用户 <i class="helpToolTip" data-content="累计到该时间点，成功登录游戏的老玩家数量"></i></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${result['isCompare'] == 0 }">
                                    <s:if test="result['data'].size > 0">
                                        <s:set var="allonlineUsers" value="0"></s:set>
                                        <s:set var="allroleUsers" value="0"></s:set>
                                        <s:set var="allnewDevices" value="0"></s:set>
                                        <s:set var="allpayAmount" value="0"></s:set>
                                        <s:set var="allSize" value="0"></s:set>
                                        <s:set var="allactiveUsers" value="0"></s:set>
                                        <s:iterator value="result['data']" var="item">
                                            <s:set var="allonlineUsers"
                                                   value="#allonlineUsers + #item.onlineUsers"></s:set>
                                            <s:set var="allroleUsers" value="#allroleUsers + #item.roleUsers"></s:set>
                                            <s:set var="allnewDevices"
                                                   value="#allnewDevices + #item.newDevices"></s:set>
                                            <s:set var="allpayAmount" value="#allpayAmount + #item.payAmount"></s:set>
                                            <s:set var="allactiveUsers"
                                                   value="#allactiveUsers + #item.activeUsers"></s:set>
                                            <s:set var="allSize" value="#allSize + 1"></s:set>
                                            <tr>
                                                <td><s:date name="statDate" format="HH:mm:ss"/></td>
                                                <td><s:property value="onlineUsers"/></td>
                                                <td><s:property value="roleUsers"/></td>
                                                <td><s:property value="newDevices"/></td>
                                                <td><fmt:formatNumber value="${payAmount/100 }" pattern="0.00"/></td>
                                                <td><s:property value="activeUsers"/></td>
                                            </tr>
                                        </s:iterator>

                                        <tr>
                                            <td>合计</td>
                                            <td><fmt:formatNumber value="${allonlineUsers/allSize }" pattern="0"/>(平均)
                                            </td>
                                            <td>${allroleUsers }</td>
                                            <td>${allnewDevices }</td>
                                            <td><fmt:formatNumber value="${allpayAmount/100 }" pattern="0.00"/></td>
                                            <td></td>
                                        </tr>
                                    </s:if>
                                    <s:else>
                                        <tr>
                                            <td colspan="14" align="center">没有数据！</td>
                                        </tr>
                                    </s:else>
                                </c:if>

                                <c:if test="${result['isCompare'] == 2 && result['groupType'] == 'statDate' }">
                                    <s:if test="result['data'].size > 0">
                                        <s:set var="addonlineUsers" value="0"></s:set>
                                        <s:set var="addroleUsers" value="0"></s:set>
                                        <s:set var="addnewDevices" value="0"></s:set>
                                        <s:set var="addpayAmount" value="0"></s:set>
                                        <s:set var="addactiveUsers" value="0"></s:set>
                                        <s:set var="dateSize" value="0"></s:set>

                                        <s:iterator value="result['data']" var="item">
                                            <s:iterator value="#item.value" var="itemVal" status="st">
                                                <s:set var="addonlineUsers"
                                                       value="#addonlineUsers + #itemVal.onlineUsers"></s:set>
                                                <s:set var="addroleUsers"
                                                       value="#addroleUsers + #itemVal.roleUsers"></s:set>
                                                <s:set var="addnewDevices"
                                                       value="#addnewDevices + #itemVal.newDevices"></s:set>
                                                <s:set var="addpayAmount"
                                                       value="#addpayAmount + #itemVal.payAmount"></s:set>
                                                <s:set var="addactiveUsers"
                                                       value="#addactiveUsers + #itemVal.activeUsers"></s:set>
                                                <s:set var="dateSize" value="#dateSize + 1"></s:set>
                                            </s:iterator>
                                        </s:iterator>
                                        <tr>
                                            <td>合计</td>
                                            <td></td>
                                            <td><fmt:formatNumber value="${addonlineUsers/dateSize }"
                                                                  pattern="0"/>(平均)
                                            </td>
                                            <td>${addroleUsers }</td>
                                            <td>${addnewDevices }</td>
                                            <td><fmt:formatNumber value="${addpayAmount/100 }" pattern="0.00"/></td>
                                            <td></td>
                                        </tr>
                                        <s:iterator value="result['data']" var="item">
                                            <s:set var="allonlineUsers" value="0"></s:set>
                                            <s:set var="allroleUsers" value="0"></s:set>
                                            <s:set var="allnewDevices" value="0"></s:set>
                                            <s:set var="allpayAmount" value="0"></s:set>
                                            <s:set var="allactiveUsers" value="0"></s:set>
                                            <s:set var="dataSize" value="0"></s:set>

                                            <s:iterator value="#item.value" var="itemVal" status="st">
                                                <s:set var="allonlineUsers"
                                                       value="#allonlineUsers + #itemVal.onlineUsers"></s:set>
                                                <s:set var="allroleUsers"
                                                       value="#allroleUsers + #itemVal.roleUsers"></s:set>
                                                <s:set var="allnewDevices"
                                                       value="#allnewDevices + #itemVal.newDevices"></s:set>
                                                <s:set var="allpayAmount"
                                                       value="#allpayAmount + #itemVal.payAmount"></s:set>
                                                <s:set var="allactiveUsers"
                                                       value="#allactiveUsers + #itemVal.activeUsers"></s:set>
                                                <s:set var="dataSize" value="#dataSize + 1"></s:set>

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
                                                    <td><s:date name="#itemVal.statDate" format="yyyy-MM-dd"/></td>
                                                    <td><s:property value="#itemVal.onlineUsers"/></td>
                                                    <td><s:property value="#itemVal.roleUsers"/></td>
                                                    <td><s:property value="#itemVal.newDevices"/></td>
                                                    <td><fmt:formatNumber value="${itemVal.payAmount/100 }"
                                                                          pattern="0.00"/></td>
                                                    <td><s:property value="activeUsers"/></td>
                                                </tr>
                                            </s:iterator>
                                            <s:if test="#item.value.size >1">
                                                <tr>
                                                    <td>合计</td>
                                                    <td><fmt:formatNumber value="${allonlineUsers/dataSize }"
                                                                          pattern="0"/>(平均)
                                                    </td>
                                                    <td>${allroleUsers }</td>
                                                    <td>${allnewDevices }</td>
                                                    <td><fmt:formatNumber value="${allpayAmount/100 }"
                                                                          pattern="0.00"/></td>
                                                    <td>${allactiveUsers }</td>
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

                                <c:if test="${result['isCompare'] == 2 && groupType == 1 }">
                                    <s:if test="result['data'].size > 0">
                                        <s:set var="addonlineUsers" value="0"></s:set>
                                        <s:set var="addroleUsers" value="0"></s:set>
                                        <s:set var="addnewDevices" value="0"></s:set>
                                        <s:set var="addpayAmount" value="0"></s:set>
                                        <s:set var="dateSize" value="0"></s:set>

                                        <s:iterator value="result['data']" var="item">
                                            <s:iterator value="#item.value" var="itemVal" status="st">
                                                <s:set var="addonlineUsers"
                                                       value="#addonlineUsers + #itemVal.onlineUsers"></s:set>
                                                <s:set var="addroleUsers"
                                                       value="#addroleUsers + #itemVal.roleUsers"></s:set>
                                                <s:set var="addnewDevices"
                                                       value="#addnewDevices + #itemVal.newDevices"></s:set>
                                                <s:set var="addpayAmount"
                                                       value="#addpayAmount + #itemVal.payAmount"></s:set>
                                                <s:set var="addactiveUsers"
                                                       value="#addactiveUsers + #itemVal.activeUsers"></s:set>
                                                <s:set var="dateSize" value="#dateSize + 1"></s:set>
                                            </s:iterator>
                                        </s:iterator>
                                        <tr>
                                            <td>合计</td>
                                            <td></td>
                                            <td><fmt:formatNumber value="${addonlineUsers/dateSize }"
                                                                  pattern="0"/>(平均)
                                            </td>
                                            <td>${addroleUsers }</td>
                                            <td>${addnewDevices }</td>
                                            <td><fmt:formatNumber value="${addpayAmount/100 }" pattern="0.00"/></td>
                                            <td></td>
                                        </tr>
                                        <s:iterator value="result['data']" var="item">
                                            <s:set var="allonlineUsers" value="0"></s:set>
                                            <s:set var="allroleUsers" value="0"></s:set>
                                            <s:set var="allnewDevices" value="0"></s:set>
                                            <s:set var="allpayAmount" value="0"></s:set>
                                            <s:set var="allactiveUsers" value="0"></s:set>
                                            <s:iterator value="#item.value" var="itemVal" status="st">
                                                <s:set var="allonlineUsers"
                                                       value="#allonlineUsers + #itemVal.onlineUsers"></s:set>
                                                <s:set var="allroleUsers"
                                                       value="#allroleUsers + #itemVal.roleUsers"></s:set>
                                                <s:set var="allnewDevices"
                                                       value="#allnewDevices + #itemVal.newDevices"></s:set>
                                                <s:set var="allpayAmount"
                                                       value="#allpayAmount + #itemVal.payAmount"></s:set>
                                                <s:set var="allactiveUsers"
                                                       value="#allactiveUsers + #itemVal.activeUsers"></s:set>
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
                                                    <td><s:property value="#itemVal.zoneName"/></td>
                                                    <td><s:property value="#itemVal.onlineUsers"/></td>
                                                    <td><s:property value="#itemVal.roleUsers"/></td>
                                                    <td><s:property value="#itemVal.newDevices"/></td>
                                                    <td><fmt:formatNumber value="${itemVal.payAmount/100 }"
                                                                          pattern="0.00"/></td>
                                                    <td><s:property value="#itemVal.activeUsers"/></td>
                                                </tr>
                                            </s:iterator>
                                            <s:if test="#item.value.size >1">
                                                <tr>
                                                    <td>合计</td>
                                                    <td>${allonlineUsers }</td>
                                                    <td>${allroleUsers }</td>
                                                    <td>${allnewDevices }</td>
                                                    <td><fmt:formatNumber value="${allpayAmount/100 }"
                                                                          pattern="0.00"/></td>
                                                    <td>${allactiveUsers }</td>
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

                                <c:if test="${result['isCompare'] == 2 && groupType == 2}">
                                    <s:if test="result['data'].size > 0">
                                        <s:set var="addonlineUsers" value="0"></s:set>
                                        <s:set var="addroleUsers" value="0"></s:set>
                                        <s:set var="addnewDevices" value="0"></s:set>
                                        <s:set var="addpayAmount" value="0"></s:set>
                                        <s:set var="dateSize" value="0"></s:set>

                                        <s:iterator value="result['data']" var="item">
                                            <s:iterator value="#item.value" var="itemVal" status="st">
                                                <s:set var="addonlineUsers"
                                                       value="#addonlineUsers + #itemVal.onlineUsers"></s:set>
                                                <s:set var="addroleUsers"
                                                       value="#addroleUsers + #itemVal.roleUsers"></s:set>
                                                <s:set var="addnewDevices"
                                                       value="#addnewDevices + #itemVal.newDevices"></s:set>
                                                <s:set var="addpayAmount"
                                                       value="#addpayAmount + #itemVal.payAmount"></s:set>
                                                <s:set var="addactiveUsers"
                                                       value="#addactiveUsers + #itemVal.activeUsers"></s:set>
                                                <s:set var="dateSize" value="#dateSize + 1"></s:set>
                                            </s:iterator>
                                        </s:iterator>
                                        <tr>
                                            <td>合计</td>
                                            <td></td>
                                            <td><fmt:formatNumber value="${addonlineUsers/dateSize }"
                                                                  pattern="0"/>(平均)
                                            </td>
                                            <td>${addroleUsers }</td>
                                            <td>${addnewDevices }</td>
                                            <td><fmt:formatNumber value="${addpayAmount/100 }" pattern="0.00"/></td>
                                            <td></td>
                                        </tr>
                                        <s:iterator value="result['data']" var="item">
                                            <s:set var="allonlineUsers" value="0"></s:set>
                                            <s:set var="allroleUsers" value="0"></s:set>
                                            <s:set var="allnewDevices" value="0"></s:set>
                                            <s:set var="allpayAmount" value="0"></s:set>
                                            <s:set var="allactiveUsers" value="0"></s:set>
                                            <s:iterator value="#item.value" var="itemVal" status="st">
                                                <s:set var="allonlineUsers"
                                                       value="#allonlineUsers + #itemVal.onlineUsers"></s:set>
                                                <s:set var="allroleUsers"
                                                       value="#allroleUsers + #itemVal.roleUsers"></s:set>
                                                <s:set var="allnewDevices"
                                                       value="#allnewDevices + #itemVal.newDevices"></s:set>
                                                <s:set var="allpayAmount"
                                                       value="#allpayAmount + #itemVal.payAmount"></s:set>
                                                <s:set var="allactiveUsers"
                                                       value="#allactiveUsers + #itemVal.activeUsers"></s:set>
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
                                                    <td><s:property value="#itemVal.platformName"/></td>
                                                    <td><s:property value="#itemVal.onlineUsers"/></td>
                                                    <td><s:property value="#itemVal.roleUsers"/></td>
                                                    <td><s:property value="#itemVal.newDevices"/></td>
                                                    <td><fmt:formatNumber value="${itemVal.payAmount/100 }"
                                                                          pattern="0.00"/></td>
                                                    <td><s:property value="#itemVal.activeUsers"/></td>
                                                </tr>
                                            </s:iterator>
                                            <s:if test="#item.value.size >1">
                                                <tr>
                                                    <td>合计</td>
                                                    <td>${allonlineUsers }</td>
                                                    <td>${allroleUsers }</td>
                                                    <td>${allnewDevices }</td>
                                                    <td><fmt:formatNumber value="${allpayAmount/100 }"
                                                                          pattern="0.00"/></td>
                                                    <td>${allactiveUsers}</td>
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

                                <c:if test="${result['isCompare'] == 1 }">
                                    <s:if test="result['data'].size > 0">
                                        <s:iterator value="result['data']" var="item" status="st">
                                            <tr>
                                                <td rowspan="2"><s:date name="statDate" format="HH:mm:ss"/></td>
                                                <td>
                                                    <c:if test="${groupType == 1 }">
                                                        <s:if test="result['gamezones'].size > 0">
                                                            <s:iterator value="result['gamezones']" var="item">
                                                                ${item.zoneName }&nbsp;
                                                            </s:iterator>
                                                        </s:if>
                                                    </c:if>
                                                    <c:if test="${groupType == 2 }">
                                                        <s:if test="result['platforms'].size > 0">
                                                            <s:iterator value="result['platforms']" var="item">
                                                                ${item.platformName }&nbsp;
                                                            </s:iterator>
                                                        </s:if>
                                                    </c:if>
                                                </td>
                                                <td><s:property value="onlineUsers"/></td>
                                                <td><s:property value="roleUsers"/></td>
                                                <td><s:property value="newDevices"/></td>
                                                <td><fmt:formatNumber value="${payAmount/100 }" pattern="0.00"/></td>
                                                <td><s:property value="activeUsers"/></td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <c:if test="${groupType == 1 }">
                                                        <s:if test="result['compareGamezones'].size > 0">
                                                            <s:iterator value="result['compareGamezones']" var="item">
                                                                ${item.zoneName }&nbsp;
                                                            </s:iterator>
                                                        </s:if>
                                                    </c:if>
                                                    <c:if test="${groupType == 2 }">
                                                        <s:if test="result['comparePlatforms'].size > 0">
                                                            <s:iterator value="result['comparePlatforms']" var="item">
                                                                ${item.platformName }&nbsp;
                                                            </s:iterator>
                                                        </s:if>
                                                    </c:if>
                                                </td>
                                                <s:if test="result['compareData'].size>0">
                                                    <td>${result['compareData'][st.index].onlineUsers }</td>
                                                    <td>${result['compareData'][st.index].roleUsers }</td>
                                                    <td>${result['compareData'][st.index].newDevices }</td>
                                                    <td><fmt:formatNumber
                                                            value="${result['compareData'][st.index].payAmount/100 }"
                                                            pattern="0.00"/></td>
                                                    <td>${result['compareData'][st.index].activeUsers }</td>
                                                </s:if>
                                                <s:else>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
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

                                <c:if test="${result['isCompare'] == 3 }">
                                    <s:if test="result['data'].size > 0">
                                        <s:iterator value="result['data']" var="item" status="st">
                                            <tr>
                                                <td rowspan="2"><s:date name="statDate" format="HH:mm:ss"/></td>
                                                <td>
                                                        ${selectRange }
                                                </td>
                                                <td><s:property value="onlineUsers"/></td>
                                                <td><s:property value="roleUsers"/></td>
                                                <td><s:property value="newDevices"/></td>
                                                <td><fmt:formatNumber value="${payAmount/100 }" pattern="0.00"/></td>
                                                <td><s:property value="activeUsers"/></td>
                                            </tr>

                                            <tr>
                                                <td>
                                                        ${compareSelectRange }
                                                </td>
                                                <s:if test="result['compareData'].size>0">
                                                    <td>${result['compareData'][st.index].onlineUsers }</td>
                                                    <td>${result['compareData'][st.index].roleUsers }</td>
                                                    <td>${result['compareData'][st.index].newDevices }</td>
                                                    <td><fmt:formatNumber
                                                            value="${result['compareData'][st.index].payAmount/100 }"
                                                            pattern="0.00"/></td>
                                                    <td>${result['compareData'][st.index].activeUsers }</td>
                                                </s:if>
                                                <s:else>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
                                                    <td>0</td>
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
            </div>
        </div>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#reportLeft_2").addClass("active");
        $("#zone").addClass("active");
        if ('${result["optionJson"]}' == "") {
            $("#chartData").html("<div align='center'>没有数据！</div>");
        } else {
            var myChart = echarts.init(document.getElementById('chartData'), 'shine');
            var option = eval('(${result["optionJson"]})');
            myChart.setOption(option);
        }
    });

    <%--require(--%>
    <%--[--%>
    <%--'echarts',--%>
    <%--'echarts/chart/line',--%>
    <%--'echarts/chart/bar',--%>
    <%--'echarts/chart/pie',--%>
    <%--'echarts/chart/funnel'--%>

    <%--],--%>
    <%--function (ec) {--%>
    <%--if ('${result["optionJson"]}' == "") {--%>
    <%--$("#chartData").html("<div align='center'>没有数据！</div>");--%>
    <%--return;--%>
    <%--}--%>
    <%--var myChart = ec.init(document.getElementById('chartData'),'shine');--%>
    <%--var option = eval('(${result["optionJson"]})');--%>
    <%--myChart.setOption(option);--%>
    <%--}--%>
    <%--);--%>

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

    function selectGame(e) {
        location.assign('reportDaily_online.shtml?gameId=' + e.value);
    }
</script>
</body>
</html>