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
            <li><a href="${ctx}/bPlatformGame/bPlatformGame_list.shtml">渠道关联列表</a></li>
            <li class="active">查看渠道关联信息</li>
        </ol>
        <div class="panel panel-default">
            <div class="panel-heading ">
                <h3 class="panel-title">${bPlatformGame.platformName}-${bPlatformGame.gameName}详情</h3>
            </div>

            <div class="form-inline popover-show panel-body list_toolbar">
                <div class="row" style="margin-bottom: 15px">
                    <div class="table-responsive" style="width: 100%;">
                        <table class="table-hover table-striped table-bordered table-condensed table-big table-game">
                            <tr>
                                <td class="td-name"><b>渠道:</b></td>
                                <td>${bPlatformGame.platformName}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>游戏:</b></td>
                                <td>${bPlatformGame.gameName}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>是否停充值:</b></td>
                                <td>
                                    <c:if test="${bPlatformGame.status==0}">否</c:if>
                                    <c:if test="${bPlatformGame.status!=0}">是</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>是否停新增:</b></td>
                                <td><c:if test="${bPlatformGame.registStatus==0}">否</c:if>
                                    <c:if test="${bPlatformGame.registStatus!=0}">是</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>分成比例:</b></td>
                                <td>${bPlatformGame.discount}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>第三方平台AppKey:</b></td>
                                <td>${bPlatformGame.appKey}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>第三方平台AppID:</b></td>
                                <td>${bPlatformGame.appID}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>第三方平台SecretKey:</b></td>
                                <td>${bPlatformGame.secretKey}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>第三方平台PublicKey:</b></td>
                                <td>${bPlatformGame.publicKey}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>第三方平台PrivateKey:</b></td>
                                <td>${bPlatformGame.privateKey}</td>
                            </tr>
                            <tr>
                                <td class="td-name"><b>其他配置参数:</b></td>
                                <td>${bPlatformGame.configParams}</td>
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
