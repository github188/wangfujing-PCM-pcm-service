package com.wangfj.product.brand.domain.vo;

public class GenerateBrandCodeDto {

    private Integer brandType;// 品牌类型

    private Integer shopType;// 门店品牌的门店类型

    private String codeStart;//品牌编码开头的数字

    public Integer getBrandType() {
        return brandType;
    }

    public void setBrandType(Integer brandType) {
        this.brandType = brandType;
    }

    public Integer getShopType() {
        return shopType;
    }

    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }

    public String getCodeStart() {
        return codeStart;
    }

    public void setCodeStart(String codeStart) {
        this.codeStart = codeStart;
    }

    @Override
    public String toString() {
        return "GenerateBrandCodeDto{" +
                "brandType=" + brandType +
                ", shopType=" + shopType +
                ", codeStart='" + codeStart + '\'' +
                '}';
    }
}
