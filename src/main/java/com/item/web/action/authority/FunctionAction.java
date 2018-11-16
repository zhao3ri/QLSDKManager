package com.item.web.action.authority;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.item.domain.authority.Auth;
import com.item.domain.authority.Function;
import com.item.domain.authority.Module;
import com.item.service.authority.FunctionService;
import com.item.service.authority.ModuleService;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.web.Struts2Action;

/**
 * @author liuxh
 * @version 创建时间：2012-5-8 下午04:11:49
 * <p>
 * 类说明：功能Action类
 */
@Action
public class FunctionAction extends Struts2Action {

    private static final long serialVersionUID = 1L;

    @Autowired
    private FunctionService functionService;
    @Autowired
    private ModuleService moduleService;

    private Page<Function> page = new Page<Function>(10);
    private Function function;
    private String checkedIds;
    private List<Module> moduleList = new ArrayList<Module>();
    private Auth auth;
    private Long mid;

    public String list() {
        MapBean mb = search();
        if (mid != null && !"".equals(mid)) {
            mb.put("mid", mid);
            page = functionService.pageByModule(page, mb);
        } else {
            page = functionService.page(page, mb);
        }
        return SUCCESS;
    }

    private MapBean search() {
        MapBean mb = new MapBean();
        if (function != null) {
            if (function.getFunctionName() != null && !"".equals(function.getFunctionName())) {
                mb.put("functionName", function.getFunctionName());
            }
            if (function.getDescription() != null && !"".equals(function.getDescription())) {
                mb.put("description", function.getDescription());
            }
        }
        return mb;
    }

    public String delete() {
        try {
            functionService.delete(checkedIds);
            addActionMessage("删除功能成功");
        } catch (Exception e) {
            logger.error("删除功能出错：" + e);
            addActionMessage("删除功能出错：" + e);
        }
        return RELOAD;
    }

    public String view() {
        moduleList = moduleService.getModuleList();
        if (function != null && function.getId() != null) {
            function = functionService.get(function.getId());
        } else {
            function = new Function();
            function.setFunctionOrder(0);
        }
        return SUCCESS;
    }

    public String save() {
        if (function.getId() == null) {
            function.setIsFullPath(1);
            functionService.save(function, auth);
        } else {
            functionService.update(function);
        }
        return RELOAD;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public String getCheckedIds() {
        return checkedIds;
    }

    public void setCheckedIds(String checkedIds) {
        this.checkedIds = checkedIds;
    }

    public Page<Function> getPage() {
        return page;
    }

    public void setPage(Page<Function> page) {
        this.page = page;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }
}
