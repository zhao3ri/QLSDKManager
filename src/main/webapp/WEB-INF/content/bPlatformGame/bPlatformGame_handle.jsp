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
            <li><a href="${ctx}/bPlatformGame/bPlatformGame_list.shtml">渠道关联列表</a></li>
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
            <form identity="form" id="inputForm" action="bPlatformGame_save.shtml" method="post">
                <div class="panel-body ">
                    <div class=" tooltip-show form-horizontal">
                        <input type="hidden" name="BPlatformGame.id" value="${id}"/>

                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"><b
                                    class="color_red">*</b>渠道：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <c:if test="${id == null }">
                                    <select class="form-control" name="BPlatformGame.platformId" data-original-title=""
                                            title="">
                                        <option value="">请选择渠道</option>
                                        <s:iterator value="bPlatforms" var="item">
                                            <option value="${item.id}"
                                                    <c:if test="${item.id==bPlatformGame.platformId}">selected</c:if>>${item.platformName }</option>
                                        </s:iterator>
                                    </select>
                                </c:if>
                                <c:if test="${id != null }">
                                    <input type="hidden" name="BPlatformGame.platformId"
                                           value="${bPlatformGame.platformId}"/>
                                    ${bPlatformGame.platformName }
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"><b
                                    class="color_red">*</b>游戏：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <c:if test="${id == null }">
                                    <select class="form-control" name="BPlatformGame.gameId" data-original-title=""
                                            title="">
                                        <option value="">请选择游戏</option>
                                        <s:iterator value="games" var="item">
                                            <option value="${item.id}"
                                                    <c:if test="${item.id==bPlatformGame.gameId}">selected</c:if>>${item.gameName }</option>
                                        </s:iterator>
                                    </select>
                                </c:if>
                                <c:if test="${id != null }">
                                    <input type="hidden" name="BPlatformGame.gameId" value="${bPlatformGame.gameId}"/>
                                    ${bPlatformGame.gameName }
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"><b
                                    class="color_red">*</b>是否停充值：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <mt:selectState name="BPlatformGame.status" showType="select" stateType="yesNo"
                                                value="${BPlatformGame.status}" clazz="form-control"
                                                emptyString="--是否停充值--"/>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"><b
                                    class="color_red">*</b>是否停新增：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <mt:selectState name="BPlatformGame.registStatus" showType="select" stateType="yesNo"
                                                value="${BPlatformGame.registStatus}" clazz="form-control"
                                                emptyString="--是否停新增--"/>
                            </div>
                        </div>

                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"><b
                                    class="color_red">*</b>分成比例：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <input class="form-control" name="BPlatformGame.discount" id="bPlatformGame.discount"
                                       value="${bPlatformGame.discount}" placeholder="给渠道的百分比如3折为 30"></input>
                            </div>
                        </div>

                        <%--<div class="form-group clearfix">--%>
                        <%--<label class="control-label col-sm-3 col-lg-2 text-right"><b--%>
                        <%--class="color_red">*</b>配置参数：</label>--%>
                        <%--<div class=" col-sm-9 col-lg-5">--%>
                        <%--<textarea class="form-control" name="BPlatformGame.configParams"--%>
                        <%--id="bPlatformGame.configParams">${bPlatformGame.configParams }</textarea>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>第三方平台AppKey：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <textarea class="form-control" name="BPlatformGame.appKey"
                                          id="bPlatformGame.appKey">${bPlatformGame.appKey }</textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"></b>第三方平台AppID：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <textarea class="form-control" name="BPlatformGame.appID"
                                          id="bPlatformGame.appID">${bPlatformGame.appID }</textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"><b class="color_red">*</b>第三方平台SecretKey：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <textarea class="form-control" name="BPlatformGame.secretKey"
                                          id="bPlatformGame.secretKey">${bPlatformGame.secretKey }</textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"></b>第三方平台PublicKey：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <textarea class="form-control" name="BPlatformGame.publicKey"
                                          id="bPlatformGame.publicKey">${bPlatformGame.publicKey }</textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-sm-3 col-lg-2 text-right"></b>第三方平台PrivateKey：</label>
                            <div class=" col-sm-9 col-lg-5">
                                <textarea class="form-control" name="BPlatformGame.privateKey"
                                          id="bPlatformGame.privateKey">${bPlatformGame.privateKey }</textarea>
                            </div>
                        </div>
                        <%--<div class="form-group clearfix">--%>
                            <%--<label class="control-label col-sm-3 col-lg-2 text-right"></b>NotifyUrl：</label>--%>
                            <%--<div class=" col-sm-9 col-lg-5">--%>
                                <%--<textarea class="form-control" name="BPlatformGame.notifyUrl"--%>
                                          <%--id="bPlatformGame.notifyUrl">${bPlatformGame.notifyUrl }</textarea>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                </div>
                <div class="panel-footer">
                    <div class=" text-center">
                        <button type="submit" class="btn btn-primary"><i class="icon-ok"></i> 提交</button>
                        <a class="btn btn-default" href="bPlatformGame_list.shtml"><i class="icon-remove"></i> 取消</a>
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
                "BPlatformGame.configParams": {required: true, maxlength: 3072},
                "BPlatformGame.platformId": {required: true},
                "BPlatformGame.gameId": {required: true}
            }
        });
    });
</script>
</body>
</html>