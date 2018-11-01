package core.module.orm;

import java.util.HashMap;

/**
 * 参数传递和类型转换封装类.
 *
 * @author yjli
 * @since 2010-03-23
 */
public class MapBean extends HashMap<String, Object> {
    public static final String APP_ID = "appId";
    public static final String CLIENT_TYPE = "clientType";
    public static final String PLATFORM_Id = "platformId";

    private static final long serialVersionUID = 1775432731349180989L;

    public MapBean() {
    }

    public String getString(Object key) {
        return String.valueOf(get(key));
    }

}
