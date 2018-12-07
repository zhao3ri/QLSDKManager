<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/jquerylibs.jsp" %>
<body class="fix_top_nav_padding">
<div id="wrap">
    <%@ include file="/common/header.jsp" %>
    <div class="container">
        <ol class="breadcrumb row">
            <li><i class="icon-home"></i> <a href="${ctx}/index.shtml">首页</a></li>
            <li class="active">渠道管理</li>
            <li><a href="${ctx}/bChannelGame/bChannelGame_list.shtml">渠道关联列表</a></li>
            <s:if test="id==null">
                <li class="active">新增渠道关联信息</li>
            </s:if>
            <s:else>
                <li class="active">修改渠道关联信息</li>
            </s:else>
        </ol>
        <div class="panel panel-default">
            <div class="panel-heading ">
                <h3 class="panel-title">${id==null?"新增":"修改"}渠道关联信息</h3>
            </div>
            <form role="form" id="inputForm" action="bChannelGame_save.shtml" method="post">
                <div class="panel-body ">
                    <div class=" tooltip-show form-horizontal">
                        <input type="hidden" name="BChannelGame.id" value="${id}"/>

                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"><b
                                    class="color_red">*</b>渠道：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <c:if test="${id == null }">
                                    <select class="form-control" name="BChannelGame.channelId" data-original-title=""
                                            title="">
                                        <option value="">请选择渠道</option>
                                        <s:iterator value="bChannels" var="item">
                                            <option value="${item.id}"
                                                    <c:if test="${item.id==bChannelGame.channelId}">selected</c:if>>${item.channelName }</option>
                                        </s:iterator>
                                    </select>
                                </c:if>
                                <c:if test="${id != null }">
                                    <input type="hidden" name="BChannelGame.channelId"
                                           value="${bChannelGame.channelId}"/>
                                    ${bChannelGame.channelName }
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"><b
                                    class="color_red">*</b>游戏：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <c:if test="${id == null }">
                                    <select class="form-control" name="BChannelGame.gameId" data-original-title=""
                                            title="">
                                        <option value="">请选择游戏</option>
                                        <s:iterator value="games" var="item">
                                            <option value="${item.id}"
                                                    <c:if test="${item.id==bChannelGame.gameId}">selected</c:if>>${item.gameName }</option>
                                        </s:iterator>
                                    </select>
                                </c:if>
                                <c:if test="${id != null }">
                                    <input type="hidden" name="BChannelGame.gameId" value="${bChannelGame.gameId}"/>
                                    ${bChannelGame.gameName }
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"><b
                                    class="color_red">*</b>是否停充值：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <mt:selectState name="BChannelGame.status" showType="select" stateType="yesNo"
                                                value="${bChannelGame.status}" clazz="form-control"
                                                emptyString="--是否停充值--"/>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"><b
                                    class="color_red">*</b>是否停新增：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <mt:selectState name="BChannelGame.registStatus" showType="select" stateType="yesNo"
                                                value="${bChannelGame.registStatus}" clazz="form-control"
                                                emptyString="--是否停新增--"/>
                            </div>
                        </div>

                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"><b
                                    class="color_red">*</b>分成比例：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <input class="form-control" name="BChannelGame.discount" id="bChannelGame.discount"
                                       value="${bChannelGame.discount}" placeholder="给渠道的百分比如3折为 30"></input>
                            </div>
                        </div>

                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>第三方渠道AppKey：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <input class="form-control" name="BChannelGame.appKey"
                                       id="${bChannelGame.appKey }">
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"></b>第三方渠道AppID：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <input class="form-control" name="BChannelGame.appID"
                                       id="${bChannelGame.appID }">
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>第三方渠道SecretKey：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <textarea class="form-control" name="BChannelGame.secretKey"
                                          id="bChannelGame.secretKey">${bChannelGame.secretKey }</textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"></b>第三方渠道PublicKey：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <textarea class="form-control" name="BChannelGame.publicKey"
                                          id="bChannelGame.publicKey">${bChannelGame.publicKey }</textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"></b>第三方渠道PrivateKey：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <textarea class="form-control" name="BChannelGame.privateKey"
                                          id="bChannelGame.privateKey">${bChannelGame.privateKey }</textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right">其他配置参数：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <textarea class="form-control" name="BChannelGame.configParams"
                                          id="bChannelGame.configParams"
                                          placeholder="填入格式为：paramName1=value1;paramName2=value2;paramName3=value3;">${bChannelGame.configParams }</textarea>
                            </div>
                        </div>
                        <%--<div class="form-group clearfix">--%>
                        <%--<label class="control-label col-sm-3 col-lg-2 text-right"></b>NotifyUrl：</label>--%>
                        <%--<div class=" col-sm-9 col-lg-5">--%>
                        <%--<textarea class="form-control" name="BChannelGame.notifyUrl"--%>
                        <%--id="bChannelGame.notifyUrl">${bChannelGame.notifyUrl }</textarea>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    </div>
                </div>
                <div class="panel-footer">
                    <div class=" text-center">
                        <button type="submit" class="btn btn-primary"><i class="icon-ok"></i> 提交</button>
                        <a class="btn btn-default" href="bChannelGame_list.shtml"><i class="icon-remove"></i> 取消</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        appendValidate();
        $("#inputForm").validate({
            rules: {
                "BChannelGame.configParams": {required: true, maxlength: 3072},
                "BChannelGame.channelId": {required: true},
                "BChannelGame.gameId": {required: true}
            }
        });
    });
</script>
</body>
</html>