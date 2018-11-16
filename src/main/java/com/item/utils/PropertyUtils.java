package com.item.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import com.item.exception.ApplicationException;


/**
 * Properties查询器.
 *
 * @author liuxh
 * @since 2011-07-08
 */
public class PropertyUtils {
    private static final StringBuffer URL = new StringBuffer();
    private static Properties properties = new Properties();

    private PropertyUtils() {
    }

    public static void loadFile(String file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            properties.load(fis);
        } catch (IOException e) {
            throw new ApplicationException(e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public static String get(String sql) {
        return properties.getProperty(sql);
    }

    public static Set<Object> getKeys() {
        return properties.keySet();
    }

    public static String getPath() {
        if (URL.toString() == null || URL.toString().isEmpty()) {
            String host = get("server.host");
            String port = get("server.port");
            String absPath = get("server.abs_path");
            String url = String.format("%s:%s", host, port);
            if (!StringUtil.isEmpty(absPath)) {
                url = String.format("%s/%s", url, absPath);
            }
            URL.append(url);
        }

        return URL.toString();
    }
}
