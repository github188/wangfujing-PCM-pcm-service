package com.wangfj.product.category.domain.entity;

/**
 * 管理分类与工业分类关联表
 * @Class Name PcmCategoryRelation
 * @Author duanzhaole
 * @Create In 2015年7月2日
 */
public class PcmCategoryRelation {
    private Long sid;

    /**
     * 工业分类
     */
    private Long gyCategorySid;

    /**
     * 管理分类
     */
    private Long glCategorySid;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getGyCategorySid() {
        return gyCategorySid;
    }

    public void setGyCategorySid(Long gyCategorySid) {
        this.gyCategorySid = gyCategorySid;
    }

    public Long getGlCategorySid() {
        return glCategorySid;
    }

    public void setGlCategorySid(Long glCategorySid) {
        this.glCategorySid = glCategorySid;
    }
}