<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script src="/js/echart/echarts.min.js"></script>
<script src="/js/echart/shine.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        if (<%=PropertyUtils.get("authority.control").equals("false") %>) {
            var roleAuthStr = '${roleAuthStr }';
            // 遍历所有头部标签
            $(".menuTop .dropdown-menu").each(function () {
                var count = 0;
                // 对比权限有没有这个URL
                $(this).find("a").each(function () {
                    var targetUrl = getTargetUrl($(this).attr("href"));
                    //如果当前的链接存在于权限字符串中，则可访问
                    if (roleAuthStr.indexOf(targetUrl + ';') != -1) {
                        count++;	//可访问数量加1
                    } else {
                        $(this).parent('li').remove();
                    }
                });

                if (count == 0) {
                    if ($(this).find("a").attr("href") != '${ctx}/index.shtml') {
                        $(this).parent('.menuTop').remove();
                    }
                }
            });
        }

    });

    //获取不带命名空间的请求url
    function getTargetUrl(hrefValue) {
        var url = hrefValue;
        //去掉"?"以后的所有参数
        if (url.indexOf("?") != -1) {
            url = url.substring(0, url.indexOf("?"));
        }
        //去掉命名空间
        url = url.substr(url.lastIndexOf("/") + 1);
        return url;
    }

    <%
    // jsp文件路径
    String tempPath = request.getServletPath();
    // 设置参数值
    setTempPath(tempPath);
    %>
    <%!
    String tempPath = "";
    public void setTempPath(String f){
        this.tempPath = f;
    }
    public String getSelected(String key){
        String selected = "";
        String[] keys = key.split(",");
        for(String s : keys){
            if(tempPath.indexOf("/"+s+"/") != -1 || (s.equals("index") && tempPath.indexOf("index") != -1)){
                selected  = "active";
                return selected;
            }
        }
        return selected;
    }
    %>

    function isExit() {
        if (confirm('确定注销登录吗?')) {
            window.location.href = "${ctx}/logout.shtml";
        }
    }
</script>

<script>
    $(document).ready(function () {
        jQuery('ul.nav li.dropdown').hover(function () {
            jQuery(this).find('.dropdown-menu:first').stop(true, true).delay(50).fadeIn();
        }, function () {
            jQuery(this).find('.dropdown-menu:first').stop(true, true).delay(50).fadeOut();
        });

    });
</script>

<div class="container">
    <iframe src="/common/refresh.jsp" width="0" height="0" style="display: none"></iframe>
    <nav class="navbar navbar-default navbar-static-top row" role="navigation">

        <!-- 小屏幕菜单开始 -->
        <div class="navbar-header">
            <button type="button" class="btn-default navbar-toggle " data-toggle="collapse"
                    data-target=".navbar-ex1-collapse"><span class="sr-only">主菜单</span> <i class=" icon-th-large "></i>
                菜单
            </button>
            <a class="navbar-brand top_nav_logo" href="#"><s:text name="sys.project.name"/></a></div>

        <!-- 小屏幕菜单开结束 -->
        <!-- 大屏幕时显示菜单内容，小屏时自动折叠显示。 -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav  navbar-right">
                <li class="text-center">
                    <div class="btn-group navbar-btn tooltip-show">
                        <div class="btn-group ">
                            <button type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown"><i
                                    class="icon-user "></i><span
                                    class="badge badge-nobg">${sessionUserInfo==null?session.sessionUser:session.sessionUserInfo.realName }</span>
                                <b class="caret"></b></button>
                            <ul class="dropdown-menu text-left">
                                <li><a href="${ctx}/user/user_updateMyInfo.shtml?user.id=${sessionUserInfo.id }"><i
                                        class="icon-user"></i> 个人资料</a></li>
                                <li><a href="${ctx}/user/user_updateMyPassword.shtml?user.id=${sessionUserInfo.id }"><i
                                        class="icon-key"></i> 修改密码</a></li>
                                <%-- <li><a href="${ctx}/user/user_clearPassword.shtml"><i class="icon-remove-circle"></i> 消除记住密码</a></li> --%>
                            </ul>
                        </div>
                        <a href="javascript:return();" onclick="isExit()" class="btn btn-danger" data-toggle="tooltip"
                           data-placement="bottom" title="" data-original-title="退出登录"><i class="icon-off"></i></a>
                    </div>
                </li>
            </ul>
            <ul class="nav  nav-pills navbar-form  navbar-left">
                <li class="menuTop <% out.print(getSelected("index")); %>"><a href="${ctx}/index.shtml">首页</a></li>

                <!--渠道管理-->
                <mt:auths authUrls="bChannel_list.shtml,bChannelGame_list.shtml">
                    <li class="menuTop dropdown <% out.print(getSelected("bChannel")); %>"><a href="#"
                                                                                               class="dropdown-toggle"
                                                                                               data-toggle="dropdown">渠道统计<b
                            class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <mt:auth authUrl="bChannel_list.shtml">
                                <li><a href="${ctx}/bChannel/bChannel_list.shtml">渠道管理</a></li>
                            </mt:auth>
                            <mt:auth authUrl="bChannelGame_list.shtml">
                                <li><a href="${ctx}/bChannelGame/bChannelGame_list.shtml">渠道关联管理</a></li>
                            </mt:auth>
                            <mt:auth authUrl="bChannel_listbalance.shtml">
                                <li><a href="${ctx}/bChannel/bChannel_listbalance.shtml">加币记录</a></li>
                            </mt:auth>
                        </ul>
                    </li>
                </mt:auths>

                <!-- 游戏管理 -->
                <mt:auths authUrls="bGame_list.shtml,bGamezone_list.shtml,bRole_list.shtml">
                    <li class="menuTop dropdown <% out.print(getSelected("bGame")); %>"><a href="#"
                                                                                           class="dropdown-toggle"
                                                                                           data-toggle="dropdown">游戏管理<b
                            class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <mt:auth authUrl="bGame_list.shtml">
                                <li><a href="${ctx}/bGame/bGame_list.shtml">游戏列表</a></li>
                            </mt:auth>
                            <mt:auth authUrl="bGame_list.shtml">
                                <li><a href="${ctx}/bGamezone/bGamezone_list.shtml">游戏分区列表</a></li>
                            </mt:auth>
                            <mt:auth authUrl="bRole_list.shtml">
                                <li><a href="${ctx}/bRole/bRole_list.shtml">游戏角色列表</a></li>
                            </mt:auth>
                                <%-- <mt:auth authUrl="bRole_list.shtml">
                                        <li><a href="${ctx}/bAccount/bAccount_list.shtml">游戏玩家列表</a></li>
                                </mt:auth> --%>
                        </ul>
                    </li>
                </mt:auths>

                <!--运营数据-->
                <mt:auths authUrls="report_summary.shtml">
                    <li class="menuTop dropdown <% out.print(getSelected("report")); %>"><a href="#"
                                                                                            class="dropdown-toggle"
                                                                                            data-toggle="dropdown">运营数据<b
                            class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <mt:auth authUrl="report_summary.shtml">
                                <li><a href="${ctx}/report/report_summary.shtml">运营数据</a></li>
                            </mt:auth>
                        </ul>
                    </li>
                </mt:auths>

                <!--游戏数据-->
                <mt:auths authUrls="roleReport_realTimeLogin.shtml">
                    <li class="menuTop dropdown <% out.print(getSelected("roleReport")); %>"><a href="#"
                                                                                                class="dropdown-toggle"
                                                                                                data-toggle="dropdown">游戏数据<b
                            class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <mt:auth authUrl="roleReport_realTimeLogin.shtml">
                                <li><a href="${ctx}/roleReport/roleReport_keepRoles.shtml">游戏数据</a></li>
                            </mt:auth>
                        </ul>
                    </li>
                </mt:auths>

                <!--充值管理-->
                <mt:auths authUrls="bOrder_list.shtml">
                    <li class="menuTop dropdown <% out.print(getSelected("bOrder")); %>"><a href="#"
                                                                                            class="dropdown-toggle"
                                                                                            data-toggle="dropdown">充值管理<b
                            class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <mt:auth authUrl="bOrder_list.shtml">
                                <li><a href="${ctx}/bOrder/bOrder_list.shtml">充值管理</a></li>
                            </mt:auth>
                        </ul>
                    </li>
                </mt:auths>

                <!--权限管理-->
                <mt:auths
                        authUrls="user_list.shtml,identity_list.shtml,module_list.shtml,function_list.shtml,data_list.shtml,dictionary_list.shtml,systool_cleanCache.shtml">
                    <li class="menuTop dropdown <% out.print(getSelected("authority,dictionary,history,systool")); %>">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">权限管理<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <mt:auth authUrl="user_list.shtml">
                                <li><a href="${ctx}/user/user_list.shtml">管理员列表</a></li>
                            </mt:auth>
                            <mt:auth authUrl="identity_list.shtml">
                                <li><a href="${ctx}/identity/identity_list.shtml">身份列表</a></li>
                            </mt:auth>
                            <mt:auth authUrl="module_list.shtml">
                                <li><a href="${ctx}/module/module_list.shtml">模块列表</a></li>
                            </mt:auth>
                            <mt:auth authUrl="function_list.shtml">
                                <li><a href="${ctx}/function/function_list.shtml">功能列表</a></li>
                            </mt:auth>
                                <%--  <mt:auth authUrl="data_list.shtml">
                                     <li><a href="${ctx}/data/data_list.shtml">数据权限列表</a></li>
                                 </mt:auth> --%>
                            <mt:auth authUrl="dictionary_list.shtml">
                                <li role="presentation" class="divider"></li>
                                <li><a href="${ctx}/dictionary/dictionary_list.shtml">部门列表</a></li>
                            </mt:auth>
                                <%--  <mt:auth authUrl="history_list1.shtml">
                                   <li role="presentation" class="divider"></li>
                                   <li><a href="${ctx}/history/history_list1.shtml?t=${r}&history.oid=<s:property value="#session.sessionUserInfo.id"/>">操作记录</a></li>
                                 </mt:auth> --%>
                            <mt:auth authUrl="systool_cleanCache.shtml">
                                <li role="presentation" class="divider"></li>
                                <li><a href="${ctx}/systool/systool_cleanCache.shtml">清除缓存</a></li>
                            </mt:auth>
                        </ul>
                    </li>
                </mt:auths>
            </ul>
        </div>
        <!-- 大屏幕显示菜单内容结束 -->

    </nav>

</div>