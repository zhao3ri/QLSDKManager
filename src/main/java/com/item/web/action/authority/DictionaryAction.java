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
 * �����ֵ�� Action��.
 * <br/>
 *
 * @author liuxh
 * @version 1.0 2013-01-15 11:32:29
 * @since JDK 1.5
 */
@Action
public class DictionaryAction extends Struts2Action {

    private static final long serialVersionUID = 197266703365933779L;

    private static final String SUCCESS_MSG_FORMAT = "%s����Ϊ\"%s\"״̬�ɹ�!<br/>";
    private static final String UPDATE_ERROR_MSG_FORMAT = "�Ѿ���%s��ʹ��'%s',��Ҫɾ��,���%s����%s��Ϊ����%s��<br/>";
    private static final String UPDATE_USER = "�û�";
    private static final String UPDATE_GROUP = "С��";
    private static final String UPDATE_DEPARTMENT = "����";

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
     * ����MAP
     */
    private Map<String, String> dTypeMap = EnumUtil.getDTypeMap();

    /**
     * �б�
     *
     * @return
     */
    public String list() {
        MapBean mb = search();
        try {
            dictionaryPage = dictionaryService.page(dictionaryPage, mb);
        } catch (Exception e) {
            addActionMessage("��ȡ�б����" + e);
            logger.error("��ȡ�б����" + e);
        }
        return "list";
    }

    /**
     * �޸�/����
     *
     * @return
     */
    public String save() {

        if (dictionary != null) {

            MapBean mBean = new MapBean();
            mBean.put("dtype", dictionary.getDtype());
            mBean.put("dname", dictionary.getDname());
            Dictionary dt = dictionaryService.getforupdate(mBean);

            if (dt != null) {//��ʾ�Ѿ�������ͬ�����µ�ĳ������
                if (dictionary.getId() != null) {//�޸�
                    if (dictionary.getId().longValue() != dt.getId().longValue()) {//������ͬ�Ĳ�ΪҪ�޸ĵ�
                        addActionMessage("�����Ѿ����ڣ������޸�");
                        return "reload";
                    }
                } else {//���
                    addActionMessage("�����Ѿ����ڣ��������");
                    return "reload";
                }
            }

            if (dictionary.getId() != null) {
                try {
                    dictionaryService.update(dictionary);
                    addActionMessage("�޸���Ϣ�ɹ�");
                } catch (Exception e) {
                    addActionMessage("�޸���Ϣ����" + e);
                    logger.error("�޸���Ϣ����" + e);
                }
            } else {
                try {
                    dictionary.setInserttime(new Date());
                    if (dictionary.getDvalue() == null) {
                        dictionary.setDvalue("");
                    }
                    dictionaryService.save(dictionary);
                    addActionMessage("������Ϣ�ɹ�");
                } catch (Exception e) {
                    addActionMessage("������Ϣ����" + e);
                    logger.error("������Ϣ����" + e);
                }
            }
        }
        return "reload";
    }

    /**
     * ��ʼ��ҳ��
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
     * ɾ��
     *
     * @return
     */
    public String delete() {
        if (checkedIds != null) {
            try {
                dictionaryService.delete(checkedIds);
                addActionMessage("ɾ�����ݳɹ�");
            } catch (Exception e) {
                addActionMessage("ɾ�����ݳ���" + e);
                logger.error("ɾ�����ݳ���" + e);
            }
        } else {
            addActionMessage("��ѡ��Ҫɾ��������");
        }
        return "reload";
    }

    /**
     * �޸�״̬
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
                    if (flag == 0 && "department".equals(dictionary.getDtype())) { // ɾ������
                        boolean judge = false;
                        MapBean mb = new MapBean();
                        mb.put("did", Long.parseLong(checkedId));
                        mb.put("validstate", "-1");
                        List<User> tempUserList = us.list(mb);
                        if (tempUserList != null && tempUserList.size() > 0) {
                            sb.append(String.format(UPDATE_ERROR_MSG_FORMAT, UPDATE_USER, d.getDname(), UPDATE_USER, UPDATE_DEPARTMENT, UPDATE_DEPARTMENT));
//                            sb.append("�Ѿ����û���ʹ��'" + d.getDname() + "',��Ҫɾ��,����û����ڲ��Ÿ�Ϊ�������š�<br/>");
                            judge = true;
                        }

                        MapBean dmb = new MapBean();
                        dmb.put("dvalue", checkedId);
                        dmb.put("validstate", "-1");
                        List<Dictionary> tempDictionaryList = dictionaryService.list(dmb);
                        if (tempDictionaryList != null && tempDictionaryList.size() > 0) {
                            sb.append(String.format(UPDATE_ERROR_MSG_FORMAT, UPDATE_GROUP, d.getDname(), UPDATE_GROUP, UPDATE_DEPARTMENT, UPDATE_DEPARTMENT));
//                            sb.append("�Ѿ���С����ʹ��'" + d.getDname() + "',��Ҫɾ��,���С�����ڲ��Ÿ�Ϊ�������š�<br/>");
                            judge = true;
                        }

                        if (!judge) {
                            d.setState(flag);
                            dictionaryService.update(d);
                            sb.append(String.format(SUCCESS_MSG_FORMAT, d.getDname(), StateContext.getStateConfigs().get("operState").get(flag + "").getName()));
                        }
                    } else if (flag == 0 && "group".equals(dictionary.getDtype())) {// ɾ��С��
                        MapBean mb = new MapBean();
                        mb.put("gid", Long.parseLong(checkedId));
                        mb.put("validstate", "-1");
                        List<User> tempUserList = us.list(mb);
                        if (tempUserList != null && tempUserList.size() > 0) {
                            sb.append(String.format(UPDATE_ERROR_MSG_FORMAT, UPDATE_USER, d.getDname(), UPDATE_USER, UPDATE_GROUP, UPDATE_GROUP));
//                            sb.append("�Ѿ����û���ʹ��'" + d.getDname() + "',��Ҫɾ��,����û�����С���Ϊ����С�顣<br/>");
                        } else {
                            d.setState(flag);
                            dictionaryService.update(d);
                            sb.append(String.format(SUCCESS_MSG_FORMAT, d.getDname(), StateContext.getStateConfigs().get("operState").get(flag + "").getName()));
                        }
                    } else {// ����
                        d.setState(flag);
                        dictionaryService.update(d);
                        sb.append(String.format(SUCCESS_MSG_FORMAT, d.getDname(), StateContext.getStateConfigs().get("operState").get(flag + "").getName()));
                    }
                }
                addActionMessage(sb.toString());
            } catch (Exception e) {
                addActionMessage("�޸�״̬����" + e);
                logger.error("�޸�״̬����" + e);
            }
        } else {
            addActionMessage("��ѡ��Ҫ�޸�״̬������");
        }
        return "reload";
    }

    /**
     * ��ȡ��ѯ����
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
                // �������ö�ٵ�Ԫ�أ�����Ĭ���ǵ�һ��
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
            // ����Ĭ���ǵ�һ��
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