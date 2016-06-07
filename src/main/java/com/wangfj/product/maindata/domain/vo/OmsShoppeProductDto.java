package com.wangfj.product.maindata.domain.vo;

/**
 * Created by wangxuan on 2016-06-06 0006.
 */
public class OmsShoppeProductDto {

    private String shoppeProSid;//专柜商品编码

    public String getShoppeProSid() {
        return shoppeProSid;
    }

    public void setShoppeProSid(String shoppeProSid) {
        this.shoppeProSid = shoppeProSid;
    }

    @Override
    public String toString() {
        return "OmsShoppeProductDto{" +
                "shoppeProSid='" + shoppeProSid + '\'' +
                '}';
    }
}
