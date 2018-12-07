package core.module.orm;

import java.util.HashMap;

/**
 * 参数传递和类型转换封装类.
 *
 * @author yjli
 * @since 2010-03-23
 */
public class MapBean extends HashMap<String, Object> {
    public static final String GAME_ID = "gameId";
    public static final String GAME_IDS = "gameIds";
    public static final String GAME_NAME = "gameName";
    public static final String CLIENT_TYPE = "clientType";
    public static final String CHANNEL_ID = "channelId";
    public static final String CHANNEL_IDS = "channelIds";
    public static final String CHANNEL_NAME = "channelName";
    public static final String ZONE_ID = "zoneId";
    public static final String ZONE_IDS = "zoneIds";
    public static final String ZONE_NAME = "zoneName";
    public static final String STAT_DATE = "statDate";

    private static final long serialVersionUID = 1775432731349180989L;

    public MapBean() {
    }

    public String getString(Object key) {
        return String.valueOf(get(key));
    }

    public static MapBean getBean() {
        return new MapBean();
    }

    public static void cleanUp(MapBean bean) {
        if (bean != null) {
            bean.clear();
            bean = null;
        }
    }
}
