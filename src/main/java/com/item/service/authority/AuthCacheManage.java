package com.item.service.authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.item.domain.authority.Auth;
import com.item.domain.authority.Data;
import com.item.domain.authority.Function;
import com.item.domain.authority.Module;
import com.item.domain.authority.Role;
import com.item.domain.authority.RoleAuth;
import com.item.domain.authority.RoleData;

import core.module.orm.MapBean;

/**
 * 
 * 权限缓存管理类
 * @author guojt
 * @since 2011-02-21
 *
 */
@Service
public class AuthCacheManage {
	
	@Autowired
	private FunctionService fs;	//功能
	@Autowired
	private ModuleService ms;	//模块
	@Autowired
	private AuthService as;	//权限
	@Autowired
	private RoleService rs;	//角色
	@Autowired
	private RoleAuthService ras;	//角色权限
	@Autowired
	private DataService ds; //数据集权限业务处理类
	@Autowired
	private RoleDataService rds; //角色数据集权限业务处理类

	//缓存系统每个功能
	public static List<Function> functionCacheList=new ArrayList<Function>();
	public static Map<Long, Function> functionCacheMap = new HashMap<Long, Function>();
	
	//缓存系统模块
	public static List<Module> moduleCacheList=new ArrayList<Module>();
	public static Map<Long, Module> moduleCacheMap = new HashMap<Long, Module>();
	
	//缓存权限
	public static List<Auth> authCacheList=new ArrayList<Auth>();
	public static Map<Long, Auth> authCacheMap = new HashMap<Long, Auth>();
	
	//缓存将角色权限
	public static List<RoleAuth> roleAuthCaCheList=new ArrayList<RoleAuth>();
	
	//缓存数据集列表信息
	public static List<Data> dataCacheList = new ArrayList<Data>();
	public static Map<Long, Data> dataCacheMap = new HashMap<Long, Data>();
	
	//缓存角色数据集权限
	public static List<RoleData> roleDataCacheList = new ArrayList<RoleData>();
	
	//缓存所有权限(请求的URL)(过滤器使用，用于判断是否请求是否为受保护资源)
	public static Map<String, String> allAuthMap = new HashMap<String, String>();
	
	//缓存每个角色的权限(过滤器使用，用于判断是否有权限执行请求操作)
	public static Map<Long, Map<String,String>> roleAuthMap = new HashMap<Long, Map<String,String>>();
	
	//缓存每个角色的权限字符串。字符串以分号连接。形如1;2;3(首页使用，用于控制左侧菜单显示与隐藏)
	public static Map<Long, String> roleAuthStrMap = new HashMap<Long, String>();
	
	//初始化状态，用于初始化是否已初始化过
	private static boolean INIT_STATE = false;

	/**
	 * 初始化缓存
	 */
	public void init(){
		if(!INIT_STATE){
			System.out.println("==========================权限缓存开始=======================");
			functionCacheList = fs.getFunctionList();
			moduleCacheList = ms.getModuleList();
			authCacheList = as.getAuthList();
			roleAuthCaCheList = ras.getRoleAuthList();
			dataCacheList = ds.getAllDataList();
			roleDataCacheList = rds.getAllRoleDataList();
			
			this.putAllAuthToMap();
			this.refreshRoleAuthCache(0L);
			System.out.println("==========================权限缓存完毕=======================");
			//将初始化状态标记为true
			INIT_STATE =true;
		}
	}
	
	/**
	 * 清空缓存
	 */
	public void clear(){
		functionCacheList.clear();
		moduleCacheList.clear();
		authCacheList.clear();
		roleAuthCaCheList=new ArrayList<RoleAuth>();
		
		moduleCacheMap.clear();
		functionCacheMap.clear();
		authCacheMap.clear();
		allAuthMap.clear();
		roleAuthMap.clear();
		roleAuthStrMap.clear();
		
//		functionCacheList=new ArrayList<Function>();
//		functionCacheMap = new HashMap<Long, Function>();
//		moduleCacheList=new ArrayList<Module>();
//		moduleCacheMap = new HashMap<Long, Module>();
//		authCacheList=new ArrayList<Auth>();
//		authCacheMap = new HashMap<Long, Auth>();
//		roleAuthCaCheList=new ArrayList<RoleAuth>();
//		allAuthMap = new HashMap<String, String>();
//		roleAuthMap = new HashMap<Long, Map<String,String>>();
//		roleAuthStrMap = new HashMap<Long, String>();
		
		INIT_STATE =false;
	}
	
	/**
	 * 添加角色后角色权限表的数据会发现变化，需刷新之前的缓存数据，保持同步
	 */
	public void refreshRoleAuth(){
		roleAuthCaCheList = ras.getRoleAuthList();
	}
	
	/**
	 * 刷新某个角色的权限缓存
	 * @param roleID roleID=0时，刷新所有角色的权限缓存
	 */
	public void refreshRoleAuthCache(Long roleID){
		List<Role> roleList = new ArrayList<Role>();
		
		//roleID=0时，取出所有角色
		if(roleID==0){
			roleList = rs.getRoleList(new MapBean());
		}else {
			Role role = rs.getRoleById(roleID);
			roleList.add(role);
		}
		if(roleList!=null && !roleList.isEmpty()){
			//取出每个角色对应的权限
			for(Role role:roleList){
				List<RoleAuth> roleAuthList = ras.getRoleAuthListByRoleID(role.getId());
				this.putRoleAuthsToMap(role.getId(),roleAuthList);
			}
		}
	}
	
	/**
	 * 将每个角色的权限拼凑成完整的URL，保存在Map中
	 */
	private  void putRoleAuthsToMap(Long roleID,List<RoleAuth> roleAuthList){
		//保存每个角色的权限的map
		Map<String, String> map = new HashMap<String, String>(); 
		if(roleAuthList!=null && !roleAuthList.isEmpty()){
			
			for(RoleAuth roleAuth : roleAuthList){
				Auth auth = authCacheMap.get(roleAuth.getAuthID());
				Module module = moduleCacheMap.get(auth.getModuleID());
	    		Function function = functionCacheMap.get(auth.getFunctionID());
	    			    		
	    		//如果功能是不是完整访问路径，则权限=模块URL_功能，否则权限=功能
	    		if(function.getIsFullPath()==0){
	    			String key_value = module.getModuleURL() + "_" + function.getFunctionName();
	    			map.put(key_value,key_value);
	    		}else if(function.getIsFullPath()==1){
	    			if(function.getFunctionName().indexOf(";") != -1){
	    				String[] funs = function.getFunctionName().split(";");
	    				for(String temp:funs){
	    					map.put(temp,temp);
	    				}
	    			}else{
	    				map.put(function.getFunctionName(),function.getFunctionName());
	    			}
	    		}
	    	}
			//将每个角色的权限map添加到总的map中
			roleAuthMap.put(roleID, map);
			roleAuthStrMap.put(roleID, map2String(map));
		}
	}
	
	/**
	 * 将所有权限拼凑成完整的URL，保存在Map中
	 */
	private void putAllAuthToMap(){
		if(authCacheList!=null && !authCacheList.isEmpty()){
			for(Auth auth : authCacheList){
	    		Module module = moduleCacheMap.get(auth.getModuleID());
	    		Function function = functionCacheMap.get(auth.getFunctionID());
	    		if (module == null || function==null) {
					continue;
				}

	    		//如果功能是不是完整访问路径，则权限=模块URL_功能，否则权限=功能
	    		if(function.getIsFullPath()==0){
	    			String key_value = module.getModuleURL() + "_" + function.getFunctionName();
	    			allAuthMap.put(key_value,key_value);
	    		}else if(function.getIsFullPath()==1){
	    			if(function.getFunctionName().indexOf(";") != -1){
	    				String[] funs = function.getFunctionName().split(";");
	    				for(String temp:funs){
	    					allAuthMap.put(temp,temp);
	    				}
	    			}else{
	    				allAuthMap.put(function.getFunctionName(),function.getFunctionName());
	    			}
	    		}
	    	}
		}
	}
	
	/**
	 * 将角色的权限拼凑成字符串，以分号连接。形如1;2;3
	 * @param roleAuthMap
	 * @return
	 */
	private static String map2String(Map<String, String> roleAuthMap){
		StringBuffer sb = new StringBuffer();
		if(roleAuthMap!=null && !roleAuthMap.isEmpty()){
			for(String key:roleAuthMap.keySet()){
				sb.append(key).append(";");
			}
		}
		return sb.toString();
	}	
}
