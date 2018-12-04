<%@ page contentType="text/html;charset=UTF-8" %>
<div class="list-group">
	 
	  <mt:auth authUrl="report_summary.shtml"> <a id="reportLeft_summary" href="${ctx}/report/report_summary.shtml" class="list-group-item">综合分析</a></mt:auth>
	  <mt:auth authUrl="reportDaily_online.shtml"> <a id="reportLeft_online" href="${ctx}/report/reportDaily_online.shtml" class="list-group-item">实时在线</a></mt:auth>
	  <mt:auth authUrl="reportDaily_operate.shtml"> <a id="reportLeft_operate" href="${ctx}/report/reportDaily_operate.shtml" class="list-group-item">运营日报</a></mt:auth>
	  <mt:auth authUrl="reportDaily_basic.shtml"> <a id="reportLeft_basic" href="${ctx}/report/reportDaily_basic.shtml" class="list-group-item">基础分析</a></mt:auth>
	  <mt:auth authUrl="reportDaily_newly.shtml"> <a id="reportLeft_newly" href="${ctx}/report/reportDaily_newly.shtml" class="list-group-item">新增分析</a></mt:auth>
	  <mt:auth authUrl="reportDaily_first.shtml"> <a id="reportLeft_first" href="${ctx}/report/reportDaily_first.shtml" class="list-group-item">首次付费</a></mt:auth>
	  <mt:auth authUrl="reportDaily_keep.shtml"> <a id="reportLeft_keep" href="${ctx}/report/reportDaily_keep.shtml" class="list-group-item">留存统计</a></mt:auth>
	  <mt:auth authUrl="reportDaily_loss.shtml"> <a id="reportLeft_loss" href="${ctx}/report/reportDaily_loss.shtml" class="list-group-item">流失统计</a></mt:auth>
	  <mt:auth authUrl="reportDaily_back.shtml"> <a id="reportLeft_back" href="${ctx}/report/reportDaily_back.shtml" class="list-group-item">回流统计</a></mt:auth>
	  <mt:auth authUrl="reportDaily_change.shtml"> <a id="reportLeft_change" href="${ctx}/report/reportDaily_change.shtml" class="list-group-item">转化分析</a></mt:auth>
	  <mt:auth authUrl="report_rank.shtml"> <a id="reportLeft_rechargeRank" href="${ctx}/report/report_rank.shtml" class="list-group-item">充值排行</a></mt:auth>
	  <mt:auth authUrl="report_roleRank.shtml"> <a id="reportLeft_roleRank" href="${ctx}/report/report_roleRank.shtml" class="list-group-item">角色排行</a></mt:auth>
	  <mt:auth authUrl="reportLtv_ltv.shtml"> <a id="reportLeft_ltv" href="${ctx}/report/reportLtv_ltv.shtml" class="list-group-item">LTV统计</a></mt:auth>
</div>