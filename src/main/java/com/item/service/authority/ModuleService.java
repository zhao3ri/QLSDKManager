package com.item.service.authority;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.authority.ModuleDao;
import com.item.domain.authority.Module;

import core.module.orm.MapBean;
import core.module.orm.Page;

/**
 * 系统模块处理类
 * 
 * @author 郭建填
 * @since 2011-02-20
 */
@Service
@Transactional
public class ModuleService{
	@Autowired
	private AuthService as;
	
	@Autowired
	private DataService ds;
	
	@Autowired
	private ModuleDao moduleDao;

	public Page<Module> page(Page<Module> page,MapBean mb){
		return moduleDao.find(page, mb, "Module.count", "Module.page");
	}
	
	public List<Module> getModuleList(){		
		List<Module> moduleList = moduleDao.find("Module.listAll", null);
		this.list2map(moduleList);
		return moduleList;
	}
	
	/**
	 * 将list转为map，使用map取单条记录，速度更快
	 */
	public void list2map(List<Module> moduleList){
		if(moduleList!=null && !moduleList.isEmpty()){
			for (Module module:moduleList) {
				AuthCacheManage.moduleCacheMap.put(module.getId(), module);
			}
		}
	}
	
	/**
	 * 通过id从缓存中取值
	 * @param id
	 * @return
	 */
	public Module getModuleByID(Long id){
		if(AuthCacheManage.moduleCacheMap.isEmpty()){
			return null;
		}
		return AuthCacheManage.moduleCacheMap.get(id);
	}
	
	/**
	 * 取得包含模块权限的模块列表,用于初始化新建角色页面
	 * @return
	 */
	@Transactional( readOnly = true )
	public List<Module> getModuleListForCreate() {
		List<Module> moduleList = AuthCacheManage.moduleCacheList;
		if(moduleList!=null && !moduleList.isEmpty()){
			for (Module module:moduleList) {
				module.setAuthHtml(as.getAuthHtml(module.getId()));
				module.setDatasetHtml(ds.getDataListHtml(module.getId()));
			}
		}
		return moduleList;
	}
	
	/**
	 * 取得包含模块权限的模块列表,用于初始化修改角色页面
	 * @return
	 */
	@Transactional( readOnly = true )
	public List<Module> getModuleListForUpdate(Long roleID){
		List<Module> moduleList = AuthCacheManage.moduleCacheList;
		for (Module module : moduleList) {
			module.setAuthHtml(as.getUpdateAuthHtml(module.getId(), roleID));
			module.setDatasetHtml(ds.getUpdateDataListHtml(module.getId(), roleID));
		}
		return moduleList;
	}
	
	public void save(Module entity){
		moduleDao.save("Module.save", entity);
	}
	
	public void delete(String checkedIds) throws Exception{
		for(String checkedId : StringUtils.split(checkedIds,",")){
			moduleDao.delete("Module.delete", Long.valueOf(checkedId));
		}
	}
	
	public void update(Module entity){
		moduleDao.update("Module.update", entity);
	}
	
	public Module get(Long id){
		return moduleDao.get("Module.selectUniqueModuleById", id);
	}
	
	 public Module getEntity(Long id){
	        return moduleDao.get("Module.getEntity",id);
	    }
}