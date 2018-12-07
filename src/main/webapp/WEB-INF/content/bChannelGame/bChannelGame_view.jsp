<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
<div id="wrap">
    <%@ include file="/common/header.jsp" %>
    <style type="text/css">
        .table-game {
            width: 90%;
            margin: auto;
            table-layout: fixed;
            word-break: break-all;
            word-wrap: break-word;
        }
        .td-name{
            width: 250px;
        }
    </style>
    <div class="container">
        <ol class="breadcrumb row">
            <li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
            <li class="active">渠道管理</li>
            <li><a href="${ctx}/bChannelGame/bChannelGame_list.shtml">渠道关联列表</a></li>
            <li class="active">查看渠道关联信息</li>
        </ol>
        <div class="panel panel-default">
            <div class="panel-heading ">
                <h3 class="panel-title">${bChannelGame.channelName}-${bChannelGame.gameName}详情</h3>
            </div>

            <div class="form-inline popover-show panel-body list_toolbar">
                <div class="row" style="margin-bottom: 15px">
                    <div class="table-responsive" style="width: 100%;">
                        <table class="table-hover table-striped table-bordered table-condensed table-big table-game">
                            <tr>
                                <td class="td-name"><b>渠道:</b></td>
                                <td>${bChannelGame.channelName}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>游戏:</b></td>
                                <td>${bChannelGame.gameName}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>是否停充值:</b></td>
                                <td>
                                    <c:if test="${bChannelGame.status==0}">否</c:if>
                                    <c:if test="${bChannelGame.status!=0}">是</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>是否停新增:</b></td>
                                <td><c:if test="${bChannelGame.registStatus==0}">否</c:if>
                                    <c:if test="${bChannelGame.registStatus!=0}">是</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>分成比例:</b></td>
                                <td>${bChannelGame.discount}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>第三方渠道AppKey:</b></td>
                                <td>${bChannelGame.appKey}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>第三方渠道AppID:</b></td>
                                <td>${bChannelGame.appID}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>第三方渠道SecretKey:</b></td>
                                <td>${bChannelGame.secretKey}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>第三方渠道PublicKey:</b></td>
                                <td>${bChannelGame.publicKey}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>第三方渠道PrivateKey:</b></td>
                                <td>${bChannelGame.privateKey}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>其他配置参数:</b></td>
                                <td>${bChannelGame.configParams}</td>
                            </tr>

                        </table>
                    </div>
                </div>

                <div class=" text-center">
                    <a class="btn btn-primary" style="margin-bottom: 20px" href="javascript:history.go(-1)"> 返回</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
</body>
</html>
