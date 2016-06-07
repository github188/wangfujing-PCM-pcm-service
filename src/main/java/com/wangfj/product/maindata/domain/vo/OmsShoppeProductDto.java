package com.wangfj.product.maindata.domain.vo;

/**
 * Created by wangxuan on 2016-06-06 0006.
 */
public class OmsShoppeProductDto {

    private String shoppeProSid;//专柜商品编码

    private String industryCategoryLevel;//需要查询工业分类的级数

    public String getShoppeProSid() {
        return shoppeProSid;
    }

    public void setShoppeProSid(String shoppeProSid) {
        this.shoppeProSid = shoppeProSid;
    }

    public String getIndustryCategoryLevel() {
        return industryCategoryLevel;
    }

    public void setIndustryCategoryLevel(String industryCategoryLevel) {
        this.industryCategoryLevel = industryCategoryLevel;
    }

    @Override
    public String toString() {
        return "OmsShoppeProductPara{" +
                "shoppeProSid='" + shoppeProSid + '\'' +
                ", industryCategoryLevel='" + industryCategoryLevel + '\'' +
                '}';
    }
}
