package com.item.web.action.authority;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.item.domain.BPlatform;
import com.item.domain.authority.Identity;
import com.item.service.BPlatformService;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.item.domain.Game;
import com.item.domain.authority.Module;
import com.item.service.BGameService;
import com.item.service.SysGameManagerService;
import com.item.service.authority.AuthCacheManager;
import com.item.service.authority.ModuleService;
import com.item.service.authority.IdentityService;
import com.item.service.authority.DictionaryService;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.utils.Struts2Utils;
import core.module.web.Struts2Action;

/**
 * 身份管理控制类
 *
 * @author guojt
 * @since 2011-02-20
 */
@Action
public class IdentityAction extends Struts2Action {

    private static final long serialVersionUID = 1L;

    @Autowired
    private IdentityService is;
    @Autowired
    private ModuleService ms;
    @Autowired
    private AuthCacheManager acm;
    @Autowired
    private DictionaryService ds;
    @Resource
    private SysGameManagerService gameManagerService;
    @Resource
    private BGameService gameService;
    @Autowired
    private BPlatformService channelService;

    private Identity identity;
    private Identity searchIdentity;
    private Long[] authIds;
    private Long[] gameIds;
    private Long[] channelIds;
    private List<Identity> identityList;
    private List<Module> moduleList;
    private List<Game> games;
    private Page<Identity> page = new Page<Identity>(10);
    private String checkedIds;
    private String name;
    private Long id;
    private List<String> datasetLevels;
    private List<BPlatform> channelList;

    /**
     * 获取身份列表
     */
    public String list() {
        try {
            MapBean mb = this.putSearchConditions();
            page = is.getIdentityPage(page, mb);
            for (Identity item : page.getResult()) {
                item.setAuthGames(gameService.getGameList(item.getId()));
            }
        } catch (Exception e) {
            logger.error("获取身份列表出错：", e);
        }
        return SUCCESS;
    }

    /**
     * 设置搜索条件
     *
     * @return
     */
    private MapBean putSearchConditions() {
        MapBean mb = new MapBean();
        if (searchIdentity != null) {
            if (searchIdentity.getName() != null && !"".equals(searchIdentity.getName())) {
                mb.put("identityId", searchIdentity.getName());
            }
        }
        return mb;
    }

    /**
     * 初始化新建身份页面
     */
    public String create() {
        moduleList = ms.getModuleListWithHtml(null);
        games = gameService.getGameList();
        channelList = channelService.getAllPlatform();
        return SUCCESS;
    }

    /**
     * 添加/更新身份
     *
     * @return
     */
    public String save() {
        // id为空，则表示添加,否则为修改
        if (identity.getId() == null) {
            try {
                Long identityId = is.createIdentity(identity, authIds, datasetLevels);
                acm.refreshPermission();    //刷新之前的身份权限缓存数据，保持同步
                acm.refreshPermissionCache(identityId);    //添加新的身份后，刷新该身份权限缓存

                gameManagerService.save(identityId, gameIds);
                channelService.saveIdentityChannel(identityId, channelIds);
                System.err.println(channelIds);

                addActionMessage("新增身份成功");
            } catch (Exception e) {
                addActionMessage("新增身份出错：" + e);
                logger.error("新增身份出错：" + e);
            }
        } else {
            try {
                is.updateIdentity(identity, authIds, datasetLevels);
                acm.refreshPermission();    //刷新之前的身份权限缓存数据，保持同步
                acm.refreshPermissionCache(identity.getId());    //刷新该身份的权限缓存

                if (gameIds != null && gameIds.length > 0) {
                    gameManagerService.deleteByIdentityId(identity.getId());
                    gameManagerService.save(identity.getId(), gameIds);
                }
                channelService.saveIdentityChannel(identity.getId(), channelIds);
                addActionMessage("身份修改成功");
            } catch (Exception e) {
                e.printStackTrace();
                addActionMessage("身份修改出错：" + e);
                logger.error("身份修改出错：" + e);
            }
        }
        return RELOAD;

    }

    /**
     * 初始化更新身份页面
     *
     * @return
     */
    public String update() {
        identity = is.getIdentityById(identity.getId());
        moduleList = ms.getModuleListWithHtml(identity.getId());
        channelList = channelService.getIdentityChannelList(identity.getId());

        games = gameService.getGameList(null);

        List<Long> authGameIds = gameManagerService.getGameIdsByIdentityId(identity.getId());
        Map<String, String> map = new HashMap<String, String>();
        for (Long gameId : authGameIds) {
            map.put(gameId.toString(), "");
        }
        if (!CollectionUtils.isEmpty(games)) {
            for (Game game : games) {
                if (null != map.get(game.getId().toString())) {
                    game.setIsAuth(1);
                }
            }
        }
        return SUCCESS;
    }

    /**
     * 批量删除身份
     *
     * @return
     */
    public String delete() {
        try {
            is.deleteIdentities(checkedIds);
            channelService.deleteIdentitiesChannel(checkedIds);
            addActionMessage("删除身份成功");
        } catch (Exception e) {
            logger.error("删除身份出错：" + e);
            addActionMessage("删除身份出错：" + e);
        }
        return RELOAD;
    }

    /**
     * 检查身份名是否存在
     *
     * @return
     */
    public String checkIdentityName() {
        boolean flag = false;
        try {
            Identity tempIdentity = is.isNameExist(name);
            if (id != null) {
                if (tempIdentity != null && id.longValue() != tempIdentity.getId().longValue()) {
                    flag = true;
                } else {
                    flag = false;
                }
            } else {
                if (tempIdentity != null) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            Struts2Utils.renderText(flag + "");
        } catch (Exception e) {
            logger.error("检查身份名是否存在出错：" + e.getMessage(), e);
        }
        return null;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public List<Identity> getIdentityList() {
        return identityList;
    }

    public void setIdentityList(List<Identity> identityList) {
        this.identityList = identityList;
    }

    public Identity getSearchIdentity() {
        return searchIdentity;
    }

    public void setSearchIdentity(Identity searchIdentity) {
        this.searchIdentity = searchIdentity;
    }

    public Long[] getAuthIds() {
        return authIds;
    }

    public void setAuthIds(Long[] authIds) {
        this.authIds = authIds;
    }

    public Page<Identity> getPage() {
        return page;
    }

    public void setPage(Page<Identity> page) {
        this.page = page;
    }

    public String getCheckedIds() {
        return checkedIds;
    }

    public void setCheckedIds(String checkedIds) {
        this.checkedIds = checkedIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getDatasetLevels() {
        return datasetLevels;
    }

    public void setDatasetLevels(List<String> datasetLevels) {
        this.datasetLevels = datasetLevels;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Long[] getGameIds() {
        return gameIds;
    }

    public void setGameIds(Long[] gameIds) {
        this.gameIds = gameIds;
    }

    public Long[] getChannelIds() {
        return channelIds;
    }

    public void setChannelIds(Long[] channelIds) {
        this.channelIds = channelIds;
    }

    public List<BPlatform> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<BPlatform> channelList) {
        this.channelList = channelList;
    }
}
