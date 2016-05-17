package com.wangfj.product.brand.domain.entity;

public class PcmBrandAlias {
    private Long sid;

    private Long brandSid;//集团品牌SID

    private String brandAlias;//别名

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getBrandSid() {
        return brandSid;
    }

    public void setBrandSid(Long brandSid) {
        this.brandSid = brandSid;
    }

    public String getBrandAlias() {
        return brandAlias;
    }

    public void setBrandAlias(String brandAlias) {
        this.brandAlias = brandAlias == null ? null : brandAlias.trim();
    }
}