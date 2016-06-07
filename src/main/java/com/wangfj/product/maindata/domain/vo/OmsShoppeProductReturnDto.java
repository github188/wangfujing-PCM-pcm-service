package com.wangfj.product.maindata.domain.vo;

import java.util.List;

/**
 * Created by wangxuan on 2016-06-07 0007.
 */
public class OmsShoppeProductReturnDto {

    private String shoppeProSid;//专柜商品编码

    private List<OmsCategoryDto> categoryDtoList;//工业分类list

    public String getShoppeProSid() {
        return shoppeProSid;
    }

    public void setShoppeProSid(String shoppeProSid) {
        this.shoppeProSid = shoppeProSid;
    }

    public List<OmsCategoryDto> getCategoryDtoList() {
        return categoryDtoList;
    }

    public void setCategoryDtoList(List<OmsCategoryDto> categoryDtoList) {
        this.categoryDtoList = categoryDtoList;
    }

    @Override
    public String toString() {
        return "OmsShoppeProductReturnDto{" +
                "shoppeProSid='" + shoppeProSid + '\'' +
                ", categoryDtoList=" + categoryDtoList +
                '}';
    }
}
