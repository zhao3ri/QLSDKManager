package com.item.web.action;

import com.item.domain.Game;
import com.item.service.authority.AuthCacheManager;
import com.item.utils.CookieUtils;
import core.module.utils.Struts2Utils;
import core.module.web.Struts2Action;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

class BaseAction extends Struts2Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAction.class);
    protected List<Game> currentIdentityGames;
    protected List<Long> gameIds;

    private void onCreate() {
        currentIdentityGames = AuthCacheManager.getInstance().getIdentityPermissionGameList();
        gameIds = AuthCacheManager.getInstance().getGameIds();
    }

    protected boolean initData() {
        onCreate();
        if (CollectionUtils.isEmpty(currentIdentityGames)) {
            try {
                Struts2Utils.getResponse().sendRedirect(Struts2Utils.getRequest().getContextPath()+"/common/403.jsp");
            } catch (IOException e) {
                LOGGER.error("rechargeRegion error", e);
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    protected long getFirstGameId() {
        long appId = currentIdentityGames.get(0).getId();
        String cookieAppId = CookieUtils.getCookieValue(Struts2Utils.getRequest(), "cookie_gameId");
        if (StringUtils.isNotBlank(cookieAppId)) {
            appId = Long.valueOf(cookieAppId);
        }
        CookieUtils.setCookieValue(Struts2Utils.getResponse(), "cookie_gameId", String.valueOf(appId));
        return appId;
    }

    public List<Game> getCurrentIdentityGames() {
        return currentIdentityGames;
    }

    public void setCurrentIdentityGames(List<Game> currentIdentityGames) {
        this.currentIdentityGames = currentIdentityGames;
    }

    public List<Long> getGameIds() {
        return gameIds;
    }

    public void setGameIds(List<Long> gameIds) {
        this.gameIds = gameIds;
    }
}
