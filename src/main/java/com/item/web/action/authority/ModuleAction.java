package com.item.web.action.authority;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.item.domain.authority.Module;
import com.item.service.authority.ModuleService;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.web.Struts2Action;

/**
 * @author liuxh
 * @version 创建时间：2012-4-13 上午11:20:39
 *
 * 类说明：功能模块Action类
 *
 */
@Action
public class ModuleAction extends Struts2Action {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ModuleService moduleService;
	
	private Page<Module> page=new Page<Module>(10);
	private Module module;
	private String checkedIds;
	
	public String list(){
		try {
			MapBean mb = this.putSearchConditions();
			page = moduleService.page(page, mb);
		} catch (Exception e) {
			logger.error("获取模块列表出错：", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 设置搜索条件
	 * 
	 * @return
	 */
	private MapBean putSearchConditions() {
		MapBean mb = new MapBean();
		if (module != null) {
			if (module.getModuleName() != null && !"".equals(module.getModuleName())) {
				mb.put("moduleName", module.getModuleName());
			}
		}
		return mb;
	}
	
	public String delete(){
		try {
			moduleService.delete(checkedIds);
			addActionMessage("删除模块成功");
		} catch (Exception e) {
			logger.error("删除模块出错："+e);
			addActionMessage("删除模块出错："+e);
		}
		return RELOAD;
	}
	
	public String view(){
		if(module!=null&&module.getId()!=null){
			module=moduleService.get(module.getId());
		}else{
			module=new Module();
			module.setModuleOrder(0);
		}
		return SUCCESS;
	}
	
	public String save(){
		if(module.getId()==null){
			module.setModuleURL("shtml");
			moduleService.save(module);
		}else{
			moduleService.update(module);
		}
		return RELOAD;
	}

	public String getCheckedIds() {
		return checkedIds;
	}

	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Page<Module> getPage() {
		return page;
	}

	public void setPage(Page<Module> page) {
		this.page = page;
	}

}
