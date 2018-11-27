package com.item.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.item.domain.BAccount;
import com.item.domain.BPlatform;
import com.item.service.BAccountService;
import com.item.service.BPlatformService;
import com.item.utils.DateUtils;
import com.item.utils.Excel;
import com.item.utils.ExcelExport;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.web.Struts2Action;

public class BAccountAction extends Struts2Action {

	private static final long serialVersionUID = 1L;

	@Autowired
	private BAccountService bAccountService;
	@Autowired
	private BPlatformService platformService;

	private Page<BAccount> accountPage = new Page<BAccount>(10);
	private BAccount account;
	private List<BPlatform> platforms;

	public String list() {
		MapBean mb = search();
		platforms = platformService.getCurrentIdentityChannelList();

		if (account == null) {
			account = new BAccount();
		}
		accountPage = bAccountService.page(accountPage, mb);
		return "list";
	}

	public void excelExport() {
		try {
			MapBean mb = search();
			List<BAccount> list = bAccountService.list(mb);
			ExcelExport ee = new ExcelExport();
			ee.setHead("游戏玩家统计报表_"
					+ DateUtils.format(new Date(), "yyyyMMddHHmmss"));
			ee.getEl().add("平台ID");
			ee.getEl().add("平台名称");
			ee.getEl().add("UID");
			ee.getEl().add("创建时间");

			for (int i = 0; i < list.size(); i++) {
				List<Excel> e = new ArrayList<Excel>();
				e.add(new Excel(list.get(i).getPlatformId(), 20));
				e.add(new Excel(list.get(i).getPlatformName(), 20));
				e.add(new Excel(list.get(i).getUid(), 20));
				e.add(new Excel(DateUtils.format(list.get(i).getCreateTime()),
						20));
				ee.getEll().add(e);
			}
			ee.setFoot("总共：" + list.size() + "条记录");
			ee.excelExport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private MapBean search() {
		MapBean mb = new MapBean();
		if (account != null) {
			if (account.getPlatformId() != null) {
				mb.put("platformId", account.getPlatformId());
			}
			if (StringUtils.isNotBlank(account.getUid())) {
				mb.put("uid", account.getUid());
			}
		}
		mb.put("orderby", "a.createTime desc");
		return mb;
	}

	public Page<BAccount> getAccountPage() {
		return accountPage;
	}

	public void setAccountPage(Page<BAccount> accountPage) {
		this.accountPage = accountPage;
	}

	public BAccount getAccount() {
		return account;
	}

	public void setAccount(BAccount account) {
		this.account = account;
	}

	public List<BPlatform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<BPlatform> platforms) {
		this.platforms = platforms;
	}
}
