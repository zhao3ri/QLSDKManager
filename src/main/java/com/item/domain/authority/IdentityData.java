package com.item.domain.authority;

/**
 * 角色数据集关联表实体类
 *
 * @author Administrator
 * @since 2013-10-10
 */
public class IdentityData extends SystemModule {

    public static final Long LEVEL_PERSONAL = 1L;
    public static final Long LEVEL_GROUP = 2L;
    public static final Long LEVEL_DEPARTMENT = 3L;
    public static final Long LEVEL_ALL = 4L;

    private Long datasetId;
    private Long identityId;
    private Long level;


    public Long getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Long datasetId) {
        this.datasetId = datasetId;
    }

    public Long getIdentityId() {
        return identityId;
    }

    public void setIdentityId(Long identityId) {
        this.identityId = identityId;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

}
