package com.item.service.authority;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.authority.FunctionDao;
import com.item.domain.authority.Auth;
import com.item.domain.authority.Function;

import core.module.orm.MapBean;
import core.module.orm.Page;

/**
 * 模块功能逻辑类
 *
 * @author 郭建填
 * @since 2011-02-20
 */
@Service
@Transactional
public class FunctionService {
    @Autowired
    private FunctionDao functionDao;
    @Autowired
    private AuthService authService;
    @Autowired
    private IdentityPermissionService identityPermissionService;

    /**
     * 取所有功能
     *
     * @return
     */
    public List<Function> getFunctionList() {
        List<Function> functionList = functionDao.find("Function.listAll", null);
        this.list2map(functionList);
        return functionList;
    }

    /**
     * 将list转为map,使用map取单条记录，速度更快
     */
    public void list2map(List<Function> functionList) {
        if (functionList != null && !functionList.isEmpty()) {
            for (Function function : functionList) {
                AuthCacheManager.getInstance().putFunction2Cache(function);
            }
        }
    }

    /**
     * 通过ID从缓存中取功能
     *
     * @return
     */
    public Function getFunctionByID(Long id) {
        return AuthCacheManager.getInstance().getFunctionByCache(id);
    }

    public Function getFunctionByName(String functionName) {
        return AuthCacheManager.getInstance().getFunctionByName(functionName);
    }

    public Page<Function> page(Page<Function> page, MapBean mb) {
        return functionDao.find(page, mb, "Function.count", "Function.page");
    }

    public Page<Function> pageByModule(Page<Function> page, MapBean mb) {
        return functionDao.find(page, mb, "Function.countByModule", "Function.pageByModule");
    }

    public void save(Function entity, Auth auth) {
        Long fid = (Long) functionDao.save("Function.save", entity);
        auth.setFunctionID(fid);
        authService.save(auth);
    }

    public void delete(String checkedIds) throws Exception {
        for (String checkedId : StringUtils.split(checkedIds, ",")) {
            functionDao.delete("Function.delete", Long.valueOf(checkedId));

            Auth auth = authService.getAuthByFunctionID(Long.valueOf(checkedId));
            identityPermissionService.deleteByAuthID(auth.getId());

            authService.delete(Long.valueOf(checkedId));
        }
    }

    public void update(Function entity) {
        functionDao.update("Function.update", entity);
    }

    public Function get(Long id) {
        return functionDao.get("Function.selectUniqueFunctionById", id);
    }

    public Function getEntity(Long id) {
        return functionDao.get("Function.getEntity", id);
    }
}
