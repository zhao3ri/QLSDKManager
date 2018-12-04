<%@ page contentType="text/html;charset=UTF-8" %>
<div class="list-group">
	 
	  <mt:auth authUrl="roleReport_realTimeLogin.shtml"> <a id="roleReportLeft_login" href="${ctx}/roleReport/roleReport_realTimeLogin.shtml" class="list-group-item">登陆数据</a></mt:auth>
	  <mt:auth authUrl="roleReport_online.shtml"> <a id="roleReportLeft_online" href="${ctx}/roleReport/roleReport_online.shtml" class="list-group-item">在线数据</a></mt:auth>
	  <mt:auth authUrl="roleReport_playTime.shtml"> <a id="roleReportLeft_duration" href="${ctx}/roleReport/roleReport_playTime.shtml" class="list-group-item">使用时长</a></mt:auth>
	  <mt:auth authUrl="roleReport_lossRoles.shtml"> <a id="roleReportLeft_loss" href="${ctx}/roleReport/roleReport_lossRoles.shtml" class="list-group-item">流失用户</a></mt:auth>
	  <mt:auth authUrl="roleReport_keepRoles.shtml"> <a id="roleReportLeft_keep" href="${ctx}/roleReport/roleReport_keepRoles.shtml" class="list-group-item">角色留存</a></mt:auth>
	  <mt:auth authUrl="roleReport_roleActive.shtml"> <a id="roleReportLeft_active" href="${ctx}/roleReport/roleReport_roleActive.shtml" class="list-group-item">角色活跃</a></mt:auth>
	  <mt:auth authUrl="roleReport_rechargeRealtime.shtml"> <a id="roleReportLeft_recharge" href="${ctx}/roleReport/roleReport_rechargeRealtime.shtml" class="list-group-item">充值数据</a></mt:auth>
	  <mt:auth authUrl="roleReport_activeRecharge.shtml"> <a id="roleReportLeft_activeRecharge" href="${ctx}/roleReport/roleReport_activeRecharge.shtml" class="list-group-item">活跃充值</a></mt:auth>
	  <mt:auth authUrl="roleReport_rechargeRegion.shtml"> <a id="roleReportLeft_rechargeRegion" href="${ctx}/roleReport/roleReport_rechargeRegion.shtml" class="list-group-item">充值区间</a></mt:auth>
	  <mt:auth authUrl="dataReport_dataReport.shtml"> <a id="roleReportLeft_gather" href="${ctx}/dataReport/dataReport_daily.shtml" class="list-group-item">数据汇总</a></mt:auth>
</div>