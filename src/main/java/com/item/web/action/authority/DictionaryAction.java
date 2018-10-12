/*
 * Copyright (c) 2010-2020 liuxh.
 * [Project:ZDOP,Id:DictionaryDao.java  2013-01-15 11:32:29 liuxh ]
 */
package com.item.web.action.authority;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.item.application.StateContext;
import com.item.constants.TypeConstant;
import com.item.domain.authority.User;
import com.item.domain.authority.Dictionary;
import com.item.service.authority.UserService;
import com.item.service.authority.DictionaryService;
import com.item.utils.EnumUtil;

import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.web.Struts2Action;

/**
 * 数据字典表 Action类.
 * <br/>
 *
 * @author liuxh
 * @version 1.0 2013-01-15 11:32:29
 * @since JDK 1.5
 */
@Action
public class DictionaryAction extends Struts2Action {

    private static final long serialVersionUID = 197266703365933779L;

    private static final String SUCCESS_MSG_FORMAT = "%s设置为/\"%s/\"状态成功!<br/>";
    private static final String UPDATE_ERROR_MSG_FORMAT = "已经有%s在使用'%s',如要删除,请把%s所在%s改为其它%s。<br/>";
    private static final String UPDATE_USER = "用户";
    private static final String UPDATE_GROUP = "小组";
    private static final String UPDATE_DEPARTMENT = "部门";

    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private UserService us;

    private Page<Dictionary> dictionaryPage = new Page<Dictionary>(10);
    private String checkedIds;
    private Dictionary dictionary;
    private Long id;
    private int flag;
    /**
     * 类型MAP
     */
    private Map<String, String> dTypeMap = EnumUtil.getDTypeMap();

    /**
     * 列表
     *
     * @return
     */
    public String list() {
        MapBean mb = search();
        try {
            dictionaryPage = dictionaryService.page(dictionaryPage, mb);
        } catch (Exception e) {
            addActionMessage("获取列表出错：" + e);
            logger.error("获取列表出错：" + e);
        }
        return "list";
    }

    /**
     * 修改/保存
     *
     * @return
     */
    public String save() {

        if (dictionary != null) {

            MapBean mBean = new MapBean();
            mBean.put("dtype", dictionary.getDtype());
            mBean.put("dname", dictionary.getDname());
            Dictionary dt = dictionaryService.getforupdate(mBean);

            if (dt != null) {//表示已经存在相同类型下的某个名称
                if (dictionary.getId() != null) {//修改
                    if (dictionary.getId().longValue() != dt.getId().longValue()) {//名称相同的不为要修改的
                        addActionMessage("名称已经存在，不能修改");
                        return "reload";
                    }
                } else {//添加
                    addActionMessage("名称已经存在，不能添加");
                    return "reload";
                }
            }

            if (dictionary.getId() != null) {
                try {
                    dictionaryService.update(dictionary);
                    addActionMessage("修改信息成功");
                } catch (Exception e) {
                    addActionMessage("修改信息出错：" + e);
                    logger.error("修改信息出错：" + e);
                }
            } else {
                try {
                    dictionary.setInserttime(new Date());
                    if (dictionary.getDvalue() == null) {
                        dictionary.setDvalue("");
                    }
                    dictionaryService.save(dictionary);
                    addActionMessage("保存信息成功");
                } catch (Exception e) {
                    addActionMessage("保存信息出错：" + e);
                    logger.error("保存信息出错：" + e);
                }
            }
        }
        return "reload";
    }

    /**
     * 初始化页面
     *
     * @return
     */
    public String handle() {
        if (id != null) {
            dictionary = dictionaryService.get(id);
        }
        return "handle";
    }

    /**
     * 删除
     *
     * @return
     */
    public String delete() {
        if (checkedIds != null) {
            try {
                dictionaryService.delete(checkedIds);
                addActionMessage("删除数据成功");
            } catch (Exception e) {
                addActionMessage("删除数据出错：" + e);
                logger.error("删除数据出错：" + e);
            }
        } else {
            addActionMessage("请选择要删除的数据");
        }
        return "reload";
    }

    /**
     * 修改状态
     *
     * @return
     */
    public String updateState() {
        StringBuilder sb = new StringBuilder();
        if (checkedIds != null) {
            try {
//        		System.out.println(dictionary.getDtype()+"-----------");
//	        	dictionaryService.updateState(checkedIds,flag);
                for (String checkedId : StringUtils.split(checkedIds, ",")) {
                    Dictionary d = dictionaryService.get(Long.parseLong(checkedId));
                    if (flag == 0 && "department".equals(dictionary.getDtype())) { // 删除部门
                        boolean judge = false;
                        MapBean mb = new MapBean();
                        mb.put("did", Long.parseLong(checkedId));
                        mb.put("validstate", "-1");
                        List<User> tempUserList = us.list(mb);
                        if (tempUserList != null && tempUserList.size() > 0) {
                            sb.append(String.format(UPDATE_ERROR_MSG_FORMAT, UPDATE_USER, d.getDname(), UPDATE_USER, UPDATE_DEPARTMENT, UPDATE_DEPARTMENT));
//                            sb.append("已经有用户在使用'" + d.getDname() + "',如要删除,请把用户所在部门改为其它部门。<br/>");
                            judge = true;
                        }

                        MapBean dmb = new MapBean();
                        dmb.put("dvalue", checkedId);
                        dmb.put("validstate", "-1");
                        List<Dictionary> tempDictionaryList = dictionaryService.list(dmb);
                        if (tempDictionaryList != null && tempDictionaryList.size() > 0) {
                            sb.append(String.format(UPDATE_ERROR_MSG_FORMAT, UPDATE_GROUP, d.getDname(), UPDATE_GROUP, UPDATE_DEPARTMENT, UPDATE_DEPARTMENT));
//                            sb.append("已经有小组在使用'" + d.getDname() + "',如要删除,请把小组所在部门改为其它部门。<br/>");
                            judge = true;
                        }

                        if (!judge) {
                            d.setState(flag);
                            dictionaryService.update(d);
                            sb.append(String.format(SUCCESS_MSG_FORMAT, d.getDname(), StateContext.getStateConfigs().get("operState").get(flag + "").getName()));
                        }
                    } else if (flag == 0 && "group".equals(dictionary.getDtype())) {// 删除小组
                        MapBean mb = new MapBean();
                        mb.put("gid", Long.parseLong(checkedId));
                        mb.put("validstate", "-1");
                        List<User> tempUserList = us.list(mb);
                        if (tempUserList != null && tempUserList.size() > 0) {
                            sb.append(String.format(UPDATE_ERROR_MSG_FORMAT, UPDATE_USER, d.getDname(), UPDATE_USER, UPDATE_GROUP, UPDATE_GROUP));
//                            sb.append("已经有用户在使用'" + d.getDname() + "',如要删除,请把用户所在小组改为其它小组。<br/>");
                        } else {
                            d.setState(flag);
                            dictionaryService.update(d);
                            sb.append(String.format(SUCCESS_MSG_FORMAT, d.getDname(), StateContext.getStateConfigs().get("operState").get(flag + "").getName()));
                        }
                    } else {// 其它
                        d.setState(flag);
                        dictionaryService.update(d);
                        sb.append(String.format(SUCCESS_MSG_FORMAT, d.getDname(), StateContext.getStateConfigs().get("operState").get(flag + "").getName()));
                    }
                }
                addActionMessage(sb.toString());
            } catch (Exception e) {
                addActionMessage("修改状态出错：" + e);
                logger.error("修改状态出错：" + e);
            }
        } else {
            addActionMessage("请选择要修改状态的数据");
        }
        return "reload";
    }

    /**
     * 获取查询条件
     *
     * @return
     */
    private MapBean search() {
        MapBean mb = new MapBean();
        if (dictionary != null) {
            if (dictionary.getId() != null) {
                mb.put("id", dictionary.getId());
            }
            if (dictionary.getDtype() != null && !"".equals(dictionary.getDtype())) {
                // 如果不是枚举的元素，设置默认是第一个
                if (!EnumUtil.checkDType(dictionary.getDtype())) {
                    dictionary.setDtype(TypeConstant.department.name());
                }
                mb.put("dtype", dictionary.getDtype());
            }
            if (dictionary.getDname() != null && !"".equals(dictionary.getDname())) {
                mb.put("dname", dictionary.getDname());
            }
            if (dictionary.getDepict() != null && !"".equals(dictionary.getDepict())) {
                mb.put("depict", dictionary.getDepict());
            }
            if (dictionary.getDvalue() != null && !"".equals(dictionary.getDvalue())) {
                mb.put("dvalue", dictionary.getDvalue());
            }
            if (dictionary.getState() != null) {
                mb.put("state", dictionary.getState());
            }
            if (dictionary.getDsort() != null) {
                mb.put("dsort", dictionary.getDsort());
            }
            if (dictionary.getInserttime() != null) {
                mb.put("inserttime", dictionary.getInserttime());
            }
        } else {
            // 设置默认是第一个
            dictionary = new Dictionary();
            dictionary.setDtype(TypeConstant.department.name());
            mb.put("dtype", dictionary.getDtype());
        }
        mb.put("validstate", "1");
        mb.put("orderby", "id desc");
        return mb;
    }

    public Page<Dictionary> getDictionaryPage() {
        return dictionaryPage;
    }

    public void setCheckedIds(String checkedIds) {
        this.checkedIds = checkedIds;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Map<String, String> getDTypeMap() {
        return dTypeMap;
    }

    public void setDTypeMap(Map<String, String> typeMap) {
        dTypeMap = typeMap;
    }
}