package com.item.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.item.constants.Constants;
import com.item.domain.BChannel;
import com.item.domain.SBalance;
import com.item.service.authority.AuthCacheManager;
import core.module.utils.Struts2Utils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.item.service.BChannelService;
import com.item.utils.InitSearchCondition;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.web.Struts2Action;

@Action
public class BChannelAction extends Struts2Action {

    private static final long serialVersionUID = 1L;

    @Autowired
    private BChannelService bChannelService;

    private BChannel bChannel;
    private Page<BChannel> bChannelPage = new Page<BChannel>(10);
    private Integer keepSearchCondition;
    private Long id;
    private List<BChannel> bChannels;
    private InputStream channelNameIsExisted;
    private List<SBalance> balanceList;
    private Page<SBalance> pageb = new Page<SBalance>(10);

    public String list() {
//		bChannels = bPlatformService.getAllChannel();
        bChannels = bChannelService.getCurrentIdentityChannelList();

        bChannel = InitSearchCondition.initEntity(bChannel, keepSearchCondition, "bChannel");
        bChannelPage = InitSearchCondition.initPage(bChannelPage, keepSearchCondition, "bChannel");

        MapBean mb = search();
        bChannelPage = bChannelService.pagePlarform(bChannelPage, mb);
        return "list";
    }

    public String listbalance() {
//		bChannels = bPlatformService.getAllChannel();
        bChannels = bChannelService.getCurrentIdentityChannelList();
        MapBean mb = search();
        pageb = bChannelService.pageBalance(pageb, mb);

        logger.info("size :" + pageb.getResult().size());
        return "listbalance";
    }

//    private MapBean balanceSearch() {
//        MapBean mb = getParams();
//        if (bChannel != null) {
//            if (bChannel.getId() != null) {
//                mb.put("id", bChannel.getId());
//            }
//            if (bChannel.getCreateTime() != null) {
//                mb.put("createTime", bChannel.getCreateTime());
//            }
//        }
//        mb.put("orderby", "id desc");
//        return mb;
//    }

    private MapBean search() {
        MapBean mb = getParams();
        if (bChannel != null) {
            if (bChannel.getId() != null) {
                mb.put("id", bChannel.getId());
            }
            if (bChannel.getChannelName() != null && "".equals(bChannel.getChannelName()) == false) {
                mb.put(MapBean.CHANNEL_NAME, bChannel.getChannelName());
            }
            if (bChannel.getCreateTime() != null) {
                mb.put("createTime", bChannel.getCreateTime());
            }
        }
        mb.put("orderby", "id desc");
        return mb;
    }

    private MapBean getParams() {
        MapBean mb = new MapBean();
        if (bChannels == null) {
            bChannels = bChannelService.getCurrentIdentityChannelList();
        }
        List<Long> channelIds = new ArrayList<>();
        for (BChannel channel : bChannels) {
            channelIds.add(channel.getId());
        }
        mb.put(MapBean.CHANNEL_IDS, channelIds);
        return mb;
    }

    public String handle() {
        if (id != null) {
            bChannel = bChannelService.getChannelById(id);
        }
        return "handle";
    }

    public String balance() {
        if (id != null) {
            bChannel = bChannelService.getChannelById(id);
        }
        return "balance";
    }

    public String addOrder() {
        String userName = (String) Struts2Utils.getRequest().getSession().getAttribute("sessionUser");
        int status = bChannelService.controllerBalance(bChannel.getAmount(), bChannel.getAmountType(), bChannel.getId(), userName);
        return "savebalance";
    }

    public String save() {
        if (bChannel.getId() != null) {
            bChannelService.updateChannel(bChannel);
            addActionMessage("修改信息成功");
        } else {
            bChannel.setCreateTime(new Date());
            bChannelService.saveChannel(bChannel);
            Long currentIdentityId = AuthCacheManager.getInstance().getUser().getIdentityId();
            //新增的渠道添加管理者
            bChannelService.saveIdentityChannel(currentIdentityId, bChannel.getId());
            if (currentIdentityId != Constants.ADMIN_IDENTITY_ID) {
                //添加超级管理员管理权限
                bChannelService.saveIdentityChannel((long) Constants.ADMIN_IDENTITY_ID, bChannel.getId());
            }
            addActionMessage("保存信息成功");
        }
        return "save";
    }

    public String delete() {
        bChannelService.deleteChannel(id);
        return "delete";
    }

    public String channelNameIsExisted() {
        MapBean mb = search();
        Long channelCount = bChannelService.getChannelCountByName(mb);
        if (channelCount > 0) {
            try {
                channelNameIsExisted = new ByteArrayInputStream("1".getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                channelNameIsExisted = new ByteArrayInputStream("0".getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "channelNameIsExisted";
    }

    public Long getId() {
        return id;
    }

    public InputStream getChannelNameIsExisted() {
        return channelNameIsExisted;
    }

    public void setChannelNameIsExisted(InputStream channelNameIsExisted) {
        this.channelNameIsExisted = channelNameIsExisted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BChannelService getBChannelService() {
        return bChannelService;
    }

    public void setBChannelService(BChannelService bChannelService) {
        this.bChannelService = bChannelService;
    }

    public BChannel getBChannel() {
        return bChannel;
    }

    public void setBChannel(BChannel bChannel) {
        this.bChannel = bChannel;
    }

    public Page<BChannel> getBChannelPage() {
        return bChannelPage;
    }

    public void setBChannelPage(Page<BChannel> page) {
        this.bChannelPage = page;
    }

    public Integer getKeepSearchCondition() {
        return keepSearchCondition;
    }

    public void setKeepSearchCondition(Integer keepSearchCondition) {
        this.keepSearchCondition = keepSearchCondition;
    }

    public List<BChannel> getBChannels() {
        return bChannels;
    }

    public BChannel getbChannel() {
        return bChannel;
    }

    public void setbChannel(BChannel bChannel) {
        this.bChannel = bChannel;
    }

    public Page<BChannel> getbChannelPage() {
        return bChannelPage;
    }

    public void setbChannelPage(Page<BChannel> bChannelPage) {
        this.bChannelPage = bChannelPage;
    }

    public List<BChannel> getbChannels() {
        return bChannels;
    }

    public void setbChannels(List<BChannel> bChannels) {
        this.bChannels = bChannels;
    }

    public List<SBalance> getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(List<SBalance> balanceList) {
        this.balanceList = balanceList;
    }

    public void setBChannels(List<BChannel> bChannels) {
        this.bChannels = bChannels;
    }


    public Page<SBalance> getPageb() {
        return pageb;
    }

    public void setPageb(Page<SBalance> pageb) {
        this.pageb = pageb;
    }
}
