package com.item.web.action;

import com.item.domain.Game;
import com.item.service.BGameService;
import com.item.utils.CookieUtils;
import core.module.utils.Struts2Utils;
import core.module.web.Struts2Action;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class BaseReportAction extends Struts2Action {
    @Autowired
    protected BGameService bGameService;
    protected List<Game> allGames;

    protected boolean initData() {
        allGames = bGameService.list(null);
        return true;
    }

    protected long getFirstGameId() {
        long appId = allGames.get(0).getId();
        String cookieAppId = CookieUtils.getCookieValue(Struts2Utils.getRequest(), "cookie_appId");
        if (StringUtils.isNotBlank(cookieAppId)) {
            appId = Long.valueOf(cookieAppId);
        }
        CookieUtils.setCookieValue(Struts2Utils.getResponse(), "cookie_appId", String.valueOf(appId));
        return appId;
    }

    public List<Game> getAllGames() {
        return allGames;
    }

    public void setAllGames(List<Game> allGames) {
        this.allGames = allGames;
    }

}
