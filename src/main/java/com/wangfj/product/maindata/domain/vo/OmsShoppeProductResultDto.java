package com.wangfj.product.maindata.domain.vo;

/**
 * Created by wangxuan on 2016-06-06 0006.
 */
public class OmsShoppeProductResultDto {

    private String shoppeProSid;//专柜商品编码

    private String industryCategoryCode;//工业分类编码

    private String industryCategoryLevel;//工业分类级别

    private String categoryParentSid;//工业分类的父类的sid

    public String getShoppeProSid() {
        return shoppeProSid;
    }

    public void setShoppeProSid(String shoppeProSid) {
        this.shoppeProSid = shoppeProSid;
    }

    public String getIndustryCategoryCode() {
        return industryCategoryCode;
    }

    public void setIndustryCategoryCode(String industryCategoryCode) {
        this.industryCategoryCode = industryCategoryCode;
    }

    public String getIndustryCategoryLevel() {
        return industryCategoryLevel;
    }

    public void setIndustryCategoryLevel(String industryCategoryLevel) {
        this.industryCategoryLevel = industryCategoryLevel;
    }

    public String getCategoryParentSid() {
        return categoryParentSid;
    }

    public void setCategoryParentSid(String categoryParentSid) {
        this.categoryParentSid = categoryParentSid;
    }

    @Override
    public String toString() {
        return "OmsShoppeProductResultDto{" +
                "shoppeProSid='" + shoppeProSid + '\'' +
                ", industryCategoryCode='" + industryCategoryCode + '\'' +
                ", industryCategoryLevel='" + industryCategoryLevel + '\'' +
                ", categoryParentSid='" + categoryParentSid + '\'' +
                '}';
    }
}
