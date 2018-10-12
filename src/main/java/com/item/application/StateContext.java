package com.item.application;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.item.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.dom4j.Document;
import org.dom4j.Element;

import com.item.exception.ApplicationException;
import com.item.utils.FileUtil;
import com.item.utils.XmlUtil;

/**
 * @author liuxh
 * @version 创建时间：2013-1-16 下午03:07:12
 * <p>
 * 类说明：状态内容初始化
 */
public class StateContext {

    private static Map<String, Map<String, StateVo>> stateConfigs = null;

    /**
     * 状态项目初始化，该方法应在系统启动时调用
     */
    @SuppressWarnings("unchecked")
    public static void initStateConfigs(String path) {
        stateConfigs = new HashMap<String, Map<String, StateVo>>();

        File file = null;
        try {
            file = FileUtil.getFile(path);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Document doc = null;
        try {
            doc = XmlUtil.getDocument(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (doc == null) {
            throw new ApplicationException("状态配置文件 xml 不能转化为 document");
        }

        Element root = doc.getRootElement();
        Iterator<Element> it = root.elementIterator("state");
        while (it.hasNext()) {
            Element elem = it.next();
            String name = elem.attributeValue("name");
            String state = elem.attributeValue("state");
            stateConfigs.put(StringUtils.trim(name), stateToMap(StringUtils.trim(state)));
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, StateVo> stateToMap(String state) {

        List<StateVo> stateVoList = JsonUtil.stringToObject(state, new TypeReference<List<StateVo>>() {
        });
        Map<String, StateVo> stateVoMap = new HashMap<String, StateVo>();
        for (StateVo stateVo : stateVoList) {
            stateVoMap.put(stateVo.getId(), stateVo);
        }

        return stateVoMap;
    }

    public static Map<String, Map<String, StateVo>> getStateConfigs() {
        return stateConfigs;
    }
}
