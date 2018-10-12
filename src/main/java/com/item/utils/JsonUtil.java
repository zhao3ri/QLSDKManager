package com.item.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static ObjectMapper getMapper() {
        return new ObjectMapper();
    }

    public static String toJsonString(Object object) {
        try {
            return getMapper().writeValueAsString(object);
        } catch (Exception e) {
            logger.error("write " + object + " to string exception", e);
        }
        return null;
    }

    public static <T> T stringToObject(String jsonStr, Class<T> classz) {
        try {
            return getMapper().readValue(jsonStr, classz);
        } catch (Exception e) {
            logger.error("json string  to " + classz + " exception", e);
        }
        return null;
    }

    public static <T> T stringToObject(String content, TypeReference valueTypeRef) {
        try {
            return getMapper().readValue(content, valueTypeRef);
        } catch (IOException e) {
            logger.error("json string  to " + valueTypeRef + " exception", e);
        }
        return null;
    }
}
