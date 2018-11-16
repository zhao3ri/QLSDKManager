package com.item.service.authority;

import java.util.ArrayList;
import java.util.List;

import com.item.domain.authority.IdentityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.authority.IdentityDataDao;

/**
 * 身份数据集权限业务处理类
 *
 * @author
 * @since 2013-10-12
 */
@Service
@Transactional
public class IdentityDataService {
    @Autowired
    private IdentityDataDao identityDataDao;

    /**
     * 获取全部的身份数据集权限信息
     *
     * @return
     */
    public List<IdentityData> getAllIdentityDataList() {
        return identityDataDao.find("IdentityData.listAll", null);
    }

    /**
     * 通过身份ID获取身份对应的数据集权限信息
     *
     * @param id
     * @return
     */
    public List<IdentityData> getIdentityDataListById(Long id) {
        List<IdentityData> identityDataList = new ArrayList<IdentityData>();
        if (id == null) {
            return identityDataList;
        }
        for (IdentityData rd : AuthCacheManager.getInstance().getIdentityDataCache()) {
            if (id.equals(rd.getIdentityId())) {
                identityDataList.add(rd);
            }
        }
        return identityDataList;
    }

    /**
     * 通过身份ID和数据集ID获取身份数据集信息
     *
     * @param id
     * @param datasetId
     * @return
     */
    public IdentityData getIdentityDataByIdentityAndData(Long id, Long datasetId) {
        IdentityData identityData = null;
        if (id == null || datasetId == null) {
            return identityData;
        }
        for (IdentityData rd : AuthCacheManager.getInstance().getIdentityDataCache()) {
            if (id.equals(rd.getIdentityId()) && datasetId.equals(rd.getDatasetId())) {
                identityData = rd;
                break;
            }
        }
        return identityData;
    }

    /**
     * 通过身份ID删除身份数据集权限信息
     *
     * @param id
     */
    public void deleteIdentityDataById(Long id) {
        List<IdentityData> delList = new ArrayList<IdentityData>();
        if (AuthCacheManager.getInstance().getIdentityDataCache() != null && !AuthCacheManager.getInstance().getIdentityDataCache().isEmpty()) {
            for (IdentityData rd : AuthCacheManager.getInstance().getIdentityDataCache()) {
                if (id != null && id.equals(rd.getIdentityId())) {
                    delList.add(rd);
                }
            }
        }
        AuthCacheManager.getInstance().getIdentityDataCache().removeAll(delList);
        identityDataDao.delete("IdentityData.deleteByIdentityId", id);
    }

    /**
     * 保存身份与数据集的关联信息到数据库和缓存
     *
     * @param identityData
     * @return
     */
    public Long saveIdentityIdData(IdentityData identityData) {
        Long id = (Long) identityDataDao.save("IdentityData.save", identityData);
        identityData.setId(id);//ibatis不像hibernate那样在保存完成后自动更新对象
        if (!AuthCacheManager.getInstance().getIdentityDataCache().contains(identityData)) {
            AuthCacheManager.getInstance().addIdentityData(identityData);
        }
        return id;
    }
}
