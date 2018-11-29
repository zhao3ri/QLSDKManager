package com.item.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.item.constants.Constants;
import com.item.domain.SBalance;
import com.item.service.authority.AuthCacheManager;
import core.module.utils.Struts2Utils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.item.domain.BPlatform;
import com.item.service.BPlatformService;
import com.item.utils.InitSearchCondition;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.web.Struts2Action;

@Action
public class BPlatformAction extends Struts2Action {

    private static final long serialVersionUID = 1L;

    @Autowired
    private BPlatformService bPlatformService;

    private BPlatform bPlatform;
    private Page<BPlatform> bPlatformPage = new Page<BPlatform>(10);
    private Integer keepSearchCondition;
    private Long id;
    private List<BPlatform> bPlatforms;
    private InputStream platformNameIsExisted;
    private List<SBalance> balanceList;
    private Page<SBalance> pageb = new Page<SBalance>(10);

    public String list() {
//		bPlatforms = bPlatformService.getAllPlatform();
        bPlatforms = bPlatformService.getCurrentIdentityChannelList();

        bPlatform = InitSearchCondition.initEntity(bPlatform, keepSearchCondition, "bPlatform");
        bPlatformPage = InitSearchCondition.initPage(bPlatformPage, keepSearchCondition, "bPlatform");

        MapBean mb = search();
        bPlatformPage = bPlatformService.pagePlarform(bPlatformPage, mb);
        return "list";
    }

    public String listbalance() {
//		bPlatforms = bPlatformService.getAllPlatform();
        bPlatforms = bPlatformService.getCurrentIdentityChannelList();
        MapBean mb = search();
        pageb = bPlatformService.pageBalance(pageb, mb);

        logger.info("size :" + pageb.getResult().size());
        return "listbalance";
    }

//    private MapBean balanceSearch() {
//        MapBean mb = getParams();
//        if (bPlatform != null) {
//            if (bPlatform.getId() != null) {
//                mb.put("id", bPlatform.getId());
//            }
//            if (bPlatform.getCreateTime() != null) {
//                mb.put("createTime", bPlatform.getCreateTime());
//            }
//        }
//        mb.put("orderby", "id desc");
//        return mb;
//    }

    private MapBean search() {
        MapBean mb = getParams();
        if (bPlatform != null) {
            if (bPlatform.getId() != null) {
                mb.put("id", bPlatform.getId());
            }
            if (bPlatform.getPlatformName() != null && "".equals(bPlatform.getPlatformName()) == false) {
                mb.put("platformName", bPlatform.getPlatformName());
            }
            if (bPlatform.getCreateTime() != null) {
                mb.put("createTime", bPlatform.getCreateTime());
            }
        }
        mb.put("orderby", "id desc");
        return mb;
    }

    private MapBean getParams() {
        MapBean mb = new MapBean();
        if (bPlatforms == null) {
            bPlatforms = bPlatformService.getCurrentIdentityChannelList();
        }
        List<Long> channelIds = new ArrayList<>();
        for (BPlatform channel : bPlatforms) {
            channelIds.add(channel.getId());
        }
        mb.put("platformIds", channelIds);
        return mb;
    }

    public String handle() {
        if (id != null) {
            bPlatform = bPlatformService.getPlatformById(id);
        }
        return "handle";
    }

    public String balance() {
        if (id != null) {
            bPlatform = bPlatformService.getPlatformById(id);
        }
        return "balance";
    }

    public String addOrder() {
        String userName = (String) Struts2Utils.getRequest().getSession().getAttribute("sessionUser");
        int status = bPlatformService.controllerBalance(bPlatform.getAmount(), bPlatform.getAmountType(), bPlatform.getId(), userName);
        return "savebalance";
    }

    public String save() {
        if (bPlatform.getId() != null) {
            bPlatformService.updatePlatform(bPlatform);
            addActionMessage("修改信息成功");
        } else {
            bPlatform.setCreateTime(new Date());
            bPlatformService.savePlatform(bPlatform);
            Long currentIdentityId = AuthCacheManager.getInstance().getUser().getIdentityId();
            //新增的渠道添加管理者
            bPlatformService.saveIdentityChannel(currentIdentityId, bPlatform.getId());
            if (currentIdentityId != Constants.ADMIN_IDENTITY_ID) {
                //添加超级管理员管理权限
                bPlatformService.saveIdentityChannel((long) Constants.ADMIN_IDENTITY_ID, bPlatform.getId());
            }
            addActionMessage("保存信息成功");
        }
        return "save";
    }

    public String delete() {
        bPlatformService.deletePlatform(id);
        return "delete";
    }

    public String platformNameIsExisted() {
        MapBean mb = search();
        Long platformNameCount = bPlatformService.getPlatformCountByName(mb);
        if (platformNameCount > 0) {
            try {
                platformNameIsExisted = new ByteArrayInputStream("1".getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                platformNameIsExisted = new ByteArrayInputStream("0".getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "platformNameIsExisted";
    }

    public Long getId() {
        return id;
    }

    public InputStream getPlatformNameIsExisted() {
        return platformNameIsExisted;
    }

    public void setPlatformNameIsExisted(InputStream platformNameIsExisted) {
        this.platformNameIsExisted = platformNameIsExisted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BPlatformService getBPlatformService() {
        return bPlatformService;
    }

    public void setBPlatformService(BPlatformService bPlatformService) {
        this.bPlatformService = bPlatformService;
    }

    public BPlatform getBPlatform() {
        return bPlatform;
    }

    public void setBPlatform(BPlatform bPlatform) {
        this.bPlatform = bPlatform;
    }

    public Page<BPlatform> getBPlatformPage() {
        return bPlatformPage;
    }

    public void setBPlatformPage(Page<BPlatform> bPlatformPage) {
        this.bPlatformPage = bPlatformPage;
    }

    public Integer getKeepSearchCondition() {
        return keepSearchCondition;
    }

    public void setKeepSearchCondition(Integer keepSearchCondition) {
        this.keepSearchCondition = keepSearchCondition;
    }

    public List<BPlatform> getBPlatforms() {
        return bPlatforms;
    }

    public BPlatform getbPlatform() {
        return bPlatform;
    }

    public void setbPlatform(BPlatform bPlatform) {
        this.bPlatform = bPlatform;
    }

    public Page<BPlatform> getbPlatformPage() {
        return bPlatformPage;
    }

    public void setbPlatformPage(Page<BPlatform> bPlatformPage) {
        this.bPlatformPage = bPlatformPage;
    }

    public List<BPlatform> getbPlatforms() {
        return bPlatforms;
    }

    public void setbPlatforms(List<BPlatform> bPlatforms) {
        this.bPlatforms = bPlatforms;
    }

    public List<SBalance> getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(List<SBalance> balanceList) {
        this.balanceList = balanceList;
    }


    public void setBPlatforms(List<BPlatform> bPlatforms) {
        this.bPlatforms = bPlatforms;
    }


    public Page<SBalance> getPageb() {
        return pageb;
    }

    public void setPageb(Page<SBalance> pageb) {
        this.pageb = pageb;
    }
}
