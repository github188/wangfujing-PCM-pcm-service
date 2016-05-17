package com.wangfj.product.maindata.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 专柜商品管理分类关联表
 * @Class Name PcmShoppeProductCategory
 * @Author zhangxy
 * @Create In 2015年7月15日
 */
public class PcmShoppeProductCategory extends BaseEntity {
    private Long sid;
	/**
	 * 专柜商品编码
	 */
    private String shoppeProSid;
	/**
	 * 管理分类编码
	 */
    private String categorySid;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getShoppeProSid() {
        return shoppeProSid;
    }

    public void setShoppeProSid(String shoppeProSid) {
        this.shoppeProSid = shoppeProSid;
    }

    public String getCategorySid() {
        return categorySid;
    }

    public void setCategorySid(String categorySid) {
        this.categorySid = categorySid;
    }
}