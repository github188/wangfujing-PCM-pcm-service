package com.wangfj.product.maindata.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 专柜商品属性表
 * @Class Name PcmShoppeProductParameters
 * @Author zhangxy
 * @Create In 2015年7月15日
 */
public class PcmShoppeProductParameters extends BaseEntity {
    private Long sid;
	/**
	 * 专柜商品编码
	 */
    private String shoppeProSid;
	/**
	 * 管理分类编码
	 */
    private String categorySid;
	/**
	 * 属性名
	 */
    private String propsName;
	/**
	 * 属性值
	 */
    private String propsValue;

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

    public String getPropsName() {
        return propsName;
    }

    public void setPropsName(String propsName) {
        this.propsName = propsName;
    }

    public String getPropsValue() {
        return propsValue;
    }

    public void setPropsValue(String propsValue) {
        this.propsValue = propsValue;
    }
}