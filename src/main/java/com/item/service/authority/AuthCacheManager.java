package com.item.service.authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.item.domain.authority.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.item.domain.authority.Identity;

import core.module.orm.MapBean;

/**
 * 权限缓存管理类
 *
 * @author guojt
 * @since 2011-02-21
 */
@Service
public class AuthCacheManager {
    @Autowired
    private FunctionService fs;    //功能
    @Autowired
    private ModuleService ms;    //模块
    @Autowired
    private AuthService as;    //权限
    @Autowired
    private IdentityService is;    //身份
    @Autowired
    private IdentityPermissionService ips;    //身份权限
    @Autowired
    private DataService ds; //数据集权限业务处理类
    @Autowired
    private IdentityDataService ids; //身份数据集权限业务处理类

    private static AuthCacheManager manager;

    //缓存系统每个功能
    private List<Function> functionCacheList;
    private Map<Long, Function> functionCacheMap;

    //缓存系统模块
    private List<Module> moduleCacheList;
    private Map<Long, Module> moduleCacheMap;

    //缓存权限
    private List<Auth> permissionCacheList;
    private Map<Long, Auth> permissionCacheMap;

    //缓存身份权限
    private List<IdentityPermission> identityPermissionCacheList;

    //缓存数据集列表信息
    private List<Data> dataCacheList;
    private Map<Long, Data> dataCacheMap;

    //缓存身份数据集权限
    private List<IdentityData> identityDataCacheList;

    //缓存所有权限(请求的URL)(过滤器使用，用于判断是否请求是否为受保护资源)
    private Map<String, String> allPermissionMap;

    //缓存每个身份的权限(过滤器使用，用于判断是否有权限执行请求操作)
    private Map<Long, Map<String, String>> identityPermissionMap;

    //缓存每个身份的权限字符串。字符串以分号连接。形如1;2;3(首页使用，用于控制左侧菜单显示与隐藏)
    private Map<Long, String> identityPermissionStrMap;

    //初始化状态，用于初始化是否已初始化过
    private boolean initState = false;

    private AuthCacheManager() {
        functionCacheList = new ArrayList<>();
        functionCacheMap = new HashMap<>();
        moduleCacheList = new ArrayList<>();
        moduleCacheMap = new HashMap<>();
        permissionCacheList = new ArrayList<>();
        permissionCacheMap = new HashMap<>();
        identityPermissionCacheList = new ArrayList<>();
        dataCacheList = new ArrayList<>();
        dataCacheMap = new HashMap<>();
        identityDataCacheList = new ArrayList<>();
        allPermissionMap = new HashMap<>();
        identityPermissionMap = new HashMap<>();
        identityPermissionStrMap = new HashMap<>();
    }

    public static AuthCacheManager getInstance() {
        synchronized (AuthCacheManager.class) {
            if (manager == null) {
                manager = new AuthCacheManager();
            }
        }
        return manager;
    }

    public synchronized static AuthCacheManager create(AuthCacheManager authCacheManager) {
        if (manager == null) {
            manager = authCacheManager;
        }
        return manager;
    }

    /**
     * 初始化缓存
     */
    public void init() {
        if (!initState) {
            System.out.println("==========================权限缓存开始=======================");
            functionCacheList = fs.getFunctionList();
            moduleCacheList = ms.getModuleList();
            permissionCacheList = as.getAuthList();
            identityPermissionCacheList = ips.getIdentityPermissionList();
            dataCacheList = ds.getAllDataList();
            identityDataCacheList = ids.getAllIdentityDataList();

            this.putAllPermissionToMap();
            this.refreshPermissionCache(0L);
            System.out.println("==========================权限缓存完毕=======================");
            //将初始化状态标记为true
            initState = true;
        }
    }

    /**
     * 清空缓存
     */
    public void clear() {
        functionCacheList.clear();
        moduleCacheList.clear();
        permissionCacheList.clear();
        identityPermissionCacheList = new ArrayList<>();

        moduleCacheMap.clear();
        functionCacheMap.clear();
        permissionCacheMap.clear();
        allPermissionMap.clear();
        identityPermissionMap.clear();
        identityPermissionStrMap.clear();

        initState = false;
    }

    public Map<String, String> getAllPermissions() {
        return allPermissionMap;
    }

    public String getPermissionText(long identityId) {
        return identityPermissionStrMap.get(identityId);
    }

    public Map<String, String> getIdentityPermissions(long identityId) {
        return identityPermissionMap.get(identityId);
    }

    public List<IdentityPermission> getIdentityPermissionCache() {
        return identityPermissionCacheList;
    }

    public void putPermission2Cache(Auth auth) {
        permissionCacheMap.put(auth.getId(), auth);
    }

    public Auth getPermissionByCache(long id) {
        if (permissionCacheMap.isEmpty()) {
            return null;
        }
        return permissionCacheMap.get(id);
    }

    public List<Auth> getPermissionsByModuleId(long moduleId) {
        List<Auth> authList = new ArrayList<>();
        if (permissionCacheList != null && !permissionCacheList.isEmpty()) {
            for (Auth auth : permissionCacheList) {
                if (auth.getModuleID().equals(moduleId)) {
                    authList.add(auth);
                }
            }
        }
        return authList;
    }

    public void putModule2Cache(Module module) {
        moduleCacheMap.put(module.getId(), module);
    }

    public Module getModuleByCache(long id) {
        if (moduleCacheMap.isEmpty()) {
            return null;
        }
        return moduleCacheMap.get(id);
    }

    public List<Module> getModules() {
        return moduleCacheList;
    }

    public void putFunction2Cache(Function function) {
        functionCacheMap.put(function.getId(), function);
    }

    public Function getFunctionByCache(long id) {
        if (functionCacheMap.isEmpty()) {
            return null;
        }
        return functionCacheMap.get(id);
    }

    public Function getFunctionByName(String functionName) {
        if (functionCacheList == null || functionCacheList.isEmpty()) {
            return null;
        }
        Function fun = null;
        for (Function f : functionCacheList) {
            if (f.getFunctionName().equals(functionName)) {
                fun = f;
                break;
            }
        }
        return fun;
    }

    public long getLevelByDatasetId(long identityId, long datasetId) {
        if (identityDataCacheList != null && !identityDataCacheList.isEmpty()) {
            for (IdentityData rd : identityDataCacheList) {
                if (rd.getDatasetId().equals(datasetId) && rd.getIdentityId().equals(identityId)) {
                    return rd.getLevel();
                }
            }
        }
        return IdentityData.LEVEL_ALL;
    }

    public Long getDatasetIdByKey(String key) {
        Long datasetId = null;
        if (dataCacheList != null && !dataCacheList.isEmpty()) {
            for (Data data : dataCacheList) {
                if (data.getDatasetKey().equals(key)) {
                    datasetId = data.getId();
                    break;
                }
            }
        }
        return datasetId;
    }

    public List<IdentityData> getIdentityDataCache() {
        return identityDataCacheList;
    }

    public void addIdentityData(IdentityData data) {
        identityDataCacheList.add(data);
    }

    public void putData2Cache(Data data) {
        dataCacheMap.put(data.getId(), data);
    }

    public List<Data> getDataCache() {
        return dataCacheList;
    }

    /**
     * 添加身份后身份权限表的数据会发现变化，需刷新之前的缓存数据，保持同步
     */
    public void refreshPermission() {
        identityPermissionCacheList = ips.getIdentityPermissionList();
    }

    /**
     * 刷新某个身份的权限缓存
     *
     * @param identityId identityId=0时，刷新所有身份的权限缓存
     */
    public void refreshPermissionCache(Long identityId) {
        List<Identity> identityList = new ArrayList<Identity>();

        //identityId=0时，取出所有身份
        if (identityId == 0) {
            identityList = is.getIdentityList(new MapBean());
        } else {
            Identity identity = is.getIdentityById(identityId);
            identityList.add(identity);
        }
        if (identityList != null && !identityList.isEmpty()) {
            //取出每个身份对应的权限
            for (Identity identity : identityList) {
                List<IdentityPermission> identityPermissionList = ips.getIdentityPermissionListById(identity.getId());
                this.putPermissionsToMap(identity.getId(), identityPermissionList);
            }
        }
    }

    /**
     * 将每个身份的权限拼凑成完整的URL，保存在Map中
     */
    private void putPermissionsToMap(Long identityId, List<IdentityPermission> identityPermissionList) {
        //保存每个身份的权限的map
        Map<String, String> map = new HashMap<String, String>();
        if (identityPermissionList != null && !identityPermissionList.isEmpty()) {

            for (IdentityPermission identityPermission : identityPermissionList) {
                Auth auth = permissionCacheMap.get(identityPermission.getAuthId());
                Module module = moduleCacheMap.get(auth.getModuleID());
                Function function = functionCacheMap.get(auth.getFunctionID());
                putPermission(function, module, map);
            }
            //将每个身份的权限map添加到总的map中
            identityPermissionMap.put(identityId, map);
            identityPermissionStrMap.put(identityId, map2String(map));
        }
    }

    /**
     * 将所有权限拼凑成完整的URL，保存在Map中
     */
    private void putAllPermissionToMap() {
        if (permissionCacheList != null && !permissionCacheList.isEmpty()) {
            for (Auth auth : permissionCacheList) {
                Module module = moduleCacheMap.get(auth.getModuleID());
                Function function = functionCacheMap.get(auth.getFunctionID());
                if (module == null || function == null) {
                    continue;
                }
                putPermission(function, module, allPermissionMap);
            }
        }
    }

    private void putPermission(Function function, Module module, Map map) {
        //如果功能是不是完整访问路径，则权限=模块URL_功能，否则权限=功能
        if (function.getIsFullPath() == 0) {
            String key_value = module.getModuleURL() + "_" + function.getFunctionName();
            map.put(key_value, key_value);
        } else if (function.getIsFullPath() == 1) {
            if (function.getFunctionName().indexOf(";") != -1) {
                String[] funs = function.getFunctionName().split(";");
                for (String temp : funs) {
                    map.put(temp, temp);
                }
            } else {
                map.put(function.getFunctionName(), function.getFunctionName());
            }
        }
    }

    /**
     * 将身份的权限拼凑成字符串，以分号连接。形如1;2;3
     *
     * @param roleAuthMap
     * @return
     */
    private static String map2String(Map<String, String> roleAuthMap) {
        StringBuffer sb = new StringBuffer();
        if (roleAuthMap != null && !roleAuthMap.isEmpty()) {
            for (String key : roleAuthMap.keySet()) {
                sb.append(key).append(";");
            }
        }
        return sb.toString();
    }
}
